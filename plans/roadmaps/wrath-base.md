# Wrath Base Implementation Roadmap

_Status: Draft • Created 2025-09-30_

This roadmap exists for Copilot to self-track Wrath base implementation progress without retaining excessive conversation context. Update checkboxes as work completes, cite commits, and keep references in sync.

---

## Phase 0 · Baseline & Tooling Readiness

- [ ] **Confirm local test server readiness**  \
  ↳ `server/README.md`, `server/start.sh`, `server/plugins/`
  - [ ] Verify Paper `paper-1.21.6-48.jar` present and runnable via `./start.sh`
  - [ ] Ensure `eula.txt` contains `true`; refresh timestamp if jar changes
- [ ] **Review specification sources**  \
  ↳ `plans/ability-overveiw.md`, `plans/spec/souls/wrath/base/spec.md`
  - [ ] Highlight balancing TODOs or notes directly in spec margin comments
- [ ] **Refresh registry tooling awareness**  \
  ↳ `scripts/convert_specs.py`, `scripts/validate_registry.py`, `plans/registry/**`
  - [ ] Decide whether Wrath registry entries need “implemented” annotations; document decision in `plans/registry/conflicts.md`
- [ ] **Build pipeline smoke test**  \
  ↳ `plugin/build.gradle.kts`, Gradle wrapper
  - [ ] Run `./gradlew clean build`
  - [ ] Note Java version availability (21) flag for README update later

## Phase 1 · Wrath Base Implementation

### 1A · Player Opt-In & State Tracking

- [ ] **Heat lifecycle**  \
  ↳ `WrathAbilityManager.java`, `WrathHeatManager.java`
  - [ ] Confirm join/remove lifecycle handling (login/logout + plugin disable)
  - [ ] Add optional debug logging toggle (future config placeholder)
- [ ] **Heat tick rules**  \
  ↳ Spec “Resource – Heat”
  - [ ] Tick cadence matches spec (decay 1/10s idle, +1/5s in combat)
  - [ ] Consider `/wrath heat <player>` for debugging (optional)
  - [ ] Document assumptions in `plans/system architecture.md` under “Resources”

### 1B · Passive: Burning Spirit

- [ ] **Damage + heat bonus implementation**  \
  ↳ `WrathListener.java`, spec “Burning Spirit”
  - [ ] Threshold: ≤ 60% max HP; +15% outgoing damage snapshot
  - [ ] Heat gain +1 per enemy damaged while passive active
  - [ ] Visual flame aura (particle/sound) tied to configurable toggle
- [ ] **Validation**  \
  - [ ] Manual duel test on Paper server; record outcome in `plans/logs/wrath-testing.md`

### 1C · Tactical: Ignition

- [ ] **Ability flow**  \
  ↳ `WrathCommand.java`, `WrathIgnitionManager.java`, spec “Ignition”
  - [ ] Ensure unique enemy hit ⇒ +2 heat (0.5s ICD)
  - [ ] After ≥5 hits purge 10 heat with explosion FX
  - [ ] Fire duration extension (+2s/hit) and cooldown 16s respected
- [ ] **Testing**  \
  - [ ] Add MockBukkit test harness stub (future) covering unique-hit logic
  - [ ] Manual validation vs multi-mob encounter; log in testing doc

### 1D · Movement: Inferno Chain Surge

- [ ] **Implementation**  \
  ↳ New `WrathChainManager.java`, spec “Inferno Chain Surge”
  - [ ] Chain attachment up to 5 blocks; launch 3 blocks past point
  - [ ] Tether deals 1 HP/s + 20% slow for 3s; recast detonation window 2s
  - [ ] Detonation deals 6 HP fire (cap 6 HP per target)
  - [ ] Heat +5 on first enemy hit during surge
- [ ] **Polish**  \
  - [ ] Particle trail + sound design
  - [ ] Fail-safe for invalid targets/attachments
- [ ] **Testing**  \
  - [ ] Arena scenario to stress mobility + tether; log tuning notes

### 1E · Ultimate: Meteorfall

- [ ] **Functionality**  \
  ↳ New `WrathMeteorManager.java`, spec “Meteorfall”
  - [ ] Schedule 5 meteors over 3s (Bukkit scheduler)
  - [ ] Damage: 6 HP within 2-block radius, max 18 HP per target, max 2 center hits
  - [ ] Ground ignition for 3s; respect block safety
  - [ ] Heat clears 40% (rounded down to nearest 5)
- [ ] **Performance**  \
  - [ ] Validate no async hazards; main thread execution only
  - [ ] Stress-test with multiple players; log metrics

### 1F · Config & Messaging

- [ ] **Config plumbing**  \
  ↳ New `config.yml`, updates to `SoulSMPPlugin`
  - [ ] Centralize ability tuning constants
  - [ ] Add reload support stub (future)
- [ ] **Localization**  \
  ↳ New `messages.properties`
  - [ ] Replace hard-coded color strings
  - [ ] Document message keys in `plans/system architecture.md`

## Phase 2 · Quality Assurance & Automation

- [ ] **Unit tests**  \
  ↳ Add JUnit + MockBukkit to Gradle build
  - [ ] Heat tick behavior coverage
  - [ ] Ignition unique hit + purge logic coverage
- [ ] **Manual test checklist**  \
  ↳ `plans/logs/wrath-testing.md`
  - [ ] Scenarios: low HP damage buff, ignition purge, chain surge, meteor AoE
  - [ ] Track pass/fail per build snapshot
- [ ] **Performance profiling**  \
  ↳ Create `scripts/profile_wrath.md`
  - [ ] Use `/timings` or Spark (if installed) to confirm no major lag
  - [ ] Record findings + next steps

## Phase 3 · Documentation & Release Prep

- [ ] **Player-facing guide**  \
  ↳ New `plans/player-guides/wrath-base.md`
  - [ ] Summaries aligned with spec; no conflicting numbers
- [ ] **Developer docs**  \
  ↳ `plans/system architecture.md`
  - [ ] Add “Wrath Module” section with class diagram + data flow
- [ ] **Packaging & README**  \
  ↳ `plugin/build.gradle.kts`, repo README (future)
  - [ ] Confirm Shadow JAR contains `plugin.yml`
  - [ ] Document build instructions + Java version requirements
- [ ] **Release criteria**  \
  ↳ New `CHANGELOG.md`
  - [ ] Establish semantic version (0.1.x base, 0.2.x paths)
  - [ ] Draft release notes template

## Phase 4 · Future Enhancements & Path Prep

- [ ] **Path foundations**  \
  ↳ `plans/spec/souls/wrath/ash/spec.md`, `/blood/spec.md`
  - [ ] Identify shared hooks needed (weapon infusion, lifesteal APIs)
  - [ ] Log TODOs in `plans/roadmaps/wrath-paths.md` (to be created)
- [ ] **Resource sharing infrastructure**  \
  - [ ] Evaluate abstracting heat manager for other souls; design notes in architecture doc
- [ ] **Metrics & telemetry (optional)**  \
  - [ ] Plan analytics hooks (damage dealt, heat usage) with privacy considerations

---

## Quality Gates (Run Per Milestone)

- Build: `./gradlew clean build`
- Tests: `./gradlew test` (once harness exists)
- Static analysis (future): Spotless/Checkstyle
- Server smoke: copy jar to `server/plugins`, execute `./start.sh`
- Documentation parity: ensure spec ⇄ code ⇄ guides alignment; note in test log
- Issue tracking: log blockers in `plans/registry/conflicts.md`

---

## Progress Log

Format entries as: `YYYY-MM-DD · [x] Task (commit abc123)`.

- _No entries yet._
