package dev.soulsmp.wrath;

import java.util.UUID;

final class WrathPlayerState {
    private final UUID playerId;
    private int heat;
    private long lastCombatMillis;
    private long lastDecayMillis;
    private long lastCombatAwardMillis;

    WrathPlayerState(UUID playerId) {
        this.playerId = playerId;
        this.heat = 0;
        long now = System.currentTimeMillis();
        this.lastCombatMillis = now;
        this.lastDecayMillis = now;
        this.lastCombatAwardMillis = now;
    }

    UUID getPlayerId() {
        return playerId;
    }

    int getHeat() {
        return heat;
    }

    void setHeat(int heat) {
        this.heat = Math.max(0, Math.min(100, heat));
    }

    void addHeat(int delta) {
        if (delta == 0) {
            return;
        }
        setHeat(this.heat + delta);
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
