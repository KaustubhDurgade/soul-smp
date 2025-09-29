Perfect! Here’s a **full SMP Mythics Catalog**, formatted like the Contracts blueprint, giving you a **ready-to-implement reference** for your plugin. I included **name, type, active/passive effect, resonance interactions, particle/display ideas, and tier/duration**.

---

# 🌌 SMP Mythics Catalog

| Mythic Name               | Type                   | Active / Passive Effect                                                                   | Soul Interaction / Resonance Modifier                               | Particle / Display Ideas                                     | Tier / Duration                      |
| ------------------------- | ---------------------- | ----------------------------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------ |
| **Eclipse Blade**         | Weapon                 | Active: AoE slash, doubles Shadow/Silence ability effects; Passive: +15% damage           | Enhances stealth & clone attacks; Shadow+Trickery resonance ↑       | Dark arcs, shadow streaks, pulsing sigil under player        | Tier 3, Active 8–10s, Passive always |
| **Tempest Halberd**       | Weapon                 | Active: Lightning cleave, chain attacks; Passive: +Wind and Tides ability damage          | Amplifies Wind+Tides resonances; can trigger Fire+Wind auto synergy | Electric arcs, storm clouds, crackling air particles         | Tier 3, Active 10s, Passive always   |
| **Infernal Cannon**       | Weapon                 | Active: Fire AoE projectile, explosive; Passive: Flame abilities burn longer              | Fire+Wind resonance automatically triggers Fire Tornado on hit      | Fire explosions, smoke spirals, glowing ember particles      | Tier 3, Active 12s, Passive always   |
| **Chronos Hourglass**     | Artifact               | Active: Reduce cooldowns of all nearby allies 50%; Slow enemies in radius                 | Boosts passive and tactical abilities of nearby souls               | Floating golden hourglass, spinning particle rings           | Tier 2, Active 8–12s                 |
| **Soul Prism**            | Artifact               | Passive: Doubles duration and strength of all passive/tactical abilities within 10 blocks | Amplifies all nearby resonances                                     | Orbiting colorful prisms, swirling rainbow particle halo     | Tier 2, Passive always               |
| **Elemental Orb**         | Artifact               | Active: Store and release amplified elemental AoE (Fire, Frost, Tides, Lightning)         | Compatible with elemental resonances                                | Floating orb, elemental arcs, sphere of particles            | Tier 3, Active 10–15s                |
| **Aegis of the Ancients** | Armor                  | Passive: Reflect projectiles; Amplifies defensive resonances                              | Boosts AoE defensive and shield abilities                           | Golden shield arcs, protective particle dome                 | Tier 2, Passive always               |
| **Phantom Mantle**        | Armor                  | Passive: Invisibility on ultimate; Shadow+Trickery resonance amplified                    | Enhances stealth and clone attack synergy                           | Ethereal cloak particles, dark mist trailing player          | Tier 3, Passive always               |
| **Dragon’s Heart**        | Transformation         | Active: Fire dragon form; AoE flame breath; Aerial mobility                               | Flame+Wind resonance enhanced                                       | Flaming wings, smoke trails, fiery aura                      | Tier 3, Active 12s–15s               |
| **Leviathan Core**        | Transformation         | Active: Water form; Pulls enemies; Tidal AoE control                                      | Strengthens Tides abilities; Electrified Whirlpool synergy          | Water spiral particles, prismarine tentacles, splash effects | Tier 3, Active 12s–15s               |
| **Void Mantle**           | Transformation         | Active: Shadow form; Teleportation; Clone attacks; Stealth                                | Shadow+Trickery and Silence resonances amplified                    | Dark particle wrap, floating shadow armorstands              | Tier 3, Active 10s                   |
| **Eclipse Totem**         | World / Artifact       | Active: Darken area; Amplify Shadow/Silence; Reduce enemy healing                         | Boosts nearby shadow resonances                                     | Shadow dome, floating dark runes, mist particles             | Tier 3, Active 15s                   |
| **Meteoric Scepter**      | World / Artifact       | Active: Calls down meteor storm; terrain damage                                           | Fire+Wind resonance triggers automatically                          | Falling meteors, impact sparks, smoke columns                | Tier 3, Active 12–15s                |
| **Celestial Compass**     | World / Artifact       | Active: Converts biome temporarily to elemental-aligned field                             | Amplifies elemental abilities in area                               | Floating compass display, glowing elemental aura             | Tier 2, Active 20s                   |
| **Aurora Crown**          | World / Transformation | Passive: Increase movement speed, ultimate gain; Active: Summon aurora field for buffs    | Enhances multi-soul resonances                                      | Floating crown particle halo, rainbow particle arcs          | Tier 2, Active 8s, Passive always    |
| **Oblivion Shard**        | Artifact               | Active: AoE silence and damage; Passive: +Shadow and Silence ability power                | Multi-soul synergy with Shadow+Silence                              | Black shards orbiting player, void mist dome                 | Tier 3, Active 10s, Passive +15%     |

---

## **Implementation Notes**

1. **Activation / Passive**

   * Weapons & Transformations: right-click / keybind for active; passive always applies while equipped.
   * Artifacts & Armor: passive effects continuous; active abilities can have cooldowns.
   * World Mythics: triggered by event or activation, visually dominates the area.

2. **Particle & Display**

   * **Weapons:** streaks, arcs, AoE bursts
   * **Artifacts:** floating orbs, sigils, rotating runes
   * **Transformations:** full-body particle wrap, elemental aura, armorstand projections
   * **World-effects:** domes, meteor strikes, elemental storms, terrain overlays

3. **Scaling / Resonances**

   * Effects scale with:

     * Nearby players / number of souls
     * Active resonances or contracts
     * Player level or mythic rarity tier
   * Example: Dragon’s Heart AoE damage = Base × (1 + 0.2 × # Flame+Wind souls nearby)

4. **Tiering**

   * **Tier 2:** Strong, regional effects, moderate duration, enhances multiple abilities/resonances
   * **Tier 3:** Cataclysmic, massive area, long duration, visually spectacular, multi-soul or world-altering

---

This catalog gives you a **plug-and-play list of Mythics** with:

* **Name & type**
* **Effects** (active/passive)
* **Resonance interactions**
* **Visual / particle concepts**
* **Tier / duration**

It’s ready to be **integrated into your SMP plugin framework**, interacting with **souls, contracts, and events**.

---

If you want, the **next step** could be me **designing a unified SMP system architecture**, showing **how all 13 souls, abilities, resonances, contracts, events, and mythics interact dynamically**, with **data flow, tick scheduling, and modular plugin structure**.

Do you want me to do that next?
