---
id: contract.mana_infusion
name: Mana Infusion Pact
tier: 1-2
class: SUPPORT
scope: LOCAL
sacrifice:
  type: RESOURCE
  mana_cost: 40-80
activation:
  mode: INSTANT
  cooldown_seconds: 120
effects:
  ult_gain_percent: 0.15
  passive_amp_percent: 0.10
  duration_seconds: 8
scaling:
  formula: "ult_gain = base + (elemental_souls*0.03)"
  clamp_ult_gain: 0.30
resonance_hooks:
  - condition: elemental_resonance_count>=2 within:12
    modify:
      passive_amp_percent_add: 0.05
conflict_tags: [cooldown_reducer]
anti_abuse:
  resource_threshold_min: 30
logging: BASIC
status: draft
---
## Summary
Converts mana into ultimate progress and passive amplification.

## Mechanics
* If resource below threshold, deny activation.

## Scaling
Elemental souls increase ult gain (cap 30%).

## Resonance Hooks
Multiple elemental resonances add passive amplification.

## Visuals
Floating mana orbs spiral into player, shimmering dome.

## Anti-Abuse & Conflicts
Falls under cooldown_reducer; internal resource threshold prevents micro-spam.

## Edge Cases
* If ultimate already full, ult_gain converts to minor regen buff.

## Implementation Notes
Resource manager deducts variable cost proportionally to desired tier.
