---
id: soul.nature.thorns
resource: TBD
ability_names:
  passive: Thornskin
  tactical: Vine Lash
  movement: Bramble Dash
  ultimate: Garden of Pain
  weapon: Thornwhip
  final_stand: Bloom of Thorns
difficulty: tbd
---

# Soul of Nature – Path of Thorns

Philosophy: Defensive plant manipulation and retaliatory damage through thorn barriers.

Ability Kit

P – Thornskin
- Projectile Reflection: Returns 100% damage from vanilla projectiles (arrows, crossbow bolts, tridents).
- Soul Projectile Reduction: Returns 50% damage from soul-based projectiles after damage mitigation.
- Damage Cap: Maximum 6 HP reflected per individual hit (prevents extreme damage reflection).
- Reflection Mechanics: Damage calculated post-mitigation (after armor/resistance effects).
- No Knockback: Reflected damage doesn't cause knockback to original attacker.
- Visual Effects: Thorn barrier particle effects activate on successful reflection.
- Growth Charge Generation: +2 per successful reflection, +4 for killing blow via reflection.

T – Vine Lash (15s cooldown)
- Auto-Target Priority: Automatically targets lowest HP enemy within 10 blocks.
- Pull Mechanics: Target pulled to stop 1.5 blocks away from caster.
- Manual Override: Can be manually targeted like normal ability.
- Auto-Trigger Condition: Automatically activates if enemy below 4 hearts comes within 6 blocks.
- Auto-Trigger Cooldown: 30s internal cooldown for auto-activation (manual use not affected).
- Growth Charge Generation: +4 for auto-trigger activations, +2 for manual use.
- Emergency Response: Auto-trigger has priority over other auto-abilities.

M – Bramble Dash (15s cooldown)
- Dash Distance: 5 blocks in aimed direction.
- Bramble Trail: 3s duration trail dealing 1 HP/s to enemies crossing.
- Trail Width: 2 blocks wide following exact dash path.
- Root Effect: First enemy hit during dash receives 1s root.
- Dash Properties: Dash cannot be interrupted or slowed during execution.
- Growth Charge Generation: +3 for enemy hit during dash, +2 for each enemy taking trail damage.
- Environmental Synergy: Trail lasts +1s longer on natural terrain (grass, dirt).

U – Garden of Pain (130s cooldown)
- Field Creation: Thorn field radius 7 at target location (range 8).
- Field Duration: 10s active thorn field.
- Persistent Damage: Enemies inside take 1.5 HP/s continuous damage.
- Exit Burst: 3 HP burst damage when enemy leaves field boundary.
- Movement Restriction: Field applies 15% movement speed reduction to enemies inside.
- Growth Charge Generation: +1 per enemy-second inside field, +3 per exit burst.
- Field Stacking: Multiple fields don't stack damage but extend coverage area.

Wpn – Thornwhip
- Base Weapon: Whip-style weapon with thorn aesthetics.
- Entangle Mechanic: Each hit applies Entangle Stack (tracks per enemy).
- Stack Trigger: At 3 stacks, target receives 1s root and all stacks consumed.
- Stack Duration: Individual stacks last 8s before expiring.
- Alt Attack – Sweep (20s cooldown): 4 block frontal arc dealing 4 HP + apply 1 Entangle Stack to all hit.
- Growth Charge Generation: +1 per stack applied, +3 per successful 3-stack root.
- Visual Tracking: Enemies show thorn indicators representing current stack count.

FS – Bloom of Thorns (Per life)
- Trigger: On death, immediate thorn forest creation at death location.
- Forest Area: Radius 6 thorn forest with enhanced effects.
- Enhanced Damage: Same mechanics as Garden of Pain but +50% damage (2.25 HP/s).
- Duration: 12s active forest duration.
- No Reform: Pure area denial Final Stand with no revival condition.
- Forest Interaction: Blocks movement abilities that would cross forest boundary.
- Environmental Permanence: Forest leaves temporary terrain modification (thorny ground texture for 60s).
