---
id: order+serenity.still_time
souls: [order, serenity]
name: Still Time
tier: F
category: CTRL
trigger: AREA(sanctuary + edict/time ability)
version: 0.1.0
design_status: draft
---
### Overview
Time-slow zone reduces movement and projectile speed.
### Trigger Conditions
Sanctuary overlapping Edict / Stasis / Chrono ability.
### Scaling & Math
Slow = 0.25 (0.3 if participants>1). ProjectileSpeedMult=0.7.
### Effects
Applies slow + projectile dampening inside merged zone.
### Cooldowns & Limits
Internal 30s after zone ends.
### Conflicts
Absolute Judgment removes projectile dampening.
### Anti-Abuse
No stacking with Temporal Fracture bubble.
### Implementation Notes
Zone fusion context.
