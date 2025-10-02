package dev.soulsmp.core.player.storage;

import dev.soulsmp.core.player.PlayerProfile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface ProfileStorage {

    Optional<PlayerProfile> load(UUID playerId) throws IOException;

    void save(PlayerProfile profile) throws IOException;
}
