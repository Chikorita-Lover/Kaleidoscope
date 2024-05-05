package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;

public class KilningRecipe extends AbstractCookingRecipe {
    public KilningRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(Kaleidoscope.KILNING, group, category, ingredient, result, experience, cookingTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(KaleidoscopeBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER;
    }
}
