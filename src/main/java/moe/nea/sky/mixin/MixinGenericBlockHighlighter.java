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
