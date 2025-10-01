---
id: contract.elemental_offering
name: Elemental Offering
tier: 2
class: AREA
scope: AREA
sacrifice:
  type: ENV_RESOURCE_POOL
  required_blocks: [water, log, lava, snow]
  consume_each: 4
activation:
  mode: SEQUENCE
  steps:
    - gather_scan_seconds: 4
    - channel_seconds: 3
cooldown_seconds: 210
effects:
  field_duration: 12
  field_radius: 11
  elemental_cycle_interval: 3
  cycle_order: [FIRE, WATER, FROST, STORM]
scaling:
  formula: "radius = base + (unique_elemental_blocks/2)"
  clamp_radius: 15
resonance_hooks:
  - condition: elemental_resonance_count>=3 within:14
    modify:
      elemental_cycle_interval_mult: 0.85
      adds_effect: intensity_stack
conflict_tags: [area_control_fire]
anti_abuse:
  max_blocks_consumed: 32
logging: EXTENDED
status: draft
---
## Summary
Consumes a set of multi-element environmental resources to generate a rotating elemental field.

## Mechanics
* Each cycle changes damage / slow / pull / burn profile.

## Scaling
More distinct valid blocks marginally increase radius.

## Resonance Hooks
Three elemental resonances accelerate cycles and enable intensity stacking.

## Visuals
Color-shifting aura, elemental particle overlays rotating per phase.

## Anti-Abuse & Conflicts
Cap total consumed blocks to 32.

## Edge Cases
* If missing an element type, replace its cycle with neutral heal tick.

## Implementation Notes
Cycle scheduler updates effect payload reference each interval.
