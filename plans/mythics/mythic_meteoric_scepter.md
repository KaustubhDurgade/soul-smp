---
id: mythic.meteoric_scepter
name: Meteoric Scepter
mythic_type: WORLD_OBJECT
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: EVENT_DROP
soul_focus: [Flame]
cooldown_seconds: 360
active_window_seconds: 12
active:
  meteor_interval_seconds: 2.5
  meteor_damage: 16
  impact_radius: 4
scaling:
  flame_souls_meteor_damage_per: 1 (cap_add: 6)
resonance_hooks:
  - condition: resonance:flame_wind active_within:24
    modify:
      meteor_interval_seconds_mult: 0.85
      meteor_damage_mult: 1.10
visual:
  model: world/meteor_scepter
  meteor_trail: flame_tail
  activation_burst: meteor_swarm
anti_abuse:
  max_active_meteors: 30
attunement:
  exclusive_slot: world_anchor
logging: FULL
status: draft
---
## Summary
Summons controllable meteor storm over target area.

## Mechanics
Spawns targeted meteors focusing entity clusters.

## Scaling
Flame souls increase damage.

## Resonance Hooks
Flame+Wind reduces interval and boosts damage.

## Visuals
Frequent fiery streaks, crater embers.

## Anti-Abuse
Cap concurrent meteors to 30.

## Edge Cases
* Avoid protected structure radii.

## Implementation Notes
Target selection weighted by hostile density heatmap.
