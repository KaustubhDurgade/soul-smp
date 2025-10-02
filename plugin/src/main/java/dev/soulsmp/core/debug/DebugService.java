package dev.soulsmp.core.debug;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DebugService {

    private final Set<UUID> eventSubscribers = ConcurrentHashMap.newKeySet();
    private final AtomicBoolean consoleSubscribed = new AtomicBoolean(false);

    public boolean togglePlayerEvents(UUID playerId) {
        if (eventSubscribers.contains(playerId)) {
            eventSubscribers.remove(playerId);
            return false;
        }
        eventSubscribers.add(playerId);
        return true;
    }

    public boolean toggleConsoleEvents() {
        boolean wasEnabled = consoleSubscribed.get();
        consoleSubscribed.set(!wasEnabled);
        return !wasEnabled;
    }

    public boolean isPlayerSubscribed(UUID playerId) {
        return eventSubscribers.contains(playerId);
    }

    public boolean isConsoleSubscribed() {
        return consoleSubscribed.get();
    }

    public Set<UUID> subscribers() {
        return Set.copyOf(eventSubscribers);
    }
}
