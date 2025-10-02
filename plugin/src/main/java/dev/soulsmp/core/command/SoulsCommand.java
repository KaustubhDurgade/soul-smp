package dev.soulsmp.core.command;

import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.resource.ResourceManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class SoulsCommand implements CommandExecutor, TabCompleter {

    private static final List<String> SUBCOMMANDS = List.of("help", "reload");

    private final ConfigManager configManager;
    private final MessageService messageService;
    private final Runnable reloadCallback;

    public SoulsCommand(ConfigManager configManager,
                        MessageService messageService,
                        Runnable reloadCallback) {
        this.configManager = configManager;
        this.messageService = messageService;
        this.reloadCallback = reloadCallback;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelp(sender, label);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("souls.admin.reload")) {
                messageService.send(sender, "no-permission");
                return true;
            }

            try {
                configManager.reload();
                messageService.load(Locale.ENGLISH);
                reloadCallback.run();
                messageService.send(sender, "command.reload.success");
            } catch (Exception ex) {
                ex.printStackTrace();
                messageService.send(sender, "command.reload.failure");
            }
            return true;
        }

        messageService.send(sender, "unknown-subcommand");
        return true;
    }

    private void sendHelp(CommandSender sender, String label) {
        messageService.send(sender, "command.help.header", Collections.singletonMap("label", label));
        messageService.send(sender, "command.help.reload");
        messageService.send(sender, "command.help.debug");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            List<String> suggestions = new ArrayList<>();
            for (String option : SUBCOMMANDS) {
                if (option.startsWith(prefix)) {
                    suggestions.add(option);
                }
            }
            return suggestions;
        }
        return Collections.emptyList();
    }
}
