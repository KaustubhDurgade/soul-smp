package dev.soulsmp.core.soul;

import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Handles automatic soul assignment when players first join.
 */
public final class SoulAssignmentService {

    private final SoulCatalog soulCatalog;
    private final PlayerProfileManager profileManager;
    private final MessageService messageService;
    private final SoulRevealAnimationService animationService;
    private final SoulStateService soulStateService;
    private final Random random = new Random();

    public SoulAssignmentService(SoulCatalog soulCatalog,
                                 PlayerProfileManager profileManager,
                                 MessageService messageService,
                                 SoulRevealAnimationService animationService,
                                 SoulStateService soulStateService) {
        this.soulCatalog = soulCatalog;
        this.profileManager = profileManager;
        this.messageService = messageService;
        this.animationService = animationService;
        this.soulStateService = soulStateService;
    }

    /**
     * Check if player needs soul assignment and assign one randomly.
     * @return the newly assigned definition or {@code null} when no assignment happened
     */
    public SoulDefinition assignIfNeeded(Player player) {
        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        
        // Check if already has soul
        if (profile.getSoulId() != null && !profile.getSoulId().isBlank()) {
            return null;
        }

        // Get available souls
        List<SoulDefinition> availableSouls = soulCatalog.list();
        if (availableSouls.isEmpty()) {
            messageService.send(player, "soul.assignment.no-souls");
            return null;
        }

        // Pick random soul
        SoulDefinition chosen = availableSouls.get(random.nextInt(availableSouls.size()));
        profile.setSoulId(chosen.id());
        profile.setSoulLevel(0);
        profile.setSoulExperience(0);

        profileManager.save(player.getUniqueId());
        soulStateService.onSoulChanged(player, profile);

        if (animationService != null) {
            animationService.playReveal(player, chosen);
        } else {
            messageService.send(player, "soul.assignment.assigned", java.util.Map.of(
                "soul", chosen.displayName(),
                "description", chosen.description()
            ));
        }

        return chosen;
    }

    /**
     * Admin override to assign specific soul to player.
     */
    public boolean assignSoul(Player target, String soulId) {
        SoulDefinition soul = soulCatalog.find(soulId).orElse(null);
        if (soul == null) {
            return false;
        }

        PlayerProfile profile = profileManager.getOrCreate(target.getUniqueId());
        String oldSoul = profile.getSoulId();
        
        profile.setSoulId(soul.id());
        profile.setSoulLevel(0);
        profile.setSoulExperience(0);

        messageService.send(target, "soul.assignment.changed", Map.of(
            "old", oldSoul != null ? oldSoul : "none",
            "new", soul.displayName()
        ));

        profileManager.save(target.getUniqueId());
        soulStateService.onSoulChanged(target, profile);
        return true;
    }
}
