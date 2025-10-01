---
id: contract.life_for_power
name: Life-for-Power
tier: 2
class: OFFENSE
scope: LOCAL
sacrifice:
  type: GROUP_HP
  min_each: 0.05
  max_each: 0.50
activation:
  mode: INSTANT
  cooldown_seconds: 170
effects:
  strength_amp_base: 0.15
  cdr_bonus: 0.10
  duration_seconds: 10
scaling:
  formula: "strength = base + (avg_sacrifice_frac*0.5)"
  clamp_strength_amp: 0.45
resonance_hooks:
  - condition: resonance:blood_shadow within:10
    modify:
      lifesteal_add: 0.10
      stealth_strike_bonus_add: 0.15
conflict_tags: [cooldown_reducer]
anti_abuse:
  min_players: 2
logging: EXTENDED
status: draft
---
## Summary
Group health sacrifice yields offensive stat burst + cooldown reduction.

## Mechanics
* Average sacrifice fraction drives strength amp.

## Scaling
Avg health paid (capped) contributes up to +30% beyond base.

## Resonance Hooks
Blood+Shadow adds lifesteal + stealth strike synergy.

## Visuals
Red-black expanding pulse, heartbeat aura effect.

## Anti-Abuse & Conflicts
Requires ≥2 participants; falls under cooldown_reducer domain.

## Edge Cases
* If a participant cancels, recalc average; abort if <2 remain.

## Implementation Notes
Snapshot participants at activation for fairness.
