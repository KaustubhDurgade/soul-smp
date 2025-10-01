package dev.soulsmp.wrath;

import dev.soulsmp.SoulSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class WrathAbilityManager {
    private final SoulSMPPlugin plugin;
    private final Set<UUID> activePlayers = Collections.synchronizedSet(new HashSet<>());
    private final WrathHeatManager heatManager;
    private final WrathIgnitionManager ignitionManager;
    private final WrathListener listener;
    private final WrathCommand command;

    public WrathAbilityManager(SoulSMPPlugin plugin) {
        this.plugin = plugin;
        this.heatManager = new WrathHeatManager(plugin, this);
        this.ignitionManager = new WrathIgnitionManager(plugin, this.heatManager);
        this.listener = new WrathListener(this, this.heatManager, this.ignitionManager);
        this.command = new WrathCommand(this, this.ignitionManager, this.heatManager);
    }

    public void register() {
        PluginCommand command = plugin.getCommand("wrath");
        if (command != null) {
            command.setExecutor(this.command);
            command.setTabCompleter(this.command);
        } else {
            plugin.getLogger().warning("/wrath command missing from plugin.yml");
        }

        Bukkit.getPluginManager().registerEvents(this.listener, this.plugin);
        this.heatManager.start();
        this.ignitionManager.start();
    }

    public void unregister() {
        HandlerList.unregisterAll(this.listener);
        this.heatManager.stop();
        this.ignitionManager.stop();
        this.activePlayers.clear();
    }

    public void addPlayer(Player player) {
        this.activePlayers.add(player.getUniqueId());
        this.heatManager.ensureState(player);
    }

    public void removePlayer(Player player) {
        this.activePlayers.remove(player.getUniqueId());
        this.heatManager.removeState(player.getUniqueId());
    }

    public boolean hasPlayer(Player player) {
        return this.activePlayers.contains(player.getUniqueId());
    }

    public Collection<UUID> getActivePlayers() {
        return Set.copyOf(this.activePlayers);
    }
}
