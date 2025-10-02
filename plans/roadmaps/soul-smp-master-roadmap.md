# Soul SMP Plugin Master Roadmap

_Status: Draft • Created 2025-09-30_

> **Purpose**: Deliver an end-to-end execution guide for building the entire Soul SMP plugin suite. This document is intentionally hyper-detailed and references every known specification, registry, script, and runtime system. Treat each checkbox as a trackable unit of work. When a task completes, flip `[ ]` → `[x]`, append a date/initials, and cite the commit.

---

## Phase 0 · Repository Hygiene & Tooling Foundation


  - [x] Validate `.gitignore` covers `/server/world`, `/server/logs`, `/plugin/build`, `/plugin/.gradle`, `/server/plugins/SoulSMP.jar`. _(2025-10-01 · uncommitted)_
  - [x] **Repo audit + Git hygiene** _(2025-10-01 · uncommitted)_
  - [x] Add `.editorconfig` aligning with Java + Markdown style if missing. _(2025-10-01 · uncommitted)_
  - [x] Ensure `README.md` at repo root describes project structure. _(2025-10-01 · uncommitted)_
  - [x] **Task runner standardization** _(2025-10-01 · uncommitted)_
  - [x] Confirm Gradle wrapper version (`gradle-wrapper.properties`) is current (>=8.7). _(2025-10-01 · uncommitted)_
  - [x] Add convenience scripts: `scripts/build_plugin.sh`, `scripts/deploy_plugin.sh` (copy jar to Paper server). _(2025-10-01 · uncommitted)_
  - [x] **CI/CD scaffolding** _(2025-10-01 · uncommitted)_
  - [x] Introduce GitHub Action workflow `/.github/workflows/build.yml` (Java 21, cache Gradle, run build + tests). _(2025-10-01 · uncommitted)_
  - [x] Add badge to README once pipeline operational. _(2025-10-01 · uncommitted)_
  - [x] **Documentation index** _(2025-10-01 · uncommitted)_
  - [x] Create `plans/README.md` summarizing planning docs, referencing registries and specs. _(2025-10-01 · uncommitted)_
  - [x] Link the master roadmap and Wrath roadmap from that index. _(2025-10-01 · uncommitted)_

---

## Phase 1 · Data & Specification Pipeline

- [x] **Registry consolidation** _(2025-10-01 · uncommitted)_
  - [x] Review `plans/registry/*.yml`, ensure consistent schema keys (id, name, status, notes). _(2025-10-01 · uncommitted)_
  - [x] Add `status` field for implementation state (planned/in-progress/done/deprecated). _(2025-10-01 · enforced via converter)_
  - [x] Run `scripts/validate_registry.py` and document output in `plans/logs/registry-validation.md`. _(2025-10-01 · see log)_
- [x] **Spec conversion tooling**
  - [x] Extend `scripts/convert_specs.py` to emit JSON/Markdown summary per path for in-game tooltips. _(2025-10-01 · uncommitted)_
  - [x] Create unit tests for scripts using `pytest` (add `requirements.txt` or `pyproject.toml`). _(2025-10-01 · uncommitted)_
  - [x] Automate spec → registry sync job (optional) and log instructions in `scripts/README.md`. _(2025-10-01 · uncommitted)_
- [x] **Knowledge base** _(2025-10-01 · uncommitted)_
  - [x] Aggregate cross-cutting notes into `plans/system architecture.md` (add sections for resources, cooldowns, effects, integration points). _(2025-10-01 · uncommitted)_
  - [x] Ensure each soul/path spec under `/plans/spec/souls/**` has front-matter metadata (id, resource, ability names, difficulty). _(2025-10-01 · scripted sweep)_

---

## Phase 2 · Core Runtime & Services

- [x] **Plugin bootstrap** _(2025-10-01 · uncommitted)_
  - [x] Flesh out `SoulSMPPlugin.java` with subsystem registration order, config loading, metrics hooks. _(2025-10-01 · uncommitted)_
  - [x] Introduce dependency injection container (simple service registry) for managers. _(2025-10-01 · uncommitted)_
  - [x] Implement graceful disable sequence (save state, unregister listeners, cancel tasks). _(2025-10-01 · uncommitted)_
- [x] **Configuration system** _(2025-10-01 · uncommitted)_
  - [x] Create `plugin/src/main/resources/config.yml` default values covering global tuning, path toggles, debugging levels. _(2025-10-01 · uncommitted)_
  - [x] Implement `ConfigManager` handling reloads (`/souls reload`). _(2025-10-01 · uncommitted)_
  - [x] Document config keys in `docs/configuration.md`. _(2025-10-01 · uncommitted)_
- [x] **Messaging & localization** _(2025-10-01 · uncommitted)_
  - [x] Add `messages.properties` (and optional locale-specific variants). _(2025-10-01 · uncommitted)_
  - [x] Implement `MessageService` with prefix, color, placeholder interpolation. _(2025-10-01 · uncommitted)_
  - [x] Update all commands/listeners to use message service. _(2025-10-01 · uncommitted)_
- [x] **Scheduler utilities** _(2025-10-01 · uncommitted)_
  - [x] Create `TaskScheduler` wrapper with tracking (for cancel-on-disable, repeating tasks, async validations where safe). _(2025-10-01 · uncommitted)_
- [x] **Player profile & persistence** _(2025-10-01 · uncommitted)_
  - [x] Design `PlayerProfile` object storing soul choice, path, progress, unlocked perks. _(2025-10-01 · uncommitted)_
  - [x] Choose persistence layer (YAML per player, SQLite, or database) and implement repository. _(2025-10-01 · uncommitted)_
  - [x] Add migration strategy if schema evolves. _(2025-10-01 · uncommitted)_
- [x] **Combat event pipeline** _(2025-10-01 · uncommitted)_
  - [x] Centralize damage/heal events to allow souls to hook consistently. _(2025-10-01 · uncommitted)_
  - [x] Add throttling/priority ordering to avoid conflicts between souls. _(2025-10-01 · uncommitted)_
- [x] **Effect & status engine** _(2025-10-01 · uncommitted)_
  - [x] Implement reusable effect classes (burn, bleed, stun, etc.) with registration. _(2025-10-01 · uncommitted)_
  - [x] Provide API for stacking, refreshing, and visual cues. _(2025-10-01 · uncommitted)_
- [x] **Resource engine** _(2025-10-01 · uncommitted)_
  - [x] Generalize Heat manager concept for other souls (e.g., Harmony, Wealth) via `ResourceManager` interface. _(2025-10-01 · uncommitted)_
  - [x] Add tick bus for resource updates with per-resource cadence definitions. _(2025-10-01 · uncommitted)_
- [x] **Cooldown manager** _(2025-10-01 · uncommitted)_
  - [x] Implement central cooldown tracking keyed by ability IDs; allow dynamic adjustments via buffs/debuffs. _(2025-10-01 · uncommitted)_
- [x] **Logging & analytics** _(2025-10-01 · uncommitted)_
  - [x] Create `TelemetryService` capturing ability usage, damage, healing, resource peaks; respect privacy config toggles. _(2025-10-01 · uncommitted)_
  - [x] Output summary logs or integrate with external analytics if desired. _(2025-10-01 · uncommitted)_

---

## Phase 3 · Command & UI Layer

- [x] **Global command suite** _(2025-10-02 · uncommitted)_
  - [x] `/souls` root command with subcommands: list, info, select, respec, admin override.
  - [x] Implement tab completion using context-aware suggestions.
- [x] **Admin tools** _(2025-10-02 · uncommitted)_
  - [x] `/souls admin grant <player> <soul/path>` for testing.
  - [x] `/souls admin resource <player> <resource> <set/add>`.
  - [x] `/souls admin cooldown <player> <ability> reset`.
- [x] **Debugging utilities** _(2025-10-02 · uncommitted)_
  - [x] `/souls debug events` toggle to print event flow to player chat/console.
  - [x] `/souls metrics export` to dump telemetry snapshot.
- [x] **UI feedback** _(2025-10-02 · uncommitted)_
  - [x] BossBar/ActionBar displays for current resource, cooldowns.
  - [x] Particle libraries and sound constants centralized per ability.
- [ ] **In-game tutorials**
  - [ ] Implement book/GUI (e.g., Adventure API) to guide new players through souls.
  - [ ] Link content to specs for accuracy.

---

## Phase 4 · Base Souls Implementation (13 Souls)

> Each soul has Base kit + 2 Paths. Base implementation includes Passive, Tactical, Movement, Ultimate, Resource behavior. Reference `plans/spec/souls/<soul>/base/spec.md` and update roadmap checkboxes per soul. Create per-soul sub-roadmaps (Wrath already exists).

### 4.1 Wrath (Base)
- [x] Follow `plans/roadmaps/wrath-base.md` tasks; sync status here when each sub-section completes. _(2025-10-02 · uncommitted)_
- [x] Implement Resource: Heat with tick bus integration. _(2025-10-02)_
- [x] Passive: Burning Spirit (damage bonus at low HP + heat generation). _(2025-10-02)_
- [x] Tactical: Ignition (weapon flame coating with heat mechanics). _(2025-10-02)_
- [x] Movement: Inferno Chain Surge (chain mobility + tether + detonation). _(2025-10-02)_
- [x] Ultimate: Meteorfall (5 meteors with AoE damage + ground fire). _(2025-10-02)_
- [ ] Manual testing and balance validation.
- [ ] Update `plans/registry/souls.yml` entry status to `implemented` post-verification.

### 4.2 Serenity (Base)
- [ ] Implement Resource: Harmony (with Resonance hook).
- [ ] Abilities: Still Waters, Soothing Breeze, Tranquil Veil Step, Sanctuary.
- [ ] Integrate cleanse logic with global effect engine.
- [ ] Document synergy notes in `plans/spec/souls/serenity/base/spec.md` if adjustments made.

### 4.3 Greed (Base)
- [ ] Resource: Wealth with subresources Gilded Charge & Appetite.
- [ ] Abilities: Golden Hunger (loot modifier), Drain Touch, Market Shift, Pillage.
- [ ] Implement economy interface for coin tracking; respect server economy plugin compatibility.

### 4.4 Pride (Base)
- [ ] Resource: Renown, Isolation.
- [ ] Abilities: Commanding Presence (ally aura), Challenge (duel mark), Imperial Advance, Crown of Dominion.
- [ ] Design ally detection and buff stacking rules.

### 4.5 Despair (Base)
- [ ] Resources: Decay Index, Blight, Terror.
- [ ] Abilities: Withering Touch, Decay Bolt, Umbral Corridor, Plague Field.
- [ ] Ensure vision/withering effects utilize central effect engine.

### 4.6 Hope (Base)
- [ ] Resources: Light Reservoir, Martyr Gauge.
- [ ] Abilities: Guiding Light, Radiant Beam, Solar Arc Glide, Beacon.
- [ ] Implement martyr HP sacrifice logic with safety checks.

### 4.7 Chaos (Base)
- [ ] Randomization engine for Chaotic Aura, Unstable Burst, Fracture Warp, Riftstorm.
- [ ] Logging for rolled outcomes; ensure fairness constraints documented.

### 4.8 Order (Base)
- [ ] Resource: Balance, Temporal.
- [ ] Abilities: Balanced Flow (CDR/damage trade), Binding Chains, Axiom Phase March, Edict.
- [ ] Interactions with other souls’ abilities (cancellation field) thoroughly tested.

### 4.9 Glory (Base)
- [ ] Abilities: Hero’s Spark, Rallying Cry, Legacy Vault, Immortal Banner.
- [ ] Implement persistent structures (banners) with cleanup.

### 4.10 Curiosity (Base)
- [ ] Abilities: Keen Eye, Analyze, Insight Pivot, Arcane Experiment.
- [ ] Possibly integrate item inspection UI; ensure data privacy.

### 4.11 Trickery (Base)
- [ ] Abilities: False Image, Smoke Trick, Quickstep, Grand Illusion.
- [ ] Clone/decoy system with AI behavior.

### 4.12 Nature (Base)
- [ ] Abilities: Verdant Blessing, Vine Snare, Vine Whip, Nature’s Wrath.
- [ ] Environmental growth and storm effects tuned to prevent griefing.

### 4.13 Silence (Base)
- [ ] Resources: Soul Meter, Null Saturation.
- [ ] Abilities: Quiet Aura, Null Strike, Silent Step, Silence Field.
- [ ] Ability cancellation integration with core event bus.

For each soul:
- [ ] Implement dedicated manager class, register listeners/commands.
- [ ] Configure resources, cooldowns, visual fx.
- [ ] Add tests + QA entries.
- [ ] Update documentation and registry status.

---

## Phase 5 · Path Implementations (26 Paths)

> Each path replaces the base kit; ensure modular architecture supports swapping ability sets. Create dedicated sub-roadmaps per path family under `plans/roadmaps/` (e.g., `wrath-ash.md`, `wrath-blood.md`).

- [ ] **Wrath Paths**
  - [ ] Ash – Implement volcanic burst, meteor amplification, explosive mobility. Reference `/plans/spec/souls/wrath/ash/spec.md`.
  - [ ] Blood – Implement vampirism loops, berserk windows, lifesteal scaling. Spec at `/plans/spec/souls/wrath/blood/spec.md`.
- [ ] **Serenity Paths**: Renewal, Reflection.
- [ ] **Greed Paths**: Gold, Gluttony.
- [ ] **Pride Paths**: Authority, Solitude.
- [ ] **Despair Paths**: Rot, Night.
- [ ] **Hope Paths**: Radiance, Flame.
- [ ] **Chaos Paths**: Discord, Void.
- [ ] **Order Paths**: Chains, Clockwork.
- [ ] **Glory Paths**: Eternal Flame, Champion.
- [ ] **Curiosity Paths**: Discovery, Madness.
- [ ] **Trickery Paths**: Masks, Misfortune.
- [ ] **Nature Paths**: Thorns, Beasts.
- [ ] **Silence Paths**: Reaper, Emptiness.

For each path:
- [ ] Create ability set manager, path-specific weapon/final-stand, resource modifications.
- [ ] Implement unlock quests or requirements if specified.
- [ ] Update player progression system to handle reforms.
- [ ] Add QA checklist entries, tests, documentation updates.

---

## Phase 6 · Progression & Meta Systems

- [ ] **Soul Acquisition Flow**
  - [ ] Implement leveling hooks (souls 1–4 unlocking base kits).
  - [ ] Add reform milestone at level 5; ensure UI communicates choices.
  - [ ] Track unlock state in `PlayerProfile`.
- [ ] **Contracts & Events integration**
  - [ ] Utilize `plans/registry/contracts.yml` to create rotating missions affecting abilities/resources.
  - [ ] Implement event scheduler referencing `plans/registry/events.yml`.
- [ ] **Items & Mythics**
  - [ ] Reference `plans/registry/items.yml` and `mythics.yml` to create custom items, crafting recipes.
  - [ ] Integrate with MythicMobs or custom entity logic if needed.
- [ ] **Resonances & Shared Mechanics**
  - [ ] Implement resonance system per `plans/registry/resonances.yml` (cross-soul synergies, team buffs).
- [ ] **Final Stand & Soulbound Weapons**
  - [ ] For each path, implement final stand ability + weapon behavior.
  - [ ] Manage durability, persistence, re-summoning.
- [ ] **Achievements & Titles**
  - [ ] Design plugin-specific advancement system; integrate with scoreboard achievements.

---

## Phase 7 · Testing, Balancing, and Live Ops

- [ ] **Automated testing framework**
  - [ ] Add MockBukkit-based integration tests for key ability flows.
  - [ ] Introduce parameterized tests for resource thresholds.
- [ ] **Manual QA matrix**
  - [ ] Create `plans/logs/testing-matrix.xlsx` or `.md` with environment details (paper build, players, mods).
  - [ ] Run scenario-based testing per soul/path; log results.
- [ ] **Balance iteration loops**
  - [ ] Capture player feedback, adjust specs, update docs.
  - [ ] Version control spec changes with changelog entries.
- [ ] **Performance monitoring**
  - [ ] Integrate timings, Spark, or custom profilers to monitor heavy effects.
  - [ ] Document fixes for any identified hotspots.
- [ ] **Release management**
  - [ ] Maintain `CHANGELOG.md` with semantic version entries.
  - [ ] Tag releases, package jars, update documentation, announce changes.

---

## Phase 8 · Nice-to-Have Enhancements

- [ ] **API Exposure**
  - [ ] Provide developer API for other plugins to query soul data.
  - [ ] Document in `docs/api.md` with examples.
- [ ] **Web Dashboard**
  - [ ] Build optional web UI (e.g., Next.js) to display player soul stats.
  - [ ] Sync with telemetry data.
- [ ] **Modularization**
  - [ ] Consider multi-module Gradle project (core API, runtime, add-ons).
- [ ] **Localization**
  - [ ] Translate messages into additional languages per community needs.
- [ ] **Accessibility**
  - [ ] Provide settings for visual effects intensity, color blindness filters.

---

## Quality Gate Checklist (Per Release)

1. `./gradlew clean build`
2. `./gradlew test`
3. Static analysis (Spotless/Checkstyle) clean
4. Server smoke test (`./start.sh`, verify no errors on enable/disable)
5. Manual QA scenarios signed off
6. Documentation parity check (spec ↔ code ↔ player guide)
7. Changelog + version bump committed
8. Tag release, archive artifact in `/server/plugins` or distribution channel

---

## Progress Log Template

Record completed tasks here:
```
YYYY-MM-DD · [x] <Task Description> (commit <hash>) — notes/links
```

- 2025-10-01 · [x] Phase 0 repository hygiene + tooling groundwork (uncommitted) — Added .gitignore, .editorconfig, README structure overview, build/deploy scripts, CI workflow, planning index.
- 2025-10-01 · [x] Phase 2 core runtime services (uncommitted) — Introduced service registry, resource tick bus, combat pipeline, effect engine, telemetry/metrics, config docs, and Wrath subsystem rewiring.
- 2025-10-01 · [x] Core runtime scaffolding: config/messages/task scheduler/profile persistence/resource manager/cooldown manager (uncommitted)
