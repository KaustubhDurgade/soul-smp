---
id: curiosity+pride.glory_thesis
souls: [curiosity, pride]
name: Glory Thesis
tier: C
category: UTIL
trigger: PROX(<=12)
version: 1.0.0
design_status: draft
---
### Overview
Captures ally buff stats; small renown scaling.
### Trigger Conditions
Within 12 blocks while Pride has >=2 trusted allies present.
### Scaling & Math
RenownGain = 1 + 0.2*(participants-1) per 30s window.
### Effects
Generates data log; Pride gets tiny renown pulse.
### Cooldowns & Limits
Tick every 30s.
### Conflicts
Legendary Banner overrides (suspends pulses).
### Anti-Abuse
Ignores if no combat flagged in last 60s.
### Implementation Notes
Trusted ally count poll.
