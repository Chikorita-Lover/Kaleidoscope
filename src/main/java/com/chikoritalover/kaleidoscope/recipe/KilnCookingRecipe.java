package com.chikoritalover.kaleidoscope.recipe;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.registry.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class KilnCookingRecipe extends AbstractCookingRecipe {
    public KilnCookingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Kaleidoscope.KILNING, id, group, input, output, experience, cookTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //The Serializer will be created later
        return Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER;
    }
}
