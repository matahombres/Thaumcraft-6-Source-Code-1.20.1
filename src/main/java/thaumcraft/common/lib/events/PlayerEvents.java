package thaumcraft.common.lib.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import thaumcraft.Thaumcraft;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * PlayerEvents - Handles all player-related events for Thaumcraft.
 * 
 * Ported from 1.12.2. Key functionality:
 * - Attach capabilities to players
 * - Sync knowledge/warp when player joins
 * - Clone capabilities on death/dimension change
 * - Track research progress triggers (crystal pickup, thaumonomicon, etc.)
 * 
 * Key API changes:
 * - AttachCapabilitiesEvent<Entity> same
 * - EntityJoinWorldEvent -> EntityJoinLevelEvent
 * - PlayerEvent.Clone same
 * - EntityPlayer -> Player
 * - EntityPlayerMP -> ServerPlayer
 * - world.isRemote -> level.isClientSide
 */
@Mod.EventBusSubscriber(modid = Thaumcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {
    
    // Players that need their knowledge synced
    public static final Set<String> syncList = new HashSet<>();
    
    // ==================== Capability Events ====================
    
    // NOTE: Capability attachment is handled by ThaumcraftCapabilities class
    // to avoid duplicate registration errors.
    
    /**
     * Clone capabilities when player respawns or changes dimension
     */
    @SubscribeEvent
    public static void cloneCapabilitiesEvent(PlayerEvent.Clone event) {
        try {
            // Clone knowledge
            IPlayerKnowledge oldKnowledge = ThaumcraftCapabilities.getKnowledge(event.getOriginal());
            IPlayerKnowledge newKnowledge = ThaumcraftCapabilities.getKnowledge(event.getEntity());
            if (oldKnowledge != null && newKnowledge != null) {
                CompoundTag nbtKnowledge = oldKnowledge.serializeNBT();
                newKnowledge.deserializeNBT(nbtKnowledge);
            }
            
            // Clone warp
            IPlayerWarp oldWarp = ThaumcraftCapabilities.getWarp(event.getOriginal());
            IPlayerWarp newWarp = ThaumcraftCapabilities.getWarp(event.getEntity());
            if (oldWarp != null && newWarp != null) {
                CompoundTag nbtWarp = oldWarp.serializeNBT();
                newWarp.deserializeNBT(nbtWarp);
            }
        } catch (Exception e) {
            Thaumcraft.LOGGER.error("Could not clone player [{}] knowledge when respawning/changing dimensions", 
                event.getOriginal().getName().getString(), e);
        }
    }
    
    // ==================== Join/Login Events ====================
    
    /**
     * Sync capabilities when player joins world
     */
    @SubscribeEvent
    public static void playerJoin(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide && event.getEntity() instanceof ServerPlayer player) {
            IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
            IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);
            
            if (knowledge != null) {
                knowledge.sync(player);
            }
            if (warp != null) {
                warp.sync(player);
            }
            
            Thaumcraft.LOGGER.debug("Synced Thaumcraft data for player: {}", player.getName().getString());
        }
    }
    
    // ==================== Tick Events ====================
    
    /**
     * Player tick event - handles periodic sync and warp effects
     */
    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (!player.level().isClientSide) {
                // Periodic knowledge sync (every 20 ticks)
                if (player.tickCount % 20 == 0 && player instanceof ServerPlayer serverPlayer) {
                    String playerName = player.getName().getString();
                    if (syncList.remove(playerName)) {
                        IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
                        if (knowledge != null) {
                            knowledge.sync(serverPlayer);
                        }
                    }
                }
                
                // TODO: Periodic research checks (every 200 ticks)
                // if (player.tickCount % 200 == 0) {
                //     ConfigResearch.checkPeriodicStuff(player);
                // }
                
                // Warp effects (every 2000 ticks = ~100 seconds)
                if (player.tickCount % 2000 == 0) {
                    WarpEvents.checkWarpEvent(player);
                }
                
                // Death Gaze effect check (every 20 ticks)
                if (player.tickCount % 20 == 5) {
                    WarpEvents.checkDeathGaze(player);
                }
            }
        }
    }
    
    // ==================== Item Events ====================
    
    /**
     * Track who threw an item (for item-specific mechanics)
     */
    @SubscribeEvent
    public static void droppedItem(ItemTossEvent event) {
        ItemEntity itemEntity = event.getEntity();
        CompoundTag itemData = itemEntity.getPersistentData();
        itemData.putString("thrower", event.getPlayer().getName().getString());
    }
    
    // ==================== Utility Methods ====================
    
    /**
     * Mark a player for knowledge sync on next tick
     */
    public static void markForSync(Player player) {
        syncList.add(player.getName().getString());
    }
    
    /**
     * Get runic charge from an item
     */
    public static int getRunicCharge(net.minecraft.world.item.ItemStack stack) {
        if (stack.isEmpty() || !stack.hasTag()) {
            return 0;
        }
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("TC.RUNIC")) {
            return tag.getByte("TC.RUNIC");
        }
        return 0;
    }
    
    /**
     * Get warp value from an item
     */
    public static int getFinalWarp(net.minecraft.world.item.ItemStack stack, Player player) {
        if (stack.isEmpty()) {
            return 0;
        }
        int warp = 0;
        
        // Check for IWarpingGear interface
        if (stack.getItem() instanceof thaumcraft.api.items.IWarpingGear warpingGear) {
            warp += warpingGear.getWarp(stack, player);
        }
        
        // Check for NBT warp tag
        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains("TC.WARP")) {
                warp += tag.getByte("TC.WARP");
            }
        }
        
        return warp;
    }
    
    /**
     * Get vis discount from an item
     */
    public static int getFinalDiscount(net.minecraft.world.item.ItemStack stack, Player player) {
        if (stack.isEmpty() || !(stack.getItem() instanceof thaumcraft.api.items.IVisDiscountGear gear)) {
            return 0;
        }
        return gear.getVisDiscount(stack, player);
    }
}
