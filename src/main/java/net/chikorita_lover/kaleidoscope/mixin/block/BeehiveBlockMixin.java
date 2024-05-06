package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @ModifyExpressionValue(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean isOfShears(boolean bl, @Local(argsOnly = true) ItemStack stack) {
        return bl || stack.getItem() instanceof ShearsItem;
    }

    @ModifyArg(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/stat/StatType;getOrCreateStat(Ljava/lang/Object;)Lnet/minecraft/stat/Stat;"))
    private Object foo(Object key, @Local(argsOnly = true) ItemStack stack) {
        return stack.getItem();
    }
}
