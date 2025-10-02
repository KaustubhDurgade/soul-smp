package dev.soulsmp.core.combat;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.Optional;

public final class CombatHealContext {

    private final EntityRegainHealthEvent event;

    CombatHealContext(EntityRegainHealthEvent event) {
        this.event = event;
    }

    public EntityRegainHealthEvent event() {
        return event;
    }

    public Optional<LivingEntity> target() {
        if (event.getEntity() instanceof LivingEntity living) {
            return Optional.of(living);
        }
        return Optional.empty();
    }

    public double amount() {
        return event.getAmount();
    }

    public void setAmount(double amount) {
        event.setAmount(amount);
    }
}
