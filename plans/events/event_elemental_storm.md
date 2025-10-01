---
id: event.elemental_storm
name: Elemental Storm
category: WEATHER
scope: REGIONAL
rarity: UNCOMMON
intensity_tier: 2
trigger:
  mode: TIMER
  interval_range_minutes: [45,75]
  biome_filter: [desert,ocean,plains]
  prerequisite:
    min_players: 4
    min_unique_souls: 3
phases:
  telegraph_seconds: 20
  active_seconds: 180
  cooldown_seconds: 900
scaling:
  player_count:
    radius_per_player: 1.2
    max_extra_radius: 30
  unique_souls:
    elemental_bias_weight: 0.15
  active_resonances:
    damage_amp_per_relevant: 0.05
    damage_amp_cap: 0.25
mechanics:
  elemental_rotation: [FIRE,TIDES,FROST,LIGHTNING]
  cycle_interval_seconds: 30
  effects:
    FIRE:
      tick_damage: 2
      fire_stack_increase: 1
    TIDES:
      slow_percent: 0.25
      pull_strength: 0.12
    FROST:
      freeze_chance: 0.08
    LIGHTNING:
      strike_rate_seconds: 5
resonance_hooks:
  - condition: resonance:flame_wind active_within:32
    modify:
      FIRE.fire_stack_increase: +1
      LIGHTNING.strike_rate_seconds: -1
visual:
  telegraph: swirling_cloud_band
  active_layers: 3
  particle_budget: 400
  suppression_strategy: density_scale_then_layer_drop
rewards:
  loot_table_id: loot.elemental_shards_t2
  participation_min_ticks: 60
anti_abuse:
  region_reentry_grace_seconds: 40
  diminishing_tag: weather_chain
logging: EXTENDED
status: draft
---
## Summary
Rotating multi-element weather event applying cyclical environmental pressure and buffs/nerfs.

## Telegraph
Cloud band forms with faint elemental flashes corresponding to first phase.

## Mechanics
Cycles every 30s; elemental sub-phase determines active effect payload.

## Scaling
Player count increases radius; resonance density adds capped damage amp.

## Resonance Hooks
Flame+Wind increases fire stacking and accelerates lightning.

## Visuals
Layered cloud strata, colored lightning, tidal mist, frost shards.

## Anti-Abuse
Weather fatigue tag prevents immediate re-roll loops.

## Edge Cases
* If element sub-phase has zero relevant souls present, intensity -25% that segment.

## Implementation Notes
Phase scheduler referencing rotation array; apply per-phase effect template.
