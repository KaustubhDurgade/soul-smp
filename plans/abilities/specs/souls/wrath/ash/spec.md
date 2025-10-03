---
id: soul.wrath.ash
resource: Cinder Meter
ability_names:
  passive: Cinder Veins
  tactical: Flame Surge
  movement: Ash Glide
  ultimate: Infernal Storm
  weapon: Cinderblade
  final_stand: Worldscar Caldera
unlock_requirements:
  passive:
    type: auto
    id: wrath.ash.passive.attunement
    description: Automatically granted when embracing the Path of Ash.
  tactical:
    type: player_kill
    id: wrath.ash.tactical.first_blood
    description: Defeat a player while they suffer burn damage you applied within the last 5 seconds.
    criteria:
      requires_burning_target: true
  movement:
    type: challenge
    id: wrath.ash.movement.shadow_dive
    description: Execute three Ash Glide backstab criticals within 45 seconds without taking damage or touching water.
    criteria:
      consecutive_backstabs: 3
      time_window_seconds: 45
      fail_conditions:
        - damage_taken
        - water_contact
  ultimate:
    type: crafting
    id: wrath.ash.ultimate.infernal_matrix
    description: Forge the Infernal Matrix at the Volcanic Crucible to unlock Infernal Storm.
    crafting:
      station: volcanic_crucible
      recipe:
        - meteor_shard:3
        - blaze_core:4
        - magma_heart:2
        - wither_ember:1
  weapon:
    type: event_item
    id: wrath.ash.weapon.cinderblade_scroll
    description: Claim the Cinderblade schematic from the Ashen Siege world event chest.
    source:
      event: ashen_siege
      drop: mastery_scroll
  final_stand:
    type: event_item
    id: wrath.ash.final_stand.worldscar_rite
    description: Complete the Worldscar Caldera ritual encounter inside the Ashen Decimator raid to bind the final stand.
    source:
      raid: ashen_decimator
      requirement: finish_ritual_channel
difficulty: tbd
---

# Soul of Wrath – Path of Ash (Resource Extension: Cinder Meter)

Philosophy: Volcanic destruction and stealth assassination through fire mastery and ash manipulation.

Resource – Cinder Meter
- Cap: 100
- Gain: Fire DoT ticks (+1 per tick, 0.5s ICD per target), Flame Surge unique enemies (+1 per enemy, max 5), Ash Glide backstabs (+2), Cinderblade enemy hits (+1 per enemy).
- Spend: Infernal Storm enhancement (20 per +2 meteors, consumes Cinder).
- Decay: 1 per 10s when not dealing fire damage or applying DoTs.
- Cinder Mastery: Higher Cinder Meter enables devastating meteor enhancements.

Ability Kit

## Unlock Progression

- **Passive – Cinder Veins:** Granted immediately when the Path of Ash is selected.
- **Tactical – Flame Surge:** Unlock by finishing a player who is actively burning from your fire effects.
- **Movement – Ash Glide:** Perform three consecutive backstab critical hits with Ash Glide within 45 seconds, taking no damage and avoiding water contact.
- **Ultimate – Infernal Storm:** Craft the Infernal Matrix (3 Meteor Shards, 4 Blaze Cores, 2 Magma Hearts, 1 Wither Ember) at the Volcanic Crucible.
- **Weapon – Cinderblade:** Secure the mastery scroll reward from the Ashen Siege world event.
- **Final Stand – Worldscar Caldera:** Complete the Worldscar ritual inside the Ashen Decimator raid to bind this final stand.

P – Cinder Veins
- Fire Resistance Bypass: Fire and lava damage ignores enemy fire resistance completely.
- True Damage Conversion: +10% of fire damage converted to true damage (maximum 4 HP true per hit).
- Cinder Generation: +1 Cinder per fire DoT tick (0.5s internal cooldown per target).
- Volcanic Blood: Represents internalized volcanic power enhancing all fire effects.
- Resistance Penetration: Perfect counter to fire-resistant enemies.
- True Fire: Most potent fire damage enhancement representing pure volcanic fury.

T – Flame Surge (18s cooldown)
- Pillar Formation: 3 fire pillars in straight line with 3 block spacing.
- Pillar Damage: Each pillar deals 5 HP + Fire II for 2s.
- Knockup Effect: Each pillar provides 0.5 block vertical lift to hit enemies.
- Cinder Generation: +1 per unique enemy hit (maximum 5 Cinder).
- Line Formation: Tactical positioning tool for controlling enemy movement.
- Surge Visualization: Dramatic fire pillars erupting from ground in sequence.

M – Ash Glide (20s cooldown)
- Glide Duration: 1.8s enhanced movement with slowfall effect.
- Stealth Window: True Invisibility for 1.2s during mid-glide.
- Critical Enhancement: First attack after glide is guaranteed critical hit (1.5x damage).
- Backstab Bonus: If critical attack is from behind, add +6 HP true damage.
- Cinder Generation: +2 Cinder on successful backstab execution.
- Ash Mastery: Perfect blend of mobility, stealth, and assassination potential.

U – Infernal Storm (120s cooldown)
- Meteor Count: 12 micro-meteors falling over 4s duration.
- Meteor Damage: 4 HP + Blind I for 1s on direct hit.
- Ground Ignition: Each impact creates ignited patch lasting 6s (1 HP/s).
- Single Target Cap: Maximum 28 HP damage to any individual enemy.
- Cinder Enhancement: Spend 20 Cinder to add +2 meteors (maximum +6 meteors, consumes Cinder).
- Ultimate Bombardment: Most devastating area ability representing volcanic apocalypse.

Wpn – Cinderblade (24s charge cooldown)
- Charge System: Stores 2 heavy swing charges.
- Charge Mechanics: 0.6s charge time per heavy swing.
- Explosion Effect: 2.5 block radius explosion dealing 6 HP + 1.5 block knockback.
- Armor Damage: +25% armor durability damage per explosion.
- Cinder Generation: +1 per enemy hit by explosion.
- Heavy Weapon: Requires tactical timing for maximum effectiveness.

FS – Worldscar Caldera (Per life)
- Caldera Creation: 12 block radius magma/basalt terrain transformation.
- Vent System: 6 volcanic vents dealing 3 HP every 2s for 10s.
- Ash Blindness: Continuous 1s blind pulses throughout caldera.
- Channel Requirement: 8s channel to complete volcanic ritual.
- Revival Condition: ≥3 enemies inside caldera during channel grants revival at 30% HP.
- Terrain Persistence: If revival fails, volcanic terrain persists for 30 minutes.
- Ultimate Transformation: Most powerful environmental change representing world-scarring volcanic eruption.

Play Pattern Notes
- Stack Cinder through DoTs and pillars, then cash out for storm amplification.
- Glide enables setup positioning and backstab assassination windows.
- Resource management crucial for maximizing meteor bombardment.
