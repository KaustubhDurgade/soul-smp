# Ability Mechanics Specification

Version: 0.1 (First-pass balancing draft)  
Scope: Mechanical (numbers, cooldowns, durations, formulas, status effect definitions).  
Style: (P) Passive, (T) Trigger/Basic Active, (M) Mobility/Movement, (U) Ultimate.  

Note: This monolithic file is being decomposed into `spec/souls/<soul>/<path>/<ability>.md` fragments for readability. Treat this as the authoritative aggregate until migration completion.

## 0. Global Systems & Conventions

### 0.1 Core Cooldown Tiers (Baseline Guidelines)
These are baselines before modifiers (Balance/Order passives, weapon reductions, time effects, etc.). Individual abilities may deviate.

| Tier | Category | Typical CD | Notes |
|------|----------|-----------:|-------|
| Passive | Always on | – | May have internal proc cooldown (ICD) |
| Minor Active (T) | Basic utility/damage | 14s | Range 10–20s variance |
| Movement (M) | Mobility / reposition | 12s | 8–18s variance depending on power & damage |
| Major Utility | Control / big heal / heavy CC | 35s | Often the (T) for control classes |
| Ultimate (U) | Signature | 110s | Range 90–150s (offense shorter, huge impact longer) |
| Final Stand | Death-triggered | Per life | May soft-lock reactivation for 15 real minutes after proc if resurrect mechanic |

### 0.2 Scaling & Formula Conventions
- Heart = 2 HP. Percentages reference max HP unless stated.
- True Damage: Bypasses armor & protection enchants (still prevented by full invulnerability).
- Empowered Weapon Hit Damage Bonus: Additive percent to post-armor final damage, unless stated.
- Lifesteal: Heals post-damage-dealt; cannot exceed missing health.
- Cooldown Reduction Effects (CDR) are multiplicative and clamp at 50% total reduction.
- Diminishing Returns: Stacking slows/roots from different sources add duration up to 150% of longest single source maximum.

### 0.3 Status Effects (Custom)
| Name                          | Mechanics                                                                                                                                                                                                                                                            |
| ----------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Rot (Level X)                 | While active: Reduce visible max hearts by X (hearts hidden not actually lost). When Rot ends, hearts return proportionally. If current HP > new temporary max, excess becomes Overflow (decays at 1 HP/s). Stack refreshes duration; higher level overwrites lower. |
| Silence                       | Cannot activate abilities (T/M/U/Weapon actives); passives continue.                                                                                                                                                                                                 |
| Nullify                       | Removes and prevents reapplication of specified buff categories for duration.                                                                                                                                                                                        |
| Stasis                        | Entity frozen: no movement, actions, rotation, or damage output. Takes +10% incoming damage unless otherwise stated.                                                                                                                                                 |
| Reflection                    | Returns a % of pre-mitigation damage as same type (capped at 25 raw). Does not trigger attacker lifesteal on reflected portion.                                                                                                                                      |
| Blind                         | Use blundness/darkness effect                                                                                                                                                                                                                                        |
| Reveal                        | Shows target HP & ability cooldown snapshot (non-live updating) for 5s.                                                                                                                                                                                              |
| Soul Meter                    | Numeric resource (0–100). Generated via specified actions. Certain ultimates scale with current value then consume (or partially consume).                                                                                                                           |
| Disguise                      | Overrides nameplate & skin server-side; breaks on damage dealt or taken (unless flagged otherwise).                                                                                                                                                                  |
| Domain                        | Area with persistent rule alterations (buffs/debuffs) anchored to entity, block, or location.                                                                                                                                                                        |
| Temporal Rewind Buffer        | Circular buffer storing last N seconds of: health snapshots, position, potion effects (whitelisted).                                                                                                                                                                 |
| Chains                        | Immobilize + disables jump/sprint + pearl/chorus usage.                                                                                                                                                                                                              |
| Execute Threshold             | If target current HP <= threshold (true hearts), effect triggers elevated damage or instant kill.                                                                                                                                                                    |
| Fear (Terror)                 | Random 1–3 block strafing movement override, cannot sprint toward source, -10% damage dealt.                                                                                                                                                                         |
| Confusion                     | Reversed horizontal look delta & occasional (every 0.75s) 10° aim jitter.                                                                                                                                                                                            |
| Item Corrupt (Gold Transmute) | Temporarily transforms item material & stats; reverts after duration.                                                                                                                                                                                                |
| Void Lock                     | Prevents ender pearls, chorus fruits, rift/void/teleport abilities for duration.                                                                                                                                                                                     |

### 0.4 Interaction Rules
- Silence overrides channeling & cancels charging abilities (refund 50% cooldown if <25% elapsed). 
- Reflection cannot reflect Ultimates; instead converts 25% of reflected ultimate damage into shielding.
- Disguise vs Reveal: Reveal immediately breaks disguise & sets 5s internal immunity to disguised reapplication.
- Time Freeze/Stasis cannot overlap; the stronger (longer remaining) persists; weaker ends.
- Blink / Teleport pathing: If obstructed arrival block is unsafe, attempts adjacent safe block search radius 2.

### 0.5 Balance Tags
To assist future balancing automation, each ability has Impact Tag(s): [Damage], [Heal], [Control], [Mobility], [Defense], [Resource], [Random], [Exec], [Summon], [Support], [Area].

### 0.6 Progression & Unlock Requirements
Progression adds RPG depth; thresholds configurable.

Tier Ladder:
1. Passive (P) – Granted on Soul pick.
2. Tactical (T) – Perform 300 Soul-Themed Actions (see 0.7) OR defeat 5 themed mobs.
3. Movement (M) – Use Tactical 120 times AND complete Traversal Metric (distance / special condition) OR Movement Trial.
4. Ultimate (U) – Accrue 2,500 impact (damage + healing + controlSeconds×2) OR craft Rite Catalyst and perform altar ritual.
5. Reform (Subclass Select) – Reach 10,000 Soul XP + finish Soul Trial instance.
6. Subclass Passive – Immediate after Reform.
7. Subclass Tactical – Trigger subclass passive 180 times OR gather path resource ≥60.
8. Subclass Movement – Path traversal metric (unique per path) OR Micro Trial.
9. Subclass Ultimate – Complete Path Trial OR reach path resource 100 & spend 80.
10. Soulbound Weapon – Forge with Path Keystone (Trial boss drop) + base weapon + 3 catalysts.
11. Final Stand – Use Soulbound weapon in 3 Major Encounters & accumulate 8,000 interaction value (damage + healing + support seconds). One activation per life.

Command: `/soul progress` lists counters and unlock status.

### 0.7 Soul-Themed Action Counter Examples
| Soul | Action Counter (T Unlock) | Movement Metric (M Unlock) | Ultimate Rite Example |
|------|--------------------------|----------------------------|-----------------------|
| Wrath | Damage while <60% HP | Distance traveled while burning | Smelt 128 ores in Ritual Forge |
| Serenity | Hearts healed allies | Cleansing blink distance | Brew & offer 32 Purity Tonics |
| Greed | Containers looted | Distance carrying ≥32 gold | Sacrifice 64 gold blocks |
| Pride | Ally buff seconds / duels | Steps with ≥2 allies buffed | Defend Royal Standard waves |
| Despair | Rot/Wither seconds inflicted | Corridor (shadow tunnel) length | Infect 50 mobs event |
| Hope | Hearts healed in aura | Vertical ascent in light aura | Ignite Dawn Beacon |
| Chaos | Random roll events | Warp chain distance | Stabilize 5 Rifts |
| Order | CC seconds applied | Phased blocks passed | Align 4 Temporal Seals |
| Glory | Glory stacks (kills) | Leap arcs over statues | Light 3 Legacy Flames |
| Curiosity | Unique scans (block/biome) | Insight pivots executed | Compile 8 Research Pages |
| Trickery | Illusions spawned/broken | Mirage path segments | Flawless Masquerade Trial |
| Nature | Root/entangle seconds | Vine swings / foliage contacts | Grow 256 plants ritual |
| Silence | Abilities silenced | Silent steps (no sound zone) | Extinguish 40 buffs rite |

### 0.8 Unique Resource Systems (Caps 100 unless noted)
| Soul/Path         | Resource          | Gain                                    | Effect / Spend                          |
| ----------------- | ----------------- | --------------------------------------- | --------------------------------------- |
| Wrath             | Heat              | Damage <60% HP & damage taken           | +1% dmg per 10; Meteorfall clears 40%   |
| Ash               | Cinder Meter      | Fire DoT ticks/backstab crit            | +2 meteors /20 spent                    |
| Blood             | Bloodlust         | Lifesteal overflow & kills              | Overdrive spend 50 negate Exhaust       |
| Serenity          | Harmony           | Out-of-combat regen seconds             | Sanctuary spend 50 +20% dome HP         |
| Reflection        | Resonance         | Successful reflects                     | +2 capacity Dome /30 spent              |
| Greed             | Wealth Value      | Weighted inventory & loot               | Pillage extra buff /30 wealth (passive) |
| Gold Path         | Gilded Charge     | Nearby ore scans                        | Treasurefall spend 60 +2s               |
| Gluttony          | Hunger Momentum   | Tokens gained                           | Feast spend 30 +10% drain speed         |
| Pride             | Renown            | Ally buff seconds / duels               | Crown gains +1% dmg /20 Renown          |
| Solitude          | Resolve           | Alone seconds / solo kills              | Wrath spend 80 ignore fatigue           |
| Despair           | Decay Index       | Unique Rot/Wither targets               | Plague Field +2% radius /20             |
| Rot Path          | Blight Saturation | Rot level applications                  | Blightstorm spend 60 +2s                |
| Night Path        | Terror Charge     | Blind/Fear applies                      | Dreadfall spend 40 initial Fear burst   |
| Hope              | Light Reservoir   | Ally healing (1 per 2 HP)               | Beacon spend 50 double shields          |
| Flame Path        | Martyr Gauge      | Self HP sacrificed                      | Pyre spend 60 persistent Sunflare       |
| Chaos             | Instability       | Random rolls                            | Riftstorm pull +25% at 80               |
| Discord           | Entropy           | Debuff spreads                          | Reality Fracture spend 50 bias positive |
| Void Path         | Void Charge       | Void patch damage                       | Singularity spend 70 KB immunity        |
| Order             | Balance           | Distinct ability usage                  | Edict +0.1s silence /15 (cap +2s)       |
| Clockwork         | Temporal Charge   | Cooldown skips / proc                   | Rewind spend 50 +1s history (max5)      |
| Glory             | Legacy Sparks     | Kills & assists                         | Banner spend 60 +4s persist             |
| Champion          | Valor             | Duel uptime                             | Colosseum spend 80 +2 radius            |
| Curiosity         | Inspiration       | New scans                               | Arcane Experiment spend 40 rare buff    |
| Madness           | Fracture          | Debuff applications                     | Tome spend 70 empowered summon          |
| Trickery          | Deception Layers  | Disguise/invis seconds & decoys survive | Illusion +1 clone /40 (cap +2)          |
| Misfortune        | Luck Debt         | Enemy whiffs (Hex)                      | Wheel spend 60 reroll                   |
| Nature            | Growth Charge     | Plant growth ticks                      | Nature’s Wrath spend 70 extra strikes   |
| Beasts            | Pack Spirit       | Active wolf seconds                     | Stampede spend 60 +2 herd lines         |
| Silence Reaper    | Soul Meter        | Kills & executes                        | Beam scaling                            |
| Silence Emptiness | Null Saturation   | Buffs stripped                          | Oblivion spend 60 +2s lock              |

API Hooks: GainResourceEvent, SpendResourceEvent, ResourceDecayTick.

---
# 1. Soul of Wrath
Theme: Aggressive damage & berserk amplification.
Unique Resource: Heat.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Burning Spirit | Passive | – | +15% outgoing damage while below 60% HP. Snapshotted per hit at wind-up. |
| T | Ignition | Active | 16s | Enflame weapon 6s: each hit adds 2s Fire (level 1) + +10% durability damage to enemy armor. Refreshes own fire timer on multi hits. |
| M | Inferno Chain Surge | Movement | 15s | Fire chain 5 blocks; on latch slingshot 3 blocks past leaving burning tether (1 HP/s slow 20% 3s). Recast within 2s to snap back detonating tether (6 HP fire). +5 Heat on first hit. |
| U | Meteorfall | Ultimate | 105s | Target 12-block radius (range 20). 5 meteors over 3s, each 6 HP AoE (2 block radius) + sets fire 3s. Player at center hit twice max. Total single target cap 18 HP (pre-mitig). |

## Subclass: Path of Ash
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Cinder Veins | Passive | – | Your fire & lava-based damage ignores fire resistance & adds +10% true damage portion (cap 4 HP per hit). |
| T | Flame Surge | Active | 18s | Erupt 3 fire pillars (line forward, 3 block spacing). Each pillar: 5 HP AoE + 2s Fire II, small vertical knock (0.5). |
| M | Ash Glide | Movement | 20s | Glide 1.8s (slow fall). Enter True Invisibility 1.2s mid-glide (attacking ends early). First attack from invis: guaranteed crit (1.5×) + if backstab (attacker behind 120° cone) deal +6 HP true ignoring bonus hearts. |
| U | Infernal Storm | Ultimate | 120s | 12 random micro-meteors in 6 block radius over 4s: each 4 HP + 1s Blind if direct. Ignite ground patches (soulsand style) for 6s dealing 1 HP /s. Single target cap 28 HP (pre-mitig). |
| Wpn | Cinderblade | Weapon | 24s internal | Heavy swing alt-fire (hold 0.6s) causes 2.5 block radius explosion (6 HP + 1.5 block knock) & +25% armor dura damage. Stores up to 2 charges. |
| FS | Worldscar Caldera | Final Stand | Per life | On death erupt radius 12 replacing surface with basalt/magma, spawn 6 lava vents (3 HP geyser /2s for 10s) & ash blinds 1s pulses. Specter channel 8s: if ≥3 enemies remain inside you reform at 30% HP; else terrain persists 30m. |

## Subclass: Path of Blood
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Thirst for War | Passive | – | 5% lifesteal (post-mitig) capped 2 HP per hit. +1% per 2 missing hearts (max +10%). |
| T | Blood Rend | Active | 14s | Two spectral slashes (0.25s spacing). Each: 4 HP true to target + drains 2 saturation + 1.5 hearts ignoring armor (true). Projectile range 12 blocks. Hitting same target twice applies Bleed (3 HP over 3s). |
| M | Frenzy Leap | Movement | 15s | Leap 7 blocks. First enemy landed near (2.5 radius) takes 5 HP & heals you 50% damage dealt (pre-cap). |
| U | Crimson Overdrive | Ultimate | 95s | 10s Berserk: -4 max hearts (visual removal), Gain Strength III (approx +260% base melee), Resistance I, Speed IV. On end apply Exhaust (-15% damage 5s). Cannot be used below 6 hearts current. |
| Wpn | Bloodfang | Weapon | – | On kill: Adds +1 Sharpness level (max +5 above initial). Loses 1 stack per death. Alt bite (20s cd): 3 HP true + 3 HP heal. |
| FS | Crimson Deluge | Final Stand | Per life | On death saturate radius 10 blood field (slow 25%). Spawn 5 blood geysers pulsing every 1.5s (3 HP) for 20s. Spectral you channels 6s collecting Vitality (1 per enemy per 2s). If ≥8 collected you reform at 40% HP & apply Bleed (6 over 6s) to enemies inside; else field persists 20m as slowing area. |

---
# 2. Soul of Serenity
Focus: Sustain healing, cleansing, defense.
Unique Resource: Harmony.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Still Waters | Passive | – | Out of combat (no damage dealt/received 8s): Regen I + +50% natural saturation regen rate. |
| T | Soothing Breeze | Active | 12s | 4 block radius AOE: Heal allies 3 hearts + cleanse negative vanilla potion effects (excl. Wither). |
| M | Tranquil Veil Step | Movement | 13s | Deploy Veil Anchor at target block ≤10 (LOS). Reactivate within 4s to blink there cleansing self & allies (2 block) & grant 2s 20% damage reduction. If you heal ≥8 HP before reactivation, arrival also grants 2s AOE invis (breaks on action) to allies. Cancellation (sneak) refunds 70% CD. |
| U | Sanctuary | Ultimate | 120s | Dome radius 6 for 10s: Allies gain Regen II (tick 2 HP/2.5s) + -15% damage taken. Enemies inside -10% damage. Dome HP 60 (invisible); enemy projectile hits deduct 5 HP. |

## Subclass: Path of Renewal
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Blooming Grace | Passive | – | Your direct heals also purge 1 custom debuff (silence, rot level ≤2, bleed). 6s ICD per target. |
| T | Nature’s Touch | Active | 10s | Target ally (range 12): Heal 4 hearts instantly + 2 hearts over 4s (HoT). |
| M | Verdant Stride | Movement | 18s | Channel 0.6s root self & target ally (opt-in sneak) granting both +40% heal received for 5s. If no ally, self-only with +20%. |
| U | Circle of Renewal | Ultimate | 150s | 6 block radius. Revive all ally corpses (last 90s) at 30% HP; living allies heal 6 hearts + remove Rot up to Level 3. Consumes 60% of your current HP minimum 4 hearts (cannot kill you). |
| Wpn | Staff of Blossoms | Weapon | – | All heals +10% (after passives). Alt Bloom (30s): Plant zone 4 radius 6s adding +2 HP HoT/sec (does not stack with itself). |
| FS | Rebirth | Final Stand | Per life | On death pick: (A) Revive self at 4 hearts OR (B) Sacrifice: instantly revive up to 2 allies in 15 block radius at 50% HP. Choice via sneak toggle in final 0.5s death window. |

## Subclass: Path of Reflection
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Mirror Skin | Passive | – | Reflect 10% pre-mitig damage taken (cap 5 HP per hit). Internal chain ICD 0.5s per source. No knockback on reflected packet. |
| T | Parry | Active | 16s | 0.8s Parry window. If projectile/ability (tagged reflectable) hits: return at 1.5× damage (cap 10 HP) & apply 0.4s stun. If miss: self 0.5s stun (-50% move). |
| M | Prism Step | Movement | 14s | Blink 6 blocks forward & leave a Prism Echo at origin for 3s. Reactivate within 3s to swap positions (2nd press no extra CD). Swapping grants 2s Resistance I. |
| U | Dome of Mirrors | Ultimate | 130s | 7s dome radius 5. Reflects first 6 ranged/projectile or beam ability packets (not ultimates) back to origin (same velocity). Each reflect reduces remaining capacity. When capacity 0: dome shatters dealing 4 HP AOE + Blind 1s to enemies inside. |
| Wpn | Mirror Aegis | Weapon | 32s block cd | Custom shield: Stores up to 10 Mirror Charges. When blocking an ability projectile consumes charges equal to its tier (T=1,M=2,U=4) negating effect. Recharges 1 charge / 6s. |
| FS | Echo Barrier | Final Stand | Per life | On death leave Barrier (stationary) for 10 in-game days? (Adjusted: 30 real minutes). Barrier radius 4 granting allies 10% damage reflect & Resistance I. Despawn early if destroyed (HP 120). |

---
# 3. Soul of Greed
Emphasis: Resource theft, item manipulation, economic pressure.
Unique Resource: Wealth Value.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Golden Hunger | Passive | – | +15% chance for hostile mob extra loot roll (1 reroll table). Players killed: +10% XP yield. |
| T | Drain Touch | Active | 18s | Melee hit brand (5s): every second steal 3 XP + 5 saturation (converted to you if missing) & 10% chance siphon 1 random non-soulbound item (drops). |
| M | Market Shift | Movement | 17s | Place Trade Marker (lasts 6s). Reactivate to instantly swap with it dropping 3 spectral coins that home (each +1 Wealth). If swap occurs within 6 blocks of ≥2 natural ore blocks refund 5s CD. Grants 0.5s invuln frames + Speed II 2s after arrival. |
| U | Pillage | Ultimate | 110s | 6 block radius. Steal all active positive potion & custom buff tags from enemies (max 5 buffs) applying them to you 8s (if duration >8 truncate) while suspending enemies’ copies. After 8s they return with -4s remaining each. |

## Subclass: Path of Gold
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Gilded Aura | Passive | – | Within 6 blocks of natural ore block or stored gold block carried: +8% damage +8% damage reduction. |
| T | Midas Grasp | Active | 22s | Target held item (enemy main hand) transforms into Gold Nugget for 5s (silently stored). User cannot target soulbound / cursed. If armor selected (sneak): armor slot becomes Golden variant stats for 5s. On revert items unchanged. |
| M | Greed Pull | Movement | 14s | Magnetize 6 block radius items to you over 1s; enemy thrown weapons pulled mid-air. If ≥8 items pulled gain Speed II 3s. |
| U | Treasurefall | Ultimate | 125s | 7s rain (radius 6). Each second spawn 5 gold clusters; each cluster explodes (2 block radius, 4 HP) & has 30% chance drop 1 gold nugget (non-doubling). Single target cap 24 HP. |
| Wpn | Gilded Greataxe | Weapon | – | On hit steal lowest-tier enchant from target weapon/armor (20s ICD per target). Alt Melt (28s): absorb 1 enchant to axe (if compatible) stacking (max 5). |
| FS | Golden Catastrophe | Final Stand | Per life | On death 3s delay then 9 gold comets impact radius 5 (each 5 HP + ignite). For each player hit drop 1 Gold Block. |

## Subclass: Path of Gluttony
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Endless Appetite | Passive | – | Critical hits restore 2 saturation & 0.5 heart (ICD 0.5s). At full saturation gain +5% damage. |
| T | Devour | Active | 16s | Consume nearest 3 dropped items prioritizing food/potions. Each grants 4s Buff Token: Food = +5% damage; Potion = replicate strongest positive effect 4s; Other = +2% lifesteal (stack). Max 5 tokens. |
| M | Maw Dash | Movement | 12s | 5 block chomp dash. First enemy: 4 HP + heal 50% damage & steal 1 hunger (1 saturation). |
| U | Feast of Souls | Ultimate | 115s | 5 block radius drain over 4s: Each enemy tick (per second) lose 1 heart (true) + 2 saturation; you gain half as healing (true heal) & fill Feast Meter (for visual effect). Total per enemy cap 6 hearts. |
| Wpn | Vorpal Maw | Weapon | – | On hit +20% durability damage to enemy armor. Devour synergy: Each token adds +1 HP chomp bonus (applies to Maw Dash & weapon alt-bite / 18s). |
| FS | Last Feast | Final Stand | Per life | On death consume all dropped items radius 5 granting +1 HP true explosion per item (cap 20 HP total). Heals trusted allies caught in blast for half. |

---
# 4. Soul of Pride
Dual path: Leadership vs Solo empowerment.
Unique Resource: Renown.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Commanding Presence | Passive | – | Trusted allies within 8 blocks gain +5% damage. If none nearby you gain +3% damage. |
| T | Challenge | Active | 20s | Target player (range 12). For 5s target deals -10% damage to allies other than you & you deal +10% to them. If they hit only you for full duration you are Marked (next challenge +5s CD). |
| M | Imperial Advance | Movement | 16s | Project 5-tile procession path (1.2s travel). You stride it unstoppable (immune to slows) gaining +10% damage during. Allies stepping on tiles gain +15% speed & 10% KB resist 4s. Recast within 2s to plant Rally Standard at end granting +5% damage 6s (no extra CD). +5 Renown if ≥2 allies buffed. |
| U | Crown of Dominion | Ultimate | 120s | 10s aura radius 8. Allies: +15% damage, +10% damage reduction. You: +10% additional damage per trusted ally affected (cap +40%). After ends apply Fatigue (-10% damage) 8s. |

## Subclass: Path of Authority
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Bannerlord | Passive | – | For each trusted ally within 10 blocks (max 4): +2% damage & -2% damage taken (cap +8/-8). |
| T | Royal Decree | Active | 26s | Buff up to 4 nearby allies (radius 8): +20% damage for 8s. You gain Shield = 2 hearts per ally affected (decays after 8s). |
| M | Sovereign Pathstride | Movement | 18s | Lay 7 block gilded path (5s). Allies on path: Speed II + Regen I (2s refresh). First enemy crossing slowed 30% 2s. If Warbanner active each ally tick extends banner 0.5s (cap +4s). |
| U | Warbanner | Ultimate | 135s | Place Banner (HP 80). 10s zone radius 7: Allies +15% damage & Regen I; enemies -10% speed & -5% damage. Destroying banner early causes 4 HP backlash to destroyer. |
| Wpn | Crownblade | Weapon | 30s aura cd | Unsheathe (swap to) triggers 6s aura (radius 6): +5% damage & +5% damage reduction to allies. 30s internal. |
| FS | Monument of Sovereigns | Final Stand | Per life | On death erect marble dais radius 6 with Banner (HP 120) persisting 20m unless revival. Trusted allies inside: +10% damage & +10% DR; enemies: -10% damage. Accrue Ally-Time (ally seconds). If ≥360 ally-seconds you reform at 35% HP shattering (6 HP AOE, knock 1). Else dais persists full duration. |

## Subclass: Path of Solitude
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Lone Glory | Passive | – | No trusted ally within 15 blocks: +12% damage, +8% damage reduction. If an ally enters range buff fades over 3s. |
| T | Duelist’s Strike | Active | 14s | If only you & target mutually damaged each other in last 3s: strike deals +30% damage & grants Resistance I 2s. Else normal attack +6s CD penalty. |
| M | Dominion Pivot | Movement | 17s | Mark spot, leap 5 blocks. Within 1.5s or on recast swap back emitting shockwave (3 HP, knock 1) excluding most recent damaged enemy (instead Slow 25% 2s). Gain +5% damage 4s if no allies within 15 blocks when swapping. |
| U | Emperor’s Wrath | Ultimate | 100s | 8s personal buff: +30% damage, +20% damage reduction, immunity to knockback, each kill during extends 2s (cap +6). After end self -15% damage 6s. |
| Wpn | Emperor’s Blade | Weapon | – | Damage scales +1% per consecutive 3s without a trusted ally near (cap +15%). Resets when an ally enters 10 blocks. |
| FS | Silent Arena | Final Stand | Per life | On death generate obsidian ring radius 8 isolating nearest enemy (others repelled). 8s duel barrier prevents entry/exit. If you kill target you reform at 30% HP; else ring persists 25m granting +5% DR to occupants (excludes you if dead). |

---
# 5. Soul of Despair
Mixed rot/decay and fear/shadow control.
Unique Resource: Decay Index (base). Rot Path: Blight Saturation. Night Path: Terror Charge.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Withering Touch | Passive | – | 20% chance on melee to apply Wither I 2s (ICD per target 6s). |
| T | Decay Bolt | Active | 13s | Hitscan (range 18). Apply Wither II 8s (4 HP over time) + 1 Rot Level 1 (4s). |
| M | Umbral Corridor | Movement | 15s | Create shadow tunnel length 6 (0.8s). Rush to end; tunnel persists 3s: enemies entering Blind 1s & take 1 HP shadow (no stack). Recast within 2s to reverse. +3 Decay Index if ≥2 enemies affected. |
| U | Plague Field | Ultimate | 115s | 8s corrupted zone radius 6: Apply Rot Level 2 (duration 6s refresh) + Wither I + -20% healing received. |

## Subclass: Path of Rot
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Fungal Growth | Passive | – | Each melee applies Rot Stack (Level 1, 6s). At 3 stacks upgrade to Level 2 (6s). Max Level 3 via abilities. |
| T | Spore Cloud | Active | 14s | 3 block radius lingering 4s: Enemies entering gain Rot Level 1 (4s). |
| M | Rootcrawl Advance | Movement | 18s | Send rooting wave 5 blocks; first enemy rooted 2s & you pull 3 blocks toward it (20% DR during). Apply Rot Lv1 (refresh). +2 Blight Saturation per rooted enemy. |
| U | Blightstorm | Ultimate | 130s | 5s storm radius 7: Applies Rot Level 3 (duration 6s after exit). Deals 1 HP true /s & slows 20%. |
| Wpn | Moldfang Scythe | Weapon | 22s pull | Alt ability: Hook (range 10) pulls target 4 blocks & applies Rot Level 2 4s. Basic hits add +1s rot duration. |
| FS | Blighted Crater | Final Stand | Per life | On death corrupt radius 10 terrain (mycelium/decay) spawning 5 vents (1 HP + Rot Lv1 /s 12s). Specter channel 7s: if ≥5 enemies rot-tagged you reform at 30% HP & vents upgrade (Rot Lv2 + slow 15%). Else crater persists 30m applying -10% healing to enemies standing. |

## Subclass: Path of Night
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Terror Gaze | Passive | – | First hit on a player after 8s out of combat blinds 1s (ICD 8s per target). |
| T | Shadow Dagger | Active | 12s | Throw dagger (speed 1.2, range 15). 5 HP + Blind 1.5s. Returns if hits. |
| M | Eclipse Veil | Movement | 17s | Place Shadow Anchor (range 6) & become true invis 1.5s (+20% speed). Reactivate/timeout: appear at anchor emitting darkness (Blind +10% slow 2s). First attack within 2s +30% vs Blinded. +4 Terror Charge if ≥2 enemies blinded. |
| U | Dreadfall | Ultimate | 120s | 6s darkness field radius 6: Enemies receive Blind overlay & you deal +20% damage to them. Leaving field removes buff/debuff instantly. |
| Wpn | Dagger of Eclipse | Weapon | – | Critical hits inflict Blind 1s (ICD 5s per target). Alt vanish (20s): 1s invis + next attack guaranteed crit. |
| FS | Eternal Dusk Obelisks | Final Stand | Per life | On death spawn 3 obelisks (triangle radius 8) projecting darkness field. Every 4s enemies inside Fear 1s first time per cycle. Destroy all 3 (HP40 each) within 90s to keep you dead; if any survive you reform at 25% HP & remaining obelisks persist 20m (reduced Fear every 8s). |

---
# 6. Soul of Hope
Light, protection, sacrificial damage.
Unique Resource: Light Reservoir (base). Flame Path: Martyr Gauge.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Guiding Light | Passive | – | Allies within 6 glow (client) & gain Regen I if you have not taken damage in last 6s. |
| T | Radiant Beam | Active | 12s | Ray range 14: If ally heal 3 hearts. If undead enemy: 5 HP holy damage & 1s Weakness I. |
| M | Solar Arc Glide | Movement | 15s | 1.2s glide arc (ascend 2 then descend). Trail grants allies 2 HP shield (stack to 6) + Speed I 3s. Mid-glide recast (once) dive burst heals allies below 4 hearts +2 HP. +5 Light Reservoir if ≥2 allies shielded. |
| U | Beacon | Ultimate | 115s | Pillar (radius 5, 10s). Allies: +10% damage, +10% speed, +2 hearts absorb shield. Enemies undead take 2 HP /s radiant. |

## Subclass: Path of Radiance
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Shining Presence | Passive | – | Allies under one of your buffs gain Knockback Resistance 40%. |
| T | Solar Wave | Active | 14s | 6 block frontal cone heal 3 hearts allies & 4 HP damage undead (ignite 2s). |
| M | Aegis Path | Movement | 17s | Project 6 block radiant path (5s). Crossing grants 1-hit barrier (≤6 HP) +10% speed 4s. If 3 barriers consumed within 6s reduce Aurora Dome CD 6s. |
| U | Aurora Dome | Ultimate | 130s | 8s dome radius 6: Allies inside take -25% damage & purge negative effects every 2s (cleanse cycle). Dome HP 70 vs enemy attacks. |
| Wpn | Sunblade | Weapon | – | Aura radius 4: Allies +5% damage. Alt flare (22s): Emit flash 4 HP holy & Blind undead 2s. |
| FS | Pillar of Dawn | Final Stand | Per life | On death create radiant pillar radius 7 channel 8s. Allies inside heal 2 HP/s & +10% DR. If ≥40 ally-seconds accrue you reform at 35% HP; else pillar persists 25m granting regen 1 HP/2s to allies. |

## Subclass: Path of Flame
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Burning Faith | Passive | – | Unarmed hit on ally heals 1 heart & deals 1 heart true to you (ICD 2s per ally). |
| T | Firebrand | Active | 13s | Slash projectile 10 blocks: 5 HP fire + self cost 1 heart (true). Enhanced if below 40% HP (+2 HP). |
| M | Sacrificial Flare Vault | Movement | 14s | Vault 5 blocks leaving flame arc 4s (1 HP/s). Lose 1 heart true; first tick on each enemy refunds 2s CD & +5 Martyr Gauge (once/enemy). If <40% HP distance +2. |
| U | Martyr’s Pyre | Ultimate | 105s | Self-sacrifice 4 hearts true to create 6 block explosion (8 HP fire center scaling to 4 min) & apply +20% damage buff to allies 6s. Cannot cast below 5 hearts. |
| Wpn | Pyrelance | Weapon | 18s empower | Empower (toggle): each hit consumes 0.5 heart true to add +2 HP fire (tick). Disable if <4 hearts. |
| FS | Phoenix Crucible | Final Stand | Per life | On death ignite crucible ring radius 6 (10s 2 HP/s fire to enemies). Spectral phoenix channels 7s consuming Martyr Gauge (1/0.2s). If ≥40 consumed reform at 30% HP with 6 HP fire nova; else zone persists 30m granting allies +10% fire damage. |

---
# 7. Soul of Chaos
Randomness & void control.
Unique Resource: Instability (base). Discord Path: Entropy. Void Path: Void Charge.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Chaotic Aura | Passive | 30s cycle | Every 30s roll random set (1 buff + 1 debuff) from curated tables. If duplicate debuff would occur, reroll once. |
| T | Unstable Burst | Active | 15s | AOE radius 4: Roll effect table (weights) – 60% moderate effect (4 HP dmg or minor heal allies), 25% strong (6 HP + knock or cleanse allies), 10% off-soul mimic (copy random other base T), 5% backlash (self 4 HP). |
| M | Fracture Warp | Movement | 12s | Blink 5 blocks then (0.4s window) optional recast to blink additional 5 with ±1 block lateral fracture offset. Each blink leaves 2s distortion slowing 15%. If both blinks used spawn mini rift 2s pulling 0.5 block/s. +4 Instability if rift affects ≥2 enemies. |
| U | Riftstorm | Ultimate | 120s | 6s unstable rift radius 5 pulling entities 0.7 block/s to center & dealing 2 HP /s (true on 6th second). On collapse knockback 4 blocks. |

## Subclass: Path of Discord
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Entropic Gift | Passive | – | Your positive random buffs +50% duration; debuffs applied by you have 25% chance to spread (radius 3) at half duration. |
| T | Wild Surge | Active | 16s | Target entity (range 10). 60% buff ally +15% damage 6s; 35% debuff enemy -15% damage 6s; 5% backlash silence self 2s. |
| M | Chaotic Sprint | Movement | 14s | 4s speed state (Speed III). Each second leaves random patch (slow, minor explosion 2 HP, heal 1 heart allies, or blind smoke 1s). |
| U | Reality Fracture | Ultimate | 135s | Roll outcome tier -4 to +4: -4 Self 10 HP dmg & Silence 4s; -3 Self 6 HP; -2 Nothing; -1 Minor 4 HP AOE; 0 Null; +1 6 HP AOE; +2 10 HP AOE + slow; +3 14 HP AOE + knock 2; +4 Cataclysm 20 HP true (cap 14 target) + pulls. Distribution bell skewed to 0. |
| Wpn | Entropy Shard | Weapon | – | On hit 30% random effect: (heal self 1 heart, deal +2 HP, apply 1s slow, self -1 HP). |
| FS | Rift Cataclysm | Final Stand | Per life | On death open unstable rift field radius 10 (15s) cycling anomalies every 3s (pull, knock, random buff, random debuff). If 4+ anomalies complete without you being struck by enemy ability you reform at 30% HP; else field persists 25m, instability surges (random minor events every 20s). |

## Subclass: Path of Void
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Phase Step | Passive | 12% roll | 12% chance on being damaged to negate damage & teleport 1 block sideways. ICD 2s. |
| T | Rift Slash | Active | 11s | Void blade: 6 HP true (ignores armor) frontal 3 block arc. |
| M | Void Lattice Step | Movement | 10s | Blink 4 blocks & spawn lattice node. Within 3s recast to chain up to 2 additional nodes (max 3) forming triangle; third cast collapses lattice pulling enemies inward (2 HP + 0.5 pull). Gain +6 Void Charge if ≥2 enemies pulled. |
| U | Singularity | Ultimate | 125s | 4s mini black hole radius growing 2→5 blocks. Pull 1 block/s, apply Void Lock 3s on exit. Total damage 10 HP (2.5/s). |
| Wpn | Voidripper | Weapon | – | Each third hit applies Armor Sunder (-10% armor value stacking 3, 6s). Alt rip (22s): remove 1 Sunder stack to deal 4 HP true bonus. |
| FS | Void Implosion | Final Stand | Per life | On death create collapsing sphere (radius 8 → 3 over 10s) dealing 1.5 HP/s true & Void Lock 1s per 3s tick. If ≥200 total damage dealt inside you reform at 25% HP; else implosion leaves void scar (ender-like terrain) 30m granting you +10% ability CDR while nearby on future lives. |

---
# 8. Soul of Order
Structure, control, time.
Unique Resource: Balance (base). Clockwork Path: Temporal Charge.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Balanced Flow | Passive | – | -10% cooldowns, -10% damage dealt (applied last). |
| T | Binding Chains | Active | 15s | Root enemy 2s (chain visual) + apply Void Lock 2s + freeze aim (mouse) sensitivity 0 for 1s. |
| M | Axiom Phase March | Movement | 17s | Project linear corridor 6 blocks; you phase walk over 1s (cannot be slowed / displaced). Corridor persists 3s: first enemy crossing is Rooted 1s & grants +3 Balance. Recast within 1s to snap back early (refund 30% CD). |
| U | Edict | Ultimate | 125s | AOE radius 6: Cancel channeling/charging abilities & silence enemies 4s. Movement abilities separately locked 8s. |

## Subclass: Path of Chains
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Shackled Will | Passive | – | Each hit slows target 10% 2s (non-stacking; refresh). |
| T | Iron Bind | Active | 18s | Trap enemy in spectral chains 3s (cannot move, dash, teleport). If already rooted prolong +1s. |
| M | Chain Pull | Movement | 14s | Grapple to enemy (range 12) or terrain; if enemy, applies mini root 0.5s on arrival. |
| U | Prison of Order | Ultimate | 140s | Create cage (chunk anchored height-limited) radius 8 functional boundary 10s: Enemies inside -50% healing & hunger regen paused. Leaving boundary teleports back (once; second attempt allowed). |
| Wpn | Lawkeeper’s Chains | Weapon | 24s multibind | Alt: Launch chain shockwave binding up to 3 enemies (range 8) root 1.5s each (independent). |
| FS | Crystalline Tribunal | Final Stand | Per life | On death erect crystal court radius 8 (10s stasis aura slowing enemies 20% & +10% ability CD). If ≥5 distinct enemies affected you reform at 30% HP; else court persists 25m conferring +5% CDR to allies inside. |

## Subclass: Path of Clockwork
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Temporal Stability | Passive | – | 10% chance to not trigger cooldown when using an ability (roll per usage, cannot proc twice in a row). |
| T | Rewind | Active | 24s | Revert last 3s of damage taken (up to 8 hearts) & negative potion, not death. Uses Time Buffer. If lethal event <0.5s ago can prevent death (1 HP). |
| M | Chronopivot | Movement | 16s | Record anchor & gain 15% speed 2s. Recast (≤4s) to rewind position & negate last 2s fall/void damage + restore up to 4 HP recently lost (not lethal). +4 Temporal Charge if ≥6s of cooldowns reduced during window. |
| U | Stasis Lock | Ultimate | 135s | Freeze enemies radius 5 for 5s (Stasis). Apply immediate +3s cooldown to all non-trusted abilities currently off cooldown (excludes passives). |
| Wpn | Chronoblade | Weapon | – | On hit reduce random (non-ultimate) cooldown by 1s (ICD 1s). |
| FS | Temporal Fracture | Final Stand | Per life | On death freeze time bubble radius 8 (entities slowed 70%, projectiles paused) for 6s while spectral you records damage events. If ≥60 HP total events captured you reform at 30% HP & bubble shatters (4 HP AOE). Else bubble persists 20m applying +10% cooldown to enemies entering. |

---
# 9. Soul of Glory
Fame, legacy persistence.
Unique Resource: Legacy Sparks (base). Champion Path: Valor.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Hero’s Spark | Passive | – | Gain +20% combat XP; kills grant Glory Stack (cosmetic) up to 50. |
| T | Rallying Cry | Active | 15s | 6 block radius allies Speed II 5s & +5% damage 5s. |
| M | Legacy Vault | Movement | 15s | Arc leap (parabolic 7 forward, 3 up) creating Impact Zone 4 radius for 5s: allies entering gain +5% damage 5s (stack 2). Land shockwave 4 HP + minor knock. +4 Legacy Sparks if ≥2 allies buffed. |
| U | Immortal Banner | Ultimate | 120s | Place banner 12s radius 6 giving +10% damage & Regen I to allies. Destroying banner early heals you 4 hearts. |

## Subclass: Path of Eternal Flame
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Eternal Mark | Passive | – | A statue marker persists 10 real minutes where you die (max 3). Visiting grants +5% damage 60s. |
| T | Burning Oath | Active | 16s | Shield ally 6s absorbing 6 hearts; when shield breaks deals 4 HP fire burst to nearby enemies. |
| M | Phoenix Dash | Movement | 13s | Dash 6 blocks leaving 3s flame trail (1 HP/s). If you pass through your statue flame buff +5% damage 5s. |
| U | Flame of Legacy | Ultimate | 135s | Place immortal flame beacon 20s: Allies respawning within time can choose to return at beacon (once). Beacon HP 70. |
| Wpn | Emberblade | Weapon | – | On hit ignite 2s & allies in 3 radius gain +2% damage (stack 5, 6s). |
| FS | Ashen Scar | Final Stand | Per life | On death create flaming crater radius 7 with Eternal Ember (HP 100) channel 12s. Allies inside gain +10% damage & +1 HP/s. If Ember survives full channel you reform at 35% HP; else crater persists 30m granting rally buff on entry (+5% damage 10s). |

## Subclass: Path of Champion
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Arena Mastery | Passive | – | If only you & one enemy have dealt damage to each other in last 5s: +10% damage & -10% damage taken. |
| T | Crushing Blow | Active | 13s | Charged 0.6s heavy strike 6 HP + knockback 1.8; if duel state active +2 HP. |
| M | Valor Rush | Movement | 15s | Target enemy (range 8). Charge in unstoppable line; first enemy hit takes 4 HP & is marked (you +10% damage vs marked 5s). If in duel (+10% damage state) reset 40% CD. +4 Valor on hit. |
| U | Colosseum | Ultimate | 140s | 12s arena (radius 9) prevents exit (teleport back). You gain +20% damage & Resistance I; enemies inside cannot use teleport abilities. |
| Wpn | Champion’s Spear | Weapon | – | In duel: +1 reach block & +10% damage. Alt impale (20s): Root enemy 1s & deal 4 HP. |
| FS | Arena of Legends | Final Stand | Per life | On death raise arena platform radius 10 sealing you & killer (others spectate barrier) 10s. If you (spectral auto-gladiator) win via dealing ≥20 HP before expiry you reform at 40% HP; else platform persists 25m giving allies +5% speed while near. |

---
# 10. Soul of Curiosity
Knowledge reveals, experimentation, madness.
Unique Resource: Inspiration (base). Madness Path: Fracture.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Keen Eye | Passive | – | Highlights invisible entities & trap blocks (TNT, tripwire) within 8 blocks. |
| T | Analyze | Active | 16s | Reveal target (range 15) HP & cooldown snapshot + Mark (you deal +8% damage 6s). |
| M | Insight Pivot | Movement | 14s | Deploy Arcane Anchor (range 6). Within 3s recast to pivot (blink) to anchor granting +12% CDR 5s & revealing nearby enemies (range 8) outlines 2s. If Analyze active when pivoting refund 30% CD & +5 Inspiration. |
| U | Arcane Experiment | Ultimate | 120s | Explosion of 6 random potion effects (positive for allies, 1 negative for enemies) radius 6 lasting 8s (pos) / 5s (neg). |

## Subclass: Path of Discovery
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Alchemical Insight | Passive | – | Unlocks special crafting & +10% potion duration crafted. |
| T | Flask Toss | Active | 12s | Throw flask (arc) that either heals allies 3 hearts or damages enemies 4 HP + Weakness I (decide via crouch). |
| M | Catalyst Jet Spiral | Movement | 17s | Spiral ascent 3 up 5 forward leaving particle helix granting allies +5% CDR 4s (stack 2). Landing splash 3 HP arcane to enemies (radius 2). +4 Inspiration if ≥2 allies buffed. |
| U | Transmute | Ultimate | 150s | Convert up to 5 selected common items in inventory (UI list) into higher tier equivalents (iron→gold, gold→emerald, emerald→diamond shard). Cooldown starts after selection (3s UI). |
| Wpn | Philosopher’s Tool | Weapon | – | Alt transmute (40s): Attempt convert single item held by enemy into random same-tier variant (no soulbound). |
| FS | Transmutation Field | Final Stand | Per life | On death create alchemy field radius 8 converting nearby common blocks (stone → moss, sand → glass) over 12s. Allies inside gain +8% CDR; enemies random debuff 1s every 4s. If ≥30 block conversions succeed you reform at 30% HP; else field persists 25m granting passive +2% CDR to allies. |

## Subclass: Path of Madness
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Insatiable Mind | Passive | – | +30% XP gain, +8% damage taken. |
| T | Eldritch Bolt | Active | 11s | Projectile 14 blocks: 4 HP + random debuff (Blind 1.5s / Slow 2s / Weakness 2s). |
| M | Fracture Drift | Movement | 16s | Sequence 4 micro-blinks (1.2 blocks each) over 0.8s leaving rifts (0.5 HP/ tick mental damage 2s) stacking visual only. During drift 50% projectile phase. +5 Fracture on completion. |
| U | Forbidden Tome | Ultimate | 140s | Summon Eldritch Entity (warden variant or custom) 20s allied. Its damage scaled -30% from vanilla, HP 180, despawns early if you die. |
| Wpn | Tome of Whispers | Weapon | – | On hit 20% chance apply random minor debuff 2s. Alt murmur (25s): AoE Confusion 3s radius 4. |
| FS | Eldritch Bloom | Final Stand | Per life | On death spawn growing anomaly (radius 4→8 over 10s) applying Confusion 3s & Weakness I refresh. If anomaly reaches max size you reform at 30% HP; else bloom persists 25m intermittently whispering (audio) & granting +5% damage to you next life if you die within its radius. |

---
# 11. Soul of Trickery
Illusions, deception, chance manipulation.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | False Image | Passive | – | 20% chance on being hit to spawn decoy (8 HP, lasts 4s) that mimics movement. ICD 6s. |
| T | Smoke Trick | Active | 14s | Smoke cloud radius 4 Blind enemies 1.5s & you gain Invis 1s. |
| M | Quickstep | Movement | 11s | 5 block dash + 1s invis. First hit out of invis +15% damage. |
| U | Grand Illusion | Ultimate | 115s | Create 3 clones 10s (each 50% damage, take 150% damage). Clones mirror your attacks (projectiles cosmetic except 30% chance one is real). |

## Subclass: Path of Masks
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Doppelganger | Passive | – | On hitting enemy first time in 20s, 30% chance auto-disguise as them 5s. |
| T | Mask Swap | Active | 20s | Assume ally form 6s: Allies get +5% damage if near disguised target (ally). Taking or dealing damage breaks. |
| M | Phantom Dash | Movement | 14s | Dash 6 & enter disguise (random enemy seen last 10s) 2s. |
| U | Identity Theft | Ultimate | 140s | Copy target enemy ultimate (range 10) storing for 180s or until used (replaces your U). Single store. Immune to Silence while casting stolen U. |
| Wpn | Maskblade | Weapon | – | Weapon appearance & damage type adapt to current disguise (cosmetic + adds +5% damage vs disguised template). |
| FS | Final Masquerade | Final Stand | Per life | On death disguise as killer 15s (no damage taken reveals until you attack). If you land a killing blow while disguised you resurrect at 30% HP. |

## Subclass: Path of Misfortune
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Cursed Fortune | Passive | – | Enemies within 6 have -10% critical damage & 10% chance crits downgrade to normal. |
| T | Hex | Active | 15s | Curse enemy 6s: 20% chance each melee attempt whiffs (0 damage swing). |
| M | Trickster’s Vault | Movement | 16s | Vault 4 blocks upward & drop illusory gold (no effect) distracting (sound) & granting you Invis 1s mid-air. |
| U | Wheel of Fate | Ultimate | 135s | Roll for each enemy in radius 6: (25% Blind 2s, 25% Slow 4s, 15% Silence 2s, 15% Weakness II 4s, 10% Disarm main hand 1.5s, 10% Jackpot = all previous except Disarm). |
| Wpn | Hexstaff | Weapon | – | On hit apply stacking Misfortune (-2% damage, -1% luck-based rolls) 5s stacking 5. Alt purge (28s): Consume stacks to deal 1 HP per stack true. |
| FS | Gambler’s End | Final Stand | Per life | On death roll global debuff on enemies involved in combat with you (tagged): random 1 of (Blind 3s, Weakness II 6s, Slow II 5s, Silence 2s, Wither I 5s). |

---
# 12. Soul of Nature
Growth, entanglement, beasts.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Verdant Blessing | Passive | – | Crop tick rate +25% in 8 radius; you gain +2% damage per natural plant block within 3 (cap +12%). |
| T | Vine Snare | Active | 13s | Root enemy 1.8s (range 12). |
| M | Vine Whip | Movement | 12s | Grapple to surface (range 14) or pull self to enemy (stops 2 blocks away). Hitting enemy on arrival deals 3 HP. |
| U | Nature’s Wrath | Ultimate | 125s | 8s storm radius 6: Periodic lightning-style strikes (every 1s random 2 spots) each 4 HP + Slow 10% 2s. |

## Subclass: Path of Thorns
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Thornskin | Passive | – | Returns 100% projectile damage (vanilla) & 50% soul projectiles after mitigation (cap 6 HP). |
| T | Vine Lash | Active | 15s | Pull lowest health enemy within 10 blocks to you (stops 1.5 blocks). Auto-trigger if enemy below 4 hearts within 6 (30s internal). |
| M | Bramble Dash | Movement | 15s | Dash 5 blocks leaving brambles 3s slowing 30% & dealing 1 HP/s. First enemy hit rooted 1s. |
| U | Garden of Pain | Ultimate | 130s | Spawn thorn field radius 7 10s: Enemies inside take 1.5 HP/s & on exit take 3 HP burst. |
| Wpn | Thornwhip | Weapon | – | On hit add Entangle Stack (at 3 stacks root 1s & consume). Alt sweep (20s): 4 block arc 4 HP + apply 1 stack. |
| FS | Bloom of Thorns | Final Stand | Per life | On death spawn thorn forest radius 6 12s (same as field but +50% damage). |

## Subclass: Path of Beasts
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Beast Bond | Passive | – | Within 6 blocks of ≥1 animal: +8% damage & +8% move speed. |
| T | Wolf Call | Active | 24s | Summon 2 wolves (scaled HP 20, damage +50%) 20s. If animal present nearby add +1 wolf. |
| M | Pounce | Movement | 14s | Leap onto enemy (range 5). Attach 2s dealing 1 HP every 0.4s (total 5 HP). Cancelled by stun/knockback. Not weapon-scaled. |
| U | Primal Stampede | Ultimate | 140s | Summon herd (line 10 blocks) charges forward 2s: Each hit enemy 6 HP + heavy knock & +30% armor durability damage. |
| Wpn | Beastclaw | Weapon | – | On hit 15% chance summon mini spirit claw (1 HP) & if bonded (animal near) +1 extra HP. Alt roar (25s): Fear nearby enemies 1.5s. |
| FS | Alpha’s Last Howl | Final Stand | Per life | On death spawn Alpha Beast (HP 60) targeting killer; despawns on killer death or after 25s. |

---
# 13. Soul of Silence
Nullification, anti-magic, execution.

## Base Kit
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Quiet Aura | Passive | – | Enemies within 5 deal -8% damage (multiplicative). |
| T | Null Strike | Active | 12s | Next melee within 5s: if hits enemy casting/ channeling cancels and silences 2s; otherwise deals +3 HP bonus. |
| M | Silent Step | Movement | 13s | Teleport 6 blocks (no sound) & invis 1s. |
| U | Silence Field | Ultimate | 120s | 8s field radius 5: Enemies cannot activate abilities (Silence). Existing channels aborted. |

## Subclass: Path of the Reaper
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Harvest Spirit | Passive | – | Killing blow grants 10 Soul Meter (0–100) & heal 2 hearts. |
| T | Reaping Slash | Active | 14s | 5 block cleave 5 HP & steal 1 heart (heal you) + 10 Soul Meter if killing blow or target below 25% HP. |
| M | Deathstride | Movement | 15s | Blink to lowest HP enemy within 8 (≤50% HP). Costs 10 Soul Meter; refunds if no target. Applies Minor Slow 1s. |
| U | Soul Harvest | Ultimate | 150s | Consume all Soul Meter firing beam 8 blocks 0.5s tick. Base 4 HP/tick + +0.5 HP per 10 Soul Meter spent. Instantly executes targets <15% HP. Max duration 4s. |
| Wpn | Deathscythe | Weapon | – | Executes enemies below 10% HP (instantly kill, counts as kill trigger). Alt reap (25s): wide arc 6 HP ignoring shields. |
| FS | Grim Finale | Final Stand | Per life | On death execute all enemies within 6 below 25% HP (true). Else deal 6 HP true to each. |

## Subclass: Path of Emptiness
| Code | Name | Type | CD | Mechanics |
|------|------|------|----|-----------|
| P | Void Presence | Passive | – | Enemies within 6 deal -15% damage (overrides Quiet Aura for stronger). |
| T | Null Pulse | Active | 16s | 5 block wave strips all positive buffs (duration >4s truncated to 0). Grants you 2s Silence Immunity. |
| M | Void Dash | Movement | 14s | Blink 5 blocks leaving 3s silence patch radius 2 (1s silence on enter). |
| U | Oblivion | Ultimate | 140s | 6 block radius wave: Removes active abilities (cancels) & silences 6s; prevents reapplication of buffs 4s. |
| Wpn | Voidbrand | Weapon | – | On hit apply 1s silence (ICD 5s per target). Alt null (30s): 3 block AOE silence 2s. |
| FS | Collapse into Nothing | Final Stand | Per life | Global: Remove all active buffs & debuffs from all players (including allies) & silence enemies 2s. You vanish (no corpse). |

---
## 14. Balancing & Future Work Notes
- All numbers first-pass; expect iterative telemetry-based adjustments.
- Identify outliers: High stacking (Bloodfang scaling, Chronoblade CDR snowball). Add potential soft caps if needed.
- Need central registry for: Status definitions, Resistances, Execution logic, Resource (Soul Meter) accumulation hooks.
- Logging: Per ability cast log with timestamp, source, targets, damage/healing, statuses applied.

## 15. Implementation Priority Suggestions (MVP)
1. Core frameworks (Cooldown Manager, Status Engine, Resource Pools, Area Domains).  
2. Movement & Blink safety pathing.  
3. Silence, Rot, Execution thresholds.  
4. Two sample Souls (Wrath Blood / Serenity Renewal) for vertical slice.  
5. Data-driven JSON ability definitions for easier tuning.

---
