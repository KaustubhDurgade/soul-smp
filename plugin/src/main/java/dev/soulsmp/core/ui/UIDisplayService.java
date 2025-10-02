package dev.soulsmp.core.ui;

import dev.soulsmp.core.cooldown.CooldownManager;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.soul.SoulProgressionService;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages ActionBar and BossBar displays for players.
 * Provides real-time visual feedback for resources and cooldowns.
 */
public final class UIDisplayService {

    private final Plugin plugin;
    private final PlayerProfileManager profileManager;
    private final ResourceManager resourceManager;
    private final CooldownManager cooldownManager;
    private final SoulProgressionService progressionService;

    private final Map<UUID, BossBar> activeBossBars = new ConcurrentHashMap<>();
    private final Map<UUID, UIDisplayMode> displayModes = new ConcurrentHashMap<>();
    private final Map<UUID, String> primaryResourceKeys = new ConcurrentHashMap<>();

    private int updateTaskId = -1;

    public UIDisplayService(Plugin plugin,
                           PlayerProfileManager profileManager,
                           ResourceManager resourceManager,
                           CooldownManager cooldownManager,
                           SoulProgressionService progressionService) {
        this.plugin = plugin;
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.cooldownManager = cooldownManager;
        this.progressionService = progressionService;
    }

    /**
     * Start the UI update task that refreshes displays every tick.
     */
    public void start() {
        if (updateTaskId != -1) {
            return;
        }

        updateTaskId = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateDisplay(player);
            }
        }, 0L, 10L).getTaskId(); // Update every 10 ticks (0.5s)
    }

    /**
     * Stop the UI update task.
     */
    public void stop() {
        if (updateTaskId != -1) {
            Bukkit.getScheduler().cancelTask(updateTaskId);
            updateTaskId = -1;
        }

        // Clear all boss bars
        for (Map.Entry<UUID, BossBar> entry : activeBossBars.entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());
            if (player != null) {
                player.hideBossBar(entry.getValue());
            }
        }
        activeBossBars.clear();
    }

    /**
     * Set the display mode for a player.
     */
    public void setDisplayMode(UUID playerId, UIDisplayMode mode) {
        displayModes.put(playerId, mode);
    }

    /**
     * Get the current display mode for a player.
     */
    public UIDisplayMode getDisplayMode(UUID playerId) {
        return displayModes.getOrDefault(playerId, UIDisplayMode.ACTION_BAR);
    }

    /**
     * Set the primary resource to display for a player.
     * This is typically determined by their selected soul.
     */
    public void setPrimaryResource(UUID playerId, String resourceKey) {
        primaryResourceKeys.put(playerId, resourceKey);
    }

    /**
     * Update the UI display for a specific player.
     */
    private void updateDisplay(Player player) {
        UUID playerId = player.getUniqueId();
        PlayerProfile profile = profileManager.getOrCreate(playerId);
        if (profile == null) {
            return;
        }

        UIDisplayMode mode = getDisplayMode(playerId);
        switch (mode) {
            case ACTION_BAR -> updateActionBar(player, profile);
            case BOSS_BAR -> updateBossBar(player, profile);
            case BOTH -> {
                updateActionBar(player, profile);
                updateBossBar(player, profile);
            }
            case NONE -> clearDisplay(player);
        }
    }

    /**
     * Update the ActionBar for a player - now shows abilities and cooldowns.
     */
    private void updateActionBar(Player player, PlayerProfile profile) {
        // Build ability status display
        Component abilityDisplay = buildAbilityStatusComponent(player, profile);
        
        if (abilityDisplay != null) {
            player.sendActionBar(abilityDisplay);
        }
    }

    /**
     * Build component showing all abilities and their cooldowns/lock status.
     */
    private Component buildAbilityStatusComponent(Player player, PlayerProfile profile) {
        String soulId = profile.getSoulId();
        if (soulId == null || soulId.isBlank()) {
            return Component.text("No soul assigned", NamedTextColor.GRAY);
        }

        String normalized = soulId.trim().toLowerCase(Locale.ROOT);
        return switch (normalized) {
            case "wrath" -> buildWrathActionBar(player, profile, false);
            case "admin" -> buildAdminActionBar(player, profile);
            default -> buildGenericActionBar(player, profile, normalized);
        };
    }

    private Component buildGenericActionBar(Player player, PlayerProfile profile, String normalizedSoulId) {
        TextComponent.Builder builder = Component.text();
        builder.append(Component.text(normalizedSoulId.toUpperCase(Locale.ROOT) + " ", NamedTextColor.GOLD));
        builder.append(Component.text("Lv." + profile.getSoulLevel(), NamedTextColor.YELLOW));
        builder.append(Component.text(" | ", NamedTextColor.DARK_GRAY));

        builder.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.TACTICAL,
            "T", "Tactical", "tactical", false));
        builder.append(Component.text(" | ", NamedTextColor.DARK_GRAY));

        builder.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.MOVEMENT,
            "M", "Movement", "movement", false));
        builder.append(Component.text(" | ", NamedTextColor.DARK_GRAY));

        builder.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.ULTIMATE,
            "U", "Ultimate", "ultimate", false));

        return builder.build();
    }

    private Component buildWrathActionBar(Player player, PlayerProfile profile, boolean forceUnlocks) {
        TextComponent.Builder builder = Component.text();
        builder.append(Component.text("⚔ Wrath ", NamedTextColor.RED));
        builder.append(Component.text("Lv." + profile.getSoulLevel(), NamedTextColor.GOLD));
        builder.append(Component.text(" | ", NamedTextColor.DARK_GRAY));
        builder.append(buildWrathAbilities(player, profile, forceUnlocks));
        return builder.build();
    }

    private Component buildWrathAbilities(Player player, PlayerProfile profile, boolean forceUnlocks) {
        TextComponent.Builder abilities = Component.text();
        abilities.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.TACTICAL,
            "✴", "Ignition", "tactical", forceUnlocks));
        abilities.append(Component.text(" | ", NamedTextColor.DARK_GRAY));

        abilities.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.MOVEMENT,
            "⛓", "Chain", "movement", forceUnlocks));
        abilities.append(Component.text(" | ", NamedTextColor.DARK_GRAY));

        abilities.append(formatAbilityStatus(player, profile, SoulProgressionService.AbilityType.ULTIMATE,
            "☄", "Meteor", "ultimate", forceUnlocks));
        return abilities.build();
    }

    private Component buildAdminActionBar(Player player, PlayerProfile profile) {
        TextComponent.Builder builder = Component.text();
        builder.append(Component.text("👑 Admin Soul", NamedTextColor.LIGHT_PURPLE));

        String abilitySoul = normalize(profile.getAdminAbilitySoul());
        builder.append(Component.text(" | Ability: ", NamedTextColor.DARK_AQUA));
        if (abilitySoul == null) {
            builder.append(Component.text("Unset", NamedTextColor.GRAY));
        } else {
            builder.append(Component.text(capitalize(abilitySoul), NamedTextColor.GOLD));
            builder.append(Component.text(" • ", NamedTextColor.DARK_GRAY));
            switch (abilitySoul) {
                case "wrath" -> builder.append(buildWrathAbilities(player, profile, true));
                default -> builder.append(Component.text("No preview", NamedTextColor.GRAY));
            }
        }

        builder.append(Component.text(" | Passive: ", NamedTextColor.DARK_AQUA));
        String passiveSoul = normalize(profile.getAdminPassiveSoul());
        builder.append(Component.text(passiveSoul == null ? "None" : capitalize(passiveSoul), NamedTextColor.GOLD));

        return builder.build();
    }

    private Component formatAbilityStatus(Player player,
                                          PlayerProfile profile,
                                          SoulProgressionService.AbilityType abilityType,
                                          String icon,
                                          String label,
                                          String cooldownKey,
                                          boolean forceUnlock) {
        TextComponent.Builder builder = Component.text();
        builder.append(Component.text(icon + " ", NamedTextColor.GOLD));
        builder.append(Component.text(label + " ", NamedTextColor.YELLOW));

        boolean unlocked = forceUnlock || progressionService.hasUnlocked(player.getUniqueId(), abilityType);
        if (!unlocked) {
            int requiredLevel = requiredLevelFor(abilityType);
            builder.append(Component.text("LOCKED Lv" + requiredLevel, NamedTextColor.RED));
            return builder.build();
        }

        Duration remaining = cooldownManager.getRemaining(player.getUniqueId(), cooldownKey);
        long seconds = remaining.toSeconds();
        if (seconds > 0) {
            builder.append(Component.text(seconds + "s", NamedTextColor.RED));
        } else {
            builder.append(Component.text("READY", NamedTextColor.GREEN));
        }
        return builder.build();
    }

    private int requiredLevelFor(SoulProgressionService.AbilityType abilityType) {
        return switch (abilityType) {
            case PASSIVE -> SoulProgressionService.PASSIVE_UNLOCK_LEVEL;
            case TACTICAL -> SoulProgressionService.TACTICAL_UNLOCK_LEVEL;
            case MOVEMENT -> SoulProgressionService.MOVEMENT_UNLOCK_LEVEL;
            case ULTIMATE -> SoulProgressionService.ULTIMATE_UNLOCK_LEVEL;
            case FINAL_STAND -> SoulProgressionService.FINAL_STAND_UNLOCK_LEVEL;
        };
    }

    private String normalize(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return null;
        }
        return trimmed.toLowerCase(Locale.ROOT);
    }

    private String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.substring(0, 1).toUpperCase(Locale.ROOT) + value.substring(1).toLowerCase(Locale.ROOT);
    }

    /**
     * Update the BossBar for a player.
     */
    private void updateBossBar(Player player, PlayerProfile profile) {
        String resourceKey = primaryResourceKeys.get(player.getUniqueId());
        if (resourceKey == null || resourceKey.isBlank()) {
            return;
        }

        int current = resourceManager.get(player.getUniqueId(), resourceKey);
        int max = resourceManager.getMax(player.getUniqueId(), resourceKey);

        float progress = max > 0 ? Math.max(0.0f, Math.min(1.0f, (float) current / max)) : 0.0f;

        BossBar bossBar = activeBossBars.get(player.getUniqueId());
        if (bossBar == null) {
            bossBar = BossBar.bossBar(
                buildResourceComponent(resourceKey, current, max),
                progress,
                determineBarColor(resourceKey),
                BossBar.Overlay.PROGRESS
            );
            activeBossBars.put(player.getUniqueId(), bossBar);
            player.showBossBar(bossBar);
        } else {
            bossBar.name(buildResourceComponent(resourceKey, current, max));
            bossBar.progress(progress);
        }
    }

    /**
     * Build a component displaying the resource value.
     */
    private Component buildResourceComponent(String resourceKey, int current, int max) {
        TextColor color = determineResourceColor(resourceKey, current, max);
        return Component.text(capitalizeResource(resourceKey) + ": ", NamedTextColor.GRAY)
            .append(Component.text(current + "/" + max, color));
    }

    /**
     * Determine the color for a resource based on its value.
     */
    private TextColor determineResourceColor(String resourceKey, int current, int max) {
        if (max == 0) {
            return NamedTextColor.GRAY;
        }

        float percentage = (float) current / max;
        if (percentage >= 0.75f) {
            return NamedTextColor.GREEN;
        } else if (percentage >= 0.5f) {
            return NamedTextColor.YELLOW;
        } else if (percentage >= 0.25f) {
            return NamedTextColor.GOLD;
        } else {
            return NamedTextColor.RED;
        }
    }

    /**
     * Determine the BossBar color based on resource type.
     */
    private BossBar.Color determineBarColor(String resourceKey) {
        return switch (resourceKey.toLowerCase()) {
            case "heat" -> BossBar.Color.RED;
            case "harmony" -> BossBar.Color.BLUE;
            case "wealth" -> BossBar.Color.YELLOW;
            default -> BossBar.Color.WHITE;
        };
    }

    /**
     * Clear all displays for a player.
     */
    private void clearDisplay(Player player) {
        BossBar bossBar = activeBossBars.remove(player.getUniqueId());
        if (bossBar != null) {
            player.hideBossBar(bossBar);
        }
    }

    /**
     * Clean up displays when a player logs out.
     */
    public void onPlayerQuit(UUID playerId) {
        activeBossBars.remove(playerId);
        displayModes.remove(playerId);
        primaryResourceKeys.remove(playerId);
    }

    /**
     * Capitalize a resource key for display.
     */
    private String capitalizeResource(String key) {
        if (key == null || key.isEmpty()) {
            return key;
        }
        return key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
    }
}
