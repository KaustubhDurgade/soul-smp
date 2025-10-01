---
id: contract.natures_tribute
name: Nature's Tribute
tier: 1
class: SUPPORT
scope: LOCAL
sacrifice:
  type: ENVIRONMENT
  required_blocks: [log, leaves]
  radius_scan: 6
  consume_count: 6
activation:
  mode: INSTANT
  cooldown_seconds: 100
effects:
  regen_per_tick: 1
  tick_interval: 1
  duration_seconds: 8
  cleanse_negative: true
scaling:
  formula: "regen = base + (tides_souls * 0.5)"
  clamp_regen: 3
resonance_hooks:
  - condition: resonance:nature_tides within:10
    modify:
      regen_per_tick_add: 0.5
      adds_status: water_blessing
conflict_tags: [support_regen]
anti_abuse:
  block_restore_after_seconds: 60
logging: BASIC
status: draft
---
## Summary
Consumes nearby foliage to create a brief healing rain with optional cleanse.

## Mechanics
* Removes a limited number of eligible environment blocks (restored later or replaced with saplings).

## Scaling
Tides souls increase regen (cap 3).

## Resonance Hooks
Nature+Tides adds extra regen and a water_blessing status (minor resistance).

## Visuals
Green aura sprays, falling leaf particles, subtle water ripples.

## Anti-Abuse & Conflicts
support_regen domain chooses strongest active regen aura; others suppressed.

## Edge Cases
* If insufficient blocks, activation aborts with message.
* Blocks protected by region plugin are skipped (count still required).

## Implementation Notes
Track consumed block positions for timed restoration queue.
