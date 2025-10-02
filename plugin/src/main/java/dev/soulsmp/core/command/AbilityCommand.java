package dev.soulsmp.core.command;

import dev.soulsmp.admin.AdminSoulManager;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.soul.SoulProgressionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Base class for ability commands (/passive, /tactical, /movement, /ultimate).
 */
public abstract class AbilityCommand implements CommandExecutor {

    protected final PlayerProfileManager profileManager;
    protected final SoulProgressionService progressionService;
    protected final MessageService messageService;
    protected final SoulProgressionService.AbilityType abilityType;
    protected final AdminSoulManager adminSoulManager;

    protected AbilityCommand(PlayerProfileManager profileManager,
                           SoulProgressionService progressionService,
                           MessageService messageService,
                           SoulProgressionService.AbilityType abilityType,
                           AdminSoulManager adminSoulManager) {
        this.profileManager = profileManager;
        this.progressionService = progressionService;
        this.messageService = messageService;
        this.abilityType = abilityType;
        this.adminSoulManager = adminSoulManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            messageService.send(sender, "command.player-only");
            return true;
        }

        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        
        String primarySoul = profile.getSoulId();
        if (primarySoul == null || primarySoul.isBlank()) {
            messageService.send(player, "ability.no-soul");
            return true;
        }

        boolean adminSoul = adminSoulManager != null && adminSoulManager.isAdminSoul(profile);
        String abilitySoul = resolveAbilitySoul(profile, adminSoul);
        if (abilitySoul == null) {
            messageService.send(player, "ability.admin.no-override");
            return true;
        }

        if (!adminSoul && !progressionService.hasUnlocked(player.getUniqueId(), abilityType)) {
            int requiredLevel = switch (abilityType) {
                case PASSIVE -> SoulProgressionService.PASSIVE_UNLOCK_LEVEL;
                case TACTICAL -> SoulProgressionService.TACTICAL_UNLOCK_LEVEL;
                case MOVEMENT -> SoulProgressionService.MOVEMENT_UNLOCK_LEVEL;
                case ULTIMATE -> SoulProgressionService.ULTIMATE_UNLOCK_LEVEL;
                case FINAL_STAND -> SoulProgressionService.FINAL_STAND_UNLOCK_LEVEL;
            };
            messageService.send(player, "ability.locked", Map.of(
                "ability", abilityType.name().toLowerCase(),
                "level", String.valueOf(requiredLevel),
                "current", String.valueOf(profile.getSoulLevel())
            ));
            return true;
        }

        // Execute soul-specific ability
        executeAbility(player, profile, abilitySoul, args);
        return true;
    }

    /**
     * Execute the ability for the player's current soul.
     */
    protected abstract void executeAbility(Player player, PlayerProfile profile, String abilitySoul, String[] args);

    private String resolveAbilitySoul(PlayerProfile profile, boolean adminSoul) {
        if (!adminSoul) {
            return profile.getSoulId();
        }
        String override = profile.getAdminAbilitySoul();
        if (override == null || override.isBlank()) {
            return null;
        }
        return override.toLowerCase();
    }
}
