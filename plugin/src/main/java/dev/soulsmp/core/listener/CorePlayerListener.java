package dev.soulsmp.core.listener;

import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.soul.SoulAssignmentService;
import dev.soulsmp.core.soul.SoulStateService;
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
    private final SoulAssignmentService soulAssignmentService;
    private final SoulStateService soulStateService;

    public CorePlayerListener(PlayerProfileManager profileManager,
                             ResourceManager resourceManager,
                             UIDisplayService uiDisplayService,
                             SoulAssignmentService soulAssignmentService,
                             SoulStateService soulStateService) {
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.uiDisplayService = uiDisplayService;
        this.soulAssignmentService = soulAssignmentService;
        this.soulStateService = soulStateService;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        resourceManager.applyDefinitions(profileManager.getOrCreate(player.getUniqueId()));
        
        // Auto-assign soul if player doesn't have one
        soulAssignmentService.assignIfNeeded(player);

        // Ensure runtime state is synced with the player's profile
        soulStateService.refresh(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        uiDisplayService.onPlayerQuit(player.getUniqueId());
        soulStateService.clear(player);
        profileManager.unload(player.getUniqueId());
    }
}
