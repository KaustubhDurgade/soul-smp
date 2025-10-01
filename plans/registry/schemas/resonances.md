# Resonance Schema (Pairwise)

| Field | Type | Required | Notes |
|-------|------|----------|-------|
| id | string | yes | `resonance.<soulA>_<soulB>` |
| souls | list(2) | yes | Ordered or canonical pair |
| tier | enum | yes | C,T,F,U,X mapping or internal numeric |
| category | enum | yes | OFF, DEF, UTIL, CTRL, HYB |
| trigger | object | yes | type + conditions |
| internal_cooldown | number | optional | seconds |
| effects | object | yes | mechanical payload |
| scaling | object | optional | formulas + clamps |
| conflicts | list | optional | conflict tags |
| anti_abuse | object | optional | diminishing / caps |
| status | enum | optional | draft/stable |
