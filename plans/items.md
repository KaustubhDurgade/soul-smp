# 🛠 Item System – Structured Specification (Step 1.0 → 2.0 Ready)

Items extend souls, resonances, and contracts by providing modular stat hooks, actives, passives, transformation triggers, and crafting progression. All items are defined in a registry first; code consumes declarative entries.

---

## 0. Pillars
* Horizontal Modularity (small stacking effects > single overpowering artifact)
* Intent Clarity (front-loaded readable attributes)
* Controlled Power Creep (rarity gating + upgrade ceilings)
* Visual Identity (each tier has distinct display/pattern)
* Predictable Math (every modifier resolved through centralized attribute resolver)

---

## 1. Taxonomy
| Field | Values | Notes |
|-------|--------|-------|
| slot_type | WEAPON, ARMOR_HEAD, ARMOR_CHEST, ARMOR_LEGS, ARMOR_FEET, TRINKET, OFFHAND, ARTIFACT, CONSUMABLE, CATALYST, WORLD_CORE | Slot gating & stacking rules |
| rarity | COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC | Affects base stat budget & upgrade cap |
| item_class | STAT, ACTIVE, PASSIVE, HYBRID, TRANSFORMATION, WORLD_OBJECT | Behavior resolution pipeline |
| soul_affinity | (list of souls) or ANY | Affects attunement bonus or penalties |
| binding | NONE, SOUL_BOUND, ACCOUNT_BOUND, TEMPORAL | Affects trade & drop rules |
| upgrade_path | LINEAR, BRANCHED, INFUSION, EVOLUTION | Evolution may change slot_type |

---

## 2. Data Schema (YAML Prototype)
```yaml
id: item.eclipse_blade
name: Eclipse Blade
slot_type: WEAPON
rarity: LEGENDARY
item_class: HYBRID
soul_affinity: [Shadow, Silence]
binding: SOUL_BOUND
stats:
	attack_power: 22
	crit_chance: 0.05
passives:
	- id: passive.shadow_amp
		effect: +15% shadow ability damage
	- id: passive.silence_extension
		effect: +0.5s silence duration (cap aware)
active:
	cooldown_seconds: 30
	duration_seconds: 8
	effects:
		- type: radial_slash
			radius: 6
			damage: 12
			extra_vs_marked: 1.25
resonance_hooks:
	- condition: resonance:shadow_trickery active_within:16
		modify:
			active.effects[0].radius: +2
			passive.shadow_amp: +0.05 (cap aware)
visual:
	model_key: weapon/eclipse_blade
	swing_trail: shadow_arc
	aura: dark_pulse
upgrade:
	path: LINEAR
	stages:
		- to: item.eclipse_blade_plus
			cost: { essence_shadow: 6, essence_silence: 4 }
			stat_scalars: { attack_power: 1.10, crit_chance: +0.01 }
anti_abuse:
	diminishing_tags: [silence_extension]
	stack_rules:
		silence_extension: strongest_only
logging:
	audit: BASIC
```

---

## 3. Attribute Resolver
Items emit contributions into a layered pipeline:
1. Base kit (soul innate)
2. Resonances (pair modifiers)
3. Item stat add/mul (order: additive pools then multiplicative tier scalars)
4. Contracts temporary buffs
5. Event / world state modifiers

Conflict rules declared by `diminishing_tags` and `stack_rules` (e.g., strongest_only, additive_cap, diminishing_sequence).

---

## 4. Stacking / Caps Examples
| Attribute | Global Cap | Softening Strategy |
|-----------|------------|--------------------|
| movement_speed_percent | +60% | After +40% apply 0.5 multiplier to further gains |
| cooldown_reduction | 45% | Convert additive >30% into multiplicative remainder |
| silence_duration_bonus | +1.5s | Only strongest source | 
| lifesteal_percent | 40% | Diminishing table (1.0, 0.7, 0.45) |

---

## 5. Crafting & Infusion
| Process | Inputs | Output | Notes |
|---------|--------|--------|-------|
| Standard Craft | Base material + essence | Base rarity item | Essence tied to soul theme |
| Infusion | Item + resonance shard | Adds resonance hook | Limited to 1–2 hooks |
| Evolution | Max upgraded item + mythic core | New ID (e.g., transformation variant) | Resets upgrade path |
| Stabilization | Item + purity catalyst | Removes corruption risk | High-end cost gate |

Crafting stations: ESSENCE_FORGE (standard), INFUSION_ALTAR (resonance), ASCENSION_PYLON (evolution).

---

## 6. Corruption (Optional System Toggle)
High-tier upgrades may add corruption risk: each attempt rolls outcome (success / partial / corrupt). Corruption attaches debuff tag requiring stabilization item. Fully optional for balance pacing.

---

## 7. Attunement
Players may attune to N affinity items (config, default 3). Non-attuned affinity items apply at 70% stat effectiveness & no active synergy bonus. Attunement swap has cooldown (e.g., 10m) to curb mid-fight swapping.

---

## 8. Visual Budget
| Rarity | Max Persistent Visual Layers | Swing Trails Allowed | Idle Aura Budget |
|--------|------------------------------|----------------------|------------------|
| COMMON | 0 | None | 0 |
| RARE | 1 | Simple | 1 |
| EPIC | 2 | Enhanced | 1 |
| LEGENDARY | 3 | Advanced | 2 |
| MYTHIC | 4 | Advanced | 3 |

Budget enforcement: degrade non-functional visuals first when client hint requests low mode or server load high.

---

## 9. Item Catalog (Illustrative Slice)
| ID | Name | Slot | Rarity | Class | Core Hook | Resonance Tie |
|----|------|------|--------|-------|-----------|---------------|
| item.eclipse_blade | Eclipse Blade | WEAPON | LEGENDARY | HYBRID | Shadow + Silence burst | Shadow+Trickery amplify |
| item.tempest_halberd | Tempest Halberd | WEAPON | LEGENDARY | ACTIVE | Lightning cleave chain | Wind+Tides chain boost |
| item.infernal_cannon | Infernal Cannon | WEAPON | LEGENDARY | ACTIVE | Fire AoE projectile | Fire+Wind auto tornado |
| item.chronos_hourglass | Chronos Hourglass | ARTIFACT | EPIC | ACTIVE | Cooldown mass reduction | Time-based contract synergy |
| item.soul_prism | Soul Prism | ARTIFACT | EPIC | PASSIVE | Double passive strength radius | Multi-resonance scaling |
| item.elemental_orb | Elemental Orb | ARTIFACT | LEGENDARY | ACTIVE | Store/release elemental AoE | Elemental rotation events |
| item.aegis_ancients | Aegis of the Ancients | ARMOR_CHEST | EPIC | PASSIVE | Projectile reflect | Defensive resonance boost |
| item.phantom_mantle | Phantom Mantle | ARMOR_CHEST | LEGENDARY | PASSIVE | Invis on ultimate | Shadow+Trickery stealth loop |
| item.dragon_heart | Dragon’s Heart | CATALYST | MYTHIC | TRANSFORMATION | Fire dragon form | Flame+Wind synergy enhance |
| item.leviathan_core | CATALYST | MYTHIC | TRANSFORMATION | Water control form | Tides lightning vortex |
| item.void_mantle | CATALYST | MYTHIC | TRANSFORMATION | Shadow warp form | Shadow+Silence amplifier |
| item.meteoric_scepter | WORLD_CORE | LEGENDARY | ACTIVE | Meteor storm | Fire event synergy |
| item.celestial_compass | WORLD_CORE | EPIC | ACTIVE | Temporary biome field | Elemental resonance layering |
| item.aurora_crown | TRINKET | EPIC | HYBRID | Movement + ult regen aura | Multi-soul buff radius |
| item.oblivion_shard | ARTIFACT | LEGENDARY | ACTIVE | AoE silence & damage | Shadow+Silence enhancer |

---

## 10. Anti-Abuse / Guardrails
* Active effect concurrency: same item ID cannot have >1 active instance per player
* Passives with `strongest_only` diminish others with same tag
* Upgrade economy: daily soft cap on highest rarity essence acquisition
* Attunement prevents hot-swap exploitation mid-combat
* Rate limiter on transformation toggles (min interval between revert/recast)

---

## 11. Telemetry
* equip_frequency
* upgrade_success_rate
* active_usage_interval
* corruption_incidence (if enabled)
* performance_cost_estimate (tracked per visual effect instantiation)

---

## 12. Implementation Checklist
1. Schema validator & loader
2. Attribute merge engine
3. Stacking/diminishing resolver
4. Upgrade pipeline service (cost consumption + evolution ID swap)
5. Active ability executor (cooldown + visual + effect)
6. Transformation harness (swap model/state; revert guardrail)
7. Attunement manager
8. Telemetry emitter + admin query commands

---

End of item spec.
