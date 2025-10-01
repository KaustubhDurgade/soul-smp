---
id: glory+serenity.resonant_banner
souls: [glory, serenity]
name: Resonant Banner
tier: C
category: DEF
trigger: PROX(sanctuary near banner)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary near banner amplifies regen and grants small barrier pulses.
### Trigger Conditions
Sanctuary radius intersects banner aura.
### Scaling & Math
RegenAmp = +20%; Barrier = 2 HP per 10s.
### Effects
Applies regen multiplier; periodic barrier tick.
### Cooldowns & Limits
Tick 8s.
### Conflicts
Resplendent Well overrides barrier magnitude.
### Anti-Abuse
Barrier pulses skip if player recently damaged <2s.
### Implementation Notes
Overlap detector + periodic task.
