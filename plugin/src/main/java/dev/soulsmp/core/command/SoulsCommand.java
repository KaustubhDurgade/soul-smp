package dev.soulsmp.core.command;

import dev.soulsmp.admin.AdminSoulManager;
import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.cooldown.CooldownManager;
import dev.soulsmp.core.debug.DebugService;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.metrics.MetricsService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.soul.SoulCatalog;
import dev.soulsmp.core.soul.SoulDefinition;
import dev.soulsmp.core.soul.SoulProgressionService;
import dev.soulsmp.core.telemetry.TelemetryService;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class SoulsCommand implements CommandExecutor, TabCompleter {

    private static final List<String> ROOT_SUBCOMMANDS = List.of("help", "list", "info", "select", "respec", "admin", "debug", "metrics", "reload");

    private final ConfigManager configManager;
    private final MessageService messageService;
    private final PlayerProfileManager profileManager;
    private final ResourceManager resourceManager;
    private final SoulCatalog soulCatalog;
    private final CooldownManager cooldownManager;
    private final TelemetryService telemetryService;
    private final MetricsService metricsService;
    private final DebugService debugService;
    private final AdminSoulManager adminSoulManager;
    private final SoulProgressionService soulProgressionService;
    private final Runnable reloadCallback;

    public SoulsCommand(ConfigManager configManager,
                        MessageService messageService,
                        PlayerProfileManager profileManager,
                        ResourceManager resourceManager,
                        SoulCatalog soulCatalog,
                        CooldownManager cooldownManager,
                        TelemetryService telemetryService,
                        MetricsService metricsService,
                        DebugService debugService,
                        AdminSoulManager adminSoulManager,
                        SoulProgressionService soulProgressionService,
                        Runnable reloadCallback) {
        this.configManager = configManager;
        this.messageService = messageService;
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.soulCatalog = soulCatalog;
        this.cooldownManager = cooldownManager;
        this.telemetryService = telemetryService;
        this.metricsService = metricsService;
        this.debugService = debugService;
        this.adminSoulManager = adminSoulManager;
        this.soulProgressionService = soulProgressionService;
        this.reloadCallback = reloadCallback;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendHelp(sender, label);
            return true;
        }

        String subcommand = args[0].toLowerCase(Locale.ROOT);
        switch (subcommand) {
            case "help" -> sendHelp(sender, label);
            case "list" -> handleList(sender);
            case "info" -> handleInfo(sender, args);
            case "select" -> handleSelect(sender, args);
            case "respec" -> handleRespec(sender);
            case "admin" -> handleAdmin(sender, Arrays.copyOfRange(args, 1, args.length));
            case "debug" -> handleDebug(sender, Arrays.copyOfRange(args, 1, args.length));
            case "metrics" -> handleMetrics(sender, Arrays.copyOfRange(args, 1, args.length));
            case "reload" -> handleReload(sender);
            default -> messageService.send(sender, "unknown-subcommand");
        }
        return true;
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("souls.admin.reload")) {
            messageService.send(sender, "no-permission");
            return;
        }

        try {
            configManager.reload();
            soulCatalog.reload();
            reloadCallback.run();
            messageService.send(sender, "command.reload.success");
        } catch (Exception ex) {
            ex.printStackTrace();
            messageService.send(sender, "command.reload.failure");
        }
    }

    private void handleList(CommandSender sender) {
        List<SoulDefinition> souls = soulCatalog.list();
        if (souls.isEmpty()) {
            messageService.send(sender, "souls.list.empty");
            return;
        }

        messageService.send(sender, "souls.list.header", Map.of("count", Integer.toString(souls.size())));
        for (SoulDefinition definition : souls) {
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("id", definition.id());
            placeholders.put("name", definition.displayName());
            placeholders.put("difficulty", definition.difficulty());
            placeholders.put("paths", definition.paths().isEmpty() ? "-" : String.join(", ", definition.paths()));
            messageService.send(sender, "souls.list.entry", placeholders);
        }
    }

    private void handleInfo(CommandSender sender, String[] args) {
        if (args.length < 2) {
            messageService.send(sender, "souls.info.usage");
            return;
        }

        String soulId = args[1];
        Optional<SoulDefinition> optional = soulCatalog.find(soulId);
        if (optional.isEmpty()) {
            messageService.send(sender, "souls.info.not-found", Map.of("id", soulId));
            return;
        }

        SoulDefinition definition = optional.get();
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("id", definition.id());
        placeholders.put("name", definition.displayName());
        placeholders.put("difficulty", definition.difficulty());
        placeholders.put("description", definition.description());
        placeholders.put("paths", definition.paths().isEmpty() ? "-" : String.join(", ", definition.paths()));
        messageService.send(sender, "souls.info.details", placeholders);
    }

    private void handleSelect(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            messageService.send(sender, "command.player-only");
            return;
        }

        if (args.length < 2) {
            messageService.send(sender, "souls.select.usage");
            return;
        }

        String soulId = args[1];
        Optional<SoulDefinition> optional = soulCatalog.find(soulId);
        if (optional.isEmpty()) {
            messageService.send(sender, "souls.select.invalid", Map.of("id", soulId));
            return;
        }

        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        if (profile.getSoulId() != null && !profile.getSoulId().isBlank()) {
            messageService.send(sender, "souls.select.already", Map.of("id", profile.getSoulId()));
            return;
        }

        profile.setSoulId(optional.get().id());
        profile.setPathId(null);
        resourceManager.applyDefinitions(profile);
        profileManager.save(player.getUniqueId());

        messageService.send(sender, "souls.select.success", Map.of("id", optional.get().id(), "name", optional.get().displayName()));
    }

    private void handleRespec(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            messageService.send(sender, "command.player-only");
            return;
        }

        if (!configManager.souls().allowRespec()) {
            messageService.send(sender, "souls.respec.disabled");
            return;
        }

        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        if (profile.getSoulId() == null || profile.getSoulId().isBlank()) {
            messageService.send(sender, "souls.respec.none");
            return;
        }

        profile.setSoulId(null);
        profile.setPathId(null);
        resourceManager.applyDefinitions(profile);
        profileManager.save(player.getUniqueId());
        messageService.send(sender, "souls.respec.success");
    }

    private void handleAdmin(CommandSender sender, String[] args) {
        if (args.length == 0) {
            messageService.send(sender, "souls.admin.usage");
            return;
        }

        String action = args[0].toLowerCase(Locale.ROOT);
        switch (action) {
            case "grant" -> handleAdminGrant(sender, args);
            case "resource" -> handleAdminResource(sender, args);
            case "cooldown" -> handleAdminCooldown(sender, args);
            case "level" -> handleAdminLevel(sender, args);
            case "ability" -> handleAdminAbility(sender, args);
            case "passive" -> handleAdminPassive(sender, args);
            default -> messageService.send(sender, "souls.admin.usage");
        }
    }

    private void handleAdminGrant(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.grant")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 3) {
            messageService.send(sender, "souls.admin.grant.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        PlayerProfile profile = profileManager.getOrCreate(targetId.get());

        String raw = args[2];
        String soulId = raw;
        String pathId = null;
        int slashIndex = raw.indexOf('/');
        if (slashIndex > 0 && slashIndex < raw.length() - 1) {
            soulId = raw.substring(0, slashIndex);
            pathId = raw.substring(slashIndex + 1);
        }

        if ("admin".equalsIgnoreCase(soulId)) {
            adminSoulManager.assignAdminSoul(targetId.get());
            PlayerProfile updatedProfile = profileManager.getOrCreate(targetId.get());
            resourceManager.applyDefinitions(updatedProfile);
            profileManager.save(targetId.get());
            Map<String, String> placeholders = Map.of(
                "player", args[1],
                "soul", "admin",
                "path", "-"
            );
            messageService.send(sender, "souls.admin.grant.success", placeholders);
            Player online = Bukkit.getPlayer(targetId.get());
            if (online != null && !Objects.equals(online, sender)) {
                messageService.send(online, "souls.admin.grant.notify", placeholders);
            }
            return;
        }

        Optional<SoulDefinition> optional = soulCatalog.find(soulId);
        if (optional.isEmpty()) {
            messageService.send(sender, "souls.admin.grant.invalid-soul", Map.of("id", soulId));
            return;
        }

        SoulDefinition definition = optional.get();
        final String finalPathId = pathId;
        if (finalPathId != null && definition.paths().stream().noneMatch(path -> path.equalsIgnoreCase(finalPathId))) {
            messageService.send(sender, "souls.admin.grant.invalid-path", Map.of("path", finalPathId, "id", definition.id()));
            return;
        }

        profile.setSoulId(definition.id());
        profile.setPathId(pathId == null ? null : pathId.toLowerCase(Locale.ROOT));
        resourceManager.applyDefinitions(profile);
        profileManager.save(targetId.get());

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("player", args[1]);
        placeholders.put("soul", definition.id());
        placeholders.put("path", pathId == null ? "-" : pathId.toLowerCase(Locale.ROOT));
        messageService.send(sender, "souls.admin.grant.success", placeholders);

        Player online = Bukkit.getPlayer(targetId.get());
        if (online != null && !Objects.equals(online, sender)) {
            messageService.send(online, "souls.admin.grant.notify", placeholders);
        }
    }

    private void handleAdminResource(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.resource")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 5) {
            messageService.send(sender, "souls.admin.resource.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        String resourceId = args[2];
        String mode = args[3].toLowerCase(Locale.ROOT);
        String amountRaw = args[4];
        int amount;
        try {
            amount = Integer.parseInt(amountRaw);
        } catch (NumberFormatException ex) {
            messageService.send(sender, "souls.admin.resource.invalid-amount", Map.of("value", amountRaw));
            return;
        }

        int result;
        switch (mode) {
            case "set" -> {
                resourceManager.set(targetId.get(), resourceId, amount);
                result = resourceManager.get(targetId.get(), resourceId);
            }
            case "add" -> result = resourceManager.modify(targetId.get(), resourceId, amount);
            default -> {
                messageService.send(sender, "souls.admin.resource.invalid-mode", Map.of("mode", mode));
                return;
            }
        }

        profileManager.save(targetId.get());
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("player", args[1]);
        placeholders.put("resource", resourceId);
        placeholders.put("value", Integer.toString(result));
        messageService.send(sender, "souls.admin.resource.updated", placeholders);
    }

    private void handleAdminCooldown(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.cooldown")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 4) {
            messageService.send(sender, "souls.admin.cooldown.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        String abilityId = args[2];
        String mode = args[3].toLowerCase(Locale.ROOT);
        if (!"reset".equals(mode)) {
            messageService.send(sender, "souls.admin.cooldown.usage");
            return;
        }

        cooldownManager.clear(targetId.get(), abilityId);
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("player", args[1]);
        placeholders.put("ability", abilityId);
        messageService.send(sender, "souls.admin.cooldown.reset", placeholders);
    }

    private void handleAdminLevel(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.level")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 4) {
            messageService.send(sender, "souls.admin.level.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        String mode = args[2].toLowerCase(Locale.ROOT);
        int value;
        try {
            value = Integer.parseInt(args[3]);
        } catch (NumberFormatException ex) {
            messageService.send(sender, "souls.admin.level.invalid", Map.of("value", args[3]));
            return;
        }

        PlayerProfile profile = profileManager.getOrCreate(targetId.get());
        if (profile.getSoulId() == null || profile.getSoulId().isBlank()) {
            messageService.send(sender, "souls.admin.level.no-soul", Map.of("player", args[1]));
            return;
        }

        int originalLevel = profile.getSoulLevel();
        int updatedLevel = originalLevel;

        switch (mode) {
            case "set" -> updatedLevel = clampLevel(value);
            case "add" -> updatedLevel = clampLevel(originalLevel + value);
            default -> {
                messageService.send(sender, "souls.admin.level.usage");
                return;
            }
        }

    profile.setSoulLevel(updatedLevel);
    int floorXp = soulProgressionService.getXpFloorForLevel(updatedLevel);
    profile.setSoulExperience(floorXp);
        profileManager.save(targetId.get());

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("player", args[1]);
        placeholders.put("level", Integer.toString(updatedLevel));
        placeholders.put("mode", mode);
        messageService.send(sender, "souls.admin.level.updated", placeholders);

        Player online = Bukkit.getPlayer(targetId.get());
        if (online != null && !Objects.equals(online, sender)) {
            messageService.send(online, "souls.admin.level.notify", placeholders);
        }
    }

    private void handleAdminAbility(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.ability")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 3) {
            messageService.send(sender, "souls.admin.ability.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        String mode = args[2].toLowerCase(Locale.ROOT);
        switch (mode) {
            case "set" -> {
                if (args.length < 4) {
                    messageService.send(sender, "souls.admin.ability.usage");
                    return;
                }
                adminSoulManager.setAdminAbilitySoul(targetId.get(), args[3]);
                Map<String, String> placeholders = Map.of(
                    "player", args[1],
                    "soul", args[3].toLowerCase(Locale.ROOT)
                );
                messageService.send(sender, "souls.admin.ability.set", placeholders);
                Player online = Bukkit.getPlayer(targetId.get());
                if (online != null && !Objects.equals(online, sender)) {
                    messageService.send(online, "souls.admin.ability.notify", placeholders);
                }
            }
            case "clear" -> {
                adminSoulManager.clearAdminAbilitySoul(targetId.get());
                Map<String, String> placeholders = Map.of("player", args[1]);
                messageService.send(sender, "souls.admin.ability.cleared", placeholders);
                Player online = Bukkit.getPlayer(targetId.get());
                if (online != null && !Objects.equals(online, sender)) {
                    messageService.send(online, "souls.admin.ability.cleared-self", Map.of());
                }
            }
            default -> messageService.send(sender, "souls.admin.ability.usage");
        }
    }

    private void handleAdminPassive(CommandSender sender, String[] args) {
        if (!sender.hasPermission("souls.admin.passive")) {
            messageService.send(sender, "no-permission");
            return;
        }

        if (args.length < 3) {
            messageService.send(sender, "souls.admin.passive.usage");
            return;
        }

        Optional<UUID> targetId = resolvePlayerUniqueId(args[1]);
        if (targetId.isEmpty()) {
            messageService.send(sender, "souls.admin.player-not-found", Map.of("name", args[1]));
            return;
        }

        String mode = args[2].toLowerCase(Locale.ROOT);
        switch (mode) {
            case "set" -> {
                if (args.length < 4) {
                    messageService.send(sender, "souls.admin.passive.usage");
                    return;
                }
                adminSoulManager.setAdminPassiveSoul(targetId.get(), args[3]);
                Map<String, String> placeholders = Map.of(
                    "player", args[1],
                    "soul", args[3].toLowerCase(Locale.ROOT)
                );
                messageService.send(sender, "souls.admin.passive.set", placeholders);
                Player online = Bukkit.getPlayer(targetId.get());
                if (online != null && !Objects.equals(online, sender)) {
                    messageService.send(online, "souls.admin.passive.notify", placeholders);
                }
            }
            case "clear" -> {
                adminSoulManager.clearAdminPassiveSoul(targetId.get());
                Map<String, String> placeholders = Map.of("player", args[1]);
                messageService.send(sender, "souls.admin.passive.cleared", placeholders);
                Player online = Bukkit.getPlayer(targetId.get());
                if (online != null && !Objects.equals(online, sender)) {
                    messageService.send(online, "souls.admin.passive.cleared-self", Map.of());
                }
            }
            default -> messageService.send(sender, "souls.admin.passive.usage");
        }
    }

    private int clampLevel(int level) {
        return Math.max(0, Math.min(SoulProgressionService.FINAL_STAND_UNLOCK_LEVEL, level));
    }

    private void handleDebug(CommandSender sender, String[] args) {
        if (args.length == 0) {
            messageService.send(sender, "souls.debug.usage");
            return;
        }

        String option = args[0].toLowerCase(Locale.ROOT);
        if (!"events".equals(option)) {
            messageService.send(sender, "souls.debug.usage");
            return;
        }

        if (sender instanceof Player player) {
            boolean enabled = debugService.togglePlayerEvents(player.getUniqueId());
            messageService.send(sender, enabled ? "souls.debug.events.enabled" : "souls.debug.events.disabled");
        } else {
            boolean enabled = debugService.toggleConsoleEvents();
            messageService.send(sender, enabled ? "souls.debug.events.enabled" : "souls.debug.events.disabled");
        }
    }

    private void handleMetrics(CommandSender sender, String[] args) {
        if (args.length == 0 || !"export".equalsIgnoreCase(args[0])) {
            messageService.send(sender, "souls.metrics.usage");
            return;
        }

        if (!sender.hasPermission("souls.metrics.export")) {
            messageService.send(sender, "no-permission");
            return;
        }

        telemetryService.flush();
        metricsService.exportSnapshot();
        messageService.send(sender, "souls.metrics.exported");
    }

    private Optional<UUID> resolvePlayerUniqueId(String name) {
        Player online = Bukkit.getPlayerExact(name);
        if (online != null) {
            return Optional.of(online.getUniqueId());
        }
        OfflinePlayer offline = Bukkit.getOfflinePlayer(name);
        if ((offline.hasPlayedBefore() || offline.isOnline()) && offline.getUniqueId() != null) {
            return Optional.of(offline.getUniqueId());
        }
        return Optional.empty();
    }

    private void sendHelp(CommandSender sender, String label) {
        messageService.send(sender, "command.help.header", Map.of("label", label));
        messageService.send(sender, "command.help.list");
        messageService.send(sender, "command.help.info");
        messageService.send(sender, "command.help.select");
        messageService.send(sender, "command.help.respec");
        messageService.send(sender, "command.help.admin");
        messageService.send(sender, "command.help.debug");
        messageService.send(sender, "command.help.metrics");
        messageService.send(sender, "command.help.reload");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase(Locale.ROOT);
            List<String> suggestions = new ArrayList<>();
            for (String option : ROOT_SUBCOMMANDS) {
                if (option.startsWith(prefix)) {
                    suggestions.add(option);
                }
            }
            return suggestions;
        }

        if (args.length == 2) {
            String sub = args[0].toLowerCase(Locale.ROOT);
            return switch (sub) {
                case "info", "select" -> tabSoulIds(args[1]);
                case "admin" -> List.of("grant", "resource", "cooldown");
                case "debug" -> List.of("events");
                case "metrics" -> List.of("export");
                default -> Collections.emptyList();
            };
        }

        if (args.length == 3 && "admin".equalsIgnoreCase(args[0])) {
            String action = args[1].toLowerCase(Locale.ROOT);
            return switch (action) {
                case "grant", "resource", "cooldown" -> tabPlayerNames(args[2]);
                default -> Collections.emptyList();
            };
        }

        if (args.length == 4 && "admin".equalsIgnoreCase(args[0])) {
            String action = args[1].toLowerCase(Locale.ROOT);
            return switch (action) {
                case "resource" -> List.of("set", "add");
                case "cooldown" -> List.of("reset");
                default -> Collections.emptyList();
            };
        }

        if (args.length == 5 && "admin".equalsIgnoreCase(args[0]) && "resource".equalsIgnoreCase(args[1])) {
            return Collections.singletonList("0");
        }

        return Collections.emptyList();
    }

    private List<String> tabSoulIds(String prefix) {
        String lower = prefix.toLowerCase(Locale.ROOT);
        List<String> matches = new ArrayList<>();
        for (SoulDefinition definition : soulCatalog.list()) {
            if (definition.id().startsWith(lower)) {
                matches.add(definition.id());
            }
        }
        return matches;
    }

    private List<String> tabPlayerNames(String prefix) {
        String lower = prefix.toLowerCase(Locale.ROOT);
        List<String> matches = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().toLowerCase(Locale.ROOT).startsWith(lower)) {
                matches.add(player.getName());
            }
        }
        return matches;
    }
}
