---
id: hope+serenity.resplendent_well
souls: [hope, serenity]
name: Resplendent Well
tier: F
category: DEF
trigger: AREA(sanctuary + beacon)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary + Beacon fusion boosts regen & barrier stacking cadence.
### Trigger Conditions
Volumes overlap >20%.
### Scaling & Math
RegenAmp = +30%; BarrierPulse = 2 HP / 5s.
### Effects
Barrier pulses convert partial overheal to shield (cap 8 HP).
### Cooldowns & Limits
Internal 30s after end.
### Conflicts
Legendary Banner adds +5% regen instead of separate aura.
### Anti-Abuse
Barrier cannot exceed 30% max HP.
### Implementation Notes
Overlap merge context.
