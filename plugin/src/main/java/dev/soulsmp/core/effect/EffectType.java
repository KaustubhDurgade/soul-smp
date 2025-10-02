package dev.soulsmp.core.effect;

import java.util.Objects;

public final class EffectType {

    private final String id;
    private final String displayName;
    private final EffectStackingPolicy stackingPolicy;
    private final int maxAmplifier;
    private final EffectApplier applier;

    private EffectType(Builder builder) {
        this.id = builder.id;
        this.displayName = builder.displayName;
        this.stackingPolicy = builder.stackingPolicy;
        this.maxAmplifier = builder.maxAmplifier;
        this.applier = builder.applier;
    }

    public String id() {
        return id;
    }

    public String displayName() {
        return displayName;
    }

    public EffectStackingPolicy stackingPolicy() {
        return stackingPolicy;
    }

    public int maxAmplifier() {
        return maxAmplifier;
    }

    public EffectApplier applier() {
        return applier;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static final class Builder {
        private final String id;
        private String displayName = "";
        private EffectStackingPolicy stackingPolicy = EffectStackingPolicy.NONE;
        private int maxAmplifier = 5;
        private EffectApplier applier = new EffectApplier() {};

        private Builder(String id) {
            this.id = Objects.requireNonNull(id, "id");
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder stackingPolicy(EffectStackingPolicy stackingPolicy) {
            this.stackingPolicy = stackingPolicy;
            return this;
        }

        public Builder maxAmplifier(int maxAmplifier) {
            this.maxAmplifier = maxAmplifier;
            return this;
        }

        public Builder applier(EffectApplier applier) {
            this.applier = applier;
            return this;
        }

        public EffectType build() {
            return new EffectType(this);
        }
    }
}
