# Global Systems & Conventions

(Extracted from ability-mechanics.md)

## 0.1 Core Cooldown Tiers
| Tier | Category | Typical CD | Notes |
|------|----------|-----------:|-------|
| Passive | Always on | – | May have internal proc cooldown (ICD) |
| Minor Active (T) | Basic utility/damage | 14s | Range 10–20s variance |
| Movement (M) | Mobility / reposition | 12s | 8–18s variance depending on power & damage |
| Major Utility | Control / big heal / heavy CC | 35s | Often the (T) for control classes |
| Ultimate (U) | Signature | 110s | Range 90–150s (offense shorter, huge impact longer) |
| Final Stand | Death-triggered | Per life | May soft-lock reactivation for 15 real minutes after proc if resurrect mechanic |

## 0.2 Scaling & Formula Conventions
Hearts = 2 HP; True Damage bypasses armor; CDR multiplicative & clamped 50%; stacking soft caps described in ability kits where relevant.

## 0.3 Status Effects
(Abbreviated – see original for full details.) Rot, Silence, Nullify, Stasis, Reflection, Blind, Reveal, Disguise, Domain, Temporal Buffer, Chains, Execute Threshold, Fear, Confusion, Corrupt, Void Lock.

## 0.4 Interaction Rules
Silence cancels channels (refund 50% CD if <25%); Reflection excludes Ultimates (converts portion to shielding); Teleport path safety search radius 2; stronger time stops override weaker.

## 0.5 Balance Tags
[Damage], [Heal], [Control], [Mobility], [Defense], [Resource], [Random], [Exec], [Summon], [Support], [Area].

## 0.6 Progression & Unlock Requirements
1 Passive; 2 Tactical (300 themed actions OR 5 themed mobs); 3 Movement (T uses + traversal metric); 4 Ultimate (impact threshold OR ritual); 5 Reform (Soul XP + trial); 6–9 Subclass tiered gating (resource & usage); 10 Weapon forge; 11 Final Stand readiness (weapon encounters + interaction value). Command: `/soul progress`.

## 0.7 Action Counter Examples
(Representative subset)
| Soul | Tactical Counter | Movement Metric | Rite Example |
|------|------------------|-----------------|--------------|
| Wrath | Damage while <60% HP | Burning distance | Smelt 128 ores |
| Serenity | Hearts healed | Cleansing blink distance | Purity tonics offering |
| Greed | Containers looted | Distance with ≥32 gold | Sacrifice 64 gold blocks |
| Pride | Ally buff seconds | Steps w/ ≥2 allies buffed | Defend Standard |
| Despair | Rot/Wither seconds | Corridor length | Infect 50 mobs |
| Hope | Ally hearts healed | Vertical ascent in aura | Ignite Dawn Beacon |
| Chaos | Random roll events | Warp chain distance | Stabilize Rifts |
| Order | CC seconds | Phased blocks passed | Align Seals |

## 0.8 Resource Systems (Caps 100 unless noted)
| Key | Resource | Gain (Summary) | Spend / Effect |
|-----|----------|----------------|----------------|
| Heat | Damage while low HP | +1% dmg /10; Meteorfall purge |
| Cinder | Fire ticks/backstab | Add meteors |
| Bloodlust | Lifesteal overflow | Negate Exhaust |
| Harmony | OOC regen ticks | Empower Sanctuary |
| Resonance | Reflects | Dome capacity |
| Wealth | Inventory weight/loot | Passive Pillage scaling |
| Gilded | Ore scans | Extend Treasurefall |
| Hunger | Devour tokens | Feast speed |
| Renown | Ally buff & duels | Crown scaling |
| Resolve | Alone uptime | Empower Solitude ult |
| Decay | Rot/Wither diversity | Field radius |
| Blight | Rot applications | Blightstorm duration |
| Terror | Blind/Fear procs | Dreadfall burst |
| Light | Healing output | Beacon shield doubling |
| Martyr | HP sacrifice | Pyre persistence |
| Instability | Random rolls | Riftstorm pull |
| Entropy | Debuff spreads | Reality Fracture bias |
| VoidCharge | Void patch dmg | Singularity KB immunity |
| Balance | Distinct ability usage | Edict silence scaling |
| Temporal | Cooldown skips | Rewind depth |
| Legacy | Kills/assists | Banner extension |
| Valor | Duel uptime | Colosseum radius |
| Inspiration | New scans | Arcane Experiment rare buff |
| Fracture | Debuff apps | Tome empowerment |
| Deception | Disguise/invis uptime | Illusion clone count |
| LuckDebt | Enemy whiffs | Wheel reroll |
| Growth | Plant ticks | Nature’s Wrath extra strikes |
| PackSpirit | Active wolves time | Stampede herd lines |
| SoulMeter | Kills/executes | Beam scaling |
| NullSat | Buffs stripped | Oblivion lock |

API Hooks: GainResourceEvent, SpendResourceEvent, ThresholdCrossEvent, ResourceDecayTick.
