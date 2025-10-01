---
id: hope+silence.quiet_light
souls: [hope, silence]
name: Quiet Light
tier: C
category: UTIL
trigger: PROX(light in silence)
version: 0.1.0
design_status: draft
---
### Overview
Beacon edge inside Silence slows enemy cast/channel speeds.
### Trigger Conditions
Enemy inside Silence & within 6 blocks of Beacon perimeter.
### Scaling & Math
CastSlow = 0.12 (0.16 if participants>1).
### Effects
Applies cast speed debuff tag.
### Cooldowns & Limits
Tick 8s.
### Conflicts
Absolute Judgment overrides with full lock.
### Anti-Abuse
Does not stack with Edict slow.
### Implementation Notes
Edge ring sampling.
