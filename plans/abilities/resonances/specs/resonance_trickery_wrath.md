---
id: trickery+wrath.fiendish_feint
souls: [trickery, wrath]
name: Fiendish Feint
tier: F
category: OFF
trigger: SEQ(clone burst + fire)
version: 0.1.0
design_status: draft
---
### Overview
Clone burst primes delayed flame strike on target.
### Trigger Conditions
Clone detonation within 1s of Wrath damaging ability.
### Scaling & Math
StrikeDamage = 3 + 0.5*(participants-1); Delay 1.5s.
### Effects
Schedules delayed strike at target location.
### Cooldowns & Limits
Internal 26s.
### Conflicts
Mirror Madness explosion consolidated (no double detonation).
### Anti-Abuse
One pending strike per target.
### Implementation Notes
Event join + scheduler.
