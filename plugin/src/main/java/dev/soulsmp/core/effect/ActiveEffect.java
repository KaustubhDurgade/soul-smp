package dev.soulsmp.core.effect;

import java.time.Duration;

public final class ActiveEffect {

    private final EffectType type;
    private int amplifier;
    private long remainingTicks;

    ActiveEffect(EffectType type, int amplifier, long durationTicks) {
        this.type = type;
        this.amplifier = amplifier;
        this.remainingTicks = durationTicks;
    }

    public EffectType type() {
        return type;
    }

    public int amplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public long remainingTicks() {
        return remainingTicks;
    }

    public void addTicks(long ticks) {
        this.remainingTicks += ticks;
    }

    public void resetTicks(long ticks) {
        this.remainingTicks = ticks;
    }

    public boolean tick() {
        if (remainingTicks <= 0) {
            return true;
        }
        remainingTicks--;
        return remainingTicks <= 0;
    }

    public Duration remainingDuration() {
        return Duration.ofMillis(remainingTicks * 50L);
    }
}
