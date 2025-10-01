---
id: greed+hope.charitable_burn
souls: [greed, hope]
name: Charitable Burn
tier: F
category: HYB
trigger: STATUS(ally burn heal conversion)
version: 0.1.0
design_status: draft
---
### Overview
Portion of burn damage dealt nearby is siphoned into ally healing pulses.
### Trigger Conditions
Burn tick from Greed or allied Wrath within 10 blocks of Hope Beacon / Aurora Dome.
### Scaling & Math
HealFraction = 0.18 + 0.04*(participants-1) (cap 0.26).
### Effects
Heals lowest HP ally in 12 blocks; excess overheal to Charity stacks.
### Cooldowns & Limits
Internal 28s per source.
### Conflicts
Purifying Flame present replaces conversion with cleanse pulse.
### Anti-Abuse
Limit to 5 pulses per 20s.
### Implementation Notes
Burn tick hook -> heal dispatcher.
