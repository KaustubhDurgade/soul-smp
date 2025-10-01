# Contract Schema (Formal Breakdown)

| Field           | Type             | Required    | Notes                                  |
| --------------- | ---------------- | ----------- | -------------------------------------- |
| id              | string           | yes         | `contract.*` namespace                 |
| name            | string           | yes         | Display                                |
| tier            | int              | yes         | 1–3                                    |
| class           | enum(OFFENSE,..) | yes         | For budget                             |
| scope           | enum             | yes         | SELF/LOCAL/AREA/GLOBAL                 |
| activation      | object           | yes         | mode, cast_time, cooldown, lockout_tag |
| sacrifice       | object           | yes         | type + parameters                      |
| effects         | object           | conditional | Aura / buff / field definitions        |
| scaling         | object           | optional    | Formulas + clamps                      |
| resonance_hooks | list             | optional    | Conditional modifications              |
| visual          | object           | yes         | Sigil + particle references            |
| conflict_tags   | list             | optional    | For stacking manager                   |
| anti_abuse      | object           | optional    | diminishing tags, floors               |
| logging         | object           | optional    | audit level                            |
