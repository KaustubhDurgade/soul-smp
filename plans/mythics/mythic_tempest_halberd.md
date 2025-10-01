---
id: mythic.tempest_halberd
name: Tempest Halberd
mythic_type: WEAPON
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: EVENT_DROP
soul_focus: [Wind, Tides]
cooldown_seconds: 260
active_window_seconds: 10
passives:
  wind_damage_amp: 0.12
  tides_damage_amp: 0.08
active:
  chain_cleave_base: 3
  chain_cleave_damage: 14
  chain_radius: 5
scaling:
  wind_souls_chain_targets_per: 1 (cap_total: 6)
resonance_hooks:
  - condition: resonance:wind_tides active_within:18
    modify:
      chain_cleave_base_add: 1
      chain_cleave_damage_mult: 1.15
visual:
  model: weapon/tempest_halberd
  swing_trail: electric_air
  activation_burst: vortex_spark
anti_abuse:
  chain_target_repeat_block_seconds: 2
attunement:
  exclusive_slot: weapon_core
logging: EXTENDED
status: draft
---
## Summary
Chain-cleaving halberd manipulating wind and tidal energy for area burst.

## Mechanics
On activation, next attacks arc to additional targets.

## Scaling
Extra Wind souls raise max chain targets (cap 6).

## Resonance Hooks
Wind+Tides increases base chain count and damage.

## Visuals
Electric gust arcs connecting targets.

## Anti-Abuse
Target repeat block prevents ping-pong hits.

## Edge Cases
* If insufficient secondary targets, unused arcs dissipate harmlessly.

## Implementation Notes
Maintain per-activation target memory set.
