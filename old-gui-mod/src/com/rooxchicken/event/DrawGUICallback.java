/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_4587
 *  net.minecraft.class_5250
 *  net.minecraft.class_5481
 *  net.minecraft.class_7417
 *  net.minecraft.class_9779
 */
package com.rooxchicken.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rooxchicken.client.KaisenClient;
import com.rooxchicken.data.HandleData;
import com.rooxchicken.screen.Element;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import net.minecraft.class_7417;
import net.minecraft.class_9779;

public class DrawGUICallback
implements HudRenderCallback {
    private class_4587 matrixStack;
    private class_2960 ceBarTex = class_2960.method_60655((String)"kaisen", (String)"textures/gui/cebaricons.png");
    private class_2960 ceSquare1 = class_2960.method_60655((String)"kaisen", (String)"textures/gui/cesquare0.png");
    private class_2960 ceSquare2 = class_2960.method_60655((String)"kaisen", (String)"textures/gui/cesquare1.png");
    private class_2960 ceBarFillTex = class_2960.method_60655((String)"kaisen", (String)"textures/gui/cebarfill.png");

    public void onHudRender(class_332 context, class_9779 tickDelta) {
        if (!KaisenClient.mainRender) {
            return;
        }
        class_310 client = class_310.method_1551();
        class_327 textRenderer = client.field_1772;
        Element element = KaisenClient.element;
        this.startScaling(context, 1.4 * element.Scale);
        int x = (int)((double)element.PositionX / (1.4 * element.Scale));
        int y = (int)((double)element.PositionY / (1.4 * element.Scale));
        class_5250 ceName1 = class_5250.method_43477((class_7417)class_2561.method_30163((String)HandleData.cursedTechnique1Name).method_10851());
        ceName1.method_10862(ceName1.method_10866().method_36139(HandleData.color1));
        class_5250 ceName2 = class_5250.method_43477((class_7417)class_2561.method_30163((String)HandleData.cursedTechnique2Name).method_10851());
        ceName2.method_10862(ceName2.method_10866().method_36139(HandleData.color2));
        context.method_25290(this.ceBarFillTex, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
        context.method_25290(class_2960.method_60655((String)"kaisen", (String)("textures/gui/icons/" + HandleData.cursedTechnique1FileName + ".png")), x + 4, y + 8, 0.0f, 0.0f, 18, 18, 18, 18);
        context.method_25290(class_2960.method_60655((String)"kaisen", (String)("textures/gui/icons/" + HandleData.cursedTechnique2FileName + ".png")), x + 109, y + 8, 18.0f, 0.0f, 18, 18, 18, 18);
        context.method_25294(x + 26, y + 8, x + 26 + 78, y + 18, -13421773);
        context.method_25294(x + 26, y + 8, x + 26 + (int)(78.0 * ((double)HandleData.cursedEnergy / 300.0)), y + 18, -12100119);
        if (HandleData.cooldown1 > 0) {
            RenderSystem.enableBlend();
            context.method_25294(x + 4, y + 9 + 17 - (int)(17.0 * (1.0 * (double)HandleData.cooldown1 / (double)HandleData.cooldown1Max)), x + 22, y + 26, -2013265920);
            RenderSystem.setShaderColor((float)1.0f, (float)0.2f, (float)0.2f, (float)1.0f);
            context.method_25290(this.ceSquare1, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            context.method_25290(this.ceSquare1, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
        }
        if (HandleData.cooldown2 > 0) {
            RenderSystem.enableBlend();
            context.method_25294(x + 110, y + 9 + 17 - (int)(17.0 * (1.0 * (double)HandleData.cooldown2 / (double)HandleData.cooldown2Max)), x + 126, y + 26, -2013265920);
            RenderSystem.setShaderColor((float)1.0f, (float)0.2f, (float)0.2f, (float)1.0f);
            context.method_25290(this.ceSquare2, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            context.method_25290(this.ceSquare2, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
        }
        RenderSystem.enableBlend();
        context.method_25290(this.ceBarTex, x, y, 0.0f, 0.0f, 130, 30, 130, 30);
        this.stopScaling(context);
        this.startScaling(context, 0.66 * element.Scale);
        x = (int)((double)element.PositionX / (0.66 * element.Scale)) - 12;
        y = (int)((double)element.PositionY / (0.66 * element.Scale)) - 12;
        class_5481 cursed1 = !HandleData.cursedTechnique1Name.equals("None") ? class_5481.method_30742((class_5481)ceName1.method_30937(), (class_5481)class_2561.method_30163((String)(" | " + HandleData.cost1 + "CE")).method_30937()) : ceName1.method_30937();
        class_5481 cursed2 = !HandleData.cursedTechnique2Name.equals("None") ? class_5481.method_30742((class_5481)ceName2.method_30937(), (class_5481)class_2561.method_30163((String)(" | " + HandleData.cost2 + "CE")).method_30937()) : ceName2.method_30937();
        context.method_35719(textRenderer, cursed1, x + 150, y + 20, -1);
        context.method_35719(textRenderer, cursed2, x + 150, y + 57, -1);
        context.method_27534(textRenderer, class_2561.method_30163((String)(HandleData.cursedEnergy + "/300")), x + 150, y + 38, -1);
        if (HandleData.cooldown1 > 0) {
            context.method_27534(textRenderer, class_2561.method_30163((String)(HandleData.cooldown1 / 20 + "s")), x + 38, y + 44, -1);
        }
        if (HandleData.cooldown2 > 0) {
            context.method_27534(textRenderer, class_2561.method_30163((String)(HandleData.cooldown2 / 20 + "s")), x + 262, y + 44, -1);
        }
        this.stopScaling(context);
    }

    private void startScaling(class_332 context, double scale) {
        this.matrixStack = context.method_51448();
        this.matrixStack.method_22903();
        this.matrixStack.method_22905((float)scale, (float)scale, (float)scale);
    }

    private void stopScaling(class_332 context) {
        this.matrixStack.method_22909();
    }
}

