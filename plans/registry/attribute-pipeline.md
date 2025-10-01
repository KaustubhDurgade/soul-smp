# Attribute & Effect Resolution Pipeline

Order of operations ensures deterministic stacking across systems.

1. Base Soul Kit
2. Soul Path Modifiers (if applicable)
3. Active Resonances (pairwise) – apply additive stat contributions
4. Resonance Derived Multipliers (category-based e.g., OFF/DEF) – multiplicative layer A
5. Contracts (active) – additive stats then multiplicative buffs (ordered by tier asc)
6. Items & Mythics Passives – additive stat pools
7. Items & Mythics Actives – multiplicative layer B (window-based)
8. Events (global/regional) – macro environment multipliers (multiplicative layer C)
9. Transformation Overrides – replace ability definitions (no retroactive buff stacking)
10. Diminishing & Conflict Resolution Pass – strongest_only, additive_cap trims
11. Clamp & Cap Enforcement – per attribute, log any clamped deltas
12. Final Snapshot Cache – used for damage / healing / control computations

## Example (Damage Amp Calculation)
```
base_damage = ability.base
resonance_add = Σ resonance.flat_damage
contract_add = Σ contract.damage_amp_flat
item_add = Σ item.damage_amp_flat
add_total = base_damage * (1 + resonance_add + contract_add + item_add)

mult_total = add_total
  * (1 + resonance_mult_layerA)
  * (1 + contract_mult_layerB)
  * (1 + event_mult_layerC)
  * (1 + mythic_active_mult)

final_damage = clamp(mult_total, damage_cap)
```

## Conflict Handling
- strongest_only: keep highest scalar, others logged as suppressed
- additive_cap: sum until cap, overflow discarded (logged)
- diminishing_sequence: apply sequence multipliers in descending source priority
- multiplicative_post_cap: if additive threshold exceeded, further contributions become multiplicative with reduced effectiveness

## Performance Notes
- Cache intermediate pools keyed by (player_id, tick) to avoid recomputation for multiple abilities inside same tick batch.
- Dirty flags: set when a layer changes (e.g., contract expiry) to invalidate snapshot.

## Telemetry Fields
- clamp_hits{attribute}
- conflict_suppressed_count{tag}
- diminishing_application_count{tag}
- snapshot_recompute_ms

End of pipeline spec.
