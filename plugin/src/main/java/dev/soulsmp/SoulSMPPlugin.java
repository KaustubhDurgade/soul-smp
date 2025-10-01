package dev.soulsmp;

import dev.soulsmp.wrath.WrathAbilityManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoulSMPPlugin extends JavaPlugin {

    private WrathAbilityManager wrathAbilityManager;

    @Override
    public void onEnable() {
        this.wrathAbilityManager = new WrathAbilityManager(this);
        this.wrathAbilityManager.register();
    }

    @Override
    public void onDisable() {
        if (this.wrathAbilityManager != null) {
            this.wrathAbilityManager.unregister();
        }
    }
}
