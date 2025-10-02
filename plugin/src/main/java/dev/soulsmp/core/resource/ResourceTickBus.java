package dev.soulsmp.core.resource;

import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.task.TaskScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ResourceTickBus {

    private final TaskScheduler scheduler;
    private final PlayerProfileManager profileManager;
    private final ResourceManager resourceManager;

    private final Map<String, HandlerRegistration> registrations = new ConcurrentHashMap<>();

    public ResourceTickBus(TaskScheduler scheduler,
                            PlayerProfileManager profileManager,
                            ResourceManager resourceManager) {
        this.scheduler = scheduler;
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
    }

    public void bind(ResourceDefinition definition, ResourceTickHandler handler) {
        unbind(definition.id());
    HandlerRegistration registration = new HandlerRegistration();

        long passiveTicks = secondsToTicks(definition.decayIntervalSeconds());
        if (passiveTicks > 0) {
            registration.passiveTask = scheduler.runTimer(() -> firePassive(definition, handler), passiveTicks, passiveTicks);
        }

        long combatTicks = secondsToTicks(definition.combatTickSeconds());
        if (combatTicks > 0) {
            registration.combatTask = scheduler.runTimer(() -> fireCombat(definition, handler), combatTicks, combatTicks);
        }

        registrations.put(definition.id(), registration);
    }

    public void unbind(String resourceId) {
        HandlerRegistration registration = registrations.remove(resourceId);
        if (registration != null) {
            registration.cancel();
        }
    }

    public void clear() {
        registrations.values().forEach(HandlerRegistration::cancel);
        registrations.clear();
    }

    private void firePassive(ResourceDefinition definition, ResourceTickHandler handler) {
        Collection<PlayerProfile> profiles = profileManager.allProfiles();
        for (PlayerProfile profile : profiles) {
            handler.onPassiveTick(profile, definition, resourceManager);
        }
    }

    private void fireCombat(ResourceDefinition definition, ResourceTickHandler handler) {
        Collection<PlayerProfile> profiles = profileManager.allProfiles();
        for (PlayerProfile profile : profiles) {
            handler.onCombatTick(profile, definition, resourceManager);
        }
    }

    private long secondsToTicks(int seconds) {
        if (seconds <= 0) {
            return 0L;
        }
        return seconds * 20L;
    }

    private static final class HandlerRegistration {
        private BukkitTask passiveTask;
        private BukkitTask combatTask;

        private void cancel() {
            if (passiveTask != null) {
                passiveTask.cancel();
            }
            if (combatTask != null) {
                combatTask.cancel();
            }
        }
    }
}
