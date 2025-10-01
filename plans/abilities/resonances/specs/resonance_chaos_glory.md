---
id: chaos+glory.legendary_paradox
souls: [chaos, glory]
name: Legendary Paradox
tier: U
category: HYB
trigger: ULT(riftstorm + immortal_banner)
version: 1.0.0
design_status: draft
---
 ### Overview
Immortal Banner inside Riftstorm causes banner aura to randomize secondary buff each tick.
### Trigger Conditions
Both ultimates active; banner origin within rift radius at activation.
### Scaling & Math
BuffAmp = 1 + 0.08 * participants (cap 1.24). Random effect weight shifts each roll.
### Effects
Each 2s: apply one extra minor buff (speed|resist|regen|strength I) chosen with chaos bias.
### Cooldowns & Limits
Fusion lockout 180s after end.
### Conflicts
Legendary Banner overrides this if active (higher priority fusion).
### Anti-Abuse
Buff duplication filtered (no stacking same tier >2).
### Implementation Notes
Fusion manager merges aura contexts.
