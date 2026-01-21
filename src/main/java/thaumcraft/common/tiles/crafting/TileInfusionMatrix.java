package thaumcraft.common.tiles.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.tiles.TileThaumcraft;
import thaumcraft.init.ModBlockEntities;
import thaumcraft.init.ModBlocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Infusion altar matrix tile entity - the central block of the infusion altar.
 * Manages infusion crafting, stability, and instability events.
 */
public class TileInfusionMatrix extends TileThaumcraft implements IAspectContainer {

    // Altar state
    public boolean active = false;
    public boolean crafting = false;
    public boolean checkSurroundings = true;

    // Stability system
    public int stabilityCap = 25;
    public float stability = 0.0f;
    public float stabilityReplenish = 0.0f;
    public float costMult = 1.0f;

    // Recipe tracking
    private AspectList recipeEssentia = new AspectList();
    private List<ItemStack> recipeIngredients = new ArrayList<>();
    private ItemStack recipeInput = ItemStack.EMPTY;
    private ItemStack recipeOutput = ItemStack.EMPTY;
    private int recipeInstability = 0;
    private String recipePlayer = null;

    // Tick counter
    private int count = 0;
    private int cycleTime = 20;
    private int countDelay = 10;

    // Client-side animation
    public int craftCount = 0;
    public float startUp = 0.0f;

    // Cached pedestal positions
    private List<BlockPos> pedestals = new ArrayList<>();

    public TileInfusionMatrix(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public TileInfusionMatrix(BlockPos pos, BlockState state) {
        this(ModBlockEntities.INFUSION_MATRIX.get(), pos, state);
    }

    // ==================== NBT ====================

    @Override
    protected void writeSyncNBT(CompoundTag tag) {
        super.writeSyncNBT(tag);
        tag.putBoolean("Active", active);
        tag.putBoolean("Crafting", crafting);
        tag.putFloat("Stability", stability);
        tag.putInt("RecipeInstability", recipeInstability);
        recipeEssentia.writeToNBT(tag);
    }

    @Override
    protected void readSyncNBT(CompoundTag tag) {
        super.readSyncNBT(tag);
        active = tag.getBoolean("Active");
        crafting = tag.getBoolean("Crafting");
        stability = tag.getFloat("Stability");
        recipeInstability = tag.getInt("RecipeInstability");
        recipeEssentia.readFromNBT(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        
        // Save recipe ingredients
        if (recipeIngredients != null && !recipeIngredients.isEmpty()) {
            ListTag ingredientList = new ListTag();
            for (ItemStack stack : recipeIngredients) {
                if (!stack.isEmpty()) {
                    CompoundTag itemTag = new CompoundTag();
                    stack.save(itemTag);
                    ingredientList.add(itemTag);
                }
            }
            tag.put("RecipeIngredients", ingredientList);
        }
        
        if (!recipeInput.isEmpty()) {
            tag.put("RecipeInput", recipeInput.save(new CompoundTag()));
        }
        if (!recipeOutput.isEmpty()) {
            tag.put("RecipeOutput", recipeOutput.save(new CompoundTag()));
        }
        if (recipePlayer != null) {
            tag.putString("RecipePlayer", recipePlayer);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        
        recipeIngredients = new ArrayList<>();
        if (tag.contains("RecipeIngredients")) {
            ListTag ingredientList = tag.getList("RecipeIngredients", 10);
            for (int i = 0; i < ingredientList.size(); i++) {
                recipeIngredients.add(ItemStack.of(ingredientList.getCompound(i)));
            }
        }
        
        if (tag.contains("RecipeInput")) {
            recipeInput = ItemStack.of(tag.getCompound("RecipeInput"));
        }
        if (tag.contains("RecipeOutput")) {
            recipeOutput = ItemStack.of(tag.getCompound("RecipeOutput"));
        }
        recipePlayer = tag.getString("RecipePlayer");
        if (recipePlayer.isEmpty()) {
            recipePlayer = null;
        }
    }

    // ==================== Tick ====================

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileInfusionMatrix tile) {
        tile.count++;
        
        if (tile.checkSurroundings) {
            tile.checkSurroundings = false;
            tile.scanSurroundings();
        }

        // Validate location periodically
        if (tile.count % (tile.crafting ? 20 : 100) == 0 && !tile.validLocation()) {
            tile.active = false;
            tile.setChanged();
            tile.syncTile(false);
            return;
        }

        // Replenish stability when active but not crafting
        if (tile.active && !tile.crafting && tile.stability < tile.stabilityCap && tile.count % Math.max(5, tile.countDelay) == 0) {
            tile.stability += Math.max(0.1f, tile.stabilityReplenish);
            if (tile.stability > tile.stabilityCap) {
                tile.stability = tile.stabilityCap;
            }
            tile.setChanged();
            tile.syncTile(false);
        }

        // Process crafting
        if (tile.active && tile.crafting && tile.count % tile.countDelay == 0) {
            tile.craftCycle();
            tile.setChanged();
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, TileInfusionMatrix tile) {
        // Handle client-side animations
        if (tile.crafting) {
            tile.craftCount++;
        } else if (tile.craftCount > 0) {
            tile.craftCount = Math.max(0, tile.craftCount - 2);
        }

        // Startup animation
        if (tile.active && tile.startUp < 1.0f) {
            tile.startUp += Math.max(tile.startUp / 10.0f, 0.001f);
            if (tile.startUp > 0.999f) tile.startUp = 1.0f;
        }
        if (!tile.active && tile.startUp > 0.0f) {
            tile.startUp -= tile.startUp / 10.0f;
            if (tile.startUp < 0.001f) tile.startUp = 0.0f;
        }
    }

    // ==================== Crafting ====================

    /**
     * Check if the altar structure is valid.
     */
    public boolean validLocation() {
        if (level == null) return false;
        
        // Check for pedestal 2 blocks below
        BlockState below2 = level.getBlockState(worldPosition.below(2));
        if (!below2.is(ModBlocks.PEDESTAL_ARCANE.get()) && 
            !below2.is(ModBlocks.PEDESTAL_ANCIENT.get()) && 
            !below2.is(ModBlocks.PEDESTAL_ELDRITCH.get())) {
            return false;
        }

        // Check for 4 pillars at corners
        BlockPos[] pillarPositions = {
            worldPosition.offset(-1, -2, -1),
            worldPosition.offset(1, -2, -1),
            worldPosition.offset(1, -2, 1),
            worldPosition.offset(-1, -2, 1)
        };

        for (BlockPos pillarPos : pillarPositions) {
            BlockState pillarState = level.getBlockState(pillarPos);
            if (!pillarState.is(ModBlocks.ARCANE_PILLAR.get()) &&
                !pillarState.is(ModBlocks.ANCIENT_PILLAR.get()) &&
                !pillarState.is(ModBlocks.ELDRITCH_PILLAR.get())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Start crafting when activated by a player with a caster.
     */
    public void startCrafting(Player player) {
        if (!validLocation()) {
            active = false;
            syncTile(false);
            return;
        }

        scanSurroundings();

        // Get input from center pedestal
        BlockEntity centerTE = level.getBlockEntity(worldPosition.below(2));
        if (centerTE instanceof TilePedestal pedestal) {
            ItemStack centerItem = pedestal.getItem(0);
            if (!centerItem.isEmpty()) {
                recipeInput = centerItem.copy();
            }
        }

        if (recipeInput.isEmpty()) return;

        // Gather components from surrounding pedestals
        List<ItemStack> components = new ArrayList<>();
        for (BlockPos pedestalPos : pedestals) {
            BlockEntity te = level.getBlockEntity(pedestalPos);
            if (te instanceof TilePedestal pedestal) {
                ItemStack stack = pedestal.getItem(0);
                if (!stack.isEmpty()) {
                    components.add(stack.copy());
                }
            }
        }

        if (components.isEmpty()) return;

        // TODO: Find matching infusion recipe
        // InfusionRecipe recipe = ThaumcraftCraftingManager.findMatchingInfusionRecipe(components, recipeInput, player);
        
        // For now, just set up a placeholder
        recipeIngredients = components;
        recipePlayer = player.getName().getString();
        crafting = true;
        
        level.playSound(null, worldPosition, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 0.5f, 1.0f);
        syncTile(false);
    }

    /**
     * Process one cycle of the crafting.
     */
    private void craftCycle() {
        if (level == null || recipeInput.isEmpty()) {
            cancelCrafting();
            return;
        }

        // Apply stability loss based on instability
        float lossPerCycle = recipeInstability / getModFromStability();
        stability -= level.random.nextFloat() * lossPerCycle;
        stability += stabilityReplenish;
        stability = Math.max(-100, Math.min(stabilityCap, stability));

        // Check if input is still valid
        BlockEntity centerTE = level.getBlockEntity(worldPosition.below(2));
        if (!(centerTE instanceof TilePedestal pedestal) || pedestal.getItem(0).isEmpty()) {
            cancelCrafting();
            return;
        }

        // TODO: Implement full crafting cycle:
        // 1. Draw essentia from jars
        // 2. Consume ingredients from pedestals
        // 3. Handle instability events
        // 4. Complete crafting and replace center item

        // Placeholder: Just finish after some time
        if (craftCount > 100) {
            finishCrafting();
        }
    }

    private void cancelCrafting() {
        crafting = false;
        recipeEssentia = new AspectList();
        recipeInstability = 0;
        level.playSound(null, worldPosition, SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1.0f, 0.6f);
        syncTile(false);
    }

    private void finishCrafting() {
        // TODO: Implement proper crafting completion
        crafting = false;
        recipeEssentia = new AspectList();
        recipeInstability = 0;
        craftCount = 0;
        level.playSound(null, worldPosition, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 0.5f, 1.0f);
        syncTile(false);
    }

    private float getModFromStability() {
        if (stability > stabilityCap / 2) return 5.0f;
        if (stability >= 0) return 6.0f;
        if (stability > -25) return 7.0f;
        return 8.0f;
    }

    /**
     * Scan surroundings for pedestals and stabilizers.
     */
    private void scanSurroundings() {
        pedestals.clear();
        stabilityReplenish = 0.0f;
        costMult = 1.0f;
        cycleTime = 10;

        // Scan for pedestals in a range around the matrix
        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                for (int y = -3; y <= 7; y++) {
                    if (x == 0 && z == 0) continue;
                    
                    BlockPos checkPos = worldPosition.offset(x, -y, z);
                    BlockState state = level.getBlockState(checkPos);
                    
                    // Check for pedestals (excluding center)
                    if (state.is(ModBlocks.PEDESTAL_ARCANE.get()) ||
                        state.is(ModBlocks.PEDESTAL_ANCIENT.get()) ||
                        state.is(ModBlocks.PEDESTAL_ELDRITCH.get())) {
                        if (!checkPos.equals(worldPosition.below(2))) {
                            pedestals.add(checkPos);
                        }
                    }
                    
                    // TODO: Check for stabilizers (skulls, candles, etc.)
                }
            }
        }

        countDelay = cycleTime / 2;
    }

    // ==================== Activation ====================

    /**
     * Called when player right-clicks with a caster.
     */
    public boolean onCasterRightClick(Player player) {
        if (level.isClientSide && active && !crafting) {
            checkSurroundings = true;
        }
        
        if (!level.isClientSide && active && !crafting) {
            startCrafting(player);
            return true;
        }
        
        if (!level.isClientSide && !active && validLocation()) {
            level.playSound(null, worldPosition, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 0.5f, 1.0f);
            active = true;
            syncTile(false);
            return true;
        }
        
        return false;
    }

    // ==================== IAspectContainer ====================

    @Override
    public AspectList getAspects() {
        return recipeEssentia;
    }

    @Override
    public void setAspects(AspectList aspects) {
        // Not used
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList list) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList list) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    // ==================== Rendering ====================

    public AABB getCustomRenderBoundingBox() {
        return new AABB(
                worldPosition.getX() - 0.1, worldPosition.getY() - 0.1, worldPosition.getZ() - 0.1,
                worldPosition.getX() + 1.1, worldPosition.getY() + 1.1, worldPosition.getZ() + 1.1
        );
    }
}
