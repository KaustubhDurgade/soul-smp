# Wrath Base Tactical: Ignition

Code: T
Cooldown: 16s
Tags: [Damage, Resource]

## Mechanics
Enflame weapon for 6s.
- Each melee hit adds 2s Fire (Level 1) to target.
- Each hit applies +10% durability damage to enemy armor piece struck.
- Refreshes its own fire timer on multi-hit AOE weapons (if any future variants).

## Resource
+2 Heat per unique enemy damaged (ICD 0.5s per target). On expiration if you dealt ≥5 hits total, purge 10 Heat (vent animation) for FX only.

## Balance Levers
- Duration (6s)
- Armor durability multiplier (10%)
- Heat gain per unique target

## Anti-Abuse
- Fire application per target can’t stack beyond extending duration.
- Armor damage only once per swing instance even if multihit plugin mechanics trigger.
