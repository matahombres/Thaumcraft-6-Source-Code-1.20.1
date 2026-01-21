package thaumcraft.common.blocks.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import thaumcraft.common.blocks.BlockTCDevice;
import thaumcraft.common.tiles.crafting.TileCrucible;
import thaumcraft.init.ModBlockEntities;

import javax.annotation.Nullable;

/**
 * The Crucible - a cauldron-like device for alchemical transmutation.
 * Items thrown in are converted to essentia when heated.
 */
public class BlockCrucible extends BlockTCDevice {

    // Collision shapes for the crucible (cauldron-like)
    private static final VoxelShape INSIDE = Block.box(2.0, 4.0, 2.0, 14.0, 16.0, 14.0);
    private static final VoxelShape SHAPE = Shapes.join(
            Shapes.block(),
            Shapes.or(
                    Block.box(0.0, 0.0, 4.0, 16.0, 3.0, 12.0),
                    Block.box(4.0, 0.0, 0.0, 12.0, 3.0, 16.0),
                    Block.box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0),
                    INSIDE
            ),
            BooleanOp.ONLY_FIRST
    );

    private int collisionDelay = 0;

    public BlockCrucible() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(2.0f, 6.0f)
                .sound(SoundType.METAL)
                .noOcclusion(),
                false, // hasFacing
                false  // hasEnabled
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return INSIDE;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TileCrucible crucible) {
                if (entity instanceof ItemEntity itemEntity) {
                    // Smelt items in heated crucible
                    if (crucible.isHeated()) {
                        crucible.attemptSmelt(itemEntity);
                    }
                } else if (entity instanceof LivingEntity living) {
                    collisionDelay++;
                    if (collisionDelay >= 10) {
                        collisionDelay = 0;
                        // Damage living entities in heated crucible
                        if (crucible.isHeated() && crucible.getTank().getFluidAmount() > 0) {
                            living.hurt(level.damageSources().inFire(), 1.0f);
                            level.playSound(null, pos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 
                                    0.4f, 2.0f + level.random.nextFloat() * 0.4f);
                        }
                    }
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                  InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack heldItem = player.getItemInHand(hand);
        BlockEntity blockEntity = level.getBlockEntity(pos);

        // TODO: Implement proper crucible interaction
        // - Fill with water from water bucket
        // - Throw items in to smelt them
        // - Shift+empty hand to dump contents

        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof TileCrucible crucible) {
                crucible.spillAll();
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        // TODO: Return signal based on essentia amount when TileCrucible is implemented
        return 0;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(10) == 0) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            // TODO: Check if crucible is heated and bubbling when TileCrucible is implemented
            // if (heated && hasWater) {
            //     level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(),
            //             SoundEvents.LAVA_POP, SoundSource.BLOCKS,
            //             0.1f + random.nextFloat() * 0.1f, 1.2f + random.nextFloat() * 0.2f, false);
            // }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TileCrucible(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        }
        return type == ModBlockEntities.CRUCIBLE.get() ?
                (lvl, pos, st, te) -> TileCrucible.serverTick(lvl, pos, st, (TileCrucible) te) : null;
    }
}
