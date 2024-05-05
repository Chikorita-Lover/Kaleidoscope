package net.chikorita_lover.kaleidoscope.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StewItem.class)
public class StewItemMixin {
    @ModifyReturnValue(method = "finishUsing", at = @At("RETURN"))
    private ItemStack finishUsing(ItemStack stack, @Local(argsOnly = true) LivingEntity user) {
        if (stack.isEmpty()) {
            return new ItemStack(Items.BOWL);
        }
        if (user instanceof PlayerEntity playerEntity && !playerEntity.isCreative()) {
            ItemStack itemStack = new ItemStack(Items.BOWL);
            if (!playerEntity.getInventory().insertStack(itemStack)) {
                playerEntity.dropItem(itemStack, false);
            }
        }
        return stack;
    }
}
