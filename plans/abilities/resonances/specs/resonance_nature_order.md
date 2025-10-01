---
id: nature+order.regimented_growth
souls: [nature, order]
name: Regimented Growth
tier: C
category: UTIL
trigger: PROX(structured crops)
version: 0.1.0
design_status: draft
---
### Overview
Order’s influence increases crop growth rate along structured rows.
### Trigger Conditions
Row of >=4 identical crop blocks adjacent when Nature near (<=8).
### Scaling & Math
GrowthMult = 1.12 (1.17 if participants>1).
### Effects
Server applies accelerated random ticks.
### Cooldowns & Limits
Tick 12s.
### Conflicts
Symbiotic Research adds +0.03 instead of separate.
### Anti-Abuse
Detect unnatural mass replant cycles.
### Implementation Notes
Crop row scan each interval.
