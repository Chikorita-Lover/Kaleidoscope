package com.chikoritalover.kaleidoscope.recipe;

import net.minecraft.util.Identifier;

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
}
