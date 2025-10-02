# Project Structure

This document provides an overview of the Soul SMP repository organization.

```
soul-smp/
├── docs/                      # 📚 Documentation
│   ├── commands.md            # Player command reference
│   ├── configuration.md       # Config.yml reference
│   ├── catalog.md            # Soul catalog schema
│   ├── DEPLOYMENT.md         # Deployment workflow guide
│   ├── ui-examples.md        # UI display examples
│   ├── phase3-completion.md  # Phase 3 technical summary
│   └── PHASE3-COMPLETE.md    # Phase 3 executive summary
│
├── plans/                     # 📋 Design Documents & Registry
│   ├── abilities/            # Ability specifications
│   ├── contracts/            # Contract definitions
│   ├── events/               # Event specifications
│   ├── items/                # Item specifications
│   ├── mythics/              # Mythic creature specs
│   ├── registry/             # Generated registry data
│   │   ├── data/            # YAML registry files
│   │   ├── schemas/         # JSON schemas
│   │   └── tooltips/        # Generated tooltips
│   ├── roadmaps/            # Project roadmaps
│   │   ├── soul-smp-master-roadmap.md
│   │   └── wrath-base.md
│   └── *.md                 # Core design documents
│
├── plugin/                    # 🎮 Minecraft Plugin (Java)
│   ├── src/main/java/        # Java source code
│   │   └── dev/soulsmp/
│   │       ├── core/         # Core systems
│   │       │   ├── bootstrap/
│   │       │   ├── combat/
│   │       │   ├── command/
│   │       │   ├── config/
│   │       │   ├── cooldown/
│   │       │   ├── debug/
│   │       │   ├── effect/
│   │       │   ├── listener/
│   │       │   ├── message/
│   │       │   ├── metrics/
│   │       │   ├── player/
│   │       │   ├── resource/
│   │       │   ├── soul/
│   │       │   ├── task/
│   │       │   ├── telemetry/
│   │       │   └── ui/
│   │       ├── wrath/        # Wrath soul implementation
│   │       └── SoulSMPPlugin.java
│   ├── src/main/resources/   # Plugin resources
│   │   ├── plugin.yml        # Plugin descriptor
│   │   ├── config.yml        # Default configuration
│   │   └── messages.properties
│   ├── build.gradle.kts      # Gradle build config
│   └── build/libs/           # Compiled JARs (gitignored)
│
├── scripts/                   # 🛠️ Build & Deployment Tools
│   ├── build_plugin.sh       # Build plugin JAR
│   ├── deploy_plugin.sh      # Automated deployment
│   ├── convert_specs.py      # Spec → Registry converter
│   ├── validate_registry.py  # Registry validator
│   └── README.md            # Script documentation
│
├── server/                    # 🖥️ Test Server (mostly gitignored)
│   ├── paper-1.21.6-48.jar  # Paper server (kept in git)
│   ├── start.sh             # Server startup script
│   ├── README.md            # Server setup guide
│   ├── plugins/             # Deployed plugins (gitignored)
│   ├── world*/              # World data (gitignored)
│   └── logs/                # Server logs (gitignored)
│
├── old-gui-mod/              # 📦 Reference: Old Fabric Mod
│   └── REVERSE_ENGINEERING_ANALYSIS.md
│
├── .venv/                    # 🐍 Python virtual environment (gitignored)
├── .gitignore               # Git ignore rules
├── .editorconfig            # Editor configuration
└── README.md                # Project overview
```

## Directory Purpose

### `/docs` - Documentation
End-user and developer documentation:
- Configuration guides
- Command references
- UI/UX examples
- Development milestones

### `/plans` - Design & Planning
Game design documents, roadmaps, and specifications:
- **Not code**, but design intent
- Converted to registry data by scripts
- Source of truth for balance/mechanics

### `/plugin` - Main Codebase
The Minecraft plugin implementation:
- Standard Maven/Gradle Java project
- Core systems (combat, resources, cooldowns, etc.)
- Soul implementations (Wrath, Serenity, etc.)
- Builds to `plugin/build/libs/soul-smp-0.1.0.jar`

### `/scripts` - Automation
Build and deployment automation:
- `deploy_plugin.sh` - One-command deploy workflow
- `convert_specs.py` - Generate registry from specs
- `validate_registry.py` - Ensure registry integrity

### `/server` - Test Environment
Local Paper server for testing:
- **Mostly gitignored** (runtime data)
- Only infrastructure files kept (jar, start.sh, README)
- Plugins deployed here by `deploy_plugin.sh`

### `/old-gui-mod` - Reference Material
Archive of previous Fabric mod for reference:
- Used to inform future client mod design
- Not actively developed

## Navigation Tips

### Working on Plugin Code
```bash
cd plugin/src/main/java/dev/soulsmp/
```

### Viewing Documentation
```bash
cd docs/
# Start with README.md, then explore specific topics
```

### Editing Game Design
```bash
cd plans/
# Edit specs, run convert_specs.py to generate registry
```

### Building & Deploying
```bash
cd scripts/
./deploy_plugin.sh
```

### Testing In-Game
```bash
cd server/
./start.sh
# Or use deploy_plugin.sh which does this automatically
```

## File Naming Conventions

### Documentation (Markdown)
- `lowercase-with-dashes.md` - User-facing docs
- `UPPERCASE.md` - Important milestone docs (e.g., PHASE3-COMPLETE.md)
- `README.md` - Directory overview (standard)

### Java Code
- `PascalCase.java` - Class names
- `camelCase` - Method/variable names
- Package structure mirrors feature organization

### Configuration (YAML)
- `config.yml` - Main plugin config
- `plugin.yml` - Bukkit plugin descriptor
- `messages.properties` - Localization strings

### Scripts
- `snake_case.py` - Python scripts
- `kebab-case.sh` - Bash scripts

## Quick Links

| Task | Path |
|------|------|
| Add a command | `plugin/src/main/java/dev/soulsmp/core/command/` |
| Add a soul | `plugin/src/main/java/dev/soulsmp/<soul-name>/` |
| Edit config schema | `plugin/src/main/resources/config.yml` |
| Add message | `plugin/src/main/resources/messages.properties` |
| View roadmap | `plans/roadmaps/soul-smp-master-roadmap.md` |
| Deploy changes | `scripts/deploy_plugin.sh` |
| Read API docs | `docs/` |

## Gitignore Strategy

**What's tracked:**
- ✅ Source code (`plugin/src/`)
- ✅ Documentation (`docs/`, `plans/`)
- ✅ Scripts (`scripts/`)
- ✅ Server infrastructure (jar, start.sh)
- ✅ Configuration files

**What's ignored:**
- ❌ Build outputs (`build/`, `*.jar` in plugin)
- ❌ Server runtime data (worlds, logs, cache)
- ❌ IDE files (`.idea/`, `.vscode/`)
- ❌ Python cache (`__pycache__/`, `.venv/`)
- ❌ OS files (`.DS_Store`)

This keeps the repository focused on source material while allowing developers to run servers locally.

---

**For new contributors:** Start with the main `README.md`, then explore this document to understand the layout!
