---
id: chaos+pride.warped_edict
souls: [chaos, pride]
name: Warped Edict
tier: F
category: CTRL
trigger: AREA(banner zone + chaos aura)
version: 1.0.0
design_status: draft
---
### Overview
Banner zone under chaos influence randomizes buff flips each tick (ally buff / enemy debuff).
### Trigger Conditions
Active Warbanner or Crown aura overlapping chaos aura (10 block) for >1s.
### Scaling & Math
FlipChance = 0.35 + 0.05*(participants-1) (cap 0.5). BuffAmp = 1 + 0.05*participants.
### Effects
Every 2s roll: grant allies minor stat buff OR apply enemy slow/weakness (mutually exclusive).
### Cooldowns & Limits
Internal 16s rolling window.
### Conflicts
Legendary Banner: disable flips, keep BuffAmp.
### Anti-Abuse
Track last 3 outcomes; prevent three identical flips.
### Implementation Notes
Aura merger; weighted roll memory buffer.
