/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_310
 *  net.minecraft.class_634
 */
package com.rooxchicken.client;

import com.rooxchicken.Kaisen;
import com.rooxchicken.event.DrawGUICallback;
import com.rooxchicken.keybinding.KeyInputHandler;
import com.rooxchicken.keybinding.Keybind;
import com.rooxchicken.screen.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_310;
import net.minecraft.class_634;

public class KaisenClient
implements ClientModInitializer {
    public ArrayList<Keybind> keybinds;
    private String category = "key.category.kaisen";
    public static boolean mainRender = false;
    public static Element element = new Element();
    public DrawGUICallback guiCallback;

    public void onInitializeClient() {
        this.keybinds = new ArrayList();
        this.keybinds.add(new Keybind(this.category, "key.kaisen.ability1", 90, "hdn_ability1"));
        this.keybinds.add(new Keybind(this.category, "key.kaisen.ability2", 88, "hdn_ability2"));
        this.guiCallback = new DrawGUICallback();
        HudRenderCallback.EVENT.register((Object)this.guiCallback);
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            mainRender = false;
        });
        KeyInputHandler.registerKeyInputs(this.keybinds, this);
        KaisenClient.load();
    }

    public static void sendChatCommand(String msg) {
        if (!mainRender && !msg.equals("hdn_verifymod")) {
            return;
        }
        class_310 client = class_310.method_1551();
        class_634 handler = client.method_1562();
        if (handler == null) {
            return;
        }
        handler.method_45730(msg);
    }

    public static void load() {
        File file = new File("kaisen.cfg");
        if (!file.exists()) {
            KaisenClient.save();
            return;
        }
        try {
            Scanner scan = new Scanner(file);
            element.load(scan);
            scan.close();
        }
        catch (FileNotFoundException e) {
            Kaisen.LOGGER.error("Failed to open config file.", (Throwable)e);
        }
    }

    public static void save() {
        File file = new File("kaisen.cfg");
        try {
            FileWriter write = new FileWriter(file);
            write.write(element.save());
            write.close();
        }
        catch (IOException e) {
            Kaisen.LOGGER.error("Failed to save config file.", (Throwable)e);
        }
    }
}

