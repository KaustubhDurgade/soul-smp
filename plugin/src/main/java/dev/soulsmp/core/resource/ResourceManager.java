package dev.soulsmp.core.resource;

import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ResourceManager {

    private final PlayerProfileManager profileManager;
    private final Map<String, ResourceDefinition> definitions = new ConcurrentHashMap<>();

    public ResourceManager(PlayerProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public void register(ResourceDefinition definition) {
        definitions.put(definition.id(), definition);
    }

    public void clearDefinitions() {
        definitions.clear();
    }

    public Optional<ResourceDefinition> definition(String id) {
        return Optional.ofNullable(definitions.get(id));
    }

    public void initializeProfile(PlayerProfile profile) {
        definitions.values().forEach(definition -> {
            if (!profile.getResourceValues().containsKey(definition.id())) {
                profile.setResource(definition.id(), definition.starting());
            }
        });
    }

    public void applyDefinitions(PlayerProfile profile) {
        definitions.values().forEach(definition -> {
            int current = profile.getResource(definition.id());
            int clamped = clamp(definition, current);
            profile.setResource(definition.id(), clamped);
        });
    }

    public void refreshProfiles(Collection<PlayerProfile> profiles) {
        profiles.forEach(this::applyDefinitions);
    }

    public int get(UUID playerId, String resourceId) {
        PlayerProfile profile = profileManager.getOrCreate(playerId);
        return profile.getResource(resourceId);
    }

    public void set(UUID playerId, String resourceId, int value) {
        PlayerProfile profile = profileManager.getOrCreate(playerId);
        int clamped = clamp(resourceId, value);
        profile.setResource(resourceId, clamped);
    }

    public int modify(UUID playerId, String resourceId, int delta) {
        PlayerProfile profile = profileManager.getOrCreate(playerId);
        int current = profile.getResource(resourceId);
        int clamped = clamp(resourceId, current + delta);
        profile.setResource(resourceId, clamped);
        return clamped;
    }

    private int clamp(String resourceId, int value) {
        ResourceDefinition definition = definitions.get(resourceId);
        return clamp(definition, value);
    }

    private int clamp(ResourceDefinition definition, int value) {
        if (definition == null) {
            return value;
        }
        if (value < definition.min()) {
            return definition.min();
        }
        if (value > definition.max()) {
            return definition.max();
        }
        return value;
    }
}
