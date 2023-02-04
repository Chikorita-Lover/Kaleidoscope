package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.item.BundleItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BundleItem.class)
public class BundleItemMixin implements DyeableItem {
    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : -1;
    }
}
