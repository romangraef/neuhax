package moe.nea.sky.mixin.config;

import com.google.gson.annotations.Expose;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigAccordionId;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorAccordion;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorBoolean;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigOption;
import io.github.moulberry.notenoughupdates.options.seperateSections.WorldConfig;
import moe.nea.sky.config.HaxConfigWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldConfig.class)
public class MixinHaxConfigWorld implements HaxConfigWorld {
    @ConfigOption(name = "NEU Hax", desc = "NEU Hax enhancements to the world")
    @ConfigEditorAccordion(id = 10000)
    public boolean neuHaxAccordion = false;

    @Expose
    @ConfigOption(name = "Mushroom Wallhacks", desc = "See glowing mushrooms through walls")
    @ConfigAccordionId(id = 10000)
    @ConfigEditorBoolean
    public boolean neuHaxMushroomWallhacks = true;

    @Override
    public boolean getNeuHaxMushroomWallhacks() {
        return this.neuHaxMushroomWallhacks;
    }

    @Override
    public void setNeuHaxMushroomWallhacks(boolean b) {
        this.neuHaxMushroomWallhacks = b;
    }
}
