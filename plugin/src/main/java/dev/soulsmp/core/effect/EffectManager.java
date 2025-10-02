package dev.soulsmp.core.effect;

import dev.soulsmp.core.task.TaskScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class EffectManager {

    private final Map<String, EffectType> types = new ConcurrentHashMap<>();
    private final Map<UUID, Map<String, ActiveEffect>> activeEffects = new ConcurrentHashMap<>();
    private final TaskScheduler scheduler;

    private BukkitTask ticker;

    public EffectManager(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void registerType(EffectType type) {
        types.put(type.id(), type);
    }

    public Optional<EffectType> getType(String id) {
        return Optional.ofNullable(types.get(id));
    }

    public void start() {
        if (ticker != null) {
            return;
        }
        ticker = scheduler.runTimer(this::tick, 20L, 20L);
    }

    public void stop() {
        if (ticker != null) {
            ticker.cancel();
            ticker = null;
        }
        activeEffects.clear();
    }

    public void apply(Player player, String effectId, int amplifier, long durationTicks) {
        EffectType type = types.get(effectId);
        if (type == null) {
            throw new IllegalArgumentException("Effect type not registered: " + effectId);
        }
        activeEffects.computeIfAbsent(player.getUniqueId(), __ -> new ConcurrentHashMap<>())
            .compute(effectId, (id, existing) -> mergeEffect(player, type, amplifier, durationTicks, existing));
    }

    public void remove(Player player, String effectId) {
        Map<String, ActiveEffect> map = activeEffects.get(player.getUniqueId());
        if (map == null) {
            return;
        }
        ActiveEffect removed = map.remove(effectId);
        if (removed != null) {
            typeApplier(effectId).ifPresent(applier -> applier.onExpire(player, removed));
        }
        if (map.isEmpty()) {
            activeEffects.remove(player.getUniqueId());
        }
    }

    public Collection<ActiveEffect> getActiveEffects(UUID playerId) {
        Map<String, ActiveEffect> map = activeEffects.get(playerId);
        return map == null ? java.util.List.of() : java.util.List.copyOf(map.values());
    }

    private ActiveEffect mergeEffect(Player player,
                                     EffectType type,
                                     int amplifier,
                                     long durationTicks,
                                     ActiveEffect existing) {
        if (existing == null) {
            ActiveEffect created = new ActiveEffect(type, clampAmplifier(type, amplifier), durationTicks);
            type.applier().onApply(player, created);
            return created;
        }

        switch (type.stackingPolicy()) {
            case NONE -> {
                return existing;
            }
            case REFRESH -> {
                existing.setAmplifier(Math.max(existing.amplifier(), clampAmplifier(type, amplifier)));
                existing.resetTicks(durationTicks);
            }
            case STACK_DURATION -> {
                existing.setAmplifier(Math.max(existing.amplifier(), clampAmplifier(type, amplifier)));
                existing.addTicks(durationTicks);
            }
            case STACK_AMPLIFIER -> {
                int newAmp = clampAmplifier(type, existing.amplifier() + amplifier);
                existing.setAmplifier(newAmp);
                existing.resetTicks(Math.max(existing.remainingTicks(), durationTicks));
            }
        }
        type.applier().onApply(player, existing);
        return existing;
    }

    private int clampAmplifier(EffectType type, int amplifier) {
        return Math.max(0, Math.min(type.maxAmplifier(), amplifier));
    }

    private void tick() {
        activeEffects.entrySet().removeIf(entry -> {
            UUID playerId = entry.getKey();
            Map<String, ActiveEffect> effects = entry.getValue();
            Player player = Bukkit.getPlayer(playerId);
            if (player == null || !player.isOnline()) {
                return false;
            }
            effects.entrySet().removeIf(effectEntry -> {
                ActiveEffect effect = effectEntry.getValue();
                boolean expired = effect.tick();
                typeApplier(effectEntry.getKey()).ifPresent(applier -> applier.onTick(player, effect));
                if (expired) {
                    typeApplier(effectEntry.getKey()).ifPresent(applier -> applier.onExpire(player, effect));
                }
                return expired;
            });
            return effects.isEmpty();
        });
    }

    private Optional<EffectApplier> typeApplier(String effectId) {
        return Optional.ofNullable(types.get(effectId)).map(EffectType::applier);
    }
}
