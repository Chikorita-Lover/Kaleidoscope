package com.chikoritalover.kaleidoscope.screen;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class KilnScreenHandler extends AbstractFurnaceScreenHandler {
    public KilnScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Kaleidoscope.KILN_SCREEN_HANDLER, Kaleidoscope.KILNING, Kaleidoscope.KILNING_CATEGORY, syncId, playerInventory);
    }

    public KilnScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Kaleidoscope.KILN_SCREEN_HANDLER, Kaleidoscope.KILNING, Kaleidoscope.KILNING_CATEGORY, syncId, playerInventory, inventory, propertyDelegate);
    }
}
