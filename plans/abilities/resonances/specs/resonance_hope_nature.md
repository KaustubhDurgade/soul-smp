---
id: hope+nature.verdant_dawn
souls: [hope, nature]
name: Verdant Dawn
tier: F
category: HYB
trigger: AREA(beacon + storm)
version: 0.1.0
design_status: draft
---
### Overview
Beacon overlapping Nature’s Wrath adds growth pulses healing & rooting enemies lightly.
### Trigger Conditions
Area overlap >15%.
### Scaling & Math
PulseHeal = 2 + 0.5*(participants-1); Root 0.4s.
### Effects
Pulses every 4s (3 pulses baseline).
### Cooldowns & Limits
Internal 26s.
### Conflicts
Resplendent Well converts root -> barrier.
### Anti-Abuse
Root immunity 10s per target.
### Implementation Notes
Overlap scheduler.
