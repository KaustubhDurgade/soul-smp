---
id: event.harmony_festival
name: Harmony Festival
category: GROUP
scope: LOCAL
rarity: UNCOMMON
intensity_tier: 2
trigger:
  mode: THRESHOLD
  condition:
    unique_souls_in_radius>=: 5
  radius_check: 10
phases:
  telegraph_seconds: 10
  active_seconds: 60
  cooldown_seconds: 1800
mechanics:
  aura_radius: 12
  regen: 1.0
  lifesteal: 0.05
  shared_ult_gain: 0.02 per 5s
scaling:
  unique_souls_radius_per: 1 (cap 18)
resonance_hooks:
  - condition: multi_resonance_count>=2 within:12
    modify:
      lifesteal_add: 0.03
visual:
  rainbow_rings: true
  elemental_arcs: true
rewards:
  buff_token: harmony_thread
anti_abuse:
  min_players: 4
logging: BASIC
status: draft
---
## Summary
Celebratory buff event triggered by high local soul diversity.

## Telegraph
Faint multicolor concentric ripples.

## Mechanics
Applies pooled passive benefits, encourages clustering briefly.

## Scaling
Each distinct soul increases radius (cap 18).

## Resonance Hooks
Multiple resonances add lifesteal.

## Visuals
Color cycling rings and small elemental flourish effects.

## Anti-Abuse
Requires at least four players; prevents self-trigger farm.

## Edge Cases
* If player count drops below 2 mid-event, early terminate.

## Implementation Notes
Diversity check uses hashed soul set signature.
