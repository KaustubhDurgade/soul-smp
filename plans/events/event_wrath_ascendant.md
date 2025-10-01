---
id: event.wrath_ascendant
name: Wrath Ascendant
category: SOUL
scope: REGIONAL
rarity: UNCOMMON
intensity_tier: 2
trigger:
  mode: THRESHOLD
  condition:
    blood_kill_count_last_10m: 
      min: 15
phases:
  telegraph_seconds: 12
  active_seconds: 120
  cooldown_seconds: 2700
mechanics:
  meteor_rate_seconds: 6
  rage_stack_bonus_damage: 0.04
  max_rage_stacks: 10
scaling:
  blood_souls_stack_bonus_per: 0.01 (cap_additional: 0.05)
resonance_hooks:
  - condition: resonance:blood_flame active_within:24
    modify:
      meteor_rate_seconds_mult: 0.85
      rage_stack_bonus_damage_add: 0.01
visual:
  sky_color_shift: crimson_haze
  meteor_style: blood_fire
rewards:
  token: wrath_fragment
anti_abuse:
  threshold_cooldown_extension: 600
logging: EXTENDED
status: draft
---
## Summary
Triggered by high recent Blood soul activity; rains rage-fueled meteors.

## Telegraph
Crimson haze forms overhead; ground tremor particles.

## Mechanics
Players gain rage stacks (damage bonus) while event active.

## Scaling
Blood souls add minor additional stack scaling.

## Resonance Hooks
Blood+Flame speeds meteor frequency.

## Visuals
Red meteors with flame tails; impact flare.

## Anti-Abuse
Threshold cooldown extension prevents chaining via farm.

## Edge Cases
* If kill counter dips below half threshold during telegraph, abort.

## Implementation Notes
Maintain sliding window counter keyed by soul type.
