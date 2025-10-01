---
id: despair+trickery.phantasmal_terror
souls: [despair, trickery]
name: Phantasmal Terror
tier: F
category: CTRL
trigger: SEQ(clone death then fear)
version: 0.1.0
design_status: draft
---
### Overview
Illusion death primes a terror pulse on next fear application.
### Trigger Conditions
Trickery clone destroyed within 4s prior to Despair fear proc.
### Scaling & Math
ExtraFearDuration = 0.5s (+0.2 if participants>1) cap 1s.
### Effects
Fear spreads to up to 2 additional nearby enemies.
### Cooldowns & Limits
Internal 30s.
### Conflicts
Dread Command fear pulse does not prime this.
### Anti-Abuse
Chain fear immunity after second spread.
### Implementation Notes
Clone death queue + fear event.
