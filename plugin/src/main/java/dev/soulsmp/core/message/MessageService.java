package dev.soulsmp.core.message;

import dev.soulsmp.SoulSMPPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public final class MessageService {

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    private final SoulSMPPlugin plugin;
    private final Properties properties = new Properties();

    public MessageService(SoulSMPPlugin plugin) {
        this.plugin = plugin;
    }

    public void load(Locale locale) throws IOException {
        properties.clear();

        if (!plugin.getDataFolder().exists() && !plugin.getDataFolder().mkdirs()) {
            plugin.getLogger().warning("Failed to create plugin data folder while loading messages.");
        }

        String filename = Locale.ENGLISH.equals(locale)
            ? "messages.properties"
            : "messages_" + locale.toLanguageTag() + ".properties";

        File file = new File(plugin.getDataFolder(), filename);
        if (!file.exists()) {
            if (!"messages.properties".equals(filename)) {
                file = new File(plugin.getDataFolder(), "messages.properties");
            }
            if (!file.exists()) {
                plugin.saveResource("messages.properties", false);
            }
        }

        try (var reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
    }

    public Component format(String key, Map<String, String> placeholders) {
        String raw = properties.getProperty(key, key);
        String processed = raw;
        if (placeholders != null && !placeholders.isEmpty()) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                processed = processed.replace("%" + entry.getKey() + '%', entry.getValue());
            }
        }
        processed = processed.replace("%prefix%", properties.getProperty("prefix", ""));
        return LEGACY_SERIALIZER.deserialize(processed);
    }

    public Component format(String key) {
        return format(key, Map.of());
    }

    public void send(CommandSender sender, String key) {
        sender.sendMessage(format(key));
    }

    public void send(CommandSender sender, String key, Map<String, String> placeholders) {
        sender.sendMessage(format(key, placeholders));
    }
}
