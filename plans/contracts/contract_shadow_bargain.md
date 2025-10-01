---
id: contract.shadow_bargain
name: Shadow Bargain
tier: 3
class: HYBRID
scope: AREA
sacrifice:
  type: HP_AND_ITEM
  hp_frac: 0.25
  item_tag: shadow_relic
activation:
  mode: INSTANT
  cooldown_seconds: 420
  internal_lockout_tag: stealth_mass_field
effects:
  group_invisibility_seconds: 8
  clone_wave_interval: 2
  clone_wave_count: 4
  clone_damage: 5
scaling:
  formula: "clone_damage = base + (trickery_souls * 1)"
  clamp_clone_damage: 10
resonance_hooks:
  - condition: resonance:shadow_trickery within:18
    modify:
      clone_wave_count_add: 1
      group_invisibility_seconds_add: 2
conflict_tags: [stealth_field, hybrid_fusion]
anti_abuse:
  invis_reveal_on_attack: true
logging: FULL
status: draft
---
## Summary
Sacrifices health and a relic to cloak allies and launch sequential clone assault waves.

## Mechanics
* Attacking removes invisibility from that player only.

## Scaling
Trickery souls raise clone damage (cap 10).

## Resonance Hooks
Shadow+Trickery adds extra wave and invisibility duration.

## Visuals
Large dark mist dome, spectral silhouettes surging outward each wave.

## Anti-Abuse & Conflicts
Stealth_field prevents overlapping massive invis auras.

## Edge Cases
* Reveal logic ignores passive aura ticks.

## Implementation Notes
Clone waves scheduled with shared pool to avoid entity spike.
