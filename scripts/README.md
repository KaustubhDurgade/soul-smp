# Scripts Overview

This folder contains utility scripts that support the Soul SMP planning pipeline and plugin deployment.

## Development Scripts

### `deploy_plugin.sh` - **Automated Plugin Deployment**

**NEW:** Fully automated deployment script for rapid development iteration.

```bash
./deploy_plugin.sh [--skip-build] [--skip-restart]
```

**What it does:**
1. ✓ Kills any running Paper server instances
2. ✓ Builds the plugin using Gradle
3. ✓ Deletes old plugin from `server/plugins/`
4. ✓ Copies new plugin as `SoulSMP.jar`
5. ✓ Starts the Paper server automatically

**Flags:**
- `--skip-build` - Skip Gradle build (use existing JAR)
- `--skip-restart` - Deploy but don't start server

**Quick workflow:**
```bash
# Make code changes
cd scripts
./deploy_plugin.sh
# Server restarts automatically!
```

See detailed usage examples below.

---

## Pipeline Scripts

### `build_plugin.sh`

Builds the plugin JAR using Gradle:
```bash
./build_plugin.sh
```

Output: `plugin/build/libs/soul-smp-0.1.0.jar`

---

## `convert_specs.py`

Generates registry artifacts from Markdown specs with YAML front-matter.

- Scans `plans/contracts`, `plans/events`, and `plans/mythics` recursively.
- Emits registry YAML files to `plans/registry/data/`.
- Creates tooltip companions:
  - JSON summaries under `plans/registry/tooltips/json/`
  - Markdown summaries under `plans/registry/tooltips/md/`
- Injects default `notes: []` and preserves/normalizes key ordering for validation.

### Usage

```
/Users/kaustubhdurgde/plugins/soul-smp/.venv/bin/python scripts/convert_specs.py [--dry-run] [--force]
```

- `--dry-run` prints the files that would change without writing them.
- `--force` rewrites all artifacts even if they already exist.

### Tooling Notes

Values such as `2 (cap_add: 8)` in front-matter are automatically quoted, allowing designers to keep human-readable annotations without breaking YAML parsing.

## `validate_registry.py`

Runs structural checks over the generated registry data (IDs, conflict tags, balance heuristics). Recommended after any conversion run:

```
/Users/kaustubhdurgde/plugins/soul-smp/.venv/bin/python scripts/validate_registry.py
```

## Testing

Unit tests for the conversion workflow live in `scripts/tests/`. They can be executed via:

```
/Users/kaustubhdurgde/plugins/soul-smp/.venv/bin/python -m pytest scripts/tests
```

All tests rely solely on temporary directories and do not modify repository data.
