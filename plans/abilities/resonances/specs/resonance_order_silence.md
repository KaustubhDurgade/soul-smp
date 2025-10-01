---
id: order+silence.absolute_judgment
souls: [order, silence]
name: Absolute Judgment
tier: F
category: CTRL
trigger: AREA(edict + silence)
version: 0.1.0
design_status: draft
---
### Overview
Edict overlapping Silence locks hostile ability usage entirely for brief window.
### Trigger Conditions
Zones overlap >=25%.
### Scaling & Math
LockDuration = 2.5s + 0.5*(participants-1) (cap 3.5).
### Effects
Applies ability lock status; purge minor buffs.
### Cooldowns & Limits
Internal 40s.
### Conflicts
Legendary Banner reduces lock by 0.5s (priority mechanic fairness).
### Anti-Abuse
Excludes immune boss flags.
### Implementation Notes
Overlap event -> lock broadcast.
