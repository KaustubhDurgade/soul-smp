package dev.soulsmp.core.config;

import dev.soulsmp.SoulSMPPlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public MetricsConfig metrics() {
        FileConfiguration config = getConfiguration();
        boolean enabled = config.getBoolean("metrics.enabled", true);
        int interval = config.getInt("metrics.report-interval-minutes", 5);
        return new MetricsConfig(enabled, interval);
    }

    public TelemetryConfig telemetry() {
        FileConfiguration config = getConfiguration();
        boolean enabled = config.getBoolean("telemetry.enabled", true);
        int seconds = config.getInt("telemetry.flush-interval-seconds", 300);
        return new TelemetryConfig(enabled, seconds);
    }

    public CombatConfig combat() {
        FileConfiguration config = getConfiguration();
        long throttleWindow = config.getLong("combat.throttle-window-millis", 200L);
        return new CombatConfig(throttleWindow);
    }

    public SoulsConfig souls() {
        FileConfiguration config = getConfiguration();
        boolean allowRespec = config.getBoolean("souls.allow-respec", true);
        List<Map<?, ?>> rawCatalog = config.getMapList("souls.catalog");
        List<SoulEntry> entries = new ArrayList<>();
        for (Map<?, ?> raw : rawCatalog) {
            if (raw == null) {
                continue;
            }
            String id = stringValue(raw.get("id"));
            String name = stringValue(raw.get("name"));
            String description = stringValue(raw.get("description"));
            String difficulty = stringValue(raw.get("difficulty"));
            List<String> paths = new ArrayList<>();
            Object rawPaths = raw.get("paths");
            if (rawPaths instanceof List<?> list) {
                for (Object pathObj : list) {
                    String pathId = stringValue(pathObj);
                    if (!pathId.isEmpty()) {
                        paths.add(pathId);
                    }
                }
            }
            if (id != null && !id.isBlank()) {
                entries.add(new SoulEntry(id, name, description, difficulty, List.copyOf(paths)));
            }
        }
        return new SoulsConfig(allowRespec, List.copyOf(entries));
    }

    private String stringValue(Object value) {
        return value == null ? null : value.toString();
    }

    public int persistenceSchemaVersion() {
        return getConfiguration().getInt("persistence.schema-version", 1);
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

    public record MetricsConfig(boolean enabled, int reportIntervalMinutes) {
    }

    public record TelemetryConfig(boolean enabled, int flushIntervalSeconds) {
    }

    public record CombatConfig(long throttleWindowMillis) {
    }

    public record SoulsConfig(boolean allowRespec, List<SoulEntry> catalog) {
    }

    public record SoulEntry(String id, String name, String description, String difficulty, List<String> paths) {
    }
}
