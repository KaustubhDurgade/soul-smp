---
id: greed+nature.gilded_harvest
souls: [greed, nature]
name: Gilded Harvest
tier: C
category: UTIL
trigger: PROX(gathering)
version: 0.1.0
design_status: draft
---
### Overview
Resource gathering near Nature soul yields occasional bonus rare drops converted to coin value.
### Trigger Conditions
Harvest action (crop/mineral) within 10 blocks of Nature.
### Scaling & Math
RareRoll +5%; CoinValue = base * 1.05.
### Effects
Spawns gilded particle + coin entity (temp).
### Cooldowns & Limits
Passive; per-node once.
### Conflicts
Regimented Growth -> +2% extra rare chance.
### Anti-Abuse
Ignores artificially regenerated blocks flagged by server.
### Implementation Notes
BlockBreakEvent filter.
