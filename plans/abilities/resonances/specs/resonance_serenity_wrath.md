---
id: serenity+wrath.temper_flux
souls: [serenity, wrath]
name: Temper Flux
tier: T
category: DEF
trigger: AREA(sanctuary + flame)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary reflects small portion of burn damage back as reduced tick.
### Trigger Conditions
Burning enemy inside Sanctuary.
### Scaling & Math
Reflect = 0.15 (0.2 if participants>1) of next 3 fire ticks.
### Effects
Applies reflect tag; deals non-lethal reflected fire.
### Cooldowns & Limits
Internal 20s.
### Conflicts
Purifying Flame prioritizes conversion over reflect.
### Anti-Abuse
Non-lethal reflect cannot execute.
### Implementation Notes
Fire tick interception.
