---
id: soul.glory.champion
resource: Valor
ability_names:
  passive: Arena Mastery
  tactical: Crushing Blow
  movement: Valor Rush
  ultimate: Colosseum
  weapon: Champion's Spear
  final_stand: Arena of Legends
difficulty: tbd
---

# Soul of Glory – Path of Champion (Resource: Valor)

Philosophy: Honorable combat mastery through duel mechanics and arena control.

Resource – Valor
- Cap: 100
- Gain: Duel state uptime (+2 per second in duel), Valor Rush hits (+4), 1v1 kills (+12), successful Crushing Blow charges (+3).
- Spend: Colosseum enhancement (80 = +2 radius), future champion techniques.
- Decay: 3 per 8s when not in combat or duel state.
- Duel State: Activated when only you and one enemy have damaged each other within last 5s.

Ability Kit

P – Arena Mastery
- Duel Detection: System tracks damage exchanges between you and enemies in last 5s.
- Duel State Activation: When only 1 enemy meets damage exchange criteria, enter Duel State.
- Duel Bonuses: +10% damage dealt & -10% damage taken while in Duel State.
- Valor Generation: +2 per second while Duel State active.
- Multi-Combat Penalty: If 2+ enemies in damage exchange, bonuses disabled.
- State Persistence: Duel State lasts until 5s pass without mutual damage or third party joins.
- Visual Indicator: Golden aura and UI element showing duel status and opponent name.

T – Crushing Blow (13s cooldown)
- Charge Mechanic: 0.6s charge time with progressive damage buildup visual.
- Base Damage: 6 HP + heavy knockback (1.8 blocks).
- Duel Bonus: +2 HP damage if cast while in Duel State.
- Charge Cancellation: Movement or other actions cancel charge (refunds 75% cooldown).
- Knockback Direction: Always away from caster regardless of hit angle.
- Valor Generation: +3 on successful charged hit.
- Counter-play: Charge telegraph allows skilled enemies to dodge or interrupt.

M – Valor Rush (15s cooldown)
- Target Selection: Enemy within range 8 (line of sight required).
- Rush Mechanics: Unstoppable dash to target (cannot be slowed, stunned, or displaced during rush).
- Impact Effects: First enemy hit takes 4 HP + receives Mark debuff.
- Mark Effect: You deal +10% damage to marked target for 5s.
- Duel State Synergy: If cast while in Duel State, refunds 40% cooldown on hit.
- Valor Generation: +4 on successful hit.
- Multi-Target: Rush stops at first enemy hit; cannot pass through.
- Mark Stacking: Only one Mark active at a time; new mark overwrites previous.

U – Colosseum (140s cooldown)
- Arena Creation: Radius 9 arena boundary at cast location.
- Duration: 12s arena persistence.
- Boundary Effects: Attempting to leave teleports back to arena edge.
- Personal Buffs: +20% damage dealt + Resistance I while inside arena.
- Enemy Restrictions: Enemies inside cannot use teleport-type abilities.
- Valor Enhancement: Spend 80 Valor during cast for +2 radius expansion.
- Arena HP: Boundary walls have 200 HP total and can be destroyed to allow escape.
- Tactical Control: Forces extended engagements and prevents hit-and-run tactics.

Wpn – Champion's Spear
- Base Weapon: Spear with extended reach and champion aesthetics.
- Duel Bonuses: While in Duel State, gain +1 reach block and +10% damage.
- Alt Attack – Impale (20s cooldown): Root target for 1s + deal 4 HP damage.
- Spear Mastery: +15% accuracy and +10% critical hit chance against marked targets.
- Reach Advantage: Base reach 1 block greater than standard weapons.
- Honor Code: Weapon deals reduced damage (-25%) to enemies not in combat with you.

FS – Arena of Legends (Per life)
- Trigger: On death, raises arena platform radius 10 around death location.
- Duel Chamber: Seals you and your killer inside arena (other players become spectators outside barrier).
- Spectral Gladiator: You return as spectral auto-combat entity with enhanced AI.
- Victory Condition: Deal ≥20 HP to killer within 10s arena duration.
- Reform Success: Meeting victory condition reforms you at 40% HP with 3s invulnerability.
- Legacy Platform: If reform fails, platform persists 25 minutes granting allies +5% movement speed when nearby.
- Spectator Mode: Other players can observe duel through transparent barrier with special camera angles.
- Honor Rules: Only killer and you can interact within arena; all others locked out. |
