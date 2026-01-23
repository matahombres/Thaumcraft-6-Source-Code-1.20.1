package thaumcraft.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thaumcraft.Thaumcraft;
import thaumcraft.api.aspects.Aspect;
import net.minecraft.world.item.DyeColor;
import thaumcraft.common.blocks.BlockTC;
import thaumcraft.common.blocks.basic.*;
import thaumcraft.common.blocks.crafting.BlockArcaneWorkbench;
import thaumcraft.common.blocks.crafting.BlockCrucible;
import thaumcraft.common.blocks.crafting.BlockFocalManipulator;
import thaumcraft.common.blocks.crafting.BlockGolemBuilder;
import thaumcraft.common.blocks.crafting.BlockInfusionMatrix;
import thaumcraft.common.blocks.crafting.BlockPatternCrafter;
import thaumcraft.common.blocks.crafting.BlockResearchTable;
import thaumcraft.common.blocks.crafting.BlockThaumatorium;
import thaumcraft.common.blocks.devices.*;
import thaumcraft.common.blocks.essentia.BlockAlembic;
import thaumcraft.common.blocks.essentia.BlockCentrifuge;
import thaumcraft.common.blocks.essentia.BlockEssentiaReservoir;
import thaumcraft.common.blocks.essentia.BlockJar;
import thaumcraft.common.blocks.essentia.BlockSmelter;
import thaumcraft.common.blocks.essentia.BlockTube;
import thaumcraft.common.blocks.misc.BlockEffect;
import thaumcraft.common.blocks.misc.BlockHole;
import thaumcraft.common.blocks.misc.BlockNitor;
import thaumcraft.common.blocks.world.ore.BlockCrystalTC;
import thaumcraft.common.blocks.world.ore.BlockOreTC;
import thaumcraft.common.blocks.world.plants.BlockCinderpearl;
import thaumcraft.common.blocks.world.plants.BlockLeavesTC;
import thaumcraft.common.blocks.world.plants.BlockPlantTC;
import thaumcraft.common.blocks.world.plants.BlockSaplingTC;
import thaumcraft.common.blocks.world.plants.BlockShimmerleaf;
import thaumcraft.common.blocks.world.plants.BlockVishroom;
import thaumcraft.common.blocks.world.taint.BlockFluxGoo;
import thaumcraft.common.blocks.world.taint.BlockTaintFibre;

import java.util.function.Supplier;

/**
 * Registry for all Thaumcraft blocks.
 * Uses DeferredRegister for 1.20.1 Forge.
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Thaumcraft.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Thaumcraft.MODID);

    // ==================== Stone Blocks ====================

    public static final RegistryObject<Block> ARCANE_STONE = registerBlock("arcane_stone",
            () -> BlockStoneTC.create(true));

    public static final RegistryObject<Block> ARCANE_STONE_BRICK = registerBlock("arcane_stone_brick",
            () -> BlockStoneTC.create(true));

    public static final RegistryObject<Block> ANCIENT_STONE = registerBlock("ancient_stone",
            () -> BlockStoneTC.create(true));

    public static final RegistryObject<Block> ANCIENT_STONE_TILE = registerBlock("ancient_stone_tile",
            () -> BlockStoneTC.create(false));

    public static final RegistryObject<Block> ANCIENT_STONE_ROCK = registerBlock("ancient_rock",
            () -> BlockStoneTC.createUnbreakable());

    public static final RegistryObject<Block> ANCIENT_STONE_GLYPHED = registerBlock("ancient_stone_glyphed",
            () -> BlockStoneTC.create(false));

    public static final RegistryObject<Block> ANCIENT_STONE_DOORWAY = registerBlock("ancient_pedestal_doorway",
            () -> BlockStoneTC.createUnbreakable());

    public static final RegistryObject<Block> ELDRITCH_STONE_TILE = registerBlock("eldritch_stone_tile",
            () -> BlockStoneTC.createReinforced());

    public static final RegistryObject<Block> POROUS_STONE = registerBlock("porous_stone",
            () -> new BlockStoneTC(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.5f, 10.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops(), true));

    // ==================== Stairs ====================

    public static final RegistryObject<Block> ARCANE_STONE_STAIRS = registerBlock("arcane_stone_stairs",
            () -> new StairBlock(() -> ARCANE_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ARCANE_STONE.get())));

    public static final RegistryObject<Block> ARCANE_STONE_BRICK_STAIRS = registerBlock("arcane_stone_brick_stairs",
            () -> new StairBlock(() -> ARCANE_STONE_BRICK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ARCANE_STONE_BRICK.get())));

    public static final RegistryObject<Block> ANCIENT_STONE_STAIRS = registerBlock("ancient_stone_stairs",
            () -> new StairBlock(() -> ANCIENT_STONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ANCIENT_STONE.get())));

    // ==================== Slabs ====================

    public static final RegistryObject<Block> ARCANE_STONE_SLAB = registerBlock("arcane_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 10.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ARCANE_STONE_BRICK_SLAB = registerBlock("arcane_stone_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 10.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ANCIENT_STONE_SLAB = registerBlock("ancient_stone_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.0f, 10.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    // ==================== Pillars ====================

    public static final RegistryObject<Block> ARCANE_PILLAR = registerBlock("arcane_pillar",
            BlockPillarTC::create);

    public static final RegistryObject<Block> ANCIENT_PILLAR = registerBlock("ancient_pillar",
            BlockPillarTC::create);

    public static final RegistryObject<Block> ELDRITCH_PILLAR = registerBlock("eldritch_pillar",
            () -> new BlockPillarTC(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(15.0f, 1000.0f)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()));

    // ==================== Wood Blocks ====================

    public static final RegistryObject<Block> GREATWOOD_LOG = registerBlock("greatwood_log",
            BlockLogTC::createGreatwood);

    public static final RegistryObject<Block> SILVERWOOD_LOG = registerBlock("silverwood_log",
            BlockLogTC::createSilverwood);

    public static final RegistryObject<Block> GREATWOOD_PLANKS = registerBlock("greatwood_planks",
            BlockPlanksTC::createGreatwood);

    public static final RegistryObject<Block> SILVERWOOD_PLANKS = registerBlock("silverwood_planks",
            BlockPlanksTC::createSilverwood);

    public static final RegistryObject<Block> GREATWOOD_STAIRS = registerBlock("greatwood_stairs",
            () -> new StairBlock(() -> GREATWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(GREATWOOD_PLANKS.get())));

    public static final RegistryObject<Block> SILVERWOOD_STAIRS = registerBlock("silverwood_stairs",
            () -> new StairBlock(() -> SILVERWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(SILVERWOOD_PLANKS.get())));

    public static final RegistryObject<Block> GREATWOOD_SLAB = registerBlock("greatwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SILVERWOOD_SLAB = registerBlock("silverwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.QUARTZ)
                    .strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD)));

    // ==================== Metal Blocks ====================

    public static final RegistryObject<Block> BRASS_BLOCK = registerBlock("brass_block",
            () -> BlockMetalTC.create(MapColor.GOLD));

    public static final RegistryObject<Block> THAUMIUM_BLOCK = registerBlock("thaumium_block",
            () -> BlockMetalTC.create(MapColor.COLOR_PURPLE));

    public static final RegistryObject<Block> VOID_METAL_BLOCK = registerBlock("void_metal_block",
            () -> BlockMetalTC.create(MapColor.COLOR_BLACK));

    public static final RegistryObject<Block> ALCHEMICAL_BRASS_BLOCK = registerBlock("alchemical_brass_block",
            () -> BlockMetalTC.create(MapColor.GOLD));

    public static final RegistryObject<Block> ALCHEMICAL_BRASS_ADVANCED_BLOCK = registerBlock("alchemical_brass_advanced_block",
            () -> BlockMetalTC.create(MapColor.GOLD));

    // ==================== Amber Blocks ====================

    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block",
            () -> new BlockTC(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(3.0f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()));

    public static final RegistryObject<Block> AMBER_BRICK = registerBlock("amber_brick",
            () -> new BlockTC(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(3.0f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()));

    // ==================== Matrix Blocks ====================

    public static final RegistryObject<Block> MATRIX_SPEED = registerBlock("matrix_speed",
            () -> BlockStoneTC.create(false));

    public static final RegistryObject<Block> MATRIX_COST = registerBlock("matrix_cost",
            () -> BlockStoneTC.create(false));

    // ==================== Crafting Blocks ====================

    public static final RegistryObject<Block> ARCANE_WORKBENCH = registerBlock("arcane_workbench",
            BlockArcaneWorkbench::new);

    public static final RegistryObject<Block> CRUCIBLE = registerBlock("crucible",
            BlockCrucible::new);

    // ==================== Ore Blocks ====================

    public static final RegistryObject<Block> AMBER_ORE = registerBlock("amber_ore",
            BlockOreTC::createAmberOre);

    public static final RegistryObject<Block> CINNABAR_ORE = registerBlock("cinnabar_ore",
            BlockOreTC::createCinnabarOre);

    public static final RegistryObject<Block> QUARTZ_ORE = registerBlock("quartz_ore",
            BlockOreTC::createQuartzOre);

    // ==================== Crystal Blocks ====================

    public static final RegistryObject<Block> CRYSTAL_AIR = registerBlock("crystal_air",
            () -> new BlockCrystalTC(Aspect.AIR));

    public static final RegistryObject<Block> CRYSTAL_FIRE = registerBlock("crystal_fire",
            () -> new BlockCrystalTC(Aspect.FIRE));

    public static final RegistryObject<Block> CRYSTAL_WATER = registerBlock("crystal_water",
            () -> new BlockCrystalTC(Aspect.WATER));

    public static final RegistryObject<Block> CRYSTAL_EARTH = registerBlock("crystal_earth",
            () -> new BlockCrystalTC(Aspect.EARTH));

    public static final RegistryObject<Block> CRYSTAL_ORDER = registerBlock("crystal_order",
            () -> new BlockCrystalTC(Aspect.ORDER));

    public static final RegistryObject<Block> CRYSTAL_ENTROPY = registerBlock("crystal_entropy",
            () -> new BlockCrystalTC(Aspect.ENTROPY));

    public static final RegistryObject<Block> CRYSTAL_FLUX = registerBlock("crystal_flux",
            () -> new BlockCrystalTC(Aspect.FLUX));

    // ==================== Plant Blocks ====================

    public static final RegistryObject<Block> SHIMMERLEAF = registerBlock("shimmerleaf",
            BlockShimmerleaf::new);

    public static final RegistryObject<Block> CINDERPEARL = registerBlock("cinderpearl",
            BlockCinderpearl::new);

    public static final RegistryObject<Block> VISHROOM = registerBlock("vishroom",
            BlockVishroom::new);

    // ==================== Tree Blocks ====================

    public static final RegistryObject<Block> GREATWOOD_LEAVES = registerBlock("greatwood_leaves",
            BlockLeavesTC::createGreatwood);

    public static final RegistryObject<Block> SILVERWOOD_LEAVES = registerBlock("silverwood_leaves",
            BlockLeavesTC::createSilverwood);

    public static final RegistryObject<Block> GREATWOOD_SAPLING = registerBlock("greatwood_sapling",
            BlockSaplingTC::createGreatwood);

    public static final RegistryObject<Block> SILVERWOOD_SAPLING = registerBlock("silverwood_sapling",
            BlockSaplingTC::createSilverwood);

    // ==================== Device Blocks ====================

    public static final RegistryObject<Block> PEDESTAL_ARCANE = registerBlock("pedestal_arcane",
            BlockPedestal::createArcane);

    public static final RegistryObject<Block> PEDESTAL_ANCIENT = registerBlock("pedestal_ancient",
            BlockPedestal::createAncient);

    public static final RegistryObject<Block> PEDESTAL_ELDRITCH = registerBlock("pedestal_eldritch",
            BlockPedestal::createEldritch);

    public static final RegistryObject<Block> TABLE_WOOD = registerBlock("table_wood",
            BlockTable::createWooden);

    public static final RegistryObject<Block> TABLE_STONE = registerBlock("table_stone",
            BlockTable::createStone);

    // ==================== Candle Blocks (16 colors) ====================

    public static final RegistryObject<Block> CANDLE_WHITE = registerBlock("candle_white",
            () -> BlockCandle.create(DyeColor.WHITE));
    public static final RegistryObject<Block> CANDLE_ORANGE = registerBlock("candle_orange",
            () -> BlockCandle.create(DyeColor.ORANGE));
    public static final RegistryObject<Block> CANDLE_MAGENTA = registerBlock("candle_magenta",
            () -> BlockCandle.create(DyeColor.MAGENTA));
    public static final RegistryObject<Block> CANDLE_LIGHT_BLUE = registerBlock("candle_light_blue",
            () -> BlockCandle.create(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> CANDLE_YELLOW = registerBlock("candle_yellow",
            () -> BlockCandle.create(DyeColor.YELLOW));
    public static final RegistryObject<Block> CANDLE_LIME = registerBlock("candle_lime",
            () -> BlockCandle.create(DyeColor.LIME));
    public static final RegistryObject<Block> CANDLE_PINK = registerBlock("candle_pink",
            () -> BlockCandle.create(DyeColor.PINK));
    public static final RegistryObject<Block> CANDLE_GRAY = registerBlock("candle_gray",
            () -> BlockCandle.create(DyeColor.GRAY));
    public static final RegistryObject<Block> CANDLE_LIGHT_GRAY = registerBlock("candle_light_gray",
            () -> BlockCandle.create(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> CANDLE_CYAN = registerBlock("candle_cyan",
            () -> BlockCandle.create(DyeColor.CYAN));
    public static final RegistryObject<Block> CANDLE_PURPLE = registerBlock("candle_purple",
            () -> BlockCandle.create(DyeColor.PURPLE));
    public static final RegistryObject<Block> CANDLE_BLUE = registerBlock("candle_blue",
            () -> BlockCandle.create(DyeColor.BLUE));
    public static final RegistryObject<Block> CANDLE_BROWN = registerBlock("candle_brown",
            () -> BlockCandle.create(DyeColor.BROWN));
    public static final RegistryObject<Block> CANDLE_GREEN = registerBlock("candle_green",
            () -> BlockCandle.create(DyeColor.GREEN));
    public static final RegistryObject<Block> CANDLE_RED = registerBlock("candle_red",
            () -> BlockCandle.create(DyeColor.RED));
    public static final RegistryObject<Block> CANDLE_BLACK = registerBlock("candle_black",
            () -> BlockCandle.create(DyeColor.BLACK));

    // ==================== Nitor Blocks (16 colors) ====================

    public static final RegistryObject<Block> NITOR_WHITE = registerBlock("nitor_white",
            () -> BlockNitor.create(DyeColor.WHITE));
    public static final RegistryObject<Block> NITOR_ORANGE = registerBlock("nitor_orange",
            () -> BlockNitor.create(DyeColor.ORANGE));
    public static final RegistryObject<Block> NITOR_MAGENTA = registerBlock("nitor_magenta",
            () -> BlockNitor.create(DyeColor.MAGENTA));
    public static final RegistryObject<Block> NITOR_LIGHT_BLUE = registerBlock("nitor_light_blue",
            () -> BlockNitor.create(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> NITOR_YELLOW = registerBlock("nitor_yellow",
            () -> BlockNitor.create(DyeColor.YELLOW));
    public static final RegistryObject<Block> NITOR_LIME = registerBlock("nitor_lime",
            () -> BlockNitor.create(DyeColor.LIME));
    public static final RegistryObject<Block> NITOR_PINK = registerBlock("nitor_pink",
            () -> BlockNitor.create(DyeColor.PINK));
    public static final RegistryObject<Block> NITOR_GRAY = registerBlock("nitor_gray",
            () -> BlockNitor.create(DyeColor.GRAY));
    public static final RegistryObject<Block> NITOR_LIGHT_GRAY = registerBlock("nitor_light_gray",
            () -> BlockNitor.create(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> NITOR_CYAN = registerBlock("nitor_cyan",
            () -> BlockNitor.create(DyeColor.CYAN));
    public static final RegistryObject<Block> NITOR_PURPLE = registerBlock("nitor_purple",
            () -> BlockNitor.create(DyeColor.PURPLE));
    public static final RegistryObject<Block> NITOR_BLUE = registerBlock("nitor_blue",
            () -> BlockNitor.create(DyeColor.BLUE));
    public static final RegistryObject<Block> NITOR_BROWN = registerBlock("nitor_brown",
            () -> BlockNitor.create(DyeColor.BROWN));
    public static final RegistryObject<Block> NITOR_GREEN = registerBlock("nitor_green",
            () -> BlockNitor.create(DyeColor.GREEN));
    public static final RegistryObject<Block> NITOR_RED = registerBlock("nitor_red",
            () -> BlockNitor.create(DyeColor.RED));
    public static final RegistryObject<Block> NITOR_BLACK = registerBlock("nitor_black",
            () -> BlockNitor.create(DyeColor.BLACK));

    // ==================== Essentia Jars ====================

    public static final RegistryObject<Block> JAR_NORMAL = registerBlock("jar_normal",
            BlockJar::createNormal);

    public static final RegistryObject<Block> JAR_VOID = registerBlock("jar_void",
            BlockJar::createVoid);

    public static final RegistryObject<Block> JAR_BRAIN = registerBlock("jar_brain",
            BlockJar::createBrain);

    // ==================== Essentia Tubes ====================

    public static final RegistryObject<Block> TUBE_NORMAL = registerBlock("tube_normal",
            BlockTube::createNormal);

    public static final RegistryObject<Block> TUBE_RESTRICTED = registerBlock("tube_restricted",
            BlockTube::createRestricted);

    public static final RegistryObject<Block> TUBE_FILTER = registerBlock("tube_filter",
            BlockTube::createFilter);

    public static final RegistryObject<Block> TUBE_VALVE = registerBlock("tube_valve",
            BlockTube::createValve);

    public static final RegistryObject<Block> TUBE_BUFFER = registerBlock("tube_buffer",
            BlockTube::createBuffer);

    public static final RegistryObject<Block> TUBE_ONEWAY = registerBlock("tube_oneway",
            BlockTube::createOneway);

    // ==================== Advanced Crafting ====================

    public static final RegistryObject<Block> RESEARCH_TABLE = registerBlock("research_table",
            BlockResearchTable::new);

    public static final RegistryObject<Block> INFUSION_MATRIX = registerBlock("infusion_matrix",
            BlockInfusionMatrix::new);

    public static final RegistryObject<Block> FOCAL_MANIPULATOR = registerBlock("focal_manipulator",
            BlockFocalManipulator::new);

    public static final RegistryObject<Block> THAUMATORIUM = registerBlock("thaumatorium",
            BlockThaumatorium::new);

    public static final RegistryObject<Block> PATTERN_CRAFTER = registerBlock("pattern_crafter",
            BlockPatternCrafter::new);

    // ==================== More Device Blocks ====================

    public static final RegistryObject<Block> BELLOWS = registerBlock("bellows",
            BlockBellows::new);

    public static final RegistryObject<Block> LAMP_ARCANE = registerBlock("lamp_arcane",
            BlockLamp::createArcane);

    public static final RegistryObject<Block> LAMP_GROWTH = registerBlock("lamp_growth",
            BlockLamp::createGrowth);

    public static final RegistryObject<Block> LAMP_FERTILITY = registerBlock("lamp_fertility",
            BlockLamp::createFertility);

    public static final RegistryObject<Block> HUNGRY_CHEST = registerBlock("hungry_chest",
            BlockHungryChest::new);

    public static final RegistryObject<Block> MIRROR_ITEM = registerBlock("mirror_item",
            BlockMirror::createItem);

    public static final RegistryObject<Block> MIRROR_ESSENTIA = registerBlock("mirror_essentia",
            BlockMirror::createPlayer);

    public static final RegistryObject<Block> STABILIZER = registerBlock("stabilizer",
            BlockStabilizer::new);

    public static final RegistryObject<Block> VIS_GENERATOR = registerBlock("vis_generator",
            BlockVisGenerator::new);

    public static final RegistryObject<Block> CONDENSER = registerBlock("condenser",
            BlockCondenser::new);

    public static final RegistryObject<Block> ARCANE_EAR = registerBlock("arcane_ear",
            BlockArcaneEar::createPulse);

    public static final RegistryObject<Block> ARCANE_EAR_TOGGLE = registerBlock("arcane_ear_toggle",
            BlockArcaneEar::createToggle);

    public static final RegistryObject<Block> REDSTONE_RELAY = registerBlock("redstone_relay",
            BlockRedstoneRelay::new);

    // ==================== Essentia Processing ====================

    public static final RegistryObject<Block> ALEMBIC = registerBlock("alembic",
            BlockAlembic::new);

    public static final RegistryObject<Block> SMELTER = registerBlock("smelter",
            BlockSmelter::new);

    public static final RegistryObject<Block> CENTRIFUGE = registerBlock("centrifuge",
            BlockCentrifuge::new);

    public static final RegistryObject<Block> INFERNAL_FURNACE = registerBlock("infernal_furnace",
            BlockInfernalFurnace::new);

    public static final RegistryObject<Block> ESSENTIA_RESERVOIR = registerBlock("essentia_reservoir",
            BlockEssentiaReservoir::new);

    public static final RegistryObject<Block> SPA = registerBlock("spa",
            BlockSpa::new);

    public static final RegistryObject<Block> FLUX_SCRUBBER = registerBlock("flux_scrubber",
            BlockFluxScrubber::new);

    public static final RegistryObject<Block> VIS_RELAY = registerBlock("vis_relay",
            BlockVisRelay::new);

    // ==================== Golem Crafting ====================

    public static final RegistryObject<Block> GOLEM_BUILDER = registerBlock("golem_builder",
            BlockGolemBuilder::new);

    // ==================== Effect Blocks ====================

    public static final RegistryObject<Block> EFFECT_SAP = registerBlockNoItem("effect_sap",
            BlockEffect::createSap);

    public static final RegistryObject<Block> EFFECT_SHOCK = registerBlockNoItem("effect_shock",
            BlockEffect::createShock);

    public static final RegistryObject<Block> EFFECT_GLIMMER = registerBlockNoItem("effect_glimmer",
            BlockEffect::createGlimmer);

    // ==================== Taint Blocks ====================

    public static final RegistryObject<Block> FLUX_GOO = registerBlockNoItem("flux_goo",
            BlockFluxGoo::new);

    public static final RegistryObject<Block> TAINT_FIBRE = registerBlockNoItem("taint_fibre",
            BlockTaintFibre::new);

    // ==================== Additional Devices ====================

    public static final RegistryObject<Block> LEVITATOR = registerBlock("levitator",
            BlockLevitator::new);

    public static final RegistryObject<Block> RECHARGE_PEDESTAL = registerBlock("recharge_pedestal",
            BlockRechargePedestal::new);

    public static final RegistryObject<Block> DIOPTRA = registerBlock("dioptra",
            BlockDioptra::new);

    public static final RegistryObject<Block> VOID_SIPHON = registerBlock("void_siphon",
            BlockVoidSiphon::new);

    public static final RegistryObject<Block> POTION_SPRAYER = registerBlock("potion_sprayer",
            BlockPotionSprayer::new);

    // ==================== Misc Blocks ====================

    public static final RegistryObject<Block> HOLE = registerBlockNoItem("hole",
            BlockHole::new);

    public static final RegistryObject<Block> EVERFULL_URN = registerBlock("everfull_urn",
            BlockWaterJug::new);

    // ==================== Helper Methods ====================

    /**
     * Register a block and its corresponding item.
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockObj = BLOCKS.register(name, block);
        registerBlockItem(name, blockObj);
        return blockObj;
    }

    /**
     * Register a block without a corresponding item.
     * Used for effect blocks, holes, and other non-obtainable blocks.
     */
    private static <T extends Block> RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    /**
     * Register a BlockItem for a block.
     */
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
