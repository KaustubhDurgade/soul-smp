# Soul of Chaos – Path of Void (Resource: Void Charge)

Philosophy: Dimensional manipulation and void energy exploitation for precise control.

Resource – Void Charge
- Cap: 100
- Gain: Phase Step activations (+4), Lattice collapse affecting ≥2 enemies (+6), Singularity damage ticks on enemies (+1 per tick), Voidripper Armor Sunder applications (+2), void patch damage (+1 per 0.5 HP dealt).
- Spend: Singularity enhancement (70 = knockback immunity during channel), future void techniques.
- Decay: 1 per 6s out of combat.
- Threshold: At ≥80 Void Charge, all void effects gain +20% area and Phase Step chance increases to 18%.

Ability Kit

P – Phase Step
- Trigger: 12% chance when taking damage (any source except fall/environmental).
- Effect: Negate incoming damage completely + teleport 1 block perpendicular to damage source vector (server chooses safest direction from 4 cardinal options).
- Internal Cooldown: 2s per activation to prevent rapid-fire negation.
- Void Charge Generation: +4 per successful phase.
- Enhanced Mode: At ≥80 Void Charge, chance increases to 18% and teleport distance extends to 1.5 blocks.
- Limitations: Cannot negate damage >15 HP in single hit (balancing vs high burst); cannot phase while channeling abilities.
- Audio/Visual: Brief void ripple effect + distinctive sound cue for counterplay awareness.

T – Rift Slash (11s cooldown)
- Area: Frontal arc 3 blocks wide, 2 blocks deep.
- Damage: 6 HP true damage (bypasses all armor and damage reduction).
- Cast Time: 0.3s windup with void energy buildup visual.
- Void Charge Synergy: At ≥50 Void Charge, arc extends to 4 blocks wide.
- Cleave Logic: Hits all valid targets within arc simultaneously; no damage falloff.
- Counterplay: Telegraph via particle buildup; targets can sidestep during windup.
- Edge Cases: Cannot hit targets behind solid blocks even if within arc geometry.

M – Void Lattice Step (10s cooldown)
- Step 1: Blink 4 blocks + place Void Node (visible marker, duration 3s).
- Step 2 (≤3s): Blink to another location 4 blocks + place second Node.
- Step 3 (≤3s): Blink + place third Node, automatically triggers Lattice Collapse.
- Lattice Collapse: Triangle formed by 3 nodes pulls all enemies inside toward center (0.5 block/s for 2s) + deals 2 HP void damage.
- Void Charge Generation: +6 if collapse affects ≥2 enemies, +3 if only 1 enemy.
- Partial Lattice: If only 1-2 nodes placed before timeout, creates line pull effect (reduced strength).
- Node Interaction: Enemies can destroy nodes (5 HP each) to prevent collapse.
- Cooldown Logic: Full cooldown starts after final node placement or 3s timeout.

U – Singularity (125s cooldown)
- Channel Duration: 4s (interruptible).
- Black Hole Growth: Radius expands from 2 to 5 blocks over duration.
- Pull Strength: 1 block/s baseline (affected by mass/knockback resistance).
- Damage: 10 HP total dealt as 2.5 HP per second.
- Void Lock: All entities exiting singularity receive 3s movement ability lockout.
- Void Charge Enhancement: Spend 70 Charge during cast for knockback immunity + uninterruptible channel.
- Positioning: Singularity anchored at cast location (does not follow caster).
- Escape Mechanics: Strong outward movement abilities (dash, teleport) can overcome pull if >1.5 block/s velocity.
- Visual Escalation: Progressive distortion effects and audio pitch increase.

Wpn – Voidripper
- Base Weapon: Standard melee with void particle effects.
- Armor Sunder Mechanic: Every 3rd consecutive hit applies -10% armor value debuff (stacks up to 3, duration 6s each stack).
- Void Charge Generation: +2 per Armor Sunder application.
- Alt Attack – Rip (22s cooldown): Consume 1 Armor Sunder stack to deal +4 HP true damage. If no stacks available, ability fails but refunds 50% cooldown.
- Stack Tracking: UI indicator shows current stack count on target; resets on target swap or 4s gap between hits.
- Multi-Target: Each enemy maintains separate stack count.

FS – Void Implosion (Per life)
- Trigger: On death, 1s delay before activation.
- Implosion Sphere: Radius shrinks from 8 to 3 blocks over 10s duration.
- Damage: 1.5 HP/s true damage to all entities inside.
- Void Lock Application: Every 3s tick applies 1s movement ability lockout to affected targets.
- Reform Condition: If total damage dealt inside sphere ≥200 HP, reform at 25% HP at sphere center.
- Void Scar: If reform fails, leaves permanent terrain modification (30 real minutes) granting +10% CDR aura to you on future lives when within 5 blocks.
- Interaction: Void Scar stacks with other CDR sources multiplicatively (cap 40% total CDR).
- Escape: Players can exit sphere during shrinking but take exit damage (3 HP true).
