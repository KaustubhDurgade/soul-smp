# Resonance Specs Directory

Contents: Individual mechanical specifications for every pairwise soul resonance.

File Naming: `resonance_<soulA>_<soulB>.md` (souls sorted alphabetically in filename).  
Status Tags: `design_status: stable|draft`. Stable = vetted exemplar. Draft = initial scaffold needing tuning.

Spec Sections
1. Frontmatter (id, souls, name, tier, category, trigger, version, design_status)
2. Overview (short mechanical identity)
3. Trigger Conditions (precise detection logic)
4. Scaling & Math (explicit formulas, clamps)
5. Effects (detailed breakdown; stacking rules)
6. Cooldowns & Limits (internal + shared)
7. Conflicts (priority vs other systems)
8. Anti-Abuse (diminishing, caps, logging)
9. Implementation Notes (engine hooks, data refs)

Triplet / Mythic Fusions will later live in `mythic/` subfolder.
