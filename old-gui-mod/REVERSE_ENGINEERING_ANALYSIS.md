# Kaisen GUI Mod - Reverse Engineering Analysis

## Overview
This is a client-side Fabric mod for Minecraft 1.21 that provides a moveable and resizable GUI overlay for another plugin. The mod demonstrates advanced GUI manipulation techniques that can be adapted for the Phase SMP plugin.

## Key Components Analysis

### 1. Element.java - Core GUI Manipulation Engine

**Purpose**: Handles position, scaling, and interactive manipulation of GUI elements.

**Key Features**:
- **Position Control**: `PositionX`, `PositionY` for element positioning
- **Scale Control**: `Scale` with minimum size constraints (`SmallestSize = 0.2`)
- **Snap-to-Grid**: `SnapIncrement = 4.0` for precise positioning
- **Mouse Interaction**: Multiple manipulation states for dragging and resizing
- **Bounding Box Detection**: `AABBCheck()` for mouse hover/click detection
- **Persistent Configuration**: Save/load position and scale to file

**Manipulation States**:
- `-2`: No interaction
- `-1`: Not selected
- `0`: Outside element bounds
- `1`: Element selected (dragging mode)
- `2`: Resize handle selected (scaling mode)

**Critical Methods**:
```java
// Main interaction handler
public void HandleLines(ConfigScreen screen, DrawContext context, TextRenderer textRenderer, int mouseX, int mouseY)

// Scaling logic with proportional resize
public void HandleScaling(int mouseX, int mouseY)

// Collision detection for mouse interactions
protected boolean AABBCheck(int mouseX, int mouseY, int x1, int x2, int y1, int y2)

// Configuration persistence
public String save() // Saves position, scale, rotation
public void load(Scanner input) // Loads saved configuration
```

### 2. ConfigScreen.java - GUI Configuration Interface

**Purpose**: Provides an interactive configuration screen for adjusting GUI elements.

**Key Features**:
- Extends Minecraft's Screen class for native integration
- Real-time preview of GUI changes
- Reset functionality
- Mouse interaction handling
- Auto-save on changes

### 3. DrawGUICallback.java - Rendering Engine

**Purpose**: Handles the actual rendering of the GUI overlay during gameplay.

**Key Rendering Techniques**:
- **Matrix Scaling**: Uses `startScaling()` and `stopScaling()` for proportional rendering
- **Texture Rendering**: Multiple texture layers with proper blending
- **Dynamic Content**: Real-time data updates (health, cooldowns, etc.)
- **Color Management**: Dynamic color changes based on game state
- **Text Rendering**: Scaled text with proper positioning

**Scaling Implementation**:
```java
private void startScaling(DrawContext context, double scale) {
    this.matrixStack = context.getMatrices();
    this.matrixStack.push();
    this.matrixStack.scale((float)scale, (float)scale, (float)scale);
}

private void stopScaling(DrawContext context) {
    this.matrixStack.pop();
}
```

### 4. KaisenClient.java - Main Client Handler

**Purpose**: Initializes the mod and manages configuration persistence.

**Key Features**:
- Fabric mod initialization
- Event registration for HUD rendering
- Configuration file management (`kaisen.cfg`)
- Network event handling
- Keybinding registration

### 5. HandleData.java - Data Management

**Purpose**: Processes server data and updates GUI display information.

**Data Protocol Analysis**:
```java
// Server sends data in format: "hdn_mode_data1_data2_..."
// Mode 0: Initialize connection
// Mode 1: Update cursed energy
// Mode 2: Update ability data (cooldowns, costs, names, colors)
```

## Adaptation Strategy for Phase SMP Plugin

### 1. GUI Element System
Create a modular `PhaseGUIElement` class based on the Element.java structure:

```java
public class PhaseGUIElement {
    // Position and scale properties
    private int positionX, positionY;
    private double scale = 1.0;
    private double minScale = 0.2;
    
    // Interaction states
    private int manipulationStatus = -1;
    private boolean isSelected = false;
    
    // Methods to implement:
    // - handleInteraction(mouseX, mouseY, mouseButton)
    // - render(context, mouseX, mouseY)
    // - save/load configuration
    // - collision detection
}
```

### 2. Magic System Integration
Adapt the data handling for magic abilities:

```java
public class MagicDataHandler {
    // Magic energy (equivalent to cursed energy)
    public static int magicEnergy = 0;
    
    // Ability data
    public static String ability1Name = "";
    public static String ability2Name = "";
    public static int ability1Cooldown = 0;
    public static int ability2Cooldown = 0;
    
    // Color schemes for different magic types
    public static int magicTypeColor = 0xFFFFFF;
    
    // Parse server data packets
    public static void parseServerData(String data) {
        // Implement data parsing similar to HandleData.parseData()
    }
}
```

### 3. Rendering System
Create a `MagicGUIRenderer` that handles:

- **Magic energy bars** with different colors per magic type
- **Ability icons** with cooldown overlays
- **Skill point displays**
- **Magic type indicators**
- **Scalable text and graphics**

### 4. Configuration Management
Implement persistent GUI settings:

```java
public class PhaseGUIConfig {
    private static final String CONFIG_FILE = "phase-gui.cfg";
    
    public static void save() {
        // Save position, scale, enabled elements
    }
    
    public static void load() {
        // Load saved configuration
    }
}
```

### 5. Server Communication Protocol
Design a communication system between the Phase SMP server plugin and client mod:

**Data Packet Format**:
```
"phase_mode_data..."
- Mode 0: Player joins, send magic type and initial data
- Mode 1: Update magic energy
- Mode 2: Update ability cooldowns
- Mode 3: Update skill points
- Mode 4: Update active effects
```

### 6. Integration Points

**Server-Side (Phase SMP Plugin)**:
- Send data packets when player stats change
- Listen for GUI configuration commands
- Handle ability activation from GUI

**Client-Side (GUI Mod)**:
- Receive and process server data
- Render GUI overlay with current information
- Send configuration changes back to server
- Handle user interactions (clicking abilities, etc.)

## Implementation Roadmap

### Phase 1: Core GUI Framework
1. Create base GUI element class with position/scale handling
2. Implement mouse interaction system
3. Add configuration save/load functionality
4. Create basic rendering framework

### Phase 2: Magic System Integration
1. Design data communication protocol
2. Implement magic-specific rendering (energy bars, icons)
3. Add magic type color schemes
4. Create ability cooldown displays

### Phase 3: Advanced Features
1. Add drag-and-drop customization
2. Implement GUI themes for different magic types
3. Add animation effects
4. Create advanced configuration options

### Phase 4: Polish & Optimization
1. Performance optimization
2. Error handling and fallbacks
3. User experience improvements
4. Documentation and setup guides

## Technical Considerations

### Minecraft Version Compatibility
- The decompiled mod targets Minecraft 1.21
- Uses obfuscated Minecraft class names (class_310, class_332, etc.)
- Fabric mod loader integration required

### Performance Optimization
- Use efficient rendering techniques
- Minimize network traffic
- Cache frequently accessed data
- Implement proper resource cleanup

### User Experience
- Intuitive drag-and-drop interface
- Visual feedback for interactions
- Reasonable default settings
- Easy reset functionality

This analysis provides a complete blueprint for adapting the Kaisen GUI mod's techniques to create a powerful, customizable GUI system for the Phase SMP magic plugin.