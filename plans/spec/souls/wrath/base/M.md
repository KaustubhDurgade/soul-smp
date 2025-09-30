# Wrath Base Movement: Inferno Chain Surge

Code: M
Cooldown: 15s
Tags: [Mobility, Damage, Control, Resource]

## Mechanics
1. Fire chain launches to target point up to 5 blocks (line trace).
2. On latch: Pull momentum then slingshot you 3 blocks past target along vector.
3. Leaves burning tether for 3s: Enemies crossing tether take 1 HP fire per second & are slowed 20% (applied per second tick; not stacking).
4. Recast within 2s to snap back to original point detonating tether for 6 HP fire (single instance; single target cap 6). If not recast tether just expires.

## Resource
+5 Heat on first enemy hit by tether or detonation.

## Micro Interaction
- Snapping back counts as movement; immune to minor slows during snap.
- Cannot pass through solid full blocks on initial chain (fails silently with short puff FX).

## Counterplay
- Step out of tether lane; avoid clustering for detonation.
- Silence before recast prevents snap detonation.
