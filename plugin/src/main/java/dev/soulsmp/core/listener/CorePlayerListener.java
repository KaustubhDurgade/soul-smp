package dev.soulsmp.core.listener;

import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.ui.UIDisplayService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class CorePlayerListener implements Listener {

    private final PlayerProfileManager profileManager;
    private final ResourceManager resourceManager;
    private final UIDisplayService uiDisplayService;

    public CorePlayerListener(PlayerProfileManager profileManager, 
                             ResourceManager resourceManager,
                             UIDisplayService uiDisplayService) {
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.uiDisplayService = uiDisplayService;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        resourceManager.applyDefinitions(profileManager.getOrCreate(player.getUniqueId()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        uiDisplayService.onPlayerQuit(player.getUniqueId());
        profileManager.unload(player.getUniqueId());
    }
}
