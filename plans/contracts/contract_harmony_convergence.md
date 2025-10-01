---
id: contract.harmony_convergence
name: Harmony Convergence
tier: 2
class: SUPPORT
scope: AREA
sacrifice:
  type: GROUP_POOL
  hp_pool_percent_each: 0.10
activation:
  mode: SEQUENCE
  steps:
    - channel_seconds: 3
    - alignment_radius: 6
activation_cooldown_seconds: 240
effects:
  aura_radius_base: 10
  buff_duration_seconds: 12
  buffs:
    - stat: damage_amp
      base: 0.10
    - stat: regen
      base: 1.0
scaling:
  formula: "radius = base + (unique_souls*1.5)"
  clamp_radius: 18
resonance_hooks:
  - condition: multi_resonance_count>=2 within:14
    modify:
      damage_amp_add: 0.05
conflict_tags: [support_regen]
anti_abuse:
  min_participants: 2
logging: EXTENDED
status: draft
---
## Summary
Group alignment ritual pooling health to project a multi-buff aura scaling with unique soul diversity.

## Mechanics
* Channel fails if participants move outside radius.
* Buff snapshot applied at completion.

## Scaling
Each unique soul increases radius (cap 18) and indirectly effectiveness.

## Resonance Hooks
If two or more active resonances within 14 blocks, damage amp +5%.

## Visuals
Converging colored beams, expanding multi-ring aura.

## Anti-Abuse & Conflicts
Requires at least 2 participants; support_regen conflict resolves strongest regen contributor.

## Edge Cases
* If one participant drops below 4 HP during channel, abort with partial refund.

## Implementation Notes
Channel manager tracks participant HP and position invariants.
