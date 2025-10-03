# 🔐 Contract System – Structured Specification (Step 1.0 → 2.0 Ready)

Contracts are intentional “equivalent exchange” actions that convert player (or group) sacrifice into temporally bounded world / combat / support effects. They form a bridge between static soul kits and dynamic multi-soul emergent play.

---

## 0. Design Pillars
* Clear Inputs → Predictable Outputs (explicit sacrifice, deterministic math)
* Spectacle With Restraint (flashy but bounded; can’t trivialize core gameplay loops)
* Multi-Soul Leverage (group contracts always feel better than selfish spam)
* Registry-Driven (no hard-coded snowflakes; everything flows through a unified schema)
* Anti-Abuse First (internal cooldowns, sacrifice floors, diminishing amplification)

---

## 1. Taxonomy
| Dimension | Values | Notes |
|----------|--------|-------|
| Sacrifice Type | HEALTH, ITEM, RESOURCE, SOUL_SLOT, COOLDOWN, POSITION, GROUP_POOL | What the player (or party) pays |
| Scope | SELF, LOCAL (radius), AREA (chunk cluster), GLOBAL | Drives broadcast & particle budget |
| Contract Class | OFFENSE, DEFENSE, SUPPORT, CONTROL, HYBRID, FUSION | For balance caps & stacking |
| Activation Mode | INSTANT, CHANNEL (maintain), SEQUENCE (ritual steps), REACTIVE (trigger on condition) | Sequence allows cinematic multi-step inputs |
| Tier | 1, 2, 3 (Mythic) | Tier ties to max radius / potency / visual layer count |
| Scaling Axes | Sacrifice Amount, #Distinct Souls, Resonance Count Active Nearby, Time Invested | All formulas reference base factors in registry |

---

## 2. Data Schema (YAML Prototype)
```yaml
id: contract.blood_pact
name: Blood Pact
tier: 1
class: SUPPORT
scope: LOCAL
activation:
   mode: INSTANT
   cast_time_ticks: 10
   cooldown_seconds: 90
   internal_lockout_tag: life_exchange_minor
sacrifice:
   type: HEALTH
   min_amount: 0.2    # fraction of max HP
   max_amount: 0.5
   floors:
      player_min_hp_after: 4   # hearts *2 for raw HP
effects:
   duration_seconds: 10
   aura_radius: 7
   lifesteal_percent_base: 0.10
   lifesteal_percent_per_extra_blood_soul: 0.04
scaling:
   formula: "effective_lifesteal = base + (sacrifice_frac * 0.15) + (blood_souls * per_extra)"
   clamps:
      lifesteal_max: 0.40
resonance_hooks:
   - trigger: ON_AURA_TICK
      condition: resonance:chaos_blood active_within:10
      modify:
         lifesteal_percent_add: 0.05
         radius_mult: 1.15
visual:
   sigil: red_rune_circle_small
   pulse_rate: 12
   particle_theme: blood_rings
anti_abuse:
   diminishing_tag: sustain_leech
   diminishing_table:
      first: 1.0
      second: 0.7
      third_plus: 0.45
logging:
   audit: BASIC
```

All contracts follow this shape (fields may be optional depending on sacrifice type or activation mode).

---

## 3. Lifecycle / State Machine
```
IDLE → (Player Input & Preconditions OK) → WINDUP → (Sacrifice Validation) → COMMIT
→ APPLY_EFFECT (register aura / buff / world effect) → ACTIVE (ticks) → EXPIRE
→ CLEANUP (revoke modifiers, visuals) → COOLDOWN (lockout) → IDLE
```
Failure branches: any validation failure returns to IDLE and refunds provisional resources (except non-refundable ritual components when final step not reached—configurable).

---

## 4. Core Formulas (Reference)
1. Sacrifice Scaling Factor (S):
   S = (paid - min) / (max - min)  (clamped 0..1)
2. Group Amplification (G):
   G = 1 + (unique_souls - 1) * group_step  (group_step typically 0.12–0.20; clamp G ≤ group_cap)
3. Resonance Modifier (R):
   R = 1 + Σ (resonance_bonus_i) (only top N counted if many; N config e.g. 2)
4. Final Magnitude (M):
   M = Base × (1 + S * sacrifice_scalar) × G × R
5. Aura Radius (A):
   A = base_radius × (1 + 0.25 * (unique_souls-1)) (soft cap; hard clamp)

---

## 5. Conflict & Stacking
| Conflict Domain | Rule |
|-----------------|------|
| sustain_leech | Only strongest lifesteal aura active; others diminish table |
| area_control_fire | Max 1 Tier 2+ persistent flame field per 32×32 region |
| hybrid_fusion | Only one Tier 3 fusion contract per party per 10 min |
| cooldown_reducer | Additive stacking converts to multiplicative after 2 sources |

Each contract declares: `conflict_tags: [sustain_leech, ...]` & optional `exclusion_ids`.

---

## 6. Anti-Abuse / Guardrails
* HP Sacrifice leaves player at ≥ 4 HP
* Resource sacrifice has daily soft cap -> diminishing return multiplier after threshold
* Internal Lockout Tags prevent rapid cycling of similar effects
* Server watchdog: if average active contracts > threshold per tick → degrade non-critical visuals first

---

## 7. Visual Budgeting
| Tier | Max Active Radius (blocks) | Max Particle Systems | Sigil Layers | Light Effects |
|------|----------------------------|----------------------|--------------|---------------|
| 1    | 8                          | 2                    | 1            | None          |
| 2    | 14                         | 4                    | 2            | Minor         |
| 3    | 22                         | 6                    | 3            | Dynamic       |

Server mode (performance degraded) trims: particle density → aura fade → removes non-functional block-displays.

---

## 8. Contract Catalog (Current Draft Set)
| ID | Name | Tier | Sacrifice | Class | Primary Effect Snapshot | Notable Resonance Hook |
|----|------|------|-----------|-------|--------------------------|------------------------|
| contract.blood_pact | Blood Pact | 1 | 20–50% HP | SUPPORT | Lifesteal aura | Chaos+Blood → radius +15% |
| contract.infernal | Infernal Contract | 2 | Flame item | OFFENSE | Firestorm pulses | Fire+Wind → Tornado embed |
| contract.frozen_oath | Frozen Oath | 2 | Frost blade + mobility lock | CONTROL | AoE freeze field | Frost+Silence → freeze +1s |
| contract.shadow_merge | Shadow Merge | 2 | Mobility cooldown | HYBRID | Clone squad + stealth burst | Shadow+Trickery → clone AoE |
| contract.tides_unbound | Tides Unbound | 2 | Dash charge | CONTROL | Whirlpool amplification | Tides+Lightning → electric pull |
| contract.natures_tribute | Nature's Tribute | 1 | Trees/plants | SUPPORT | Regen rain | Nature+Tides → add cleanse |
| contract.magma_offer | Magma Offer | 2 | Lava source | OFFENSE | Fire terrain hazard | Flame+Order → purging flames |
| contract.temporal_pact | Temporal Pact | 1 | Cooldown delay | SUPPORT | Instant ult charge + haste | Wind synergy extension |
| contract.embers_tempo | Ember’s Tempo | 1 | Attack delay | OFFENSE | AoE ignite | Flame+Trickery → dash ignite |
| contract.harmony_convergence | Harmony Convergence | 2 | Group HP pool | SUPPORT | Multi-buff elemental aura | Multi-soul scaling radius |
| contract.cataclysm | Cataclysm Pact | 3 | Rare items pool | FUSION | Massive AoE convergence | Multi fusion scaling |
| contract.life_for_power | Life-for-Power | 2 | Group HP | OFFENSE | Strength + CDR | Blood+Shadow synergy |
| contract.soul_fusion | Soul Fusion | 3 | Secondary soul / rare | FUSION | Hybrid temporary form | Pair synergy unlocked attacks |
| contract.mana_infusion | Mana Infusion | 1–2 | Mana resource | SUPPORT | Boost ult/passives | Elemental group amplifier |
| contract.shadow_bargain | Shadow Bargain | 3 | HP + shadow item | HYBRID | Group invis + clone assaults | Shadow+Trickery amplification |
| contract.elemental_offering | Elemental Offering | 2 | Env resources | AREA | Elemental AoE field | Multi-soul intensity scale |

---

## 9. Logging & Telemetry
Minimal (tier 1) vs Extended (tier 2+) vs Audit (fusion / tier 3). Telemetry fields: activation_rate, avg_sacrifice_frac, effective_magnitude, cancel_rate.

---

## 10. Implementation Checklist
* Registry loader & validation (schema + clamps)
* Conflict resolver (by tag → choose highest priority or apply diminishing)
* Sacrifice adjudicator (ensures remaining HP, item presence, etc.)
* Effect fabric (aura scheduler, tick pipeline, cleanup)
* Visual adapter (tier aware)
* Telemetry emitter
* Unit tests: formula correctness, edge clamping, diminishing integrity

---

## 11. Future Extensions
* Procedural contract templates (parametrized base forms)
* Contract evolution (repeat usage unlocks variant field modifiers)
* World node rituals (multi-location synchronized activation)

---

End of contract spec.