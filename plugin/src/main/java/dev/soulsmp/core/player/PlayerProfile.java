package dev.soulsmp.core.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerProfile {

    private UUID playerId;
    private String soulId;
    private String pathId;
    private Map<String, Integer> resourceValues = new HashMap<>();
    private int schemaVersion;

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
