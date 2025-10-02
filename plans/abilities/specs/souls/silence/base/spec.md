---
id: soul.silence.base
resource: TBD
ability_names:
  passive: Quiet Aura
  tactical: Null Strike
  movement: Silent Step
  ultimate: Silence Field
difficulty: tbd
---

# Soul of Silence – Base (Paths: Reaper & Emptiness)

Philosophy: Nullification and anti-magic through silence effects and ability suppression.

Ability Kit

P – Quiet Aura
- Range: 5 blocks from caster.
- Damage Reduction: Enemies within range deal -8% damage (multiplicative with other modifiers).
- Aura Persistence: Effect follows caster movement, no delay in application/removal.
- Stacking Rules: Multiple Silence auras don't stack; strongest takes priority.
- Silent Presence: Your movement produces 50% less sound (footsteps, armor clanking).
- Detection Immunity: Immune to sound-based detection methods.
- Synergy Foundation: Other Silence path abilities gain enhanced effects while Quiet Aura affects enemies.

T – Null Strike (12s cooldown)
- Enhancement Window: Next melee attack within 5s gains special properties.
- Casting Target Detection: If hit target is channeling or casting ability, triggers cancellation + 2s Silence.
- Alternative Effect: If target not casting, deals +3 HP bonus damage.
- Channel Detection: Identifies ongoing channels, charges, or ability windups.
- Silence Application: Standard silence rules (prevents ability activation).
- Window Persistence: Enhancement persists until used or 5s timeout.
- Visual Indicator: Weapon glows with nullification energy during enhancement window.

M – Silent Step (13s cooldown)
- Teleport Distance: 6 blocks in aimed direction.
- Sound Suppression: Teleport produces no audio cue.
- Stealth Application: Gain Invisibility I for 1s after teleport.
- Detection Avoidance: Cannot be tracked by sound-based detection during teleport.
- Positioning Tool: Ideal for repositioning without alerting enemies.
- Cooldown Scaling: At high silence mastery, cooldown reduces to 10s.
- Emergency Escape: Can be used while silenced (unlike most movement abilities).

U – Silence Field (120s cooldown)
- Field Creation: 8s duration field radius 5 centered on cast location.
- Ability Lockout: Enemies inside cannot activate any abilities (complete silence).
- Channel Cancellation: Immediately cancels all ongoing channels within field.
- Field Persistence: Field remains at cast location (doesn't follow caster).
- Entry/Exit: Effects apply/remove instantly when crossing field boundary.
- Field Interaction: Other silence effects enhanced within field (+50% duration).
- Counterplay: Field has no physical barrier; enemies can leave freely.
- Visual Design: Distinctive audio dampening effect with particle boundary.

Implementation Notes
- Quiet Aura requires efficient proximity detection for multiplayer performance.
- Null Strike needs reliable ability-state detection system.
- Silent Step requires audio system integration for sound suppression.

Balance Considerations
- Passive damage reduction balanced against lack of offensive passive.
- Silence Field ultimate provides strong area control but no direct damage.
- Kit focused on disruption and utility rather than burst damage.
