package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;

public interface BannerEquippable {
    String BANNER_KEY = "KaleidoscopeBannerItem";

    ItemStack kaleidoscope$getBannerStack();

    void kaleidoscope$setBannerStack(ItemStack stack);

    default boolean hasBanner() {
        return !this.kaleidoscope$getBannerStack().isEmpty();
    }

    default void writeBannerToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registriesLookup) {
        if (!this.kaleidoscope$getBannerStack().isEmpty()) {
            nbt.put(BANNER_KEY, this.kaleidoscope$getBannerStack().encode(registriesLookup));
        }
    }

    default void readBannerFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registriesLookup) {
        if (nbt.contains(BANNER_KEY, NbtElement.COMPOUND_TYPE)) {
            NbtCompound nbtCompound = nbt.getCompound(BANNER_KEY);
            this.kaleidoscope$setBannerStack(ItemStack.fromNbt(registriesLookup, nbtCompound).orElse(ItemStack.EMPTY));
        } else {
            this.kaleidoscope$setBannerStack(ItemStack.EMPTY);
        }
    }
}
