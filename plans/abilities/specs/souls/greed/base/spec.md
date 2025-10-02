---
id: soul.greed.base
resource: Wealth Value
ability_names:
  passive: Golden Hunger
  tactical: Drain Touch
  movement: Market Shift
  ultimate: Pillage
difficulty: tbd
---

# Soul of Greed – Base (Paths: Gluttony & Envy)

Philosophy: Accumulation and resource manipulation through wealth generation and buff theft.

Resource – Wealth Value
- Cap: 100
- Gain: Golden Hunger loot bonuses (+2 per bonus drop), Market Shift coin collection (+1 per coin), successful Pillage operations (+5 per buff stolen), ore proximity bonuses (+1 per ore type nearby).
- Spend: Enhanced Market Shift effects (10 per enhancement), Pillage duration extensions (20 per +2s).
- Decay: 1 per 15s when not actively accumulating resources or near valuable blocks.
- Wealth Momentum: Higher Wealth Value increases resource generation rates for all abilities.

Ability Kit

P – Golden Hunger
- Loot Enhancement: +15% chance for mobs to drop extra loot (separate roll from normal drops).
- Experience Bonus: +10% XP gain from all sources (mob kills, mining, crafting).
- Wealth Generation: +2 Wealth Value per bonus loot drop triggered.
- Treasure Sense: Passive detection of valuable blocks within 8 blocks (diamonds, emeralds, ancient debris).
- Stacking Mechanics: Multiple bonus rolls can trigger on single mob kill.
- Economy Integration: Enhanced interaction with server economy systems where applicable.

T – Drain Touch (18s cooldown)
- Brand Duration: 5s debuff applied on successful melee hit.
- XP Drain: Target loses 10% current XP which transfers to caster.
- Saturation Theft: Target loses 2 saturation points which heal caster for 1 heart.
- Item Siphon: 10% chance per hit to steal random item from target's inventory.
- Brand Refresh: Multiple hits refresh duration but don't stack effects.
- Wealth Generation: +3 Wealth Value per successful drain activation.
- Drain Immunity: 15s immunity period for targets after brand expires.

M – Market Shift (17s cooldown)
- Marker Placement: Creates visible waypoint lasting 6s at current location.
- Teleport Swap: Instantly swap positions with marker location.
- Coin Spawn: 3 homing coins spawn at swap origin, each worth +1 Wealth Value.
- Ore Proximity Bonus: 70% cooldown refund if swap destination is within 3 blocks of ≥2 different ore types.
- Homing Mechanics: Coins automatically seek and orbit caster for 8s before absorption.
- Tactical Usage: Escape tool with resource generation and positional mind games.
- Market Value: Higher Wealth Value increases coin spawn count (+1 per 25 Wealth, max 6 total).

U – Pillage (110s cooldown)
- Buff Theft: Steal up to 5 active buffs from target within 8 blocks.
- Stolen Duration: Stolen buffs last 8s on caster regardless of original remaining time.
- Original Pause: Target's original buffs are paused (timer frozen) during theft.
- Return Penalty: When theft expires, original buffs return with -4s duration penalty.
- Wealth Scaling: Spend 20 Wealth Value to extend stolen buff duration by +2s.
- Multi-Target: Can target multiple enemies if they have fewer total buffs than cap.
- Priority System: Prioritizes longer-duration and more powerful buffs for theft.of Greed – Base (Resource: Wealth Value)
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Golden Hunger | – | Resource | +15% mob extra loot roll; +10% player XP. |
| T | Drain Touch | 18s | Resource, Damage | Brand 5s: steals XP, saturation, 10% item siphon chance. |
| M | Market Shift | 17s | Mobility, Resource | Place marker (6s), swap, spawn 3 homing coins (+1 Wealth ea); refund 5s if near ≥2 ores. |
| U | Pillage | 110s | Buff Steal | Steal up to 5 buffs 8s; originals paused then return -4s duration. |