---
id: event.wild_mana_surge
name: Wild Mana Surge
category: RESOURCE
scope: LOCAL
rarity: COMMON
intensity_tier: 1
trigger:
  mode: PROBABILISTIC
  chance_per_interval: 0.12
  interval_minutes: 15
phases:
  telegraph_seconds: 8
  active_seconds: 50
  cooldown_seconds: 900
mechanics:
  ult_gain_mult: 1.25
  ability_cdr_mult: 0.90
scaling:
  elemental_resonances_bonus_per: 0.05 (cap 0.20)
resonance_hooks:
  - condition: elemental_resonance_count>=2 within:10
    modify:
      ult_gain_mult_add: 0.10
visual:
  mana_orbs: swirling
rewards:
  buff_token: raw_mana_essence
anti_abuse:
  region_overlap_limit: 2
logging: BASIC
status: draft
---
## Summary
Localized ambient mana field boosting ultimate gain and minor CDR.

## Telegraph
Flickering motes clustering before activation.

## Mechanics
Applies multiplier to participating players inside radius (implicit 10).

## Scaling
Elemental resonance count increases ult gain multiplier.

## Resonance Hooks
Count≥2 adds additional gain.

## Visuals
Swirling translucent mana orbs.

## Anti-Abuse
Limit overlapping fields to 2.

## Edge Cases
* If overlapping with Harmony Festival, take stronger multiplier only.

## Implementation Notes
Attach region token to prevent overscheduling in same chunk cluster.
