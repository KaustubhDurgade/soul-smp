---
id: glory+nature.hunt_of_legends
souls: [glory, nature]
name: Hunt of Legends
tier: F
category: OFF
trigger: SEQ(stampede/arena kill log)
version: 0.1.0
design_status: draft
---
### Overview
Kills during Stampede or Colosseum log legacy stacks buffing future summons.
### Trigger Conditions
Enemy killed inside Nature storm or Glory arena.
### Scaling & Math
LegacyStacks +=1 (cap 15). SummonBuff = 1 + 0.02*Stacks.
### Effects
Increases pet/minion damage output.
### Cooldowns & Limits
Internal 30s decay timer (lose 1 stack).
### Conflicts
Blazing Standard adds +1 initial stack.
### Anti-Abuse
Identical mob farming beyond 10 reduces gain.
### Implementation Notes
Kill attribution context.
