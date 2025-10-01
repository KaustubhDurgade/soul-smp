---
id: glory+wrath.blazing_standard
souls: [glory, wrath]
name: Blazing Standard
tier: F
category: OFF
trigger: AREA(meteor in banner)
version: 0.1.0
design_status: draft
---
### Overview
Meteor impacts inside banner empower periodic fire ticks.
### Trigger Conditions
Meteorfall or Infernal Storm meteor lands within banner radius.
### Scaling & Math
FireAmp = 1 + 0.08*(participants-1) (cap 1.16); Duration 12s.
### Effects
Applies aura increasing fire DoT damage to allies inside.
### Cooldowns & Limits
Internal 28s.
### Conflicts
Fire Tornado active adds +2s duration instead of stacking.
### Anti-Abuse
Ignore meteors spawned by scripted events.
### Implementation Notes
Impact event + aura context.
