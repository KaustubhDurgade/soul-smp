---
id: chaos+greed.volatile_ledger
souls: [chaos, greed]
name: Volatile Ledger
tier: F
category: OFF
trigger: AREA(rift + coin field overlap)
version: 1.0.0
design_status: draft
---
### Overview
Riftstorm overlapping Market Shift coin vacuum generates explosive coin novas.
### Trigger Conditions
Active Riftstorm AoE intersects Market Shift zone >20% area for >=0.5s.
### Scaling & Math
NovaDamage = 3 + 0.6 * min(5, coinsAbsorbed/5); Radius = 3 + 0.3 * participants.
### Effects
Every 1.2s spawn coin nova (fire dmg + knockback). Consumes 5 temporary coin spirits.
### Cooldowns & Limits
Internal CD 30s per pair. Max 3 novas per activation.
### Conflicts
If Blazing Standard present, convert fire type to pure kinetic.
### Anti-Abuse
Caps coins consumed per activation to 25.
### Implementation Notes
AOI intersection tracker; coin spirits ephemeral entity list.
