/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.rooxchicken.data;

import com.rooxchicken.client.KaisenClient;
import net.minecraft.class_310;

public class HandleData {
    public static int cursedEnergy = -1;
    public static String cursedTechnique1Name = "";
    public static String cursedTechnique2Name = "";
    public static String cursedTechnique1FileName = "";
    public static String cursedTechnique2FileName = "";
    public static int color1 = 0xFFFFFF;
    public static int color2 = 0xFFFFFF;
    public static int cooldown1 = -1;
    public static int cooldown2 = -1;
    public static int cooldown1Max = -1;
    public static int cooldown2Max = -1;
    public static int cost1 = -1;
    public static int cost2 = -1;

    public static void parseData(String msg) {
        class_310 client = class_310.method_1551();
        String[] data = msg.split("_");
        int mode = Integer.parseInt(data[1]);
        switch (mode) {
            case 0: {
                KaisenClient.sendChatCommand("hdn_verifymod");
                KaisenClient.mainRender = true;
                break;
            }
            case 1: {
                cursedEnergy = Integer.parseInt(data[2]);
                break;
            }
            case 2: {
                if (data[2].equals("0")) {
                    cursedTechnique1Name = data[3];
                    cursedTechnique1FileName = cursedTechnique1Name.toLowerCase().replace(" ", "");
                    color1 = Integer.parseInt(data[4]);
                    cooldown1 = Integer.parseInt(data[5]);
                    cooldown1Max = Integer.parseInt(data[6]) * 20;
                    cost1 = Integer.parseInt(data[7]);
                }
                if (!data[2].equals("1")) break;
                cursedTechnique2Name = data[3];
                cursedTechnique2FileName = cursedTechnique2Name.toLowerCase().replace(" ", "");
                color2 = Integer.parseInt(data[4]);
                cooldown2 = Integer.parseInt(data[5]);
                cooldown2Max = Integer.parseInt(data[6]) * 20;
                cost2 = Integer.parseInt(data[7]);
            }
        }
    }
}

