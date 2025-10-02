package dev.soulsmp.core.resource;

public final class ResourceDefinition {

    private final String id;
    private final int min;
    private final int max;
    private final int starting;
    private final int decayIntervalSeconds;
    private final int combatTickSeconds;

    private ResourceDefinition(Builder builder) {
        this.id = builder.id;
        this.min = builder.min;
        this.max = builder.max;
        this.starting = builder.starting;
        this.decayIntervalSeconds = builder.decayIntervalSeconds;
        this.combatTickSeconds = builder.combatTickSeconds;
    }

    public String id() {
        return id;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int starting() {
        return starting;
    }

    public int decayIntervalSeconds() {
        return decayIntervalSeconds;
    }

    public int combatTickSeconds() {
        return combatTickSeconds;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static final class Builder {
        private final String id;
        private int min = 0;
        private int max = 100;
        private int starting = 0;
        private int decayIntervalSeconds = 10;
        private int combatTickSeconds = 5;

        private Builder(String id) {
            this.id = id;
        }

        public Builder min(int min) {
            this.min = min;
            return this;
        }

        public Builder max(int max) {
            this.max = max;
            return this;
        }

        public Builder starting(int starting) {
            this.starting = starting;
            return this;
        }

        public Builder decayIntervalSeconds(int decayIntervalSeconds) {
            this.decayIntervalSeconds = decayIntervalSeconds;
            return this;
        }

        public Builder combatTickSeconds(int combatTickSeconds) {
            this.combatTickSeconds = combatTickSeconds;
            return this;
        }

        public ResourceDefinition build() {
            return new ResourceDefinition(this);
        }
    }
}
