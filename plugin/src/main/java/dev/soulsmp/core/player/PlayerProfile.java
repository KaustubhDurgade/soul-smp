package dev.soulsmp.core.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerProfile {

    private UUID playerId;
    private String soulId;
    private String pathId;
    private int soulLevel = 0;
    private int soulExperience = 0;
    private Map<String, Integer> resourceValues = new HashMap<>();
    private int schemaVersion;
    private String adminAbilitySoul;
    private String adminPassiveSoul;

    public PlayerProfile() {
    }

    public PlayerProfile(UUID playerId) {
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getSoulId() {
        return soulId;
    }

    public void setSoulId(String soulId) {
        this.soulId = soulId;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public int getSoulLevel() {
        return soulLevel;
    }

    public void setSoulLevel(int soulLevel) {
        this.soulLevel = soulLevel;
    }

    public int getSoulExperience() {
        return soulExperience;
    }

    public void setSoulExperience(int soulExperience) {
        this.soulExperience = soulExperience;
    }

    public void addExperience(int amount) {
        this.soulExperience += amount;
    }

    public String getAdminAbilitySoul() {
        return adminAbilitySoul;
    }

    public void setAdminAbilitySoul(String adminAbilitySoul) {
        this.adminAbilitySoul = adminAbilitySoul;
    }

    public String getAdminPassiveSoul() {
        return adminPassiveSoul;
    }

    public void setAdminPassiveSoul(String adminPassiveSoul) {
        this.adminPassiveSoul = adminPassiveSoul;
    }

    public int getResource(String resourceId) {
        return getResourceValues().getOrDefault(resourceId, 0);
    }

    public void setResource(String resourceId, int value) {
        getResourceValues().put(resourceId, value);
    }

    public Map<String, Integer> getResourceValues() {
        if (resourceValues == null) {
            resourceValues = new HashMap<>();
        }
        return resourceValues;
    }

    public void setResourceValues(Map<String, Integer> resourceValues) {
        this.resourceValues = resourceValues;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }
}
