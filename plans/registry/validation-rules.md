# Registry Validation Rules

Defines deterministic validation passes for CI / dev commands. Each rule has: id, scope, severity, check, fix_hint.

---
## 1. Structural Rules
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| STRUCT_ID_NAMESPACE | all | error | `id` matches namespace prefix (contract., event., mythic., resonance., item.) | Rename id to conform or move file |
| STRUCT_UNIQUE_ID | all | error | No duplicate ids across all registries | De-duplicate / consolidate entries |
| STRUCT_REQUIRED_FIELDS | per-schema | error | All required schema fields present | Fill missing or remove entry |
| STRUCT_ENUM_VALID | enum fields | error | Value appears in `enums.md` definition | Correct spelling / update enums |
| STRUCT_NUMERIC_TYPE | numeric fields | error | Fields expected numeric parse | Remove stray characters |
| STRUCT_LIST_TYPE | list fields | error | List is YAML sequence, not comma string | Convert to sequence |
| STRUCT_NO_TABS | file | warn | Tabs not used (spaces standard) | Replace tabs |
| STRUCT_ORDER | file | info | Canonical frontmatter key ordering | Reorder (tool auto-fix) |

## 2. Formula & Scaling Rules
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| SCALE_CLAMP_PRESENT | entries with formula | warn | Clamp provided when formula references growth variable | Add clamp_* field |
| SCALE_VARIABLE_DEFINED | formula | error | All symbols (e.g. `unique_souls`) appear in allowed variable list | Adjust formula or add variable to whitelist |
| SCALE_NO_DIV_ZERO | formula | error | No division by zero literal or var with possible zero | Guard in formula comment |
| SCALE_MONOTONIC_HINT | formula | info | Detect descending growth unexpectedly | Verify design intention |

Allowed scaling variables list (initial): `unique_souls, elemental_resonances, wind_souls, flame_souls, tides_souls, lightning_souls, shadow_souls, silence_souls, trickery_souls, order_souls, blood_souls, avg_sacrifice_frac, S`.

## 3. Balance Envelope Rules
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| BAL_DAMAGE_PULSE | contract/event/mythic pulses | warn | Pulse damage within baseline tier band (see balance-baselines) | Adjust base or clamp |
| BAL_AURA_RADIUS | aura effects | error | Radius ≤ allowed per tier (baseline + 10%) | Reduce radius or raise tier justification |
| BAL_LIFESTEAL_CAP | sustain_leech | error | Lifesteal ≤ 40% global cap | Lower scaling/clamp |
| BAL_SILENCE_DURATION | silence | error | Single source silence ≤ 2.5s | Reduce duration or split | 
| BAL_FREEZE_DURATION | freeze | error | Freeze ≤ 3.5s (with scaling) | Adjust scaling/clamp |
| BAL_COOLDOWN_RATIO | abilities | warn | (active_window / cooldown) within expected efficiency | Rebalance numbers |

## 4. Conflict & Tag Rules
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| TAG_DEFINED | conflict tag use | error | Every `conflict_tags[]` value appears in conflicts matrix | Add to matrix or remove tag |
| TAG_DOMAIN_APPROVED | conflict tag use | warn | Tag domain matches entry type domain mapping | Confirm or change tag |
| TAG_DUPLICATE | entry | info | No duplicate tags in list | Remove duplicate |
| DIMINISH_SEQUENCE_LEN | diminishing tables | error | Sequence length ≥2 and first element =1.0 | Fix table ordering |

## 5. Anti-Abuse & Caps
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| ABUSE_IMMUNITY_WINDOW | hard CC (freeze/petrify/stasis) | warn | Immunity window specified | Add `immunity_after` field |
| ABUSE_AREA_LIMIT | area_control tags | error | Region exclusivity / max active fields defined | Add `max_server_active` or region rule |
| ABUSE_RATE_LIMIT | spawn / meteor / clone loops | warn | Internal cooldown or cap present | Add ICD or cap field |

## 6. Referenced Condition Integrity
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| HOOK_CONDITION_PARSE | resonance_hooks | error | Condition string parses (simple DSL) | Fix syntax |
| HOOK_FIELD_EXISTS | modify object | error | Modified field exists or is allowed to create | Add base field or adjust name |
| HOOK_MULT_COLLISION | multiple hooks | warn | Two hooks modify same field with conflicting operations | Reorder or merge hooks |

## 7. Cross-Registry Integrity
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| CROSS_RELIC_ITEM_EXISTS | contracts with required_items | error | Each item id exists in item registry (future) | Add placeholder item entry |
| CROSS_RESONANCE_EXISTS | hooks referencing `resonance:*` | error | Resonance id valid | Correct id |
| CROSS_SOUL_SYMBOL | soul_* count references | warn | Soul names in scaling vars are valid souls | Update enum or correct name |

## 8. Telemetry Coverage (meta)
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| TELEMETRY_KEY_DEFINED | high-impact effect | warn | Key mapped in telemetry schema | Add metric or remove flag |
| TELEMETRY_NOT_EXCESSIVE | entry | info | ≤12 metrics per entry flagged | Trim metrics |

## 9. Documentation Hygiene
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| DOC_SUMMARY_PRESENT | spec markdown | warn | `## Summary` present and non-empty | Add concise summary |
| DOC_SCALING_SECTION | spec with scaling | info | `## Scaling` present | Add section |
| DOC_CONFLICT_SECTION | conflict tags | info | `## Anti-Abuse & Conflicts` or similar exists | Add explanation |

## 10. File Naming
| ID | Scope | Severity | Check | Fix Hint |
|----|-------|----------|-------|----------|
| FILE_PREFIX_MATCH_ID | registry/data | error | Filename prefix equals id last segment | Rename file |

---
### Severity Handling
* error: fails CI
* warn: allowed but reported
* info: reported for awareness only

### Output Format
JSON lines per violation: `{id, entry_id, rule, severity, message, fix_hint}`

### Auto-Fix Candidates
STRUCT_ORDER, TAG_DUPLICATE, STRUCT_NO_TABS, DOC_SECTION stubs.

---
## Implementation Sketch
Pseudo-pass order:
1. Load enums / conflicts
2. Parse YAML entries (strict schema)
3. Build symbol tables (ids, tags)
4. Run structural passes
5. Run domain passes (balance envelopes require baseline table load)
6. Emit report; exit non-zero if any error

---
## Future Extensions
* Statistical anomaly detection (live metrics vs baselines)
* Auto-generated clamp suggestions using percentile sampling
* Balance delta diffing (git pre-commit hook)

---
This file is source of truth for validation tooling; keep in sync with schema evolutions.
