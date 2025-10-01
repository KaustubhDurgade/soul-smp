---
id: serenity+trickery.veiled_sanctuary
souls: [serenity, trickery]
name: Veiled Sanctuary
tier: F
category: DEF
trigger: AREA(sanctuary cloak edge)
version: 0.1.0
design_status: draft
---
### Overview
Sanctuary edge cloaks allies entering granting brief invis & damage reduction.
### Trigger Conditions
Ally crosses Sanctuary boundary with clone/disguise event nearby.
### Scaling & Math
InvisDur = 2s ( +1 if participants>1 ). DR = 0.12.
### Effects
Applies invis + DR buff; broken on attack.
### Cooldowns & Limits
Internal 30s per ally.
### Conflicts
Radiant Veil merges (use higher DR then apply shield separately).
### Anti-Abuse
Invis not applied while in combat tag (recent hit <1s).
### Implementation Notes
Boundary crossing detection.
