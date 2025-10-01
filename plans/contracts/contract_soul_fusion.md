---
id: contract.soul_fusion
name: Soul Fusion Contract
tier: 3
class: FUSION
scope: LOCAL
sacrifice:
  type: SOUL_SLOT
  secondary_soul_consumed: true
activation:
  mode: INSTANT
  cooldown_seconds: 780
effects:
  hybrid_form_duration: 14
  hybrid_stat_scalars:
    damage_amp: 0.18
    ability_cdr: 0.12
scaling:
  formula: "damage_amp = base + (compatible_pair?0.07:0)"
  clamp_damage_amp: 0.30
resonance_hooks:
  - condition: pair_compatible true
    modify:
      ability_cdr_add: 0.05
conflict_tags: [hybrid_fusion]
anti_abuse:
  restore_soul_after_seconds: 900
logging: FULL
status: draft
---
## Summary
Consumes a secondary soul temporarily to grant hybrid form with merged kit bonuses.

## Mechanics
* On revert, secondary soul enters lockout until restore timer fires.

## Scaling
Compatible soul pair yields extra damage & CDR.

## Resonance Hooks
Compatibility check effectively a dynamic resonance boosting stats.

## Visuals
Dual-element swirling sigil, alternating color pulses.

## Anti-Abuse & Conflicts
Long restore timer prevents rapid cycling of different hybrid forms.

## Edge Cases
* If player logs out mid-form, timer continues offline.

## Implementation Notes
Secondary soul stored in persistent metadata with restore timestamp.
