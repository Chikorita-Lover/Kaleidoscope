package net.chikorita_lover.kaleidoscope.screen;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class KilnScreenHandler extends AbstractFurnaceScreenHandler {
    public KilnScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(KaleidoscopeScreenHandlerTypes.KILN, Kaleidoscope.KILNING, KilningRecipe.RECIPE_BOOK_CATEGORY, syncId, playerInventory);
    }

    public KilnScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(KaleidoscopeScreenHandlerTypes.KILN, Kaleidoscope.KILNING, KilningRecipe.RECIPE_BOOK_CATEGORY, syncId, playerInventory, inventory, propertyDelegate);
    }
}
