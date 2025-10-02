package dev.soulsmp.core.cooldown;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CooldownManager {

    private final Map<UUID, Map<String, Long>> cooldowns = new ConcurrentHashMap<>();

    public boolean isOnCooldown(UUID playerId, String abilityId) {
        Map<String, Long> playerCooldowns = cooldowns.get(playerId);
        if (playerCooldowns == null) {
            return false;
        }
        Long expiresAt = playerCooldowns.get(abilityId);
        if (expiresAt == null) {
            return false;
        }
        if (expiresAt <= System.currentTimeMillis()) {
            playerCooldowns.remove(abilityId);
            return false;
        }
        return true;
    }

    public Duration getRemaining(UUID playerId, String abilityId) {
        Map<String, Long> playerCooldowns = cooldowns.get(playerId);
        if (playerCooldowns == null) {
            return Duration.ZERO;
        }
        Long expiresAt = playerCooldowns.get(abilityId);
        if (expiresAt == null) {
            return Duration.ZERO;
        }
        long remainingMillis = expiresAt - System.currentTimeMillis();
        return remainingMillis > 0 ? Duration.ofMillis(remainingMillis) : Duration.ZERO;
    }

    public void setCooldown(UUID playerId, String abilityId, Duration duration) {
        cooldowns.computeIfAbsent(playerId, id -> new ConcurrentHashMap<>())
            .put(abilityId, System.currentTimeMillis() + duration.toMillis());
    }

    public void clear(UUID playerId, String abilityId) {
        Map<String, Long> playerCooldowns = cooldowns.get(playerId);
        if (playerCooldowns != null) {
            playerCooldowns.remove(abilityId);
        }
    }

    public void clearAll(UUID playerId) {
        cooldowns.remove(playerId);
    }

    public void clearAll() {
        cooldowns.clear();
    }
}
