---
id: order+wrath.tempered_furnace
souls: [order, wrath]
name: Tempered Furnace
tier: F
category: OFF
trigger: STATUS(chain on burning target)
version: 0.1.0
design_status: draft
---
### Overview
Chains applied to burning targets regulate fire into steady higher DPS dot.
### Trigger Conditions
Target burning when Binding Chains / Iron Bind applied.
### Scaling & Math
DoTIncrease = +15% fire over 6s (refresh not stack).
### Effects
Replace burst burn ticks with smooth damage curve.
### Cooldowns & Limits
Internal 30s per target.
### Conflicts
Fire Tornado active -> Increase curve duration +2s not damage.
### Anti-Abuse
No multiple curve overlays.
### Implementation Notes
Status converter wrapper.
