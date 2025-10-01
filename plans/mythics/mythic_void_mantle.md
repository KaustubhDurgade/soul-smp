---
id: mythic.void_mantle
name: Void Mantle
mythic_type: TRANSFORMATION
tier: 3
activation_mode: TRANSFORM
acquisition_path: CONTRACT_FUSION
soul_focus: [Shadow, Silence]
cooldown_seconds: 400
active_window_seconds: 10
transformation:
  abilities:
    primary:
      type: shadow_step
      range: 10
      bonus_damage: 14
    secondary:
      type: void_clone_field
      clone_damage: 6
      clone_interval: 2
scaling:
  shadow_souls_bonus_damage_per: 2 (cap_add: 8)
  silence_souls_range_per: 1 (cap_add: 4)
resonance_hooks:
  - condition: resonance:shadow_silence active_within:18
    modify:
      shadow_step_range_add: 2
      bonus_damage_mult: 1.10
visual:
  model: transform/void_form
  aura: void_mist
  activation_burst: dark_implosion
anti_abuse:
  transform_cancel_min_seconds: 3
attunement:
  exclusive_slot: transformation_core
logging: FULL
status: draft
---
## Summary
Assassin-style void form enabling teleport strikes and clone field.

## Mechanics
Shadow step teleports and deals burst; clone field spawns ephemeral attackers.

## Scaling
Shadow & Silence souls add damage and range.

## Resonance Hooks
Shadow+Silence boosts range & damage.

## Visuals
Dark implosion start, lingering mist.

## Anti-Abuse
Short minimum active window before cancel.

## Edge Cases
* If line of sight blocked, range reduces dynamically.

## Implementation Notes
Path check pre-teleport to avoid embedding.
