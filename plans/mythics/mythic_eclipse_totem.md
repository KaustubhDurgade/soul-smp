---
id: mythic.eclipse_totem
name: Eclipse Totem
mythic_type: WORLD_OBJECT
tier: 3
activation_mode: WORLD_DEPLOY
acquisition_path: CRAFT_INFUSION
soul_focus: [Shadow, Silence]
cooldown_seconds: 600
active_window_seconds: 15
world_object:
  deploy_time_seconds: 3
  aura_radius: 14
  enemy_healing_reduction: 0.40
  ally_shadow_silence_amp: 0.15
scaling:
  shadow_souls_radius_per: 1 (cap 4)
resonance_hooks:
  - condition: resonance:shadow_silence active_within:16
    modify:
      ally_shadow_silence_amp_add: 0.05
visual:
  model: world/eclipse_totem
  aura: dark_dome
  activation_burst: void_wave
anti_abuse:
  region_unique_anchor: true
attunement:
  exclusive_slot: world_anchor
logging: FULL
status: draft
---
## Summary
Deployed totem suppressing enemy healing and amplifying nearby shadow/silence effects.

## Mechanics
Persistent aura for window, then despawn.

## Scaling
Shadow souls marginally expand radius.

## Resonance Hooks
Shadow+Silence adds further amplification.

## Visuals
Dark dome with rotating runic rings.

## Anti-Abuse
Region uniqueness prevents stacking fields.

## Edge Cases
* If placed inside protected region, deny with refund.

## Implementation Notes
Anchor registrar tracks world anchors by chunk group.
