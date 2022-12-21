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
import io.github.moulberry.notenoughupdates.core.config.annotations.Category;
import io.github.moulberry.notenoughupdates.options.NEUConfig;
import moe.nea.sky.config.HaxConfigNeuConfig;
import moe.nea.sky.config.HaxConfigNeuHax;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NEUConfig.class)
public class MixinHaxConfigNeuConfig implements HaxConfigNeuConfig {

    @Expose
    @Category(
            name = "NEU Hax",
            desc = "NEU Hax specific settings"
    )
    public HaxConfigNeuHax neuHax = new HaxConfigNeuHax();

    @NotNull
    @Override
    public HaxConfigNeuHax getNeuHax() {
        return neuHax;
    }
}
