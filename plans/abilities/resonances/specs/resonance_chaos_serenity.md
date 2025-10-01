---
id: chaos+serenity.stilled_chaos
souls: [chaos, serenity]
name: Stilled Chaos
tier: T
category: HYB
trigger: AREA(sanctuary + chaos aura)
version: 1.0.0
design_status: draft
---
### Overview
Sanctuary moderates chaos randomness (dampens negatives, boosts positives modestly).
### Trigger Conditions
Chaos soul stands within active Sanctuary volume.
### Scaling & Math
NegativeWeightMult = 0.7; PositiveWeightMult = 1.2 (participants>1 -> 1.25).
### Effects
Chaos roll context override for duration; Serenity gains +1 Harmony per beneficial roll.
### Cooldowns & Limits
Internal 12s per chaos soul.
### Conflicts
Experimental Flux active: apply only larger positive multiplier.
### Anti-Abuse
Harmony gain capped 5 per activation.
### Implementation Notes
Zone presence tracker; modify RNG tables.
