---
id: chaos+nature.mutagenic_overgrowth
souls: [chaos, nature]
name: Mutagenic Overgrowth
tier: F
category: OFF
trigger: STATUS(storm strike mutates)
version: 1.0.0
design_status: draft
---
### Overview
Nature’s Wrath strikes cause random thorn growth bursts with altered effects.
### Trigger Conditions
Storm tick deals damage while chaos soul within 14 blocks.
### Scaling & Math
ProcChance = 0.22 + 0.04 * (participants-1) (cap 0.34); GrowthDamage = 2 + 0.5*participants.
### Effects
Spawn 3–5 thorn eruptions (small bleed) randomly; 10% chance one is empowered pull bud.
### Cooldowns & Limits
Per-storm internal 15s.
### Conflicts
Scorched Wilds converts bleed to burn if active.
### Anti-Abuse
Limit empowered buds to 1 per minute.
### Implementation Notes
Storm tick hook; random scheduler seeded per storm.
