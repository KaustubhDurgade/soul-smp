# Balance Baselines (Initial Bands)

These figures are starting envelopes; tuning happens after prototype instrumentation.

## Damage & Sustain
| Context | Tier 1 | Tier 2 | Tier 3 |
|---------|--------|--------|--------|
| Single-target burst (per 10s window) | 1.0x soul baseline | 1.35x | 1.65x |
| AoE sustained DPS (radius ≤8) | 0.6x | 0.9x | 1.15x |
| Lifesteal Aura (max) | 15% | 28% | 40% |
| Max simultaneous DoT sources (player) | 3 | 4 | 5 |

## Control & Mobility
| Metric | Baseline | Hard Cap |
|--------|----------|----------|
| Total slow effect | 40% | 60% (diminishing) |
| Silence duration (single source) | 1.5s | 2.5s |
| Knockback impulse | 1.0 | 1.6 |
| Mobility speed bonus | 30% | 60% (soft at 40%) |

## Cooldowns
| Ability Class | Typical CD | Lowest Practical (post-reduction) |
|---------------|------------|-----------------------------------|
| Tactical | 20s | 11–12s |
| Ultimate | 120s | 75–80s |
| Mythic Active Window | 300–480s | 240s (rare stacking scenario) |

## Radii & Areas
| Effect Type | Tier 1 | Tier 2 | Tier 3 |
|-------------|--------|--------|--------|
| Aura Buff | 6–8 | 10–14 | 14–22 |
| Hazard Field | 5–7 | 8–12 | 12–18 |
| Event Regional | 32–48 | 64–96 | 96–128 |

## Transformation Windows
| Tier | Duration(s) | Cooldown Range |
|------|-------------|----------------|
| 2 | 8–12 | 180–300 |
| 3 | 12–18 | 300–480 |

## Reward / Progression
| Category | Common | Rare | Mythic |
|----------|--------|------|--------|
| Event loot roll chance (base) | 40% | 10% | 1–2% |
| Bad luck protection increment | +0 | +1% | +0.15% |

Instrumentation: every effect instance logs effective magnitude vs band; anomalies flagged if >110% sustained over 5 sampled windows.
