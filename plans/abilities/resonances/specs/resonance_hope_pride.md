---
id: hope+pride.inspiring_dominion
souls: [hope, pride]
name: Inspiring Dominion
tier: C
category: HYB
trigger: PROX(light aura renown)
version: 0.1.0
design_status: draft
---
### Overview
Hope’s light increases renown generation of Pride and trusted allies.
### Trigger Conditions
Pride within 10 blocks of Hope with active Beacon/aurora.
### Scaling & Math
RenownGainMult = 1.15 (+0.05 if participants>1).
### Effects
Applies aura metadata to renown events.
### Cooldowns & Limits
Tick 10s.
### Conflicts
Glory Thesis active: choose higher multiplier only.
### Anti-Abuse
No effect if no combat event in last 30s.
### Implementation Notes
Proximity + activity check.
