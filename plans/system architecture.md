Perfect — let’s design a **full SMP System Architecture Blueprint** that integrates **souls, abilities, resonances, contracts, events, and mythics** into a coherent, modular, and scalable framework for your plugin. This will serve as a **complete developer roadmap**.

---

# 🌌 SMP System Architecture Blueprint

## **1. Core Modules**

### **A. Player Data Module**

* Stores **per-player information**:

  * Active soul(s) & subclass choices
  * Soul mastery levels (Passive, Tactical, Movement, Ultimate, Mastery/Reform, Soulbound Weapon, Final Stand)
  * Active contracts & contract states
  * Equipped mythics & soulbound items
  * Resonance alignment states
  * Cooldowns & transformations
* **Data structure example (pseudo):**

```json
PlayerData {
  UUID,
  Souls: [SoulID, SubclassID, MasteryLevel],
  ActiveContracts: [ContractID, RemainingDuration],
  EquippedMythics: [MythicID],
  ActiveResonances: [ResonanceID],
  AbilityCooldowns: {AbilityID: TimeRemaining},
  TransformState: {TransformationID, DurationRemaining}
}
```

---

### **B. Soul Module**

* Handles **all soul abilities**:

  * Passive, Tactical, Movement, Ultimate, Mastery/Reform, Soulbound Weapon, Final Stand
  * Ability selection and subclass branching
  * Ability scaling based on player stats, level, and nearby resonances
* **Mechanics:**

  * Ability activation → triggers **particle/display effects**, damage calculations, or movement modifiers.
  * Can be **modified by Mythics, Contracts, or Resonances**.

---

### **C. Resonance Module**

* Tracks **multi-soul synergies and joint effects**:

  * Detects nearby compatible souls → triggers **resonance bonuses**
  * Modifies ability parameters (damage, radius, duration, AoE patterns)
* **Event interaction:**

  * Resonances can **enhance world events, contracts, and mythic effects**.
* **Example:** Fire + Wind resonance → Fire Tornado radius +50%, particle intensity ↑

---

### **D. Contract Module**

* Handles **contract creation, activation, and resolution**:

  * Validates sacrifices (HP, items, souls, resources, time)
  * Calculates effect strength:

    ```
    Effect = BaseEffect * (SacrificeAmount / MaxSacrifice) * ResonanceMultiplier
    ```
  * Activates **particles & block_display sigils**
  * Supports **multi-player pooled contracts**
* Contracts can **trigger temporary transformations, AoE boosts, or world events**.

---

### **E. Event Module**

* Manages **world, soul-specific, multi-soul, and rare events**:

  * Tracks **trigger conditions**: biome, time, weather, nearby souls, contracts, mythics
  * Schedules events using tick-based or timer systems
  * Handles **particle effects, block_display entities, AoE calculations, terrain modification**
* Example Flow:

  ```
  EventTriggerCheck() → ConditionMet? → ActivateEvent() → Particle/Display → EffectCalculation → DurationEndCleanup()
  ```

---

### **F. Mythic Module**

* Handles **Mythic item effects**, transformations, and world-altering powers:

  * **Passive & Active abilities**
  * Scales with **nearby souls, resonances, events**
  * Generates **visual spectacle**: particles, armorstands, block_display overlays
  * Can **interact with contracts or trigger rare events**
* Mythics are **tiered (Tier 2 & 3)** for scalability.

---

## **2. Ability & Effect Engine**

* **Particle System:**

  * Generate arcs, spirals, domes, spheres, trails
  * Layered particle effects for **ultimate & resonance interactions**
* **Display Entities:**

  * `block_display` for terrain overlays, sigils, or boss models
  * Armorstands for temporary summons (shadow clones, elemental avatars)
* **Math/Mechanics:**

  * AoE = sphere/cone/vector calculations
  * Pull/push vectors for whirlpools, tornadoes
  * Damage & buff scaling: base × soul mastery × resonance × mythic modifier × contract multiplier

---

## **3. Scheduler / Tick Flow**

* **Core tick system:** Handles timing for:

  * Ability cooldowns
  * Contract durations
  * Resonance activations
  * Event scheduling
  * Mythic active durations
* **Event priority system:**

  * Player-triggered > Contract-triggered > Soul/Resonance > World events

---

## **4. Interaction Pipeline**

1. **Player Action** → triggers **ability / contract / mythic**
2. **Check Resonances** → modify parameters
3. **Check Event Interactions** → amplify AoE, trigger additional effects
4. **Particle & Display Layer** → visual feedback
5. **Effect Calculation Layer** → damage, buffs, debuffs, world alterations
6. **Cooldown / Duration Update** → tracked in tick scheduler
7. **Cleanup** → remove temporary entities, particle trails, AoE zones

---

## **5. Data Flow Diagram (Textual Overview)**

```
[PlayerData] → AbilityModule → EffectEngine → World
                   ↓
               ResonanceModule
                   ↓
               ContractModule
                   ↓
               EventModule
                   ↓
               MythicModule
                   ↓
            Particle/Display System
                   ↓
           AoE/World Effect Application
```

* Each module **reads & writes** to `PlayerData` and **interacts** with the Effect Engine and Visual System.
* Multi-soul interactions and resonances **feed back** into events, contracts, and mythics dynamically.

---

## **6. Modular Plugin Architecture**

* **Core API / Manager Classes**:

  * `SoulManager` → tracks souls, mastery, abilities
  * `ResonanceManager` → calculates active resonances & bonuses
  * `ContractManager` → validates, executes, and scales contracts
  * `EventManager` → triggers & schedules events, handles AoE/effects
  * `MythicManager` → manages mythic item activation & scaling
  * `VisualManager` → particles, block_display, armorstand models
  * `CooldownScheduler` → tick-based timing, updates durations

* **Data Storage:**

  * Use **YAML/JSON per player** for persistence or SQLite for high-scale servers
  * Optional: **Redis cache** for tick-intensive operations

---

## **7. Key Takeaways**

* This architecture allows:

  * **Dynamic synergy** between souls, resonances, contracts, events, and mythics
  * **Scalable AoE and world-altering effects**
  * **Flexible multi-player interactions**
  * **Ultra-visual spectacle** via particle/display systems
* Designed for **modularity**, so new souls, mythics, contracts, or events can be added without core rewrites.

---

If you want, the **next step** could be me creating a **visual map / infographic** showing **all modules and their interactions**, with **arrows for data flow, effect layers, and resonance amplification paths** — essentially a **developer-ready SMP plugin diagram**.

Do you want me to do that next?
