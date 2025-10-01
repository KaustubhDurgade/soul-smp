---
id: event.cataclysmic_convergence
name: Cataclysmic Convergence
category: FUSION
scope: REGIONAL
rarity: LEGENDARY
intensity_tier: 3
trigger:
  mode: COMPOSITE
  conditions:
    flame_ultimates_within_60s>=: 1
    tides_ultimates_within_60s>=: 1
    shadow_ultimates_within_60s>=: 1
phases:
  telegraph_seconds: 20
  active_seconds: 70
  recovery_seconds: 8
  cooldown_seconds: 7200
mechanics:
  vortex_radius: 15
  pulse_damage: 18
  pulse_interval: 5
  pull_strength: 0.24
scaling:
  additional_unique_soul_pulse_damage_add: 1 (cap +6)
resonance_hooks:
  - condition: multi_resonance_count>=3 within:18
    modify:
      pulse_interval_mult: 0.85
      pull_strength_mult: 1.10
visual:
  layered_domes: fire + water + shadow_spikes
  particle_density: high
rewards:
  loot_table_id: loot.cataclysm_core
anti_abuse:
  region_exclusive: true
logging: FULL
status: draft
---
## Summary
Fusion event triggered by sequential ultimates of three soul types creating a destructive vortex.

## Telegraph
Tri-color spiral converging to center with rising hum.

## Mechanics
Pulses damage + pull; final pulse stronger (×1.4).

## Scaling
Additional unique souls add pulse damage (cap +6).

## Resonance Hooks
Multiple active resonances reduce interval and boost pull.

## Visuals
Layered elemental shells; shadow spikes extrude.

## Anti-Abuse
Regional exclusivity enforced; stops scheduling of Tier 2 events concurrently.

## Edge Cases
* If one ultimate is canceled before telegraph ends, abort.

## Implementation Notes
Pulse scheduler last iteration multiplies damage.
