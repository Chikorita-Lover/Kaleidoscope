package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public interface BannerEquippable {
    String BANNER_KEY = "KaleidoscopeBannerItem";

    ItemStack kaleidoscope$getBannerStack();

    void kaleidoscope$setBannerStack(ItemStack stack);

    default boolean hasBanner() {
        return !this.kaleidoscope$getBannerStack().isEmpty();
    }

    default void writeBannerToNbt(NbtCompound nbt) {
        if (!this.kaleidoscope$getBannerStack().isEmpty()) {
            nbt.put(BANNER_KEY, this.kaleidoscope$getBannerStack().writeNbt(new NbtCompound()));
        }
    }

    default void readBannerFromNbt(NbtCompound nbt) {
        if (nbt.contains(BANNER_KEY, NbtElement.COMPOUND_TYPE)) {
            this.kaleidoscope$setBannerStack(ItemStack.fromNbt(nbt.getCompound(BANNER_KEY)));
        } else {
            this.kaleidoscope$setBannerStack(ItemStack.EMPTY);
        }
    }
}
