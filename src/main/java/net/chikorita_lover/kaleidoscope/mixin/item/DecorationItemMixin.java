package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DecorationItem.class)
public class DecorationItemMixin {
    @ModifyReturnValue(method = "useOnBlock", at = @At(value = "RETURN", ordinal = 1))
    public ActionResult failedPlacingPainting(ActionResult result) {
        return ActionResult.PASS;
    }

    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    public void decrementOnServer(ItemStack itemStack, int amount, Operation<Void> operation, ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            operation.call(itemStack, amount);
        }
    }

    @ModifyReturnValue(method = "useOnBlock", at = @At(value = "RETURN", ordinal = 4))
    public ActionResult cannotAttach(ActionResult result) {
        return ActionResult.PASS;
    }
}
