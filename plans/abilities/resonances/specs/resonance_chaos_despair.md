---
id: chaos+despair.haunting_echo
souls: [chaos, despair]
name: Haunting Echo
tier: T
category: CTRL
trigger: STATUS(fear proc + chaos ability w/in 1s)
version: 1.0.0
design_status: stable
---
### Overview
Fear applications reverberate as low-damage shadow pulses spreading minor wither.
### Trigger Conditions
Fear applied by Despair target while Chaos soul resolves Unstable Burst/Wild Surge within 1s and 10 blocks.
### Scaling & Math
PulseCount = 1 + floor(participants/2); WitherDuration += 0.5s per pulse (cap +2s).
### Effects
Emits shadow pulse (3 block radius) each 0.6s (max 4) applying Wither I (1.5s).
### Cooldowns & Limits
Internal CD 20s target-based.
### Conflicts
Silence field prevents new pulses but not ones queued.
### Anti-Abuse
Ignore if target already under >=Wither II from higher tier effect.
### Implementation Notes
Use StatusAppliedEvent listener with short temporal match window.
