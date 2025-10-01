---
id: silence+wrath.smother
souls: [silence, wrath]
name: Smother
tier: T
category: CTRL
trigger: AREA(null field + fire)
version: 0.1.0
design_status: draft
---
### Overview
Silence field overlapping burning zone suppresses enemy regeneration.
### Trigger Conditions
Enemy under burn inside Silence Field.
### Scaling & Math
RegenSuppress = full; Duration 4s.
### Effects
Applies regen halt marker.
### Cooldowns & Limits
Internal 18s.
### Conflicts
Purifying Flame chooses conversion over suppression (if both would apply).
### Anti-Abuse
No effect on potion regen > level II.
### Implementation Notes
Status merge logic.
