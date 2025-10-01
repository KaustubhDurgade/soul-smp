---
id: event.solar_flare
name: Solar Flare
category: ASTRAL
scope: WORLD
rarity: RARE
intensity_tier: 2
trigger:
  mode: TIMER
  interval_range_hours: [4,6]
  prerequisite:
    time_of_day: day
phases:
  telegraph_seconds: 25
  active_seconds: 150
  cooldown_seconds: 8400
mechanics:
  light_buff_damage_amp: 0.15
  undead_debuff: -0.20 defense
scaling:
  flame_souls_damage_bonus_per: 0.02 (cap 0.10)
resonance_hooks:
  - condition: resonance:flame_wind active_within:80
    modify:
      light_buff_damage_amp_add: 0.05
visual:
  sky_overlay: solar_radiance
  ambient_particles: golden_rays
rewards:
  buff_token: solar_essence
anti_abuse:
  global_exclusive_with: event.moon_eclipse
logging: EXTENDED
status: draft
---
## Summary
Daytime world flare amplifying light / flame output and weakening undead.

## Telegraph
Increasing brightness pulse culminating in flare.

## Mechanics
During active window, flame abilities gain extra damage; undead suffer defense debuff.

## Scaling
Flame souls add damage amp (cap included).

## Resonance Hooks
Flame+Wind synergy enhances damage further.

## Visuals
Solar bloom, radial light shafts.

## Anti-Abuse
Mutually exclusive with eclipse event.

## Edge Cases
* If scheduled at dusk, shorten duration proportionally.

## Implementation Notes
Uses brightness shader overlay + attribute broadcast patch.
