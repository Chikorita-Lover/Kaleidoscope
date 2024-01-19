package com.chikoritalover.kaleidoscope.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.Optional;

public class BrewingUtil {
    public static Optional<BrewingRecipe> getRecipeFromId(Identifier id) {
        for (BrewingRecipe recipe : BrewingRecipe.BREWING_RECIPES) {
            if (recipe.getId().equals(id)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }

    public static Collection<BrewingRecipe> getUnlockableRecipes(ItemStack stack) {
        return BrewingRecipe.BREWING_RECIPES.stream().filter(recipe -> recipe.getInput().test(stack) && PotionUtil.getPotion(stack) == recipe.getInputPotion() || recipe.getInputPotion() == Potions.AWKWARD && recipe.getReagent().test(stack)).toList();
    }
}
