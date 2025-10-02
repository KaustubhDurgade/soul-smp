package dev.soulsmp;

import dev.soulsmp.core.bootstrap.ServiceRegistry;
import dev.soulsmp.core.command.SoulsCommand;
import dev.soulsmp.core.combat.CombatService;
import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.cooldown.CooldownManager;
import dev.soulsmp.core.debug.DebugService;
import dev.soulsmp.core.effect.EffectManager;
import dev.soulsmp.core.effect.EffectTypes;
import dev.soulsmp.core.listener.CorePlayerListener;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.metrics.MetricsService;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.player.migration.ProfileMigrationService;
import dev.soulsmp.core.player.storage.ProfileStorage;
import dev.soulsmp.core.player.storage.YamlProfileStorage;
import dev.soulsmp.core.resource.ResourceDefinition;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.resource.ResourceTickBus;
import dev.soulsmp.core.soul.SoulCatalog;
import dev.soulsmp.core.task.TaskScheduler;
import dev.soulsmp.core.telemetry.TelemetryService;
import dev.soulsmp.core.ui.UIDisplayService;
import dev.soulsmp.wrath.WrathAbilityManager;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class SoulSMPPlugin extends JavaPlugin {

    private ServiceRegistry services;
    private ConfigManager configManager;
    private MessageService messageService;
    private PlayerProfileManager profileManager;
    private ResourceManager resourceManager;
    private TaskScheduler taskScheduler;
    private ResourceTickBus resourceTickBus;
    private TelemetryService telemetryService;
    private MetricsService metricsService;
    private CombatService combatService;
    private EffectManager effectManager;
    private SoulCatalog soulCatalog;
    private DebugService debugService;
    private CooldownManager cooldownManager;
    private UIDisplayService uiDisplayService;
    private WrathAbilityManager wrathAbilityManager;

    @Override
    public void onEnable() {
        this.services = new ServiceRegistry();

        try {
            bootstrapConfig();
            bootstrapCoreServices();
            bootstrapRuntimeSystems();
            bootstrapUtilityServices();
            registerCoreListeners();
            registerCommands();

            this.wrathAbilityManager = new WrathAbilityManager(this, services);
            this.wrathAbilityManager.register();
        } catch (Exception ex) {
            getLogger().severe("Failed to initialize Soul SMP plugin: " + ex.getMessage());
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        if (wrathAbilityManager != null) {
            wrathAbilityManager.unregister();
        }
        if (uiDisplayService != null) {
            uiDisplayService.stop();
        }
        if (combatService != null) {
            combatService.stop();
        }
        if (effectManager != null) {
            effectManager.stop();
        }
        if (metricsService != null) {
            metricsService.stop();
        }
        if (telemetryService != null) {
            telemetryService.shutdown();
        }
        if (resourceTickBus != null) {
            resourceTickBus.clear();
        }
        if (taskScheduler != null) {
            taskScheduler.cancelAll();
        }
        if (profileManager != null) {
            profileManager.saveAll();
        }
        if (services != null) {
            services.clear();
        }
    }

    public ConfigManager configManager() {
        return configManager;
    }

    public MessageService messageService() {
        return messageService;
    }

    public PlayerProfileManager profileManager() {
        return profileManager;
    }

    public ResourceManager resourceManager() {
        return resourceManager;
    }

    public TaskScheduler taskScheduler() {
        return taskScheduler;
    }

    private void registerCoreListeners() {
        Bukkit.getPluginManager().registerEvents(
            new CorePlayerListener(profileManager, resourceManager, uiDisplayService), this);
    }

    private void registerCommands() {
        PluginCommand soulsCommand = getCommand("souls");
        if (soulsCommand != null) {
            SoulsCommand handler = new SoulsCommand(configManager, messageService, profileManager, resourceManager,
                soulCatalog, cooldownManager, telemetryService, metricsService, debugService, this::onReload);
            soulsCommand.setExecutor(handler);
            soulsCommand.setTabCompleter(handler);
        } else {
            getLogger().warning("Souls command is missing from plugin.yml");
        }
    }

    private void onReload() {
        resourceManager.clearDefinitions();
        if (resourceTickBus != null) {
            resourceTickBus.clear();
        }
        registerDefaultResources();
        profileManager.allProfiles().forEach(resourceManager::applyDefinitions);

        telemetryService.configure(new TelemetryService.TelemetrySettings(configManager.telemetry().enabled(),
            configManager.telemetry().flushIntervalSeconds()));

        metricsService.start(new MetricsService.MetricsSettings(configManager.metrics().enabled(),
            configManager.metrics().reportIntervalMinutes()));

        combatService.start(new CombatService.CombatSettings(true, configManager.combat().throttleWindowMillis()));

        soulCatalog.reload();

        if (wrathAbilityManager != null) {
            wrathAbilityManager.unregister();
            wrathAbilityManager.register();
        }
    }

    private void bootstrapConfig() {
        this.configManager = new ConfigManager(this);
        this.configManager.load();
        this.services.register(ConfigManager.class, configManager);

        this.messageService = new MessageService(this);
        try {
            this.messageService.load(Locale.ENGLISH);
        } catch (Exception ex) {
            getLogger().severe("Failed to load messages: " + ex.getMessage());
        }
        this.services.register(MessageService.class, messageService);
    }

    private void bootstrapCoreServices() {
        this.taskScheduler = new TaskScheduler(this);
        services.register(TaskScheduler.class, taskScheduler);

        ProfileMigrationService migrationService = new ProfileMigrationService();
        services.register(ProfileMigrationService.class, migrationService);

        ProfileStorage storage = new YamlProfileStorage(this);
        this.profileManager = new PlayerProfileManager(storage, getLogger(), migrationService);
        services.register(PlayerProfileManager.class, profileManager);

        this.resourceManager = new ResourceManager(profileManager);
        profileManager.setResourceManager(resourceManager);
        services.register(ResourceManager.class, resourceManager);

        this.resourceTickBus = new ResourceTickBus(taskScheduler, profileManager, resourceManager);
        services.register(ResourceTickBus.class, resourceTickBus);

        registerDefaultResources();
    }

    private void bootstrapRuntimeSystems() {
        this.telemetryService = new TelemetryService(getLogger(), taskScheduler);
        telemetryService.configure(new TelemetryService.TelemetrySettings(configManager.telemetry().enabled(),
            configManager.telemetry().flushIntervalSeconds()));
        services.register(TelemetryService.class, telemetryService);

        this.metricsService = new MetricsService(getLogger(), configManager, profileManager, resourceManager, taskScheduler);
        metricsService.start(new MetricsService.MetricsSettings(configManager.metrics().enabled(),
            configManager.metrics().reportIntervalMinutes()));
        services.register(MetricsService.class, metricsService);

        this.combatService = new CombatService(this, telemetryService);
        combatService.start(new CombatService.CombatSettings(true, configManager.combat().throttleWindowMillis()));
        services.register(CombatService.class, combatService);

        this.effectManager = new EffectManager(taskScheduler);
        effectManager.start();
        EffectTypes.registerDefaults(effectManager);
        services.register(EffectManager.class, effectManager);
    }

    private void bootstrapUtilityServices() {
        this.soulCatalog = new SoulCatalog(configManager);
        services.register(SoulCatalog.class, soulCatalog);

        this.debugService = new DebugService();
        services.register(DebugService.class, debugService);

        this.cooldownManager = new CooldownManager();
        services.register(CooldownManager.class, cooldownManager);

        this.uiDisplayService = new UIDisplayService(this, profileManager, resourceManager, cooldownManager);
        uiDisplayService.start();
        services.register(UIDisplayService.class, uiDisplayService);
    }

    private void registerDefaultResources() {
        ConfigManager.HeatConfig heat = configManager.heat();
        ResourceDefinition definition = ResourceDefinition.builder("heat")
            .min(heat.min())
            .max(heat.max())
            .starting(heat.starting())
            .decayIntervalSeconds(heat.decayIntervalSeconds())
            .combatTickSeconds(heat.combatTickSeconds())
            .build();
        resourceManager.register(definition);
    }
}
