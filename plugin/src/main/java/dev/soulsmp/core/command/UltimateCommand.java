package dev.soulsmp.core.command;

import dev.soulsmp.admin.AdminSoulManager;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.soul.SoulProgressionService;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Handles /ultimate command for all souls.
 */
public final class UltimateCommand extends AbilityCommand {

    private final Plugin plugin;

    public UltimateCommand(Plugin plugin,
                          PlayerProfileManager profileManager,
                          SoulProgressionService progressionService,
                          MessageService messageService,
                          AdminSoulManager adminSoulManager) {
        super(profileManager, progressionService, messageService, SoulProgressionService.AbilityType.ULTIMATE, adminSoulManager);
        this.plugin = plugin;
    }

    @Override
    protected void executeAbility(Player player, PlayerProfile profile, String abilitySoul, String[] args) {
        switch (abilitySoul.toLowerCase()) {
            case "wrath" -> plugin.getServer().dispatchCommand(player, "wrath meteor");
            default -> messageService.send(player, "ability.not-implemented");
        }
    }
}
