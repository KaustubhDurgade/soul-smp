---
id: hope+trickery.radiant_veil
souls: [hope, trickery]
name: Radiant Veil
tier: F
category: DEF
trigger: STATUS(heal disguised ally)
version: 0.1.0
design_status: draft
---
### Overview
Healing disguised ally grants temporary damage shield.
### Trigger Conditions
Illusion/disguise flag present on healed ally.
### Scaling & Math
Shield = 3 + 0.5*(participants-1) HP (cap 5) for 6s.
### Effects
Applies barrier; clears on breaking.
### Cooldowns & Limits
Internal 28s per target.
### Conflicts
Veiled Sanctuary barrier merges (sum then clamp).
### Anti-Abuse
No shield stacking across multiple disguised states.
### Implementation Notes
Heal event checking disguise metadata.
