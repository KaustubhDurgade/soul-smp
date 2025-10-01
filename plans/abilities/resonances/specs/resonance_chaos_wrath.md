---
id: chaos+wrath.fire_tornado
souls: [chaos, wrath]
name: Fire Tornado
tier: T
category: OFF
trigger: SEQ(dash/chain + chaos proc <=0.9s)
version: 1.0.0
design_status: stable
---
### Overview
Spiral pull + burning vortex forms from chained activation.
### Trigger Conditions
Wrath mobility (Inferno Chain Surge/Ash Glide/Frenzy Leap) within 0.9s of Unstable Burst/Wild Surge.
### Scaling & Math
Radius = 4 + 1.2*(participants-1); TickDmg = 2 + 0.5*(participants-1) (fire).
### Effects
Vortex pulls 0.18 strength; applies burn DoT every 10 ticks (duration 120 ticks).
### Cooldowns & Limits
Internal 20s pair; max 1 active.
### Conflicts
Scorched Wilds present -> +10% tick dmg instead of new vortex.
### Anti-Abuse
Player cannot trigger twice inside 8s even if cooldown bypass.
### Implementation Notes
State machine entity w/ tick scheduler.
