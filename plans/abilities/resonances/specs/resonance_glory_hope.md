---
id: glory+hope.shining_standard
souls: [glory, hope]
name: Shining Standard
tier: C
category: DEF
trigger: PROX(banner+beacon)
version: 0.1.0
design_status: draft
---
### Overview
Beacon near Banner grants protective resist aura to allies.
### Trigger Conditions
Centers within 14 blocks; both active.
### Scaling & Math
ResistValue = +0.06 ( +0.02 if participants>1 ).
### Effects
Applies temporary resistance I effect tag.
### Cooldowns & Limits
Passive tick 8s.
### Conflicts
Legendary Banner upgrades ResistValue cap.
### Anti-Abuse
No stacking multiple banners.
### Implementation Notes
Distance check each tick.
