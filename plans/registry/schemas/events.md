# Event Schema

| Field           | Type   | Required | Notes                          |
| --------------- | ------ | -------- | ------------------------------ |
| id              | string | yes      | `event.*`                      |
| name            | string | yes      |                                |
| category        | enum   | yes      | WEATHER, etc.                  |
| scope           | enum   | yes      | LOCAL–GLOBAL                   |
| rarity          | enum   | yes      | Weighting                      |
| intensity_tier  | int    | yes      | 1–3                            |
| trigger         | object | yes      | mode, intervals, biome filters |
| phases          | object | yes      | telegraph/active/cooldown      |
| scaling         | object | optional | Axes & clamps                  |
| mechanics       | object | yes      | Concrete effect payload        |
| resonance_hooks | list   | optional | Conditional edits              |
| visual          | object | yes      | Particle budgets               |
| rewards         | object | optional | loot table ref                 |
| anti_abuse      | object | optional | fatigue, gating                |
| logging         | object | optional | audit                          |
