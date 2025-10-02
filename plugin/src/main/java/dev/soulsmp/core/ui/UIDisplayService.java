package dev.soulsmp.core.ui;

import dev.soulsmp.core.cooldown.CooldownManager;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
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

    private final Map<UUID, BossBar> activeBossBars = new ConcurrentHashMap<>();
    private final Map<UUID, UIDisplayMode> displayModes = new ConcurrentHashMap<>();
    private final Map<UUID, String> primaryResourceKeys = new ConcurrentHashMap<>();

    private int updateTaskId = -1;

    public UIDisplayService(Plugin plugin,
                           PlayerProfileManager profileManager,
                           ResourceManager resourceManager,
                           CooldownManager cooldownManager) {
        this.plugin = plugin;
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.cooldownManager = cooldownManager;
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
     * Update the ActionBar for a player.
     */
    private void updateActionBar(Player player, PlayerProfile profile) {
        String resourceKey = primaryResourceKeys.get(player.getUniqueId());
        if (resourceKey == null || resourceKey.isBlank()) {
            return;
        }

        int current = resourceManager.get(player.getUniqueId(), resourceKey);
        int max = resourceManager.getMax(player.getUniqueId(), resourceKey);

        // Build resource display
        Component resourceDisplay = buildResourceComponent(resourceKey, current, max);

        // Add cooldown display if any abilities are on cooldown
        Component cooldownDisplay = buildCooldownComponent(player.getUniqueId());

        Component combined;
        if (cooldownDisplay != null) {
            combined = resourceDisplay.append(Component.text(" | ")).append(cooldownDisplay);
        } else {
            combined = resourceDisplay;
        }

        player.sendActionBar(combined);
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
     * Build a component displaying active cooldowns.
     */
    private Component buildCooldownComponent(UUID playerId) {
        Map<String, Long> cooldowns = cooldownManager.getActiveCooldowns(playerId);
        if (cooldowns.isEmpty()) {
            return null;
        }

        // Show the first cooldown (most recently triggered)
        Map.Entry<String, Long> first = cooldowns.entrySet().iterator().next();
        long remaining = (first.getValue() - System.currentTimeMillis()) / 1000L;

        return Component.text("CD: ", NamedTextColor.GRAY)
            .append(Component.text(first.getKey() + " ", NamedTextColor.YELLOW))
            .append(Component.text(remaining + "s", NamedTextColor.WHITE));
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
