---
id: nature+trickery.camouflaged_bramble
souls: [nature, trickery]
name: Camouflaged Bramble
tier: F
category: CTRL
trigger: STATUS(decoy vine snare)
version: 0.1.0
design_status: draft
---
### Overview
Trickery decoys sprout brambles slowing pursuers.
### Trigger Conditions
Decoy / clone destroyed while Nature soul nearby (<=10).
### Scaling & Math
Slow = 0.3 (0.4 if participants>1) 3s.
### Effects
Plants bramble patch (radius 4) applying slow.
### Cooldowns & Limits
Internal 26s.
### Conflicts
Camouflaged patch counts as vine for Vine Lash synergy.
### Anti-Abuse
Limit to 3 active patches world per pair.
### Implementation Notes
Clone death event -> patch spawn.
