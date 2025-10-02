# Soul Catalog

The soul catalog defines the souls that players can discover and select via `/souls`. Entries are configured under the `souls.catalog` list in `config.yml` and are loaded during plugin startup.

## Entry Schema

| Field | Type | Purpose |
| --- | --- | --- |
| `id` | string | Stable identifier used when invoking commands such as `/souls select <id>` or when referring to a soul from other configuration files. |
| `name` | string | Player-facing name displayed in lists and menus. |
| `description` | string | Short flavor text surfaced in `/souls info`. Supports standard Minecraft color codes. |
| `difficulty` | string | Free-form label hinting at the expected challenge level. |
| `paths` | string[] | Collection of soul path keys granted to the player after selection. |

## Example

Below is a trimmed example of a catalog entry:

```yaml
souls:
  allow-respec: true
  catalog:
    - id: wrath
      name: Wrath
      description: >-
        Rage flows through your veins. Harness it to amplify every strike.
      difficulty: HARD
      paths:
        - carnage
        - devastation
```

## Operational Notes

- The catalog must contain at least one entry or the `/souls select` command will report that no souls are available.
- Catalog changes can be reloaded live with `/souls reload`.
- When `allow-respec` is `false`, existing players keep their current soul but cannot switch until the flag is restored.
