/*
 * Copyright (C) 2023 Linnea Gräf
 *
 * This file is part of NEUHax.
 *
 * NEUHax is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * NEUHax is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with NEUHax. If not, see <https://www.gnu.org/licenses/>.
 */

package moe.nea.sky.mixin;

import moe.nea.sky.events.YawRotateEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    public float rotationYaw;

    @Shadow public abstract void setAngles(float yaw, float pitch);

    @Inject(method = "setAngles", at = @At("HEAD"), cancellable = true)
    public void neuhaxOnSetAngles(float yaw, float pitch, CallbackInfo ci) {
        Entity $this = (Entity) (Object) this;
        if (yaw != 0F && $this == Minecraft.getMinecraft().thePlayer) {
            YawRotateEvent yawRotateEvent = new YawRotateEvent(yaw, this.rotationYaw);
            MinecraftForge.EVENT_BUS.post(yawRotateEvent);
            if (yawRotateEvent.isCanceled()) {
                ci.cancel();
                this.setAngles(0, pitch);
            }
        }
    }

}
