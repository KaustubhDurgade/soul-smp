---
id: pride+serenity.tempered_flux
souls: [pride, serenity]
name: Tempered Flux
tier: T
category: DEF
trigger: AREA(sanctuary + pride burst)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary moderates Pride burst damage converting portion to barriers.
### Trigger Conditions
Pride damage buff ability active inside Sanctuary.
### Scaling & Math
Conversion = 0.25 (0.3 if participants>1) of outgoing burst -> barriers.
### Effects
Creates barrier pulses per 3s.
### Cooldowns & Limits
Internal 24s.
### Conflicts
Resplendent Well barrier priority ordering.
### Anti-Abuse
Barrier cap 25% max HP.
### Implementation Notes
Damage event wrapper.
