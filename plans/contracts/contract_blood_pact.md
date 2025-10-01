---
id: contract.blood_pact
name: Blood Pact
tier: 1
class: SUPPORT
scope: LOCAL
sacrifice:
  type: HEALTH
  min_frac: 0.2
  max_frac: 0.5
activation:
  mode: INSTANT
  cast_time_ticks: 10
  cooldown_seconds: 90
  internal_lockout_tag: life_exchange_minor
effects:
  duration_seconds: 10
  aura_radius: 7
  lifesteal_percent_base: 0.10
  lifesteal_percent_per_extra_blood_soul: 0.04
scaling:
  formula: "L = base + (S*0.15) + (blood_souls*per)"
  clamp_lifesteal_max: 0.40
resonance_hooks:
  - condition: resonance:chaos_blood within:10
    modify:
      lifesteal_percent_add: 0.05
      radius_mult: 1.15
conflict_tags: [sustain_leech]
anti_abuse:
  diminishing_tag: sustain_leech
  diminishing_table: [1.0,0.7,0.45]
logging: BASIC
status: draft
---
## Summary
Sacrifice health to project a temporary lifesteal aura that scales with sacrifice fraction and nearby Blood souls.

## Mechanics
* On commit: deduct chosen HP (respect min HP floor 4)
* Apply aura ticking every 1s healing allies (and self) for computed lifesteal portion of damage dealt.

## Scaling
Let S = normalized sacrifice fraction.
Effective lifesteal = base + S*0.15 + (#extraBloodSouls * 0.04) (clamped ≤ 40%).

## Resonance Hooks
Chaos+Blood active nearby → +5% lifesteal additive & +15% radius multiplier.

## Visuals
Small red rune sigil, pulsing rings every tick, particle density tied to active allies benefitting.

## Anti-Abuse & Conflicts
Lifesteal domain uses strongest aura; others enter diminishing sequence.

## Edge Cases
* If player would drop below 4 HP, reduce sacrifice to allowable amount.
* If no allies present for 3 consecutive ticks, particle density halves.

## Implementation Notes
Aura registration keyed by contract instance; attach diminishing metadata for conflict manager.
