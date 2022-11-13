/*
 * Copyright (C) 2022 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.mixin;

import moe.nea.sky.features.gui.Enchanting;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "io.github.moulberry.notenoughupdates.miscfeatures.EnchantingSolvers$UltrasequencerItem")
public class MixinUltrasequencerItem implements Enchanting.AccessibleUltrasequencerItem {
    @Shadow
    ItemStack stack;

    @Shadow
    int containerIndex;

    @NotNull
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getContainerIndex() {
        return containerIndex;
    }
}
