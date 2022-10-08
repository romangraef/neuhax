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
