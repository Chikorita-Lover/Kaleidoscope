package net.chikorita_lover.kaleidoscope.entity;

import net.minecraft.block.Blocks;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackWithSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jetbrains.annotations.Nullable;

public interface Chestable {
    default void kaleidoscope$addChest(@Nullable PlayerEntity player, ItemStack chest) {
        this.kaleidoscope$setHasChest(true);
        this.kaleidoscope$playAddChestSound();
        chest.decrementUnlessCreative(1, player);
        this.kaleidoscope$onChestedStatusChanged();
    }

    void kaleidoscope$playAddChestSound();

    void kaleidoscope$setHasChest(boolean hasChest);

    void kaleidoscope$onChestedStatusChanged();

    boolean kaleidoscope$hasChest();

    int kaleidoscope$getInventorySize();

    void kaleidoscope$writeChestData(WriteView view);

    void kaleidoscope$readChestData(ReadView view);

    void kaleidoscope$dropChestContents(ServerWorld world);
}
