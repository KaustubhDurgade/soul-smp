---
id: pride+trickery.false_regalia
souls: [pride, trickery]
name: False Regalia
tier: F
category: CTRL
trigger: STATUS(illusion mimics banner)
version: 0.1.0
design_status: draft
---
### Overview
Illusion temporarily mimics Warbanner visuals misleading opponents.
### Trigger Conditions
Clone spawned within active Warbanner.
### Scaling & Math
MimicDuration = 6s (+2 if participants>1).
### Effects
Clone projects faux banner aura (no real buff) causing enemy misreads.
### Cooldowns & Limits
Internal 28s.
### Conflicts
Heroic Misdirection: choose one aura overlay (priority clone flag).
### Anti-Abuse
Limit 1 faux banner at a time.
### Implementation Notes
Clone spawn hook adding visual layer.
