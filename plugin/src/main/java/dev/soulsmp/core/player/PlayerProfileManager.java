package dev.soulsmp.core.player;

import dev.soulsmp.core.player.storage.ProfileStorage;
import dev.soulsmp.core.resource.ResourceManager;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public final class PlayerProfileManager {

    private final ProfileStorage storage;
    private final Map<UUID, PlayerProfile> profiles = new ConcurrentHashMap<>();
    private final Logger logger;
    private ResourceManager resourceManager;

    public PlayerProfileManager(ProfileStorage storage, Logger logger) {
        this.storage = storage;
        this.logger = logger;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public PlayerProfile getOrCreate(UUID playerId) {
        return profiles.computeIfAbsent(playerId, this::loadProfile);
    }

    public Optional<PlayerProfile> get(UUID playerId) {
        return Optional.ofNullable(profiles.get(playerId));
    }

    public void save(UUID playerId) {
        profiles.computeIfPresent(playerId, (id, profile) -> {
            try {
                storage.save(profile);
            } catch (IOException ex) {
                logger.severe("Failed to save profile for " + id + ": " + ex.getMessage());
            }
            return profile;
        });
    }

    public Collection<PlayerProfile> allProfiles() {
        return profiles.values();
    }

    public void unload(UUID playerId) {
        PlayerProfile profile = profiles.remove(playerId);
        if (profile != null) {
            try {
                storage.save(profile);
            } catch (IOException ex) {
                logger.severe("Failed to save profile for " + playerId + ": " + ex.getMessage());
            }
        }
    }

    public void saveAll() {
        profiles.values().forEach(profile -> {
            try {
                storage.save(profile);
            } catch (IOException ex) {
                logger.severe("Failed to save profile for " + profile.getPlayerId() + ": " + ex.getMessage());
            }
        });
    }

    private PlayerProfile loadProfile(UUID playerId) {
        PlayerProfile profile;
        try {
            profile = storage.load(playerId).orElseGet(() -> new PlayerProfile(playerId));
        } catch (IOException ex) {
            logger.severe("Failed to load profile for " + playerId + ": " + ex.getMessage());
            profile = new PlayerProfile(playerId);
        }

        if (profile.getPlayerId() == null) {
            profile.setPlayerId(playerId);
        }

        if (resourceManager != null) {
            resourceManager.initializeProfile(profile);
        }

        return profile;
    }
}
