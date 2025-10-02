package dev.soulsmp.core.resource;

import dev.soulsmp.core.player.PlayerProfile;

public interface ResourceTickHandler {

    default void onPassiveTick(PlayerProfile profile, ResourceDefinition definition, ResourceManager manager) {
    }

    default void onCombatTick(PlayerProfile profile, ResourceDefinition definition, ResourceManager manager) {
    }
}
