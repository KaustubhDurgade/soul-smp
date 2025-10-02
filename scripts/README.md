# Scripts Overview

This folder contains utility scripts that support the Soul SMP planning pipeline. Python scripts assume the repository virtual environment (`.venv`) is active and that dependencies from `scripts/requirements.txt` are installed.

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
