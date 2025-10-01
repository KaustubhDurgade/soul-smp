package dev.soulsmp.wrath;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class WrathCommand implements CommandExecutor, TabCompleter {
    private static final List<String> SUB_COMMANDS = List.of("join", "leave", "heat", "ignition");

    private final WrathAbilityManager abilityManager;
    private final WrathIgnitionManager ignitionManager;
    private final WrathHeatManager heatManager;

    WrathCommand(WrathAbilityManager abilityManager, WrathIgnitionManager ignitionManager, WrathHeatManager heatManager) {
        this.abilityManager = abilityManager;
        this.ignitionManager = ignitionManager;
        this.heatManager = heatManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /" + label + " <" + String.join("|", SUB_COMMANDS) + ">");
            return true;
        }

        String sub = args[0].toLowerCase();
        switch (sub) {
            case "join" -> handleJoin(player);
            case "leave" -> handleLeave(player);
            case "heat" -> handleHeat(player);
            case "ignition" -> handleIgnition(player);
            default -> sender.sendMessage(ChatColor.YELLOW + "Unknown subcommand. Options: " + String.join(", ", SUB_COMMANDS));
        }
        return true;
    }

    private void handleJoin(Player player) {
        if (this.abilityManager.hasPlayer(player)) {
            player.sendMessage(ChatColor.GOLD + "You are already aligned with Wrath.");
            return;
        }
        this.abilityManager.addPlayer(player);
        player.sendMessage(ChatColor.RED + "The Soul of Wrath takes hold. Heat will now build through combat.");
    }

    private void handleLeave(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            player.sendMessage(ChatColor.YELLOW + "You are not currently using Wrath.");
            return;
        }
        this.abilityManager.removePlayer(player);
        player.sendMessage(ChatColor.GREEN + "You release the fury within and calm returns.");
    }

    private void handleHeat(Player player) {
        int heat = this.heatManager.getHeat(player.getUniqueId());
        player.sendMessage(ChatColor.RED + "Heat: " + heat + ChatColor.DARK_RED + "/100");
    }

    private void handleIgnition(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            player.sendMessage(ChatColor.YELLOW + "You must align with Wrath before using Ignition.");
            return;
        }
        if (this.ignitionManager.activate(player)) {
            this.heatManager.recordCombat(player);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String prefix = args[0].toLowerCase();
            List<String> matches = new ArrayList<>();
            for (String option : SUB_COMMANDS) {
                if (option.startsWith(prefix)) {
                    matches.add(option);
                }
            }
            return matches;
        }
        return Collections.emptyList();
    }
}
