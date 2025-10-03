---
id: soul.wrath.base
resource: Heat
ability_names:
  passive: Burning Spirit
  tactical: Ignition
  movement: Inferno Chain Surge
  ultimate: Meteorfall
unlock_requirements:
  passive:
    type: auto
    id: wrath.base.passive.attunement
    description: Automatically granted upon attuning to the Soul of Wrath.
  tactical:
    type: player_kill
    id: wrath.base.tactical.first_blood
    description: Defeat any opposing player while attuned to Wrath.
  movement:
    type: challenge
    id: wrath.base.movement.superheated_chain
    description: Maintain Heat ≥ 80 while burning without golden apples or fire resistance for 90 seconds, then strike an enemy to seal the chain.
    criteria:
      min_heat: 80
      burn_duration_seconds: 90
      forbidden_consumables:
        - golden_apple
        - enchanted_golden_apple
        - potion_fire_resistance
  ultimate:
    type: crafting
    id: wrath.base.ultimate.meteor_core
    description: Forge the Meteoric Catalyst at the Volcanic Crucible to channel Meteorfall.
    crafting:
      station: volcanic_crucible
      recipe:
        - magma_heart:4
        - blaze_core:2
        - netherite_ingot:1
difficulty: tbd
---

# Soul of Wrath – Base (Paths: Blood & Ash)

Philosophy: Rage and fire mastery through aggressive combat and burning effects.

Resource – Heat
- Cap: 100
- Gain: Ignition unique enemy hits (+2 per enemy), Inferno Chain first hits (+5), Meteorfall activation (+varies), aggressive combat (+1 per enemy damaged), sustained combat (+1 per 5s in combat).
- Spend: Ignition heat purging (10 per visual purge), Meteorfall activation (40% current heat cleared).
- Decay: 1 per 10s when not in combat or dealing fire damage.
- Rage Mastery: Higher Heat enhances all fire effects and combat aggression.

Ability Kit

## Unlock Progression

- **Passive – Burning Spirit:** Unlocks automatically when a player attunes to the Soul of Wrath.
- **Tactical – Ignition:** Earned by claiming a player kill while wielding Wrath.
- **Movement – Inferno Chain Surge:** Survive 90 seconds of continuous burn without golden apples or fire resistance while keeping Heat above 80, then land a melee hit to bind the chains.
- **Ultimate – Meteorfall:** Craft the Meteoric Catalyst at the Volcanic Crucible using 4 Magma Hearts, 2 Blaze Cores, and 1 Netherite Ingot.

P – Burning Spirit
- Health Threshold: Damage bonus activates when below 60% HP.
- Damage Bonus: +15% outgoing damage while threshold met.
- Snapshot Timing: Bonus calculated at ability wind-up, not impact.
- Heat Generation: +1 Heat per enemy damaged while bonus active.
- Rage Visualization: Character gains flame aura when bonus is active.
- Berserker Philosophy: More dangerous when wounded and desperate.
- Burning Resolve: Represents inner fire growing stronger through adversity.

T – Ignition (16s cooldown)
- Weapon Enhancement: 6s flame coating on currently wielded weapon.
- Fire Application: Each hit applies Fire I damage over time.
- Fire Duration: Each hit extends fire duration by +2s (cumulative).
- Durability Damage: +10% armor durability damage per hit.
- Heat Generation: +2 Heat per unique enemy hit (0.5s internal cooldown).
- Heat Purge: After ≥5 hits, purge 10 Heat for visual flame explosion.
- Ignition Mastery: Transforms ordinary weapons into instruments of burning fury.

M – Inferno Chain Surge (15s cooldown)
- Chain Range: Up to 5 block chain attachment to surfaces or enemies.
- Slingshot Mechanics: Launches caster 3 blocks past attachment point.
- Burning Tether: Creates 3s tether dealing 1 HP/s + 20% slow to chained enemy.
- Recast Window: Can recast within 2s for snap-back detonation.
- Detonation Damage: 6 HP fire damage (single-target cap 6 HP).
- Heat Generation: +5 Heat when first enemy hit during surge.
- Chain Mastery: Complex mobility tool combining movement, damage, and control.

U – Meteorfall (105s cooldown)
- Meteor Count: 5 meteors falling over 3s duration.
- Individual Damage: 6 HP damage in 2 block radius per meteor.
- Ground Ignition: Each impact ignites ground for 3s additional fire damage.
- Center Hit Limit: Maximum 2 meteors can hit center point.
- Single Target Cap: 18 HP maximum damage to any individual enemy.
- Heat Consumption: Clears 40% of current Heat (rounded down to nearest 5).
- Meteor Visualization: Spectacular falling fire bombardment with impact craters.

Design Notes
- Base kit lacks soulbound weapon and Final Stand to encourage subclass specialization.
- Heat management crucial for sustained combat effectiveness.
- All abilities synergize around fire damage and aggressive engagement.