---
id: despair+pride.dread_command
souls: [despair, pride]
name: Dread Command
tier: F
category: CTRL
trigger: AREA(banner in darkness)
version: 0.1.0
design_status: draft
---
### Overview
Warbanner zone pulses fear waves while in darkness field.
### Trigger Conditions
Active Warbanner center inside Dreadfall / Plague Field area.
### Scaling & Math
PulseInterval = 5s; FearDuration = 0.8s + 0.2*(participants-1) (cap 1.2s).
### Effects
Every interval emit fear attempt to enemies (cone outward).
### Cooldowns & Limits
Internal 32s after final pulse.
### Conflicts
Legendary Banner disables fear; converts to damage debuff.
### Anti-Abuse
Enemy immune for 15s after fear success.
### Implementation Notes
Zone overlap + periodic scheduler.
