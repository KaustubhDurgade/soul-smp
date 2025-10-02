# Soul SMP Planning Index

This directory houses the design material that drives development for the Soul SMP plugin. Use this index to navigate the most important references.

## Roadmaps

- [Master development roadmap](roadmaps/soul-smp-master-roadmap.md)
- [Wrath base implementation roadmap](roadmaps/wrath-base.md)

## Registries

- [`registry/`](registry/) – Canonical data sources for souls, abilities, contracts, events, items, mythics, and resonances. Each YAML file contains the authoritative identifiers and status information that power in-game systems.

## Specifications & Notes

- [`abilities/`](abilities/) – Detailed ability breakdowns and combos.
- [`ability-overveiw.md`](ability-overveiw.md) – High-level glossary of ability terminology used across souls.
- [`contracts/`](contracts/) & [`contracts.md`](contracts.md) – Contract lifecycle planning and quest definitions.
- [`events/`](events/) & [`events.md`](events.md) – Event schedules, triggers, and design notes.
- [`items/`](items/) & [`items.md`](items.md) – Custom item specifications and integration notes.
- [`mythics/`](mythics/) & [`mythics.md`](mythics.md) – Mythic item and encounter design.
- [`system architecture.md`](system%20architecture.md) – Cross-cutting architecture decisions, including resource engines, cooldowns, and integration touchpoints.

## Workflows & Logs

- [`plans/roadmaps/`](roadmaps/) – Soul- or feature-specific execution guides.
- [`plans/registry/`](registry/) – Machine-readable registries synced with automation scripts.

## Related Scripts

Utility scripts that operate on these plans live under [`../scripts/`](../scripts/):

- `validate_registry.py` – Validates registry files against schema expectations.
- `convert_specs.py` – Converts spec documents into JSON/Markdown artifacts for in-game use.

> 💡 Keep this index updated as new planning documents land to preserve a single source of truth for design work.
