# Conflict & Diminishing Tag Matrix

Central reference ensuring systems reuse consistent tags. Each tag defines domain, enforcement point, and stacking logic.

| Tag | Domain | Applies To | Enforcement | Rule | Notes |
|-----|--------|-----------|-------------|------|-------|
| sustain_leech | Healing Sustain | Contracts, Items | Aura merge | strongest_only + diminishing table | Prevents lifesteal stacking abuse |
| area_control_fire | AoE Control | Contracts, Events, Mythics | Region occupancy | limit 1 tier≥2 per 32x32 | Fire terrain / storms |
| area_control_water | AoE Control | Contracts, Events | Region occupancy | limit 1 whirlpool per 32x32 | Avoid overlapping pulls |
| hybrid_fusion | Fusion Power Window | Contracts, Mythics | Activation gate | one per party / 10m | High spectacle cap |
| silence_extension | Crowd Control | Items, Mythics | Attribute resolver | strongest_only | Prevent oppressive silence stacking |
| cooldown_reducer | Cooldown Economy | Items, Mythics, Contracts | Merge stage | additive up to 30%, then multiplicative | Preserves diminishing returns |
| transformation | Form Shift | Mythics | Player state | 1 active | No nested forms |
| world_anchor_dark | Persistent World Object | Mythics, Events | Region occupancy | 1 per 64 radius | Dark field anchors |
| weather_chain | Event Frequency | Events | Scheduler | fatigue scoring | Avoid repetitive weather spam |
| elemental_intensity | Element Rotation Amplification | Events, Contracts, Resonances | Scalar clamp | cap at +50% | Prevent runaway multi-layer boosts |
| passive_duration_amp | Passive Buff Scaling | Items, Mythics | Duration clamp | soft cap after +60% | Avoid indefinite extension |
| control_freeze | Hard CC | Contracts, Events | Per-target immunity | immunity window after effect | Prevent freeze lock chaining |
| stealth_field | Stealth / Vision | Contracts, Mythics | Aura merge | strongest_only | Avoid stacked mass invis |
| support_regen | Sustain | Contracts, Events, Items | Aura merge | strongest_only | Pick highest regen aura |

## Diminishing Tables (Reference)
```
lifesteal: [1.0, 0.7, 0.45]
aoe_slow: [1.0, 0.8, 0.6]
cooldown_reducer (beyond additive cap): multiplicative_post_cap
```

Implementation: conflict manager performs pre-merge pass producing resolved attribute set + audit log of suppressed contributors.
