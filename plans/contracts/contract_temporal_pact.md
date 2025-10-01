---
id: contract.temporal_pact
name: Temporal Pact
tier: 1
class: SUPPORT
scope: LOCAL
sacrifice:
  type: COOLDOWN_DELAY
  delay_seconds: 15
activation:
  mode: INSTANT
  cooldown_seconds: 110
effects:
  haste_percent: 0.25
  ult_charge_instant: 0.20
  duration_seconds: 5
scaling:
  formula: "haste = base + (wind_souls * 0.03)"
  clamp_haste: 0.40
resonance_hooks:
  - condition: resonance:flame_wind within:12
    modify:
      duration_add: 2
      ult_charge_instant_add: 0.05
conflict_tags: [cooldown_reducer]
anti_abuse:
  apply_cdr_diminishing: true
logging: BASIC
status: draft
---
## Summary
Delays own ability recovery to provide a burst haste + ultimate charge.

## Mechanics
* Applies movement + attack speed buff under haste_percent.

## Scaling
Wind souls add 3% haste each (cap 40%).

## Resonance Hooks
Flame+Wind extends duration and grants extra ult charge.

## Visuals
Golden clock glyph spin, time streak particles.

## Anti-Abuse & Conflicts
Falls under cooldown_reducer domain, triggers diminishing if stacked.

## Edge Cases
* If ultimate already near full, excess charge converted to minor CDR refund.

## Implementation Notes
Record pre-delay cooldown timestamps for fairness.
