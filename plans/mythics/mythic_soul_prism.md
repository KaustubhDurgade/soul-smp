---
id: mythic.soul_prism
name: Soul Prism
mythic_type: ARTIFACT
tier: 2
activation_mode: PASSIVE_ONLY
acquisition_path: CRAFT_INFUSION
soul_focus: ANY
passives:
  passive_strength_mult: 2.0
  radius: 10
scaling:
  resonance_count_duration_bonus_per: 0.05 (cap 0.25)
resonance_hooks:
  - condition: multi_resonance_count>=2 within:12
    modify:
      passive_strength_mult_add: 0.25
visual:
  model: artifact/soul_prism
  orbiting_prisms: true
anti_abuse:
  passive_duration_cap_seconds: 20
attunement:
  exclusive_slot: artifact_core
logging: BASIC
status: draft
---
## Summary
Amplifies passive & tactical ability strength inside radius.

## Mechanics
Doubles base passive strength; extends durations within caps.

## Scaling
Resonance count adds duration bonus (cap 25%).

## Resonance Hooks
≥2 resonances grants extra strength multiplier.

## Visuals
Orbiting colored prisms with refracted beams.

## Anti-Abuse
Duration cap ensures bounded extension.

## Edge Cases
* If multiple prisms overlap, only highest strength applies.

## Implementation Notes
Applies attribute multipliers flagged for passive-only context.
