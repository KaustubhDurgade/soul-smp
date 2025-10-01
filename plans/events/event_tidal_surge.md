---
id: event.tidal_surge
name: Tidal Surge
category: SOUL
scope: REGIONAL
rarity: COMMON
intensity_tier: 1-2
trigger:
  mode: TIMER
  interval_range_minutes: [35,60]
  biome_filter: [ocean,beach]
phases:
  telegraph_seconds: 18
  active_seconds: 100
  cooldown_seconds: 1200
mechanics:
  push_pull_cycle_seconds: 6
  push_force: 0.4
  pull_force: 0.35
scaling:
  tides_souls_force_bonus_per: 0.03 (cap 0.12)
resonance_hooks:
  - condition: resonance:tides_lightning active_within:28
    modify:
      pull_force_mult: 1.2
      adds_stun_chance: 0.05
visual:
  water_walls: spiral_rise
  bubble_particles: true
rewards:
  buff_token: tidal_essence
anti_abuse:
  player_motion_cap: 1.2
logging: BASIC
status: draft
---
## Summary
Oceanic surge alternates push/pull water dynamics.

## Telegraph
Rising water spiral + deep tonal audio cue.

## Mechanics
Every cycle alternates outward push then inward pull.

## Scaling
Tides souls increase both forces (capped).

## Resonance Hooks
Tides+Lightning adds conductive stun chance.

## Visuals
Spiral water ring and bubble plumes.

## Anti-Abuse
Motion cap ensures players not flung excessively.

## Edge Cases
* Boats receive reduced force scaling.

## Implementation Notes
Apply vector field based on cyclic phase state machine.
