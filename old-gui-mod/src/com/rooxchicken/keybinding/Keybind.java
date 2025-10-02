/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
 *  net.minecraft.class_304
 *  net.minecraft.class_310
 *  net.minecraft.class_3675$class_307
 */
package com.rooxchicken.keybinding;

import com.rooxchicken.client.KaisenClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;

public class Keybind {
    public class_304 binding;
    public String command;
    private boolean wasPressed = false;

    public Keybind(String category, String name, int GLFWkey, String _command) {
        this.command = _command;
        this.binding = KeyBindingHelper.registerKeyBinding((class_304)new class_304(name, class_3675.class_307.field_1668, GLFWkey, category));
        class_310.method_1551();
    }

    public void CheckKey() {
        if (this.binding.method_1434() && !this.wasPressed) {
            KaisenClient.sendChatCommand(this.command);
            this.wasPressed = true;
        } else if (!this.binding.method_1434() && this.wasPressed) {
            this.wasPressed = false;
        }
    }
}

