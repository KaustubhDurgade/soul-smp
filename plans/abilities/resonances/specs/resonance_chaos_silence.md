---
id: chaos+silence.void_quiet
souls: [chaos, silence]
name: Void Quiet
tier: C
category: CTRL
trigger: PROX(<=8)
version: 1.0.0
design_status: draft
---
### Overview
Chaos effects subdued near Silence; sporadic buff stripping pulses.
### Trigger Conditions
Both souls within 8 blocks for >2s.
### Scaling & Math
StripChance = 0.18 baseline; +0.04 if participants>2 (group scenario).
### Effects
Every 6s: attempt remove 1 minor random buff from nearby enemies (radius 5).
### Cooldowns & Limits
Internal 6s tick; no separate cooldown.
### Conflicts
Absolute Judgment active nullifies strip.
### Anti-Abuse
Won't strip same target twice consecutively (needs 1 intervening target).
### Implementation Notes
Proximity graph edge evaluation.
