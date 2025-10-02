package dev.soulsmp.core.config;

import dev.soulsmp.SoulSMPPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class ConfigManager {

    private final SoulSMPPlugin plugin;
    private final File configFile;
    private FileConfiguration configuration;

    public ConfigManager(SoulSMPPlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public void load() {
        if (!plugin.getDataFolder().exists() && !plugin.getDataFolder().mkdirs()) {
            plugin.getLogger().warning("Failed to create plugin data folder.");
        }
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    public void reload() throws IOException, InvalidConfigurationException {
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        YamlConfiguration fresh = new YamlConfiguration();
        fresh.load(configFile);
        this.configuration = fresh;
    }

    public FileConfiguration getConfiguration() {
        if (this.configuration == null) {
            load();
        }
        return this.configuration;
    }

    public boolean isDebugEnabled() {
        return getConfiguration().getBoolean("debug.enabled", false);
    }

    public HeatConfig heat() {
        FileConfiguration config = getConfiguration();
        int min = config.getInt("resources.heat.min", 0);
        int max = config.getInt("resources.heat.max", 100);
        int starting = config.getInt("resources.heat.starting", 0);
        int decayInterval = config.getInt("resources.heat.decay-interval-seconds", 10);
        int combatTick = config.getInt("resources.heat.combat-tick-seconds", 5);
        return new HeatConfig(min, max, starting, decayInterval, combatTick);
    }

    public static final class HeatConfig {
        private final int min;
        private final int max;
        private final int starting;
        private final int decayIntervalSeconds;
        private final int combatTickSeconds;

        private HeatConfig(int min, int max, int starting, int decayIntervalSeconds, int combatTickSeconds) {
            this.min = min;
            this.max = max;
            this.starting = starting;
            this.decayIntervalSeconds = decayIntervalSeconds;
            this.combatTickSeconds = combatTickSeconds;
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

        public int starting() {
            return starting;
        }

        public int decayIntervalSeconds() {
            return decayIntervalSeconds;
        }

        public int combatTickSeconds() {
            return combatTickSeconds;
        }
    }
}
