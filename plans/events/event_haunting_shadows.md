---
id: event.haunting_shadows
name: Haunting Shadows
category: CREATURE
scope: LOCAL
rarity: COMMON
intensity_tier: 1
trigger:
  mode: PROBABILISTIC
  chance_per_interval: 0.10
  interval_minutes: 18
  prerequisite:
    time_of_day: night
phases:
  telegraph_seconds: 10
  active_seconds: 75
  cooldown_seconds: 1500
mechanics:
  wave_interval: 15
  wave_mob_count: 6
  mob_types: [shadow_wisp, shade]
scaling:
  shadow_souls_mob_bonus_per: 1 (cap_additional: 4)
resonance_hooks:
  - condition: resonance:shadow_trickery active_within:18
    modify:
      wave_interval_mult: 0.9
visual:
  ambient_mist: dark_low
  portal_effects: small_rifts
rewards:
  loot_table_id: loot.shadow_essence
anti_abuse:
  spawn_cap_region: 24
logging: BASIC
status: draft
---
## Summary
Local shadow mob incursion encouraging light / control responses.

## Telegraph
Small shadow rifts pulsate at ground level.

## Mechanics
Spawn waves every 15s; each wave mob count may scale with shadow souls.

## Scaling
Shadow souls near event increase additional mobs (cap +4 per wave).

## Resonance Hooks
Shadow+Trickery moderately accelerates waves (interval ×0.9).

## Visuals
Low dark mist, faint negative light halo.

## Anti-Abuse
Cap region spawn count to 24 simultaneously.

## Edge Cases
* If TPS < threshold, reduce wave_mob_count by 30%.

## Implementation Notes
Wave scheduler checks active spawn count before spawning new set.
