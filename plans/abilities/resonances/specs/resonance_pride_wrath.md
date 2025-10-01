---
id: pride+wrath.ember_command
souls: [pride, wrath]
name: Ember Command
tier: F
category: OFF
trigger: AREA(banner + meteor)
version: 0.1.0
design_status: draft
---
### Overview
Meteor ticks inside Warbanner increase ally fire damage briefly.
### Trigger Conditions
Meteorfall within Warbanner radius.
### Scaling & Math
FireAmp= 0.1 + 0.02*(participants-1) (cap 0.14) 10s.
### Effects
Applies fire amplification buff list to allies.
### Cooldowns & Limits
Internal 30s.
### Conflicts
Blazing Standard merges (use higher amp).
### Anti-Abuse
Single meteor counted per 3s.
### Implementation Notes
Impact + aura.
