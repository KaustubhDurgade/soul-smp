---
id: mythic.leviathan_core
name: Leviathan Core
mythic_type: TRANSFORMATION
tier: 3
activation_mode: TRANSFORM
acquisition_path: EVENT_DROP
soul_focus: [Tides, Lightning]
cooldown_seconds: 420
active_window_seconds: 15
transformation:
  abilities:
    primary:
      type: vortex_pulse
      pull_strength: 0.22
      radius: 10
    secondary:
      type: crushing_wave
      damage: 20
      slow: 0.35
scaling:
  tides_souls_radius_per: 1 (cap 4)
  lightning_souls_pull_per: 0.02 (cap 0.08)
resonance_hooks:
  - condition: resonance:tides_lightning active_within:20
    modify:
      pull_strength_mult: 1.15
      pulse_interval_reduction: 0.15
visual:
  model: transform/leviathan_form
  tentacle_particles: water_spiral
  activation_burst: geyser_column
anti_abuse:
  transform_cancel_min_seconds: 4
  revert_lockout_seconds: 6
attunement:
  exclusive_slot: transformation_core
logging: FULL
status: draft
---
## Summary
Water control transformation with vortex pulses and wave slam.

## Mechanics
Primary pulses every 3s; secondary directional wave.

## Scaling
Souls enhance radius & pull.

## Resonance Hooks
Tides+Lightning increases pull + faster pulses.

## Visuals
Geyser effects, spiral water trails.

## Anti-Abuse
Standard transform guardrails.

## Edge Cases
* On land far from water >32 blocks, apply mild self slow.

## Implementation Notes
Pulse scheduler keyed to transformation state.
