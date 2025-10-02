package dev.soulsmp.wrath;

import java.util.UUID;

final class WrathPlayerState {
    private final UUID playerId;
    private long lastCombatMillis;
    private long lastDecayMillis;
    private long lastCombatAwardMillis;

    WrathPlayerState(UUID playerId) {
        this.playerId = playerId;
        long now = System.currentTimeMillis();
        this.lastCombatMillis = now;
        this.lastDecayMillis = now;
        this.lastCombatAwardMillis = now;
    }

    UUID getPlayerId() {
        return playerId;
    }

    long getLastCombatMillis() {
        return lastCombatMillis;
    }

    void recordCombat(long now) {
        this.lastCombatMillis = now;
    }

    long getLastDecayMillis() {
        return lastDecayMillis;
    }

    void setLastDecayMillis(long lastDecayMillis) {
        this.lastDecayMillis = lastDecayMillis;
    }

    long getLastCombatAwardMillis() {
        return lastCombatAwardMillis;
    }

    void setLastCombatAwardMillis(long lastCombatAwardMillis) {
        this.lastCombatAwardMillis = lastCombatAwardMillis;
    }
}
