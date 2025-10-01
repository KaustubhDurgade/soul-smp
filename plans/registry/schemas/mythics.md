# Mythic Schema

| Field                 | Type   | Required    | Notes                       |
| --------------------- | ------ | ----------- | --------------------------- |
| id                    | string | yes         | `mythic.*`                  |
| name                  | string | yes         |                             |
| mythic_type           | enum   | yes         | WEAPON, etc.                |
| tier                  | int    | yes         | 2–3                         |
| activation_mode       | enum   | yes         | PASSIVE_ONLY...             |
| acquisition_path      | enum   | yes         | Drop/craft/fusion/etc       |
| soul_focus            | list   | optional    | Affinity                    |
| cooldown_seconds      | int    | conditional | Active modes                |
| active_window_seconds | int    | conditional | Windows / transforms        |
| transformation        | object | conditional | Ability overrides           |
| scaling               | object | optional    | Soul/resonance multipliers  |
| resonance_hooks       | list   | optional    | Conditional modifications   |
| visual                | object | yes         | Aura / model keys           |
| anti_abuse            | object | optional    | revert locks, limited flags |
| attunement            | object | optional    | exclusive slot info         |
| logging               | object | optional    | audit                       |
