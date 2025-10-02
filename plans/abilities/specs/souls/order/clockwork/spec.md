---
id: soul.order.clockwork
resource: Temporal Charge
ability_names:
  passive: Temporal Stability
  tactical: Rewind
  movement: Chronopivot
  ultimate: Stasis Lock
  weapon: Chronoblade
  final_stand: Temporal Fracture
difficulty: tbd
---

# Soul of Order – Path of Clockwork (Resource: Temporal Charge)

Philosophy: Time manipulation through precise temporal anchoring and clockwork precision.

Resource – Temporal Charge
- Cap: 100
- Gain: Temporal Stability procs (+8), cooldown reductions during Chronopivot window (+1 per second reduced), Chronoblade hits (+1), temporal effects affecting multiple targets (+3).
- Spend: Rewind history extension (50 = +1s history buffer, max 5s total), Stasis Lock enhancement.
- Decay: 2 per 12s out of combat.
- Threshold: At ≥80 Temporal Charge, all temporal effects gain +25% effectiveness.

Ability Kit

P – Temporal Stability
- Proc Chance: 10% chance per ability use to not trigger cooldown.
- Proc Limitation: Cannot proc on consecutive ability uses (requires different ability or failed proc between successes).
- Temporal Charge Generation: +8 per successful proc.
- Enhanced Mode: At ≥80 Temporal Charge, proc chance increases to 15%.
- Proc Tracking: UI indicator shows proc availability and consecutive use prevention.
- Excluded Abilities: Ultimate abilities cannot benefit from procs (balance limitation).
- Audio Cue: Distinctive clockwork chime sound on successful proc.

T – Rewind (24s cooldown)
- History Buffer: Continuously tracks damage taken and negative effects for last 3s.
- Base Reversion: Reverse up to 8 hearts damage and remove negative potion effects (excludes instant death).
- Death Prevention: If lethal damage occurred <0.5s ago, prevent death and set to 1 HP instead of full reversion.
- Temporal Charge Enhancement: Spend 50 Charge to extend history buffer by +1s (maximum 5s total buffer at 200 Charge spent).
- Limitations: Cannot reverse environmental deaths (void, lava), only combat damage and debuffs.
- Rewind Immunity: 3s window after rewind where another rewind cannot be cast (prevents spam).
- Visual: Time-reverse particle effects showing damage numbers reversing.

M – Chronopivot (16s cooldown)
- Anchor Phase: Places temporal anchor at current position + gain 15% movement speed for 2s.
- Recast Window: 4s window to recast and trigger rewind effect.
- Rewind Effects:
  - Position: Return to anchor location
  - Fall/Void Negation: Negate last 2s of fall or void damage
  - Health Restoration: Restore up to 4 HP lost in last 2s
- Temporal Charge Generation: +4 if at least 6s total cooldown reduction occurred during anchor window (from any source).
- Anchor Visualization: Temporal marker visible to caster showing anchor point.
- Speed Synergy: Movement speed bonus helps reach optimal positioning for rewind.
- Edge Cases: Anchor fails if placed in invalid location (mid-air without support).

U – Stasis Lock (135s cooldown)
- Area: Radius 5 centered on caster.
- Stasis Effect: Freeze all enemies for 5s (cannot move, attack, or use abilities).
- Cooldown Manipulation: Apply +3s cooldown to all non-trusted abilities currently off cooldown.
- Temporal Charge Enhancement: Spend 60 Charge to extend stasis duration by +1s.
- Stasis Immunity: Affected targets gain 8s stasis immunity after effect ends.
- Visual: Crystalline time-lock effect with frozen particle animations.
- Counterplay: Strong displacement effects (knockback ≥3 blocks) can break stasis early.

Wpn – Chronoblade
- Base Weapon: Moderate damage with temporal particle effects.
- Cooldown Reduction: Each hit reduces random non-ultimate ability cooldown by 1s.
- Internal Cooldown: 1s between cooldown reduction procs (prevents rapid-fire abuse).
- Temporal Charge Generation: +1 per cooldown reduction proc.
- Smart Reduction: Prioritizes abilities with longest remaining cooldowns.
- Proc Visualization: Clockwork gear particles on successful cooldown reduction.
- Limitation: Cannot reduce cooldowns below 1s remaining.

FS – Temporal Fracture (Per life)
- Trigger: On death, creates temporal anomaly at death location.
- Time Bubble: Radius 8, duration 6s.
- Temporal Effects:
  - Enemy movement: 70% speed reduction
  - Projectiles: Completely paused mid-flight
  - Ability casting: 50% slower animation speeds
- Damage Logging: Records all damage events within bubble (running total).
- Reform Condition: If ≥60 HP total damage recorded, reform at 30% HP + trigger Temporal Shatter.
- Temporal Shatter: 4 HP AOE damage + resume all paused projectiles at +25% speed.
- Persistence: If reform fails, bubble becomes 20-minute debuff zone applying +10% cooldown increase to enemies inside.
- Interaction: Multiple Clockwork deaths stack bubble effects (max 3 overlapping).
