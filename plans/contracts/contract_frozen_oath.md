---
id: contract.frozen_oath
name: Frozen Oath
tier: 2
class: CONTROL
scope: AREA
sacrifice:
  type: ITEM_AND_MOBILITY
  item_tag: frost_blade
  mobility_lock_seconds: 4
activation:
  mode: INSTANT
  cast_time_ticks: 15
  cooldown_seconds: 160
effects:
  field_radius: 9
  freeze_duration_seconds: 2.5
  slow_after_freeze: 0.30
  slow_duration_seconds: 4
scaling:
  formula: "freeze_duration = base + (silence_souls * 0.25)"
  clamp_freeze: 3.5
resonance_hooks:
  - condition: resonance:frost_silence within:12
    modify:
      freeze_duration_add: 1.0
conflict_tags: [control_freeze]
anti_abuse:
  freeze_immunity_after: 8
logging: EXTENDED
status: draft
---
## Summary
Sacrifice mobility and a frost item to project a chilling field that briefly freezes enemies then slows them.

## Mechanics
* On activation, player rooted for mobility_lock_seconds.
* Field snapshot persists for duration (implicit from freeze + slow tail ~6.5s).

## Scaling
Extra Silence souls extend freeze duration (cap 3.5s).

## Resonance Hooks
Frost+Silence → +1s freeze (still capped).

## Visuals
Icy rune ring, falling shard particles, faint mist dome.

## Anti-Abuse & Conflicts
Per-target freeze immunity for 8s after effect end.

## Edge Cases
* Entities already frozen refresh remaining shorter or extend if lower.
* Boss-class flagged as "freeze_resistant": treat as 40% slow instead.

## Implementation Notes
Use per-entity metadata for immunity timestamp.
