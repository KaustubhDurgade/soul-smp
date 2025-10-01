---
id: mythic.wrath+hope+serenity.phoenix_convergence
souls: [wrath, hope, serenity]
name: Phoenix Convergence
tier: X
category: DEF
trigger: ULT(Meteor/Beacon/Sanctuary overlap)
version: 0.1.0
design_status: draft
---
### Overview
Massive rebirth pulse revives one downed ally at low HP and grants temporary burn→heal conversion.
### Trigger Conditions
All three ultimates active with 3-way overlap region >10 blocks.
### Scaling & Math
ReviveHP = 30% max; Conversion = 0.3 fire->healing 20s.
### Effects
Revive nearest eligible ally; apply Phoenix Shield.
### Cooldowns & Limits
Global lockout 12m.
### Conflicts
Purifying Flame increases conversion +0.05.
### Anti-Abuse
Cannot chain revive within lock.
### Implementation Notes
Revive queue manager.
