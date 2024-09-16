package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin extends Item {
    public AxeItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "shouldCancelStripAttempt", at = @At("HEAD"), cancellable = true)
    private static void shouldCancelStripNullCheck(ItemUsageContext context, CallbackInfoReturnable<Boolean> cir) {
        if (context.getPlayer() == null || context.getHand() == null) {
            cir.setReturnValue(false);
        }
    }
}
