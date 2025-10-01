---
id: glory+pride.legendary_banner
souls: [glory, pride]
name: Legendary Banner
tier: U
category: HYB
trigger: ULT(banner fusion)
version: 0.1.0
design_status: draft
---
### Overview
Immortal Banner + Warbanner fuse into empowered banner combining offensive and defensive auras.
### Trigger Conditions
Both ultimates active; origins within 6 blocks at cast.
### Scaling & Math
BuffAmp = 1 + 0.1*(participants-1) (cap 1.2).
### Effects
Unified aura: damage +8%, damage taken -8%, regen I.
### Cooldowns & Limits
Fusion lockout 180s.
### Conflicts
Legendary Paradox present -> add random secondary buff.
### Anti-Abuse
Only one fused banner world-wide.
### Implementation Notes
Fusion manager merges contexts.
