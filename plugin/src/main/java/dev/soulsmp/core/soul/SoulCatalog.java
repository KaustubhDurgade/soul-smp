package dev.soulsmp.core.soul;

import dev.soulsmp.core.config.ConfigManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class SoulCatalog {

    private final Map<String, SoulDefinition> souls = new ConcurrentHashMap<>();
    private final ConfigManager configManager;

    public SoulCatalog(ConfigManager configManager) {
        this.configManager = configManager;
        reload();
    }

    public void reload() {
        souls.clear();
        ConfigManager.SoulsConfig soulsConfig = configManager.souls();
        for (ConfigManager.SoulEntry entry : soulsConfig.catalog()) {
            if (entry.id() == null || entry.id().isBlank()) {
                continue;
            }
            List<String> paths = new ArrayList<>(entry.paths());
            SoulDefinition definition = new SoulDefinition(entry.id(), entry.name(), entry.description(), entry.difficulty(), paths);
            souls.put(definition.id(), definition);
        }
    }

    public List<SoulDefinition> list() {
        return souls.values().stream()
            .sorted(Comparator.comparing(SoulDefinition::displayName, String.CASE_INSENSITIVE_ORDER))
            .toList();
    }

    public Optional<SoulDefinition> find(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(souls.get(id.trim().toLowerCase()));
    }

    public Map<String, SoulDefinition> asMap() {
        return Map.copyOf(souls);
    }
}
