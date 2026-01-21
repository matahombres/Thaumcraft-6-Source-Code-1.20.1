# Thaumcraft 6 - Minecraft 1.20.1 Migration Plan

> **Original Version:** Minecraft 1.12.2 with Forge 14.23.5.x  
> **Target Version:** Minecraft 1.20.1 with Forge 47.3.0  
> **Estimated Total Effort:** 44 weeks (~10-11 months for 1 developer)

---

## Project Statistics

| Category | Count | Status |
|----------|-------|--------|
| Total Java Files | 901 | Pending |
| Blocks | 88 | Pending |
| Items | 90+ | Pending |
| Entities | 35+ | Pending |
| Tile Entities | 31 | Pending |
| GUIs | 20+ | Pending |
| Particles | 100+ | Pending |
| Recipes | 200+ | Pending |

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
- [ ] Set up mod event bus listeners
- [ ] Set up Forge event bus listeners
- [ ] Create client setup event handler
- [ ] Create common setup event handler
- [ ] Create data generation event handler

### Network System
- [ ] Create `PacketHandler.java` with SimpleChannel
- [ ] Define packet registration system
- [ ] Create base packet interface
- [ ] Test client-server communication

### Capability System
- [ ] Create capability registration event handler
- [ ] Define `IPlayerKnowledge` capability interface
- [ ] Define `IPlayerWarp` capability interface
- [ ] Implement capability attachment to players
- [ ] Implement capability serialization (NBT save/load)

### Commands
- [ ] Migrate `CommandThaumcraft.java` to Brigadier API
- [ ] Register commands in `RegisterCommandsEvent`

---

## Phase 2: Core API (Weeks 3-5)
**Goal:** Migrate API and core systems

### Aspect System (`api/aspects/`)
- [ ] Port `Aspect.java` - Define all primal and compound aspects
- [ ] Port `AspectList.java` - Aspect container class
- [ ] Port `AspectHelper.java` - Aspect utilities
- [ ] Port `AspectEventProxy.java` - Event handling
- [ ] Port `AspectSourceHelper.java` - Source utilities
- [ ] Port `IAspectContainer.java` interface
- [ ] Port `IAspectSource.java` interface
- [ ] Port `IEssentiaContainerItem.java` interface
- [ ] Port `IEssentiaTransport.java` interface
- [ ] Create aspect registration system
- [ ] Create aspect texture loading

### Aura System (`api/aura/`, `common/world/aura/`)
- [ ] Port `AuraHelper.java` - Public API
- [ ] Port `AuraWorld.java` - Per-world aura data
- [ ] Port `AuraChunk.java` - Per-chunk storage
- [ ] Port `AuraHandler.java` - Main handler
- [ ] Port `AuraThread.java` - Background simulation
- [ ] Update to new chunk data attachment system
- [ ] Update to new `SavedData` system
- [ ] Implement aura tick scheduling

### Research API (`api/research/`)
- [ ] Port `ResearchEntry.java` - Research entry class
- [ ] Port `ResearchCategory.java` - Category organization
- [ ] Port `ResearchStage.java` - Stage definitions
- [ ] Port `IScanThing.java` interface
- [ ] Port `ScanningManager.java` - Scanning logic
- [ ] Define JSON format for research data
- [ ] Create research data loader

### Crafting API (`api/crafting/`)
- [ ] Port `IArcaneRecipe.java` interface
- [ ] Port `ShapedArcaneRecipe.java`
- [ ] Port `ShapelessArcaneRecipe.java`
- [ ] Port `CrucibleRecipe.java`
- [ ] Port `InfusionRecipe.java`
- [ ] Port `InfusionEnchantmentRecipe.java`
- [ ] Create `RecipeSerializer` implementations
- [ ] Create `RecipeType` registrations
- [ ] Define JSON recipe format

### Other API Classes
- [ ] Port `ThaumcraftApi.java` - Main API entry
- [ ] Port `ThaumcraftApiHelper.java` - Helper methods
- [ ] Port `ThaumcraftMaterials.java` - Tool/armor tiers
- [ ] Port `ThaumcraftInvHelper.java` - Inventory utilities
- [ ] Update `OreDictionaryEntries.java` to use Tags
- [ ] Create `BlocksTC.java` registry references
- [ ] Create `ItemsTC.java` registry references

### Internal API (`api/internal/`)
- [ ] Port `IInternalMethodHandler.java`
- [ ] Port `InternalMethodHandler.java` implementation
- [ ] Update all internal method signatures

---

## Phase 3: Blocks (Weeks 6-8)
**Goal:** Register all blocks

### Basic Blocks (23 blocks)
- [ ] Port ore blocks (amber, cinnabar, quartz)
- [ ] Port crystal blocks (6 primal + vitium)
- [ ] Port arcane stone variants (stone, brick, ancient)
- [ ] Port slabs and stairs (12+ variants)
- [ ] Port tables (wood, stone)
- [ ] Port pedestals (arcane, ancient, eldritch)
- [ ] Port metal blocks (brass, thaumium, void, alchemical)
- [ ] Port pillars (arcane, ancient, eldritch)
- [ ] Port candles (16 colors)
- [ ] Port banners (16 colors + crimson cult)
- [ ] Port nitor blocks (16 colors)
- [ ] Port misc blocks (amber, flesh, paving stones)

### Crafting Blocks (11 blocks)
- [ ] Port `BlockArcaneWorkbench.java`
- [ ] Port `BlockArcaneWorkbenchCharger.java`
- [ ] Port `BlockCrucible.java`
- [ ] Port `BlockFocalManipulator.java`
- [ ] Port `BlockInfusionMatrix.java`
- [ ] Port `BlockPatternCrafter.java`
- [ ] Port `BlockResearchTable.java`
- [ ] Port `BlockThaumatorium.java`
- [ ] Port `BlockThaumatoriumTop.java`
- [ ] Port `BlockVoidSiphon.java`

### Device Blocks (23 blocks)
- [ ] Port `BlockArcaneEar.java` (+ toggle variant)
- [ ] Port `BlockBellows.java`
- [ ] Port `BlockBrainBox.java`
- [ ] Port `BlockCondenser.java` (+ lattice)
- [ ] Port `BlockDioptra.java`
- [ ] Port `BlockHungryChest.java`
- [ ] Port `BlockInfernalFurnace.java`
- [ ] Port `BlockInlay.java`
- [ ] Port lamp blocks (arcane, fertility, growth)
- [ ] Port `BlockLevitator.java`
- [ ] Port mirror blocks (standard, essentia)
- [ ] Port pedestal blocks (standard, ancient, eldritch)
- [ ] Port `BlockPotionSprayer.java`
- [ ] Port `BlockRechargePedestal.java`
- [ ] Port `BlockRedstoneRelay.java`
- [ ] Port `BlockSpa.java`
- [ ] Port `BlockStabilizer.java`
- [ ] Port `BlockVisBattery.java`
- [ ] Port `BlockVisGenerator.java`
- [ ] Port `BlockWaterJug.java`

### Essentia Blocks (8 block types)
- [ ] Port `BlockAlembic.java`
- [ ] Port `BlockCentrifuge.java`
- [ ] Port smelter blocks (basic, thaumium, void, aux, vent)
- [ ] Port tube blocks (standard, valve, restrict, oneway, filter, buffer)
- [ ] Port jar blocks (normal, void, brain)
- [ ] Port essentia I/O blocks (input, output)

### World Blocks (25 blocks)
- [ ] Port tree blocks (greatwood, silverwood logs/leaves/planks)
- [ ] Port sapling blocks
- [ ] Port plant blocks (shimmerleaf, cinderpearl, vishroom)
- [ ] Port crystal ore blocks (6 types)
- [ ] Port standard ore blocks (3 types)
- [ ] Port loot blocks (crates, urns - 6 variants)
- [ ] Port taint blocks (fibre, crust, soil, rock, geyser, feature, log)
- [ ] Port ambient grass block
- [ ] Port flux goo block
- [ ] Port liquid blocks (death, purifying)

### Block Resources
- [ ] Verify all blockstate JSONs
- [ ] Create/update block model JSONs
- [ ] Verify block textures
- [ ] Create block tags

---

## Phase 4: Items (Weeks 9-10)
**Goal:** Register all items

### Core Items (8 items)
- [ ] Port `ItemThaumonomicon.java`
- [ ] Port `ItemCurio.java`
- [ ] Port `ItemLootBag.java`
- [ ] Port `ItemPrimordialPearl.java`
- [ ] Port `ItemPechWand.java`
- [ ] Port `ItemCelestialNotes.java`
- [ ] Port amber and quicksilver items

### Resource Items (25+ items)
- [ ] Port ingots (thaumium, void, brass, alchemical)
- [ ] Port nuggets (10+ metal types)
- [ ] Port clusters (metal ores)
- [ ] Port fabric, vis resonator, tallow
- [ ] Port mechanisms (simple, complex)
- [ ] Port plates (brass, iron, thaumium, void)
- [ ] Port filters, morphic resonator
- [ ] Port `ItemSalisMundus.java` (magic dust)
- [ ] Port mirrored glass, void seed
- [ ] Port mind modules (clockwork, biothaumic)
- [ ] Port `ItemCrystalEssence.java`
- [ ] Port chunks, meat treat, zombie brain
- [ ] Port labels, phials
- [ ] Port `ItemAlumentum.java`
- [ ] Port jar brace, bottle taint
- [ ] Port sanity soap, bath salts

### Tools (20 items)
- [ ] Port `ItemThaumometer.java`
- [ ] Port `ItemResonator.java`
- [ ] Port `ItemSanityChecker.java`
- [ ] Port `ItemHandMirror.java`
- [ ] Port `ItemScribingTools.java`
- [ ] Port thaumium tools (5: axe, sword, shovel, pickaxe, hoe)
- [ ] Port void tools (5: axe, sword, shovel, pickaxe, hoe)
- [ ] Port elemental tools (5: axe, sword, shovel, pickaxe, hoe)
- [ ] Port `ItemPrimalCrusher.java`
- [ ] Port `ItemCrimsonBlade.java`
- [ ] Port `ItemGrappleGun.java` (+ tip, spool)

### Armor (30+ items)
- [ ] Port `ItemGoggles.java`
- [ ] Port `ItemTravellerBoots.java`
- [ ] Port thaumium armor set (4 pieces)
- [ ] Port robe armor set (3 pieces)
- [ ] Port fortress armor set (3 pieces)
- [ ] Port void armor set (4 pieces)
- [ ] Port void robe armor set (3 pieces)
- [ ] Port crimson cult armor sets
- [ ] Port crimson praetor armor set

### Baubles/Curios (8 items) - Requires Curios API
- [ ] Port `ItemBaubles.java` base class to ICurioItem
- [ ] Port `ItemAmuletVis.java`
- [ ] Port `ItemCharmVerdant.java`
- [ ] Port `ItemBandCuriosity.java`
- [ ] Port `ItemCharmVoidseer.java`
- [ ] Port `ItemCloudRing.java`
- [ ] Port `ItemCharmUndying.java`
- [ ] Update goggles to work as Curio

### Caster Items (4+ items)
- [ ] Port `ItemCasterBasic.java`
- [ ] Port `ItemFocus.java` (3 tiers)
- [ ] Port `ItemFocusPouch.java`

### Golem Items (3 items)
- [ ] Port `ItemGolemBell.java`
- [ ] Port `ItemGolemPlacer.java`
- [ ] Port `ItemSealPlacer.java`

### Other Items
- [ ] Port turret placer
- [ ] Port causality collapser
- [ ] Port creative items (flux sponge, etc.)

### Item Resources
- [ ] Create/update item model JSONs
- [ ] Verify item textures
- [ ] Create item tags

---

## Phase 5: Block Entities (Weeks 11-12)
**Goal:** Migrate all tile entities to block entities

### Crafting Block Entities (12)
- [ ] Port `TileArcaneWorkbench.java`
- [ ] Port `TileDioptra.java`
- [ ] Port `TileCrucible.java`
- [ ] Port `TileFocalManipulator.java`
- [ ] Port `TilePedestal.java`
- [ ] Port `TileRechargePedestal.java`
- [ ] Port `TileResearchTable.java`
- [ ] Port `TileInfusionMatrix.java`
- [ ] Port `TilePatternCrafter.java`
- [ ] Port `TileThaumatorium.java`
- [ ] Port `TileThaumatoriumTop.java`
- [ ] Port `TileVoidSiphon.java`

### Device Block Entities (14)
- [ ] Port `TileArcaneEar.java`
- [ ] Port `TileLevitator.java`
- [ ] Port `TileLampGrowth.java`
- [ ] Port `TileLampArcane.java`
- [ ] Port `TileLampFertility.java`
- [ ] Port `TileMirror.java`
- [ ] Port `TileMirrorEssentia.java`
- [ ] Port `TileRedstoneRelay.java`
- [ ] Port `TileHungryChest.java`
- [ ] Port `TileInfernalFurnace.java`
- [ ] Port `TileSpa.java`
- [ ] Port `TileVisGenerator.java`
- [ ] Port `TileStabilizer.java`
- [ ] Port `TileCondenser.java`

### Essentia Block Entities (12)
- [ ] Port `TileCentrifuge.java`
- [ ] Port `TileBellows.java`
- [ ] Port `TileSmelter.java`
- [ ] Port `TileAlembic.java`
- [ ] Port `TileJar.java`
- [ ] Port `TileJarFillable.java`
- [ ] Port `TileJarFillableVoid.java`
- [ ] Port `TileJarBrain.java`
- [ ] Port `TileTube.java`
- [ ] Port `TileTubeValve.java`
- [ ] Port `TileTubeFilter.java`
- [ ] Port `TileTubeRestrict.java`
- [ ] Port `TileTubeOneway.java`
- [ ] Port `TileTubeBuffer.java`

### Other Block Entities (3)
- [ ] Port `TileBanner.java`
- [ ] Port `TileHole.java`
- [ ] Port `TileBarrierStone.java`

### Block Entity Migration Tasks
- [ ] Update all `readFromNBT` to `load(CompoundTag)`
- [ ] Update all `writeToNBT` to `saveAdditional(CompoundTag)`
- [ ] Update capability attachment system
- [ ] Update ticking system (`BlockEntityTicker`)
- [ ] Register all `BlockEntityType` instances

---

## Phase 6: Entities (Weeks 13-16)
**Goal:** Register and implement all entities

### Boss Entities (4)
- [ ] Port `EntityEldritchWarden.java`
- [ ] Port `EntityEldritchGolem.java`
- [ ] Port `EntityCultistLeader.java`
- [ ] Port `EntityTaintacleGiant.java`

### Monster Entities (20)
- [ ] Port `EntityBrainyZombie.java`
- [ ] Port `EntityGiantBrainyZombie.java`
- [ ] Port `EntityWisp.java`
- [ ] Port `EntityFirebat.java`
- [ ] Port `EntitySpellbat.java`
- [ ] Port `EntityPech.java`
- [ ] Port `EntityMindSpider.java`
- [ ] Port `EntityEldritchGuardian.java`
- [ ] Port `EntityEldritchCrab.java`
- [ ] Port `EntityCultistKnight.java`
- [ ] Port `EntityCultistCleric.java`
- [ ] Port `EntityInhabitedZombie.java`
- [ ] Port `EntityThaumicSlime.java`
- [ ] Port `EntityTaintCrawler.java`
- [ ] Port `EntityTaintacle.java`
- [ ] Port `EntityTaintacleSmall.java`
- [ ] Port `EntityTaintSwarm.java`
- [ ] Port `EntityTaintSeed.java`
- [ ] Port `EntityTaintSeedPrime.java`

### Construct Entities (4)
- [ ] Port `EntityThaumcraftGolem.java`
- [ ] Port `EntityTurretCrossbow.java`
- [ ] Port `EntityTurretCrossbowAdvanced.java`
- [ ] Port `EntityArcaneBore.java`

### Projectile Entities (10)
- [ ] Port `EntityAlumentum.java`
- [ ] Port `EntityBottleTaint.java`
- [ ] Port `EntityCausalityCollapser.java`
- [ ] Port `EntityEldritchOrb.java`
- [ ] Port `EntityFocusProjectile.java`
- [ ] Port `EntityFocusCloud.java`
- [ ] Port `EntityFocusMine.java`
- [ ] Port `EntityGolemDart.java`
- [ ] Port `EntityGolemOrb.java`
- [ ] Port `EntityGrapple.java`

### Special Entities (5)
- [ ] Port `EntityCultistPortal.java` (greater/lesser)
- [ ] Port `EntityFluxRift.java`
- [ ] Port `EntitySpecialItem.java`
- [ ] Port `EntityFollowingItem.java`
- [ ] Port `EntityFallingTaint.java`

### Entity Migration Tasks
- [ ] Update entity registration to `RegisterEvent<EntityType>`
- [ ] Update all entity constructors
- [ ] Rewrite all entity AI (Goal system)
- [ ] Update entity attributes (`AttributeSupplier`)
- [ ] Update spawn rules
- [ ] Update entity data serialization
- [ ] Create entity renderers
- [ ] Create entity models

---

## Phase 7: GUI & Containers (Weeks 17-19)
**Goal:** Implement all GUIs

### Container Classes
- [ ] Port `ContainerArcaneWorkbench.java`
- [ ] Port `ContainerFocalManipulator.java`
- [ ] Port `ContainerResearchTable.java`
- [ ] Port `ContainerThaumatorium.java`
- [ ] Port `ContainerSmelter.java`
- [ ] Port `ContainerHungryChest.java`
- [ ] Port `ContainerFocusPouch.java`
- [ ] Port all other container classes
- [ ] Register `MenuType` instances

### Screen Classes
- [ ] Port `GuiArcaneWorkbench.java` to `AbstractContainerScreen`
- [ ] Port `GuiFocalManipulator.java`
- [ ] Port `GuiResearchTable.java`
- [ ] Port `GuiThaumatorium.java`
- [ ] Port `GuiSmelter.java`
- [ ] Port `GuiHungryChest.java`
- [ ] Port `GuiFocusPouch.java`
- [ ] Port all other GUI classes

### Research GUI System
- [ ] Port `GuiResearchBrowser.java`
- [ ] Port `GuiResearchPage.java`
- [ ] Port `GuiResearchRecipe.java`
- [ ] Update research rendering system
- [ ] Port theorycraft minigame GUI

### GUI Migration Tasks
- [ ] Update all `drawGuiContainerBackgroundLayer` to `renderBg`
- [ ] Update all `drawGuiContainerForegroundLayer` to `renderLabels`
- [ ] Update mouse/keyboard event handling
- [ ] Update tooltip rendering
- [ ] Register screens with `MenuScreens.register`

---

## Phase 8: Crafting Systems (Weeks 20-24)
**Goal:** Implement all crafting systems

### Recipe Serializers
- [ ] Create `ArcaneRecipeSerializer.java`
- [ ] Create `CrucibleRecipeSerializer.java`
- [ ] Create `InfusionRecipeSerializer.java`
- [ ] Create `InfusionEnchantmentRecipeSerializer.java`
- [ ] Register all serializers

### Arcane Crafting
- [ ] Implement arcane workbench crafting logic
- [ ] Create JSON recipes for all arcane recipes
- [ ] Implement vis cost calculation
- [ ] Implement research requirement checking

### Crucible Alchemy
- [ ] Implement crucible melting logic
- [ ] Implement crucible crafting logic
- [ ] Create JSON recipes for all crucible recipes
- [ ] Implement aspect matching system
- [ ] Implement flux generation

### Infusion Crafting
- [ ] Implement infusion altar detection
- [ ] Implement infusion crafting logic
- [ ] Create JSON recipes for all infusion recipes
- [ ] Implement instability system
- [ ] Implement stabilizer detection
- [ ] Implement infusion effects (lightning, etc.)

### Other Crafting
- [ ] Implement infernal furnace smelting
- [ ] Implement thaumatorium crafting
- [ ] Implement centrifuge processing
- [ ] Implement golem press crafting

### Multiblock Detection
- [ ] Port multiblock detection system
- [ ] Update for new block state system
- [ ] Implement structure validation

---

## Phase 9: World Generation (Weeks 25-26)
**Goal:** Implement world generation

### Biome Registration
- [ ] Port `BiomeGenMagicalForest.java`
- [ ] Port `BiomeGenEerie.java`
- [ ] Port `BiomeGenEldritch.java`
- [ ] Create biome JSON definitions
- [ ] Register biomes with `RegisterEvent<Biome>`

### Ore Generation
- [ ] Create ore feature configurations
- [ ] Register ore placements
- [ ] Configure spawn rates per biome

### Tree Generation
- [ ] Port `WorldGenGreatwoodTrees.java`
- [ ] Port `WorldGenSilverwoodTrees.java`
- [ ] Create tree feature configurations
- [ ] Register tree placements

### Structure Generation
- [ ] Port `WorldGenMound.java` (barrows)
- [ ] Port other structure generators
- [ ] Create structure JSON definitions
- [ ] Register structure placements

### Aura World Generation
- [ ] Implement initial aura distribution
- [ ] Port vis node generation (if applicable)
- [ ] Port flux rift natural spawning

### Taint Spreading
- [ ] Port taint spread mechanics
- [ ] Update for new tick system
- [ ] Implement taint biome conversion

---

## Phase 10: Golem System (Weeks 27-29)
**Goal:** Implement golem system

### Golem Entity
- [ ] Complete `EntityThaumcraftGolem.java` port
- [ ] Implement golem materials (straw, wood, stone, iron, thaumium, void)
- [ ] Implement golem stats per material
- [ ] Implement golem upgrades

### Golem AI
- [ ] Rewrite golem pathfinding for 1.20.1
- [ ] Implement seal-based AI switching
- [ ] Port all golem Goal implementations
- [ ] Implement golem task priorities

### Seal System
- [ ] Port `SealHandler.java`
- [ ] Port `SealHarvest.java`
- [ ] Port `SealLumber.java`
- [ ] Port `SealFill.java` / `SealFillAdvanced.java`
- [ ] Port `SealEmpty.java` / `SealEmptyAdvanced.java`
- [ ] Port `SealGuard.java` / `SealGuardAdvanced.java`
- [ ] Port `SealPickup.java` / `SealPickupAdvanced.java`
- [ ] Port `SealUse.java`
- [ ] Port `SealProvide.java`
- [ ] Port `SealStock.java`
- [ ] Port `SealButcher.java`
- [ ] Port `SealBreaker.java` / `SealBreakerAdvanced.java`

### Golem Parts
- [ ] Port golem arm variants
- [ ] Port golem leg variants
- [ ] Port golem head variants
- [ ] Implement part combination system

### Golem GUI
- [ ] Port golem command GUI
- [ ] Port seal configuration GUI
- [ ] Port golem builder GUI

---

## Phase 11: Casting System (Weeks 30-32)
**Goal:** Implement wand/focus casting

### Caster Items
- [ ] Complete `ItemCasterBasic.java` port
- [ ] Implement vis storage and drain
- [ ] Implement focus attachment system
- [ ] Implement casting cooldowns

### Focus System
- [ ] Port `FocusEngine.java`
- [ ] Port `FocusNode.java`
- [ ] Implement focus combination tree
- [ ] Port focus effect calculation

### Focus Effects
- [ ] Port focus effect: Fire
- [ ] Port focus effect: Frost
- [ ] Port focus effect: Shock (Air)
- [ ] Port focus effect: Earth
- [ ] Port focus effect: Flux
- [ ] Port focus effect: Exchange
- [ ] Port focus effect: Rift
- [ ] Port focus effect: Heal
- [ ] Port focus effect: Curse
- [ ] Port all other focus effects

### Focus Mediums
- [ ] Port medium: Bolt
- [ ] Port medium: Projectile
- [ ] Port medium: Cloud
- [ ] Port medium: Mine
- [ ] Port medium: Touch
- [ ] Port medium: Plan

### Focus Modifiers
- [ ] Port modifier: Scatter
- [ ] Port modifier: Split (fork, scatter)
- [ ] Port all trajectory modifiers
- [ ] Port all potency modifiers

### Casting Visuals
- [ ] Implement casting particle effects
- [ ] Implement focus rendering on wand
- [ ] Implement cast beam rendering

---

## Phase 12: Visual Effects (Weeks 33-36)
**Goal:** Implement all visual effects

### Particle System
- [ ] Create particle registration system
- [ ] Port `ParticleEngine` integration
- [ ] Create `ParticleProvider` implementations

### Core Particles (estimated 100+)
- [ ] Port aura/vis particles
- [ ] Port flux particles
- [ ] Port infusion particles
- [ ] Port casting particles
- [ ] Port warp particles
- [ ] Port essentia particles
- [ ] Port research particles
- [ ] Port all other particle types

### Beam Rendering
- [ ] Port beam rendering system
- [ ] Update for new render system
- [ ] Implement beam particle spawning

### Shader Effects
- [ ] Port blur shader
- [ ] Port desaturation shader
- [ ] Port bloom shader
- [ ] Port other post-processing shaders
- [ ] Update GLSL syntax for 1.20.1

### Block Entity Renderers
- [ ] Port crucible renderer
- [ ] Port infusion altar renderer
- [ ] Port essentia jar renderer
- [ ] Port tube renderer
- [ ] Port pedestal renderer
- [ ] Port mirror renderer
- [ ] Port all other TESR classes
- [ ] Register with `EntityRenderersEvent.RegisterRenderers`

### Entity Renderers
- [ ] Port wisp renderer
- [ ] Port golem renderer
- [ ] Port eldritch creature renderers
- [ ] Port cultist renderers
- [ ] Port taint creature renderers
- [ ] Port all other entity renderers
- [ ] Port all entity models

---

## Phase 13: Research System (Weeks 37-39)
**Goal:** Complete research system

### Research Manager
- [ ] Port `ResearchManager.java`
- [ ] Update research data storage
- [ ] Implement research unlocking logic
- [ ] Implement research requirement checking

### Research Data
- [ ] Define JSON research format
- [ ] Convert all research entries to JSON
- [ ] Create research category definitions
- [ ] Create research stage definitions

### Scanning System
- [ ] Port `ScanningManager.java`
- [ ] Implement item scanning
- [ ] Implement block scanning
- [ ] Implement entity scanning
- [ ] Implement potion scanning
- [ ] Implement enchantment scanning
- [ ] Update thaumometer functionality

### Theorycraft Minigame
- [ ] Port `TheorycraftCard.java` classes
- [ ] Port theorycraft card effects
- [ ] Port research table minigame GUI
- [ ] Implement card drawing/playing logic

### Research GUI
- [ ] Complete research browser port
- [ ] Complete research page rendering
- [ ] Implement research recipe display
- [ ] Implement scanning popup display

---

## Phase 14: Sounds (Week 40)
**Goal:** Register all sounds

### Sound Registration
- [ ] Port `SoundsTC.java`
- [ ] Register all `SoundEvent` instances
- [ ] Create `sounds.json` file

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
- [ ] Port `PotionFluxTaint.java`
- [ ] Port `PotionVisExhaust.java`
- [ ] Port `PotionInfectiousVisExhaust.java`
- [ ] Port `PotionUnnaturalHunger.java`
- [ ] Port `PotionWarpWard.java`
- [ ] Port `PotionDeathGaze.java`
- [ ] Port `PotionBlurredVision.java`
- [ ] Port `PotionSunScorned.java`
- [ ] Port `PotionThaumarhia.java`

### Effect Implementation
- [ ] Update effect tick logic
- [ ] Update effect rendering
- [ ] Implement effect application

---

## Phase 16: Resources & Localization (Weeks 41-42)
**Goal:** Update all resources

### Language Files
- [ ] Convert `en_us.lang` to JSON format
- [ ] Convert `de_de.lang` to JSON format
- [ ] Convert `fr_fr.lang` to JSON format
- [ ] Convert `ja_jp.lang` to JSON format
- [ ] Convert `ko_kr.lang` to JSON format
- [ ] Convert all other language files

### Data Pack Resources
- [ ] Create all recipe JSONs
- [ ] Create loot table JSONs
- [ ] Create advancement JSONs
- [ ] Create tag JSONs (blocks, items, entities)

### Asset Verification
- [ ] Verify all blockstate JSONs
- [ ] Verify all model JSONs
- [ ] Verify all texture paths
- [ ] Update any outdated formats

---

## Phase 17: Testing & Polish (Weeks 43-44)
**Goal:** Test and fix issues

### Functional Testing
- [ ] Test all blocks place/break correctly
- [ ] Test all items function correctly
- [ ] Test all entities spawn and behave
- [ ] Test all crafting recipes
- [ ] Test research progression
- [ ] Test aura system
- [ ] Test golem system
- [ ] Test casting system
- [ ] Test world generation

### Integration Testing
- [ ] Test multiplayer compatibility
- [ ] Test with JEI integration
- [ ] Test data pack loading
- [ ] Test server-only functionality
- [ ] Test client-only functionality

### Performance Testing
- [ ] Profile aura tick performance
- [ ] Profile particle rendering
- [ ] Profile chunk generation
- [ ] Profile entity AI
- [ ] Optimize bottlenecks

### Bug Fixes
- [ ] Fix all critical bugs
- [ ] Fix all major bugs
- [ ] Fix minor bugs as time permits

### Documentation
- [ ] Update README.md
- [ ] Create CHANGELOG.md
- [ ] Document API changes
- [ ] Create migration guide for addon developers

### Release Preparation
- [ ] Final code cleanup
- [ ] Remove debug code
- [ ] Update version numbers
- [ ] Create release build
- [ ] Test release build

---

## Appendix A: File Reference

### Key Source Files (in `src/main/java_old/`)

| File | Purpose | Priority |
|------|---------|----------|
| `Thaumcraft.java` | Main mod class | Done |
| `Registrar.java` | Event-based registration | High |
| `common/config/ConfigBlocks.java` | Block registration | High |
| `common/config/ConfigItems.java` | Item registration | High |
| `common/config/ConfigEntities.java` | Entity registration | High |
| `common/config/ConfigRecipes.java` | Recipe registration | High |
| `api/ThaumcraftApi.java` | Public API | High |
| `api/aspects/Aspect.java` | Aspect definitions | High |
| `common/world/aura/AuraHandler.java` | Aura system | High |
| `common/lib/research/ResearchManager.java` | Research system | High |

### New Source Files (in `src/main/java/thaumcraft/`)

| File | Purpose | Status |
|------|---------|--------|
| `Thaumcraft.java` | Main mod class | Created |
| `init/ModBlocks.java` | Block DeferredRegister | Stub |
| `init/ModItems.java` | Item DeferredRegister | Stub |
| `init/ModEntities.java` | Entity DeferredRegister | Stub |
| `init/ModBlockEntities.java` | BlockEntity DeferredRegister | Stub |
| `init/ModEffects.java` | MobEffect DeferredRegister | Stub |
| `init/ModSounds.java` | SoundEvent DeferredRegister | Stub |
| `init/ModCreativeTabs.java` | CreativeModeTab DeferredRegister | Stub |

---

## Appendix B: API Migration Reference

### Package Renames
```
net.minecraft.block.Block -> net.minecraft.world.level.block.Block
net.minecraft.item.Item -> net.minecraft.world.item.Item
net.minecraft.entity.Entity -> net.minecraft.world.entity.Entity
net.minecraft.tileentity.TileEntity -> net.minecraft.world.level.block.entity.BlockEntity
net.minecraft.nbt.NBTTagCompound -> net.minecraft.nbt.CompoundTag
net.minecraft.util.ResourceLocation -> net.minecraft.resources.ResourceLocation
net.minecraft.world.World -> net.minecraft.world.level.Level
net.minecraft.entity.player.EntityPlayer -> net.minecraft.world.entity.player.Player
net.minecraft.util.math.BlockPos -> net.minecraft.core.BlockPos
```

### Common Method Renames
```java
// NBT
readFromNBT(NBTTagCompound) -> load(CompoundTag)
writeToNBT(NBTTagCompound) -> saveAdditional(CompoundTag)

// Items
hasEffect(ItemStack) -> isFoil(ItemStack)
getItemStackDisplayName() -> getName()

// Blocks
getStateFromMeta(int) -> [removed - use BlockState properties]
getMetaFromState(IBlockState) -> [removed - use BlockState properties]

// World
getWorld() -> level() or getLevel()
world.isRemote -> level.isClientSide()

// Player
player.inventory -> player.getInventory()
```

---

## Appendix C: Risk Assessment

### High Risk (Requires significant rewrite)
- Entity AI system
- Particle system (100+ particles)
- Research GUI system
- Golem AI and behavior
- Network packet system

### Medium Risk (Moderate changes needed)
- Recipe system (data pack conversion)
- Block entity system (31 TEs)
- World generation
- GUI system
- Rendering system

### Low Risk (Straightforward migration)
- Basic blocks and items
- Sound registration
- Potion/effect registration
- Localization files
- API interfaces

---

## Appendix D: Dependencies

### Required Dependencies
- Minecraft Forge 1.20.1 (47.3.0)
- Curios API (5.4.7+1.20.1) - replaces Baubles

### Optional Dependencies (for integration)
- JEI (for recipe viewing)
- TOP/WAILA (for block info)

### Removed Dependencies
- Baubles (replaced by Curios)
- OreDictionary (replaced by Tags)

---

*Last Updated: January 2026*
*Target Completion: ~44 weeks from start*
