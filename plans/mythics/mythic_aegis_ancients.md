---
id: mythic.aegis_ancients
name: Aegis of the Ancients
mythic_type: ARMOR
tier: 2
activation_mode: PASSIVE_ONLY
acquisition_path: CRAFT_INFUSION
soul_focus: ANY
passives:
  projectile_reflect_chance: 0.30
  damage_taken_reduction: 0.08
scaling:
  defensive_resonances_reflect_bonus_per: 0.05 (cap 0.50)
resonance_hooks:
  - condition: resonance:order_nature active_within:12
    modify:
      damage_taken_reduction_add: 0.04
visual:
  model: armor/aegis_chest
  shield_aura: golden_pulse
anti_abuse:
  reflect_proc_icd_ms: 400
attunement:
  exclusive_slot: armor_core
logging: BASIC
status: draft
---
## Summary
Defensive chestpiece reflecting projectiles and reducing damage.

## Mechanics
On reflect check, projectile redirected with reduced travel speed.

## Scaling
Defensive resonances increase reflect chance (cap 50%).

## Resonance Hooks
Order+Nature adds further damage reduction.

## Visuals
Golden protective pulses & shimmering arcs.

## Anti-Abuse
Internal proc cooldown to prevent rapid reflect spam.

## Edge Cases
* Splash projectiles reflect only center ray.

## Implementation Notes
Tag reflected projectiles to avoid recursion.
