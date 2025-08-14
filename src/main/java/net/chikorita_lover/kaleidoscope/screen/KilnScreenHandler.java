package net.chikorita_lover.kaleidoscope.screen;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;

public class KilnScreenHandler extends AbstractFurnaceScreenHandler {
    public static final RegistryKey<RecipePropertySet> PROPERTY_SET = RegistryKey.of(RecipePropertySet.REGISTRY, Kaleidoscope.of("kiln_input"));

    public KilnScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(KaleidoscopeScreenHandlerTypes.KILN, KaleidoscopeRecipeTypes.KILNING, PROPERTY_SET, KilningRecipe.CATEGORY, syncId, playerInventory);
    }

    public KilnScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(KaleidoscopeScreenHandlerTypes.KILN, KaleidoscopeRecipeTypes.KILNING, PROPERTY_SET, KilningRecipe.CATEGORY, syncId, playerInventory, inventory, propertyDelegate);
    }
}
