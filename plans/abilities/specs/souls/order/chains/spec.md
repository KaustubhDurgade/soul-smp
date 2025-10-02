---
id: soul.order.chains
resource: TBD
ability_names:
  passive: Shackled Will
  tactical: Iron Bind
  movement: Chain Pull
  ultimate: Prison of Order
  weapon: Lawkeeper's Chains
  final_stand: Crystalline Tribunal
difficulty: tbd
---

# Soul of Order – Path of Chains

Philosophy: Binding and enforcement through escalating restraints and battlefield control.

Ability Kit

P – Shackled Will
- Trigger: Every successful melee or ability hit on enemies.
- Effect: Apply 10% movement speed reduction for 2s (tag: Shackle).
- Stacking: Effect refreshes duration but does not stack magnitude; maintains single 10% slow.
- Duration Extension: Subsequent hits on same target within 2s extend duration by +0.5s (max total 4s).
- Visual: Faint chain particle effects around affected target's feet.
- Synergy: Shackled targets take +1s duration from other Chain path abilities.
- Limitation: Effect breaks if target breaks line of sight for >3s.

T – Iron Bind (18s cooldown)
- Target: Single enemy within range 10.
- Base Effect: Spectral chains trap target for 3s (complete movement, dash, and teleport lockout).
- Root Extension: If target already has any root effect, Iron Bind duration extends by +1s.
- Chain Durability: Chains have 12 HP and can be attacked by allies of the target to break early.
- Escalation: If target was affected by Shackled Will, add +0.5s duration and chains gain +4 HP.
- Visual: Heavy spectral chains anchor target to ground with periodic chain-rattle audio.
- Balance Note: Long cooldown compensates for powerful lockdown effect.

M – Chain Pull (14s cooldown)
- Dual Mode: Can target enemy (range 12) or terrain anchor point.
- Enemy Mode: Pull self to target, stopping 1.5 blocks away + apply 0.5s mini-root on arrival.
- Terrain Mode: Standard grapple mechanics to surfaces or blocks.
- Chain Visual: Visible chain projectile with 0.2s travel time (telegraphed trajectory).
- Enemy Interaction: Target takes 1 HP from chain impact + brief 0.2s stun.
- Combo Potential: Arrival triggers Shackled Will passive if within melee range.
- Escape Prevention: Mini-root prevents immediate counter-mobility for brief window.
- Range Scaling: At high Balance (≥70), range extends to 16 blocks.

U – Prison of Order (140s cooldown)
- Structure: Creates cage boundary at radius 8 from cast point (not caster-following).
- Duration: 10s cage persistence.
- Containment Effects:
  - Enemies inside: -50% healing from all sources, hunger/saturation regeneration paused.
  - Boundary Enforcement: Attempting to leave teleports violator back to cage center (1 free return per target).
  - Second Escape: Second attempt to leave is allowed but inflicts 4 HP true damage.
- Visual: Crystalline barrier walls with gaps showing containment field.
- Counterplay: Cage walls have HP (80 total) and can be broken by focused fire.
- Tactical Use: Area denial, healing disruption, forced engagement zone.

Wpn – Lawkeeper's Chains
- Base Weapon: Standard damage with chain visual effects.
- Alt Attack – Chain Shockwave (24s cooldown):
  - Area: 8 block radius from caster
  - Effect: Bind up to 3 closest enemies with 1.5s root each
  - Selection: Prioritizes enemies not currently rooted
  - Chain Link: Bound enemies connected by visible chains to caster
  - Independent Duration: Each enemy's root timer independent
- Chain Breaking: Allies can attack individual chains (8 HP each) to free specific targets.
- Synergy: Shockwave triggers Shackled Will on all bound targets.

FS – Crystalline Tribunal (Per life)
- Trigger: On death, 1.5s delay before activation.
- Crystal Court: Radius 8 crystalline structure rises from ground.
- Stasis Aura Effects (10s duration):
  - Enemies inside: 20% movement speed reduction + 10% longer ability cooldowns
  - Stasis buildup: Each second inside builds Stasis Stack (max 5)
  - At 5 stacks: Additional 1s silence when leaving court
- Reform Condition: If ≥5 distinct enemy players affected by stasis during full duration, reform at 30% HP at court center.
- Persistence: If reform fails, court becomes permanent structure (25 real minutes) granting allies +5% CDR while inside.
- Court Interaction: Structure has HP (120) and can be destroyed, but grants defensive bonuses to occupants.
- Tactical Legacy: Persistent court creates strategic map control point. Order – Path of Chains
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Shackled Will | – | Passive, Debuff | Each hit slows target 10% 2s (refresh). |
| T | Iron Bind | 18s | Control | Trap in spectral chains 3s (no move/dash/teleport). +1s if already rooted. |
| M | Chain Pull | 14s | Mobility, Control | Grapple to enemy (r12) or terrain; enemy arrival mini-root 0.5s. |
| U | Prison of Order | 140s | Area, Control | Cage radius8 boundary 10s: enemies -50% healing & hunger regen paused; leaving teleports back once (2nd attempt allowed). |
| Wpn | Lawkeeper’s Chains | 24s | Weapon, Control | Alt shockwave: bind up to 3 enemies (r8) root 1.5s each (independent). |
| FS | Crystalline Tribunal | Per life | Final Stand, Area | On death crystal court r8 (10s stasis aura slowing 20% & +10% ability CD). If ≥5 distinct enemies affected reform 30% HP; else persists 25m granting allies +5% CDR inside. |
