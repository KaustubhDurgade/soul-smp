package dev.soulsmp.core.soul;

import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.wrath.WrathAbilityManager;
import org.bukkit.entity.Player;

import java.util.Locale;

/**
 * Keeps Paper side state in sync with the soul recorded on the player's profile.
 * Responsible for wiring players into ability managers when their soul (or admin override)
 * requires it and detaching them when no longer applicable.
 */
public final class SoulStateService {

    private final PlayerProfileManager profileManager;
    private final WrathAbilityManager wrathAbilityManager;

    public SoulStateService(PlayerProfileManager profileManager, WrathAbilityManager wrathAbilityManager) {
        this.profileManager = profileManager;
        this.wrathAbilityManager = wrathAbilityManager;
    }

    /**
     * Refresh the player's runtime state so it matches their profile configuration.
     */
    public void refresh(Player player) {
        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        apply(player, profile);
    }

    /**
     * Called when a player's soul assignment changes.
     */
    public void onSoulChanged(Player player, PlayerProfile profile) {
        apply(player, profile);
    }

    /**
     * Clean up runtime references for a player (usually on quit).
     */
    public void clear(Player player) {
        detachAll(player);
    }

    private void apply(Player player, PlayerProfile profile) {
        detachAll(player);

        String primarySoul = normalize(profile.getSoulId());
        String adminAbilitySoul = normalize(profile.getAdminAbilitySoul());
        String adminPassiveSoul = normalize(profile.getAdminPassiveSoul());

        if ("wrath".equals(primarySoul)
            || ("admin".equals(primarySoul) && ("wrath".equals(adminPassiveSoul) || "wrath".equals(adminAbilitySoul)))) {
            wrathAbilityManager.addPlayer(player);
        }
    }

    private void detachAll(Player player) {
        wrathAbilityManager.removePlayer(player);
    }

    private String normalize(String soulId) {
        if (soulId == null) {
            return null;
        }
        String trimmed = soulId.trim();
        return trimmed.isEmpty() ? null : trimmed.toLowerCase(Locale.ROOT);
    }
}
