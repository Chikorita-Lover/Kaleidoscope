package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.MaxItemCountRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract Item asItem();

    @Inject(method = "getMaxCount", at = @At("RETURN"), cancellable = true)
    private void getMaxCount(CallbackInfoReturnable<Integer> info) {
        Item item = Item.class.cast(this);
        if (MaxItemCountRegistry.MAX_COUNTS.containsKey(item)) {
            info.setReturnValue(MaxItemCountRegistry.MAX_COUNTS.get(item));
            return;
        }
        if (item instanceof MusicDiscItem) {
            info.setReturnValue(64);
        }
        if (item instanceof StewItem) {
            info.setReturnValue(16);
        }
    }

    @Inject(method = "getRarity", at = @At("RETURN"), cancellable = true)
    public void getRarity(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
        if (this.asItem() == Items.LINGERING_POTION) {
            cir.setReturnValue(stack.hasEnchantments() ? Rarity.RARE : Rarity.UNCOMMON);
        }
    }

    @Inject(method = "isFireproof", at = @At("HEAD"), cancellable = true)
    public void isFireproof(CallbackInfoReturnable<Boolean> callbackInfo) {
        if (this.asItem() == Items.BLAZE_ROD || this.asItem() == Items.BLAZE_POWDER) {
            callbackInfo.setReturnValue(true);
        }
    }
}
