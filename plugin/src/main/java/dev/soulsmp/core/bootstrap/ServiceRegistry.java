package dev.soulsmp.core.bootstrap;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal dependency injection container used to share service singletons across the plugin runtime.
 */
public final class ServiceRegistry {

    private final Map<Class<?>, Object> services = new ConcurrentHashMap<>();

    public <T> void register(Class<T> type, T instance) {
        if (type == null || instance == null) {
            throw new IllegalArgumentException("Service type and instance must not be null");
        }
        services.put(type, instance);
    }

    public <T> Optional<T> get(Class<T> type) {
        Object value = services.get(type);
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(type.cast(value));
    }

    public <T> T require(Class<T> type) {
        return get(type).orElseThrow(() -> new IllegalStateException("Service not registered: " + type.getName()));
    }

    public boolean isRegistered(Class<?> type) {
        return services.containsKey(type);
    }

    public void unregister(Class<?> type) {
        services.remove(type);
    }

    public void clear() {
        services.clear();
    }
}
