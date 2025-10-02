package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.admin.AdminSoulManager;
import dev.soulsmp.core.bootstrap.ServiceRegistry;
import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.cooldown.CooldownManager;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.resource.ResourceDefinition;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.resource.ResourceTickBus;
import dev.soulsmp.core.telemetry.TelemetryService;
import dev.soulsmp.core.ui.UIDisplayService;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class WrathAbilityManager {
    private final SoulSMPPlugin plugin;
    private final Set<UUID> activePlayers = Collections.synchronizedSet(new HashSet<>());
    private final MessageService messageService;
    private final ResourceManager resourceManager;
    private final ResourceTickBus resourceTickBus;
    private final TelemetryService telemetryService;
    private final ConfigManager configManager;
    private final UIDisplayService uiDisplayService;
    private final CooldownManager cooldownManager;
    private final AdminSoulManager adminSoulManager;

    private final WrathHeatManager heatManager;
    private final WrathIgnitionManager ignitionManager;
    private final WrathChainManager chainManager;
    private final WrathMeteorManager meteorManager;
    private final WrathListener listener;
    private final WrathCommand command;

    public WrathAbilityManager(SoulSMPPlugin plugin, ServiceRegistry services) {
        this.plugin = plugin;
        this.messageService = services.require(MessageService.class);
        this.resourceManager = services.require(ResourceManager.class);
        this.resourceTickBus = services.require(ResourceTickBus.class);
        this.telemetryService = services.require(TelemetryService.class);
        this.configManager = services.require(ConfigManager.class);
    this.uiDisplayService = services.require(UIDisplayService.class);
    this.cooldownManager = services.require(CooldownManager.class);
    this.adminSoulManager = services.get(AdminSoulManager.class).orElse(null);

    this.heatManager = new WrathHeatManager(plugin, this, resourceManager, telemetryService);
    this.ignitionManager = new WrathIgnitionManager(plugin, this, this.heatManager, messageService, telemetryService);
    this.chainManager = new WrathChainManager(plugin, this, this.heatManager, messageService, telemetryService);
    this.meteorManager = new WrathMeteorManager(plugin, this, this.heatManager, messageService, telemetryService);
        this.listener = new WrathListener(this, this.heatManager, this.ignitionManager, messageService);
        this.command = new WrathCommand(this, this.ignitionManager, this.chainManager, this.meteorManager, this.heatManager, messageService, resourceManager);
    }

    public void register() {
        PluginCommand command = plugin.getCommand("wrath");
        if (command != null) {
            command.setExecutor(this.command);
            command.setTabCompleter(this.command);
        } else {
            plugin.getLogger().warning("/wrath command missing from plugin.yml");
        }

        Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);

        ResourceDefinition heatDefinition = resourceManager.definition("heat")
            .orElseGet(() -> {
                ConfigManager.HeatConfig heat = configManager.heat();
                ResourceDefinition definition = ResourceDefinition.builder("heat")
                    .min(heat.min())
                    .max(heat.max())
                    .starting(heat.starting())
                    .decayIntervalSeconds(heat.decayIntervalSeconds())
                    .combatTickSeconds(heat.combatTickSeconds())
                    .build();
                resourceManager.register(definition);
                return definition;
            });

        this.heatManager.start(resourceTickBus, heatDefinition);
        this.ignitionManager.start();
    }

    public void unregister() {
        HandlerList.unregisterAll(this.listener);
        this.heatManager.stop();
        this.ignitionManager.stop();
        this.activePlayers.clear();
    }

    public void addPlayer(Player player) {
        this.activePlayers.add(player.getUniqueId());
        this.heatManager.ensureState(player);
        
        // Enable UI display for Heat resource
        this.uiDisplayService.setPrimaryResource(player.getUniqueId(), "heat");
    }

    public void removePlayer(Player player) {
        this.activePlayers.remove(player.getUniqueId());
        this.heatManager.removeState(player.getUniqueId());
        this.chainManager.cleanup(player);
        this.meteorManager.cleanup(player);
        this.cooldownManager.clear(player.getUniqueId(), "tactical");
        this.cooldownManager.clear(player.getUniqueId(), "movement");
        this.cooldownManager.clear(player.getUniqueId(), "ultimate");
    }

    public boolean hasPlayer(Player player) {
        return this.activePlayers.contains(player.getUniqueId());
    }

    public Collection<UUID> getActivePlayers() {
        return Set.copyOf(this.activePlayers);
    }

    public boolean isPlayerActive(UUID playerId) {
        return this.activePlayers.contains(playerId);
    }

    CooldownManager cooldownManager() {
        return this.cooldownManager;
    }

    boolean shouldBypassCooldown(Player player) {
        return this.adminSoulManager != null && this.adminSoulManager.shouldBypassCooldown(player);
    }

    void recordCooldown(Player player, String key, java.time.Duration duration) {
        this.cooldownManager.setCooldown(player.getUniqueId(), key, duration);
    }

    void clearCooldown(Player player, String key) {
        this.cooldownManager.clear(player.getUniqueId(), key);
    }
}
