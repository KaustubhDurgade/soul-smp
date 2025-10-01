---
id: despair+silence.null_decay
souls: [despair, silence]
name: Null Decay
tier: T
category: CTRL
trigger: STATUS(rot tick in silence)
version: 0.1.0
design_status: draft
---
### Overview
Rot ticks inside Silence zone slightly extend silence duration (diminishing).
### Trigger Conditions
Rot damage event while target inside Silence Field.
### Scaling & Math
Extend = 0.25s (decays 50% each successive application within 8s).
### Effects
Append extension to silence timer if remaining < baseDuration+3s.
### Cooldowns & Limits
Internal 20s per target.
### Conflicts
Absolute Judgment suppresses extension.
### Anti-Abuse
Track extension stack count (max 4).
### Implementation Notes
Per-target extension ledger.
