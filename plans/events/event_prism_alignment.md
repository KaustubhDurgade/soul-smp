---
id: event.prism_alignment
name: Prism Alignment
category: FUSION
scope: REGIONAL
rarity: LEGENDARY
intensity_tier: 3
trigger:
  mode: COMPOSITE
  conditions:
    silence_souls>=: 2
    frost_souls>=: 1
    lightning_souls>=: 1
phases:
  telegraph_seconds: 25
  active_seconds: 90
  recovery_seconds: 10
  cooldown_seconds: 5400
mechanics:
  petrify_duration: 2
  silence_duration: 2.5
  aura_radius: 14
scaling:
  extra_silence_souls_petrify_add: 0.25 (cap_total: 3)
resonance_hooks:
  - condition: resonance:frost_silence active_within:20
    modify:
      silence_duration_add: 0.5
visual:
  dome_layers: ice_outer + lightning_inner
  prism_beams: true
rewards:
  loot_table_id: loot.prism_crystals
anti_abuse:
  boss_control_resistance: 0.5
logging: FULL
status: draft
---
## Summary
High-tier fusion control event freezing and silencing within a crystalline dome.

## Telegraph
Prismatic vertical beams converge into dome outline.

## Mechanics
Initial petrify then silence; repeat pulses every 20s with reduced petrify (50%).

## Scaling
Additional Silence souls extend petrify (cap 3s).

## Resonance Hooks
Frost+Silence synergy extends silence duration.

## Visuals
Layered refractive dome, lightning lattice.

## Anti-Abuse
Bosses apply control resistance multiplier.

## Edge Cases
* Entities immune to petrify receive heavy slow instead.

## Implementation Notes
Pulse scheduler with diminishing petrify flag per pulse index.
