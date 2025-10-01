# Resonance Master Chart (All Pairwise Soul Synergies)

Base Souls (13): wrath, serenity, greed, pride, despair, hope, chaos, order, glory, curiosity, trickery, nature, silence.

Legend
- Tier: C=Common (proximity/passive), T=Tactical (timed/sequence), F=Fusion (strong multi-effect), U=Ultimate Fusion (requires at least one Ultimate), X=Mythic (rare showcase / gated)

- Cat: OFF (offense), DEF (defense), UTIL (utility), CTRL (control), HYB (hybrid)

- Trigger Types: PROX (proximity), SEQ (ability sequence window), AREA (area overlap), STATUS (status cascade), ULT (ultimate overlap), REACT (reactive conditional)

- InternalCD: Default if not specified = passive/PROX tick; '-' means passive continuous.
 
- Status: stable (fully specced), draft (needs tuning), pending (spec not yet written), mythic (triplet)

| Souls | ID | Name | Tier | Cat | Trigger | InternalCD | Status | Core Effect Summary |
|-------|----|------|------|-----|---------|------------|--------|---------------------|
| chaos + curiosity | chaos+curiosity.experimental_flux | Experimental Flux | T | HYB | SEQ | 18s | stable | Mutates random outcome bias while both casting near each other |
| chaos + despair | chaos+despair.haunting_echo | Haunting Echo | T | CTRL | STATUS | 20s | stable | Fear spreads echo pulses applying minor wither |
| chaos + greed | chaos+greed.volatile_ledger | Volatile Ledger | F | OFF | AREA | 30s | draft | Rift inside coin vacuum creates explosive coin novas |
| chaos + glory | chaos+glory.legendary_paradox | Legendary Paradox | U | HYB | ULT | 180s | draft | Banner inside Riftstorm duplicates random aura effect |
| chaos + hope | chaos+hope.prismatic_refraction | Prismatic Refraction | F | UTIL | AREA | 25s | draft | Beacon in Rift splits heals into beams w/ variance |
| chaos + nature | chaos+nature.mutagenic_overgrowth | Mutagenic Overgrowth | F | OFF | STATUS | 15s | draft | Nature storm hit causes mutating thorns eruption |
| chaos + order | chaos+order.entropic_chainbreak | Entropic Chainbreak | T | CTRL | SEQ | 24s | draft | Chain/root break spawns random displacement burst |
| chaos + pride | chaos+pride.warped_edict | Warped Edict | F | CTRL | AREA | 16s | draft | Warbanner/Decree region gains random buff flips each tick |
| chaos + serenity | chaos+serenity.stilled_chaos | Stilled Chaos | T | HYB | AREA | 12s | draft | Sanctuary dampens negative chaos rolls, amplifies positive |
| chaos + silence | chaos+silence.void_quiet | Void Quiet | C | CTRL | PROX | 6s | draft | Reduced random procs; chaos pulses strip minor buffs |
| chaos + trickery | chaos+trickery.mirror_madness | Mirror Madness | F | OFF | AREA | 22s | stable | Illusion clones inherit random buffs then explode |
| chaos + wrath | chaos+wrath.fire_tornado | Fire Tornado | T | OFF | SEQ | 20s | stable | Spiral pull + fire DoT vortex |
| curiosity + despair | curiosity+despair.forbidden_analysis | Forbidden Analysis | T | UTIL | SEQ | 25s | draft | Analyze on withered target reveals extended stats & reap vuln |
| curiosity + greed | curiosity+greed.alchemical_mint | Alchemical Mint | T | UTIL | AREA | 30s | stable | Transmute near Market Shift spawns transmuted coin nodes |
| curiosity + glory | curiosity+glory.legend_record | Legend Record | C | UTIL | PROX | 5s tick | draft | Logging combat events increases shared XP trickle |
| curiosity + hope | curiosity+hope.luminous_theory | Luminous Theory | T | UTIL | SEQ | 22s | draft | Beam/Beacon data stores recent heals to replay minorly |
| curiosity + nature | curiosity+nature.symbiotic_research | Symbiotic Research | C | UTIL | PROX | 10s tick | draft | Observing growth speeds plant ticks & grants insight stacks |
| curiosity + order | curiosity+order.temporal_index | Temporal Index | F | UTIL | AREA | 45s | draft | Rewind near Analyze snapshots buff states |
| curiosity + pride | curiosity+pride.glory_thesis | Glory Thesis | C | UTIL | PROX | 30s tick | draft | Captures ally buff stats; small renown scaling |
| curiosity + serenity | curiosity+serenity.harmonic_observation | Harmonic Observation | C | UTIL | PROX | 8s tick | draft | Calm zone gives CDR + data stacks |
| curiosity + silence | curiosity+silence.null_probe | Null Probe | T | UTIL | STATUS | 28s | draft | Analyze in silence field suppresses enemy regen longer |
| curiosity + trickery | curiosity+trickery.deceptive_research | Deceptive Research | F | HYB | SEQ | 30s | draft | Illusion clones feed pseudo-data giving fake reads |
| curiosity + wrath | curiosity+wrath.combustion_study | Combustion Study | C | OFF | STATUS | 12s | pending | Studying burning enemies increases burn accuracy (consistency) |
| despair + greed | despair+greed.rotting_avarice | Rotting Avarice | F | OFF | STATUS | 30s | pending | Rotting enemies drop corruption coins on death |
| despair + glory | despair+glory.dirge_of_legends | Dirge of Legends | F | HYB | AREA | 40s | pending | Darkness in banner zone drains enemy morale (dmg debuff) |
| despair + hope | despair+hope.twilight_reassurance | Twilight Reassurance | C | HYB | PROX | 6s tick | pending | Light near decay halves fear escalation rate |
| despair + nature | despair+nature.fungal_ascent | Fungal Ascent | F | OFF | STATUS | 24s | pending | Rot triggers spore growth vines |
| despair + order | despair+order.mandated_decay | Mandated Decay | T | CTRL | SEQ | 26s | pending | Chain/root on withered target increases rot tier |
| despair + pride | despair+pride.dread_command | Dread Command | F | CTRL | AREA | 32s | pending | Warbanner zone inflicts fear pulses |
| despair + serenity | despair+serenity.muted_suffering | Muted Suffering | C | DEF | PROX | 8s tick | pending | Sanctuary lowers rot/bleed tick damage |
| despair + silence | despair+silence.null_decay | Null Decay | T | CTRL | STATUS | 20s | pending | Rot tick extends silence duration slightly |
| despair + trickery | despair+trickery.phantasmal_terror | Phantasmal Terror | F | CTRL | SEQ | 30s | pending | Illusion pop applies brief fear |
| despair + wrath | despair+wrath.ashen_rot | Ashen Rot | T | OFF | SEQ | 18s | pending | Fire hit within 1s of Rot apply upgrades rot tier |
| greed + glory | greed+glory.spoils_of_renown | Spoils of Renown | C | UTIL | PROX | 10s tick | pending | Shared kill loot buff & coin stacking |
| greed + hope | greed+hope.charitable_burn | Charitable Burn | F | HYB | STATUS | 28s | pending | Burn damage partially redistributes as ally heals |
| greed + nature | greed+nature.gilded_harvest | Gilded Harvest | C | UTIL | PROX | 12s tick | pending | Gathering near nature soul increases rare drops |
| greed + order | greed+order.contract_of_balance | Contract of Balance | T | UTIL | SEQ | 26s | pending | Item steal + chain = auto-return w/ interest buff |
| greed + pride | greed+pride.tithe_of_glory | Tithe of Glory | F | UTIL | AREA | 34s | pending | Allies in banner contribute coins -> party buff |
| greed + serenity | greed+serenity.charity_exchange | Charity Exchange | T | UTIL | STATUS | 24s | pending | Overheal converts to coin stacks |
| greed + silence | greed+silence.smuggled_void | Smuggled Void | F | UTIL | AREA | 36s | pending | Loot inside silence field phasing -> delayed materialization |
| greed + trickery | greed+trickery.fools_gold | Fool's Gold | F | CTRL | SEQ | 30s | pending | Pillage + clone = fake buff icons on enemies |
| greed + wrath | greed+wrath.blood_tithe | Blood Tithe | F | OFF | STATUS | 32s | pending | Life steal events convert to coin charges, fire increases value |
| glory + hope | glory+hope.shining_standard | Shining Standard | C | DEF | PROX | 8s tick | pending | Beacon + banner overlapping adds resist aura |
| glory + nature | glory+nature.hunt_of_legends | Hunt of Legends | F | OFF | SEQ | 30s | pending | Stampede/arena kills log for legacy buff |
| glory + order | glory+order.chronicle_decree | Chronicle Decree | T | UTIL | SEQ | 24s | pending | Decree inside banner snapshots ally stats |
| glory + pride | glory+pride.legendary_banner | Legendary Banner | U | HYB | ULT | 180s | pending | Warbanner + Immortal Banner fuse |
| glory + serenity | glory+serenity.resonant_banner | Resonant Banner | C | DEF | PROX | 8s tick | pending | Sanctuary near banner gives regen amp |
| glory + silence | glory+silence.quiet_triumph | Quiet Triumph | C | UTIL | PROX | 10s tick | pending | Silence field suppresses enemy victory effects |
| glory + trickery | glory+trickery.heroic_misdirection | Heroic Misdirection | F | CTRL | STATUS | 30s | pending | Clones flagged as 'hero' draw aggro |
| glory + wrath | glory+wrath.blazing_standard | Blazing Standard | F | OFF | AREA | 28s | pending | Meteor in banner zone empowers ticks |
| hope + nature | hope+nature.verdant_dawn | Verdant Dawn | F | HYB | AREA | 26s | pending | Beacon + storm adds growth pulses & heals |
| hope + order | hope+order.temporal_radiance | Temporal Radiance | T | UTIL | SEQ | 24s | pending | Rewind cast inside Beacon replays last heal ticks |
| hope + pride | hope+pride.inspiring_dominion | Inspiring Dominion | C | HYB | PROX | 10s tick | pending | Light aura increases renown generation |
| hope + serenity | hope+serenity.resplendent_well | Resplendent Well | F | DEF | AREA | 30s | pending | Sanctuary + Beacon supercharge regen & shielding |
| hope + silence | hope+silence.quiet_light | Quiet Light | C | UTIL | PROX | 8s tick | pending | Light dampens enemy cast speed in silence edge |
| hope + trickery | hope+trickery.radiant_veil | Radiant Veil | F | DEF | STATUS | 28s | pending | Heal ticks on disguised ally grant shield |
| hope + wrath | hope+wrath.purifying_flame | Purifying Flame | T | HYB | AREA | 22s | pending | Hope heal inside flame zone converts burn to ally pulses |
| nature + order | nature+order.regimented_growth | Regimented Growth | C | UTIL | PROX | 12s tick | pending | Structured aura speeds planned crop lines |
| nature + pride | nature+pride.emerald_standard | Emerald Standard | F | UTIL | AREA | 30s | pending | Banner + thorn field adds defensive regen |
| nature + serenity | nature+serenity.harmonic_grove | Harmonic Grove | C | DEF | PROX | 8s tick | pending | Sanctuary stabilizes storm wind-up |
| nature + silence | nature+silence.stilled_wilds | Stilled Wilds | C | CTRL | PROX | 10s tick | pending | Silence prevents hostile animal aggression |
| nature + trickery | nature+trickery.camouflaged_bramble | Camouflaged Bramble | F | CTRL | STATUS | 26s | pending | Illusion decoys plant slowing vines |
| nature + wrath | nature+wrath.scorched_wilds | Scorched Wilds | T | OFF | SEQ | 20s | pending | Thorn field + meteor impact ignites thorns |
| order + pride | order+pride.edict_of_command | Edict of Command | T | CTRL | STATUS | 24s | pending | Duel mark inside Edict focuses target |
| order + serenity | order+serenity.still_time | Still Time | F | CTRL | AREA | 30s | pending | Sanctuary + Edict/time ability slow time |
| order + silence | order+silence.absolute_judgment | Absolute Judgment | F | CTRL | AREA | 40s | pending | Edict + silence overlap = full ability lock |
| order + trickery | order+trickery.regulated_illusion | Regulated Illusion | C | CTRL | PROX | 8s tick | pending | Illusion durations standardized (less random) |
| order + wrath | order+wrath.tempered_furnace | Tempered Furnace | F | OFF | STATUS | 30s | pending | Chains on burning target increase controlled burn DoT |
| pride + serenity | pride+serenity.tempered_flux | Tempered Flux | T | DEF | AREA | 24s | pending | Sanctuary active + Ignition style buff moderates dmg |
| pride + silence | pride+silence.smothering_pretense | Smothering Pretense | F | CTRL | AREA | 30s | pending | Warbanner region in silence mutes enemy buffs |
| pride + trickery | pride+trickery.false_regalia | False Regalia | F | CTRL | STATUS | 28s | pending | Illusion can mimic banner briefly |
| pride + wrath | pride+wrath.ember_command | Ember Command | F | OFF | AREA | 30s | pending | Warbanner + meteor tick increases ally fire dmg |
| serenity + silence | serenity+silence.hollow_tranquility | Hollow Tranquility | C | DEF | PROX | 8s tick | pending | Silence field reduces hostile projectiles speed |
| serenity + trickery | serenity+trickery.veiled_sanctuary | Veiled Sanctuary | F | DEF | AREA | 30s | pending | Sanctuary cloaks allies entering edge |
| serenity + wrath | serenity+wrath.temper_flux | Temper Flux | T | DEF | AREA | 20s | pending | Sanctuary gains burn reflect |
| silence + trickery | silence+trickery.quiet_mirage | Quiet Mirage | C | CTRL | PROX | 8s tick | pending | Clones generate reduced noise; silence extends clone life |
| silence + wrath | silence+wrath.smother | Smother | T | CTRL | AREA | 18s | pending | Null field + fire tick overlap suppresses regen |
| trickery + wrath | trickery+wrath.fiendish_feint | Fiendish Feint | F | OFF | SEQ | 26s | pending | Clone burst triggers delayed flame strike |

Notes
- Sorted alphabetically by root soul pair.
- Stable exemplar specs prioritized (see `specs/`). Remaining entries flagged pending.
- Next steps: fill pending specs, then add mythic (triplet) fusions.
