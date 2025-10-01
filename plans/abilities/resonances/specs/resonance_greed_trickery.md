---
id: greed+trickery.fools_gold
souls: [greed, trickery]
name: Fool's Gold
tier: F
category: CTRL
trigger: SEQ(pillage + clone present)
version: 0.1.0
design_status: draft
---
### Overview
Pillage while clone active applies fake buff icons to nearby enemies causing misreads.
### Trigger Conditions
Pillage cast and >=1 clone within 8 blocks.
### Scaling & Math
DebuffDuration = 6s (+2 if participants>1) cap 8.
### Effects
Applies UI deception (cooldown overlay mismatches).
### Cooldowns & Limits
Internal 30s.
### Conflicts
Deceptive Research modifies overlay randomness.
### Anti-Abuse
Players immune for 60s after cleanse.
### Implementation Notes
Client hint packet injection.
