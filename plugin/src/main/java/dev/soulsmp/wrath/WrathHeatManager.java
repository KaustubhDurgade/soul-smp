package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

final class WrathHeatManager {
    private static final long DECAY_WINDOW_MILLIS = 10_000L;
    private static final long COMBAT_AWARD_WINDOW_MILLIS = 5_000L;

    private final SoulSMPPlugin plugin;
    private final WrathAbilityManager abilityManager;
    private final Map<UUID, WrathPlayerState> states = new ConcurrentHashMap<>();

    private BukkitTask ticker;

    WrathHeatManager(SoulSMPPlugin plugin, WrathAbilityManager abilityManager) {
        this.plugin = plugin;
        this.abilityManager = abilityManager;
    }

    void start() {
        if (this.ticker != null) {
            return;
        }
        this.ticker = Bukkit.getScheduler().runTaskTimer(plugin, this::tick, 20L, 20L);
    }

    void stop() {
        if (this.ticker != null) {
            this.ticker.cancel();
            this.ticker = null;
        }
        this.states.clear();
    }

    void ensureState(Player player) {
        this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
    }

    void removeState(UUID playerId) {
        this.states.remove(playerId);
    }

    int getHeat(UUID playerId) {
        WrathPlayerState state = this.states.get(playerId);
        return state == null ? 0 : state.getHeat();
    }

    void addHeat(Player player, int amount) {
        if (amount == 0) {
            return;
        }
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        state.addHeat(amount);
        state.recordCombat(System.currentTimeMillis());
    }

    void purgeHeat(Player player, int amount) {
        if (amount <= 0) {
            return;
        }
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        state.addHeat(-amount);
    }

    void recordCombat(Player player) {
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        state.recordCombat(System.currentTimeMillis());
    }

    private void tick() {
        long now = System.currentTimeMillis();
        Collection<UUID> active = abilityManager.getActivePlayers();
        for (UUID playerId : active) {
            WrathPlayerState state = this.states.computeIfAbsent(playerId, WrathPlayerState::new);
            long millisSinceCombat = now - state.getLastCombatMillis();

            if (millisSinceCombat < COMBAT_AWARD_WINDOW_MILLIS) {
                if (now - state.getLastCombatAwardMillis() >= COMBAT_AWARD_WINDOW_MILLIS) {
                    state.setLastCombatAwardMillis(now);
                    state.addHeat(1);
                }
            }

            if (state.getHeat() <= 0) {
                continue;
            }

            if (millisSinceCombat >= DECAY_WINDOW_MILLIS) {
                if (now - state.getLastDecayMillis() >= DECAY_WINDOW_MILLIS) {
                    state.addHeat(-1);
                    state.setLastDecayMillis(now);
                }
            }
        }
    }
}
