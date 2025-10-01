---
id: pride+silence.smothering_pretense
souls: [pride, silence]
name: Smothering Pretense
tier: F
category: CTRL
trigger: AREA(banner + silence)
version: 0.1.0
design_status: draft
---
### Overview
Warbanner inside Silence suppresses enemy active buffs.
### Trigger Conditions
Warbanner zone overlapping Silence Field.
### Scaling & Math
BuffSuppression = remove minor buffs instantly; major buff durations tick 2x.
### Effects
Applies suppression metadata.
### Cooldowns & Limits
Internal 30s.
### Conflicts
Absolute Judgment overrides effect.
### Anti-Abuse
Whitelist essential progression buffs.
### Implementation Notes
Buff audit cycle.
