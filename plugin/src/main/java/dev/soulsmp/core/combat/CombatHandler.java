package dev.soulsmp.core.combat;

public interface CombatHandler {

    default void onDamage(CombatContext context) {
    }

    default void onHeal(CombatHealContext context) {
    }
}
