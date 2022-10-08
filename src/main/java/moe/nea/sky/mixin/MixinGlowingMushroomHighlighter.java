package moe.nea.sky.mixin;

import io.github.moulberry.notenoughupdates.NotEnoughUpdates;
import io.github.moulberry.notenoughupdates.miscfeatures.world.GlowingMushroomHighlighter;
import moe.nea.sky.config.HaxConfigWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowingMushroomHighlighter.class)
public class MixinGlowingMushroomHighlighter {
    @Inject(method = "canPlayerSee", at = @At("HEAD"), cancellable = true, remap = false)
    public void onCanPlayerSee(double xCoord, double yCoord, double zCoord, CallbackInfoReturnable<Boolean> cir) {
        if (((HaxConfigWorld) NotEnoughUpdates.INSTANCE.config.world).getNeuHaxMushroomWallhacks()) {
            cir.setReturnValue(true);
        }
    }
}
