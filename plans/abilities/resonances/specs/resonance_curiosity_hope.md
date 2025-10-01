---
id: curiosity+hope.luminous_theory
souls: [curiosity, hope]
name: Luminous Theory
tier: T
category: UTIL
trigger: SEQ(beam/beacon)
version: 1.0.0
design_status: draft
---
### Overview
Radiant Beam or Beacon logs recent heals, replaying a fraction later.
### Trigger Conditions
Hope heal ability cast while Curiosity within 10 blocks.
### Scaling & Math
ReplayHeal = 0.15 + 0.05*(participants-1).
### Effects
After 3s, replay ReplayHeal * original heal to same targets.
### Cooldowns & Limits
Internal 22s.
### Conflicts
Prismatic Refraction modifies replay to distribute evenly.
### Anti-Abuse
No replay if original target at full HP.
### Implementation Notes
Heal event buffer retention (3s delay queue).
