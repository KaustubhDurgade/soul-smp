---
id: event.phoenix_rise
name: Phoenix Rise
category: CREATURE
scope: WORLD
rarity: LEGENDARY
intensity_tier: 3
trigger:
  mode: COMPOSITE
  conditions:
    biome: desert
    time_of_day: sunrise
    flame_souls>=: 2
phases:
  telegraph_seconds: 40
  active_seconds: 120
  cooldown_seconds: 10800
mechanics:
  boss_spawn: phoenix_entity
  aura_damage_per_second: 4
  rebirth_phase_seconds: 8
scaling:
  flame_souls_aura_bonus_per: 0.5 (cap +4)
resonance_hooks:
  - condition: resonance:flame_wind active_within:40
    modify:
      aura_damage_per_second_add: 2
visual:
  sunrise_overglow: true
  phoenix_trails: fiery_wings
rewards:
  loot_table_id: loot.phoenix_core
anti_abuse:
  boss_unique_active: true
logging: FULL
status: draft
---
## Summary
Mythic phoenix encounter with rebirth phase and persistent flame aura.

## Telegraph
Blazing sky gradient + distant phoenix silhouette.

## Mechanics
On HP depletion triggers rebirth invulnerable phase (8s) then resumes.

## Scaling
Flame souls raise aura damage (cap).

## Resonance Hooks
Flame+Wind increases aura damage.

## Visuals
Large winged fiery entity, ascending flame pillars.

## Anti-Abuse
Single active boss enforcement; cooldown long.

## Edge Cases
* If despawn due to distance, schedule deferred reattempt.

## Implementation Notes
Boss state machine handles rebirth sub-phase.
