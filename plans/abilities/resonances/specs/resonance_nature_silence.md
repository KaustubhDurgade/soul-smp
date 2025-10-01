---
id: nature+silence.stilled_wilds
souls: [nature, silence]
name: Stilled Wilds
tier: C
category: CTRL
trigger: PROX(silence in wildlife zone)
version: 0.1.0
design_status: draft
---
### Overview
Silence presence calms hostile animals preventing aggression.
### Trigger Conditions
Silence field active within 10 blocks of wildlife mob.
### Scaling & Math
AggroSuppressionChance = 1.0 while active.
### Effects
Mobs lose target & remain neutral.
### Cooldowns & Limits
Passive.
### Conflicts
Absolute Judgment sets suppression ignoring range.
### Anti-Abuse
Excludes custom boss pets.
### Implementation Notes
Mob AI tick hook.
