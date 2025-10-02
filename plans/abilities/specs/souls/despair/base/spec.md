---
id: soul.despair.base
resource: Decay Index
ability_names:
  passive: Withering Touch
  tactical: Decay Bolt
  movement: Umbral Corridor
  ultimate: Plague Field
difficulty: tbd
---

# Soul of Despair – Base (Paths: Night & Rot)

Philosophy: Decay and corruption through withering effects and plague mechanics.

Resource – Decay Index
- Cap: 100
- Gain: Withering Touch applications (+3), Decay Bolt hits (+5), Umbral Corridor multi-hits (+3), Plague Field applications (+2 per affected enemy), prolonged debuff exposure (+1 per enemy per 5s).
- Spend: Enhanced plague durations (15 per +2s), Umbral Corridor shadow upgrades (10 per shadow).
- Decay: 1 per 10s when not actively applying debuffs or near decay sources.
- Corruption Mastery: Higher Decay Index amplifies all debuff durations and potency.

Ability Kit

P – Withering Touch
- Application Chance: 20% chance on melee hits to apply Wither I for 2s.
- Internal Cooldown: 6s per target to prevent spam applications.
- Decay Generation: +3 Decay Index per successful wither application.
- Stacking Rules: Multiple applications refresh duration but don't stack intensity.
- Corruption Spread: Withered enemies have 10% chance to spread effect to nearby allies.
- Touch Visualization: Dark corruption particles on successful applications.
- Passive Corruption: Represents the soul's inherent connection to decay.

T – Decay Bolt (13s cooldown)
- Projectile Type: Hitscan beam with 18 block maximum range.
- Wither Application: Applies Wither II for 8s on successful hit.
- Rot Debuff: Additionally applies Rot Level 1 for 4s.
- Rot Mechanics: Reduces healing effectiveness and spreads to nearby enemies.
- Decay Generation: +5 Decay Index per successful hit.
- Beam Visualization: Dark corruption beam with decay particle trail.
- Piercing Shot: Bolt can hit multiple enemies in a line.

M – Umbral Corridor (15s cooldown)
- Tunnel Length: 6 block forward tunnel with 0.8s travel time.
- Persistence: Tunnel remains open for 3s after creation.
- Blind Application: Enemies in tunnel receive Blind I for 1s.
- Shadow Damage: +1 HP shadow damage to all enemies contacted.
- Reversible Travel: Can travel back through tunnel during persistence window.
- Decay Bonus: +3 Decay Index if ≥2 enemies hit during tunnel creation.
- Corridor Visualization: Dark tunnel with shadow particle effects.

U – Plague Field (115s cooldown)
- Duration: 8s persistent area effect.
- Radius: 6 block area centered on cast location.
- Rot Application: Applies Rot Level 2 on entry, refreshes continuously.
- Wither Enhancement: Enemies in field suffer Wither I throughout duration.
- Healing Reduction: -20% healing effectiveness while in field.
- Field Persistence: Area remains at cast location (doesn't follow caster).
- Plague Visualization: Sickly green miasma with continuous decay effects.

Implementation Notes
- Withering Touch requires reliable percentage-based trigger system.
- Decay Bolt needs proper hitscan implementation with decay effects.
- Umbral Corridor requires complex tunnel creation and persistence mechanics.

Balance Considerations
- Passive debuff application balanced by low trigger chance and cooldowns.
- Plague Field provides area denial but no direct damage.
- Kit focused on sustained debuff pressure rather than burst effects.of Despair – Base (Resources: Decay Index / Path Terror & Blight)
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Withering Touch | – | Debuff | 20% melee apply Wither I 2s (ICD6s/target). |
| T | Decay Bolt | 13s | Debuff, Damage | Hitscan 18b Wither II 8s + Rot Lv1 4s. |
| M | Umbral Corridor | 15s | Mobility, Control | Tunnel length6 (0.8s) persists 3s Blind 1s +1 HP shadow; reversible; +3 Decay Index if ≥2 hit. |
| U | Plague Field | 115s | Area, Debuff | 8s r6 apply Rot Lv2 refresh + Wither I -20% healing. |