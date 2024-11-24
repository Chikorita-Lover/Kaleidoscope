package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface Chestable {
    default void kaleidoscope$addChest(@Nullable PlayerEntity player, ItemStack chest) {
        this.kaleidoscope$setHasChest(true);
        this.kaleidoscope$playAddChestSound();
        if (player == null || !player.getAbilities().creativeMode) {
            chest.decrement(1);
        }
        this.kaleidoscope$onChestedStatusChanged();
    }

    void kaleidoscope$playAddChestSound();

    void kaleidoscope$setHasChest(boolean hasChest);

    void kaleidoscope$onChestedStatusChanged();

    boolean kaleidoscope$hasChest();

    int kaleidoscope$getInventorySize();
}
