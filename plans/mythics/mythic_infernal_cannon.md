---
id: mythic.infernal_cannon
name: Infernal Cannon
mythic_type: WEAPON
tier: 3
activation_mode: ACTIVE_WINDOW
acquisition_path: CONTRACT_FUSION
soul_focus: [Flame]
cooldown_seconds: 300
active_window_seconds: 12
passives:
  burn_duration_mult: 1.25
active:
  projectile_damage: 22
  explosion_radius: 5
  attack_interval: 1.2
scaling:
  flame_souls_damage_per: 2 (cap_add: 8)
resonance_hooks:
  - condition: resonance:flame_wind active_within:20
    modify:
      spawn_fire_tornado_chance: 0.25
visual:
  model: weapon/infernal_cannon
  projectile_trail: ember_comet
  explosion_effect: flame_burst
anti_abuse:
  tornado_spawn_icd_seconds: 4
attunement:
  exclusive_slot: weapon_core
logging: FULL
status: draft
---
## Summary
Heavy fire weapon launching explosive projectiles with chance to spawn fire tornadoes.

## Mechanics
Semi-automatic firing each 1.2s during window.

## Scaling
Flame souls add damage (cap +8).

## Resonance Hooks
Flame+Wind gives tornado proc chance with ICD.

## Visuals
Bright ember projectiles leaving smoke trails.

## Anti-Abuse
Internal cooldown on tornado spawns.

## Edge Cases
* Explosion damage falloff linear from center (20% at edge).

## Implementation Notes
Proc manager enforces tornado ICD.
