---
id: greed+silence.smuggled_void
souls: [greed, silence]
name: Smuggled Void
tier: F
category: UTIL
trigger: AREA(loot in silence)
version: 0.1.0
design_status: draft
---
### Overview
Items inside Silence Field phase out then reappear with improved value.
### Trigger Conditions
Item entity remains in Silence Field >=3s.
### Scaling & Math
ValueMult = 1.10 (1.15 if participants>1).
### Effects
On rephase item glows; coin conversion bonus.
### Cooldowns & Limits
Internal 36s field-level.
### Conflicts
Smother active disables value increase.
### Anti-Abuse
Blacklist high-tier artifacts.
### Implementation Notes
Item tick watch list.
