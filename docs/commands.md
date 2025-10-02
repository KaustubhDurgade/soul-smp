# Command Reference

## `/souls`

Primary interface for players and administrators to inspect and manage soul assignments.

| Subcommand        | Usage                                 | Description                                                                                                      |
| ----------------- | ------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| `list`            | `/souls list`                         | Displays all souls currently registered in the catalog.                                                          |
| `info`            | `/souls info <id>`                    | Shows detailed information about the specified soul, including description, difficulty, and unlocked paths.      |
| `select`          | `/souls select <id>`                  | Assigns the specified soul to the executing player, if it is available and they meet any selection requirements. |
| `respec`          | `/souls respec`                       | Allows the executing player to clear their current soul when `souls.allow-respec` is enabled in `config.yml`.    |
| `admin reload`    | `/souls admin reload`                 | Reloads soul catalog definitions and other configurable subsystems from disk.                                    |
| `admin cooldowns` | `/souls admin cooldowns <reset/list>` | Inspects or resets active ability cooldowns for the executing player (or a targeted player when supported).      |
| `admin profile`   | `/souls admin profile <player>`       | Inspects a player's soul profile for debugging and support purposes.                                             |
| `debug`           | `/souls debug [on/off]`               | Toggles debug event streaming for the executor. Without arguments, reports current state.                        |
| `metrics`         | `/souls metrics`                      | Forces an immediate metrics snapshot to be logged if metrics are enabled.                                        |
| `reload`          | `/souls reload`                       | Convenience alias for `/souls admin reload`.                                                                     |

### Permissions

Administrative and observability subcommands honor the plugin's permission scheme:

- `souls.command` — baseline permission to execute `/souls list`, `/souls info`, `/souls select`, and `/souls respec`.
- `souls.command.admin` — required for `/souls admin *` and `/souls reload`.
- `souls.command.debug` — required to toggle debug output via `/souls debug`.
- `souls.command.metrics` — required for `/souls metrics`.

If a player lacks the necessary permission, the command denies the action with a friendly message.
