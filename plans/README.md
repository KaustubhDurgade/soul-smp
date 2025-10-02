# Soul SMP Planning Index

This directory houses the design material that drives development for the Soul SMP plugin. Use this index to navigate the most important references.

## Specifications & Notes

- [`abilities/`](abilities/) – Detailed ability breakdowns and combos.
- [`ability-overveiw.md`](ability-overveiw.md) – High-level glossary of ability terminology used across souls.
- [`contracts/`](contracts/) & [`contracts.md`](contracts.md) – Contract lifecycle planning and quest definitions.
- [`events/`](events/) & [`events.md`](events.md) – Event schedules, triggers, and design notes.
- [`items/`](items/) & [`items.md`](items.md) – Custom item specifications and integration notes.
- [`mythics/`](mythics/) & [`mythics.md`](mythics.md) – Mythic item and encounter design.
- [`system architecture.md`](system%20architecture.md) – Cross-cutting architecture decisions, including resource engines, cooldowns, and integration touchpoints.

## Related Scripts

Utility scripts that operate on these plans live under [`../scripts/`](../scripts/):

- `validate_registry.py` – Validates registry files against schema expectations.
- `convert_specs.py` – Converts spec documents into JSON/Markdown artifacts for in-game use.

> 💡 Keep this index updated as new planning documents land to preserve a single source of truth for design work.
