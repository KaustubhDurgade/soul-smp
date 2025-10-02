---
id: soul.trickery.base
resource: Deception Layers
ability_names:
  passive: False Image
  tactical: Smoke Trick
  movement: Quickstep
  ultimate: Grand Illusion
difficulty: tbd
---

# Soul of Trickery – Base (Resources: Deception Layers / Paths: Masks & Misfortune)

Philosophy: Misdirection and illusion through decoys and stealth tactics.

Resource – Deception Layers
- Cap: 100
- Gain: Disguise/invisibility duration (+2 per second), successful decoy survival (+5), enemy misdirection events (+3), successful illusion damage (+4).
- Spend: Grand Illusion enhancement (40 = +1 clone, max +2 additional).
- Decay: 2 per 10s when not using stealth or illusion abilities.
- Philosophy: Rewards active deception and misdirection tactics.

Ability Kit

P – False Image
- Trigger: 20% chance when taking damage from any source.
- Decoy Properties: 8 HP, 4s duration, mimics your movement patterns.
- Movement AI: Decoy follows basic pathfinding toward nearest enemy, uses simple evasion patterns.
- Internal Cooldown: 6s between decoy spawns to prevent spam.
- Deception Generation: +5 when decoy survives full duration or absorbs killing blow intended for you.
- Decoy Interaction: Enemies targeting decoy waste abilities; decoy "death" produces small particle explosion.
- Advanced Mimicry: Decoy copies your current skin and armor appearance but deals no damage.

T – Smoke Trick (14s cooldown)
- Area: 4 block radius smoke cloud centered on cast location.
- Enemy Effects: Blind effect 1.5s for enemies inside cloud.
- Personal Benefit: Gain Invisibility I for 1s upon casting.
- Cloud Persistence: Smoke remains for 4s, providing visual obscurement.
- Stealth Mechanics: Invisibility breaks on dealing damage or using abilities (standard rules).
- Deception Generation: +3 per enemy blinded, +2 for successful stealth utilization.
- Tactical Applications: Escape tool, engagement opener, area denial.

M – Quickstep (11s cooldown)
- Dash Distance: 5 blocks in aim direction with rapid movement.
- Stealth Application: Invisibility I for 1s after dash completion.
- Damage Bonus: First attack out of invisibility deals +15% damage.
- Dash Properties: Brief invulnerability frames (0.1s) during dash, immune to projectiles.
- Deception Generation: +4 if damage bonus successfully applied to enemy.
- Cooldown Reduction: At ≥70 Deception Layers, cooldown reduces to 9s.
- Combo Potential: Pairs well with stealth-based follow-up abilities.

U – Grand Illusion (115s cooldown)
- Clone Creation: Spawns 3 identical clones lasting 10s.
- Clone Properties: 50% damage output, take 150% damage (fragile but threatening).
- Mirror Mechanics: Clones copy your attack animations and movement.
- Projectile Illusion: Clone projectiles are cosmetic except 30% chance one projectile per volley is real.
- Deception Enhancement: Spend 40 Deception Layers to summon +1 additional clone (max 5 total).
- Clone AI: Semi-intelligent targeting, prefers enemies you're engaging.
- Duration Extension: Each clone surviving 5+ seconds grants +2 Deception Layers.

Implementation Notes
- Decoy spawn position logic to prevent wall-clipping.
- Smoke cloud uses particle system with gradual opacity fade.
- Clone synchronization to maintain believable illusion effect.

Balance Considerations
- Resource decay encourages active trickery over passive accumulation.
- Invisibility duration balanced against stealth gameplay impact.
- Clone damage scaling prevents overwhelming burst potential.
