# Integration Architecture (High-Level Runtime Layering)

Goal: Map design artifacts (specs, registry data) to runtime services / modules in the plugin/mod implementation.

---
## 1. Core Modules
| Module | Responsibilities | Inputs | Outputs |
|--------|------------------|--------|---------|
| RegistryLoader | Parse & validate YAML, build immutable registries | YAML files, validation rules | In-memory typed maps |
| ConflictManager | Resolve overlapping auras/effects, apply diminishing | Active effect intents, conflict matrix | Filtered effect deltas, audit log |
| AttributeEngine | Deterministic merge of base stats + layered modifiers | Soul base, resonances, contracts, items, mythics | Final attribute snapshot + diff events |
| AbilityRuntime | Cooldowns, channels, resource spend/gain, triggers | Player input, attribute hooks | Ability resolution events |
| EventScheduler | Time & condition driven event activation | World state, time, triggers | Event lifecycle events |
| TransformationController | Manages active form overrides | Player state changes | Ability mapping overrides |
| TelemetryEmitter | Collect & batch metrics | Resolution events, effect summaries | JSON lines stream |
| BalanceGuard | Real-time clamps / aborts for abusive patterns | Effect intents, telemetry live stats | Adjusted or canceled effect |
| PersistenceLayer | Store player progression & cooldown snapshots | Runtime state | Disk / DB rows |

## 2. Data Flow (Simplified)
```
Player Input -> AbilityRuntime -> (Intent) -> ConflictManager -> AttributeEngine (final numbers) -> Effect Apply
                                                                   |                          
                                                                   v                          
                                                           TelemetryEmitter <--- BalanceGuard (optional adjust)
```

## 3. Effect Intent Structure (Proposed)
```yaml
intent:
  id: contract.blood_pact#cast
  source_player: uuid
  type: AURA
  tags: [sustain_leech]
  raw:
    lifesteal: 0.34
    radius: 7
    duration: 10
  context:
    tier: 1
    participants: 3
```
ConflictManager mutates / prunes `raw` → emits `resolved` event with applied diminishing factors.

## 4. Layer Ordering (Attribute Pipeline Reference)
1. Base Soul (kit passives)
2. Resonances (pairwise, mythic fusions)
3. Contracts (activated auras/buffs)
4. Items & Mythics (passives + active windows)
5. Events (regional modifiers)
6. Transformations (override / additive)
7. Post-Processing: conflicts, caps, diminishing, clamps

## 5. Concurrency & Tick Model
| Concern | Approach |
|---------|----------|
| Tick Loop | 20 TPS main thread budget instrumentation |
| Heavy Calculations | Precompute scaling factors on activation, store snapshot |
| Conflict Resolution | O(affected effects) using indexed tag → effect lists |
| Cooldowns | Wheel / bucket timer to reduce per-tick scans |
| Area Queries | Spatial hash (chunk id → active fields) |

## 6. Performance Guardrails
| Metric | Budget | Action When Exceeded |
|--------|--------|----------------------|
| Active Auras per Player | ≤12 | Start priority drop (lowest tier) |
| Clone Entities per Region | ≤40 | Suppress new spawns, log warning |
| Meteor Entities per Region | ≤32 | Queue until slot free |
| Attribute Merge Time | ≤2ms p95 | Cache & reuse partial layers |

## 7. Hot-Reload Strategy
* Dev command `/registry reload` triggers:
  * Validation run
  * Diff detection (added, removed, changed signatures)
  * Safe apply for additive changes (new contract)
  * Defer destructive changes (removed scaling var) until restart

## 8. Error Handling & Fallbacks
| Failure | Fallback |
|---------|----------|
| YAML parse error | Skip entry, log error, continue others |
| Unknown conflict tag | Treat as no-conflict, emit validation warning |
| Oversized value (e.g., radius > cap) | Auto-clamp + telemetry flag |
| Telemetry backpressure | Drop low-priority metrics first |

## 9. Testing Layers
| Test Type | Focus |
|-----------|-------|
| Unit | Scaling formulas, diminishing math |
| Integration | Ability → conflict → attribute path |
| Load Sim | Many players / stacked events performance |
| Replay Tests | Deterministic reproduction from recorded intents |

## 10. Extensibility Hooks
| Hook | Description |
|------|-------------|
| preEffectResolve(intent) | Early mutate/cancel |
| postConflict(effect) | Inspect chosen winner |
| attributeSnapshot(player,state) | External analytics |
| telemetryEnrich(row) | Add custom tags |

## 11. Prototype Milestones
| Milestone | Deliverable |
|-----------|-------------|
| M1 | Load + validate + list entries |
| M2 | Simple ability activation (no conflicts) |
| M3 | Conflict manager + diminishing tables |
| M4 | Telemetry emission baseline |
| M5 | Transformation & event layering |
| M6 | Performance guardrails instrumentation |

---
This architecture doc functions as living contract; update as modules evolve.
