package dev.soulsmp.core.player.migration;

import dev.soulsmp.core.player.PlayerProfile;

public interface ProfileMigration {

    int fromVersion();

    int toVersion();

    void migrate(PlayerProfile profile);
}
