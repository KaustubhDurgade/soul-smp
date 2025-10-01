# Soul of Nature – Base (Resources: Growth Charge / Paths: Thorns & Beasts)

Philosophy: Environmental harmony and natural growth acceleration through plant manipulation.

Resource – Growth Charge
- Cap: 100
- Gain: Plant growth ticks within range (+1), successful vine interactions (+3), Nature's Wrath strikes (+2), natural block proximity bonuses (+1 per 10s when near plants).
- Spend: Nature's Wrath enhancement (70 = +1 extra strike per second).
- Decay: None while in natural environments; 2 per 10s in artificial/underground areas.
- Environmental Synergy: Generation doubled in forest/jungle biomes.

Ability Kit

P – Verdant Blessing
- Crop Acceleration: +25% tick rate for all crops within 8 blocks.
- Damage Scaling: +2% damage per natural plant block within 3 blocks (cap +12%).
- Plant Detection: Includes grass, flowers, saplings, crops, vines, leaves as "natural plants".
- Growth Charge Generation: +1 per plant growth tick caused by acceleration.
- Area Visualization: Subtle particle effects around accelerated crops.
- Biome Synergy: Effects doubled in forest, jungle, and plains biomes.
- Environmental Harmony: Bonus damage scales with biodiversity (different plant types within range).

T – Vine Snare (13s cooldown)
- Target: Single enemy within range 12.
- Root Duration: 1.8s complete movement lockout.
- Vine Visual: Spectral vines emerge from ground to entangle target.
- Line of Sight: Requires clear path to target; vines can emerge through natural terrain.
- Growth Charge Generation: +3 per successful snare, +1 additional if target was moving when snared.
- Enhanced Range: At ≥60 Growth Charge, range extends to 16 blocks.
- Natural Terrain Bonus: +0.5s duration if cast on grass/dirt surface.

M – Vine Whip (12s cooldown)
- Dual Mode: Can target surfaces (grapple) or enemies (pull + damage).
- Surface Mode: Standard grappling hook mechanics to any solid surface within 14 blocks.
- Enemy Mode: Pull self toward enemy, stopping 2 blocks away + deal 3 HP on arrival.
- Vine Trajectory: Visible vine projectile with 0.3s travel time.
- Growth Charge Generation: +3 for enemy hits, +1 for successful grapples.
- Environmental Interaction: Vines can swing through tree canopies for extended range.
- Combo Potential: Arrival triggers plant-based damage bonus from passive.

U – Nature's Wrath (125s cooldown)
- Storm Duration: 8s nature storm centered on cast location.
- Storm Radius: 6 blocks from center point.
- Strike Pattern: Every 1s, 2 random lightning-style strikes within storm area.
- Strike Damage: 4 HP + Slow 10% for 2s per strike.
- Growth Charge Enhancement: Spend 70 Growth Charge for +1 additional strike per second.
- Strike Targeting: Prioritizes enemies; random placement if no enemies present.
- Environmental Amplification: +50% damage in natural biomes, +25% strike frequency near trees.
- Storm Persistence: Storm follows caster if they move (max 20 blocks from origin).

Implementation Notes
- Crop acceleration uses server tick manipulation for realistic growth.
- Vine animations use entity-based projectiles for smooth visual feedback.
- Growth Charge tracking integrates with biome detection systems.

Balance Considerations
- Environmental bonuses encourage outdoor/natural area combat.
- Resource decay in artificial areas prevents underground exploitation.
- Plant damage scaling rewards positioning near natural terrain.of Nature – Base (Resources: Growth Charge / Paths: Thorns & Beasts)
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Verdant Blessing | – | Passive, Buff | Crop tick +25% r8; +2% damage per natural plant block within 3 (cap +12%). |
| T | Vine Snare | 13s | Control | Root enemy 1.8s (r12). |
| M | Vine Whip | 12s | Mobility, Control | Grapple to surface (r14) or pull to enemy (stop 2 blocks away). On enemy arrival deal 3 HP. |
| U | Nature’s Wrath | 125s | Area, Damage | 8s storm r6: every 1s 2 random strikes 4 HP + Slow10% 2s. Spend 70 Growth: +1 strike per second. |
