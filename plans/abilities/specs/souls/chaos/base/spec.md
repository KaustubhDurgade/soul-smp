---
id: soul.chaos.base
resource: Instability
ability_names:
  passive: Chaotic Aura
  tactical: Unstable Burst
  movement: Fracture Warp
  ultimate: Riftstorm
difficulty: tbd
---

# Soul of Chaos – Base (Resource: Instability / Paths: Discord & Void)

Design Pillars: Volatility, emergent advantage, controlled exploitation of randomness.

Resource – Instability
- Cap: 100.
- Gain: Random roll events (Aura pair resolution, Burst outcomes hitting ≥2 targets, Rift interactions, successful dual-phase Fracture Warp with mini rift affecting ≥2 enemies).
- Threshold Effects: 80+ = Riftstorm pull strength +25%; (future hook) certain negative backlash odds slightly reduced at ≥60 to reward active play.
- Decay: Out of combat (no damage dealt or taken for 20s) drains 3 every 5s.
- Event Hooks: GainResourceEvent(Instability, amount, sourceTag), ThresholdCrossEvent(Instability,80), SpendResourceEvent when future spenders added.

Ability Kit

P – Chaotic Aura (30s rotational proc)
- Every 30s (server-aligned or per-player timer) the aura rolls one Buff Effect and one Debuff Effect from curated weighted tables.
	- Buff Table Examples (weights in parentheses): Minor Haste (20), Resistance I 6s (15), +10% Damage 6s (15), +15% CDR 5s (10), Lifesteal 10% 6s (10), Burst Shield 6 HP (8), Instability Infusion (+10 Instability immediate) (5), Amplified Burst (next T +40% effectiveness) (5), Surge Cleanse (cleanse +2s immunity) (4), Flawless Phase (next movement grants 0.3s invuln) (3).
	- Debuff Table Examples: Slowness I 4s (18), -10% Damage 6s (15), Fragile (+15% damage taken) 5s (12), Wither I 3s (10), Silence 1.5s (8), Cooldown Elongation (+10% ability CDs) 6s (10), Self Ignite 3s (6), Instability Leak (-8 Instability) (8), Self Knock (tiny 0.5 block) (4), Void Twinge (next movement dash distance -2) (4).
- Duplicate debuff rerolls once to maintain variability; second duplicate stands.
- Interaction Rules: If a debuff would kill (e.g., Wither at 1 HP) system clamps at 0.5 hearts (non-lethal safeguard for aura self-harm events).
- Synergy: Positive buff durations extended by Discord path passive later; Instability gain spikes when high-impact buff triggers (Amplified Burst grants +4 Instability instantly).

T – Unstable Burst (15s cooldown)
- Cast: 0.2s windup; radial emission radius 4 centered on player.
- Outcome Roll (mutually exclusive; roll weights):
	1. Standard Surge (60%): Enemies take 4 HP (post-mitig); allies in radius heal 4 HP (choose either enemy damage OR ally heal contextually? Implementation mode A: both happen targeting respective factions; mode B: pick one—current design uses dual-mode simultaneous for pacing; select definitive during balancing). Grants +2 Instability if ≥2 enemies damaged.
	2. Empowered Shock (25%): 6 HP damage + radial knockback (vector magnitude ~1.2, ~2.5 blocks displacement) OR (if at least one ally inside & ≤1 enemy) convert to Cleanse Pulse (cleanse negative statuses & apply 2s Resistance I). Grants +4 Instability.
	3. Mimic Pulse (10%): Copies a random other Soul’s BASE Tactical (weighted to exclude extremely high control/execute types). Damage/heal numbers scaled to 80% original to prevent overlap creep. Grants +3 Instability if mimic hits/applies.
	4. Backlash (5%): Self 4 HP true (bypass armor) + small self knock. Refunds 40% of cooldown if self HP after backlash <40% max; grants +6 Instability (risk→reward loop).
- Single Target Safety: Cap total damage to any one entity at 8 HP per cast (after mimic modification) to avoid outlier stacking.
- UI Suggestion: Particle color shift preview after RNG roll (server authoritative) ~0.05s pre-impact for telegraph identity.

M – Fracture Warp (12s cooldown)
- Cast 1: Immediate short-range blink 5 blocks along aim vector (pathing abort if target location in solid; fallback: nearest safe edge node within a 1-block radius).
- Optional Cast 2: 0.4s window; second blink additional 5 blocks with ±1 lateral random fracture offset perpendicular to initial vector (choose from discrete offsets: -1, 0, +1 weighted 40/20/40 to encourage lateral variance without excessive jitter).
- Distortion Zones: Each blink leaves a 2s lingering distortion field (cylinder radius 1.5) applying 15% slow (tag: Distortion, stacking category: MovementSlowMinor; no stacking beyond strongest in category).
- Mini Rift: If second blink used, spawn mini rift at second arrival for 2s pulling enemies inside radius 2 at 0.5 block/s (non accumulative pulls). Deals no damage (pure control). Grants +4 Instability if at least 2 distinct enemies experience >0.5 block net displacement.
- Edge Cases / Safeguards: Disallow second blink if first placed player inside portal mechanics or restricted region. Provide ghost preview (client) of potential landing offset direction.
- Tech Notes: Use server path interpolation to ensure no desync with lateral randomization; record both blink endpoints for replay/debug.

U – Riftstorm (120s cooldown)
- Active Duration: 6s unstable rift (entity) anchored at cast position; radius 5.
- Pull Mechanics: Baseline inward velocity 0.7 block/s (vector scaled by distance; min pull 0.2). At ≥80 Instability, multiply base pull by 1.25 (0.875 block/s base). Immunity list: Boss-class flagged & players currently in displacement immunity window (<0.5s after certain movement abilities) affected at 50% magnitude.
- Damage Model: 2 HP/s (tick each second). 6th second final tick converts to True damage (bypasses armor) and triggers collapse event.
- Collapse: Radial knockback 4 blocks (strength 1.6). On collapse apply brief (0.4s) anti-blink lock (Tag: RiftLock) to enemies pulled ≥3 consecutive ticks.
- Instability Interaction: Each enemy tick pulled while you are above 50 Instability grants +1 Instability every second (cap +5 per cast) reinforcing uptime play.
- Visual / Audio: Spiraling particle ring each tick, tonal pitch escalation each second to telegraph time-to-collapse.
- Counterplay: Enemies with line-of-sight dash can exit if they exceed 1.5 blocks outward velocity; displacement resistances (≥50%) reduce pull proportionally.

Implementation Notes
- Logging: Record RNG outcome categories (Aura pair, Burst outcome) for balance telemetry buckets (distribution drift detection >5% triggers alert).
- Future Extensibility: Add Instability spend skill (not yet designed) to force positive bias for next 2 outcomes (similar to Discord path spending) for advanced player agency.

Balance Considerations
- Average Instability gain per minute target: 35–45 under active combat to ensure reaching 80 threshold every ~2 minutes without deliberate farming.
- Backlash providing large Instability ensures volatility loop but bounded by self-risk (recommend internal daily cap if abused in safe zone).

Synergy Hooks
- Discord Path: Extends positive buff durations & introduces Entropy spend bias—already integrated conceptually.
- Void Path: Void Charge synergies leverage mini rift displacement for multi-soul combo setups.

Edge Cases
- Aura Debuff Silence overlapping with Backlash self-damage: Silence does not prevent Backlash effect resolution (already rolled).
- Mimic selecting a Tactical that summons persistent entity: clone persists with 70% duration if base would exceed 8s (cap rule).
