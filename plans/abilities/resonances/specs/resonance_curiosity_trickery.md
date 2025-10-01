---
id: curiosity+trickery.deceptive_research
souls: [curiosity, trickery]
name: Deceptive Research
tier: F
category: HYB
trigger: SEQ(illusion death + analyze)
version: 1.0.0
design_status: draft
---
### Overview
Illusion death events feed false data altering Analyze readouts.
### Trigger Conditions
Clone destroyed within 5s prior to Analyze cast.
### Scaling & Math
FalseDataDuration = 6 + 2*(participants-1) (cap 10).
### Effects
Analyze UI shows spoofed cooldowns for enemies (±20%).
### Cooldowns & Limits
Internal 30s.
### Conflicts
Experimental Flux prevents negative spoof on allies.
### Anti-Abuse
PvE mobs excluded.
### Implementation Notes
CloneDeathEvent queue w/time index.
