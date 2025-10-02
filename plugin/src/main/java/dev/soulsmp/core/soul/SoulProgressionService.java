package dev.soulsmp.core.soul;

import dev.soulsmp.core.message.MessageService;
import dev.soulsmp.core.player.PlayerProfile;
import dev.soulsmp.core.player.PlayerProfileManager;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

/**
 * Manages soul leveling and progression.
 * Abilities unlock at different levels.
 */
public final class SoulProgressionService {

    // Level requirements for each ability type
    public static final int PASSIVE_UNLOCK_LEVEL = 0;  // Always available
    public static final int TACTICAL_UNLOCK_LEVEL = 1;
    public static final int MOVEMENT_UNLOCK_LEVEL = 2;
    public static final int ULTIMATE_UNLOCK_LEVEL = 3;
    public static final int FINAL_STAND_UNLOCK_LEVEL = 5;

    // XP required per level (cumulative)
    private static final int[] XP_PER_LEVEL = {
        0,      // Level 0
        100,    // Level 1
        250,    // Level 2
        500,    // Level 3
        1000,   // Level 4
        2000    // Level 5 (max)
    };

    private final PlayerProfileManager profileManager;
    private final MessageService messageService;

    public SoulProgressionService(PlayerProfileManager profileManager, MessageService messageService) {
        this.profileManager = profileManager;
        this.messageService = messageService;
    }

    /**
     * Award experience to a player and handle level-ups.
     */
    public void awardExperience(Player player, int amount, String reason) {
        PlayerProfile profile = profileManager.getOrCreate(player.getUniqueId());
        if (profile.getSoulId() == null) {
            return; // No soul assigned
        }

        int oldLevel = profile.getSoulLevel();
        profile.addExperience(amount);

        // Check for level up
        int newLevel = calculateLevel(profile.getSoulExperience());
        if (newLevel > oldLevel && newLevel <= 5) {
            profile.setSoulLevel(newLevel);
            messageService.send(player, "soul.level-up", Map.of(
                "level", String.valueOf(newLevel),
                "reason", reason
            ));
            announceUnlocks(player, newLevel);
        }
    }

    /**
     * Calculate level based on total XP.
     */
    private int calculateLevel(int totalXp) {
        for (int level = XP_PER_LEVEL.length - 1; level >= 0; level--) {
            if (totalXp >= XP_PER_LEVEL[level]) {
                return level;
            }
        }
        return 0;
    }

    /**
     * Get XP needed for next level.
     */
    public int getXpForNextLevel(int currentLevel) {
        if (currentLevel >= 5) {
            return -1; // Max level
        }
        return XP_PER_LEVEL[currentLevel + 1];
    }

    /**
     * Get XP progress toward next level.
     */
    public int getXpProgress(PlayerProfile profile) {
        int currentLevel = profile.getSoulLevel();
        int currentXp = profile.getSoulExperience();
        
        if (currentLevel >= 5) {
            return 0; // Max level
        }
        
        int levelXp = XP_PER_LEVEL[currentLevel];
        return currentXp - levelXp;
    }

    /**
     * Get XP needed to reach next level from current position.
     */
    public int getXpToNextLevel(PlayerProfile profile) {
        int currentLevel = profile.getSoulLevel();
        if (currentLevel >= 5) {
            return 0;
        }
        
        int nextLevelXp = XP_PER_LEVEL[currentLevel + 1];
        int currentXp = profile.getSoulExperience();
        return nextLevelXp - currentXp;
    }

    public int getXpFloorForLevel(int level) {
        if (level <= 0) {
            return XP_PER_LEVEL[0];
        }
        int capped = Math.min(level, XP_PER_LEVEL.length - 1);
        return XP_PER_LEVEL[capped];
    }

    /**
     * Check if a player has unlocked a specific ability.
     */
    public boolean hasUnlocked(UUID playerId, AbilityType abilityType) {
        PlayerProfile profile = profileManager.getOrCreate(playerId);
        int level = profile.getSoulLevel();
        
        return switch (abilityType) {
            case PASSIVE -> level >= PASSIVE_UNLOCK_LEVEL;
            case TACTICAL -> level >= TACTICAL_UNLOCK_LEVEL;
            case MOVEMENT -> level >= MOVEMENT_UNLOCK_LEVEL;
            case ULTIMATE -> level >= ULTIMATE_UNLOCK_LEVEL;
            case FINAL_STAND -> level >= FINAL_STAND_UNLOCK_LEVEL;
        };
    }

    /**
     * Announce newly unlocked abilities.
     */
    private void announceUnlocks(Player player, int level) {
        switch (level) {
            case TACTICAL_UNLOCK_LEVEL -> 
                messageService.send(player, "soul.unlock.tactical");
            case MOVEMENT_UNLOCK_LEVEL -> 
                messageService.send(player, "soul.unlock.movement");
            case ULTIMATE_UNLOCK_LEVEL -> 
                messageService.send(player, "soul.unlock.ultimate");
            case FINAL_STAND_UNLOCK_LEVEL -> 
                messageService.send(player, "soul.unlock.final-stand");
        }
    }

    /**
     * Ability types for progression system.
     */
    public enum AbilityType {
        PASSIVE,
        TACTICAL,
        MOVEMENT,
        ULTIMATE,
        FINAL_STAND
    }
}
