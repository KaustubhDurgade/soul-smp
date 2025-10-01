# 🌟 Event System – Structured Specification (Step 1.0 → 2.0 Ready)

Events are scheduled or reactive world-layer occurrences that modulate environment, combat rhythms, resource pressure, and emergent social coordination. They are registry-driven, conflict-aware, and resonance-reactive.

---

## 0. Principles
* Pacing Layer: Adds macro rhythm on top of micro abilities/contracts
* Contextual Amplification: Souls/resonances modify event parameters (not replace them)
* Predictive Telegraphy: Pre-phase warnings reduce unfairness
* Performance-Aware: Hard cap concurrent heavy visuals; degrade gracefully
* Deterministic Core: Pure data spec → engine resolves; no hidden per-event code hacks

---

## 1. Taxonomy
| Field | Values | Notes |
|-------|--------|-------|
| scope | LOCAL, REGIONAL, WORLD, GLOBAL | Affects scheduling + broadcast radius |
| category | WEATHER, ASTRAL, SOUL, CONTRACT, RESOURCE, CREATURE, FUSION, SEASONAL | Filtering & balancing |
| trigger_mode | TIMER, PROBABILISTIC, THRESHOLD, COMPOSITE, MANUAL | Composite = multi-condition |
| phases | TELEGRAPH, ACTIVE, COOLDOWN, RECOVERY | Telegraph optional; recovery for cleanup |
| scaling_axes | player_count, unique_souls, active_resonances, biome_density, time_of_day | Declared per event |
| reward_profile | LOOT, BUFF, WORLD_STATE, CONTRACT_UNLOCK | Drives anti-farm policies |
| rarity | COMMON, UNCOMMON, RARE, LEGENDARY, MYTHIC | Weighted scheduling |
| intensity_tier | 1, 2, 3 | Visual & mechanical budgets |

---

## 2. Data Schema (YAML Prototype)
```yaml
id: event.elemental_storm
name: Elemental Storm
category: WEATHER
rarity: UNCOMMON
intensity_tier: 2
scope: REGIONAL
trigger:
  mode: TIMER
  interval_range_minutes: [45, 75]
  biome_filter: [desert, ocean, plains]
  prerequisite:
    min_players: 4
    min_unique_souls: 3
phases:
  telegraph_seconds: 20
  active_seconds: 180
  cooldown_seconds: 900
scaling:
  player_count:
    radius_per_player: 1.2
    max_extra_radius: 30
  unique_souls:
    elemental_bias_weight: 0.15
  active_resonances:
    damage_amp_per_relevant: 0.05 (cap 0.25)
mechanics:
  elemental_rotation: [FIRE, TIDES, FROST, LIGHTNING]
  cycle_interval_seconds: 30
  effects:
    FIRE:
      tick_damage: 2
      fire_stack_increase: 1
    TIDES:
      slow_percent: 0.25
      pull_strength: 0.12
    FROST:
      freeze_chance: 0.08
    LIGHTNING:
      strike_rate_seconds: 5
resonance_hooks:
  - condition: resonance:flame_wind active_within:32
    modify:
      FIRE.fire_stack_increase: +1
      LIGHTNING.strike_rate_seconds: -1
visual:
  telegraph: swirling_cloud_band
  active_layers: 3
  particle_budget: 400
  suppression_strategy: density_scale_then_layer_drop
rewards:
  loot_table_id: loot.elemental_shards_t2
  participation_min_ticks: 60
anti_abuse:
  region_reentry_grace_seconds: 40
  diminishing_tag: weather_chain
logging:
  audit: EXTENDED
```

---

## 3. Lifecycle
```
SCHEDULED → TELEGRAPH (optional) → ACTIVE → RECOVERY (optional cleanup fade) → COOLDOWN → (re-eligible)
```
Preemption rules: A higher-tier event can delay same-region lower-tier events; never chain >2 high-intensity events in same region within 30m.

---

## 4. Scheduling Engine
* Weighted pool per region (configured base weights × rarity scalar × recent activity penalty)
* Region segmentation: world partitioned into logical cells (e.g., 128×128)
* Soft concurrency limits per scope tier (ex: max 2 Tier 2 regional events simultaneously)
* Backoff: each executed event increments its category fatigue score → decays over time

---

## 5. Scaling Formulas
| Parameter | Formula (simplified) | Clamp |
|-----------|----------------------|-------|
| radius | base + min(player_count, Pcap)*rpp | ≤ base + max_extra_radius |
| damage_amp | Σ relevant_resonance * bonus | ≤ 25% |
| strike_rate | base * (1 - soul_density * k) | ≥ base * 0.5 |
| reward_quality | base * (1 + rarity_scalar + participation_scalar) | config limit |

Participation scalar uses effective contribution score (time_in_zone × action_weight).

---

## 6. Conflict / Priority Rules
| Domain | Rule |
|--------|------|
| weather | Only one weather category regional event per region ID |
| creature | Max 1 mythic creature in world concurrently |
| fusion | Fusion (tier 3) suppresses Tier 1 telegraphs within 48 blocks |
| astral | Eclipse / Solar events are mutually exclusive in 2h window |

Priority chain: FUSION > CREATURE > ASTRAL > WEATHER > RESOURCE.

---

## 7. Anti-Abuse & Fairness
* Participation credit requires minimum active time & non-AFK action events
* AFK detection reduces reward share (movement variance + interaction count)
* Loot roll protections: per-player bad luck counter for rare drops
* Edge guard: event ceases if TPS < threshold (enters SAFE MODE – visuals minimal, reward token auto-grant)

---

## 8. Visual Performance Strategy
Escalation tiers – remove in order: (1) ambient particles (2) outer layer displays (3) secondary dynamic light (4) projectile trail density.

Per-event declares particle_budget & degrade path.

---

## 9. Event Catalog (Initial Draft)
| ID | Name | Tier | Category | Scope | Core Mechanic | Sample Resonance Effect |
|----|------|------|----------|-------|---------------|--------------------------|
| event.elemental_storm | Elemental Storm | 2 | WEATHER | REGIONAL | Rotating elemental phases | Flame+Wind increases fire rotation intensity |
| event.moon_eclipse | Moon Eclipse | 1–2 | ASTRAL | WORLD | Light level suppression, shadow buffs | Shadow+Silence stealth extension |
| event.solar_flare | Solar Flare | 2 | ASTRAL | WORLD | Day damage boost, debuff undead | Light + Flame amplify burst |
| event.meteor_shower | Meteor Shower | 2 | WEATHER | REGIONAL | Falling meteor projectiles | Wind resonance reduces impact scatter |
| event.tidal_surge | Tidal Surge | 1–2 | SOUL | REGIONAL | Water push/pull pulses | Tides+Lightning electrifies pulses |
| event.wrath_ascendant | Wrath Ascendant | 2 | SOUL | REGIONAL | Fire rage meteors | Blood+Flame increases meteor rate |
| event.prism_alignment | Prism Alignment | 3 | FUSION | REGIONAL | Freeze + silence field | Frost+Silence extends duration |
| event.harmony_festival | Harmony Festival | 2 | GROUP | LOCAL | Buff zone multi-soul | Multi-soul scaling radius |
| event.cataclysmic_convergence | Cataclysmic Convergence | 3 | FUSION | REGIONAL | Ultimate vortex fusion | Multi fusion stacking → capped |
| event.wild_mana_surge | Wild Mana Surge | 1 | RESOURCE | LOCAL | Amplify ult gain | Elemental combos widen zone |
| event.haunting_shadows | Haunting Shadows | 1 | CREATURE | LOCAL | Shadow mob waves | Shadow+Trickery clone additive |
| event.phoenix_rise | Phoenix Rise | 3 | CREATURE | WORLD | Phoenix spawn + fire aura | Flame+Wind storm enhancement |
| event.leviathan_spawn | Leviathan Spawn | 3 | CREATURE | WORLD | Leviathan encounter water control | Tides synergy adds pull fields |

---

## 10. Telemetry
Tracked:
* spawn_interval_actual
* participation_count
* avg_duration_vs_spec
* reward_dispersion
* tps_during_active

---

## 11. Implementation Checklist
* Scheduler module (priority queue + region map)
* Trigger evaluators (time, biome, composite)
* Phase controller (telegraph/active transitions)
* Scaling resolver (compute final tuned params)
* Conflict arbitrator (priority-based suppression)
* Visual orchestrator (layers + degrade ladder)
* Reward allocator (participation adjudication)
* Telemetry emitter + admin debug commands

---

End of event spec.
