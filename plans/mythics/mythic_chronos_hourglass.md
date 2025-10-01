---
id: mythic.chronos_hourglass
name: Chronos Hourglass
mythic_type: ARTIFACT
tier: 2
activation_mode: ACTIVE_WINDOW
acquisition_path: EVENT_DROP
soul_focus: [Wind]
cooldown_seconds: 210
active_window_seconds: 10
passives:
  minor_cdr_percent: 0.05
active:
  cdr_reduction_percent: 0.50
  enemy_slow_percent: 0.25
  radius: 10
scaling:
  wind_souls_radius_per: 1 (cap 14)
resonance_hooks:
  - condition: resonance:flame_wind active_within:14
    modify:
      cdr_reduction_percent_add: 0.05
visual:
  model: artifact/chronos_hourglass
  aura: time_ripple
  activation_burst: hourglass_spin
anti_abuse:
  cdr_diminishing_domain: cooldown_reducer
attunement:
  exclusive_slot: artifact_core
logging: EXTENDED
status: draft
---
## Summary
Time artifact compressing cooldowns and slowing enemies in an area.

## Mechanics
Applies multiplicative reduction to current remaining cooldowns upon activation, then additive regen bonus.

## Scaling
Wind souls add radius (cap 14).

## Resonance Hooks
Flame+Wind adds extra CDR.

## Visuals
Floating hourglass rotating with temporal ripple distortion.

## Anti-Abuse
Falls inside cooldown_reducer domain; diminishing applied after merges.

## Edge Cases
* Abilities already <2s remaining unaffected to avoid micro abuse.

## Implementation Notes
Snapshot remaining cooldowns, apply scaling, then continuous regen tick.
