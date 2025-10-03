# Soul SMP Plugin

The Phase 1 foundation for the Soul SMP experience. This module handles soul assignment, persistent player data, and the signature action bar layout that future abilities will plug into.

## ✨ Features (Phase 1)

- One soul per player with persistent storage.
- Configurable, Unicode-powered action bar that mirrors the legacy Phase layout.
- Automatic soul attunement on first join (optional) with friendly announcements.
- `/soul` command for viewing, listing, and administratively managing assignments.
- Lightweight framework ready for upcoming abilities, cooldowns, and mastery paths.

## 📦 Installation

1. Build the plugin (see below) or download a prebuilt JAR.
2. Drop the JAR into your Paper/Spigot 1.21+ server's `plugins/` folder.
3. Start the server to generate the default configuration at `plugins/SoulSMP/config.yml`.
4. Adjust settings such as action bar behaviour or soul assignment rules as needed.

## 🛠️ Building from Source

### Prerequisites
- Java 21+
- Git

### Build steps
```bash
git clone <repository-url>
cd soul-plugin
./gradlew build
```

The shaded plugin will be generated at `build/libs/SoulSMP-0.1.0.jar`.

## ⚙️ Configuration Highlights

`config.yml` exposes the following key sections:

- `action-bar`: Toggle detailed glyphs, tweak cooldown displays, and set refresh cadence.
- `souls`: Control whether players receive a soul automatically and whether reassignment is allowed.
- `storage`: Tune automatic save intervals.

Every option is documented inline for quick iteration.

## 🔑 Permissions

- `soulsmp.player.use` — Access to basic Soul SMP features (default: true).
- `soulsmp.admin` — Administrative soul controls (default: OP).

## 🏗️ Project Layout

```
src/main/java/dev/soulsmp/
├── SoulSMP.java                # Plugin bootstrap
├── actionbar/ActionBarManager.java
├── commands/SoulCommand.java
├── data/DataManager.java
├── data/PlayerData.java
├── listeners/PlayerJoinListener.java
├── listeners/PlayerQuitListener.java
└── soul/
    ├── SoulManager.java
    └── SoulType.java
```

## 📚 API Target
Built for Paper API `1.21-R0.1`.

## 🤝 Contributing
Contributions are welcome as we move through the roadmap! Please open a PR or reach out with ideas for future phases.