/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_7594
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.rooxchicken.mixin;

import com.rooxchicken.data.HandleData;
import net.minecraft.class_2561;
import net.minecraft.class_7594;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_7594.class})
public class OnChatMessage {
    @Inject(method={"onGameMessage(Lnet/minecraft/text/Text;Z)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void onGameMessage(class_2561 message, boolean overlay, CallbackInfo info) {
        String content = message.getString();
        if (content.length() < 7) {
            return;
        }
        if (content.substring(0, 6).equals("ksn612")) {
            HandleData.parseData(content);
            info.cancel();
        }
    }
}

