---
id: curiosity+serenity.harmonic_observation
souls: [curiosity, serenity]
name: Harmonic Observation
tier: C
category: UTIL
trigger: PROX(<=10)
version: 1.0.0
design_status: draft
---
### Overview
Calm zone grants CDR & data stacks.
### Trigger Conditions
Curiosity inside Sanctuary radius.
### Scaling & Math
CDR = 0.04 participants (cap 0.08). DataStack per 8s.
### Effects
At 5 stacks: next Analyze free.
### Cooldowns & Limits
Stack window 60s.
### Conflicts
Resplendent Well -> double stack gain rate.
### Anti-Abuse
Leaving radius resets after 12s grace.
### Implementation Notes
Zone membership tracker.
