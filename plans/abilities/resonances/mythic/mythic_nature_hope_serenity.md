---
id: mythic.nature+hope+serenity.world_bloom
souls: [nature, hope, serenity]
name: World Bloom
tier: X
category: HYB
trigger: ULT(Storm + Beacon + Sanctuary)
version: 0.1.0
design_status: draft
---
### Overview
Terrain flowers granting temporary interact buffs (speed, regen, thorns) while zone persists.
### Trigger Conditions
Three ultimates overlap >15% area.
### Scaling & Math
FlowerCount = 8 + 2*(participants>3?1:0); BuffDuration 25s.
### Effects
Interact to gain one random minor buff (once each).
### Cooldowns & Limits
Global lockout 12m.
### Conflicts
Resplendent Well reduces buff randomness (weighted toward defense).
### Anti-Abuse
Limit 64 tile modifications.
### Implementation Notes
Temporary block replacements revert on end.
