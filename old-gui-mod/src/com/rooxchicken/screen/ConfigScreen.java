/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_9779
 */
package com.rooxchicken.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rooxchicken.client.KaisenClient;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_9779;

public class ConfigScreen
extends class_437 {
    private KaisenClient kClient;
    private int mouseStatus = -2;
    private int index = 0;
    private int clickAction = -1;
    private class_4185 resetButton;
    public boolean ObjectSelected = false;

    public ConfigScreen(class_2561 title, KaisenClient _client) {
        super(title);
        this.kClient = _client;
    }

    public void method_25426() {
        this.resetButton = class_4185.method_46430((class_2561)class_2561.method_30163((String)"Reset"), button -> KaisenClient.element.reset()).method_46434(this.field_22789 / 2 - 50, this.field_22790 - 30, 100, 20).method_46431();
        this.method_37063((class_364)this.resetButton);
    }

    public void method_25419() {
        super.method_25419();
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        KaisenClient.element.MouseStatus = this.mouseStatus = button;
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        KaisenClient.element.MouseStatus = this.mouseStatus = -1;
        KaisenClient.save();
        return super.method_25406(mouseX, mouseY, button);
    }

    public void method_25393() {
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            class_310.method_1551().method_1507(null);
            return true;
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        super.method_25394(context, mouseX, mouseY, delta);
        class_310 client = class_310.method_1551();
        this.kClient.guiCallback.onHudRender(context, class_9779.field_51955);
        RenderSystem.enableBlend();
        KaisenClient.element.HandleLines(this, context, this.field_22793, mouseX, mouseY);
    }
}

