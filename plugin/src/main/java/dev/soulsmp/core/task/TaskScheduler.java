package dev.soulsmp.core.task;

import dev.soulsmp.SoulSMPPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class TaskScheduler {

    private final SoulSMPPlugin plugin;
    private final Set<BukkitTask> tasks = Collections.synchronizedSet(new HashSet<>());

    public TaskScheduler(SoulSMPPlugin plugin) {
        this.plugin = plugin;
    }

    public BukkitTask run(Runnable runnable) {
        final BukkitTask[] holder = new BukkitTask[1];
        BukkitTask task = Bukkit.getScheduler().runTask(plugin, () -> {
            try {
                runnable.run();
            } finally {
                if (holder[0] != null) {
                    tasks.remove(holder[0]);
                }
            }
        });
        holder[0] = task;
        tasks.add(task);
        return task;
    }

    public BukkitTask runLater(Runnable runnable, long delayTicks) {
        final BukkitTask[] holder = new BukkitTask[1];
        BukkitTask task = Bukkit.getScheduler().runTaskLater(plugin, () -> {
            try {
                runnable.run();
            } finally {
                if (holder[0] != null) {
                    tasks.remove(holder[0]);
                }
            }
        }, delayTicks);
        holder[0] = task;
        tasks.add(task);
        return task;
    }

    public BukkitTask runTimer(Runnable runnable, long delayTicks, long periodTicks) {
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, runnable, delayTicks, periodTicks);
        tasks.add(task);
        return task;
    }

    public void cancelAll() {
        synchronized (tasks) {
            tasks.forEach(BukkitTask::cancel);
            tasks.clear();
        }
    }
}
