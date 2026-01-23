package thaumcraft.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thaumcraft.Thaumcraft;
import thaumcraft.common.world.features.GreatwoodTreeFeature;
import thaumcraft.common.world.features.SilverwoodTreeFeature;
import thaumcraft.common.world.features.ThaumcraftPlantFeature;

/**
 * Registry for all Thaumcraft world generation features.
 * 
 * In 1.20.1, world generation uses the Feature system:
 * - Feature: The actual generation logic
 * - ConfiguredFeature: A feature with its configuration (datapack JSON or code)
 * - PlacedFeature: A configured feature with placement rules (where it spawns)
 * 
 * Features are registered here, while ConfiguredFeatures and PlacedFeatures
 * are defined in datapacks under:
 * - data/thaumcraft/worldgen/configured_feature/
 * - data/thaumcraft/worldgen/placed_feature/
 */
public class ModFeatures {
    
    public static final DeferredRegister<Feature<?>> FEATURES = 
            DeferredRegister.create(ForgeRegistries.FEATURES, Thaumcraft.MODID);
    
    // ==================== Tree Features ====================
    
    /**
     * Greatwood tree - large magical tree with 2x2 trunk.
     * Spawns in forests, plains, and similar biomes.
     * Has a rare spider nest variant.
     */
    public static final RegistryObject<GreatwoodTreeFeature> GREATWOOD_TREE = 
            FEATURES.register("greatwood_tree", 
                    () -> new GreatwoodTreeFeature(NoneFeatureConfiguration.CODEC));
    
    /**
     * Silverwood tree - magical pale tree with unique trunk shape.
     * Rarer than greatwood, spawns in magical biomes and forests.
     * Spawns shimmerleaf flowers around it.
     */
    public static final RegistryObject<SilverwoodTreeFeature> SILVERWOOD_TREE = 
            FEATURES.register("silverwood_tree", 
                    () -> new SilverwoodTreeFeature(NoneFeatureConfiguration.CODEC));
    
    // ==================== Plant Features ====================
    
    /**
     * Cinderpearl plant cluster - desert fire plants.
     * Spawns in desert biomes on sand.
     */
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> CINDERPEARL_PATCH = 
            FEATURES.register("cinderpearl_patch", 
                    () -> new ThaumcraftPlantFeature(NoneFeatureConfiguration.CODEC, 
                            ThaumcraftPlantFeature.PlantType.CINDERPEARL));
    
    /**
     * Shimmerleaf plant cluster - glowing magical flowers.
     * Primarily spawns around silverwood trees, but can appear in magical biomes.
     */
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SHIMMERLEAF_PATCH = 
            FEATURES.register("shimmerleaf_patch", 
                    () -> new ThaumcraftPlantFeature(NoneFeatureConfiguration.CODEC, 
                            ThaumcraftPlantFeature.PlantType.SHIMMERLEAF));
    
    /**
     * Vishroom mushroom cluster - magical cave mushrooms.
     * Spawns underground in caves.
     */
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> VISHROOM_PATCH = 
            FEATURES.register("vishroom_patch", 
                    () -> new ThaumcraftPlantFeature(NoneFeatureConfiguration.CODEC, 
                            ThaumcraftPlantFeature.PlantType.VISHROOM));
    
    // ==================== Ore Features ====================
    // Note: Ore generation in 1.20.1 typically uses vanilla OreFeature
    // with custom OreConfiguration. For Thaumcraft ores (crystals, cinnabar, etc.),
    // we can use datapack JSON configurations or create custom features.
    
    // Future features:
    // - Crystal cluster generation (vis crystals)
    // - Amber ore generation
    // - Cinnabar ore generation
    // - Quartz ore generation (Thaumcraft variant)
    
    // ==================== Structure Features ====================
    // Note: Structures in 1.20.1 use the Structure system, not Features.
    // Barrow mounds and other structures should be registered separately.
    
    // Future structure features:
    // - Barrow mound (underground structure with loot)
    // - Cultist portal spawning
    // - Eldritch obelisks
}
