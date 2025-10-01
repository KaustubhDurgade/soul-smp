---
id: nature+wrath.scorched_wilds
souls: [nature, wrath]
name: Scorched Wilds
tier: T
category: OFF
trigger: SEQ(thorn field + meteor impact)
version: 0.1.0
design_status: draft
---
### Overview
Meteor impacting thorn field ignites thorns causing reactive burn bursts.
### Trigger Conditions
Meteor lands inside thorn / Garden of Pain zone.
### Scaling & Math
BurstDamage = 2 + 0.5*(participants-1); Bursts=3.
### Effects
Sequential burn bursts ignite enemies.
### Cooldowns & Limits
Internal 20s.
### Conflicts
Fire Tornado adds +1 burst instead of new instance.
### Anti-Abuse
Ignore natural lava meteors.
### Implementation Notes
Impact event + zone query.
