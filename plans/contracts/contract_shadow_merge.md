---
id: contract.shadow_merge
name: Shadow Merge
tier: 2
class: HYBRID
scope: LOCAL
sacrifice:
  type: COOLDOWN
  ability_lock_ids: [dash, blink]
  lock_duration_seconds: 8
activation:
  mode: INSTANT
  cast_time_ticks: 10
  cooldown_seconds: 130
effects:
  stealth_duration_seconds: 5
  clone_count: 2
  clone_lifespan_seconds: 6
  opening_strike_bonus: 0.25
scaling:
  formula: "clone_count = base + floor(trickery_souls/2)"
  clamp_clone_count: 4
resonance_hooks:
  - condition: resonance:shadow_trickery within:14
    modify:
      clone_count_add: 1
      opening_strike_bonus_add: 0.10
conflict_tags: [stealth_field]
anti_abuse:
  stealth_reveal_on_damage: true
logging: BASIC
status: draft
---
## Summary
Locks mobility abilities to generate shadow clones and brief stealth, empowering the next attack.

## Mechanics
* Clones mirror basic attack every 1.5s (reduced damage 40%).
* Breaking stealth early still consumes cooldown.

## Scaling
Additional Trickery souls increase clone count (cap 4).

## Resonance Hooks
Shadow+Trickery adds a bonus clone and higher opening strike multiplier.

## Visuals
Dark mist swirl, ephemeral armorstand silhouettes flicker.

## Anti-Abuse & Conflicts
Stealth ends instantly on offensive action or damage taken >2 HP.

## Edge Cases
* If no attack within stealth window, opening strike bonus expires.
* Clones vanish if owner >40 blocks away.

## Implementation Notes
Clone scheduler shares owner combat tag; prevent infinite clone recursion.
