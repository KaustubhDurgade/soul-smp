---
id: contract.magma_offer
name: Magma Offer
tier: 2
class: OFFENSE
scope: AREA
sacrifice:
  type: ENVIRONMENT
  requires_block: lava_source
activation:
  mode: INSTANT
  cooldown_seconds: 150
  internal_lockout_tag: area_control_fire
effects:
  hazard_radius: 9
  tick_damage: 5
  terrain_scorch_seconds: 12
scaling:
  formula: "tick_damage = base + (order_souls * 1.0)"
  clamp_tick_damage: 10
resonance_hooks:
  - condition: resonance:flame_order within:14
    modify:
      tick_damage_mult: 1.2
      adds_effect: purge_flames
conflict_tags: [area_control_fire]
anti_abuse:
  scorch_block_limit: 40
logging: EXTENDED
status: draft
---
## Summary
Channels lava into a fiery hazard that scorches terrain and damages entities.

## Mechanics
* Scorched blocks visually tinted; revert after duration.

## Scaling
Order souls elevate damage (cap 10).

## Resonance Hooks
Flame+Order adds purge_flames: periodic cleanse of enemy buffs.

## Visuals
Lava splash particles, orange heat shimmer, occasional smoke columns.

## Anti-Abuse & Conflicts
Cap scorched block count to avoid massive terrain mutation.

## Edge Cases
* Non-source lava replaced with magma block for safety.
* Fire-immune mobs still receive minimal heat tick (1) for tagging.

## Implementation Notes
Maintain a set of modified blocks for cleanup.
