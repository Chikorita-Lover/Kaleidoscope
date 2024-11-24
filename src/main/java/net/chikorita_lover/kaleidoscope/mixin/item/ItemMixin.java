package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract Item asItem();

    @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
    private int modifyMaxCount(int maxCount) {
        Item item = this.asItem();
        if (item instanceof BannerItem || item instanceof BannerPatternItem || item instanceof SignItem || item instanceof MusicDiscItem || item instanceof SnowballItem || item instanceof EggItem) {
            return 64;
        }
        return maxCount;
    }

    @ModifyReturnValue(method = "isFireproof", at = @At("RETURN"))
    private boolean isFireproof(boolean fireproof) {
        return fireproof || this.asItem() == Items.BLAZE_ROD || this.asItem() == Items.BLAZE_POWDER || this.asItem() == Items.MAGMA_CREAM;
    }
}
