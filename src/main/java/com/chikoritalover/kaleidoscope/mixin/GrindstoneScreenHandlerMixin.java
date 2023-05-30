package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.CompassItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneScreenHandlerMixin extends ScreenHandler {
    @Shadow
    @Final
    Inventory input;

    @Shadow
    @Final
    private Inventory result;

    @Shadow protected abstract ItemStack grind(ItemStack item, int damage, int amount);

    protected GrindstoneScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "updateResult", at = @At("RETURN"), cancellable = true)
    public void updateResult(CallbackInfo callbackInfo) {
        ItemStack itemStack = this.input.getStack(0);
        ItemStack itemStack2 = this.input.getStack(1);
        boolean bl = CompassItem.hasLodestone(itemStack) || CompassItem.hasLodestone(itemStack2);
        boolean bl2 = !itemStack.isEmpty() && itemStack2.isEmpty();
        if (bl) {
            if (!itemStack.isEmpty() && !itemStack2.isEmpty()) {
                this.result.setStack(0, ItemStack.EMPTY);
            } else {
                ItemStack itemStack3 = bl2 ? itemStack.copy() : itemStack2.copy();
                this.result.setStack(0, this.grind(itemStack3, 0, itemStack3.getCount()));
            }
            this.sendContentUpdates();
            callbackInfo.cancel();
        }
    }

    @Inject(method = "grind", at = @At("RETURN"), cancellable = true)
    public void grind(ItemStack item, int damage, int amount, CallbackInfoReturnable<ItemStack> callbackInfo) {
        ItemStack itemStack = callbackInfo.getReturnValue();
        if (!CompassItem.hasLodestone(itemStack)) {
            return;
        }
        itemStack.removeSubNbt("LodestoneTracked");
        itemStack.removeSubNbt("LodestoneDimension");
        itemStack.removeSubNbt("LodestonePos");
        itemStack.removeSubNbt("RepairCost");
        callbackInfo.setReturnValue(itemStack);
    }

    @Mixin(targets = "net.minecraft.screen.GrindstoneScreenHandler$2")
    public static class GrindstoneScreenHandler2Mixin {
        @Inject(method = "canInsert", at = @At("RETURN"), cancellable = true)
        private void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
            callbackInfo.setReturnValue(callbackInfo.getReturnValue() || CompassItem.hasLodestone(stack));
        }
    }

    @Mixin(targets = "net.minecraft.screen.GrindstoneScreenHandler$3")
    public static class GrindstoneScreenHandler3Mixin {
        @Inject(method = "canInsert", at = @At("RETURN"), cancellable = true)
        private void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
            callbackInfo.setReturnValue(callbackInfo.getReturnValue() || CompassItem.hasLodestone(stack));
        }
    }
}
