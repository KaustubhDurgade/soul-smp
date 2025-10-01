# Soul of Order – Base (Resource: Balance / Paths: Chains & Clockwork)

Philosophy: Structured control through disciplined ability rotation and battlefield management.

Resource – Balance
- Cap: 100
- Gain: Distinct ability usage in sequence (P→T→M→U cycle +8, partial sequences +2-5), successful Axiom Phase March enemy crossing (+3), interrupting enemy channels with Edict (+5).
- Spend: Enhanced Edict duration (15 Balance = +0.1s Silence, cap +2s total).
- Decay: None during combat; 5 per 10s when out of combat >15s.
- Philosophy: Rewards disciplined rotation over ability spam.

Ability Kit

P – Balanced Flow
- Cooldown Reduction: All abilities have -10% cooldown (multiplicative with other CDR sources).
- Damage Penalty: All damage dealt reduced by 10% (applied after all other modifiers as final step).
- Balance Generation: Using abilities in distinct sequence grants Balance:
  - Full Cycle (P→T→M→U): +8 Balance
  - 3-Ability Sequence: +5 Balance  
  - 2-Ability Sequence: +3 Balance
  - Repeated Ability: +1 Balance (minimal)
- Sequence Tracking: 8s window to complete sequence; breaks on duplicate or timeout.
- Design Intent: Encourages tactical planning over button mashing.

T – Binding Chains (15s cooldown)
- Target: Single enemy within range 12 (line of sight required).
- Root Effect: 2s movement lockout (cannot walk, dash, or teleport).
- Void Lock: 2s ability lockout specifically for movement-type abilities (independent of root duration).
- Aim Freeze: 1s mouse sensitivity reduction to 0 (aim vector locked).
- Chain Visual: Spectral chains connect caster to target for effect duration.
- Balance Synergy: At ≥60 Balance, adds 0.5s Silence to the effect package.
- Counterplay: Teammates can 'break chains' by dealing 8 HP to the chain link (targetable entity).
- Limitations: Cannot target already rooted enemies; effect immediately breaks if line of sight lost.

M – Axiom Phase March (17s cooldown)
- Corridor Creation: Projects linear corridor 6 blocks in aim direction (width 2 blocks).
- Phase Walk: 1s movement through corridor at fixed speed, immune to displacement effects, slows, or knockback.
- Corridor Persistence: Remains for 3s after phase walk completion.
- Enemy Interaction: First enemy crossing corridor takes 1s Root + grants +3 Balance to caster.
- Snap Back: Recast within 1s of completion to instantly return to start position (refunds 30% cooldown).
- Balance Generation: Additional +2 Balance if corridor intersects with 2+ enemies (even if they don't cross).
- Pathing: Corridor creation fails if endpoint in solid block; truncates at nearest valid position.
- Tactical Use: Ideal for repositioning, enemy control, or quick escape with snap-back.

U – Edict (125s cooldown)
- Area: Radius 6 centered on caster.
- Channel Cancellation: Interrupts all active channeling abilities within area.
- Base Silence: 4s lockout on all abilities for affected enemies.
- Movement Lock: Additional 8s lockout specifically for movement abilities (stacks with base silence).
- Balance Enhancement: Spend Balance in increments of 15 to extend base silence by +0.1s each (maximum +2s extension at 30 Balance spent).
- Priority Targeting: Affects enemies first, then neutrals; allies immune.
- Counterplay Window: 0.5s telegraph before effect applies (audio cue + particle buildup).
- Interaction: Cancellation grants +5 Balance per interrupted ability.

Implementation Notes
- Balance Tracking: UI element shows current balance + sequence progress.
- Sequence Reset: Logging out or dying resets sequence progress but preserves Balance resource.
- Edge Case: If multiple Order players in area, Balance generation reduced by 50% to prevent stacking abuse.

Balance Philosophy
- Damage penalty encourages support/control role rather than burst damage.
- High CDR with Balance system rewards patient, methodical play.
- Control-heavy kit with limited escape (snap-back only) requires positioning skill.
