package moe.nea.sky.mixin.config;

import com.google.gson.annotations.Expose;
import io.github.moulberry.notenoughupdates.core.config.annotations.*;
import io.github.moulberry.notenoughupdates.options.seperateSections.Enchanting;
import moe.nea.sky.config.HaxConfigEnchanting;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Enchanting.class)
public class MixinHaxConfigEnchanting implements HaxConfigEnchanting {
    @ConfigOption(name = "NEU Hax", desc = "NEU Hax enhancements to enchanting")
    @ConfigEditorAccordion(id = 10000)
    public boolean neuHaxAccordion = false;

    @Expose
    @ConfigOption(name = "Auto solve Keybinding", desc = "Press this button to click on the next correct item")
    @ConfigAccordionId(id = 10000)
    @ConfigEditorKeybind(defaultKey = Keyboard.KEY_SPACE)
    public int neuHaxSolveKeybinding = Keyboard.KEY_SPACE;

    @Expose
    @ConfigOption(name = "Auto solve speed", desc = "How fast you can press the button to solve, in milliseconds")
    @ConfigEditorSlider(minValue = 10, maxValue = 500, minStep = 10)
    public int neuHaxTimeout = 100;

    @Override
    public int getNeuHaxSolveKeybinding() {
        return neuHaxSolveKeybinding;
    }

    @Override
    public int getNeuHaxTimeout() {
        return neuHaxTimeout;
    }
}
