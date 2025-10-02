package dev.soulsmp.core.command;

import dev.soulsmp.admin.AdminSoulManager;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.soul.SoulProgressionService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * Handles /tactical command for all souls.
 */
public final class TacticalCommand extends AbilityCommand implements Listener {

    private final Plugin plugin;

    public TacticalCommand(Plugin plugin,
                          PlayerProfileManager profileManager,
                          SoulProgressionService progressionService,
                          MessageService messageService,
                          AdminSoulManager adminSoulManager) {
        super(profileManager, progressionService, messageService, SoulProgressionService.AbilityType.TACTICAL, adminSoulManager);
        this.plugin = plugin;
    }

    @Override
    protected void executeAbility(Player player, PlayerProfile profile, String abilitySoul, String[] args) {
        // Route to appropriate soul's tactical ability
        switch (abilitySoul.toLowerCase()) {
            case "wrath" -> executeWrathTactical(player);
            // Add other souls here
            default -> messageService.send(player, "ability.not-implemented");
        }
    }

    private void executeWrathTactical(Player player) {
        // This will be handled by WrathIgnitionManager
        plugin.getServer().dispatchCommand(player, "wrath ignition");
    }
}
