package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;

public interface BannerEquippable {
    String BANNER_KEY = "KaleidoscopeBannerItem";

    ItemStack kaleidoscope$getBannerStack();

    void kaleidoscope$setBannerStack(ItemStack stack);

    default boolean kaleidoscope$hasBanner() {
        return !this.kaleidoscope$getBannerStack().isEmpty();
    }

    default void kaleidoscope$writeBannerData(WriteView view) {
        ItemStack stack = this.kaleidoscope$getBannerStack();
        if (!stack.isEmpty()) {
            view.put(BANNER_KEY, ItemStack.CODEC, stack);
        }
    }

    default void kaleidoscope$readBannerData(ReadView view) {
        this.kaleidoscope$setBannerStack(view.read(BANNER_KEY, ItemStack.CODEC).orElse(ItemStack.EMPTY));
    }
}
