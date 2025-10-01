---
id: mythic.aurora_crown
name: Aurora Crown
mythic_type: ARTIFACT
tier: 2
activation_mode: ACTIVE_WINDOW
acquisition_path: EVENT_DROP
soul_focus: ANY
cooldown_seconds: 200
active_window_seconds: 8
passives:
  movement_speed_percent: 0.08
  ult_gain_percent: 0.05
active:
  aura_radius: 10
  regen: 1.0
  lifesteal: 0.04
scaling:
  unique_souls_radius_per: 1 (cap 16)
resonance_hooks:
  - condition: multi_resonance_count>=2 within:12
    modify:
      lifesteal_add: 0.02
visual:
  model: artifact/aurora_crown
  aura: rainbow_arcs
anti_abuse:
  lifesteal_conflict_tag: sustain_leech
attunement:
  exclusive_slot: artifact_core
logging: BASIC
status: draft
---
## Summary
Buff crown granting moderate passive utility and an active supportive aura.

## Mechanics
Active aura shares regen + lifesteal for duration.

## Scaling
Unique souls add radius.

## Resonance Hooks
≥2 resonances increase lifesteal.

## Visuals
Rainbow arc pulses and soft gradient halo.

## Anti-Abuse
Lifesteal domain conflict ensures strongest aura only.

## Edge Cases
* Lifesteal suppressed if stronger sustain already present.

## Implementation Notes
Marks aura with sustain_leech tag for conflict manager.
