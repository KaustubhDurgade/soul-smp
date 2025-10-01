---
id: contract.cataclysm
name: Cataclysm Pact
tier: 3
class: FUSION
scope: AREA
sacrifice:
  type: RARE_ITEMS_POOL
  required_items:
    - flame_relic
    - tides_relic
    - shadow_relic
activation:
  mode: SEQUENCE
  steps:
    - place_relics_seconds: 5
    - channel_core_seconds: 6
  failure_interrupt_damage_threshold: 8
cooldown_seconds: 900
effects:
  vortex_radius: 16
  pulses: 6
  pulse_damage: 14
  pull_strength: 0.22
scaling:
  formula: "pulse_damage = base + (unique_souls*2)"
  clamp_pulse_damage: 26
resonance_hooks:
  - condition: multi_resonance_count>=3 within:20
    modify:
      pulses_add: 2
      pull_strength_mult: 1.15
conflict_tags: [hybrid_fusion, area_control_fire]
anti_abuse:
  max_server_active: 1
logging: FULL
status: draft
---
## Summary
High-cost multi-relic ritual spawning a cataclysmic convergence vortex dealing repeated AoE damage + pull.

## Mechanics
* Interrupt if cumulative damage to channelers > threshold before core completes.

## Scaling
Unique souls increase pulse damage (cap 26).

## Resonance Hooks
Three or more resonances amplify pulses and pull.

## Visuals
Layered elemental domes, shadow spikes, swirling fire-water helix.

## Anti-Abuse & Conflicts
Server-limited: only one active; hybrid_fusion domain enforces large cooldown.

## Edge Cases
* Partial failure consumes 50% of relics.

## Implementation Notes
Sequence orchestrator manages step timers + interruption conditions.
