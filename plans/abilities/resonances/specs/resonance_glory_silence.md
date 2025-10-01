---
id: glory+silence.quiet_triumph
souls: [glory, silence]
name: Quiet Triumph
tier: C
category: UTIL
trigger: PROX(silence edge)
version: 0.1.0
design_status: draft
---
### Overview
Silence field edges suppress enemy "victory" effects (on-kill triggers) and grant subdued renown.
### Trigger Conditions
Glory within 4 blocks of Silence perimeter.
### Scaling & Math
RenownPulse = 1 every 10s ( +1 if participants>1 ).
### Effects
Blocks enemy kill-heal triggers in zone.
### Cooldowns & Limits
Tick 10s.
### Conflicts
Absolute Judgment extends block effect +2s after leaving.
### Anti-Abuse
Does not suppress quest triggers.
### Implementation Notes
Perimeter ring evaluation.
