package moe.nea.sky.mixin.config;

import com.google.gson.annotations.Expose;
import io.github.moulberry.notenoughupdates.core.config.annotations.*;
import io.github.moulberry.notenoughupdates.options.seperateSections.Fishing;
import moe.nea.sky.config.HaxConfigFishing;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Fishing.class)
public class MixinHaxConfigFishing implements HaxConfigFishing {
    private static final int NEUHAXACCORDIONID = 10000;
    @ConfigOption(name = "Auto Fishing", desc = "AutoFishing")
    @ConfigEditorAccordion(id = NEUHAXACCORDIONID)
    public boolean neuHaxAutoFishing = false;

    @Expose
    @ConfigOption(
            name = "Enable Auto Fishing",
            desc = "Automatically reel in fish")
    @ConfigAccordionId(id = NEUHAXACCORDIONID)
    @ConfigEditorBoolean()
    public boolean neuHaxAutoFishEnable = false;
    @Expose
    @ConfigOption(
            name = "Minimum Delay",
            desc = "Minimum delay to wait before reeling in the fish after it gets hooked, in ticks")
    @ConfigAccordionId(id = NEUHAXACCORDIONID)
    @ConfigEditorSlider(minValue = 0, maxValue = 40, minStep = 1)
    public int neuHaxAutoFishMinDelay = 2;
    @Expose
    @ConfigOption(
            name = "Maximum Delay",
            desc = "Maximum delay to wait before reeling in the fish after it gets hooked, in ticks")
    @ConfigAccordionId(id = NEUHAXACCORDIONID)
    @ConfigEditorSlider(minValue = 0, maxValue = 40, minStep = 1)
    public int neuHaxAutoFishMaxDelay = 10;

    @Expose
    @ConfigOption(
            name = "Reextend Fishing Rod",
            desc = "Automatically start fishing again after fishing up something")
    @ConfigAccordionId(id = NEUHAXACCORDIONID)
    @ConfigEditorBoolean()
    public boolean neuHaxReengageFishingRod = false;

    @Override
    public boolean getNeuHaxAutoFishEnable() {
        return neuHaxAutoFishEnable;
    }

    @Override
    public boolean getNeuHaxReengageFishingRod() {
        return neuHaxReengageFishingRod;
    }

    @Override
    public int getNeuHaxAutoFishDelayMinimum() {
        return neuHaxAutoFishMinDelay;
    }

    @Override
    public int getNeuHaxAutoFishDelayMaximum() {
        return neuHaxAutoFishMaxDelay;
    }
}
