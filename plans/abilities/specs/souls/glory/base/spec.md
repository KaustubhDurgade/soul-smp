---
id: soul.glory.base
resource: Legacy Sparks
ability_names:
  passive: Hero's Spark
  tactical: Rallying Cry
  movement: Legacy Vault
  ultimate: Immortal Banner
difficulty: tbd
---

# Soul of Glory – Base (Resource: Legacy Sparks / Paths: Eternal Flame & Champion)

Philosophy: Lasting impact through team empowerment and heroic presence.

Resource – Legacy Sparks
- Cap: 100
- Gain: Kills (+8), assists (+4), Legacy Vault ally buffing (+4 if ≥2 allies), Banner persistence duration milestones (+2 per 4s active), Glory Stack accumulation (+1 per 5 stacks).
- Spend: Immortal Banner duration extension (60 = +4s duration).
- Decay: 1 per 10s when no allies within 15 blocks.
- Philosophy: Resource tied to team impact and heroic moments.

Ability Kit

P – Hero's Spark
- Combat XP Bonus: +20% experience from combat sources (damage dealt, healing done, kills, assists).
- Glory Stacks: Kills grant cosmetic Glory Stack counter (max 50) visible to all players.
- Legacy Spark Generation: +8 per kill, +4 per assist, +1 per 5 Glory Stacks accumulated.
- Stack Display: UI element shows current Glory Stacks with bronze/silver/gold tier visual progression (10/25/40 thresholds).
- XP Scaling: Bonus applies to both vanilla XP and any custom progression systems.
- Team Visibility: Other players can inspect your Glory Stack count, encouraging competitive achievement.

T – Rallying Cry (15s cooldown)
- Area: 6 block radius centered on caster.
- Speed Buff: Speed II for 5s to all allies in range.
- Damage Buff: +5% damage dealt for 5s (multiplicative with other bonuses).
- Buff Stacking: Effects stack with other Speed/damage sources but not multiple Rallying Cries.
- Legacy Sparks: +2 per ally affected (encouraging group coordination).
- Audio: Heroic horn sound effect audible to all nearby players.
- Range Extension: At ≥70 Legacy Sparks, radius increases to 8 blocks.

M – Legacy Vault (15s cooldown)
- Leap Trajectory: Parabolic arc 7 blocks forward, 3 blocks peak height.
- Impact Zone: 4 block radius area persists for 5s at landing point.
- Zone Benefits: Allies entering gain +5% damage for 5s (stacks up to 2 times for +10% total).
- Landing Damage: 4 HP + minor knockback (1 block) to enemies at impact.
- Legacy Spark Generation: +4 if ≥2 allies receive the buff during zone lifetime.
- Vault Mechanics: Cannot be interrupted mid-flight; provides brief invulnerability during peak (0.2s).
- Tactical Use: Positioning tool that creates beneficial terrain for team fights.

U – Immortal Banner (120s cooldown)
- Placement: Banner entity deployed at target location (range 8, line of sight required).
- Base Duration: 12s active duration.
- Area: 6 block radius around banner.
- Banner Effects:
  - Allies: +10% damage dealt + Regeneration I
  - Banner HP: 50 (can be destroyed by enemies)
- Early Destruction Bonus: If banner destroyed by enemies OR manually recalled, heal caster 4 hearts.
- Legacy Spark Enhancement: Spend 60 Sparks during placement for +4s duration extension.
- Banner Interaction: Allies can 'rally to banner' (right-click) for additional +2% damage (stacks with base).
- Cooldown Start: Begins when banner is destroyed or expires naturally.

Implementation Notes
- Glory Stack persistence across sessions (saved to player data).
- Legacy Spark UI integration with resource bar.
- Banner placement restrictions (no invalid terrain, minimum spacing from other banners).

Balance Considerations
- XP bonus encourages active combat participation.
- Team-focused resource generation discourages solo play.
- Banner vulnerability creates risk/reward for powerful area control.of Glory – Base (Resource: Legacy Sparks / Paths: Eternal Flame & Champion)
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Hero’s Spark | – | Passive, Resource | +20% combat XP; kills grant Glory Stack (cosmetic) & Legacy Sparks. |
| T | Rallying Cry | 15s | Support, Buff | r6 allies Speed II 5s & +5% damage 5s. |
| M | Legacy Vault | 15s | Mobility, Support, Damage, Resource | Arc leap (7f/3u) create Impact Zone r4 5s granting allies +5% damage 5s (stack2). Land shockwave 4 HP knock minor. +4 Sparks if ≥2 allies buffed. |
| U | Immortal Banner | 120s | Area, Support | Place banner 12s r6 +10% damage & Regen I allies. Destroy early: heal you 4 hearts. Spend 60 Sparks: +4s duration. |
