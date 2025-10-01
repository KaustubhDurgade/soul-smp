---
id: contract.embers_tempo
name: Ember's Tempo
tier: 1
class: OFFENSE
scope: LOCAL
sacrifice:
  type: ATTACK_DELAY
  delay_next_attack_seconds: 2
activation:
  mode: INSTANT
  cooldown_seconds: 90
effects:
  ignite_radius: 6
  ignite_damage: 4
  ignite_duration: 4
scaling:
  formula: "ignite_damage = base + (trickery_souls * 1)"
  clamp_ignite_damage: 8
resonance_hooks:
  - condition: resonance:flame_trickery within:10
    modify:
      ignite_radius_add: 2
      adds_dash_ignite: true
conflict_tags: []
anti_abuse:
  max_ignite_targets: 8
logging: BASIC
status: draft
---
## Summary
Delays next attack to unleash an igniting pulse around the user.

## Mechanics
* Affected entities receive a burning DoT once; re-entries do not re-apply until expired.

## Scaling
Trickery souls add flat damage (cap 8).

## Resonance Hooks
Flame+Trickery expands radius and causes dash to spread ignite when used within 5s.

## Visuals
Flickering ember burst, brief heat distortion.

## Anti-Abuse & Conflicts
Target cap 8 to avoid huge mob farm exploitation.

## Edge Cases
* If dash ignite triggers, it cannot re-trigger ignite on already burning targets.

## Implementation Notes
Per-entity burn timestamp map with rapid lookup set.
