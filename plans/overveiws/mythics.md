# 🌌 Mythic System – Structured Specification (Step 1.0 → 2.0 Ready)

Mythics are apex items / transformations / world anchors that create limited, high-impact windows of altered gameplay. They must never become mandatory baselines; they are strategic accelerators with strict acquisition friction and stacking guardrails.

---

## 0. Design Goals
* Singular Identity (each mythic solves a unique combat / support niche)
* Windowed Power (bursts or sustained passives within controlled bands)
* Multi-System Fusion (always interacts with at least one of: resonance, contract, event)
* Predictable Counterplay (visual clarity, telegraphs, cooldown readability)
* Data-Driven Scalability (no bespoke code branches per mythic)

---

## 1. Taxonomy
| Field | Values | Notes |
|-------|--------|-------|
| mythic_type | WEAPON, ARTIFACT, ARMOR, TRANSFORMATION, WORLD_OBJECT, CATALYST | Drives activation + persistence rules |
| tier | 2, 3 (mythics start high) | Tier influences visual + stacking ceilings |
| activation_mode | PASSIVE_ONLY, ACTIVE_WINDOW, TRANSFORM, WORLD_DEPLOY | Behavior lifecycle |
| acquisition_path | EVENT_DROP, CRAFT_INFUSION, CONTRACT_FUSION, BOSS_REWARD, WORLD_TRIGGER | Balancing levers |
| soul_focus | list(souls) or ANY | Used for affinity & attunement |
| limited_flag | true/false | If true, server-limited count concurrently |

---

## 2. Data Schema (YAML Prototype)
```yaml
id: mythic.dragon_heart
name: Dragon's Heart
mythic_type: TRANSFORMATION
tier: 3
activation_mode: TRANSFORM
acquisition_path: EVENT_DROP
soul_focus: [Flame, Wind]
cooldown_seconds: 420
active_window_seconds: 15
transformation:
   model_key: transform/dragon_form
   ability_overrides:
      slot.primary:
         type: cone_breath
         damage: 26
         dot: 4 over 4s
      slot.secondary:
         type: wing_dash
         distance: 12
         knockback: 1.2
scaling:
   flame_souls_bonus_damage_per: 0.06 (cap 0.24)
   wind_souls_dash_bonus_per: 0.5 (blocks, cap +3)
resonance_hooks:
   - condition: resonance:flame_wind active
      modify:
         cone_breath.radius: +2
         cone_breath.damage_mult: 1.15
visual:
   aura: fiery_wings
   trail: ember_stream
   activation_burst: meteor_flare
anti_abuse:
   transform_cancel_min_seconds: 4
   revert_lockout_seconds: 6
   limited_flag: false
attunement:
   exclusive_slot: transformation_core
logging:
   audit: EXTENDED
```

---

## 3. Lifecycle
```
IDLE → (Acquire) → EQUIPPED (passives maybe) → ACTIVATION_CHECK (cooldown OK) → WINDUP → ACTIVE_WINDOW / TRANSFORMED → REVERT → COOLDOWN → EQUIPPED
```
World objects (e.g., Eclipse Totem) substitute REVERT with DESPAWN.

---

## 4. Attunement & Exclusive Slots
* Mythics occupy special attunement channels (e.g., transformation_core, world_anchor)
* A player may attune to at most ONE from each exclusive channel
* Attempt to activate when channel occupied → UI feedback (deny + reason)

---

## 5. Stacking & Conflict Rules
| Domain | Rule |
|--------|------|
| silence_amp | Strongest_only |
| transformation | Only 1 active transformation per player |
| world_anchor_dark | Max 1 within 64 blocks |
| cooldown_mass_reducer | Additive → multiplicative beyond 40% |

Mythic declares `conflict_tags`, engine enforces at activation or merges with diminishing table.

---

## 6. Acquisition Philosophy
| Path | Intent | Control Lever |
|------|--------|---------------|
| EVENT_DROP | Link to macro world pacing | Drop weight & bad luck protection |
| CRAFT_INFUSION | Long-term progression | Essence costs & gating components |
| CONTRACT_FUSION | Group cooperative ritual | Sacrifice totals & ritual timing risk |
| BOSS_REWARD | High-challenge success | Boss token economy |
| WORLD_TRIGGER | Exploration reward | Hidden structure spawn rate |

Bad luck protection increments chance after each failed eligible participation.

---

## 7. Power Budgeting
| Tier | Active Window (s) | Passive Budget (approx stat units) | Visual Layers | Cooldown Band |
|------|-------------------|-------------------------------------|---------------|---------------|
| 2 | 8–12 | 1.0 (baseline) | ≤3 | 180–300 |
| 3 | 10–18 | 1.35 | ≤5 | 300–480 |

Stat Units: Normalized equivalence bucket (e.g., +5% ability dmg = 0.25 unit) used to keep builds balanced.

---

## 8. Visual Degrade Ladder
1. Ambient aura density
2. Secondary wing/halo trails
3. Ground sigil pulses
4. Large volumetric displays

When server performance inspector flags degrade-mode, steps apply globally to mythics.

---

## 9. Mythic Catalog
| ID | Name | Type | Tier | Mode | Core Identity | Resonance Highlight |
|----|------|------|------|------|---------------|---------------------|
| mythic.eclipse_blade | Eclipse Blade | WEAPON | 3 | ACTIVE_WINDOW | Shadow/Silence burst hybrid | Shadow+Trickery clone burst |
| mythic.tempest_halberd | Tempest Halberd | WEAPON | 3 | ACTIVE_WINDOW | Lightning cleave chaining | Wind+Tides chain extension |
| mythic.infernal_cannon | Infernal Cannon | WEAPON | 3 | ACTIVE_WINDOW | High AoE fire salvo | Fire+Wind tornado auto-proc |
| mythic.chronos_hourglass | Chronos Hourglass | ARTIFACT | 2 | ACTIVE_WINDOW | AoE cooldown compression | Multi-soul support boost |
| mythic.soul_prism | Soul Prism | ARTIFACT | 2 | PASSIVE_ONLY | Passive/tactical amplifier | Resonance duration scaling |
| mythic.elemental_orb | Elemental Orb | ARTIFACT | 3 | ACTIVE_WINDOW | Stored elemental discharge | Elemental cycle synergy |
| mythic.aegis_ancients | Aegis of the Ancients | ARMOR | 2 | PASSIVE_ONLY | Reflective defense shell | Defensive resonance layering |
| mythic.phantom_mantle | Phantom Mantle | ARMOR | 3 | PASSIVE_ONLY | Invis on ult + stealth chain | Shadow+Trickery extension |
| mythic.dragon_heart | Dragon’s Heart | TRANSFORMATION | 3 | TRANSFORM | Fire dragon aerial form | Flame+Wind breath amplification |
| mythic.leviathan_core | Leviathan Core | TRANSFORMATION | 3 | TRANSFORM | Water vortex control form | Tides+Lightning conductive pulls |
| mythic.void_mantle | Void Mantle | TRANSFORMATION | 3 | TRANSFORM | Shadow warp assassin form | Shadow+Silence stacking |
| mythic.eclipse_totem | Eclipse Totem | WORLD_OBJECT | 3 | WORLD_DEPLOY | Area darkness & suppression | Shadow/Silence synergy radius |
| mythic.meteoric_scepter | Meteoric Scepter | WORLD_OBJECT | 3 | ACTIVE_WINDOW | Meteor storm cascade | Fire event synergy scaling |
| mythic.celestial_compass | Celestial Compass | WORLD_OBJECT | 2 | ACTIVE_WINDOW | Temporary elemental biome field | Elemental resonance layering |
| mythic.aurora_crown | Aurora Crown | ARTIFACT | 2 | ACTIVE_WINDOW | Movement + ult regen aura | Multi-soul aura scaling |
| mythic.oblivion_shard | Oblivion Shard | ARTIFACT | 3 | ACTIVE_WINDOW | AoE silence + damage | Shadow+Silence amplifier |

Earlier detailed table remains valid; this catalog normalizes it into registry perspective.

---

## 10. Anti-Abuse / Safeguards
* Transform revert cooldown prevents burst weaving
* World objects limited per region & per-player deploy rate
* Cooldown compression stacking respects contract + artifact diminishing rules
* Passive amplifiers (Soul Prism) follow absolute duration caps after merges
* Telemetry auto-flags mythics with >X% participation dominance

---

## 11. Telemetry
* acquisition_source_distribution
* average_active_window_utilization
* transformation_cancel_rate
* conflict_denial_count
* visual_degrade_invocations

---

## 12. Implementation Checklist
1. Mythic registry loader (with structural validation)
2. Exclusive slot / attunement service integration
3. Activation pipeline (conflict -> gating -> activation -> revert)
4. Transformation harness (model swap & ability override binding)
5. World object deployer (region occupancy tracking)
6. Visual degrade orchestrator reuse
7. Telemetry + admin introspection commands

---

End of mythic spec.
