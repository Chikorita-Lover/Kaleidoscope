package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PumpkinBlock.class)
public class PumpkinBlockMixin {
    @ModifyExpressionValue(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean isOfShears(boolean bl, @Local(argsOnly = true) ItemStack stack) {
        return bl || stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }

    @ModifyArg(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/stat/StatType;getOrCreateStat(Ljava/lang/Object;)Lnet/minecraft/stat/Stat;"))
    private Object getUsedItem(Object item, @Local(argsOnly = true) ItemStack stack) {
        return stack.getItem();
    }
}
