package dev.soulsmp.core.combat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;
import java.util.UUID;

public final class CombatContext {

    private final EntityDamageByEntityEvent event;
    private double damage;

    CombatContext(EntityDamageByEntityEvent event) {
        this.event = event;
        this.damage = event.getDamage();
    }

    public EntityDamageByEntityEvent event() {
        return event;
    }

    public Optional<Player> attacker() {
        Entity attacker = event.getDamager();
        if (attacker instanceof Player player) {
            return Optional.of(player);
        }
        return Optional.empty();
    }

    public Optional<LivingEntity> victim() {
        if (event.getEntity() instanceof LivingEntity living) {
            return Optional.of(living);
        }
        return Optional.empty();
    }

    public UUID victimId() {
        return event.getEntity().getUniqueId();
    }

    public double originalDamage() {
        return event.getOriginalDamage(EntityDamageByEntityEvent.DamageModifier.BASE);
    }

    public double damage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
