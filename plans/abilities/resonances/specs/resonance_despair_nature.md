---
id: despair+nature.fungal_ascent
souls: [despair, nature]
name: Fungal Ascent
tier: F
category: OFF
trigger: STATUS(rot tick triggers vines)
version: 0.1.0
design_status: draft
---
### Overview
Rot ticks may spawn grasping vine growth that briefly snares and deals minor poison.
### Trigger Conditions
Rot tick occurs while Nature soul within 10 blocks of afflicted target.
### Scaling & Math
ProcChance = 0.20 + 0.05*(participants-1) (cap 0.3).
### Effects
Spawn 1–2 vine tendrils (root 0.6s, poison I 2s).
### Cooldowns & Limits
Internal 24s target-based.
### Conflicts
Scorched Wilds converts poison to burn (retain duration).
### Anti-Abuse
Ignore if target already rooted in last 4s.
### Implementation Notes
Tick listener hooking Rot status pipeline.
