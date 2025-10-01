---
id: greed+glory.spoils_of_renown
souls: [greed, glory]
name: Spoils of Renown
tier: C
category: UTIL
trigger: PROX(shared combat)
version: 0.1.0
design_status: draft
---
### Overview
Shared combat proximity yields incremental coin + renown trickle on kill assists.
### Trigger Conditions
Both souls within 12 blocks when enemy dies granting XP.
### Scaling & Math
CoinChance = 0.25; RenownPulse = 1 ( +1 if participants>2 group ).
### Effects
Drops bonus coin entity; applies small renown to Glory.
### Cooldowns & Limits
Tick evaluation 10s; each death limited once.
### Conflicts
Hunt of Legends adds +5% CoinChance.
### Anti-Abuse
No farm credit if same mob type >10 in 60s.
### Implementation Notes
DeathEvent aggregator.
