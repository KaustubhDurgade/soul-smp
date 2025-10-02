package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

/**
 * Manages Meteorfall ultimate ability for Wrath soul.
 * Drops 5 meteors over 3 seconds dealing AoE fire damage.
 */
final class WrathMeteorManager {
    private static final int COOLDOWN_SECONDS = 105;
    private static final int METEOR_COUNT = 5;
    private static final int DURATION_TICKS = 60; // 3 seconds
    private static final double METEOR_DAMAGE = 6.0;
    private static final double METEOR_RADIUS = 2.0;
    private static final int GROUND_FIRE_DURATION_TICKS = 60; // 3 seconds
    private static final double SINGLE_TARGET_CAP = 18.0;
    private static final int CENTER_HIT_LIMIT = 2;
    private static final double HEAT_CONSUMPTION_PERCENT = 0.40;

    private final SoulSMPPlugin plugin;
    private final WrathAbilityManager abilityManager;
    private final WrathHeatManager heatManager;
    private final MessageService messageService;
    private final TelemetryService telemetryService;
    
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final Map<UUID, MeteorStorm> activeStorms = new HashMap<>();

    WrathMeteorManager(SoulSMPPlugin plugin, WrathAbilityManager abilityManager, WrathHeatManager heatManager,
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
            messageService.send(player, "wrath.meteor.cooldown", Map.of("seconds", String.valueOf(remaining)));
            return;
        }

        if (activeStorms.containsKey(player.getUniqueId())) {
            if (!bypass) {
                messageService.send(player, "wrath.meteor.already-active");
                return;
            }
            MeteorStorm running = activeStorms.remove(player.getUniqueId());
            if (running != null) {
                running.cancel();
            }
        }

        if (bypass) {
            abilityManager.clearCooldown(player, "ultimate");
        }

        if (bypass) {
            cooldowns.remove(player.getUniqueId());
        }

        if (!bypass) {
            cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + (COOLDOWN_SECONDS * 1000L));
            abilityManager.recordCooldown(player, "ultimate", java.time.Duration.ofSeconds(COOLDOWN_SECONDS));
        }

        // Get targeting location (player's cursor or current location)
        Location targetCenter = player.getTargetBlockExact(30) != null
            ? player.getTargetBlockExact(30).getLocation()
            : player.getLocation();

        // Consume heat
        int currentHeat = heatManager.getHeat(player.getUniqueId());
        int heatToConsume = (int) (currentHeat * HEAT_CONSUMPTION_PERCENT);
        heatToConsume = (heatToConsume / 5) * 5; // Round down to nearest 5
        heatManager.addHeat(player, -heatToConsume);

        // Start meteor storm
        MeteorStorm storm = new MeteorStorm(player, targetCenter);
        activeStorms.put(player.getUniqueId(), storm);
        storm.start();

        telemetryService.recordEvent("wrath.meteor.activated");
        messageService.send(player, "wrath.meteor.activated");
    }

    void cleanup(Player player) {
        cooldowns.remove(player.getUniqueId());
        abilityManager.clearCooldown(player, "ultimate");
        MeteorStorm storm = activeStorms.remove(player.getUniqueId());
        if (storm != null) {
            storm.cancel();
        }
    }

    private class MeteorStorm extends BukkitRunnable {
        private final Player caster;
        private final Location centerLocation;
        private final Map<Entity, Double> damageDealt = new HashMap<>();
        private final Map<Location, Integer> centerHits = new HashMap<>();
        private int meteorsDropped = 0;
        private final int tickInterval;

        MeteorStorm(Player caster, Location centerLocation) {
            this.caster = caster;
            this.centerLocation = centerLocation;
            this.tickInterval = DURATION_TICKS / METEOR_COUNT;
        }

        void start() {
            runTaskTimer(plugin, 0L, tickInterval);
        }

        @Override
        public void run() {
            if (meteorsDropped >= METEOR_COUNT || !caster.isOnline()) {
                activeStorms.remove(caster.getUniqueId());
                cancel();
                return;
            }

            dropMeteor();
            meteorsDropped++;
        }

        private void dropMeteor() {
            // Random offset from center (within 5 block radius)
            double offsetX = (Math.random() - 0.5) * 10;
            double offsetZ = (Math.random() - 0.5) * 10;
            Location impactLocation = centerLocation.clone().add(offsetX, 0, offsetZ);
            
            // Find ground level
            impactLocation.setY(impactLocation.getWorld().getHighestBlockYAt(impactLocation) + 1);

            // Meteor falling animation
            Location skyLocation = impactLocation.clone().add(0, 20, 0);
            animateMeteorFall(skyLocation, impactLocation);

            // Deal damage after short delay
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                impactMeteor(impactLocation);
            }, 10L);
        }

        private void animateMeteorFall(Location from, Location to) {
            new BukkitRunnable() {
                double progress = 0;
                
                @Override
                public void run() {
                    if (progress >= 1.0) {
                        cancel();
                        return;
                    }

                    Location current = from.clone().add(
                        to.toVector().subtract(from.toVector()).multiply(progress)
                    );
                    
                    current.getWorld().spawnParticle(Particle.FLAME, current, 5, 0.2, 0.2, 0.2, 0.05);
                    current.getWorld().spawnParticle(Particle.SMOKE, current, 3, 0.1, 0.1, 0.1, 0.02);
                    
                    progress += 0.2;
                }
            }.runTaskTimer(plugin, 0L, 1L);
        }

        private void impactMeteor(Location location) {
            // Track center hits
            Location blockLoc = location.getBlock().getLocation();
            centerHits.put(blockLoc, centerHits.getOrDefault(blockLoc, 0) + 1);
            
            // Check if this location has exceeded center hit limit
            if (centerHits.get(blockLoc) > CENTER_HIT_LIMIT) {
                return; // Skip damage for this meteor
            }

            // Impact effects
            location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 2.0f, 0.8f);
            location.getWorld().spawnParticle(Particle.EXPLOSION, location, 3, 0.5, 0.5, 0.5, 0);
            location.getWorld().spawnParticle(Particle.FLAME, location, 50, 1.0, 0.5, 1.0, 0.1);
            location.getWorld().spawnParticle(Particle.LAVA, location, 20, 1.0, 0.2, 1.0, 0);

            // Deal damage to entities in radius
            Collection<Entity> nearby = location.getWorld().getNearbyEntities(location, METEOR_RADIUS, METEOR_RADIUS, METEOR_RADIUS);
            for (Entity entity : nearby) {
                if (!(entity instanceof LivingEntity living) || entity.equals(caster)) {
                    continue;
                }

                double currentDamage = damageDealt.getOrDefault(entity, 0.0);
                if (currentDamage >= SINGLE_TARGET_CAP) {
                    continue; // Skip if target has reached damage cap
                }

                double damageToApply = Math.min(METEOR_DAMAGE, SINGLE_TARGET_CAP - currentDamage);
                living.damage(damageToApply, caster);
                living.setFireTicks(GROUND_FIRE_DURATION_TICKS);
                
                damageDealt.put(entity, currentDamage + damageToApply);
                telemetryService.recordEvent("wrath.meteor.damage-dealt");
            }

            // Ground fire effect
            igniteGround(location);
        }

        private void igniteGround(Location center) {
            // Create fire blocks in small radius
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    Location fireLoc = center.clone().add(x, 0, z);
                    if (fireLoc.getBlock().getType() == Material.AIR) {
                        Location below = fireLoc.clone().add(0, -1, 0);
                        if (below.getBlock().getType().isSolid()) {
                            fireLoc.getBlock().setType(Material.FIRE);
                            
                            // Remove fire after duration
                            Location finalLoc = fireLoc.clone();
                            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                                if (finalLoc.getBlock().getType() == Material.FIRE) {
                                    finalLoc.getBlock().setType(Material.AIR);
                                }
                            }, GROUND_FIRE_DURATION_TICKS);
                        }
                    }
                }
            }
        }
    }
}
