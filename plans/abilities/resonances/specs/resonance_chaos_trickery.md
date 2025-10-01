---
id: chaos+trickery.mirror_madness
souls: [chaos, trickery]
name: Mirror Madness
tier: F
category: OFF
trigger: AREA(clones inside chaos aura)
version: 1.0.0
design_status: stable
---
### Overview
Illusion clones inherit random buffs then detonate after delay.
### Trigger Conditions
>=2 active Trickery clones within 10 blocks of Chaos soul.
### Scaling & Math
BuffCount = 1 + (participants>1 ? 1 : 0); DetonationDamage = 2.5 + 0.7*participants.
### Effects
Apply random minor buff to clone; on expiry explode (radius 3) fire/arcane mix.
### Cooldowns & Limits
Internal 22s (per clone group).
### Conflicts
Experimental Flux increases buff pool size by 1.
### Anti-Abuse
Max 4 empowered clones tracked; excess behave normal.
### Implementation Notes
Clone entity tag scanning + scheduled task.
