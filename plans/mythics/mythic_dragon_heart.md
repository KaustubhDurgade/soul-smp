---
id: mythic.dragon_heart
name: Dragon’s Heart
mythic_type: TRANSFORMATION
tier: 3
activation_mode: TRANSFORM
acquisition_path: EVENT_DROP
soul_focus: [Flame, Wind]
cooldown_seconds: 420
active_window_seconds: 15
transformation:
  abilities:
    primary:
      type: cone_breath
      damage: 26
      dot: 4 over 4s
    secondary:
      type: wing_dash
      distance: 12
      knockback: 1.2
scaling:
  flame_souls_damage_per: 0.06 (cap 0.24)
  wind_souls_dash_bonus_blocks_per: 0.5 (cap 3)
resonance_hooks:
  - condition: resonance:flame_wind active_within:20
    modify:
      cone_breath_damage_mult: 1.15
      cone_breath_radius_add: 2
visual:
  model: transform/dragon_form
  wings_particles: fire_trail
  activation_burst: meteor_flare
anti_abuse:
  transform_cancel_min_seconds: 4
  revert_lockout_seconds: 6
attunement:
  exclusive_slot: transformation_core
logging: FULL
status: draft
---
## Summary
Transformation granting aerial mobility and fiery breath.

## Mechanics
Breath applies DoT; dash has mild knockback.

## Scaling
Flame & Wind souls raise breath damage and dash distance.

## Resonance Hooks
Flame+Wind increases damage and breath radius.

## Visuals
Fiery wings, ember trails.

## Anti-Abuse
Minimum active window before cancel allowed.

## Edge Cases
* In water, breath damage reduced 40%.

## Implementation Notes
Transformation harness swaps ability mapping table.
