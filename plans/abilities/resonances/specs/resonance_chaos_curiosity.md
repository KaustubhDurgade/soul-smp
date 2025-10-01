---
id: chaos+curiosity.experimental_flux
souls: [chaos, curiosity]
name: Experimental Flux
tier: T
category: HYB
trigger: SEQ(ability within 0.9s)
version: 1.0.0
design_status: stable
---
### Overview
Biases chaos random table toward beneficial outcomes while Curiosity analyzes nearby events.
### Trigger Conditions
Cast Wild Surge or Unstable Burst within 0.9s of Arcane Experiment / Analyze inside 12 block radius.
### Scaling & Math
BenefitBias = min(0.45, 0.25 + 0.05 * ParticipantsBeyond1)
CDRBonus = 0.04 * participants (clamped 0.12).
### Effects
Adjust chaos roll weight distribution; grant +Insight stack (max 5) per activation.
### Cooldowns & Limits
Internal CD: 18s per pair. Shared chaos resonance lock: 6s.
### Conflicts
If Temporal Index active, combine bias (sum then clamp). No stacking with Mirror Madness bias.
### Anti-Abuse
Log activations; if >5/min reduce bias by 50% remainder minute.
### Implementation Notes
Hook: AbilityResolveEvent buffer; apply bias context until next chaos roll.
