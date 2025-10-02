package dev.soulsmp;

import dev.soulsmp.core.command.SoulsCommand;
import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.listener.CorePlayerListener;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.player.storage.ProfileStorage;
import dev.soulsmp.core.player.storage.YamlProfileStorage;
import dev.soulsmp.core.resource.ResourceDefinition;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.task.TaskScheduler;
import dev.soulsmp.wrath.WrathAbilityManager;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class SoulSMPPlugin extends JavaPlugin {

    private ConfigManager configManager;
    private MessageService messageService;
    private PlayerProfileManager profileManager;
    private ResourceManager resourceManager;
    private TaskScheduler taskScheduler;
    private WrathAbilityManager wrathAbilityManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.configManager.load();

        this.messageService = new MessageService(this);
        try {
            this.messageService.load(Locale.ENGLISH);
        } catch (Exception ex) {
            getLogger().severe("Failed to load messages: " + ex.getMessage());
        }

        ProfileStorage storage = new YamlProfileStorage(this);
        this.profileManager = new PlayerProfileManager(storage, getLogger());
        this.resourceManager = new ResourceManager(this.profileManager);
        this.profileManager.setResourceManager(this.resourceManager);

        registerDefaultResources();

        this.taskScheduler = new TaskScheduler(this);

        registerCoreListeners();
        registerCommands();

        this.wrathAbilityManager = new WrathAbilityManager(this);
        this.wrathAbilityManager.register();
    }

    @Override
    public void onDisable() {
        if (this.wrathAbilityManager != null) {
            this.wrathAbilityManager.unregister();
        }
        if (this.taskScheduler != null) {
            this.taskScheduler.cancelAll();
        }
        if (this.profileManager != null) {
            this.profileManager.saveAll();
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

    private void registerDefaultResources() {
        ConfigManager.HeatConfig heat = configManager.heat();
        resourceManager.register(ResourceDefinition.builder("heat")
            .min(heat.min())
            .max(heat.max())
            .starting(heat.starting())
            .decayIntervalSeconds(heat.decayIntervalSeconds())
            .combatTickSeconds(heat.combatTickSeconds())
            .build());
    }

    private void registerCoreListeners() {
        Bukkit.getPluginManager().registerEvents(new CorePlayerListener(profileManager, resourceManager), this);
    }

    private void registerCommands() {
        PluginCommand soulsCommand = getCommand("souls");
        if (soulsCommand != null) {
            SoulsCommand handler = new SoulsCommand(configManager, messageService, this::onReload);
            soulsCommand.setExecutor(handler);
            soulsCommand.setTabCompleter(handler);
        } else {
            getLogger().warning("Souls command is missing from plugin.yml");
        }
    }

    private void onReload() {
        resourceManager.clearDefinitions();
        registerDefaultResources();
        profileManager.allProfiles().forEach(resourceManager::applyDefinitions);
    }
}
