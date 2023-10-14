package com.chikoritalover.kaleidoscope.recipe;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class KilnCookingRecipe extends AbstractCookingRecipe {
    public KilnCookingRecipe(String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Kaleidoscope.KILNING, group, category, input, output, experience, cookTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(KaleidoscopeBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //The Serializer will be created later
        return Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER;
    }
}
