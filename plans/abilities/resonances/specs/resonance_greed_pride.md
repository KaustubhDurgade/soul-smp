---
id: greed+pride.tithe_of_glory
souls: [greed, pride]
name: Tithe of Glory
tier: F
category: UTIL
trigger: AREA(banner contribution)
version: 0.1.0
design_status: draft
---
### Overview
Allies donate coins in Warbanner radius granting stacking team buffs.
### Trigger Conditions
Coin pickup inside active Warbanner.
### Scaling & Math
BuffPerCoin = +0.5% dmg (cap 8%). Decay begins after 20s idle.
### Effects
Applies Tithe aura; displays stack bar to allies.
### Cooldowns & Limits
Internal 34s after aura expires.
### Conflicts
Legendary Banner doubles cap but halves decay delay.
### Anti-Abuse
Rejects artificially spawned coins (debug tag).
### Implementation Notes
Aura context with stack timer.
