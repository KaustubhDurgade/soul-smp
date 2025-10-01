---
id: despair+wrath.ashen_rot
souls: [despair, wrath]
name: Ashen Rot
tier: T
category: OFF
trigger: SEQ(fire hit within 1s of rot apply)
version: 0.1.0
design_status: draft
---
### Overview
Immediate fire follow-up upgrades rot tier temporarily granting bonus burn instance.
### Trigger Conditions
Wrath fire ability damages target within 1s of new Rot application.
### Scaling & Math
RotTierIncrease +1 for 5s; BonusBurn = 1 + 0.3*(participants-1).
### Effects
Applies rot upgrade marker; adds a one-time burn tick.
### Cooldowns & Limits
Internal 18s per target.
### Conflicts
Mandated Decay chooses highest new tier only.
### Anti-Abuse
Cannot stack multiple upgrade markers.
### Implementation Notes
Temporal join of damage + status events.
