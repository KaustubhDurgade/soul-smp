---
id: silence+trickery.quiet_mirage
souls: [silence, trickery]
name: Quiet Mirage
tier: C
category: CTRL
trigger: PROX(clones in silence)
version: 0.1.0
design_status: draft
---
### Overview
Clones within Silence persist slightly longer and produce no sounds.
### Trigger Conditions
Clone inside Silence Field.
### Scaling & Math
DurationMult = 1.15.
### Effects
Extends clone timer; removes sound events.
### Cooldowns & Limits
Tick 8s.
### Conflicts
Regulated Illusion sets absolute duration (higher rule precedence).
### Anti-Abuse
Limit +3s extension.
### Implementation Notes
Clone tick adjust.
