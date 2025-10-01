---
id: greed+serenity.charity_exchange
souls: [greed, serenity]
name: Charity Exchange
tier: T
category: UTIL
trigger: STATUS(overheal conversion)
version: 0.1.0
design_status: draft
---
### Overview
Overheal in Sanctuary converts into coin charge stacks fueling later healing bursts.
### Trigger Conditions
Ally receives heal above max HP inside Sanctuary.
### Scaling & Math
CoinCharge = floor(OverhealHP/2) (cap 8 per event).
### Effects
At 20 charge release burst heal (radius 8).
### Cooldowns & Limits
Internal 24s per player; burst consumes all charge.
### Conflicts
Resplendent Well converts burst to barrier.
### Anti-Abuse
Ignore overheal from environmental regen loops.
### Implementation Notes
Heal event inspector with overheal calc.
