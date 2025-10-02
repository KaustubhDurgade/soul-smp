---

# Soul SMP System Architecture Knowledge Base

This document captures the shared systems that every soul, path, contract, and registry entry relies on. Use it as the canonical cross-cutting reference while implementing or balancing gameplay features.

## 🔥 Resources

- **Runtime ownership**: `ResourceManager` + `ResourceTickBus` (see `plugin/src/main/java/dev/soulsmp/core/resource`). Resource definitions live in `config.yml` (`resources.*`) and registry YAML under `plans/registry/data/*`.
- **Tick cadence**: `ResourceTickBus` binds per-resource passive and combat handlers. Defaults come from spec front-matter (`resource` field) and schema docs in `plans/registry/schemas/`.
- **Heat baseline**: Wrath Heat tuning (min/max/decay) mirrors `config.yml` defaults; any new soul resource must register identical min/max fields before activating.
- **Decay and generation audit checklist**:
  - Specify gain/spend hooks in the spec front-matter summary before coding.
  - Configure decay intervals in the resource definition (avoid hard-coding inside soul managers).
  - Wire telemetry counters (`resource.*`) whenever a resource changes; this feeds the metrics snapshot.

## ⏱ Cooldowns

- **Central service**: `CooldownManager` ensures consistent tracking, modifier stacking, and persistence per player.
- **Spec alignment**: Cooldown values in specs should match registry YAML (`cooldown_seconds` or `active_window_seconds`). During implementation, read from registry rather than duplicating constants.
- **Scaling hooks**:
  - Balance changes go through `CooldownManager#modify` to keep UI displays accurate.
  - Items/resonances that alter cooldowns must log telemetry (`cooldown.adjusted`) for balancing.
- **UI integration**: BossBar/ActionBar roadmap tasks depend on `CooldownManager` publishing events; reserve listener slots to avoid duplicate displays.

## 🜂 Effects & Status

- **Engine**: `EffectManager` + `EffectType` provide stacking rules, amplification, and tick scheduling.
- **Registered defaults**: Burn, Bleed, and Stun live in `EffectTypes.registerDefaults`. New status effects should follow the builder pattern (stacking policy, max amplifier, applier callbacks).
- **Soul interactions**:
  - Specs must call out which statuses they apply; mirror that in front-matter `ability_names` and in registry notes.
  - Avoid direct Bukkit potion calls; route everything through `EffectManager` so expiration events fire correctly.
- **Visual feedback**: Pair each effect with particles or sounds defined in the spec notes to keep designers and engineers in sync.

## 🔗 Integration Points

- **Registry pipeline**: Markdown specs → `convert_specs.py` → `plans/registry/data` + tooltips. Always regenerate after spec edits to maintain schema guarantees (`notes`, `status`, `logging`).
- **Telemetry & Metrics**: `TelemetryService` counts key events (`combat.*`, `resource.*`), while `MetricsService` snapshots online players and resource usage. Document new counters in `plans/registry/telemetry-keys.json`.
- **Contracts & Events**: Reference IDs in registry files must match spec `id` fields and in-game handlers. Keep `plans/logs/registry-validation.md` updated after running validators.
- **Knowledge hand-off**: When implementing a soul/path, read:
  1. Spec front-matter (resource, ability names, difficulty baseline).
  2. Registry YAML/tooltips for runtime numbers.
  3. This knowledge base for shared services and telemetry requirements.

## 📚 Reference Map

| Area | Primary Sources | Runtime Entry Points |
| --- | --- | --- |
| Resources | Spec front-matter `resource`, `plans/registry/data/**/*yaml`, `config.yml` | `ResourceManager`, `ResourceTickBus` |
| Cooldowns | Spec ability blocks, registry timings | `CooldownManager`, action bar/boss bar listeners |
| Effects | Spec notes, `EffectTypes`, registry `effects` arrays | `EffectManager`, `EffectType` appliers |
| Integration | `convert_specs.py`, validation logs, telemetry schema | `TelemetryService`, `MetricsService`, contract/event handlers |

Keep this file in sync whenever shared services evolve or new cross-cutting rules emerge.

