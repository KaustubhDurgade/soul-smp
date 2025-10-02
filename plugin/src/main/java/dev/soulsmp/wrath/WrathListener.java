package dev.soulsmp.wrath;

import dev.soulsmp.core.message.MessageService;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

final class WrathListener implements Listener {
    private final WrathAbilityManager abilityManager;
    private final WrathHeatManager heatManager;
    private final WrathIgnitionManager ignitionManager;
    private final MessageService messageService;

    WrathListener(WrathAbilityManager abilityManager,
                  WrathHeatManager heatManager,
                  WrathIgnitionManager ignitionManager,
                  MessageService messageService) {
        this.abilityManager = abilityManager;
        this.heatManager = heatManager;
        this.ignitionManager = ignitionManager;
        this.messageService = messageService;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (!(damager instanceof Player player)) {
            return;
        }
        if (!this.abilityManager.hasPlayer(player)) {
            return;
        }

        LivingEntity victim = event.getEntity() instanceof LivingEntity living ? living : null;
        if (victim != null) {
            this.ignitionManager.handleAttack(player, victim);
        }

        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (player.getHealth() <= maxHealth * 0.6) {
            double adjusted = event.getDamage() * 1.15;
            event.setDamage(adjusted);
            this.heatManager.addHeat(player, 1);
            player.spawnParticle(Particle.SMALL_FLAME, player.getLocation().add(0, 1, 0), 5, 0.2, 0.4, 0.2, 0.01);
        }

        this.heatManager.recordCombat(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!this.abilityManager.hasPlayer(player)) {
            return;
        }
        this.abilityManager.removePlayer(player);
        messageService.send(player, "wrath.logout-cleared");
    }
}
