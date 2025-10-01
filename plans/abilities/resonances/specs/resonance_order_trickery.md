---
id: order+trickery.regulated_illusion
souls: [order, trickery]
name: Regulated Illusion
tier: C
category: CTRL
trigger: PROX(order near clones)
version: 0.1.0
design_status: draft
---
### Overview
Order stabilizes clone durations making them predictable and slightly weaker.
### Trigger Conditions
Order within 8 blocks of >=1 clone.
### Scaling & Math
DurationSet = 6s (was variable 4–8). CloneDamage -10%.
### Effects
Applies regulated tag to clones.
### Cooldowns & Limits
Tick 8s.
### Conflicts
Mirror Madness explosion unaffected.
### Anti-Abuse
Limits clone network size tracking.
### Implementation Notes
Clone tick modification.
