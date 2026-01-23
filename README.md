# Thaumcraft 6 - 1.20.1 Port

This repository contains the ongoing effort to port **Thaumcraft 6** from Minecraft 1.12.2 (Forge) to Minecraft 1.20.1 (Forge).

## 🚧 Status: Active Development (Phase 3-5)

The project has completed the foundation phase and is actively implementing blocks, items, recipes, and block entities.

### Progress Overview

| Category | Count | Status |
|----------|-------|--------|
| **Blocks** | 120+ | ✅ Registered |
| **Items** | 90+ | ✅ Registered |
| **Block Entities** | 35+ | ✅ Ported |
| **Entities** | 35+ | ✅ Registered |
| **Recipes** | 80+ | 🔄 In Progress |
| **Research System** | - | 🔄 In Progress |
| **World Generation** | - | ❌ Pending |

### Feature Status

| Feature | Status | Notes |
|---------|--------|-------|
| **Build System** | ✅ Complete | Gradle 8.8, Java 17+, Forge 47.3.0 |
| **Registration** | ✅ Complete | `DeferredRegister` for all registries |
| **Blocks** | ✅ Complete | All major blocks registered and implemented |
| **Block Entities** | ✅ Complete | Crafting, devices, essentia systems ported |
| **Items** | ✅ Complete | Tools, armor, curios, resources registered |
| **Entities** | 🔄 In Progress | Registration done; some renderers pending |
| **Recipes** | 🔄 In Progress | 80+ recipes (40 arcane, 18 crucible, 22 infusion) |
| **API** | ✅ Complete | Aspects, Aura, Research, Crafting APIs ported |
| **Capabilities** | ✅ Complete | Player knowledge and warp systems |
| **Curios Integration** | ✅ Complete | Baubles replaced with Curios API |
| **Research System** | 🔄 In Progress | Core system ported; GUI needs work |
| **World Generation** | ❌ Pending | Biomes, ores, structures pending |
| **Networking** | ✅ Complete | `PacketHandler` with SimpleChannel |

## 🏗 Project Structure

```
src/main/java/thaumcraft/
├── Thaumcraft.java          # Main mod entry point
├── init/                    # DeferredRegister classes
│   ├── ModBlocks.java       # 120+ blocks
│   ├── ModItems.java        # 90+ items
│   ├── ModBlockEntities.java # 35+ block entities
│   ├── ModEntities.java     # All entities
│   ├── ModEffects.java      # Mob effects
│   └── ...
├── api/                     # Public API
│   ├── aspects/             # Aspect system
│   ├── research/            # Research API
│   └── crafting/            # Recipe types
├── common/                  # Implementation
│   ├── blocks/              # Block classes
│   ├── tiles/               # Block entities
│   ├── items/               # Item classes
│   └── entities/            # Entity classes
└── client/                  # Client-side code
    ├── gui/                 # Screens
    └── renderers/           # Block entity & entity renderers

src/main/java_old/           # Original 1.12.2 code (REFERENCE ONLY)

src/main/resources/
├── data/thaumcraft/recipes/ # JSON recipes
│   ├── arcane_workbench/    # 40 recipes
│   ├── crucible/            # 18 recipes
│   └── infusion/            # 22 recipes
└── assets/thaumcraft/       # Textures, models, lang
```

## 📦 Implemented Systems

### Blocks
- **Crafting**: Arcane Workbench, Crucible, Infusion Matrix, Research Table, Thaumatorium, Focal Manipulator
- **Essentia**: Jars (normal/void/brain), Tubes (6 types), Alembic, Smelter, Centrifuge
- **Devices**: Lamps (arcane/growth/fertility), Mirrors, Pedestals, Bellows, Hungry Chest
- **World**: Ores, Crystals, Plants (Shimmerleaf, Cinderpearl, Vishroom), Trees (Greatwood, Silverwood)
- **Decorative**: Candles (16 colors), Nitor (16 colors), Banners (17 variants), Paving Stones
- **Special**: Flux Goo, Taint blocks, Liquid Death, Purifying Fluid, Barrier system

### Items
- **Tools**: Thaumium, Void, Elemental tool sets, Primal Crusher
- **Armor**: Thaumium, Void, Fortress, Robes sets
- **Curios**: Goggles, Amulets, Rings, Charms (via Curios API)
- **Caster**: Gauntlet, Foci (3 tiers), Focus Pouch
- **Resources**: Ingots, Nuggets, Plates, Crystals, Phials

### Recipes (80+ total)
- **Arcane Workbench** (40): Thaumometer, Goggles, Tubes, Smelters, Devices
- **Crucible** (18): Metal transmutation, Fabric, Soap, Bath Salts
- **Infusion** (22): Foci, Mirrors, Lamps, Armor upgrades, Curios

## 🔧 Key Architectural Changes from 1.12.2

### Registration
- `RegistryEvent.Register<T>` → `DeferredRegister<T>`
- Centralized in `thaumcraft.init.*` classes

### Block Entities
- `TileEntity` → `BlockEntity`
- `readFromNBT`/`writeToNBT` → `load`/`saveAdditional`
- Ticking via `BlockEntityTicker` interface

### Entities
- `EntityEntry` → `EntityType<T>`
- AI: `EntityAIBase` → `Goal` system
- Attributes: `SharedMonsterAttributes` → `AttributeSupplier`

### Recipes
- Hardcoded recipes → JSON data-driven recipes
- Custom `RecipeType` and `RecipeSerializer` implementations

### Dependencies
- **Baubles** → **Curios API**
- Updated to 1.20.1 Forge APIs

## 🛠 Building & Running

### Prerequisites
- JDK 17 or higher
- Gradle (wrapper included)

### Commands
```bash
# Build the mod
./gradlew build

# Run Client
./gradlew runClient

# Run Server
./gradlew runServer

# Setup IDE
./gradlew genIntellijRuns   # IntelliJ IDEA
./gradlew genEclipseRuns    # Eclipse
```

### Output
Built JAR located at: `build/libs/thaumcraft-1.20.1-*.jar`

## 📝 Contributing

1. Check [TODO.md](TODO.md) for the detailed task breakdown
2. Reference the original 1.12.2 code in `src/main/java_old/`
3. Follow the existing code patterns in the new source
4. Test changes with `./gradlew build`

### Priority Areas
- World generation (biomes, ores, structures)
- Entity renderers and models
- GUI/Container implementations
- Additional recipes

## 📄 License

This is a community port of Thaumcraft. Original mod by Azanor.

---
*Last updated: January 2026*
