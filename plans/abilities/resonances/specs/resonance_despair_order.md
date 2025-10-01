---
id: despair+order.mandated_decay
souls: [despair, order]
name: Mandated Decay
tier: T
category: CTRL
trigger: SEQ(chain/root then rot escalate)
version: 0.1.0
design_status: draft
---
### Overview
Applying a chain/root to a withered foe elevates rot tier briefly.
### Trigger Conditions
Binding Chains / Iron Bind hits target already Wither II+.
### Scaling & Math
RotTierIncrease = +1 (cap Tier 4) Duration 4s.
### Effects
Temporarily flags rot severity for damage over time scaling.
### Cooldowns & Limits
Internal 26s per target.
### Conflicts
Ashen Rot upgrade chooses highest resulting tier only.
### Anti-Abuse
Tier buff not extendable; refresh overwrites remaining time if >50% elapsed.
### Implementation Notes
Root application event with status query.
