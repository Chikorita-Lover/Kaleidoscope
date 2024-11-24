package net.chikorita_lover.kaleidoscope.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class FireworksTableOutputSlot extends Slot {
    private final FireworksTableScreenHandler handler;
    private final PlayerEntity player;
    private int amount;

    public FireworksTableOutputSlot(FireworksTableScreenHandler handler, PlayerEntity player, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
        this.player = player;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack takeStack(int amount) {
        if (this.hasStack()) {
            this.amount += Math.min(amount, this.getStack().getCount());
        }
        return super.takeStack(amount);
    }

    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        this.amount += amount;
        this.onCrafted(stack);
    }

    @Override
    protected void onTake(int amount) {
        this.amount += amount;
    }

    @Override
    protected void onCrafted(ItemStack stack) {
        if (this.amount > 0) {
            stack.onCraft(this.player.getWorld(), this.player, this.amount);
        }
        this.amount = 0;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);
        for (Slot slot : this.handler.slots.subList(FireworksTableScreenHandler.INPUT_START, FireworksTableScreenHandler.INPUT_END)) {
            if (slot.hasStack()) {
                slot.takeStack(1);
            }
        }
        if (!this.handler.getBaseSlot().hasStack()) {
            for (Slot slot : this.handler.slots.subList(4, FireworksTableScreenHandler.INPUT_END)) {
                player.getInventory().offerOrDrop(slot.getStack());
            }
        }
        this.handler.playResultSound();
        super.onTakeItem(player, stack);
    }
}
