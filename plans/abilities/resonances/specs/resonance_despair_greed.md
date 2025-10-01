---
id: despair+greed.rotting_avarice
souls: [despair, greed]
name: Rotting Avarice
tier: F
category: OFF
trigger: STATUS(rot death within loot aura)
version: 0.1.0
design_status: draft
---
### Overview
Enemies dying while under Rot inside Market Shift drop corruption coins (decay-charged currency) that can be spent for temporary offensive buffs.
### Trigger Conditions
Enemy has Rot >=3s remaining at death and dies within Market Shift radius.
### Scaling & Math
CoinCount = 1 + floor(RotTier/2) (cap 4). DamageBuff = 0.03 * coinsConsumed (cap 0.18, 12s).
### Effects
Spawn corruption coins (entity tag). Consuming grants stacking decay-as-fire conversion buff.
### Cooldowns & Limits
Internal 30s per killer pair; coins expire 15s.
### Conflicts
Volatile Ledger active: convert first coin into nova (no buff).
### Anti-Abuse
Boss/miniboss limited to 1 coin.
### Implementation Notes
DeathEvent hook filtering status & location.
