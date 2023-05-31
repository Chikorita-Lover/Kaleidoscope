package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Inject(method = "craftRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.BEFORE))
    private static void craftRecipe(DynamicRegistryManager registryManager, @Nullable Recipe<?> recipe, DefaultedList<ItemStack> slots, int count, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (slots.get(0).isOf(Blocks.WET_SPONGE.asItem()) && !slots.get(1).isEmpty() && slots.get(1).isOf(Items.GLASS_BOTTLE)) {
            slots.set(1, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
        }
    }
}
