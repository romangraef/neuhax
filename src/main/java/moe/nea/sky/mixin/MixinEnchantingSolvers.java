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

import io.github.moulberry.notenoughupdates.miscfeatures.EnchantingSolvers;
import moe.nea.sky.features.gui.Enchanting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(EnchantingSolvers.class)
public class MixinEnchantingSolvers implements Enchanting.AccessibleEnchantingSolvers {
    @Shadow
    private static int chronomatronReplayIndex;

    @Shadow
    @Final
    private static List<String> chronomatronOrder;

    @Shadow
    private static int ultrasequencerReplayIndex;

    @Shadow
    @Final
    private static Map<Integer, Enchanting.AccessibleUltrasequencerItem> ultraSequencerOrder;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        Enchanting.solversInstance = (EnchantingSolvers) (Object) this;
    }

    @Override
    public int getChronomatronReplyIndex() {
        return chronomatronReplayIndex;
    }

    @Override
    public void setChronomatronReplyIndex(int i) {
        chronomatronReplayIndex = i;
    }

    @Override
    public List<String> getChronomatronOrder() {
        return chronomatronOrder;
    }

    @Override
    public int getUltrasequencerReplayIndex() {
        return ultrasequencerReplayIndex;
    }

    @Override
    public void setUltrasequencerReplayIndex(int i) {
        ultrasequencerReplayIndex = i;
    }

    @Override
    public Map<Integer, Enchanting.AccessibleUltrasequencerItem> getUltraSequencerOrder() {
        return ultraSequencerOrder;
    }
}
