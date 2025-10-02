# Soul SMP Plugin

[![Build](https://github.com/KaustubhDurgade/soul-smp/actions/workflows/build.yml/badge.svg)](https://github.com/KaustubhDurgade/soul-smp/actions/workflows/build.yml)

A custom Paper plugin bringing a souls-and-paths progression system to multiplayer Minecraft. Players choose from 13 unique souls, each with distinct abilities, resource mechanics, and branching upgrade paths.

---

## 🚀 Quick Start

### Prerequisites
- **Java 21** (OpenJDK or Adoptium Temurin)
- **Paper 1.21.6** server
- **Gradle** (wrapper included)

### Deploy & Run
```bash
cd scripts
./deploy_plugin.sh
```

This automatically:
1. Stops any running server
2. Builds the plugin
3. Deploys to `server/plugins/`
4. Starts the Paper server

**Manual build:**
```bash
cd plugin
./gradlew build
# JAR output: plugin/build/libs/soul-smp-0.1.0.jar
```

---

## 📂 Project Structure

```
soul-smp/
├── plugin/          # Java plugin source code
├── docs/            # API & user documentation
├── plans/           # Design specs & roadmaps
├── scripts/         # Build & deployment automation
└── server/          # Local test server (gitignored)
```

**Detailed structure:** See [`docs/PROJECT-STRUCTURE.md`](docs/PROJECT-STRUCTURE.md)

---

## 📚 Documentation

### For Players & Admins
- [**Commands Reference**](docs/commands.md) - Full `/souls` command suite
- [**Configuration Guide**](docs/configuration.md) - `config.yml` reference
- [**Soul Catalog**](docs/catalog.md) - How to define souls
- [**UI Examples**](docs/ui-examples.md) - ActionBar/BossBar displays

### For Developers
- [**Project Structure**](docs/PROJECT-STRUCTURE.md) - Repository organization
- [**Deployment Workflow**](docs/DEPLOYMENT.md) - Build & deploy guide
- [**Phase 3 Summary**](docs/PHASE3-COMPLETE.md) - Latest milestone

### Design Documents
- [**Master Roadmap**](plans/roadmaps/soul-smp-master-roadmap.md) - Overall project plan
- [**Wrath Soul Roadmap**](plans/roadmaps/wrath-base.md) - First soul implementation
- [**System Architecture**](plans/system%20architecture.md) - Core systems overview

---

## 🎮 Features

### Phase 0-2 ✅ Complete
- Resource management (Heat, Harmony, Wealth, etc.)
- Combat event pipeline with throttling
- Effect system (Burn, Bleed, Stun)
- Player profile persistence (YAML/JSON)
- Telemetry & metrics tracking
- Migration system for profile upgrades

### Phase 3 ✅ Complete
- **Global command suite** (`/souls list|info|select|respec|admin`)
- **Admin tools** (grant souls, modify resources, reset cooldowns)
- **Debug utilities** (event streaming, metrics export)
- **UI feedback** (ActionBar/BossBar resource & cooldown displays)
- **Message localization** (50+ keys in `messages.properties`)

### Phase 4 🚧 In Progress
- Base soul implementations (13 souls)
- Passive abilities
- Tactical abilities
- Movement abilities
- Ultimate abilities

---

## 🛠️ Development Workflow

### Rapid Iteration
```bash
# 1. Make code changes
vim plugin/src/main/java/dev/soulsmp/...

# 2. Deploy & test
cd scripts && ./deploy_plugin.sh

# 3. Test in-game
# Server starts automatically with new plugin
```

### Configuration Testing
```bash
# Skip rebuild for faster config changes
./deploy_plugin.sh --skip-build
```

### Watch Logs
```bash
tail -f server/logs/latest.log
```

---

## 🎯 Current Status

**Build:** ✅ Passing  
**Tests:** ✅ N/A (integration testing in-game)  
**Latest Version:** `0.1.0`  
**Target Minecraft:** `1.21.6`  
**Phase:** `3 Complete, Phase 4 Starting`

**Recent Updates:**
- ✅ Phase 3 command/UI layer complete
- ✅ Automated deployment script
- ✅ ActionBar/BossBar resource displays
- ✅ Full admin tooling

---

## 📋 Roadmap

### Completed Phases
- [x] **Phase 0** - Project scaffolding & configuration
- [x] **Phase 1** - Core resource & profile systems
- [x] **Phase 2** - Combat, effects, telemetry
- [x] **Phase 3** - Commands & UI feedback

### Next Steps
- [ ] **Phase 4** - Base soul implementations (13 souls)
- [ ] **Phase 5** - Soul paths (2 per soul)
- [ ] **Phase 6** - Contracts & mythics
- [ ] **Phase 7** - Items & resonances

See [**Master Roadmap**](plans/roadmaps/soul-smp-master-roadmap.md) for details.

---

## 🤝 Contributing

1. **Branch** from `main`
2. **Build** with `./gradlew build`
3. **Test** using `deploy_plugin.sh`
4. **Document** changes in relevant `/docs` files
5. **Pull Request** with roadmap task references

**Code Style:**
- Java: Google Java Style
- YAML: 2-space indentation (no tabs!)
- Markdown: Standard conventions

---

## 📜 License

This project is currently **proprietary**. Distribution or reuse requires explicit permission from the maintainers.

---

## 🔗 Quick Links

| Resource | Link |
|----------|------|
| Commands | [docs/commands.md](docs/commands.md) |
| Config Reference | [docs/configuration.md](docs/configuration.md) |
| Deployment Guide | [docs/DEPLOYMENT.md](docs/DEPLOYMENT.md) |
| Project Structure | [docs/PROJECT-STRUCTURE.md](docs/PROJECT-STRUCTURE.md) |
| Master Roadmap | [plans/roadmaps/soul-smp-master-roadmap.md](plans/roadmaps/soul-smp-master-roadmap.md) |

---

**Built with ❤️ for the Soul SMP community**
