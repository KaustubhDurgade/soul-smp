---
id: mythic.chaos+curiosity+trickery.fractured_insight
souls: [chaos, curiosity, trickery]
name: Fractured Insight
tier: X
category: HYB
trigger: ULT(Riftstorm + Experiment + Grand Illusion)
version: 0.1.0
design_status: draft
---
### Overview
Creates research anomaly granting random tech boon; clones gather data fueling buffs.
### Trigger Conditions
All three abilities active simultaneously for 3s.
### Scaling & Math
BoonStrength = Random(major buff table) 60s.
### Effects
Awards one random major boon (speed/strength/regen/CDR combo).
### Cooldowns & Limits
Global lockout 10m.
### Conflicts
Experimental Flux influences boon weight.
### Anti-Abuse
One boon per player per hour.
### Implementation Notes
Anomaly entity state machine.
