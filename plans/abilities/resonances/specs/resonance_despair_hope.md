---
id: despair+hope.twilight_reassurance
souls: [despair, hope]
name: Twilight Reassurance
tier: C
category: HYB
trigger: PROX(light near decay)
version: 0.1.0
design_status: draft
---
### Overview
Hope’s light steadies allies affected by fear/rot reducing escalation speed.
### Trigger Conditions
Hope soul within 8 blocks of ally under Fear or Rot.
### Scaling & Math
EscalationRateMult = 0.5; WitherTickDmgMult = 0.9 if participants>1.
### Effects
Applies soothing aura tag reducing new fear stack chance.
### Cooldowns & Limits
Passive tick every 6s.
### Conflicts
Muted Suffering: use lower damage modifier only once (non-stacking).
### Anti-Abuse
Does not reduce scripted boss fear events.
### Implementation Notes
Status evaluation in proximity graph.
