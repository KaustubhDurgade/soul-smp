---
id: contract.infernal
name: Infernal Contract
tier: 2
class: OFFENSE
scope: AREA
sacrifice:
  type: ITEM
  item_tag: flame_core
activation:
  mode: INSTANT
  cast_time_ticks: 20
  cooldown_seconds: 150
  internal_lockout_tag: area_control_fire
effects:
  duration_seconds: 15
  pulses_every_seconds: 3
  pulse_damage: 6
  field_radius: 10
scaling:
  formula: "Damage = base * (1 + elemental_resonances*0.08)"
  clamp_pulse_damage: 12
resonance_hooks:
  - condition: resonance:flame_wind within:16
    modify:
      field_radius_mult: 1.25
      pulse_damage_mult: 1.2
conflict_tags: [area_control_fire]
anti_abuse:
  max_concurrent_same_field_region: 1
logging: EXTENDED
status: draft
---
## Summary
Consumes a flame-aligned item to create a pulsing firestorm field dealing periodic AoE damage.

## Mechanics
* Field entity ticks every pulse interval.
* Applies ignite stack (1) per pulse to hostile entities inside.

## Scaling
Elemental resonance count nearby (fire/wind/tides/lightning) increases pulse damage (cap 12) and radius via hook.

## Resonance Hooks
Flame+Wind → radius ×1.25 and damage ×1.2.

## Visuals
Flaming sigil base, upward spiraling ember columns, intermittent smoke plumes.

## Anti-Abuse & Conflicts
`area_control_fire` prevents overlapping Tier 2+ fire hazard fields in same 32×32 region.

## Edge Cases
* If field spawn location blocked, shift upward until air or abort.
* Ignite immune entities still take base damage.

## Implementation Notes
Region registrar manages one active hazard per tag.
