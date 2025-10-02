package dev.soulsmp.core.player.migration;

import dev.soulsmp.core.player.PlayerProfile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ProfileMigrationService {

    public static final int CURRENT_VERSION = 1;

    private final List<ProfileMigration> migrations = new ArrayList<>();

    public ProfileMigrationService() {
        register(new V0ToV1Migration());
    }

    public void register(ProfileMigration migration) {
        migrations.add(migration);
        migrations.sort(Comparator.comparingInt(ProfileMigration::fromVersion));
    }

    public void migrate(PlayerProfile profile) {
        int version = profile.getSchemaVersion();
        if (version >= CURRENT_VERSION) {
            profile.setSchemaVersion(CURRENT_VERSION);
            return;
        }
        for (ProfileMigration migration : migrations) {
            if (migration.fromVersion() == version) {
                migration.migrate(profile);
                profile.setSchemaVersion(migration.toVersion());
                version = migration.toVersion();
                if (version >= CURRENT_VERSION) {
                    break;
                }
            }
        }
        profile.setSchemaVersion(CURRENT_VERSION);
    }

    private static final class V0ToV1Migration implements ProfileMigration {
        @Override
        public int fromVersion() {
            return 0;
        }

        @Override
        public int toVersion() {
            return 1;
        }

        @Override
        public void migrate(PlayerProfile profile) {
            if (profile.getResourceValues() == null) {
                profile.setResourceValues(new java.util.HashMap<>());
            }
        }
    }
}
