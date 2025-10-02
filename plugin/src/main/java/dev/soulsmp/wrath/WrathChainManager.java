package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages Inferno Chain Surge ability for Wrath soul.
 * Movement ability that chains to surfaces/enemies and provides mobility + tether damage.
 */
final class WrathChainManager {
    private static final int COOLDOWN_SECONDS = 15;
    private static final double CHAIN_RANGE = 5.0;
    private static final double LAUNCH_DISTANCE = 3.0;
    private static final int TETHER_DURATION_TICKS = 60; // 3 seconds
    private static final double TETHER_DAMAGE = 1.0;
    private static final int TETHER_SLOWNESS_AMPLIFIER = 1; // 20% slow (level 2 = 30%)
    private static final double DETONATION_DAMAGE = 6.0;
    private static final int RECAST_WINDOW_TICKS = 40; // 2 seconds
    private static final int HEAT_GAIN_FIRST_HIT = 5;

    private final SoulSMPPlugin plugin;
    private final WrathAbilityManager abilityManager;
    private final WrathHeatManager heatManager;
    private final MessageService messageService;
    private final TelemetryService telemetryService;
    
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final Map<UUID, ChainState> activeChains = new HashMap<>();

    WrathChainManager(SoulSMPPlugin plugin, WrathAbilityManager abilityManager, WrathHeatManager heatManager,
                      MessageService messageService, TelemetryService telemetryService) {
        this.plugin = plugin;
        this.abilityManager = abilityManager;
        this.heatManager = heatManager;
        this.messageService = messageService;
        this.telemetryService = telemetryService;
    }

    boolean isOnCooldown(Player player) {
        return cooldowns.getOrDefault(player.getUniqueId(), 0L) > System.currentTimeMillis();
    }

    long getCooldownRemaining(Player player) {
        long expiry = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        return Math.max(0, expiry - System.currentTimeMillis()) / 1000;
    }

    void activate(Player player) {
        boolean bypass = abilityManager.shouldBypassCooldown(player);
        if (!bypass && isOnCooldown(player)) {
            long remaining = getCooldownRemaining(player);
            messageService.send(player, "wrath.chain.cooldown", Map.of("seconds", String.valueOf(remaining)));
            return;
        }

        // Check if player is in recast window
        ChainState state = activeChains.get(player.getUniqueId());
        if (state != null && state.canRecast()) {
            performDetonation(player, state);
            return;
        }

        // Find chain target
        RayTraceResult result = player.getWorld().rayTrace(
            player.getEyeLocation(),
            player.getEyeLocation().getDirection(),
            CHAIN_RANGE,
            FluidCollisionMode.NEVER,
            true,
            0.5,
            entity -> entity instanceof LivingEntity && entity != player
        );

        if (result == null) {
            messageService.send(player, "wrath.chain.no-target");
            return;
        }

        Location targetLoc;
        Entity targetEntity = null;

        if (result.getHitEntity() != null) {
            targetEntity = result.getHitEntity();
            targetLoc = targetEntity.getLocation();
        } else {
            targetLoc = result.getHitPosition().toLocation(player.getWorld());
        }

        // Launch player
        Vector direction = targetLoc.toVector().subtract(player.getLocation().toVector()).normalize();
        Vector launchVelocity = direction.multiply(LAUNCH_DISTANCE * 0.5); // Adjust multiplier for proper distance
        launchVelocity.setY(Math.max(launchVelocity.getY(), 0.3)); // Ensure upward component
        player.setVelocity(launchVelocity);

        // Visual effects
        player.playSound(player.getLocation(), Sound.ITEM_FIRECHARGE_USE, 1.0f, 0.8f);
        drawChainEffect(player.getEyeLocation(), targetLoc);

        // Create tether if target is entity
        if (targetEntity instanceof LivingEntity living) {
            ChainState chainState = new ChainState(living);
            activeChains.put(player.getUniqueId(), chainState);
            startTether(player, chainState);

            // Heat generation on first hit
            heatManager.addHeat(player, HEAT_GAIN_FIRST_HIT);
        }

        // Set cooldown
        if (!bypass) {
            cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (COOLDOWN_SECONDS * 1000L));
            abilityManager.recordCooldown(player, "movement", java.time.Duration.ofSeconds(COOLDOWN_SECONDS));
        } else {
            abilityManager.clearCooldown(player, "movement");
        }
        telemetryService.recordEvent("wrath.chain.activated");
    }

    private void startTether(Player player, ChainState state) {
        new BukkitRunnable() {
            int ticksElapsed = 0;

            @Override
            public void run() {
                if (ticksElapsed >= TETHER_DURATION_TICKS || !state.target.isValid() || state.target.isDead()) {
                    activeChains.remove(player.getUniqueId());
                    cancel();
                    return;
                }

                // Apply tether effects
                state.target.damage(TETHER_DAMAGE, player);
                state.target.addPotionEffect(
                    new org.bukkit.potion.PotionEffect(
                        org.bukkit.potion.PotionEffectType.SLOWNESS,
                        20, // 1 second duration
                        TETHER_SLOWNESS_AMPLIFIER,
                        false,
                        true
                    )
                );

                // Visual tether
                if (ticksElapsed % 5 == 0) {
                    drawChainEffect(player.getLocation().add(0, 1, 0), state.target.getLocation().add(0, 1, 0));
                }

                ticksElapsed++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void performDetonation(Player player, ChainState state) {
        if (!state.target.isValid() || state.target.isDead()) {
            messageService.send(player, "wrath.chain.target-invalid");
            activeChains.remove(player.getUniqueId());
            return;
        }

        // Deal detonation damage
        state.target.damage(DETONATION_DAMAGE, player);
        
        // Visual explosion
        Location loc = state.target.getLocation();
        state.target.getWorld().playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.2f);
        state.target.getWorld().spawnParticle(Particle.FLAME, loc, 30, 0.5, 0.5, 0.5, 0.1);
        state.target.getWorld().spawnParticle(Particle.SMOKE, loc, 20, 0.5, 0.5, 0.5, 0.05);

        activeChains.remove(player.getUniqueId());
        telemetryService.recordEvent("wrath.chain.detonated");
        messageService.send(player, "wrath.chain.detonated");
    }

    private void drawChainEffect(Location from, Location to) {
        Vector direction = to.toVector().subtract(from.toVector());
        double distance = direction.length();
        direction.normalize();

        for (double i = 0; i < distance; i += 0.3) {
            Location particleLoc = from.clone().add(direction.clone().multiply(i));
            from.getWorld().spawnParticle(Particle.FLAME, particleLoc, 1, 0, 0, 0, 0);
        }
    }

    void cleanup(Player player) {
        cooldowns.remove(player.getUniqueId());
        activeChains.remove(player.getUniqueId());
        abilityManager.clearCooldown(player, "movement");
    }

    private static class ChainState {
        final LivingEntity target;
        final long createdAt;

        ChainState(LivingEntity target) {
            this.target = target;
            this.createdAt = System.currentTimeMillis();
        }

        boolean canRecast() {
            long elapsed = System.currentTimeMillis() - createdAt;
            return elapsed < (RECAST_WINDOW_TICKS * 50L); // Convert ticks to ms
        }
    }
}
