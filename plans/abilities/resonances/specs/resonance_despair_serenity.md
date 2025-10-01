---
id: despair+serenity.muted_suffering
souls: [despair, serenity]
name: Muted Suffering
tier: C
category: DEF
trigger: PROX(within sanctuary)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary reduces incoming decay/bleed ticks for allies.
### Trigger Conditions
Ally under Rot/Bleed standing in Sanctuary radius.
### Scaling & Math
TickDmgMult = 0.85 (0.8 if participants>1).
### Effects
Applies protective modifier metadata to status calc.
### Cooldowns & Limits
Passive tick evaluation every 8s.
### Conflicts
Twilight Reassurance: take lower damage multiplier only.
### Anti-Abuse
No stacking multiple Sanctuary overlaps.
### Implementation Notes
Zone presence filter in status damage compute.
