package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.item.ItemStack;

public interface HealableAnimal {
    default boolean kaleidoscope$isFeedingItem(ItemStack stack) {
        return false;
    }
}
