package net.chikorita_lover.kaleidoscope.recipe;

import com.chocohead.mm.api.ClassTinkerers;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;

public class KilningRecipe extends AbstractCookingRecipe {
    public static final RecipeBookCategory RECIPE_BOOK_CATEGORY = ClassTinkerers.getEnum(RecipeBookCategory.class, "KALEIDOSCOPE_KILN");

    public KilningRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(Kaleidoscope.KILNING, group, category, ingredient, result, experience, cookingTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(KaleidoscopeBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KaleidoscopeRecipeSerializers.KILNING;
    }
}
