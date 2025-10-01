---
id: serenity+silence.hollow_tranquility
souls: [serenity, silence]
name: Hollow Tranquility
tier: C
category: DEF
trigger: PROX(projectiles through silence)
version: 0.1.0
design_status: draft
---
### Overview
Silence field reduces projectile speed; Sanctuary synergy lowers further.
### Trigger Conditions
Projectile enters Silence Field while Serenity within 10 blocks.
### Scaling & Math
SpeedMult = 0.8 (0.7 if Sanctuary present).
### Effects
Applies slow to projectile velocity update.
### Cooldowns & Limits
Passive; per projectile once.
### Conflicts
Still Time further reduces to 0.6 (cap).
### Anti-Abuse
Cap large AoE projectiles min speed 0.5.
### Implementation Notes
Projectile tick wrapper.
