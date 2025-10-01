---
id: curiosity+glory.legend_record
souls: [curiosity, glory]
name: Legend Record
tier: C
category: UTIL
trigger: PROX(<=14)
version: 1.0.0
design_status: draft
---
### Overview
Combat events near Glory logged granting shared XP trickle.
### Trigger Conditions
Both souls within 14 blocks; Glory in combat state.
### Scaling & Math
XPTrickle = 0.5 + 0.1*(participants-1) per 5s (cap 1.2).
### Effects
Every 5s if combat flagged: distribute XPTrickle to nearby allies (radius 10).
### Cooldowns & Limits
Passive tick; combat inactivity >8s pauses.
### Conflicts
Legendary Paradox active -> +10% XPTrickle.
### Anti-Abuse
Kill credit farming detection (duplicate mob types >10 quickly) halves gain.
### Implementation Notes
Combat state tracker + periodic task.
