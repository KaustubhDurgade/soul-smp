---
id: greed+wrath.blood_tithe
souls: [greed, wrath]
name: Blood Tithe
tier: F
category: OFF
trigger: STATUS(lifesteal event)
version: 0.1.0
design_status: draft
---
### Overview
Lifesteal generates coin charges; fire damage increases coin yield conversion.
### Trigger Conditions
Wrath lifesteal or Greed drain ability heals attacker.
### Scaling & Math
ChargeGain = 1 per 4 HP healed (cap 5 per event). FireBonus = +1 charge if burning target.
### Effects
At 15 charge: unleash Blood Dividend (AoE damage + small heal). Resets.
### Cooldowns & Limits
Internal 32s after dividend.
### Conflicts
Charitable Burn active splits heal before charge calc.
### Anti-Abuse
Ignore healing from passive regen.
### Implementation Notes
Heal attribution listener.
