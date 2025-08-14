package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.chicory.api.recipe.RecipeBookTypeRegistry;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.mixin.recipe.SingleStackRecipeAccessor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.registry.RegistryKey;

import java.util.HashMap;
import java.util.Map;

public class KilningRecipe extends AbstractCookingRecipe {
    public static final RecipeBookType CATEGORY = RecipeBookTypeRegistry.register("kaleidoscope_kiln", KaleidoscopeRecipeBookCategories.KILN_BLOCKS, KaleidoscopeRecipeBookCategories.KILN_MISC);
    public static final Map<RegistryKey<Recipe<?>>, RegistryKey<Recipe<?>>> SMELTING_TO_KILNING = new HashMap<>();

    public KilningRecipe(String group, CookingRecipeCategory category, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    public static KilningRecipe createFromSmelting(SmeltingRecipe recipe) {
        ItemStack result = ((SingleStackRecipeAccessor) recipe).kaleidoscope$result();
        return new KilningRecipe(recipe.getGroup(), recipe.getCategory(), recipe.ingredient(), result, recipe.getExperience(), recipe.getCookingTime() / 2);
    }

    @Override
    protected Item getCookerItem() {
        return KaleidoscopeBlocks.KILN.asItem();
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return KaleidoscopeRecipeTypes.KILNING;
    }

    @Override
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return KaleidoscopeRecipeSerializers.KILNING;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return this.getCategory() == CookingRecipeCategory.BLOCKS ? KaleidoscopeRecipeBookCategories.KILN_BLOCKS : KaleidoscopeRecipeBookCategories.KILN_MISC;
    }
}
