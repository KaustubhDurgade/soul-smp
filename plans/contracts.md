
# 🌌 SMP Contract System Blueprint

| Contract Name            | Type                   | Sacrifice                             | Gain / Effect                                                     | Soul Interaction / Resonance Modifier                     | Particle / Display Ideas                                          | Tier / Duration |
| ------------------------ | ---------------------- | ------------------------------------- | ----------------------------------------------------------------- | --------------------------------------------------------- | ----------------------------------------------------------------- | --------------- |
| **Blood Pact**           | Health                 | 20–50% HP                             | AoE lifesteal around player                                       | Blood soul: heals scale + nearby Blood souls              | Red sigil under player, pulsing crimson rings                     | Tier 1, 10s     |
| **Infernal Contract**    | Item                   | Ember Crystal / Flame Item            | Firestorm AoE, +damage for Flame abilities                        | Flame+Wind resonance → Fire Tornado radius ↑              | Flaming circle sigil, fire arcs, smoke trails                     | Tier 2, 15s     |
| **Frozen Oath**          | Item / Soul            | Frost Blade / Movement                | AoE freeze effect, Frost ultimate amplified                       | Frost+Silence → Ice Fog extended                          | Blue runes on ground, icy particle dome                           | Tier 2, 12s     |
| **Shadow Merge**         | Soul                   | Movement / Ability Cooldown           | Summon shadow clones, stealth attack                              | Shadow+Trickery → clones + AoE surprise attack            | Dark mist, ephemeral armorstand clones, shadow trails             | Tier 2, 8s      |
| **Tides Unbound**        | Soul                   | Water mobility / dash                 | Whirlpool radius doubles, pull strength ↑                         | Tides+Lightning → Electrified Whirlpool enhanced          | Spiral bubble particles, prismarine block wave display            | Tier 2, 10s     |
| **Nature’s Tribute**     | Resource / Environment | Nearby trees / plants                 | Healing rain, AoE regeneration                                    | Nature+Tides → Water + Healing synergy                    | Green aura particles, floating leaves, water ripple block display | Tier 1, 8s      |
| **Magma Offer**          | Resource / Environment | Lava source / fire block              | Fire AoE, terrain damage                                          | Flame+Order → Purge Flames amplified                      | Lava eruptions, flame arcs, smoke spirals                         | Tier 2, 10s     |
| **Temporal Pact**        | Time / Action          | Delay 10–20s ability cooldown         | Instant ultimate charge / movement buff                           | Flame+Wind → Fire Tornado duration ↑                      | Golden clock sigil, light-speed streak particles                  | Tier 1, 5s      |
| **Ember’s Tempo**        | Time / Action          | Delay next attack                     | Ignite enemies in AoE                                             | Flame+Trickery → clone dash ignite                        | Flickering ember particles, flame trails                          | Tier 1, 6s      |
| **Harmony Convergence**  | Multi-Soul             | Pool HP of 2–5 players                | Buffs all nearby allies, triggers minor elemental storm           | Resonance: amplifies radius & duration based on # players | Multi-colored aura rings, swirling elemental arcs                 | Tier 2, 12s     |
| **Cataclysm Pact**       | Multi-Soul             | Pool rare items / souls               | Trigger Cataclysmic Convergence, massive AoE                      | Resonance: ultimate fusion effect scales                  | Layered swirling domes, fire+water arcs, shadow spikes            | Tier 3, 15–20s  |
| **Life-for-Power**       | Health / Multi-Soul    | HP sacrifice (5–50% per player)       | Strength boost, cooldown reduction                                | Blood+Shadow → lifesteal + stealth attack combo           | Red-black particle rings, pulsing glow sigils                     | Tier 2, 10s     |
| **Soul Fusion Contract** | Soul / Item            | Sacrifice secondary soul or rare item | Temporary hybrid soul form with mixed abilities                   | Compatible soul pairs unlock joint attacks                | Floating dual-sigil display, swirling dual-element particles      | Tier 3, 12–15s  |
| **Mana Infusion Pact**   | Resource               | Consume mana / energy                 | Boost ultimate or passive abilities                               | All elemental souls → damage + radius increase            | Floating mana orbs, shimmering particle dome                      | Tier 1–2, 8–10s |
| **Shadow Bargain**       | Item / Health          | Sacrifice HP + rare shadow item       | AoE invisibility, shadow clone attacks                            | Shadow+Trickery → stealth and AoE enhancement             | Dark mist dome, shadow clone armorstands                          | Tier 3, 12s     |
| **Elemental Offering**   | Resource / Multi-Soul  | Environmental resources pooled        | Spawn temporary elemental AoE field (Fire, Water, Ice, Lightning) | Multi-soul: radius scales, visual intensity ↑             | Elemental particle arcs, block displays of terrain effect         | Tier 2, 10–12s  |

---

## **Key Implementation Notes**

1. **Activation & Visual Feedback**

   * Contract activation shows **sigil under player**, pulsing according to tier.
   * Particle arcs, spirals, domes indicate effect type and radius.
   * Multi-soul contracts: multiple sigils combine with color-coded overlays.

2. **Scaling Mechanics**

   * **Effect strength = Base × (Sacrifice Amount / MaxSacrifice) × # Nearby Resonant Souls**
   * Radius, duration, damage, and particle density scale proportionally.

3. **Resonance Integration**

   * Certain contracts **enhance existing resonances**.
   * Example: Infernal Contract + Fire+Wind resonance → Fire Tornado radius +50%, damage +20%.
   * Example: Harmony Convergence + multi-soul resonance → AoE buffs doubled.

4. **Tier System**

   * **Tier 1**: Minor effect, short duration, single-player or minor buff.
   * **Tier 2**: Regional effect, moderate duration, multi-player scaling.
   * **Tier 3**: Global or cataclysmic effect, long duration, multi-soul fusion, visually spectacular.

5. **Particles & Display**

   * Tier 1: small sigil + subtle particle arcs.
   * Tier 2: mid-sized domes, swirling particles, floating icons.
   * Tier 3: massive multi-layered domes, dynamic particle spirals, block_display bosses or terrain effects.

---

This blueprint covers:

* **All contract types**: health, item, soul, resource, time/action, multi-soul.
* **Sacrifice → Gain mapping** with scaling and resonance modifiers.
* **Visual implementation** ideas using particles, sigils, and block displays.
* **Tiered scaling** for gameplay balance and spectacle.

]