/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 */
package com.rooxchicken.screen;

import com.rooxchicken.screen.ConfigScreen;
import java.util.Scanner;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;

public class Element {
    public String Name;
    public String Description;
    public boolean Enabled;
    public boolean Visible = true;
    public boolean HasLines = true;
    public double SmallestSize = 0.2;
    public double SnapIncrement = 4.0;
    public String KeyName;
    public boolean SettingsOpen;
    public int MouseStatus = -2;
    public int ManipulationStatus = -1;
    public int PositionX = -1;
    public int PositionY = -1;
    public double Scale = -1.0;
    public double ScaleX;
    public double ScaleY;
    protected int x1Mod = 0;
    protected int x2Mod = 64;
    protected int y1Mod = 0;
    protected int y2Mod = 64;
    protected int oldMouseX = 0;
    protected int oldMouseY = 0;
    protected int oldPositionX = 0;
    protected int oldPositionY = 0;
    protected double oldScale = 0.0;
    protected int length;
    public boolean rotation = false;

    public Element() {
        this.reset();
    }

    public void HandleLines(ConfigScreen screen, class_332 context, class_327 textRenderer, int mouseX, int mouseY) {
        class_310 client = class_310.method_1551();
        double scalingFactor = client.method_22683().method_4495();
        int x1 = this.PositionX + this.x1Mod;
        int x2 = (int)((double)this.PositionX + (double)this.x2Mod * this.Scale);
        int y1 = this.PositionY + this.y1Mod;
        int y2 = (int)((double)this.PositionY + (double)this.y2Mod * this.Scale);
        if (this.ManipulationStatus == 2 && this.MouseStatus > -1) {
            context.method_25294(x1, y1, x1 + 8, y1 + 8, -65308);
        } else {
            context.method_25294(x1, y1, x1 + 8, y1 + 8, -1);
        }
        context.method_25292(x1, x2, y1, -1);
        context.method_25292(x1, x2, y2, -1);
        context.method_25301(x1, y1, y2, -1);
        context.method_25301(x2, y1, y2, -1);
        if (!screen.ObjectSelected && this.MouseStatus > -1 && this.ManipulationStatus == -1) {
            if (this.AABBCheck(mouseX, mouseY, x1, x2, y1, y2)) {
                this.ManipulationStatus = 1;
                if (this.AABBCheck(mouseX, mouseY, x1, x1 + 8, y1, y1 + 8)) {
                    this.ManipulationStatus = 2;
                    this.oldScale = this.Scale;
                    this.length = x2 - x1;
                    this.oldMouseX = mouseX;
                    this.oldMouseY = mouseY;
                    this.oldPositionX = this.PositionX;
                    this.oldPositionY = this.PositionY;
                }
                screen.ObjectSelected = true;
            } else {
                this.ManipulationStatus = 0;
            }
        } else if (this.MouseStatus == -1) {
            this.ManipulationStatus = -1;
            screen.ObjectSelected = false;
        }
        if (this.ManipulationStatus == 1) {
            this.PositionX += mouseX - this.oldMouseX;
            this.PositionY += mouseY - this.oldMouseY;
        }
        if (this.ManipulationStatus == 2) {
            this.HandleScaling(mouseX, mouseY);
            if (this.Scale < this.SmallestSize) {
                this.Scale = this.SmallestSize;
            }
            return;
        }
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
    }

    public void HandleScaling(int mouseX, int mouseY) {
        double val;
        double mScale = Math.max((double)(mouseX - this.oldMouseX) + 0.0, (double)(mouseY - this.oldMouseY) + 0.0);
        this.Scale = val = this.oldScale * (((double)this.length - mScale) / (double)this.length);
        if (this.Scale < this.SmallestSize) {
            this.Scale = this.SmallestSize;
            return;
        }
        if (this.MouseStatus == 1) {
            this.Scale = (double)((int)(this.Scale * this.SnapIncrement)) / this.SnapIncrement;
        }
        this.PositionX = (int)((double)this.oldPositionX + (this.oldScale - this.Scale) * (double)this.x2Mod);
        this.PositionY = (int)((double)this.oldPositionY + (this.oldScale - this.Scale) * (double)this.y2Mod);
    }

    protected boolean AABBCheck(int mouseX, int mouseY, int x1, int x2, int y1, int y2) {
        return mouseX > x1 && mouseX < x2 && mouseY < y2 && mouseY > y1;
    }

    public void reset() {
        this.x1Mod = -2;
        this.x2Mod = 182;
        this.y1Mod = -5;
        this.y2Mod = 42;
        this.PositionX = 10;
        this.PositionY = 10;
        this.Scale = 1.0;
        this.SmallestSize = 0.15;
    }

    public String save() {
        return this.PositionX + "\n" + this.PositionY + "\n" + this.Scale + "\n" + this.rotation + "\n";
    }

    public void load(Scanner input) {
        this.PositionX = Integer.parseInt(input.nextLine());
        this.PositionY = Integer.parseInt(input.nextLine());
        this.Scale = Double.parseDouble(input.nextLine());
        this.rotation = Boolean.parseBoolean(input.nextLine());
    }
}

