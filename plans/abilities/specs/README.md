# Soul SMP Design Spec Structure

This `spec/` folder splits the formerly monolithic `ability-mechanics.md` into modular documents for clarity.

## File Map
- global-systems.md – Core rules: cooldown tiers, formulas, status effects, interaction rules, progression, action counters, resources.
- souls/<soul>/<path>/spec.md – Per soul & path ability sheets (base, plus two subclasses). Each file holds P / T / M / U / Weapon / Final Stand.
- data-schema.md – Forthcoming: canonical JSON/YAML structures.
- (Planned) final-stands-terrain.md – Terrain alteration governance & persistence schema.
- (Planned) movement-archetypes.md – Catalog & implementation notes for non-standard movement (corridors, lattices, pivots, fractures, tethers, anchors).
- (Planned) progression-trials.md – Trial templates & scaling parameters.
- (Planned) resource-systems.md – Central registry of generation/spend hooks & event contracts.

## Conventions
- Each ability row preserves: Code, Name, Type, Cooldown, Mechanics, and implicit tags referenced in central balance tags table.
- Resource interactions noted inline with +Gain / Spend phrases.
- Final Stand entries include terrain persistence & revival condition if any.

## Migration Note
The original `ability-mechanics.md` remains for historical diff; authoritative editing now happens in per-path spec files plus `global-systems.md`. Periodic back-sync optional.
