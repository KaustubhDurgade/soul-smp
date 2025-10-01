---
id: glory+trickery.heroic_misdirection
souls: [glory, trickery]
name: Heroic Misdirection
tier: F
category: CTRL
trigger: STATUS(flag hero clone)
version: 0.1.0
design_status: draft
---
### Overview
Designated clone radiates hero aura drawing aggro / misdirecting targeted abilities.
### Trigger Conditions
Clone spawn while Glory in combat within 10 blocks.
### Scaling & Math
MisdirectChance = 0.25 + 0.05*(participants-1) (cap 0.35).
### Effects
Redirects single-target ability to clone; clone explodes on redirect.
### Cooldowns & Limits
Internal 30s.
### Conflicts
Mirror Madness buff stacking reduces chance overlap to max 40%.
### Anti-Abuse
One flagged clone at a time.
### Implementation Notes
Clone spawn tagging + redirect hook.
