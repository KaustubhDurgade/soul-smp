package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.resource.ResourceDefinition;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.resource.ResourceTickBus;
import dev.soulsmp.core.resource.ResourceTickHandler;
import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

final class WrathHeatManager implements ResourceTickHandler {
    private static final long DECAY_WINDOW_MILLIS = 10_000L;
    private static final long COMBAT_AWARD_WINDOW_MILLIS = 5_000L;

    private final SoulSMPPlugin plugin;
    private final WrathAbilityManager abilityManager;
    private final ResourceManager resourceManager;
    private final TelemetryService telemetryService;
    private final Map<UUID, WrathPlayerState> states = new ConcurrentHashMap<>();

    private ResourceTickBus tickBus;
    private ResourceDefinition heatDefinition;

    WrathHeatManager(SoulSMPPlugin plugin,
                     WrathAbilityManager abilityManager,
                     ResourceManager resourceManager,
                     TelemetryService telemetryService) {
        this.plugin = plugin;
        this.abilityManager = abilityManager;
        this.resourceManager = resourceManager;
        this.telemetryService = telemetryService;
    }

    void start(ResourceTickBus tickBus, ResourceDefinition definition) {
        this.tickBus = tickBus;
        this.heatDefinition = definition;
        if (definition == null) {
            plugin.getLogger().warning("Heat definition missing; resource tick bus will not start.");
            return;
        }
        tickBus.bind(definition, this);
    }

    void stop() {
        if (tickBus != null && heatDefinition != null) {
            tickBus.unbind(heatDefinition.id());
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
        if (heatDefinition == null) {
            return 0;
        }
        return resourceManager.get(playerId, heatDefinition.id());
    }

    void addHeat(Player player, int amount) {
        if (amount == 0) {
            return;
        }
        if (heatDefinition == null) {
            return;
        }
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        resourceManager.modify(player.getUniqueId(), heatDefinition.id(), amount);
        state.recordCombat(System.currentTimeMillis());
        telemetryService.recordEvent("resource.heat.gain", amount);
    }

    void purgeHeat(Player player, int amount) {
        if (amount <= 0) {
            return;
        }
        if (heatDefinition == null) {
            return;
        }
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        resourceManager.modify(player.getUniqueId(), heatDefinition.id(), -amount);
        telemetryService.recordEvent("resource.heat.purge", amount);
    }

    void recordCombat(Player player) {
        WrathPlayerState state = this.states.computeIfAbsent(player.getUniqueId(), WrathPlayerState::new);
        state.recordCombat(System.currentTimeMillis());
        telemetryService.recordEvent("combat.wrath.combat-tick");
    }

    @Override
    public void onPassiveTick(PlayerProfile profile, ResourceDefinition definition, ResourceManager manager) {
        if (heatDefinition == null) {
            return;
        }
        if (!isHeatDefinition(definition) || profile.getPlayerId() == null) {
            return;
        }
        UUID playerId = profile.getPlayerId();
        if (!abilityManager.isPlayerActive(playerId)) {
            return;
        }

        WrathPlayerState state = states.computeIfAbsent(playerId, WrathPlayerState::new);
        long now = System.currentTimeMillis();
        long millisSinceCombat = now - state.getLastCombatMillis();

        if (millisSinceCombat >= DECAY_WINDOW_MILLIS && now - state.getLastDecayMillis() >= DECAY_WINDOW_MILLIS) {
            int newValue = manager.modify(playerId, definition.id(), -1);
            state.setLastDecayMillis(now);
            telemetryService.recordEvent("resource.heat.decay");
            if (newValue <= definition.min()) {
                telemetryService.recordEvent("resource.heat.at-minimum");
            }
        }
    }

    @Override
    public void onCombatTick(PlayerProfile profile, ResourceDefinition definition, ResourceManager manager) {
        if (heatDefinition == null) {
            return;
        }
        if (!isHeatDefinition(definition) || profile.getPlayerId() == null) {
            return;
        }
        UUID playerId = profile.getPlayerId();
        if (!abilityManager.isPlayerActive(playerId)) {
            return;
        }
        WrathPlayerState state = states.computeIfAbsent(playerId, WrathPlayerState::new);
        long now = System.currentTimeMillis();
        long millisSinceCombat = now - state.getLastCombatMillis();

        if (millisSinceCombat < COMBAT_AWARD_WINDOW_MILLIS && now - state.getLastCombatAwardMillis() >= COMBAT_AWARD_WINDOW_MILLIS) {
            int newValue = manager.modify(playerId, definition.id(), 1);
            state.setLastCombatAwardMillis(now);
            telemetryService.recordEvent("resource.heat.combat-award");
            if (newValue >= definition.max()) {
                telemetryService.recordEvent("resource.heat.at-maximum");
            }
        }
    }

    private boolean isHeatDefinition(ResourceDefinition definition) {
        return heatDefinition != null && heatDefinition.id().equals(definition.id());
    }
}
