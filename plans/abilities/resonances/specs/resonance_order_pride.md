---
id: order+pride.edict_of_command
souls: [order, pride]
name: Edict of Command
tier: T
category: CTRL
trigger: STATUS(duel mark inside edict)
version: 0.1.0
design_status: draft
---
### Overview
Edict amplifies duel focus forcing target to prioritize marked opponent.
### Trigger Conditions
Duel mark applied while inside Edict radius.
### Scaling & Math
CDIncrease = 0.1 ability CDs if ignoring duel target.
### Effects
Applies enforcement debuff monitoring target actions.
### Cooldowns & Limits
Internal 24s target.
### Conflicts
Absolute Judgment overrides enforcement with hard silence.
### Anti-Abuse
Ignores forced targeting by bosses.
### Implementation Notes
Action monitor listener.
