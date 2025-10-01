---
id: despair+glory.dirge_of_legends
souls: [despair, glory]
name: Dirge of Legends
tier: F
category: HYB
trigger: AREA(darkness within banner)
version: 0.1.0
design_status: draft
---
### Overview
Banner radius infused with despair reduces enemy outgoing damage and amplifies Glory renown accrual.
### Trigger Conditions
Active Immortal Banner inside Plague Field / Dread darkness zone >20% overlap.
### Scaling & Math
EnemyDmgDown = 0.08 + 0.02*(participants-1) (cap 0.14). RenownGainMult = 1 + 0.15.
### Effects
Aura debuff + party renown buff while overlap persists.
### Cooldowns & Limits
Reapply tick every 3s; internal re-trigger grace 10s after separation.
### Conflicts
Legendary Banner upgrades RenownGainMult to +0.25.
### Anti-Abuse
PvE mobs cap debuff at 6%.
### Implementation Notes
Overlap tracker of aura volumes.
