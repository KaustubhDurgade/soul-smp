# UI Display Examples

This document shows what players will see with the Phase 3 UI feedback system.

## ActionBar Display

The ActionBar appears above the player's hotbar and shows:

### Basic Resource Display
```
Heat: 45/100
```

### With Active Cooldown
```
Heat: 67/100 | CD: ignition 8s
```

### Multiple Resources (Future)
```
Heat: 45/100 | Harmony: 80/150
```

### Color Coding
- **Green** (≥75%): `Heat: 85/100`
- **Yellow** (≥50%): `Heat: 60/100`
- **Gold** (≥25%): `Heat: 35/100`
- **Red** (<25%): `Heat: 15/100`

---

## BossBar Display

The BossBar appears at the top of the screen as a progress bar:

### Visual Appearance
```
┌────────────────────────────────────────┐
│ Heat: 67/100 ██████████░░░░░░░░░░░░░░ │
└────────────────────────────────────────┘
```

### Bar Colors by Resource Type
- **RED** - Heat (Wrath)
- **BLUE** - Harmony (Serenity)
- **YELLOW** - Wealth (Greed)
- **WHITE** - Default/Unknown

### Progress Fill
The bar fills proportionally:
- 0/100 Heat = Empty bar
- 50/100 Heat = Half-filled bar
- 100/100 Heat = Full bar

---

## Display Modes

Players can choose from four display modes (future command):

### ACTION_BAR (Default)
- Shows resource/cooldowns above hotbar
- Less intrusive
- Always visible during gameplay

### BOSS_BAR
- Shows resource as progress bar at top
- More prominent
- Better for tracking resource levels

### BOTH
- Combines both displays
- Maximum visibility
- Recommended for complex souls

### NONE
- Disables all UI displays
- For players who prefer minimal HUD
- Data still tracked server-side

---

## Configuration

Server operators can configure UI behavior in `config.yml`:

```yaml
ui:
  # Default display mode for new players
  # Options: ACTION_BAR, BOSS_BAR, BOTH, NONE
  default-mode: ACTION_BAR
  
  # How often to update displays (in ticks)
  # 10 ticks = 0.5 seconds (recommended)
  # 20 ticks = 1.0 seconds
  update-interval-ticks: 10
```

---

## Implementation Details

### Resource Display Logic
```java
// Color changes based on percentage
float percentage = current / max;
if (percentage >= 0.75f) → GREEN
if (percentage >= 0.50f) → YELLOW
if (percentage >= 0.25f) → GOLD
else → RED
```

### Cooldown Display Logic
```java
// Shows first active cooldown
CD: <ability-name> <seconds-remaining>s

// Examples:
CD: ignition 3s
CD: devastation 12s
```

### Auto-Cleanup
- UI displays automatically removed on player quit
- No ghost bars or stale data
- Clean server restarts

---

## Future Enhancements (Fabric Mod)

The optional client mod will enhance these displays:

### Draggable Elements
```
┌─────────────────┐
│ Heat: 45/100    │ ← Drag anywhere
└─────────────────┘
```

### Resizable Elements
```
┌────────────┐        ┌──────────────────┐
│ Heat: 45   │   →    │ Heat: 45/100     │
└────────────┘        └──────────────────┘
     Small                    Large
```

### Custom Textures
- Soul-specific icons
- Animated resource bars
- Visual effects on resource changes
- Particle effects on cooldown ready

### Advanced Layouts
```
┌──────────────────────────────┐
│ [Wrath Icon]  Heat: 67/100   │
│                               │
│ [Fire Icon] Ignition: 3s     │
│ [Sword Icon] Devastation: 8s │
└──────────────────────────────┘
```

---

## Player Commands (Future)

Eventually players will be able to customize their UI:

```bash
/souls ui mode <ACTION_BAR|BOSS_BAR|BOTH|NONE>
/souls ui toggle           # Quick on/off
/souls ui scale <0.5-2.0>  # Size adjustment (mod only)
/souls ui position reset   # Reset to defaults (mod only)
```

---

## Technical Notes

### Performance
- Updates every 10 ticks (0.5s) by default
- Minimal CPU impact (<1ms per update)
- Scales to 100+ concurrent players

### Compatibility
- Works on vanilla clients (no mod required)
- Uses native Minecraft ActionBar/BossBar APIs
- Paper/Spigot compatible
- Forwards/backwards compatible across MC versions

### Data Flow
```
ResourceManager → UIDisplayService → Player Display
     ↓                    ↓              ↓
  Updates           Formatting     ActionBar/BossBar
```

---

This UI system provides immediate visual feedback for soul resources and abilities while maintaining vanilla compatibility. The optional Fabric mod will add advanced customization for players who want it.
