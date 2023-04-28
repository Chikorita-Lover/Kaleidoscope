package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StewItem.class)
public class StewItemMixin {
    @Inject(method = "finishUsing", at = @At("RETURN"), cancellable = true)
    public void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> callbackInfo) {
        if (stack.isEmpty()) {
            callbackInfo.setReturnValue(new ItemStack(Items.BOWL));
            return;
        }
        if (user instanceof PlayerEntity playerEntity && !playerEntity.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(Items.BOWL);
            if (!playerEntity.getInventory().insertStack(itemStack)) {
                playerEntity.dropItem(itemStack, false);
            }
        }
        callbackInfo.setReturnValue(stack);
    }
}
