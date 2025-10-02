package dev.soulsmp.core.soul;

import dev.soulsmp.SoulSMPPlugin;
import dev.soulsmp.core.message.MessageService;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Plays a dramatic "soul reveal" animation for first-time assignments.
 */
public final class SoulRevealAnimationService {

    private static final Duration FADE_IN = Duration.ofMillis(0);
    private static final Duration STAY = Duration.ofMillis(900);
    private static final Duration FADE_OUT = Duration.ofMillis(200);

    private final SoulSMPPlugin plugin;
    private final SoulCatalog soulCatalog;
    private final MessageService messageService;
    private final Random random = new Random();

    public SoulRevealAnimationService(SoulSMPPlugin plugin, SoulCatalog soulCatalog, MessageService messageService) {
        this.plugin = plugin;
        this.soulCatalog = soulCatalog;
        this.messageService = messageService;
    }

    public void playReveal(Player player, SoulDefinition finalSoul) {
        if (!player.isOnline()) {
            return;
        }

        List<SoulDefinition> rotations = new ArrayList<>(soulCatalog.list());
        if (rotations.isEmpty()) {
            rotations.add(finalSoul);
        }

        int totalSteps = Math.min(rotations.size() * 3, 24);
        Title.Times times = Title.Times.times(FADE_IN, STAY, FADE_OUT);

        new BukkitRunnable() {
            int step = 0;

            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                boolean finale = step >= totalSteps;
                SoulDefinition shownSoul = finale ? finalSoul : rotations.get(random.nextInt(rotations.size()));

                Component titleText = Component.text("Soul Alignment", NamedTextColor.GOLD);
                Component subtitle = Component.text(shownSoul.displayName(), finale ? NamedTextColor.GREEN : NamedTextColor.YELLOW);

                player.showTitle(Title.title(titleText, subtitle, times));
                Sound sound = finale ? Sound.UI_TOAST_CHALLENGE_COMPLETE : Sound.UI_TOAST_IN;
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);

                if (finale) {
                    messageService.send(player, "soul.assignment.assigned", java.util.Map.of(
                        "soul", shownSoul.displayName(),
                        "description", shownSoul.description()
                    ));
                    cancel();
                    return;
                }

                step++;
            }
        }.runTaskTimer(plugin, 0L, 15L);
    }
}
