---
id: hope+order.temporal_radiance
souls: [hope, order]
name: Temporal Radiance
tier: T
category: UTIL
trigger: SEQ(rewind in beacon)
version: 0.1.0
design_status: draft
---
### Overview
Rewind cast inside Beacon replays partial recent healing to allies.
### Trigger Conditions
Rewind resolves while Beacon active.
### Scaling & Math
ReplayFraction = 0.18 + 0.04*(participants-1) (cap 0.26).
### Effects
After 2s delay heal all allies previously healed in last 4s.
### Cooldowns & Limits
Internal 24s.
### Conflicts
Luminous Theory merges to single replay (highest fraction).
### Anti-Abuse
No replay if target full HP.
### Implementation Notes
Heal log ring buffer.
