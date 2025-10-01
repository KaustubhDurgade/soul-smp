---
id: mythic.order+silence+despair.absolute_suppression
souls: [order, silence, despair]
name: Absolute Suppression
tier: X
category: CTRL
trigger: ULT(Edict + Silence + Plague Field)
version: 0.1.0
design_status: draft
---
### Overview
Full ability negation zone also inverts healing to minor decay for enemies.
### Trigger Conditions
Overlap of three ultimates >25% area for 2s.
### Scaling & Math
NegationDuration = 6s hard cap.
### Effects
Enemies: abilities locked, healing → wither conversion (low).
### Cooldowns & Limits
Global lockout 15m.
### Conflicts
Absolute Judgment subsumed (no separate lock) inside.
### Anti-Abuse
Boss immune flag reduces duration to 2s.
### Implementation Notes
Fusion zone sentinel.
