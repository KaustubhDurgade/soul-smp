---
id: curiosity+greed.alchemical_mint
souls: [curiosity, greed]
name: Alchemical Mint
tier: T
category: UTIL
trigger: AREA(transmute near market shift)
version: 1.0.0
design_status: stable
---
### Overview
Transmute near Market Shift zone spawns transmuted coin items.
### Trigger Conditions
Transmute/Flask/AOE alchemy effect center inside Market Shift radius.
### Scaling & Math
CoinCount = 2 + floor(WealthTier/2) (cap 6). Each coin value +5% if participants>1.
### Effects
Spawn coin entities (collect -> wealth resource). 10% chance rare reagent.
### Cooldowns & Limits
Internal 30s; coin lifetime 20s.
### Conflicts
Volatile Ledger active -> reduce coin count by 30% (explosive energy taken).
### Anti-Abuse
Despawns if pocket dimension exploit detected (vertical >20 blocks difference).
### Implementation Notes
Zone membership + transmute event hook.
