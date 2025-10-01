# Item Schema

| Field | Type | Required | Notes |
|-------|------|----------|-------|
| id | string | yes | `item.*` |
| name | string | yes |  |
| slot_type | enum | yes | Slot gating |
| rarity | enum | yes | Budget envelope |
| item_class | enum | yes | STAT/ACTIVE/PASSIVE/HYBRID |
| soul_affinity | list | optional | Affects attunement bonus |
| stats | object | optional | Numeric pairs |
| passives | list | optional | Passive definitions |
| active | object | conditional | Cooldown + effects |
| resonance_hooks | list | optional | Conditional modifications |
| visual | object | optional | Model + trails |
| upgrade | object | optional | Path + stages |
| anti_abuse | object | optional | Stack rules |
| logging | object | optional | audit |
