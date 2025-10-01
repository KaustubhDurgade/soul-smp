# Telemetry Schema & Instrumentation Plan

Purpose: Provide consistent metric keys enabling balance dashboards, anomaly detection, and auto-tuning heuristics.

---
## 1. Collection Principles
* Minimal: only capture metrics needed for balance & abuse detection
* Aggregated at source: pre-summarize (e.g. counts, sums) to reduce volume
* Anonymized: player identifiers hashed (salted) for aggregation
* Sampling: heavy events (e.g. projectile reflects) sampled at configurable rate

## 2. Metric Namespaces
`ability.*`, `contract.*`, `event.*`, `mythic.*`, `resonance.*`, `system.*`

Format: `namespace.entryId.metricKey`

## 3. Core Metric Keys
| Metric Key | Type | Level | Description | Notes |
|------------|------|-------|-------------|-------|
| casts | counter | per activation | Number of activations | All active abilities |
| uptime_seconds | sum | window | Effective buff / field uptime | Auras / fields |
| damage_total | sum | per activation | Total post-mitigation damage | Pulses aggregated |
| damage_true_total | sum | activation | Portion true damage | Exec clarity |
| healing_total | sum | activation | Effective healing delivered | Excludes overheal |
| overheal_total | sum | activation | Overheal for efficiency | Sustain tuning |
| lifesteal_heal | sum | activation | Heal attributed to lifesteal domain | Conflict balancing |
| cc_applied_count | counter | activation | # of discrete CC instances (freeze, silence) | Per-target unique |
| cc_full_duration_count | counter | activation | CC not shortened by DR | Detect chaining |
| resource_generated | sum | activation | e.g. Cinder, Bloodlust | Resource economy |
| resource_spent | sum | activation | | Efficiency |
| internal_cooldown_hits | counter | activation | Times effect blocked by ICD | Over-capacity detection |
| participants | gauge | activation | # unique allies/enemies affected | Group scaling |
| meteor_count | counter | activation | Meteors spawned | Meteor tuning |
| clone_count | counter | activation | Clones spawned | Perf guardrails |
| entity_spawns | counter | activation | Summoned entities | Perf tracking |
| execution_count | counter | activation | # executes triggered | Lethality |
| revive_count | counter | activation | # revives performed | Revival economy |
| dash_distance_total | sum | activation | Mobility budget | Movement analysis |
| pull_events | counter | activation | Whirl / vortex pulls | Stack detection |
| reflect_count | counter | activation | Projectiles reflected | Reflect balancing |

## 4. Derived KPIs
| KPI | Formula | Purpose |
|-----|---------|---------|
| dmg_per_cast | damage_total / casts | Efficiency | 
| effective_heal_ratio | healing_total / (healing_total + overheal_total) | Sustain quality |
| avg_cc_duration | (Σ appliedDuration) / cc_applied_count | DR tuning |
| resource_cycle_efficiency | resource_spent / resource_generated | Economy sink balance |
| execution_rate | execution_count / casts | Execution thresholds |
| uptime_ratio | uptime_seconds / wallClockWindow | Aura strength |

## 5. Emission Timing
| Category | Timing |
|----------|--------|
| Instant cast | end-of-frame after resolution |
| Channels | tick & final snapshot |
| Fields/Auras | per tick (light) + summary at end |
| Transformations | at revert |
| Events | phase transitions + final summary |

## 6. Sampling Strategy
| Metric | Default Sample Rate |
|--------|--------------------|
| damage_total | 1.0 |
| meteor_count | 1.0 |
| reflect_count | 0.5 (configurable) |
| clone_count | 0.75 |
| pull_events | 0.5 |

Sampling applied post-aggregation (counting some events vs all). Adjust via config hot-reload.

## 7. Data Shapes
JSON line example:
```
{
  "ts": 1735758392,
  "shard": "world-1",
  "player": "h:3ad91f...", 
  "entry": "contract.blood_pact",
  "metrics": {"casts":1, "damage_total":0, "healing_total":42, "participants":3},
  "meta": {"tier":1, "tags":["sustain_leech"]}
}
```

## 8. Storage & Retention
| Layer | Retention | Purpose |
|-------|-----------|---------|
| Hot (rolling file / buffer) | 24h | Live dashboards |
| Warm (compressed) | 14d | Balance iteration |
| Cold (optional) | 90d | Long-term trend |

## 9. Alert Threshold Concepts
| Condition | Alert |
|-----------|-------|
| avg dmg_per_cast > 1.25× tier baseline over 50 casts | High damage anomaly |
| avg effective_heal_ratio < 0.45 for sustain entries | Inefficient sustain |
| silence avg duration > 2.3s | Silence stacking risk |
| cc_full_duration_count / cc_applied_count < 0.35 | Overly strong DR or immunities |
| resource_cycle_efficiency < 0.55 | Resource overflow risk |

## 10. Privacy & PII
* No raw usernames in persisted data
* Hash salted per deployment (non-reversible centrally)
* Opt-out flag removes per-player rows (keeps aggregated numerator/denominator counters if allowed)

## 11. Implementation Notes
* Central metric registry enumerates allowed keys (hard-fail unknown)
* Use ring buffer batching (flush every 2s or 4KB)
* Backpressure: if queue > threshold, degrade by reducing optional metrics first (e.g. reflect_count)

## 12. Future Extensions
* Percentile latency per ability (server tick cost)
* Spatial heatmaps (bucketed region id) for hazard density
* Automatic clamp proposals from p90 outliers

---
This schema intentionally excludes raw per-hit logs—aggregate at source to limit storage.
