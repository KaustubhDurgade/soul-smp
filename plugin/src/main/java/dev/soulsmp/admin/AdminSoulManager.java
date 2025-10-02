package dev.soulsmp.admin;

import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.soul.SoulStateService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

/**
 * Coordinates players that are using the special Admin soul. Supports configuring which
 * soul should power their abilities/passives and handles telling the runtime services about
 * any changes so cooldowns/resources line up.
 */
public final class AdminSoulManager {

	private final PlayerProfileManager profileManager;
	private final SoulStateService soulStateService;

	public AdminSoulManager(PlayerProfileManager profileManager, SoulStateService soulStateService) {
		this.profileManager = profileManager;
		this.soulStateService = soulStateService;
	}

	public boolean isAdminSoul(PlayerProfile profile) {
		return "admin".equalsIgnoreCase(normalize(profile.getSoulId()));
	}

	public boolean isAdminSoul(Player player) {
		PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
		return isAdminSoul(profile);
	}

	/**
	 * Admins ignore cooldown checks when they have an ability soul configured.
	 */
	public boolean shouldBypassCooldown(Player player) {
		PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
		if (!isAdminSoul(profile)) {
			return false;
		}
		return normalize(profile.getAdminAbilitySoul()) != null;
	}

	public void assignAdminSoul(UUID playerId) {
		PlayerProfile profile = profileManager.getOrCreate(playerId);
		profile.setSoulId("admin");
		profile.setAdminAbilitySoul(null);
		profile.setAdminPassiveSoul(null);
		profileManager.save(playerId);
		refreshIfOnline(playerId);
	}

	public void setAdminAbilitySoul(UUID playerId, String soulId) {
		PlayerProfile profile = profileManager.getOrCreate(playerId);
		profile.setAdminAbilitySoul(normalize(soulId));
		profileManager.save(playerId);
		refreshIfOnline(playerId);
	}

	public void clearAdminAbilitySoul(UUID playerId) {
		setAdminAbilitySoul(playerId, null);
	}

	public void setAdminPassiveSoul(UUID playerId, String soulId) {
		PlayerProfile profile = profileManager.getOrCreate(playerId);
		profile.setAdminPassiveSoul(normalize(soulId));
		profileManager.save(playerId);
		refreshIfOnline(playerId);
	}

	public void clearAdminPassiveSoul(UUID playerId) {
		setAdminPassiveSoul(playerId, null);
	}

	private void refreshIfOnline(UUID playerId) {
		Player player = Bukkit.getPlayer(playerId);
		if (player != null) {
			soulStateService.refresh(player);
		}
	}

	private String normalize(String soulId) {
		if (soulId == null) {
			return null;
		}
		String value = soulId.trim();
		if (value.isEmpty()) {
			return null;
		}
		return value.toLowerCase(Locale.ROOT);
	}
}
