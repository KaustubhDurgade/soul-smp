# Wrath Base Passive: Burning Spirit

Code: P
Type: Passive
Tags: [Damage, Resource]

## Mechanics
+15% outgoing damage while below 60% max HP. Snapshot at attack wind-up (projectiles / swing start) to prevent mid-flight recalculation abuse.

## Interactions
- Heat Generation Synergy: Damage while buff active still contributes to Heat; no multiplier on Heat itself.
- Stacking: Additive with other % damage sources then multiplicative with global final modifiers.

## Edge Cases
- If max HP temporarily reduced (Rot), threshold recalculates off effective max.
- Does not apply to environmental (lava) damage reflection.
