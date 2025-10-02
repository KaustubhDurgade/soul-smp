# Conversion Status (Specs → YAML Registry)

Legend: ✅ converted, ⏳ pending, ⚠ needs review.

## Contracts
Generated YAML and tooltip artifacts present under `plans/registry/data` and `plans/registry/tooltips`.

## Events
_Pending conversion._

## Mythics
_Pending conversion._

## Resonances
Not yet in automated scope (planned next phase).

## Tasks
- [x] Run `scripts/convert_specs.py` to generate YAML from markdown specs _(2025-10-01 · automated)_
- [x] Inspect generated YAML for unintended omissions _(2025-10-01 · automated)_
- [x] Commit generated files _(pending review)_
- [x] Run `scripts/validate_registry.py` and address any ERROR entries _(2025-10-01 · see `plans/logs/registry-validation.md`)_
- [ ] Expand converter to resonances & (optional) base soul kits if desired

_This file will be updated by a future automation step to list each id with status._
