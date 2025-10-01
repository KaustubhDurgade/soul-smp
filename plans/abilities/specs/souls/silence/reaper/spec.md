# Soul of Silence – Path of the Reaper (Resource: Soul Meter)

Philosophy: Death mastery through soul harvesting and execution mechanics.

Resource – Soul Meter
- Cap: 100
- Gain: Killing blows (+10), executions (+15), damage to enemies below 25% HP (+2 per hit), Soul Harvest beam ticks (+1).
- Spend: Soul Harvest beam intensity scaling (consumed during beam), Deathstride activation (10 per use).
- Decay: 1 per 8s when not dealing damage to low-health enemies.
- Death Focus: Resource generation accelerates when multiple enemies are below execution thresholds.

Ability Kit

P – Harvest Spirit
- Killing Blow Reward: +10 Soul Meter + heal 2 hearts for each enemy killed.
- Soul Meter Display: UI element shows current meter with execution threshold indicators.
- Healing Mechanics: Heals apply immediately on kill confirmation (prevents death-trading).
- Kill Credit: Requires final blow (not just assist) for full benefits.
- Undead Synergy: Killing undead mobs grants +15 Soul Meter instead of +10.
- Meter Overflow: Excess healing above max HP converts to temporary absorption (max 2 hearts, 30s).
- Visual Effects: Soul orbs visibly flow from killed enemies to caster.

T – Reaping Slash (14s cooldown)
- Area: 5 block frontal cleave hitting all enemies in arc.
- Base Damage: 5 HP to all enemies hit.
- Life Steal: Heal 1 heart per enemy hit (multiple enemies = multiple hearts).
- Soul Meter Bonus: +10 if killing blow OR target below 25% HP when hit.
- Cleave Mechanics: Equal damage to all targets, no falloff.
- Execution Synergy: Enhanced effects when multiple low-health enemies present.
- Cooldown Reduction: -2s cooldown if slash kills at least one enemy.

M – Deathstride (15s cooldown)
- Target Selection: Automatically targets lowest HP enemy within 8 blocks.
- Health Threshold: Only targets enemies at or below 50% HP.
- Cost: 10 Soul Meter per activation (refunded if no valid target).
- Blink Effect: Instant teleport to target location.
- Arrival Effect: Apply Minor Slow I for 1s to target.
- Target Verification: Ability fails gracefully if target dies during cast.
- Execution Setup: Ideal positioning tool for finishing wounded enemies.

U – Soul Harvest (150s cooldown)
- Activation: Consumes ALL current Soul Meter to fuel beam.
- Beam Properties: 8 block range, 0.5s tick rate, max 4s duration.
- Damage Calculation: 4 HP base + 0.5 HP per 10 Soul Meter consumed.
- Execution Threshold: Instantly kills enemies below 15% HP regardless of beam damage.
- Beam Mechanics: Continuous targeting required; beam follows crosshair.
- Channel Vulnerability: Can be interrupted like other channeled abilities.
- Resource Scaling: Higher Soul Meter = proportionally more damage per tick.

Wpn – Deathscythe
- Base Weapon: Scythe with enhanced reach and execution properties.
- Execution Passive: Automatically kills enemies below 10% HP (instant execution).
- Alt Attack – Reap (25s cooldown): Wide arc attack dealing 6 HP, ignores all shields/barriers.
- Execution Trigger: Execution attempts trigger Harvest Spirit benefits.
- Soul Meter Generation: +3 per execution, +1 per Reap hit.
- Visual Design: Scythe glows more intensely as Soul Meter increases.

FS – Grim Finale (Per life)
- Trigger: On death, affects all enemies within 6 blocks of death location.
- Execution Check: Enemies below 25% HP are instantly executed (true damage bypass).
- Fallback Damage: Enemies above 25% HP take 6 HP true damage.
- Simultaneous Resolution: All effects apply at the same moment.
- No Reform: Pure execution Final Stand with no revival mechanics.
- Death Delay: 0.5s delay between death and Finale activation (allows for last-second healing). Silence – Path of the Reaper (Resource: Soul Meter)
| Code | Name | CD | Tags | Details |
|------|------|----|------|---------|
| P | Harvest Spirit | – | Passive, Resource | Killing blow +10 Soul Meter (0–100) & heal 2 hearts. |
| T | Reaping Slash | 14s | Damage, Heal | Cleave r5 5 HP & steal 1 heart (heal you) +10 Soul Meter if kill or target <25% HP. |
| M | Deathstride | 15s | Mobility, Exec, Resource | Blink to lowest HP enemy within 8 (≤50% HP). Costs 10 Soul Meter; refund if no target. Applies Minor Slow 1s. |
| U | Soul Harvest | 150s | Damage, Exec | Consume all Soul Meter firing beam r8 tick/0.5s: 4 HP base +0.5 HP per 10 Meter. Executes <15% HP. Max 4s. |
| Wpn | Deathscythe | – | Weapon, Exec | Executes enemies <10% HP. Alt Reap (25s): wide arc 6 HP ignore shields. |
| FS | Grim Finale | Per life | Final Stand, Exec | On death execute enemies within 6 below 25% HP (true); others take 6 HP true. |
