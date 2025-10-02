# Configuration Reference

This document explains every key in `config.yml` so server operators can confidently tune the Soul SMP plugin.

## `debug`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `debug.enabled` | boolean | `false` | Enables verbose logging to help trace resource changes and ability triggers during development. |

## `resources`

### `resources.heat`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `min` | int | `0` | The lowest legal value for the Heat resource. |
| `max` | int | `100` | The upper bound for Heat. |
| `starting` | int | `0` | Starting Heat for new player profiles. |
| `decay-interval-seconds` | int | `10` | How often (in seconds) passive Heat decay ticks fire via the resource tick bus. |
| `combat-tick-seconds` | int | `5` | How often (in seconds) combat ticks fire for Heat-specific logic. |

## `souls`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `allow-respec` | boolean | `true` | Allows players to respec their currently selected soul using `/souls respec`. |

### `souls.catalog[]`

Each entry describes a soul available for selection. At least one entry is required to let players choose a soul.

| Field | Type | Description |
| --- | --- | --- |
| `id` | string | Unique identifier used internally and by commands such as `/souls select`. |
| `name` | string | Display name surfaced in `/souls list`. |
| `description` | string | Flavor text shown in `/souls info`. |
| `difficulty` | string | Narrative difficulty label (e.g., `NORMAL`, `HARD`). |
| `paths` | string[] | Collection of soul path keys unlocked when the soul is selected. |

## `persistence`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `storage` | string (`yaml`) | `yaml` | Controls which persistence backend to use. YAML (JSON over YAML file) is currently implemented. |
| `autosave-interval-minutes` | int | `5` | Interval for scheduled autosaves of cached player profiles. |
| `schema-version` | int | `1` | Schema version applied to new player profiles. Used by the migration service to reconcile older data files. |

## `metrics`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `enabled` | boolean | `true` | Toggles metric reporting. When enabled, the metrics service logs periodic snapshots with player and resource counts. |
| `report-interval-minutes` | int | `5` | Minutes between metric reports. |

## `telemetry`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `enabled` | boolean | `true` | Turns telemetry counters on/off. Disable to avoid collecting runtime usage data. |
| `flush-interval-seconds` | int | `300` | Frequency (seconds) for flushing telemetry counters to the log. |

## `combat`

| Key | Type | Default | Description |
| --- | --- | --- | --- |
| `throttle-window-millis` | long | `200` | Minimum milliseconds between consecutive damage events from the same attacker to the same target processed by the combat pipeline. Prevents duplicate listeners from stacking. |

## Notes

- Changes to `config.yml` can be applied in-game via `/souls reload`.
- When adding new resources, remember to document them here to keep operators informed.
