---
id: mythic.greed+glory+pride.imperial_treasury
souls: [greed, glory, pride]
name: Imperial Treasury
tier: X
category: HYB
trigger: ULT(Pillage + Immortal Banner + Warbanner active)
version: 0.1.0
design_status: draft
---
### Overview
Tri-fusion creates Treasury zone generating a legendary coin granting temporary major stat boon when claimed.
### Trigger Conditions
All three ultimates overlap within 5s window.
### Scaling & Math
CoinBoon = +15% damage, -10% dmg taken, +10% CDR for 45s.
### Effects
Spawns single legendary coin entity (unique UUID) claimable once.
### Cooldowns & Limits
Global lockout 10m.
### Conflicts
Legendary Banner active merges aura (no duplicate buffs).
### Anti-Abuse
Coin despawns if unclaimed 60s.
### Implementation Notes
Fusion orchestrator gating global timer.
