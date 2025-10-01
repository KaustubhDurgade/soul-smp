---
id: glory+order.chronicle_decree
souls: [glory, order]
name: Chronicle Decree
tier: T
category: UTIL
trigger: SEQ(decree inside banner)
version: 0.1.0
design_status: draft
---
### Overview
Casting Edict/Decree inside banner snapshots ally stat baselines for reactive restore.
### Trigger Conditions
Order ability resolves while banner radius contains both.
### Scaling & Math
SnapshotCount = 2 + (participants>1?1:0).
### Effects
After 6s restore lost %HP up to snapshot value (cap 20%).
### Cooldowns & Limits
Internal 24s.
### Conflicts
Still Time increases restore delay reliability (+2s window).
### Anti-Abuse
No snapshot if ally at full health.
### Implementation Notes
Snapshot store keyed by entity UUID.
