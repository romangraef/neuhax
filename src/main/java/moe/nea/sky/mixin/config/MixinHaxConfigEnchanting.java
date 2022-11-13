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
    @ConfigEditorSlider(minValue = 0, maxValue = 500, minStep = 10)
    public int neuHaxTimeout = 50;

    @Override
    public int getNeuHaxSolveKeybinding() {
        return neuHaxSolveKeybinding;
    }

    @Override
    public int getNeuHaxTimeout() {
        return neuHaxTimeout;
    }
}
