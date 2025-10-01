---
id: mythic.oblivion_shard
name: Oblivion Shard
mythic_type: ARTIFACT
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: BOSS_REWARD
soul_focus: [Shadow, Silence]
cooldown_seconds: 260
active_window_seconds: 10
passives:
  shadow_power_amp: 0.15
  silence_power_amp: 0.15
active:
  aoe_radius: 8
  damage: 20
  silence_duration: 2.0
scaling:
  silence_souls_duration_per: 0.25 (cap_add: 1.0)
resonance_hooks:
  - condition: resonance:shadow_silence active_within:18
    modify:
      damage_mult: 1.10
      silence_duration_add: 0.5
visual:
  model: artifact/oblivion_shard
  aura: void_dome
  activation_burst: silence_wave
anti_abuse:
  silence_duration_cap: 2.5
attunement:
  exclusive_slot: artifact_core
logging: FULL
status: draft
---
## Summary
Silence + damage artifact with passive power amplification.

## Mechanics
On activation emits silence + damage wave; silence respects global cap.

## Scaling
Silence souls extend duration within cap.

## Resonance Hooks
Shadow+Silence increases wave potency.

## Visuals
Void dome with ripple pulse.

## Anti-Abuse
Cap ensures stacking sources cannot exceed 2.5s single-source silence.

## Edge Cases
* If target already silenced > remaining cap, skip extension.

## Implementation Notes
Applies silence with tag for conflict/cap management.
