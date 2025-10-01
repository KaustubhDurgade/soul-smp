---
id: mythic.elemental_orb
name: Elemental Orb
mythic_type: ARTIFACT
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: EVENT_DROP
soul_focus: [Flame,Frost,Tides,Lightning]
cooldown_seconds: 320
active_window_seconds: 12
passives:
  charge_capacity: 4
active:
  discharge_damage_per_charge: 10
  discharge_radius: 7
  cycle_elements: [FIRE,WATER,FROST,LIGHTNING]
scaling:
  elemental_resonance_count_damage_bonus_per: 0.05 (cap 0.25)
resonance_hooks:
  - condition: elemental_resonance_count>=3 within:16
    modify:
      discharge_radius_add: 2
visual:
  model: artifact/elemental_orb
  elemental_arcs: rotating
  activation_burst: prismatic_wave
anti_abuse:
  max_discharge_per_second: 2
attunement:
  exclusive_slot: artifact_core
logging: EXTENDED
status: draft
---
## Summary
Stores elemental charges then discharges multi-element AoE blasts.

## Mechanics
Gain charges passively over time & via elemental kills; discharge sequentially during window.

## Scaling
Elemental resonance count increases damage per charge.

## Resonance Hooks
≥3 resonances enlarge radius.

## Visuals
Color-shifting orb with rotating arc patterns.

## Anti-Abuse
Rate limit discharge frequency.

## Edge Cases
* If no charges, activation aborts (no cooldown consumed).

## Implementation Notes
Charge tracker persists across restarts via player data.
