---
id: contract.tides_unbound
name: Tides Unbound
tier: 2
class: CONTROL
scope: AREA
sacrifice:
  type: DASH_CHARGE
  charges_spent: 1
activation:
  mode: INSTANT
  cooldown_seconds: 140
effects:
  whirlpool_radius: 8
  pull_strength: 0.18
  duration_seconds: 10
  tick_interval: 1.0
scaling:
  formula: "pull = base + (lightning_souls * 0.02)"
  clamp_pull: 0.26
resonance_hooks:
  - condition: resonance:tides_lightning within:12
    modify:
      pull_strength_mult: 1.25
      tick_interval_mult: 0.85
conflict_tags: [area_control_water]
anti_abuse:
  entity_pull_cap_per_tick: 6
logging: BASIC
status: draft
---
## Summary
Converts a dash resource into a persistent whirlpool control field pulling entities inward.

## Mechanics
* Applies minor slow to pulled entities.
* Ignores entities above Y+6 from center (vertical guardrail).

## Scaling
Lightning souls boost pull (cap 0.26).

## Resonance Hooks
Tides+Lightning increases pull and accelerates tick rate.

## Visuals
Spiral bubble particles, rotating water ring.

## Anti-Abuse & Conflicts
Pull limit per tick prevents giant mob herds from overloading server.

## Edge Cases
* Large boss flagged: pull_strength reduced 75%.
* If waterlogged terrain, add extra bubble bursts.

## Implementation Notes
Compute vector field once; reuse normalized direction per entity.
