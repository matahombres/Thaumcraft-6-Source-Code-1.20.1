package thaumcraft.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thaumcraft.Thaumcraft;
import thaumcraft.common.tiles.crafting.*;
import thaumcraft.common.tiles.devices.*;
import thaumcraft.common.tiles.essentia.*;

/**
 * Registry for all Thaumcraft block entities (tile entities).
 * Uses DeferredRegister for 1.20.1 Forge.
 */
public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Thaumcraft.MODID);

    // ==================== Essentia ====================

    public static final RegistryObject<BlockEntityType<TileJar>> JAR =
            BLOCK_ENTITIES.register("jar",
                    () -> BlockEntityType.Builder.of(TileJar::new,
                            ModBlocks.JAR_NORMAL.get(),
                            ModBlocks.JAR_VOID.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileAlembic>> ALEMBIC =
            BLOCK_ENTITIES.register("alembic",
                    () -> BlockEntityType.Builder.of(TileAlembic::new,
                            ModBlocks.ALEMBIC.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileSmelter>> SMELTER =
            BLOCK_ENTITIES.register("smelter",
                    () -> BlockEntityType.Builder.of(TileSmelter::new,
                            ModBlocks.SMELTER.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileTube>> TUBE =
            BLOCK_ENTITIES.register("tube",
                    () -> BlockEntityType.Builder.of(TileTube::new,
                            ModBlocks.TUBE_NORMAL.get(),
                            ModBlocks.TUBE_RESTRICTED.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileTubeFilter>> TUBE_FILTER =
            BLOCK_ENTITIES.register("tube_filter",
                    () -> BlockEntityType.Builder.of(TileTubeFilter::new,
                            ModBlocks.TUBE_FILTER.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileTubeValve>> TUBE_VALVE =
            BLOCK_ENTITIES.register("tube_valve",
                    () -> BlockEntityType.Builder.of(TileTubeValve::new,
                            ModBlocks.TUBE_VALVE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileTubeBuffer>> TUBE_BUFFER =
            BLOCK_ENTITIES.register("tube_buffer",
                    () -> BlockEntityType.Builder.of(TileTubeBuffer::new,
                            ModBlocks.TUBE_BUFFER.get()
                    ).build(null));

    // ==================== Crafting ====================

    public static final RegistryObject<BlockEntityType<TilePedestal>> PEDESTAL =
            BLOCK_ENTITIES.register("pedestal",
                    () -> BlockEntityType.Builder.of(TilePedestal::new,
                            ModBlocks.PEDESTAL_ARCANE.get(),
                            ModBlocks.PEDESTAL_ANCIENT.get(),
                            ModBlocks.PEDESTAL_ELDRITCH.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileCrucible>> CRUCIBLE =
            BLOCK_ENTITIES.register("crucible",
                    () -> BlockEntityType.Builder.of(TileCrucible::new,
                            ModBlocks.CRUCIBLE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileArcaneWorkbench>> ARCANE_WORKBENCH =
            BLOCK_ENTITIES.register("arcane_workbench",
                    () -> BlockEntityType.Builder.of(TileArcaneWorkbench::new,
                            ModBlocks.ARCANE_WORKBENCH.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileResearchTable>> RESEARCH_TABLE =
            BLOCK_ENTITIES.register("research_table",
                    () -> BlockEntityType.Builder.of(TileResearchTable::new,
                            ModBlocks.RESEARCH_TABLE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileInfusionMatrix>> INFUSION_MATRIX =
            BLOCK_ENTITIES.register("infusion_matrix",
                    () -> BlockEntityType.Builder.of(TileInfusionMatrix::new,
                            ModBlocks.INFUSION_MATRIX.get()
                    ).build(null));

    // ==================== Devices ====================

    public static final RegistryObject<BlockEntityType<TileBellows>> BELLOWS =
            BLOCK_ENTITIES.register("bellows",
                    () -> BlockEntityType.Builder.of(TileBellows::new,
                            ModBlocks.BELLOWS.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileHungryChest>> HUNGRY_CHEST =
            BLOCK_ENTITIES.register("hungry_chest",
                    () -> BlockEntityType.Builder.of(TileHungryChest::new,
                            ModBlocks.HUNGRY_CHEST.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileLampArcane>> LAMP_ARCANE =
            BLOCK_ENTITIES.register("lamp_arcane",
                    () -> BlockEntityType.Builder.of(TileLampArcane::new,
                            ModBlocks.LAMP_ARCANE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileLampGrowth>> LAMP_GROWTH =
            BLOCK_ENTITIES.register("lamp_growth",
                    () -> BlockEntityType.Builder.of(TileLampGrowth::new,
                            ModBlocks.LAMP_GROWTH.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileLampFertility>> LAMP_FERTILITY =
            BLOCK_ENTITIES.register("lamp_fertility",
                    () -> BlockEntityType.Builder.of(TileLampFertility::new,
                            ModBlocks.LAMP_FERTILITY.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileMirror>> MIRROR_ITEM =
            BLOCK_ENTITIES.register("mirror_item",
                    () -> BlockEntityType.Builder.of(TileMirror::new,
                            ModBlocks.MIRROR_ITEM.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileStabilizer>> STABILIZER =
            BLOCK_ENTITIES.register("stabilizer",
                    () -> BlockEntityType.Builder.of(TileStabilizer::new,
                            ModBlocks.STABILIZER.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileMirrorEssentia>> MIRROR_ESSENTIA =
            BLOCK_ENTITIES.register("mirror_essentia",
                    () -> BlockEntityType.Builder.of(TileMirrorEssentia::new,
                            ModBlocks.MIRROR_ESSENTIA.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileVisGenerator>> VIS_GENERATOR =
            BLOCK_ENTITIES.register("vis_generator",
                    () -> BlockEntityType.Builder.of(TileVisGenerator::new,
                            ModBlocks.VIS_GENERATOR.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileCondenser>> CONDENSER =
            BLOCK_ENTITIES.register("condenser",
                    () -> BlockEntityType.Builder.of(TileCondenser::new,
                            ModBlocks.CONDENSER.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileArcaneEar>> ARCANE_EAR =
            BLOCK_ENTITIES.register("arcane_ear",
                    () -> BlockEntityType.Builder.of(TileArcaneEar::new,
                            ModBlocks.ARCANE_EAR.get(),
                            ModBlocks.ARCANE_EAR_TOGGLE.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<TileRedstoneRelay>> REDSTONE_RELAY =
            BLOCK_ENTITIES.register("redstone_relay",
                    () -> BlockEntityType.Builder.of(TileRedstoneRelay::new,
                            ModBlocks.REDSTONE_RELAY.get()
                    ).build(null));
}
