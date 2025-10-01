---
id: greed+order.contract_of_balance
souls: [greed, order]
name: Contract of Balance
tier: T
category: UTIL
trigger: SEQ(steal then chain)
version: 0.1.0
design_status: draft
---
### Overview
Successful item steal followed by chain hit returns item with an interest buff.
### Trigger Conditions
Drain Touch / Midas effect steals item; target chained within 2s.
### Scaling & Math
InterestBuff = +0.05 dmg taken (enemy) 6s.
### Effects
Item ghost returns; mark enemy with Balance seal.
### Cooldowns & Limits
Internal 26s per target.
### Conflicts
Edict suppresses steal portion if mid-cast.
### Anti-Abuse
No effect on unique artifacts.
### Implementation Notes
Steal event buffer + chain resolution join.
