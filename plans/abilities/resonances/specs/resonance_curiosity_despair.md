---
id: curiosity+despair.forbidden_analysis
souls: [curiosity, despair]
name: Forbidden Analysis
tier: T
category: UTIL
trigger: SEQ(analyze on withered target)
version: 1.0.0
design_status: draft
---
### Overview
Analyzing a withered foe exposes advanced statistics & reap window.
### Trigger Conditions
Target currently under Wither (>=2s remaining) when Analyze resolves.
### Scaling & Math
ReapVuln = 0.06 + 0.02*(participants-1) duration 4s.
### Effects
Display extra panel; target takes +ReapVuln damage from execute-type abilities.
### Cooldowns & Limits
Internal 25s target scope.
### Conflicts
Haunting Echo fear pulses don't increase vuln stacking.
### Anti-Abuse
One active vulnerability marker per target.
### Implementation Notes
Overlay UI injection + metadata tag.
