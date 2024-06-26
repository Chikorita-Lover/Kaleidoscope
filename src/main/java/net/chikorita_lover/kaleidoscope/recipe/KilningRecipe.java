package net.chikorita_lover.kaleidoscope.recipe;

import com.chocohead.mm.api.ClassTinkerers;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;

import java.util.ArrayList;

public class KilningRecipe extends AbstractCookingRecipe {
    public static final RecipeBookCategory RECIPE_BOOK_CATEGORY = ClassTinkerers.getEnum(RecipeBookCategory.class, "KALEIDOSCOPE_KILN");
    public static final ArrayList<RecipeEntry<Recipe<?>>> KILNING_RECIPE_ENTRIES = new ArrayList<>();

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
