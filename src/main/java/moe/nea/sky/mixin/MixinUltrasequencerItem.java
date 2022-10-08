package moe.nea.sky.mixin;

import moe.nea.sky.features.gui.Enchanting;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "io.github.moulberry.notenoughupdates.miscfeatures.EnchantingSolvers$UltrasequencerItem")
public class MixinUltrasequencerItem implements Enchanting.AccessibleUltrasequencerItem {
    @Shadow
    private ItemStack stack;

    @Shadow
    private int containerIndex;

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
