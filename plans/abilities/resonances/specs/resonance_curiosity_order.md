---
id: curiosity+order.temporal_index
souls: [curiosity, order]
name: Temporal Index
tier: F
category: UTIL
trigger: AREA(rewind near analyze)
version: 1.0.0
design_status: draft
---
### Overview
Rewind near Analyze snapshots buff states for later restoration.
### Trigger Conditions
Rewind cast within 8 blocks of Curiosity performing Analyze in last 2s.
### Scaling & Math
SnapshotCap = 3 + (participants>1?1:0).
### Effects
Stores up to SnapshotCap active positive buffs; on expiry reapply at 60% duration.
### Cooldowns & Limits
Internal 45s.
### Conflicts
Still Time active -> reapply at 80%.
### Anti-Abuse
No stacking identical stored buff types.
### Implementation Notes
Buff snapshot data structure keyed by resonance id.
