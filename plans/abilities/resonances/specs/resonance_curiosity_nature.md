---
id: curiosity+nature.symbiotic_research
souls: [curiosity, nature]
name: Symbiotic Research
tier: C
category: UTIL
trigger: PROX(<=10)
version: 1.0.0
design_status: draft
---
### Overview
Observing growth speeds plant ticks & grants insight stacks.
### Trigger Conditions
Both souls near crop growth tick location.
### Scaling & Math
GrowthMult = 1.10 (+0.05 if participants>1). InsightGain = 1 stack / 10s.
### Effects
Accelerates crops; at 5 insight stacks Curiosity gains minor CDR burst.
### Cooldowns & Limits
Passive; CDR burst 30s internal.
### Conflicts
Regimented Growth adds +0.05 GrowthMult instead of separate.
### Anti-Abuse
Ignores artificially forced random tick spam (server flagged).
### Implementation Notes
Crop tick listener + proximity filter.
