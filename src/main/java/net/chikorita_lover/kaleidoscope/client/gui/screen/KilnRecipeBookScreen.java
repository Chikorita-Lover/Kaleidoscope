package net.chikorita_lover.kaleidoscope.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookScreen;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import java.util.Set;

@Environment(value = EnvType.CLIENT)
public class KilnRecipeBookScreen extends AbstractFurnaceRecipeBookScreen {
    private static final Text TOGGLE_KILNABLE_RECIPES_TEXT = Text.translatable("gui.recipebook.toggleRecipes.kilnable");

    @Override
    protected Text getToggleCraftableButtonText() {
        return TOGGLE_KILNABLE_RECIPES_TEXT;
    }

    @Override
    protected Set<Item> getAllowedFuels() {
        return AbstractFurnaceBlockEntity.createFuelTimeMap().keySet();
    }
}
