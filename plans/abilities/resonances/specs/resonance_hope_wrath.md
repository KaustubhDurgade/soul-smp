---
id: hope+wrath.purifying_flame
souls: [hope, wrath]
name: Purifying Flame
tier: T
category: HYB
trigger: AREA(heal inside flame)
version: 0.1.0
design_status: draft
---
### Overview
Hope heal inside Wrath flame zone converts portion of burn to ally healing pulses.
### Trigger Conditions
Ally receives Hope heal while standing in burning AOE (Ignition/Meteor/Storm).
### Scaling & Math
Conversion = 0.2 + 0.05*(participants-1) (cap 0.3).
### Effects
Emits up to 3 heal pulses (radius 8).
### Cooldowns & Limits
Internal 22s.
### Conflicts
Charitable Burn chooses higher conversion only.
### Anti-Abuse
One active pulse set per heal event.
### Implementation Notes
Heal event + environment tag.
