package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceFuelSlot.class)
public class FurnaceFuelSlotMixin {
    @Inject(method = "canInsert", at = @At("RETURN"), cancellable = true)
    public void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        callbackInfo.setReturnValue(callbackInfo.getReturnValue() || stack.isOf(Items.GLASS_BOTTLE));
    }

    @Inject(method = "getMaxItemCount", at = @At("RETURN"), cancellable = true)
    public void getMaxItemCount(ItemStack stack, CallbackInfoReturnable<Integer> callbackInfo) {
        if (stack.isOf(Items.GLASS_BOTTLE)) {
            callbackInfo.setReturnValue(1);
        }
    }
}
