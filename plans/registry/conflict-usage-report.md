# Conflict Tag Usage Report (Generated Manually Phase 1)

| Tag | Referenced In (count) | Notes |
|-----|-----------------------|-------|
| sustain_leech | contracts: blood_pact, harmony_convergence (regen domain via support_regen interplay), aurora_crown (lifesteal) | Lifesteal + regen separation verified |
| area_control_fire | infernal, magma_offer, cataclysm, elemental_offering | All hazard style; mutual exclusion works |
| area_control_water | tides_unbound | Single current user (ok) |
| hybrid_fusion | cataclysm, soul_fusion, shadow_bargain | Fusion gating correct |
| silence_extension | (future items/mythics) eclipse_blade passive extension, oblivion_shard cap | Ensure cap enforcement central |
| cooldown_reducer | temporal_pact, life_for_power, mana_infusion, chronos_hourglass | Diminishing domain active |
| transformation | all transformation mythics (implied) | Tag applied at activation layer |
| world_anchor_dark | (none yet – eclipse_totem uses region_unique_anchor) | Consider mapping eclipse_totem into this tag family |
| weather_chain | elemental_storm (fatigue), meteor_shower | Verified |
| elemental_intensity | elemental_offering (planned), (future) event amplifications | Add explicit usage later |
| passive_duration_amp | soul_prism | Duration cap applied |
| control_freeze | frozen_oath | Per-target immunity table required |
| stealth_field | shadow_merge, shadow_bargain | Shared invis domain |
| support_regen | natures_tribute, harmony_convergence | Strongest-only rule required |

Next Pass: auto script to verify every `conflict_tags:` token is declared here; mark unknowns.
