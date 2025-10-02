package dev.soulsmp.core.telemetry;

import dev.soulsmp.core.task.TaskScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

public final class TelemetryService {

    private final Logger logger;
    private final TaskScheduler taskScheduler;
    private final Map<String, LongAdder> counters = new ConcurrentHashMap<>();

    private TelemetrySettings settings = new TelemetrySettings(false, 300);
    private BukkitTask flushTask;

    public TelemetryService(Logger logger, TaskScheduler taskScheduler) {
        this.logger = logger;
        this.taskScheduler = taskScheduler;
    }

    public void configure(TelemetrySettings settings) {
        this.settings = settings;
        if (!settings.enabled()) {
            cancelFlushTask();
            counters.clear();
            return;
        }
        long periodTicks = Math.max(1L, Duration.ofSeconds(settings.flushIntervalSeconds()).toMillis() / 50L);
        cancelFlushTask();
        this.flushTask = taskScheduler.runTimer(this::flush, periodTicks, periodTicks);
    }

    public void recordEvent(String key) {
        if (!settings.enabled()) {
            return;
        }
        counters.computeIfAbsent(key, __ -> new LongAdder()).increment();
    }

    public void recordEvent(String key, long amount) {
        if (!settings.enabled() || amount <= 0) {
            return;
        }
        counters.computeIfAbsent(key, __ -> new LongAdder()).add(amount);
    }

    public void flush() {
        if (!settings.enabled() || counters.isEmpty()) {
            return;
        }
        StringBuilder builder = new StringBuilder("[Telemetry] Snapshot @ ")
            .append(Instant.now()).append('\n');
        counters.forEach((key, value) -> builder.append("  ").append(key).append(": ").append(value.longValue()).append('\n'));
        logger.info(builder.toString().trim());
        counters.clear();
    }

    public void shutdown() {
        cancelFlushTask();
        flush();
    }

    private void cancelFlushTask() {
        if (flushTask != null) {
            flushTask.cancel();
            flushTask = null;
        }
    }

    public record TelemetrySettings(boolean enabled, int flushIntervalSeconds) {
    }
}
