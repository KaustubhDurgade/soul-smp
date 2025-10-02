package dev.soulsmp.wrath;

import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.resource.ResourceDefinition;
import dev.soulsmp.core.resource.ResourceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final class WrathCommand implements CommandExecutor, TabCompleter {
    private static final List<String> SUB_COMMANDS = List.of("join", "leave", "heat", "ignition", "chain", "meteor");

    private final WrathAbilityManager abilityManager;
    private final WrathIgnitionManager ignitionManager;
    private final WrathChainManager chainManager;
    private final WrathMeteorManager meteorManager;
    private final WrathHeatManager heatManager;
    private final MessageService messageService;
    private final ResourceManager resourceManager;

    WrathCommand(WrathAbilityManager abilityManager,
                 WrathIgnitionManager ignitionManager,
                 WrathChainManager chainManager,
                 WrathMeteorManager meteorManager,
                 WrathHeatManager heatManager,
                 MessageService messageService,
                 ResourceManager resourceManager) {
        this.abilityManager = abilityManager;
        this.ignitionManager = ignitionManager;
        this.chainManager = chainManager;
        this.meteorManager = meteorManager;
        this.heatManager = heatManager;
        this.messageService = messageService;
        this.resourceManager = resourceManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            messageService.send(sender, "wrath.player-only");
            return true;
        }

        if (args.length == 0) {
            messageService.send(sender, "wrath.usage", Map.of("label", label));
            return true;
        }

        String sub = args[0].toLowerCase();
        switch (sub) {
            case "join" -> handleJoin(player);
            case "leave" -> handleLeave(player);
            case "heat" -> handleHeat(player);
            case "ignition" -> handleIgnition(player);
            case "chain" -> handleChain(player);
            case "meteor" -> handleMeteor(player);
            default -> messageService.send(sender, "unknown-subcommand");
        }
        return true;
    }

    private void handleJoin(Player player) {
        if (this.abilityManager.hasPlayer(player)) {
            messageService.send(player, "wrath.join.already");
            return;
        }
        this.abilityManager.addPlayer(player);
        messageService.send(player, "wrath.join.success");
    }

    private void handleLeave(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            messageService.send(player, "wrath.leave.not-active");
            return;
        }
        this.abilityManager.removePlayer(player);
        messageService.send(player, "wrath.leave.success");
    }

    private void handleHeat(Player player) {
        int heat = this.heatManager.getHeat(player.getUniqueId());
        int max = maxHeat();
        messageService.send(player, "wrath.heat.value", Map.of("value", Integer.toString(heat), "max", Integer.toString(max)));
    }

    private void handleIgnition(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            messageService.send(player, "wrath.ignition.not-aligned");
            return;
        }
        if (this.ignitionManager.activate(player)) {
            this.heatManager.recordCombat(player);
        }
    }

    private void handleChain(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            messageService.send(player, "wrath.chain.not-aligned");
            return;
        }
        this.chainManager.activate(player);
    }

    private void handleMeteor(Player player) {
        if (!this.abilityManager.hasPlayer(player)) {
            messageService.send(player, "wrath.meteor.not-aligned");
            return;
        }
        this.meteorManager.activate(player);
    }

    private int maxHeat() {
        Optional<ResourceDefinition> definition = resourceManager.definition("heat");
        return definition.map(ResourceDefinition::max).orElse(100);
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
        return java.util.Collections.emptyList();
    }
}
