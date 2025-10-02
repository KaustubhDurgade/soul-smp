package dev.soulsmp.core.player.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.player.PlayerProfile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

public final class YamlProfileStorage implements ProfileStorage {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final SoulSMPPlugin plugin;
    private final File directory;

    public YamlProfileStorage(SoulSMPPlugin plugin) {
        this.plugin = plugin;
        this.directory = new File(plugin.getDataFolder(), "profiles");
        if (!directory.exists() && !directory.mkdirs()) {
            plugin.getLogger().warning("Failed to create profiles directory");
        }
    }

    @Override
    public Optional<PlayerProfile> load(UUID playerId) throws IOException {
        File file = profileFile(playerId);
        if (!file.exists()) {
            return Optional.empty();
        }
        try (var reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            PlayerProfile profile = GSON.fromJson(reader, PlayerProfile.class);
            return Optional.ofNullable(profile);
        }
    }

    @Override
    public void save(PlayerProfile profile) throws IOException {
        File file = profileFile(profile.getPlayerId());
        try (var writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            GSON.toJson(profile, writer);
        }
    }

    private File profileFile(UUID playerId) {
        return new File(directory, playerId.toString() + ".json");
    }
}
