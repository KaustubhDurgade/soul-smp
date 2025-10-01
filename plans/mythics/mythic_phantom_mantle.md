---
id: mythic.phantom_mantle
name: Phantom Mantle
mythic_type: ARMOR
tier: 3
activation_mode: PASSIVE_ONLY
acquisition_path: EVENT_DROP
soul_focus: [Shadow, Trickery]
passives:
  invis_on_ultimate_seconds: 4
  stealth_attack_bonus: 0.20
scaling:
  trickery_souls_bonus_per: 0.03 (cap 0.12)
resonance_hooks:
  - condition: resonance:shadow_trickery active_within:14
    modify:
      invis_on_ultimate_seconds_add: 2
visual:
  model: armor/phantom_mantle
  cloak_particles: dark_trail
anti_abuse:
  invis_damage_reveal: true
attunement:
  exclusive_slot: armor_core
logging: EXTENDED
status: draft
---
## Summary
Stealth-enabled mantle granting invisibility on ultimate and boosting opening strikes.

## Mechanics
Invisibility ends on attack or damage >1.5 hearts.

## Scaling
Trickery souls extend stealth attack bonus potential.

## Resonance Hooks
Shadow+Trickery extends invis window.

## Visuals
Dark cloak shimmer and trailing particle mist.

## Anti-Abuse
Immediate reveal on sustained AoE damage output.

## Edge Cases
* Channeled abilities cancel invis at start.

## Implementation Notes
Stealth tag includes source reference for audit.
