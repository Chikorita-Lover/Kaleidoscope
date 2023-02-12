package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoodComponent.class)
public class FoodComponentMixin {
    @Inject(at = @At("HEAD"), method = "isSnack", cancellable = true)
    public void isSnack(CallbackInfoReturnable<Boolean> info) {
        FoodComponent foodComponent = FoodComponent.class.cast(this);

        if (foodComponent == FoodComponents.COOKIE) {
            info.setReturnValue(true);
        }
    }
}
