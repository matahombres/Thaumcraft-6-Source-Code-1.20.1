package thaumcraft.common.tiles.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import thaumcraft.common.tiles.TileThaumcraftInventory;
import thaumcraft.init.ModBlockEntities;

/**
 * Research table tile entity - used for the theorycraft minigame.
 * Holds scribing tools (slot 0) and paper (slot 1).
 */
public class TileResearchTable extends TileThaumcraftInventory {

    public static final int SLOT_SCRIBING_TOOLS = 0;
    public static final int SLOT_PAPER = 1;

    // Research table data for theorycraft
    // TODO: Port ResearchTableData class
    private CompoundTag researchData = null;

    public TileResearchTable(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, 2);
        // These slots should be synced to client
        this.syncedSlots = new int[] { 0, 1 };
    }

    public TileResearchTable(BlockPos pos, BlockState state) {
        this(ModBlockEntities.RESEARCH_TABLE.get(), pos, state);
    }

    // ==================== NBT ====================

    @Override
    protected void writeSyncNBT(CompoundTag tag) {
        super.writeSyncNBT(tag);
        if (researchData != null) {
            tag.put("ResearchData", researchData);
        }
    }

    @Override
    protected void readSyncNBT(CompoundTag tag) {
        super.readSyncNBT(tag);
        if (tag.contains("ResearchData")) {
            researchData = tag.getCompound("ResearchData");
        } else {
            researchData = null;
        }
    }

    // ==================== Research Methods ====================

    /**
     * Start a new theory research session.
     * TODO: Implement full theorycraft system
     */
    public void startNewTheory(Player player) {
        researchData = new CompoundTag();
        researchData.putString("Player", player.getName().getString());
        researchData.putLong("StartTime", level.getGameTime());
        markDirtyAndSync();
    }

    /**
     * Finish the current theory and grant knowledge.
     * TODO: Implement full theorycraft rewards
     */
    public void finishTheory(Player player) {
        if (researchData != null) {
            // TODO: Calculate and grant research points based on theorycraft results
            researchData = null;
            markDirtyAndSync();
            
            // Play completion sound
            if (level != null) {
                level.playSound(null, worldPosition, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    /**
     * Check if there's an active theory session.
     */
    public boolean hasActiveTheory() {
        return researchData != null;
    }

    /**
     * Get the current research data.
     */
    public CompoundTag getResearchData() {
        return researchData;
    }

    // ==================== Consumables ====================

    /**
     * Try to consume ink from the scribing tools.
     * @return true if ink was consumed
     */
    public boolean consumeInk() {
        ItemStack tools = getItem(SLOT_SCRIBING_TOOLS);
        // TODO: Check for IScribeTools interface when implemented
        if (!tools.isEmpty() && tools.isDamageableItem() && tools.getDamageValue() < tools.getMaxDamage()) {
            tools.setDamageValue(tools.getDamageValue() + 1);
            markDirtyAndSync();
            return true;
        }
        return false;
    }

    /**
     * Try to consume paper from the paper slot.
     * @return true if paper was consumed
     */
    public boolean consumePaper() {
        ItemStack paper = getItem(SLOT_PAPER);
        if (!paper.isEmpty() && paper.is(Items.PAPER) && paper.getCount() > 0) {
            paper.shrink(1);
            if (paper.isEmpty()) {
                setItem(SLOT_PAPER, ItemStack.EMPTY);
            }
            markDirtyAndSync();
            return true;
        }
        return false;
    }

    // ==================== Container ====================

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == SLOT_SCRIBING_TOOLS) {
            // TODO: Check for IScribeTools interface
            // For now, allow any damageable item (placeholder)
            return stack.isDamageableItem();
        }
        if (slot == SLOT_PAPER) {
            return stack.is(Items.PAPER);
        }
        return false;
    }

    // ==================== Block Events ====================

    @Override
    public boolean triggerEvent(int id, int param) {
        if (id == 1) {
            // Play learn sound
            if (level != null && level.isClientSide) {
                level.playLocalSound(
                        worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5,
                        SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS,
                        1.0f, 1.0f, false
                );
            }
            return true;
        }
        return super.triggerEvent(id, param);
    }
}
