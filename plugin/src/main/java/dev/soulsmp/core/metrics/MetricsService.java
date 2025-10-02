package dev.soulsmp.core.metrics;

import dev.soulsmp.core.config.ConfigManager;
import dev.soulsmp.core.player.PlayerProfileManager;
import dev.soulsmp.core.resource.ResourceManager;
import dev.soulsmp.core.task.TaskScheduler;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public final class MetricsService {

    private final Logger logger;
    private final ConfigManager configManager;
    private final PlayerProfileManager profileManager;
    private final ResourceManager resourceManager;
    private final TaskScheduler taskScheduler;

    private final AtomicBoolean running = new AtomicBoolean(false);
    private BukkitTask reportingTask;
    private MetricsSettings settings;

    public MetricsService(Logger logger,
                          ConfigManager configManager,
                          PlayerProfileManager profileManager,
                          ResourceManager resourceManager,
                          TaskScheduler taskScheduler) {
        this.logger = logger;
        this.configManager = configManager;
        this.profileManager = profileManager;
        this.resourceManager = resourceManager;
        this.taskScheduler = taskScheduler;
    }

    public void start(MetricsSettings settings) {
        this.settings = settings;
        cancelTask();
        if (!settings.enabled()) {
            running.set(false);
            return;
        }
        running.set(true);
        long periodTicks = Math.max(1L, Duration.ofMinutes(settings.reportIntervalMinutes()).toSeconds() * 20L);
        this.reportingTask = taskScheduler.runTimer(this::reportSnapshot, periodTicks, periodTicks);
        logger.info("Metrics service started. Reporting every " + settings.reportIntervalMinutes() + " minute(s).");
    }

    public void stop() {
        if (!running.getAndSet(false)) {
            return;
        }
        cancelTask();
        reportSnapshot();
        logger.info("Metrics service stopped.");
    }

    public void exportSnapshot() {
        reportSnapshot();
    }

    private void cancelTask() {
        if (reportingTask != null) {
            reportingTask.cancel();
            reportingTask = null;
        }
    }

    private void reportSnapshot() {
        if (settings == null || !settings.enabled()) {
            return;
        }
        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        int cachedProfiles = profileManager.allProfiles().size();
        int registeredResources = resourceManager.registeredDefinitions().size();

        logger.info(() -> String.format("[Metrics] Players online=%d, cached profiles=%d, resource types=%d", onlinePlayers, cachedProfiles, registeredResources));
    }

    public record MetricsSettings(boolean enabled, int reportIntervalMinutes) {
    }
}
