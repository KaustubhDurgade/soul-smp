package dev.soulsmp.core.effect;

import org.bukkit.entity.Player;

public interface EffectApplier {

    default void onApply(Player player, ActiveEffect effect) {
    }

    default void onTick(Player player, ActiveEffect effect) {
    }

    default void onExpire(Player player, ActiveEffect effect) {
    }
}
