---
id: event.meteor_shower
name: Meteor Shower
category: WEATHER
scope: REGIONAL
rarity: UNCOMMON
intensity_tier: 2
trigger:
  mode: PROBABILISTIC
  chance_per_interval: 0.08
  interval_minutes: 20
  prerequisite:
    time_of_day: night
phases:
  telegraph_seconds: 15
  active_seconds: 90
  cooldown_seconds: 1800
mechanics:
  meteor_interval_seconds: 3
  meteor_damage: 10
  impact_radius: 3
scaling:
  wind_souls_scatter_reduce_per: 0.05 (cap 0.30)
resonance_hooks:
  - condition: resonance:flame_wind active_within:24
    modify:
      meteor_interval_seconds_mult: 0.85
      meteor_damage_mult: 1.10
visual:
  sky_trails: fiery_streaks
  impact_effect: explosion_sparks
rewards:
  loot_table_id: loot.meteor_fragments
anti_abuse:
  max_active_regions: 2
logging: BASIC
status: draft
---
## Summary
Regional meteor bombardment applying area denial and damage pressure.

## Telegraph
Small streak precursors and low rumble.

## Mechanics
Meteors target random points biased toward player clusters.

## Scaling
Wind souls reduce scatter, increasing precision.

## Resonance Hooks
Flame+Wind speeds interval and boosts damage.

## Visuals
Fiery arcs, crater sparks, light plume.

## Anti-Abuse
Limit simultaneous active regions to 2.

## Edge Cases
* Avoid impacts within 3 blocks of protected structures.

## Implementation Notes
Precompute candidate impact grid for fairness.
