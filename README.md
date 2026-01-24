# Thaumcraft 6 - 1.20.1 Port

This repository contains the ongoing effort to port **Thaumcraft 6** from Minecraft 1.12.2 (Forge) to Minecraft 1.20.1 (Forge).

## 📊 Feature Parity: ~93%

```
███████████████████████░░ 93%
```

The port is nearly complete with all core systems functional and all recipes implemented. Remaining work focuses on visual polish, GUI completion, and testing.

---

## ✅ Current Status: PLAYABLE

**The game runs and loads into a world successfully!**

As of January 2026, the mod:
- Compiles without errors
- Loads in Minecraft 1.20.1 with Forge 47.3.0
- Player can join world and play
- Aura system runs (background threads for all dimensions)
- Research system loads (64 entries across 7 categories)
- Blocks and items are registered and functional

### Known Issues (Non-Fatal)
- Some research entries fail to load due to uppercase ResourceLocation names (1.20+ requires lowercase)
- Recipe book categories for custom recipes show warnings
- Some research JSON uses old item stack format that needs updating

---

## 🚧 Development Status

### Progress by Category

| Category | Ported | Original | Parity | Status |
|----------|--------|----------|--------|--------|
| **Java Files** | 698 | 901 | 77% | 🔄 In Progress |
| **Blocks** | 191 | 91+ | 100%+ | ✅ Complete |
| **Items** | 175 | 90+ | 100%+ | ✅ Complete |
| **Block Entities** | 50 | 31 | 100%+ | ✅ Complete |
| **Entities** | 46 | 35+ | 100%+ | ✅ Complete |
| **Mob Effects** | 9 | 9 | 100% | ✅ Complete |
| **Menus/GUIs** | 20 | 22 | 91% | 🔄 In Progress |
| **Entity Renderers** | 34 | ~40 | 85% | 🔄 In Progress |
| **Block Entity Renderers** | 23 | ~25 | 92% | 🔄 In Progress |

### Recipe Progress

| Recipe Type | Created | Original | Parity |
|-------------|---------|----------|--------|
| **Arcane Workbench** | 81 | 81 | 100% |
| **Crucible** | 57 | 52 | 100%+ |
| **Infusion** | 60 | 60 | 100% |
| **Vanilla Crafting** | 64 | 64 | 100% |
| **Smelting** | 8 | 8 | 100% |
| **Total** | **270** | **265** | **100%+** |

### System Status

| System | Status | Notes |
|--------|--------|-------|
| **Build System** | ✅ Complete | Gradle 8.8, Java 17+, Forge 47.3.0 |
| **Registration** | ✅ Complete | All DeferredRegister classes done |
| **Aspect System** | ✅ Complete | All 51 aspects, AspectList, containers |
| **Aura System** | ✅ Complete | Chunk-based vis/flux, background thread |
| **Research API** | ✅ Complete | Categories, stages, scanning |
| **Crafting Systems** | ✅ Complete | Arcane, Crucible, Infusion, Thaumatorium |
| **Essentia System** | ✅ Complete | Tubes, jars, transport, centrifuge |
| **Infusion Altar** | ✅ Complete | Matrix, pedestals, stabilizers, instability |
| **Golem System** | 🔄 Partial | Entity done; seal AI needs work |
| **Focus/Casting** | ✅ Complete | Caster, foci, effects |
| **Curios Integration** | ✅ Complete | Replaces Baubles API |
| **World Generation** | 🔄 Partial | Biomes, ores done; structures partial |
| **Particles** | 🔄 Partial | Core particles; some effects pending |
| **Networking** | ✅ Complete | PacketHandler with SimpleChannel |

---

## 📦 What's Implemented

### Blocks (191 registered)
- **Crafting**: Arcane Workbench, Crucible, Infusion Matrix, Research Table, Thaumatorium, Focal Manipulator, Pattern Crafter
- **Essentia**: Jars (normal/void/brain), Tubes (6 types), Alembic, Smelters (basic/thaumium/void), Centrifuge
- **Devices**: Lamps (arcane/growth/fertility), Mirrors, Pedestals, Bellows, Hungry Chest, Levitator, Condenser
- **World**: Ores (amber/cinnabar/quartz + deepslate), Crystals, Plants, Trees (Greatwood/Silverwood)
- **Taint**: Taint Fibre, Soil, Rock, Crust, Log, Feature, Geyser, Flux Goo
- **Decorative**: Candles (16), Nitor (16), Banners (17), Paving Stones, Metal Blocks

### Items (175 registered)
- **Tools**: Thaumium, Void, Elemental sets (5 tools each), Primal Crusher, Crimson Blade
- **Armor**: Thaumium, Void, Fortress, Robes, Void Robes, Cultist sets
- **Curios**: Goggles, Vis Amulet, Cloud Ring, Curiosity Band, Charms (Verdant/Voidseer/Undying)
- **Caster**: Basic Gauntlet, Foci (3 tiers), Focus Pouch
- **Resources**: Ingots, Nuggets, Plates, Clusters, Crystals, Phials, Mechanisms

### Entities (46 registered)
- **Bosses**: Eldritch Warden, Eldritch Golem, Cultist Leader, Giant Taintacle
- **Monsters**: Wisps, Pech, Mind Spider, Eldritch creatures, Cultists, Taint creatures
- **Constructs**: Thaumcraft Golem, Turrets (2 types), Arcane Bore
- **Projectiles**: Focus projectiles, Alumentum, Bottle Taint, Grapple
- **Special**: Flux Rift, Cultist Portal, Following Item

### Recipes (172 created)
- **Arcane** (55): Mechanisms, Thaumometer, Goggles, Tubes, Smelters, Devices, Armor
- **Crucible** (57): Metal transmutation, Vis crystals, Seals, Hedge alchemy
- **Infusion** (60): Foci, Mirrors, Lamps, Tools, Armor, Curios, Clusters

---

## 🏗 Project Structure

```
src/main/java/thaumcraft/
├── Thaumcraft.java              # Main mod class
├── init/                        # Registration
│   ├── ModBlocks.java           # 191 blocks
│   ├── ModItems.java            # 175 items
│   ├── ModBlockEntities.java    # 50 block entities
│   ├── ModEntities.java         # 46 entities
│   ├── ModEffects.java          # 9 effects
│   ├── ModRecipeTypes.java      # Recipe types
│   └── ModMenuTypes.java        # Menu types
├── api/                         # Public API
│   ├── aspects/                 # Aspect, AspectList
│   ├── research/                # Research system
│   ├── crafting/                # Recipe interfaces
│   └── aura/                    # Aura helpers
├── common/                      # Server-side
│   ├── blocks/                  # Block implementations
│   ├── tiles/                   # Block entities
│   ├── items/                   # Item implementations
│   ├── entities/                # Entity implementations
│   ├── golems/                  # Golem system
│   ├── world/                   # World gen, aura
│   ├── lib/crafting/            # Recipe implementations
│   └── menu/                    # Container menus
└── client/                      # Client-side
    ├── gui/screens/             # GUI screens
    ├── renderers/               # Renderers
    └── models/                  # Entity models

src/main/java_old/               # Original 1.12.2 (REFERENCE ONLY)

src/main/resources/
├── data/thaumcraft/
│   ├── recipes/                 # 172 JSON recipes
│   │   ├── arcane_workbench/    # 81 arcane recipes
│   │   ├── crafting/            # 64 vanilla recipes
│   │   ├── crucible/            # 57 crucible recipes
│   │   ├── infusion/            # 60 infusion recipes
│   │   └── smelting/            # 8 smelting recipes
│   ├── worldgen/                # Biomes, features
│   └── tags/                    # Block/item tags
└── assets/thaumcraft/
    ├── textures/                # All textures
    ├── models/                  # Block/item models
    ├── blockstates/             # Block states
    └── lang/                    # 9 languages
```

---

## 🔧 Key Changes from 1.12.2

| 1.12.2 | 1.20.1 |
|--------|--------|
| `RegistryEvent.Register<T>` | `DeferredRegister<T>` |
| `TileEntity` | `BlockEntity` |
| `readFromNBT`/`writeToNBT` | `load`/`saveAdditional` |
| `ITickable` | `BlockEntityTicker` |
| `EntityEntry` | `EntityType<T>` |
| `EntityAIBase` | `Goal` |
| `SharedMonsterAttributes` | `AttributeSupplier` |
| Baubles API | Curios API |
| Hardcoded recipes | JSON data-driven |
| `GuiContainer` | `AbstractContainerScreen` |

---

## 🛠 Building & Running

### Prerequisites
- JDK 17 or higher
- Gradle (wrapper included)

### Commands
```bash
# Compile (verify no errors)
./gradlew compileJava

# Build JAR
./gradlew build

# Run Client (requires Java 17)
export JAVA_HOME=/path/to/java17
./gradlew runClient

# Run Server  
./gradlew runServer

# IDE Setup
./gradlew genIntellijRuns   # IntelliJ IDEA
./gradlew genEclipseRuns    # Eclipse
```

### Java Version Note
The project requires **Java 17** to run. If you have multiple Java versions installed, set `JAVA_HOME` before running:
```bash
# Example on Linux
export JAVA_HOME=/home/user/.gradle/jdks/eclipse_adoptium-17-amd64-linux.2
./gradlew runClient
```

### Output
`build/libs/thaumcraft-1.20.1-6.0.0.jar`

---

## 📋 Remaining Work (~7%)

### High Priority
- [x] ~~Create all arcane recipes~~ (81/81)
- [x] ~~Create vanilla crafting recipes~~ (64/64)
- [x] ~~Create smelting recipes~~ (8/8)
- [x] ~~Fix runClient blocker~~ (mods.toml format, BlockTCDevice constructor)
- [x] ~~Fix duplicate capability registration~~
- [ ] Fix research JSON files (lowercase ResourceLocation names)
- [ ] Fix item stack parsing in research system

### Medium Priority
- [ ] Implement golem seal-based AI switching
- [ ] Port remaining 5 GUIs (17/22)
- [ ] Complete entity renderers (29/~40)
- [ ] Port theorycraft minigame
- [ ] Finish particle effects

### Lower Priority
- [ ] Re-enable Parchment mappings (currently using official)
- [ ] Re-enable Curios runtime dependency
- [ ] Port multiblock detection system
- [ ] Create structure generation (eldritch obelisk, etc.)
- [ ] Polish and testing

---

## 📝 Contributing

1. Check [TODO.md](TODO.md) for detailed task breakdown
2. Reference original code in `src/main/java_old/`
3. Follow existing patterns in new source
4. Test with `./gradlew compileJava`

### Quick Start Areas
- Add missing recipes in `src/main/resources/data/thaumcraft/recipes/`
- Port GUIs from `java_old/client/gui/` to `client/gui/screens/`
- Complete entity renderers in `client/renderers/entity/`

---

## 📄 License

Community port of Thaumcraft. Original mod by Azanor.

---

*Last updated: January 23, 2026 | Build: Passing | Game: Playable*
