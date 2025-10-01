---
id: event.moon_eclipse
name: Moon Eclipse
category: ASTRAL
scope: WORLD
rarity: RARE
intensity_tier: 1-2
trigger:
  mode: TIMER
  interval_range_hours: [3,5]
  prerequisite:
    time_of_day: night
phases:
  telegraph_seconds: 30
  active_seconds: 120
  cooldown_seconds: 7200
mechanics:
  darkness_level: 0.6
  shadow_buff: stealth_duration +0.5s
  light_debuff: damage_amp -0.1
scaling:
  unique_shadow_souls_bonus: 0.02 (cap 0.10)
resonance_hooks:
  - condition: resonance:shadow_silence active_within:64
    modify:
      shadow_buff_stealth_duration_add: 0.5
visual:
  sky_overlay: eclipse_disc
  ambient_particles: dark_motes
rewards:
  buff_token: eclipse_fragments
anti_abuse:
  global_unique_active: true
logging: EXTENDED
status: draft
---
## Summary
World eclipse lowering light and empowering shadow interactions.

## Telegraph
Moon darkening animation disc + ambient dimming.

## Mechanics
Light-based abilities slightly weakened; shadow stealth extended.

## Scaling
Unique shadow souls add buff intensity up to cap.

## Resonance Hooks
Shadow+Silence extends stealth further.

## Visuals
Fading skylight filter, slow swirling dark motes.

## Anti-Abuse
Global uniqueness prevents stacking with Solar Flare.

## Edge Cases
* If solar flare scheduled inside cooldown window, defer.

## Implementation Notes
Uses world lighting modifier wrapper; revert gracefully post-event.
