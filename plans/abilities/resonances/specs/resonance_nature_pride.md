---
id: nature+pride.emerald_standard
souls: [nature, pride]
name: Emerald Standard
tier: F
category: UTIL
trigger: AREA(banner + thorns)
version: 0.1.0
design_status: draft
---
### Overview
Banner overlapping thorn field grants allies regen & projectile thorns.
### Trigger Conditions
Warbanner zone intersects Garden of Pain / Thorn field.
### Scaling & Math
Regen = +2 HP/10s; ReturnDmg = 0.25 ranged.
### Effects
Applies reactive thorn buff.
### Cooldowns & Limits
Internal 30s.
### Conflicts
Legendary Banner adds +1 HP/10s instead of stacking.
### Anti-Abuse
Projectile reflect capped per attacker.
### Implementation Notes
Zone overlap map.
