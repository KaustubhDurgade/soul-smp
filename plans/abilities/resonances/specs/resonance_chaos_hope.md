---
id: chaos+hope.prismatic_refraction
souls: [chaos, hope]
name: Prismatic Refraction
tier: F
category: UTIL
trigger: AREA(beacon inside rift)
version: 1.0.0
design_status: draft
---
### Overview
Healing from Beacon refracts into split beams with variable strength bounded by total.
### Trigger Conditions
Beacon center within active Riftstorm radius.
### Scaling & Math
TotalHeal = BaseHeal; BeamCount = 3 ( +1 if participants>2 ); EachBeam = TotalHeal * Random(0.18–0.42) normalized.
### Effects
Distributes heal packets to random allies in 18 blocks; applies 0.5s HoT extension.
### Cooldowns & Limits
Internal 25s. Once per beacon instance.
### Conflicts
Resplendent Well takes precedence; convert to flat +% instead of refraction.
### Anti-Abuse
Track ally receipt; no more than 2 beams per ally per activation.
### Implementation Notes
Beacon tick listener modifies heal distribution pipeline.
