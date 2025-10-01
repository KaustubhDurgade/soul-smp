# Resonance System Expansion (Detailed Design)

This document expands the high‑level resonance blueprint into a structured, implementable catalog covering:
1. Taxonomy & Trigger Model
2. Data Schema Proposal
3. Core Engine Flow
4. Pairwise Resonance Catalog (Initial Wave)
5. Triplet / Rare Fusion Patterns
6. Scaling Formulas & Anti-Abuse Rules
7. Conflict & Priority Matrix
8. Extensibility Roadmap

---
## 1. Taxonomy & Trigger Model
| Layer | Trigger Type | Description | Typical Window | Engine Hook |
|-------|--------------|-------------|----------------|-------------|
| Proximity | Distance Cohesion | Souls inside radius simultaneously | Continuous (tick) | OnTickProximityCheck |
| Sequenced | Ability Temporal Overlap | Two abilities resolved within Δt | 0.75–1.0s | AbilityResolveEvent buffer |
| Spatial Overlap | Area Intersections | AoE volumes intersect > X% | Instant (when overlap) | AreaIntersectionEvent |
| Reactive | Status Cascade | Application of status by Soul A to target influenced by Soul B | Status lifetime | StatusAppliedEvent |
| Ultimate Fusion | U-overlap gating | Two (or more) Ultimates active concurrently | U durations | UltimateStateChangedEvent |
| Persistent Network | Linked Multi-node | ≥3 souls maintain network channel (e.g. beams) | Channel lifetime | NetworkLinkTick |

---
## 2. Data Schema (YAML / JSON friendly)
```yaml
id: chaos+wrath.fire_tornado
name: Fire Tornado
souls: [chaos.void?, wrath.ash?]   # flexible pattern matching (path optional)
trigger:
  type: SEQUENCED
  window_ms: 900
  sequence:
    - source: wrath
      ability: ignition|inferno_chain_surge
    - source: chaos
      ability: unstable_burst|riftstorm
conditions:
  environment:
    forbid: [rain]
  targeting:
    minEnemies: 1
scaling:
  participants: linear      # radius grows per valid participant
  baseRadius: 4
  perSoulRadius: 1.2
  tickDamage:
    base: 2
    perSoul: 0.5
effects:
  - type: AOE_DOT
    interval: 10           # ticks
    duration: 120          # ticks
    status: set_on_fire
  - type: PULL_SPIRAL
    strength: 0.18
visual:
  particles: FIRE_SPIRAL
  sound: ENTITY_BLAZE_SHOOT
anti_abuse:
  cooldown_shared_ms: 20000
  diminishing_if_retriggered_within_ms: 60000
  maxConcurrent: 1
```
Key Notes:
- `souls` supports wildcarding base vs path (`wrath.*`, `wrath.blood`, etc.).
- `participants` scaling clarifies how many distinct players contribute.
- `anti_abuse` prevents resonance spam loops (especially in coordinated groups).

---
## 3. Engine Flow (Pseudo)
```
AbilityResolveEvent e -> buffer[event.soul].push(ts, abilityId, loc)
Every tick:
  prune older than maxWindow (e.g. 1500ms)
  attemptMatch(buffer, resonanceRegistry)

attemptMatch:
  for resonance in registry:
    if trigger.type == PROXIMITY -> evaluate distance graph
    if trigger.type == SEQUENCED -> order & Δt constraints
    if trigger.type == ULT_FUSION -> check active ultimate flags
    if conditions satisfied -> instantiate effect entity (state machine)
```
State Machine Example (AOE_DOT + PULL):
```
struct ActiveResonance { id, participants[], started, expires, radius, tickInterval, nextTick }
Tick -> if(now >= nextTick) applyEffects(); nextTick+=tickInterval
OnExpire -> cleanup visuals, remove from active index
```

---
## 4. Pairwise Resonance Catalog (Initial Wave – 20 Exemplars)
| ID | Souls | Name | Trigger | Core Effect | Scaling Hook |
|----|-------|------|---------|-------------|--------------|
| wrath+chaos.fire_tornado | Wrath+Chaos | Fire Tornado | Sequenced (dash + random burst) | Spiral pull + fire DoT | Radius per participant |
| wrath+despair.ashen_rot | Wrath+Despair | Ashen Rot | Fire hit within 1s of Rot apply | Upgrades Rot tier & adds burn | Rot tier synergy |
| wrath+hope.purifying_flame | Wrath+Hope | Purifying Flame | Hope heal inside flame zone | Converts part of burn to ally heal pulses | Allies present |
| wrath+serenity.temper_flux | Wrath+Serenity | Tempered Flux | Sanctuary active + Ignition | Sanctuary gains burn reflect | Sanctuary size |
| wrath+silence.smother | Wrath+Silence | Smother | Null field + fire tick overlap | Suppresses enemy regen entirely | Null Sat level |
| serenity+hope.resplendent_well | Serenity+Hope | Resplendent Well | Beacon + Sanctuary overlap | Supercharge regen & shield ramp | Overlap duration |
| serenity+order.still_time | Serenity+Order | Still Time | Sanctuary + Stasis/Edict | Time-slow zone (movement & projectiles) | Temporal resource |
| serenity+greed.charity_exchange | Serenity+Greed | Charity Exchange | Heal > overheal threshold | Converts overheal → Wealth coins | Overheal amount |
| greed+curiosity.alchemical_mint | Greed+Curiosity | Alchemical Mint | Transmute near Market Shift | Spawn transmuted coin items | Wealth value |
| greed+trickery.fool’s_gold | Greed+Trickery | Fool's Gold | Pillage + clone active | Fake buff icons applied to enemies | #Illusions |
| pride+glory.legendary_banner | Pride+Glory | Legendary Banner | Warbanner + Immortal Banner radius intersect | Merges into empowered banner (stats amplify) | Ally count |
| pride+order.edict_of_command | Pride+Order | Duel mark inside Edict | Forces focus (taunt style) & CD increase if disobey | Renown level |
| despair+silence.null_decay | Despair+Silence | Rot/Wither tick inside Silence Field | Extends silence duration slightly per tick | Decay index |
| despair+chaos.haunting_echo | Despair+Chaos | Fear proc + random roll | Fear spreads chain lightning fear pulses | Fear targets |
| chaos+curiosity.experimental_flux | Chaos+Curiosity | Arcane Experiment triggers Wild Surge nearby | Mutates effect selection bias | Instability resource |
| chaos+trickery.mirror_madness | Chaos+Trickery | Illusion clones inside random aura | Clones inherit random buffs then explode | Clone count |
| order+silence.absolute_judgment | Order+Silence | Edict + Oblivion overlap | Full ability lock + status purge | Balance resource |
| order+hope.temporal_radiance | Order+Hope | Rewind cast inside Beacon | Stores & replays last heal ticks | Temporal resource |
| nature+hope.verdant_dawn | Nature+Hope | Beacon + Nature’s Wrath storm | Adds growth pulses (vines restrain) & heal | Vine hits |
| nature+wrath.scorched_wilds | Nature+Wrath | Thorn field + meteor impact | Thorns ignite causing extra reactive damage | Meteor count |

Each entry would get a full spec later: scaling curves, caps, visuals.

---
## 5. Triplet / Rare Fusion Patterns (Examples)
| Souls | Name | Pattern | Effect | Guardrails |
|-------|------|---------|--------|-----------|
| Wrath + Hope + Serenity | Phoenix Convergence | Flame ult + Beacon + Sanctuary | Massive rebirth pulse (revive downed ally at low HP) | 1 / 10 min lock |
| Order + Silence + Despair | Absolute Suppression | Edict + Oblivion + Plague Field | Full ability negation + healing invert | Duration hard cap 6s |
| Pride + Glory + Greed | Imperial Treasury | Warbanner + Immortal Banner + Pillage | Generates legendary coin (consumable temp stat boon) | Coin unique ID |
| Chaos + Curiosity + Trickery | Fractured Insight | Wild Surge + Experiment + Illusion clones | Spawns research anomaly granting random tech boon | Single owner |
| Nature + Hope + Serenity | World Bloom | Nature’s Wrath + Beacon + Sanctuary | Terrain flowers (temporary buffs on interaction) | 64 tile limit |

---
## 6. Scaling & Anti-Abuse
Principles:
- Diminishing Returns: Additional participants after 3 yield 50% effectiveness contribution.
- Cooldown Linking: Triggering resonance places short (e.g. 15s) personal resonance cooldown unless Ultimate fusion (60s).
- Cap Integrity: Never exceed intended single-target DPS benchmarks (resonances redistribute, not multiply unchecked).

Formulas:
```
# Example AOE DOT scaling
radius = base + min(3, participants) * per_participant + max(0, participants-3)*per_participant*0.5

dps = base_dps + (participants-1) * step
# clamp dps <= base_dps * 2.2
```

---
## 7. Conflict & Priority Matrix
| Overlap Case | Resolution |
|--------------|------------|
| Silence Field vs Time Stop | Time stop freezes silence expansion; existing silence persists. |
| Multiple Pull Fields | Strongest pull strength dominates; others degrade to cosmetic. |
| Reflect + True Damage | Reflection converts true portion to shield instead of reflecting true. |
| Duel Arena vs External Resonance | External resonance effects suppressed inside active duel boundary. |
| Execution & Revival Simultaneity | Execution resolves first; revival deferred one tick if still valid. |

---
## 8. Extensibility Roadmap
1. Phase 1: Implement engine + 8 showcase pairwise resonances.
2. Phase 2: Add registry + data-driven loading (YAML under `data/resonances/`).
3. Phase 3: Telemetry (log activation counts, average participants, balance tuning).
4. Phase 4: Introduce triplet rare fusions with global announcement layer.
5. Phase 5: Player unlock system (resonance codex progression).

---
## Appendices
A. Particle Palette Suggestions
B. Sound Mapping Table
C. Future: Cross-Dimension (Nether / End) resonance modifiers.

---
This serves as the foundation to iteratively codify, balance, and extend Resonances without rewriting core logic.
