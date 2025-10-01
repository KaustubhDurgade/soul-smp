# Soul of Curiosity – Base (Resource: Inspiration / Paths: Discovery & Madness)

Philosophy: Knowledge acquisition and experimental discovery through investigation and analysis.

Resource – Inspiration
- Cap: 100
- Gain: New entity/block scans (+3), successful Analyze applications (+2), Insight Pivot with active Analyze (+5), discovering hidden mechanics (+8), first-time biome exploration (+5).
- Spend: Arcane Experiment enhancement (40 = guarantee 1 rare beneficial effect).
- Decay: 1 per 15s when not actively investigating (no scanning, analyzing, or ability usage).
- Philosophy: Rewards active exploration and investigation.

Ability Kit

P – Keen Eye
- Detection Range: 8 blocks from player.
- Invisible Entity Highlighting: Shows outline/nameplate for:
  - Invisible players (Invis potion, stealth abilities)
  - Hidden mobs (spiders in webs, etc.)
  - Camouflaged entities
- Trap Block Detection: Highlights dangerous blocks:
  - TNT (armed and unarmed)
  - Tripwire hooks and strings
  - Pressure plate traps
  - Hidden redstone mechanisms
- Visual Style: Subtle glow effect with investigative particle overlay.
- Inspiration Generation: +3 for each new type of entity/block detected for first time.
- Detection Immunity: Cannot be bypassed by higher-tier invisibility effects.

T – Analyze (16s cooldown)
- Target Range: 15 blocks, line of sight required.
- Information Revealed:
  - Current HP (exact values + percentage)
  - All ability cooldowns with remaining times
  - Active buffs/debuffs with durations
  - Equipped weapon/armor details
- Mark Application: Target receives Analysis Mark visible to all allies.
- Mark Effect: You deal +8% damage to marked target for 6s.
- Inspiration Generation: +2 per successful analysis, +3 if target has unusual status effects.
- Information Display: Temporary UI overlay shows gathered data for 8s.
- Mark Limitation: Only one mark active at a time; new analysis overwrites previous.

M – Insight Pivot (14s cooldown)
- Anchor Deployment: Place Arcane Anchor at target location within range 6.
- Anchor Duration: 3s window for recast activation.
- Recast Effects:
  - Blink to anchor location
  - +12% CDR for 5s
  - Enemy outline reveal (range 8) for 2s
- Analyze Synergy: If Analyze mark active when pivoting:
  - Refund 30% cooldown
  - +5 Inspiration generation
  - Extend mark duration by +2s
- Anchor Interaction: Enemies can destroy anchor (8 HP) to prevent pivot.
- Tactical Use: Information gathering tool combined with repositioning.

U – Arcane Experiment (120s cooldown)
- Area: 6 block radius centered on caster.
- Effect Generation: Creates 6 random potion effects + 1 negative enemy effect.
- Positive Effects (allies, 8s duration): Speed, Strength, Resistance, Regeneration, Night Vision, Water Breathing, Fire Resistance, etc.
- Negative Effects (enemies, 5s duration): Slowness, Weakness, Poison, Blindness, Mining Fatigue, etc.
- Inspiration Enhancement: Spend 40 Inspiration to guarantee 1 rare beneficial effect (Absorption, Health Boost, Luck, etc.).
- Effect Randomization: Uses weighted tables to prevent overpowered combinations.
- Friendly Fire: Positive effects never harm allies; negative effects never help enemies.

Implementation Notes
- Keen Eye detection updates in real-time without performance impact.
- Analyze data cached for 30s to prevent spam-scanning same targets.
- Inspiration gain tracking prevents exploitation of repetitive actions.

Balance Considerations
- Detection range balanced against stealth gameplay.
- Analyze cooldown prevents constant information advantage.
- Inspiration decay encourages active play over passive accumulation.
