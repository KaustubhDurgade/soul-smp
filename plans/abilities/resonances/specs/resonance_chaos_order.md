---
id: chaos+order.entropic_chainbreak
souls: [chaos, order]
name: Entropic Chainbreak
tier: T
category: CTRL
trigger: SEQ(chain break within 1s of chaos proc)
version: 1.0.0
design_status: draft
---
### Overview
Breaking an Order root/chain while chaos ability fires causes displacement burst.
### Trigger Conditions
Iron Bind / Binding Chains removed (timeout or cleanse) within 1s of Unstable Burst/Wild Surge.
### Scaling & Math
BurstRadius = 3 + 0.4*participants; Displace = 2 + 0.3*participants blocks.
### Effects
Knockback radial & scramble (minor random yaw).
### Cooldowns & Limits
Internal 24s pair.
### Conflicts
Absolute Judgment active: displacement suppressed.
### Anti-Abuse
Ignore trivial (sub 0.5s) chain durations.
### Implementation Notes
Root removal event diffing previous state.
