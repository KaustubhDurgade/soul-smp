---
id: soul.wrath.blood
resource: Bloodlust
ability_names:
  passive: Thirst for War
  tactical: Blood Rend
  movement: Frenzy Leap
  ultimate: Crimson Overdrive
  weapon: Bloodfang
  final_stand: Crimson Deluge
unlock_requirements:
  passive:
    type: auto
    id: wrath.blood.passive.attunement
    description: Automatically unlocked upon choosing the Path of Blood.
  tactical:
    type: player_kill
    id: wrath.blood.tactical.first_harvest
    description: Finish a player while they are affected by your Bleed debuff from Blood Rend.
    criteria:
      requires_bleed_kill: true
  movement:
    type: challenge
    id: wrath.blood.movement.relentless_pounce
    description: Chain three Frenzy Leap impacts on <50% HP enemies within 60 seconds without touching the ground between leaps.
    criteria:
      chain_hits: 3
      time_window_seconds: 60
      airborne_requirement: true
  ultimate:
    type: crafting
    id: wrath.blood.ultimate.sanguine_core
    description: Craft the Sanguine Core at a Blood Crucible to stabilize Crimson Overdrive.
    crafting:
      station: blood_crucible
      recipe:
        - nether_star:1
        - vampire_fang:4
        - ancient_debris:2
  weapon:
    type: event_item
    id: wrath.blood.weapon.bloodfang_scroll
    description: Acquire the Bloodfang mastery scroll from the Crimson Gauntlet seasonal event.
    source:
      event: crimson_gauntlet
      drop: mastery_scroll
  final_stand:
    type: event_item
    id: wrath.blood.final_stand.crimson_deluge_rite
    description: Complete the Blood Moon siege encounter and consume the Crimson Rite to unlock Crimson Deluge.
    source:
      event: blood_moon_siege
      requirement: crimson_rite_offering
difficulty: tbd
---

# Soul of Wrath – Path of Blood (Resource Extension: Bloodlust)

Philosophy: Vampiric escalation and blood magic through life steal amplification and berserker states.

Resource – Bloodlust
- Cap: 100
- Gain: Kills (+1), overheal overflow (+1 per full heart), Blood Rend unique targets (+2 per target, max 6), Frenzy Leap on low-health targets (+3), Bloodfang kills (+1 additional).
- Spend: Crimson Overdrive exhaustion negation (50 consumed to prevent Exhaust debuff).
- Decay: 1 per 12s when not dealing damage or healing.
- Blood Mastery: Higher Bloodlust enables stronger berserker states and vampiric effects.

Ability Kit

## Unlock Progression

- **Passive – Thirst for War:** Granted immediately on adopting the Path of Blood.
- **Tactical – Blood Rend:** Slay a bleeding opponent whose Bleed was applied by you.
- **Movement – Frenzy Leap:** Chain three airborne Frenzy Leap hits on sub‑50% enemies within 60 seconds without touching the ground between leaps.
- **Ultimate – Crimson Overdrive:** Craft the Sanguine Core (Nether Star ×1, Vampire Fang ×4, Ancient Debris ×2) at the Blood Crucible.
- **Weapon – Bloodfang:** Earn the mastery scroll from the Crimson Gauntlet world event.
- **Final Stand – Crimson Deluge:** Survive the Blood Moon siege and complete the Crimson Rite to bind the final stand.

P – Thirst for War
- Base Life Steal: 5% life steal with 2 HP maximum per hit.
- Scaling Life Steal: +1% life steal per 2 missing hearts (maximum +10% at critical health).
- Bloodlust Generation: +1 per kill and +1 per full heart of overheal (overflow healing).
- Life Steal Cap: Individual hits capped at healing for damage dealt or 2 HP, whichever is lower.
- War Thirst: Life steal effectiveness increases as health decreases, encouraging aggressive play.
- Vampiric Mastery: Perfect scaling system rewarding both kills and sustained combat.

T – Blood Rend (14s cooldown)
- Dual Slash: Two consecutive slashes with 0.25s between attacks.
- Damage Per Slash: 4 HP true damage per slash (8 HP total if both connect).
- Saturation Drain: Each slash drains 2 saturation from target.
- Life Steal: Each slash heals caster for 1.5 hearts (true healing).
- Range: 12 block reach for both slashes.
- Bleed Application: If both slashes hit same target, apply Bleed (3 HP over 3s).
- Bloodlust Generation: +2 per unique target hit (maximum 6 if hitting 3 different enemies).

M – Frenzy Leap (15s cooldown)
- Leap Distance: 7 block leap to target location.
- Impact Damage: First enemy within 2.5 block radius takes 5 HP damage.
- Life Steal: Heal for 50% of damage dealt to impact target.
- Bloodlust Bonus: +3 Bloodlust if target is below 50% HP when hit.
- Frenzy Visualization: Predatory leap with blood trail effects.
- Hunt Mechanics: Bonus generation encourages targeting weakened enemies.

U – Crimson Overdrive (95s cooldown)
- Duration: 10s berserker state with massive stat bonuses.
- Health Cost: Lose 4 hearts (visual only - non-lethal).
- Stat Bonuses: Strength III, Resistance I, Speed IV during effect.
- Exhaust Penalty: After effect ends, suffer -15% damage for 5s unless negated.
- Bloodlust Negation: Spend 50 Bloodlust to prevent Exhaust penalty (consumes Bloodlust).
- Ultimate Berserker: Peak blood magic representing total battle fury.

Wpn – Bloodfang
- Kill Scaling: Each kill grants +1 Sharpness (maximum +5 stacks).
- Death Penalty: Lose all Sharpness stacks on death.
- Alt Attack – Bite (20s cooldown): 3 HP true damage + heal 3 HP.
- Bloodlust Generation: +1 Bloodlust per kill (additional to normal kill generation).
- Predatory Weapon: Grows in power through sustained killing.
- Vampiric Bite: Alternative attack providing reliable healing and damage.

FS – Crimson Deluge (Per life)
- Blood Field: Creates 10 block radius field with 25% movement slow lasting 20s.
- Geyser System: 5 blood geysers pulse 3 HP damage every 1.5s.
- Vitality Collection: Channel for 6s collecting 1 Vitality per enemy per 2s.
- Revival Condition: ≥8 Vitality grants revival at 40% HP + Bleed application (6 HP over 6s).
- Field Persistence: If revival fails, field persists for 20 minutes.
- Blood Legacy: Creates lasting battlefield hazard affecting future engagements.

Play Pattern Notes
- Accelerate kills to ramp Sharpness and Bloodlust for compound scaling.
- Overdrive timing crucial - balance power vs Exhaust trade-off.
- Deluge zone retention creates ongoing territorial pressure.
