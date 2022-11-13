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

import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.miscfeatures.world.GenericBlockHighlighter;
import moe.nea.sky.config.HaxConfigWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GenericBlockHighlighter.class)
public class MixinGenericBlockHighlighter {
    @Inject(method = "canPlayerSeeBlock", at = @At("HEAD"), cancellable = true, remap = false)
    public void onCanPlayerSee(double xCoord, double yCoord, double zCoord, CallbackInfoReturnable<Boolean> cir) {
        if (((HaxConfigWorld) NotEnoughUpdates.INSTANCE.config.world).getNeuHaxWallhacks()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canPlayerSeeNearBlocks", at = @At("HEAD"), cancellable = true, remap = false)
    public void onPlayerCanSeeNearBlocks(double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (((HaxConfigWorld) NotEnoughUpdates.INSTANCE.config.world).getNeuHaxWallhacks()) {
            cir.setReturnValue(true);
        }
    }
}
