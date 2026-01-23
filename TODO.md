# Thaumcraft 6 - Minecraft 1.20.1 Migration Plan

> **Original Version:** Minecraft 1.12.2 with Forge 14.23.5.x  
> **Target Version:** Minecraft 1.20.1 with Forge 47.3.0  
> **Estimated Total Effort:** 44 weeks (~10-11 months for 1 developer)

---

## Project Statistics

| Category | Ported | Original | Status |
|----------|--------|----------|--------|
| Java Files | 698 | 901 | 77% Complete |
| Blocks | 191 | 91+ | ✅ Complete |
| Items | 175 | 90+ | ✅ Complete |
| Entities | 46 | 35+ | ✅ Complete |
| Block Entities | 50 | 31 | ✅ Complete |
| GUIs | 19 | 22 | 86% Complete |
| Recipes | 270 | 265 | ✅ Complete |
| Particles | - | 100+ | Pending |

### Recipe Breakdown
| Type | Count | Status |
|------|-------|--------|
| Arcane Workbench | 81 | ✅ Complete |
| Crucible | 57 | ✅ Complete |
| Infusion | 60 | ✅ Complete |
| Vanilla Crafting | 64 | ✅ Complete |
| Smelting | 8 | ✅ Complete |
| **Total** | **270** | ✅ Complete |

---

## Phase 1: Foundation (Weeks 1-2)
**Goal:** Set up project structure and core systems

### Build System
- [x] Create `build.gradle` for Forge 1.20.1
- [x] Create `settings.gradle` with Foojay toolchain plugin
- [x] Update `gradle.properties` with mod metadata
- [x] Update Gradle wrapper to 8.8
- [x] Create `mods.toml` template
- [x] Verify build compiles successfully
- [x] Generate IDE run configurations

### Core Mod Class
- [x] Update `Thaumcraft.java` to 1.20.1 API
- [x] Create `ModBlocks.java` DeferredRegister stub
- [x] Create `ModItems.java` DeferredRegister stub
- [x] Create `ModEntities.java` DeferredRegister stub
- [x] Create `ModBlockEntities.java` DeferredRegister stub
- [x] Create `ModEffects.java` DeferredRegister stub
- [x] Create `ModSounds.java` DeferredRegister stub
- [x] Create `ModCreativeTabs.java` DeferredRegister stub

### Event System
- [x] Set up mod event bus listeners (`Thaumcraft.java`)
- [x] Set up Forge event bus listeners (`Thaumcraft.java`)
- [x] Create client setup event handler (`ClientModEvents`)
- [x] Create common setup event handler (`commonSetup`)
- [x] Create data generation event handler

### Network System
- [x] Create `PacketHandler.java` with SimpleChannel
- [ ] Define packet registration system
- [ ] Create base packet interface
- [x] Test client-server communication

### Capability System
- [x] Create capability registration event handler (`ThaumcraftCapabilities.java`)
- [x] Define `IPlayerKnowledge` capability interface
- [x] Define `IPlayerWarp` capability interface
- [x] Implement capability attachment to players
- [x] Implement capability serialization (NBT save/load)

### Commands
- [x] Migrate `CommandThaumcraft.java` to Brigadier API
- [x] Register commands in `RegisterCommandsEvent`

---

## Phase 2: Core API (Weeks 3-5)
**Goal:** Migrate API and core systems

### Aspect System (`api/aspects/`)
- [x] Port `Aspect.java` - Define all primal and compound aspects
- [x] Port `AspectList.java` - Aspect container class
- [x] Port `AspectHelper.java` - Aspect utilities
- [ ] Port `AspectEventProxy.java` - Event handling
- [x] Port `AspectSourceHelper.java` - Source utilities
- [x] Port `IAspectContainer.java` interface
- [x] Port `IAspectSource.java` interface
- [x] Port `IEssentiaContainerItem.java` interface
- [x] Port `IEssentiaTransport.java` interface
- [x] Create aspect registration system
- [x] Create aspect texture loading
- [x] Create `InfusionEnchantmentRecipe.java` (in `thaumcraft.common.lib.crafting`)

### Aura System (`api/aura/`, `common/world/aura/`)
- [x] Port `AuraHelper.java` - Public API
- [x] Port `AuraWorld.java` - Per-world aura data
- [x] Port `AuraChunk.java` - Per-chunk storage
- [x] Port `AuraHandler.java` - Main handler
- [x] Port `AuraThread.java` - Background simulation
- [x] Port `AuraThreadManager.java` - Thread lifecycle management
- [x] Update to new chunk data attachment system
- [x] Update to new `SavedData` system
- [x] Implement aura tick scheduling

### Research API (`api/research/`)
- [x] Port `ResearchEntry.java` - Research entry class
- [x] Port `ResearchCategory.java` - Category organization
- [x] Port `ResearchStage.java` - Stage definitions
- [x] Port `IScanThing.java` interface
- [x] Port `ScanningManager.java` - Scanning logic
- [x] Define JSON format for research data
- [x] Create research data loader (`ResearchManager.java`)

### Crafting API (`api/crafting/`)
- [x] Port `IArcaneRecipe.java` interface
- [x] Port `ShapedArcaneRecipe.java`
- [x] Port `ShapelessArcaneRecipe.java`
- [x] Port `CrucibleRecipe.java`
- [x] Port `InfusionRecipe.java`
- [x] Port `InfusionEnchantmentRecipe.java`
- [x] Create `RecipeSerializer` implementations
- [x] Create `RecipeType` registrations
- [x] Define JSON recipe format

### Other API Classes
- [x] Port `ThaumcraftApi.java` - Main API entry
- [x] Port `ThaumcraftApiHelper.java` - Helper methods
- [x] Port `ThaumcraftMaterials.java` - Tool/armor tiers
- [x] Port `ThaumcraftInvHelper.java` - Inventory utilities
- [x] Update `OreDictionaryEntries.java` to use Tags
- [x] Create `BlocksTC.java` registry references
- [x] Create `ItemsTC.java` registry references

### Internal API (`api/internal/`)
- [x] Port `IInternalMethodHandler.java`
- [x] Port `InternalMethodHandler.java` implementation
- [x] Update all internal method signatures

---

## Phase 3: Blocks (Weeks 6-8)
**Goal:** Register all blocks

### Basic Blocks (26 blocks)
- [x] Port ore blocks (amber, cinnabar, quartz)
- [x] Port deepslate ore blocks (amber, cinnabar, quartz)
- [x] Port crystal blocks (6 primal + vitium)
- [x] Port arcane stone variants (stone, brick, ancient)
- [x] Port slabs and stairs (12+ variants)
- [x] Port tables (wood, stone)
- [x] Port pedestals (arcane, ancient, eldritch)
- [x] Port metal blocks (brass, thaumium, void, alchemical)
- [x] Port pillars (arcane, ancient, eldritch)
- [x] Port candles (16 colors)
- [x] Port banners (16 colors + crimson cult)
- [x] Port nitor blocks (16 colors)
- [x] Port misc blocks (amber, flesh, paving stones)

### Crafting Blocks (11 blocks)
- [x] Port `BlockArcaneWorkbench.java`
- [x] Port `BlockArcaneWorkbenchCharger.java`
- [x] Port `BlockCrucible.java`
- [x] Port `BlockFocalManipulator.java`
- [x] Port `BlockInfusionMatrix.java`
- [x] Port `BlockPatternCrafter.java`
- [x] Port `BlockResearchTable.java`
- [x] Port `BlockThaumatorium.java`
- [x] Port `BlockThaumatoriumTop.java`
- [x] Port `BlockVoidSiphon.java`

### Device Blocks (23 blocks)
- [x] Port `BlockArcaneEar.java` (+ toggle variant)
- [x] Port `BlockBellows.java`
- [x] Port `BlockBrainBox.java`
- [x] Port `BlockCondenser.java` (+ lattice)
- [x] Port `BlockDioptra.java`
- [x] Port `BlockHungryChest.java`
- [x] Port `BlockInfernalFurnace.java`
- [x] Port `BlockInlay.java`
- [x] Port lamp blocks (arcane, fertility, growth)
- [x] Port `BlockLevitator.java`
- [x] Port mirror blocks (standard, essentia)
- [x] Port pedestal blocks (standard, ancient, eldritch)
- [x] Port `BlockPotionSprayer.java`
- [x] Port `BlockRechargePedestal.java`
- [x] Port `BlockRedstoneRelay.java`
- [x] Port `BlockSpa.java`
- [x] Port `BlockStabilizer.java`
- [x] Port `BlockVisBattery.java`
- [x] Port `BlockVisGenerator.java`
- [x] Port `BlockWaterJug.java`

### Essentia Blocks (8 block types)
- [x] Port `BlockAlembic.java`
- [x] Port `BlockCentrifuge.java`
- [x] Port smelter blocks (basic, thaumium, void, aux, vent)
- [x] Port tube blocks (standard, valve, restrict, oneway, filter, buffer)
- [x] Port jar blocks (normal, void, brain)
- [x] Port essentia I/O blocks (input, output)

### World Blocks (25 blocks)
- [x] Port tree blocks (greatwood, silverwood logs/leaves/planks)
- [x] Port sapling blocks
- [x] Port plant blocks (shimmerleaf, cinderpearl, vishroom)
- [x] Port crystal ore blocks (6 types)
- [x] Port standard ore blocks (3 types)
- [x] Port loot blocks (crates, urns - 6 variants)
- [x] Port taint blocks (fibre, crust, soil, rock, geyser, feature, log)
- [x] Port ambient grass block
- [x] Port flux goo block
- [x] Port liquid blocks (death, purifying)

### Block Resources
- [x] Verify all blockstate JSONs
- [ ] Create/update block model JSONs
- [ ] Verify block textures
- [x] Create block tags

---

## Phase 4: Items (Weeks 9-10)
**Goal:** Register all items

### Core Items (8 items)
- [x] Port `ItemThaumonomicon.java`
- [x] Port `ItemCurio.java` (Partial)
- [x] Port `ItemLootBag.java`
- [x] Port `ItemPrimordialPearl.java`
- [x] Port `ItemPechWand.java`
- [x] Port `ItemCelestialNotes.java`
- [x] Port amber and quicksilver items

### Resource Items (25+ items)
- [x] Port ingots (thaumium, void, brass, alchemical)
- [x] Port nuggets (10+ metal types)
- [x] Port clusters (metal ores)
- [x] Port fabric, vis resonator, tallow
- [x] Port mechanisms (simple, complex)
- [x] Port plates (brass, iron, thaumium, void)
- [x] Port filters, morphic resonator
- [x] Port `ItemSalisMundus.java` (magic dust)
- [x] Port mirrored glass, void seed
- [x] Port mind modules (clockwork, biothaumic)
- [x] Port `ItemCrystalEssence.java`
- [x] Port chunks, meat treat, zombie brain
- [x] Port labels, phials
- [x] Port `ItemAlumentum.java`
- [x] Port jar brace, bottle taint
- [x] Port sanity soap, bath salts

### Tools (20 items)
- [x] Port `ItemThaumometer.java`
- [x] Port `ItemResonator.java`
- [x] Port `ItemSanityChecker.java`
- [x] Port `ItemHandMirror.java`
- [x] Port `ItemScribingTools.java`
- [x] Port thaumium tools (5: axe, sword, shovel, pickaxe, hoe)
- [x] Port void tools (5: axe, sword, shovel, pickaxe, hoe)
- [x] Port elemental tools (5: axe, sword, shovel, pickaxe, hoe)
- [x] Port `ItemPrimalCrusher.java`
- [x] Port `ItemCrimsonBlade.java`
- [x] Port `ItemGrappleGun.java` (+ tip, spool)

### Armor (30+ items)
- [x] Port `ItemGoggles.java`
- [x] Port `ItemTravellerBoots.java`
- [x] Port thaumium armor set (4 pieces)
- [x] Port robe armor set (3 pieces)
- [x] Port fortress armor set (3 pieces)
- [x] Port void armor set (4 pieces)
- [x] Port void robe armor set (3 pieces)
- [x] Port crimson cult armor sets
- [x] Port crimson praetor armor set

### Baubles/Curios (8 items) - Requires Curios API
- [x] Port `ItemBaubles.java` base class to ICurioItem
- [x] Port `ItemAmuletVis.java`
- [x] Port `ItemCharmVerdant.java`
- [x] Port `ItemBandCuriosity.java`
- [x] Port `ItemCharmVoidseer.java`
- [x] Port `ItemCloudRing.java`
- [x] Port `ItemCharmUndying.java`
- [x] Update goggles to work as Curio

### Caster Items (4+ items)
- [x] Port `ItemCasterBasic.java`
- [x] Port `ItemFocus.java` (3 tiers)
- [x] Port `ItemFocusPouch.java`

### Golem Items (3 items)
- [x] Port `ItemGolemBell.java`
- [x] Port `ItemGolemPlacer.java`
- [x] Port `ItemSealPlacer.java`

### Other Items
- [x] Port turret placer
- [x] Port causality collapser
- [x] Port creative items (flux sponge, etc.)

### Item Resources
- [ ] Create/update item model JSONs
- [ ] Verify item textures
- [x] Create item tags

---

## Phase 5: Block Entities (Weeks 11-12)
**Goal:** Migrate all tile entities to block entities

### Crafting Block Entities (12)
- [x] Port `TileArcaneWorkbench.java`
- [x] Port `TileDioptra.java`
- [x] Port `TileCrucible.java`
- [x] Port `TileFocalManipulator.java`
- [x] Port `TilePedestal.java`
- [x] Port `TileRechargePedestal.java`
- [x] Port `TileResearchTable.java`
- [x] Port `TileInfusionMatrix.java`
- [x] Port `TilePatternCrafter.java`
- [x] Port `TileThaumatorium.java`
- [x] Port `TileThaumatoriumTop.java`
- [x] Port `TileVoidSiphon.java`

### Device Block Entities (14)
- [x] Port `TileArcaneEar.java`
- [x] Port `TileLevitator.java`
- [x] Port `TileLampGrowth.java`
- [x] Port `TileLampArcane.java`
- [x] Port `TileLampFertility.java`
- [x] Port `TileMirror.java`
- [x] Port `TileMirrorEssentia.java`
- [x] Port `TileRedstoneRelay.java`
- [x] Port `TileHungryChest.java`
- [x] Port `TileInfernalFurnace.java`
- [x] Port `TileSpa.java`
- [x] Port `TileVisGenerator.java`
- [x] Port `TileStabilizer.java`
- [x] Port `TileCondenser.java`

### Essentia Block Entities (12)
- [x] Port `TileCentrifuge.java`
- [x] Port `TileBellows.java`
- [x] Port `TileSmelter.java`
- [x] Port `TileAlembic.java`
- [x] Port `TileJar.java`
- [x] Port `TileJarFillable.java` (merged into TileJar)
- [x] Port `TileJarFillableVoid.java`
- [x] Port `TileJarBrain.java`
- [x] Port `TileTube.java`
- [x] Port `TileTubeValve.java`
- [x] Port `TileTubeFilter.java`
- [x] Port `TileTubeRestrict.java`
- [x] Port `TileTubeOneway.java`
- [x] Port `TileTubeBuffer.java`

### Other Block Entities (3)
- [x] Port `TileBanner.java`
- [x] Port `TileHole.java`
- [x] Port `TileBarrierStone.java`

### Block Entity Migration Tasks
- [x] Update all `readFromNBT` to `load(CompoundTag)`
- [x] Update all `writeToNBT` to `saveAdditional(CompoundTag)`
- [x] Update capability attachment system
- [x] Update ticking system (`BlockEntityTicker`)
- [x] Register all `BlockEntityType` instances

---

## Phase 6: Entities (Weeks 13-16)
**Goal:** Register and implement all entities

### Boss Entities (4)
- [x] Port `EntityEldritchWarden.java`
- [x] Port `EntityEldritchGolem.java`
- [x] Port `EntityCultistLeader.java`
- [x] Port `EntityTaintacleGiant.java`

### Monster Entities (20)
- [x] Port `EntityBrainyZombie.java`
- [x] Port `EntityGiantBrainyZombie.java`
- [x] Port `EntityWisp.java`
- [x] Port `EntityFirebat.java`
- [x] Port `EntitySpellbat.java`
- [x] Port `EntityPech.java`
- [x] Port `EntityMindSpider.java`
- [x] Port `EntityEldritchGuardian.java`
- [x] Port `EntityEldritchCrab.java`
- [x] Port `EntityCultistKnight.java`
- [x] Port `EntityCultistCleric.java`
- [x] Port `EntityInhabitedZombie.java`
- [x] Port `EntityThaumicSlime.java`
- [x] Port `EntityTaintCrawler.java`
- [x] Port `EntityTaintacle.java`
- [x] Port `EntityTaintacleSmall.java`
- [x] Port `EntityTaintSwarm.java`
- [x] Port `EntityTaintSeed.java`
- [x] Port `EntityTaintSeedPrime.java`

### Construct Entities (4)
- [x] Port `EntityThaumcraftGolem.java`
- [x] Port `EntityTurretCrossbow.java`
- [x] Port `EntityTurretCrossbowAdvanced.java`
- [x] Port `EntityArcaneBore.java`

### Projectile Entities (10)
- [x] Port `EntityAlumentum.java`
- [x] Port `EntityBottleTaint.java`
- [x] Port `EntityCausalityCollapser.java`
- [x] Port `EntityEldritchOrb.java`
- [x] Port `EntityFocusProjectile.java`
- [x] Port `EntityFocusCloud.java`
- [x] Port `EntityFocusMine.java`
- [x] Port `EntityGolemDart.java`
- [x] Port `EntityGolemOrb.java`
- [x] Port `EntityGrapple.java`

### Special Entities (5)
- [x] Port `EntityCultistPortal.java` (greater/lesser)
- [x] Port `EntityFluxRift.java`
- [x] Port `EntitySpecialItem.java`
- [x] Port `EntityFollowingItem.java`
- [x] Port `EntityFallingTaint.java`

### Entity Migration Tasks
- [x] Update entity registration to `RegisterEvent<EntityType>`
- [x] Update all entity constructors
- [x] Rewrite all entity AI (Goal system)
- [x] Update entity attributes (`AttributeSupplier`)
- [x] Update spawn rules
- [x] Update entity data serialization
- [x] Create entity renderers
- [x] Create entity models

---

## Phase 7: GUI & Containers (Weeks 17-19)
**Goal:** Implement all GUIs

### Container Classes (now called Menu classes in 1.20.1)
- [x] Port `ContainerArcaneWorkbench.java` -> `ArcaneWorkbenchMenu.java`
- [x] Port `ContainerFocalManipulator.java` -> `FocalManipulatorMenu.java`
- [x] Port `ContainerResearchTable.java` -> `ResearchTableMenu.java`
- [x] Port `ContainerThaumatorium.java` -> `ThaumatoriumMenu.java`
- [x] Port `ContainerSmelter.java` -> `SmelterMenu.java`
- [x] Port `ContainerHungryChest.java` -> `HungryChestMenu.java`
- [x] Port `ContainerFocusPouch.java` -> `FocusPouchMenu.java`
- [x] Port all other container classes (GolemBuilder, Seal, HandMirror, Spa, VoidSiphon, etc.)
- [x] Register `MenuType` instances

### Screen Classes
- [x] Port `GuiArcaneWorkbench.java` to `AbstractContainerScreen`
- [x] Port `GuiFocalManipulator.java`
- [x] Port `GuiResearchTable.java`
- [x] Port `GuiThaumatorium.java`
- [x] Port `GuiSmelter.java`
- [x] Port `GuiHungryChest.java` (HungryChestScreen)
- [x] Port `GuiFocusPouch.java`
- [x] Port all other GUI classes

### Research GUI System
- [x] Port `GuiResearchBrowser.java`
- [x] Port `GuiResearchPage.java`
- [ ] Port `GuiResearchRecipe.java`
- [ ] Update research rendering system
- [x] Port theorycraft minigame GUI (ResearchTableScreen with full card animation)

### GUI Migration Tasks
- [ ] Update all `drawGuiContainerBackgroundLayer` to `renderBg`
- [ ] Update all `drawGuiContainerForegroundLayer` to `renderLabels`
- [ ] Update mouse/keyboard event handling
- [ ] Update tooltip rendering
- [x] Register screens with `MenuScreens.register`

---

## Phase 8: Crafting Systems (Weeks 20-24)
**Goal:** Implement all crafting systems

### Recipe Serializers
- [x] Create `ArcaneRecipeSerializer.java`
- [x] Create `CrucibleRecipeSerializer.java`
- [x] Create `InfusionRecipeSerializer.java`
- [x] Create `InfusionEnchantmentRecipeSerializer.java`
- [x] Register all serializers

### Arcane Crafting
- [x] Implement arcane workbench crafting logic
- [x] Create JSON recipes for arcane recipes (81 of 81 recipes - COMPLETE)
- [ ] Implement vis cost calculation
- [ ] Implement research requirement checking

### Crucible Alchemy
- [x] Implement crucible melting logic
- [x] Implement crucible crafting logic
- [x] Create JSON recipes for crucible recipes (57 recipes - COMPLETE)
- [x] Implement aspect matching system
- [x] Implement flux generation
- [x] Implement crucible water bucket interaction

### Infusion Crafting
- [x] Implement infusion altar detection
- [x] Implement infusion crafting logic
- [x] Create JSON recipes for infusion recipes (existing recipes)
- [x] Implement instability system
- [x] Implement stabilizer detection (IInfusionStabiliser, IInfusionStabiliserExt)
- [x] Implement infusion effects (lightning, warp, flux, etc.)

### Other Crafting
- [x] Implement infernal furnace smelting
- [x] Implement thaumatorium crafting
- [x] Implement centrifuge processing
- [ ] Implement golem press crafting

### Multiblock Detection
- [ ] Port multiblock detection system
- [ ] Update for new block state system
- [ ] Implement structure validation

---

## Phase 9: World Generation (Weeks 25-26)
**Goal:** Implement world generation

### Biome Registration
- [x] Port `BiomeHandler.java` - Biome info and aura modifiers
- [x] Create `magical_forest.json` biome definition
- [x] Create `eerie.json` biome definition  
- [x] Create `eldritch.json` biome definition
- [x] Create biome tags (is_thaumcraft, is_magical, is_tainted)

### Ore Generation
- [x] Create ore feature configurations
- [x] Register ore placements
- [x] Configure spawn rates per biome

### Tree Generation
- [x] Port `WorldGenGreatwoodTrees.java`
- [x] Port `WorldGenSilverwoodTrees.java`
- [x] Create tree feature configurations
- [x] Register tree placements

### Structure Generation
- [x] Port `WorldGenMound.java` (barrows) - BarrowFeature.java + JSON configs
- [ ] Port other structure generators
- [x] Create structure JSON definitions
- [x] Register structure placements

### Aura World Generation
- [x] Implement initial aura distribution
- [ ] Port vis node generation (if applicable)
- [x] Port flux rift natural spawning

### Taint Spreading
- [x] Port taint spread mechanics (TaintHelper.java updated with proper block conversions)
- [x] Port taint blocks (BlockTaint, BlockTaintLog, BlockTaintFeature)
- [x] Update for new tick system (randomTick in taint blocks)
- [ ] Implement taint biome conversion

---

## Phase 10: Golem System (Weeks 27-29)
**Goal:** Implement golem system

### Golem Entity
- [x] Complete `EntityThaumcraftGolem.java` port
- [x] Implement golem materials (straw, wood, stone, iron, thaumium, void)
- [x] Implement golem stats per material
- [x] Implement golem upgrades

### Golem AI
- [x] Rewrite golem pathfinding for 1.20.1
- [ ] Implement seal-based AI switching
- [x] Port all golem Goal implementations
- [ ] Implement golem task priorities

### Seal System
- [x] Port `SealHandler.java`
- [x] Port `SealHarvest.java`
- [x] Port `SealLumber.java`
- [x] Port `SealFill.java` / `SealFillAdvanced.java`
- [x] Port `SealEmpty.java` / `SealEmptyAdvanced.java`
- [x] Port `SealGuard.java` / `SealGuardAdvanced.java`
- [x] Port `SealPickup.java` / `SealPickupAdvanced.java`
- [x] Port `SealUse.java`
- [x] Port `SealProvide.java`
- [x] Port `SealStock.java`
- [x] Port `SealButcher.java`
- [x] Port `SealBreaker.java` / `SealBreakerAdvanced.java`

### Golem Parts
- [x] Port golem arm variants
- [x] Port golem leg variants
- [x] Port golem head variants
- [ ] Implement part combination system

### Golem GUI
- [x] Port golem command GUI (SealScreen)
- [x] Port seal configuration GUI
- [x] Port golem builder GUI

---

## Phase 11: Casting System (Weeks 30-32)
**Goal:** Implement wand/focus casting

### Caster Items
- [x] Complete `ItemCasterBasic.java` port
- [x] Implement vis storage and drain
- [x] Implement focus attachment system
- [x] Implement casting cooldowns
- [ ] Implement casting particle effects
- [ ] Implement focus rendering on wand
- [ ] Implement cast beam rendering

---

## Phase 12: Visual Effects (Weeks 33-36)
**Goal:** Implement all visual effects

### Particle System
- [ ] Create particle registration system
- [x] Port `ParticleEngine` integration (via `FXDispatcher`)
- [ ] Create `ParticleProvider` implementations

### Core Particles (estimated 100+)
- [x] Port aura/vis particles
- [x] Port flux particles
- [ ] Port infusion particles
- [ ] Port casting particles
- [ ] Port warp particles
- [ ] Port essentia particles
- [ ] Port research particles
- [ ] Port all other particle types

### Beam Rendering
- [x] Port beam rendering system
- [ ] Update for new render system
- [ ] Implement beam particle spawning

### Shader Effects
- [ ] Port blur shader
- [ ] Port desaturation shader
- [ ] Port bloom shader
- [ ] Port other post-processing shaders
- [ ] Update GLSL syntax for 1.20.1

### Block Entity Renderers
- [x] Port crucible renderer
- [x] Port infusion altar renderer
- [x] Port essentia jar renderer
- [ ] Port tube renderer
- [x] Port pedestal renderer
- [x] Port mirror renderer
- [ ] Port all other TESR classes
- [x] Register with `EntityRenderersEvent.RegisterRenderers`

### Entity Renderers
- [x] Port wisp renderer
- [x] Port golem renderer
- [x] Port eldritch creature renderers
- [x] Port cultist renderers
- [x] Port taint creature renderers
- [ ] Port all other entity renderers
- [x] Port all entity models

---

## Phase 13: Research System (Weeks 37-39)
**Goal:** Complete research system

### Research Manager
- [x] Port `ResearchManager.java`
- [ ] Update research data storage
- [ ] Implement research unlocking logic
- [ ] Implement research requirement checking

### Research Data
- [ ] Define JSON research format
- [ ] Convert all research entries to JSON
- [x] Create research category definitions
- [x] Create research stage definitions

### Scanning System
- [x] Port `ScanningManager.java`
- [x] Implement item scanning
- [x] Implement block scanning
- [x] Implement entity scanning
- [ ] Implement potion scanning
- [ ] Implement enchantment scanning
- [x] Update thaumometer functionality

### Theorycraft Minigame
- [x] Port `TheorycraftCard.java` classes
- [x] Port theorycraft card effects
- [ ] Port research table minigame GUI
- [ ] Implement card drawing/playing logic

### Research GUI
- [x] Complete research browser port
- [x] Complete research page rendering
- [ ] Implement research recipe display
- [ ] Implement scanning popup display

---

## Phase 14: Sounds (Week 40)
**Goal:** Register all sounds

### Sound Registration
- [ ] Port `SoundsTC.java`
- [x] Register all `SoundEvent` instances (`ModSounds.java`)
- [x] Create `sounds.json` file

### Sound Categories
- [ ] Ambient sounds (aura, rifts)
- [ ] Block sounds (machines, crafting)
- [ ] Entity sounds (mobs, golems)
- [ ] Item sounds (wands, tools)
- [ ] GUI sounds (research, crafting)

---

## Phase 15: Potions/Effects (Week 40)
**Goal:** Register all mob effects

### Effect Registration
- [x] Port `PotionFluxTaint.java`
- [x] Port `PotionVisExhaust.java`
- [x] Port `PotionInfectiousVisExhaust.java`
- [x] Port `PotionUnnaturalHunger.java`
- [x] Port `PotionWarpWard.java`
- [x] Port `PotionDeathGaze.java`
- [x] Port `PotionBlurredVision.java`
- [x] Port `PotionSunScorned.java`
- [x] Port `PotionThaumarhia.java`

### Effect Implementation
- [ ] Update effect tick logic
- [ ] Update effect rendering
- [ ] Implement effect application

---

## Phase 16: Resources & Localization (Weeks 41-42)
**Goal:** Update all resources

### Language Files
- [x] Convert `en_us.lang` to JSON format
- [x] Convert `de_de.lang` to JSON format
- [x] Convert `fr_fr.lang` to JSON format
- [x] Convert `ja_jp.lang` to JSON format
- [x] Convert `ko_kr.lang` to JSON format
- [x] Convert all other language files (nl_nl, ru_ru, zh_cn, zh_tw)

### Data Pack Resources
- [ ] Create all recipe JSONs
- [ ] Create loot table JSONs
- [ ] Create advancement JSONs
- [ ] Create tag JSONs (blocks, items, entities)

### Asset Verification
- [x] Verify all blockstate JSONs
- [ ] Verify all model JSONs
- [ ] Verify all texture paths
- [ ] Update any outdated formats

---
