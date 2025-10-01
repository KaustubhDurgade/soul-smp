---
id: event.leviathan_spawn
name: Leviathan Spawn
category: CREATURE
scope: WORLD
rarity: LEGENDARY
intensity_tier: 3
trigger:
  mode: COMPOSITE
  conditions:
    biome: deep_ocean
    tides_souls>=: 2
    shadow_souls>=: 1
phases:
  telegraph_seconds: 50
  active_seconds: 150
  cooldown_seconds: 10800
mechanics:
  boss_spawn: leviathan_entity
  vortex_radius: 18
  water_control_pulse_interval: 6
scaling:
  tides_souls_vortex_radius_per: 1 (cap +6)
resonance_hooks:
  - condition: resonance:tides_lightning active_within:50
    modify:
      water_control_pulse_interval_mult: 0.85
visual:
  deep_sea_glow: blue_pulse
  leviathan_tentacles: display_entities
rewards:
  loot_table_id: loot.leviathan_core
anti_abuse:
  boss_unique_active: true
logging: FULL
status: draft
---
## Summary
Massive oceanic boss with control vortex and periodic water manipulation pulses.

## Telegraph
Ocean surface whirlpool forms; bioluminescent glow.

## Mechanics
Control pulses pull entities and apply brief drown debuff.

## Scaling
Tides souls increase vortex size (cap).

## Resonance Hooks
Tides+Lightning accelerates pulses.

## Visuals
Large tentacle projections, swirling water column.

## Anti-Abuse
Unique active world creature enforcement.

## Edge Cases
* If boss moves out of ocean biome, reduce movement speed 60%.

## Implementation Notes
Vortex field recalculated every 2 pulses for performance.
