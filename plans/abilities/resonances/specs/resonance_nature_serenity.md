---
id: nature+serenity.harmonic_grove
souls: [nature, serenity]
name: Harmonic Grove
tier: C
category: DEF
trigger: PROX(storm within sanctuary)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary stabilizes Nature’s Wrath mitigating incoming enemy slows.
### Trigger Conditions
Nature storm active in Sanctuary radius.
### Scaling & Math
SlowResist = 0.3 (+0.1 if participants>1).
### Effects
Allies inside gain slow resistance.
### Cooldowns & Limits
Tick 8s.
### Conflicts
Verdant Dawn root replaced by resistance if both active.
### Anti-Abuse
No effect on boss slows flagged unresistable.
### Implementation Notes
Storm presence + zone membership.
