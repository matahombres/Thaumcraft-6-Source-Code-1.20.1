package thaumcraft.common.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * Base class for all Thaumcraft tile entities.
 * Provides common functionality for NBT sync and client updates.
 */
public abstract class TileThaumcraft extends BlockEntity {

    public TileThaumcraft(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // ==================== NBT Serialization ====================

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        writeSyncNBT(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        readSyncNBT(tag);
    }

    /**
     * Write data that should be synced to clients.
     * Override in subclasses to add custom data.
     */
    protected void writeSyncNBT(CompoundTag tag) {
        // Override in subclasses
    }

    /**
     * Read data synced from server.
     * Override in subclasses to read custom data.
     */
    protected void readSyncNBT(CompoundTag tag) {
        // Override in subclasses
    }

    // ==================== Client Sync ====================

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        writeSyncNBT(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    /**
     * Mark this tile entity as needing sync to clients.
     */
    public void syncTile(boolean rerender) {
        if (level != null && !level.isClientSide) {
            setChanged();
            BlockState state = getBlockState();
            level.sendBlockUpdated(worldPosition, state, state, rerender ? 3 : 2);
        }
    }

    /**
     * Convenience method to mark dirty and sync.
     */
    public void markDirtyAndSync() {
        setChanged();
        syncTile(false);
    }
}
