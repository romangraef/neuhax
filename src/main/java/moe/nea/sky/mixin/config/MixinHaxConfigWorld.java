/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

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
    @ConfigOption(name = "Wallhacks", desc = "See highlighted blocks through walls")
    @ConfigAccordionId(id = 10000)
    @ConfigEditorBoolean
    public boolean neuHaxMushroomWallhacks = true;

    @Override
    public boolean getNeuHaxWallhacks() {
        return this.neuHaxMushroomWallhacks;
    }

    @Override
    public void setNeuHaxWallhacks(boolean b) {
        this.neuHaxMushroomWallhacks = b;
    }
}
