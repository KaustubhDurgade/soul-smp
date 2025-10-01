---
id: mythic.celestial_compass
name: Celestial Compass
mythic_type: WORLD_OBJECT
tier: 2
activation_mode: ACTIVE_WINDOW
acquisition_path: CRAFT_INFUSION
soul_focus: ANY
cooldown_seconds: 300
active_window_seconds: 20
world_object:
  biome_field_type: elemental
  radius: 18
  elemental_amp: 0.12
scaling:
  elemental_resonance_count_amp_per: 0.03 (cap_add: 0.12)
resonance_hooks:
  - condition: elemental_resonance_count>=3 within:20
    modify:
      elemental_amp_add: 0.05
visual:
  model: world/celestial_compass
  aura: prismatic_field
anti_abuse:
  field_overlap_limit: 1
attunement:
  exclusive_slot: world_anchor
logging: EXTENDED
status: draft
---
## Summary
Deploys temporary biome-aligned elemental amplification field.

## Mechanics
Amplifies elemental ability output inside radius.

## Scaling
Elemental resonance count increases amp (cap).

## Resonance Hooks
≥3 resonances boost amp further.

## Visuals
Compass rose display with rotating elemental inlays.

## Anti-Abuse
Only one such field can overlap region.

## Edge Cases
* If radius crosses protected claim, clamp effect area.

## Implementation Notes
Applies world biome override token for duration.
