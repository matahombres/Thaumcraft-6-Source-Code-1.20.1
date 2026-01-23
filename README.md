# Thaumcraft 6 - 1.20.1 Port

This repository contains the ongoing effort to port **Thaumcraft 6** from Minecraft 1.12.2 (Forge) to Minecraft 1.20.1 (Forge).

## 🚧 Status: Early Development (Phase 1/2)

The project is currently in the **foundation and core API migration phase**. While the file structure has been established and many core classes have been created, significant logic implementation is still pending.

| Feature | Status | Notes |
|---------|--------|-------|
| **Build System** | ✅ Complete | Gradle 8, Java 17, Forge 1.20.1 |
| **Registration** | ✅ Complete | Migrated to `DeferredRegister` system |
| **Blocks** | 🔄 In Progress | Basic blocks registered; Tile Entities migrated to Block Entities |
| **Items** | 🔄 In Progress | Basic items registered; Curios integration started |
| **Entities** | 🔄 In Progress | `EntityType` registration done; AI/Goals pending rewrite |
| **API** | 🔄 In Progress | Core API interfaces ported; Internal logic pending |
| **Research** | ❌ Pending | GUI and logic need complete rewrite |
| **Networking** | 🔄 In Progress | `PacketHandler` setup; Packets need updating |

## 🏗 Project Structure

The repository is organized to facilitate reference-based porting:

*   **`src/main/java/thaumcraft/`**: The **NEW** 1.20.1 source code.
    *   `Thaumcraft.java`: Main mod entry point.
    *   `init/`: `DeferredRegister` classes for Blocks, Items, Entities, etc.
    *   `api/`: The public API (being updated).
    *   `common/`: Implementation logic.
*   **`src/main/java_old/`**: The **OLD** 1.12.2 source code (Reference).
    *   Contains the original decompiled source for comparison.
    *   Do **NOT** edit files in this directory.

## 🔧 Key Architectural Changes

### 1. Registration System
*   **1.12.2**: Used `RegistryEvent.Register<T>` in `Registrar.java`.
*   **1.20.1**: Uses `DeferredRegister<T>` in `thaumcraft.init.*` classes.

### 2. Block & Tile Entities
*   **Blocks**: `BlockTC` inheritance replaced by standard `Block` with `BlockBehaviour.Properties`.
*   **Tile Entities**: Renamed to **Block Entities**.
    *   `TileEntity` -> `BlockEntity`
    *   `getUpdatePacket` -> `getUpdatePacket` (Network)
    *   `readFromNBT`/`writeToNBT` -> `load`/`saveAdditional`
    *   Ticking logic moved to `BlockEntityTicker`.

### 3. Entities (Mobs)
*   **Registration**: `EntityEntry` -> `EntityType`.
*   **AI**: Old `EntityAIBase` tasks are being rewritten to the new `Goal` system.
*   **Rendering**: `Render` classes migrated to `EntityRenderer`, registered via `EntityRenderersEvent.RegisterRenderers`.

### 4. Dependencies
*   **Baubles**: Removed. Replaced by **Curios API**.
*   **JEI**: Updated to 1.20.1 API.

## 🛠 Building & Running

### Prerequisites
*   JDK 17
*   Gradle (Wrapper included)

### Commands
```bash
# Build the mod
./gradlew build

# Run Client
./gradlew runClient

# Setup IDE (IntelliJ/Eclipse)
./gradlew genIntellijRuns
# or
./gradlew genEclipseRuns
```

## 📝 TODO
Refer to [TODO.md](TODO.md) for a detailed breakdown of remaining tasks.

---
*Original Thaumcraft by Azanor. Porting effort by Community.*
