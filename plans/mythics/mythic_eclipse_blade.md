---
id: mythic.eclipse_blade
name: Eclipse Blade
mythic_type: WEAPON
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: CRAFT_INFUSION
soul_focus: [Shadow, Silence]
cooldown_seconds: 240
active_window_seconds: 10
passives:
  damage_amp_shadow: 0.15
  silence_effect_duration_add: 0.5
active:
  slash_radius: 6
  slash_damage: 18
  bonus_vs_silenced_mult: 1.25
scaling:
  trickery_souls_bonus_per: 0.02 (cap 0.08)
resonance_hooks:
  - condition: resonance:shadow_trickery active_within:16
    modify:
      slash_radius_add: 2
      damage_amp_shadow_add: 0.05
visual:
  model: weapon/eclipse_blade
  swing_trail: dark_arc
  activation_burst: shadow_flash
anti_abuse:
  silence_extension_cap: 1.5
attunement:
  exclusive_slot: weapon_core
logging: EXTENDED
status: draft
---
## Summary
Hybrid burst blade amplifying shadow/silence effects with an active cleave window.

## Mechanics
Active window enables radial cleave every 1.5s up to 3 times.

## Scaling
Trickery souls increase passive damage amp.

## Resonance Hooks
Shadow+Trickery adds radius and more shadow amp.

## Visuals
Dark energy arcs, occasional silence glyph fragments.

## Anti-Abuse
Silence duration additive contributions capped globally.

## Edge Cases
* If target already silenced, apply bonus damage but not refresh extension beyond cap.

## Implementation Notes
Window manager schedules cleave charges; respects global silence cap.
