package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

final class WrathIgnitionManager {
    private static final long DURATION_MILLIS = 6_000L;
    private static final long COOLDOWN_MILLIS = 16_000L;
    private static final long UNIQUE_HIT_INTERNAL_MILLIS = 500L;

    private final SoulSMPPlugin plugin;
    private final WrathHeatManager heatManager;
    private final MessageService messageService;
    private final TelemetryService telemetryService;

    private final Map<UUID, IgnitionState> active = new HashMap<>();
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    private BukkitTask ticker;

    WrathIgnitionManager(SoulSMPPlugin plugin,
                         WrathHeatManager heatManager,
                         MessageService messageService,
                         TelemetryService telemetryService) {
        this.plugin = plugin;
        this.heatManager = heatManager;
        this.messageService = messageService;
        this.telemetryService = telemetryService;
    }

    void start() {
        if (this.ticker != null) {
            return;
        }
        this.ticker = Bukkit.getScheduler().runTaskTimer(this.plugin, this::tick, 10L, 10L);
    }

    void stop() {
        if (this.ticker != null) {
            this.ticker.cancel();
            this.ticker = null;
        }
        this.active.clear();
        this.cooldowns.clear();
    }

    boolean activate(Player player) {
        UUID id = player.getUniqueId();
        long now = System.currentTimeMillis();
        Long cooldownUntil = this.cooldowns.get(id);
        if (cooldownUntil != null && cooldownUntil > now) {
            long seconds = (cooldownUntil - now + 999) / 1000;
            messageService.send(player, "wrath.ignition.cooldown", java.util.Map.of("seconds", Long.toString(seconds)));
            return false;
        }
        if (this.active.containsKey(id)) {
            messageService.send(player, "wrath.ignition.active");
            return false;
        }
        IgnitionState state = new IgnitionState(now + DURATION_MILLIS);
        this.active.put(id, state);
        this.cooldowns.put(id, now + COOLDOWN_MILLIS);
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.2f);
        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(0, 1, 0), 40, 0.3, 0.6, 0.3, 0.02);
        messageService.send(player, "wrath.ignition.armed");
        telemetryService.recordEvent("wrath.ignition.activate");
        return true;
    }

    boolean isActive(Player player) {
        return this.active.containsKey(player.getUniqueId());
    }

    void handleAttack(Player player, LivingEntity victim) {
        UUID id = player.getUniqueId();
        IgnitionState state = this.active.get(id);
        if (state == null) {
            return;
        }

        long now = System.currentTimeMillis();
        if (state.expiryMillis < now) {
            this.active.remove(id);
            return;
        }

        state.totalHits++;
        victim.setFireTicks(Math.max(victim.getFireTicks(), 40) + 40);
        victim.getWorld().spawnParticle(Particle.SMALL_FLAME, victim.getLocation().add(0, 1, 0), 20, 0.3, 0.4, 0.3, 0.01);
        victim.getWorld().playSound(victim.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 0.6f, 1.1f);

        if (state.registerUniqueTarget(victim.getUniqueId(), now)) {
            this.heatManager.addHeat(player, 2);
            telemetryService.recordEvent("wrath.ignition.unique-hit");
        }

        if (state.totalHits >= 5 && !state.hasPurgedHeat) {
            state.hasPurgedHeat = true;
            this.heatManager.purgeHeat(player, 10);
            victim.getWorld().spawnParticle(Particle.EXPLOSION, victim.getLocation(), 20, 0.6, 0.6, 0.6, 0.02);
            victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0f, 0.9f);
            telemetryService.recordEvent("wrath.ignition.heat-purge");
        }
    }

    private void tick() {
        long now = System.currentTimeMillis();
        this.active.entrySet().removeIf(entry -> entry.getValue().expiryMillis < now);
        this.cooldowns.entrySet().removeIf(entry -> entry.getValue() < now);
    }

    private static final class IgnitionState {
        private final long expiryMillis;
        private final Set<UUID> uniqueTargets = new HashSet<>();
        private long lastUniqueHitMillis;
        private int totalHits;
        private boolean hasPurgedHeat;

        private IgnitionState(long expiryMillis) {
            this.expiryMillis = expiryMillis;
        }

        private boolean registerUniqueTarget(UUID targetId, long now) {
            if (uniqueTargets.add(targetId)) {
                lastUniqueHitMillis = now;
                return true;
            }
            if (now - lastUniqueHitMillis >= UNIQUE_HIT_INTERNAL_MILLIS) {
                lastUniqueHitMillis = now;
                return true;
            }
            return false;
        }
    }
}
