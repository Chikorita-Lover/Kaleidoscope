package net.chikorita_lover.kaleidoscope.screen;

import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class StriderScreenHandler extends ScreenHandler {
    public final StriderEntity entity;
    private final Inventory inventory;

    public StriderScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, final StriderEntity entity) {
        super(null, syncId);
        int column;
        int row;
        this.inventory = inventory;
        this.entity = entity;
        inventory.onOpen(playerInventory.player);
        if (((Chestable) entity).kaleidoscope$hasChest()) {
            for (row = 0; row < 3; ++row) {
                for (column = 0; column < 5; ++column) {
                    this.addSlot(new Slot(inventory, column + row * 5, 80 + column * 18, 18 + row * 18));
                }
            }
        }
        for (row = 0; row < 3; ++row) {
            for (column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 102 + row * 18 + -18));
            }
        }
        for (column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player) && this.entity.isAlive() && this.entity.distanceTo(player) < 8.0F;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            stack = itemStack2.copy();
            int size = this.inventory.size();
            if (slot < size) {
                if (!this.insertItem(itemStack2, size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (size < 1 || !this.insertItem(itemStack2, 0, size, false)) {
                int hotbarStart = size + 27;
                int hotbarEnd = hotbarStart + 9;
                if (slot >= hotbarStart ? !this.insertItem(itemStack2, size, hotbarStart, false) : !this.insertItem(itemStack2, hotbarStart, hotbarEnd, false)) {
                    return ItemStack.EMPTY;
                }
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }
        }
        return stack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.inventory.onClose(player);
    }
}
