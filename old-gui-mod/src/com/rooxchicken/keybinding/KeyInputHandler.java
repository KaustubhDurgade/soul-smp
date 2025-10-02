/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.minecraft.class_2561
 *  net.minecraft.class_304
 *  net.minecraft.class_437
 */
package com.rooxchicken.keybinding;

import com.rooxchicken.client.KaisenClient;
import com.rooxchicken.keybinding.Keybind;
import com.rooxchicken.screen.ConfigScreen;
import java.util.ArrayList;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_2561;
import net.minecraft.class_304;
import net.minecraft.class_437;

public class KeyInputHandler {
    private static ArrayList<Keybind> bindings;
    private static class_304 configKey;

    public static void registerKeyInputs(ArrayList<Keybind> _bindings, KaisenClient _client) {
        bindings = _bindings;
        KeyBindingHelper.registerKeyBinding((class_304)configKey);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!KaisenClient.mainRender) {
                return;
            }
            for (Keybind bind : bindings) {
                bind.CheckKey();
            }
            if (configKey.method_1436()) {
                client.method_1507((class_437)new ConfigScreen(class_2561.method_30163((String)"Config Screen"), _client));
            }
        });
    }

    static {
        configKey = new class_304("key.kaisen.config", 67, "key.category.kaisen");
    }
}

