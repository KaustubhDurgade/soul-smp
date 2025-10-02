package dev.soulsmp.core.effect;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class EffectTypes {

    private EffectTypes() {
    }

    public static void registerDefaults(EffectManager manager) {
        manager.registerType(EffectType.builder("burn")
            .displayName("Burn")
            .stackingPolicy(EffectStackingPolicy.REFRESH)
            .applier(new EffectApplier() {
                @Override
                public void onApply(Player player, ActiveEffect effect) {
                    player.setFireTicks((int) Math.max(player.getFireTicks(), effect.remainingTicks()));
                }

                @Override
                public void onTick(Player player, ActiveEffect effect) {
                    player.setFireTicks((int) Math.max(player.getFireTicks(), effect.remainingTicks()));
                }

                @Override
                public void onExpire(Player player, ActiveEffect effect) {
                    player.setFireTicks(0);
                }
            })
            .build());

        manager.registerType(EffectType.builder("bleed")
            .displayName("Bleed")
            .stackingPolicy(EffectStackingPolicy.STACK_DURATION)
            .applier(new EffectApplier() {
                @Override
                public void onTick(Player player, ActiveEffect effect) {
                    double damage = 1.0 + (0.25 * effect.amplifier());
                    player.damage(damage);
                }
            })
            .build());

        manager.registerType(EffectType.builder("stun")
            .displayName("Stun")
            .stackingPolicy(EffectStackingPolicy.REFRESH)
            .applier(new EffectApplier() {
                @Override
                public void onApply(Player player, ActiveEffect effect) {
                    applyPotion(player, effect);
                }

                @Override
                public void onTick(Player player, ActiveEffect effect) {
                    applyPotion(player, effect);
                }

                private void applyPotion(Player player, ActiveEffect effect) {
                    int duration = (int) Math.min(Integer.MAX_VALUE, effect.remainingTicks());
                    int amplifier = Math.min(4, effect.amplifier());
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, amplifier, false, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, duration, amplifier, false, false, false));
                }

                @Override
                public void onExpire(Player player, ActiveEffect effect) {
                    player.removePotionEffect(PotionEffectType.SLOWNESS);
                    player.removePotionEffect(PotionEffectType.MINING_FATIGUE);
                }
            })
            .build());
    }
}
