# Wrath Base Ultimate: Meteorfall

Code: U
Cooldown: 105s
Tags: [Damage, Area]

## Mechanics
Target 12-block radius (cast range 20). Sequence over 3s:
- 5 meteors staggered (every 0.6s) each impacting for 6 HP AoE radius 2 & igniting ground 3s.
- Center point may be struck by at most 2 meteors (positional variance algorithm ensures spread).
- Single target damage cap: 18 HP pre-mitigation (apply diminishing beyond 3rd hit to 50%).

## Resource Interaction
On cast: Clear (vent) 40% of current Heat (rounded down to nearest 5) providing slight self-balance to sustain builds.

## Visual / Audio
- Red sky tint pulse.
- Each impact: explosion particle (non-block-destroy) + ember spiral.

## Counterplay
- Mobility out of predicted circles (telegraph 0.4s scorch marker per meteor).
- Fire resistance mitigates ground DoT (but not Cinder path true components).
