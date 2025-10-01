---
id: curiosity+silence.null_probe
souls: [curiosity, silence]
name: Null Probe
tier: T
category: UTIL
trigger: STATUS(analyze in silence field)
version: 1.0.0
design_status: draft
---
### Overview
Analyze in Silence Field suppresses enemy regen longer.
### Trigger Conditions
Analyze resolves on target inside Silence Field.
### Scaling & Math
RegenSuppression = 2.5s + 0.5*(participants-1).
### Effects
Target regen & passive healing halted for duration.
### Cooldowns & Limits
Internal 28s target scope.
### Conflicts
Absolute Judgment extends by +1s (cap total 5s).
### Anti-Abuse
No suppression if target already under stronger heal block.
### Implementation Notes
Analyze event + zone query.
