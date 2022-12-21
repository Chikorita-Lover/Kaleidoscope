package com.chikorita_lover.chikorita_lover_mod.recipe;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class KilnCookingRecipe extends AbstractCookingRecipe {
    public KilnCookingRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(ChikoritaLoverMod.KILNING, id, group, input, output, experience, cookTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(ModBlocks.KILN);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //The Serializer will be created later
        return ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER;
    }
}
