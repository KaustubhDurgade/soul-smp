package dev.soulsmp.core.soul;

import java.util.List;
import java.util.Locale;

public record SoulDefinition(String id,
                             String displayName,
                             String description,
                             String difficulty,
                             List<String> paths) {

    public SoulDefinition {
        String normalizedId = id == null ? "" : id.trim().toLowerCase(Locale.ROOT);
        if (normalizedId.isEmpty()) {
            throw new IllegalArgumentException("Soul id must not be empty");
        }
        id = normalizedId;
        displayName = displayName == null || displayName.isBlank() ? capitalizeId(id) : displayName;
        description = description == null ? "" : description;
        difficulty = difficulty == null || difficulty.isBlank() ? "UNKNOWN" : difficulty;
        paths = paths == null ? List.of() : List.copyOf(paths);
    }

    private static String capitalizeId(String value) {
        if (value.isEmpty()) {
            return value;
        }
        if (value.length() == 1) {
            return value.toUpperCase(Locale.ROOT);
        }
        return value.substring(0, 1).toUpperCase(Locale.ROOT) + value.substring(1);
    }
}
