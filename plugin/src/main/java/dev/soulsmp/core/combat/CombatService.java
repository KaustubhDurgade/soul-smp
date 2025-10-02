package dev.soulsmp.core.combat;

import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public final class CombatService implements Listener {

    private final Plugin plugin;
    private final TelemetryService telemetryService;
    private final Logger logger;

    private final EnumMap<CombatPriority, List<CombatHandler>> handlers = new EnumMap<>(CombatPriority.class);
    private final Map<String, Long> throttleIndex = new ConcurrentHashMap<>();

    private CombatSettings settings = CombatSettings.disabled();

    public CombatService(Plugin plugin, TelemetryService telemetryService) {
        this.plugin = plugin;
        this.telemetryService = telemetryService;
        this.logger = plugin.getLogger();
        for (CombatPriority priority : CombatPriority.values()) {
            handlers.put(priority, new ArrayList<>());
        }
    }

    public void start(CombatSettings settings) {
        this.settings = settings;
        HandlerList.unregisterAll(this);
        if (!settings.enabled()) {
            logger.info("Combat event pipeline disabled via configuration.");
            return;
        }
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        logger.info("Combat event pipeline initialized with throttle window " + settings.throttleWindowMillis() + "ms.");
    }

    public void stop() {
        HandlerList.unregisterAll(this);
        throttleIndex.clear();
    }

    public void registerHandler(CombatPriority priority, CombatHandler handler) {
        handlers.get(priority).add(handler);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!settings.enabled()) {
            return;
        }
        if (settings.throttleWindowMillis() > 0 && shouldThrottle(event)) {
            return;
        }
        CombatContext context = new CombatContext(event);
        dispatchDamage(context);
        event.setDamage(context.damage());
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onHeal(EntityRegainHealthEvent event) {
        if (!settings.enabled()) {
            return;
        }
        CombatHealContext context = new CombatHealContext(event);
        dispatchHeal(context);
    }

    private void dispatchDamage(CombatContext context) {
        for (CombatPriority priority : CombatPriority.values()) {
            List<CombatHandler> bucket = handlers.get(priority);
            if (bucket.isEmpty()) {
                continue;
            }
            for (CombatHandler handler : bucket) {
                handler.onDamage(context);
            }
        }

        context.attacker().ifPresent(player -> telemetryService.recordEvent("combat.damage.dealt"));
        context.victim().ifPresent(victim -> telemetryService.recordEvent("combat.damage.taken"));
    }

    private void dispatchHeal(CombatHealContext context) {
        for (CombatPriority priority : CombatPriority.values()) {
            List<CombatHandler> bucket = handlers.get(priority);
            if (bucket.isEmpty()) {
                continue;
            }
            for (CombatHandler handler : bucket) {
                handler.onHeal(context);
            }
        }
        context.target().ifPresent(entity -> telemetryService.recordEvent("combat.heal.received"));
    }

    private boolean shouldThrottle(EntityDamageByEntityEvent event) {
        if (!settings.enabled()) {
            return false;
        }
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();

        UUID damagerId = damager.getUniqueId();
        UUID victimId = victim.getUniqueId();

        long now = System.currentTimeMillis();
        String key = damagerId + ":" + victimId;
        Long last = throttleIndex.put(key, now);
        if (last == null) {
            return false;
        }
        long diff = now - last;
        if (diff < settings.throttleWindowMillis()) {
            return true;
        }
        return false;
    }

    public CombatSettings settings() {
        return settings;
    }

    public record CombatSettings(boolean enabled, long throttleWindowMillis) {
        public static CombatSettings disabled() {
            return new CombatSettings(false, 0L);
        }
    }
}
