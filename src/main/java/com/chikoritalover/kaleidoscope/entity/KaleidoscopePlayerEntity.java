package com.chikoritalover.kaleidoscope.entity;

import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.server.ServerBrewingRecipeBook;

import java.util.Collection;

public interface KaleidoscopePlayerEntity {
    default ClientBrewingRecipeBook kaleidoscope$getClientBrewingRecipeBook() {
        return null;
    }

    default ServerBrewingRecipeBook kaleidoscope$getServerBrewingRecipeBook() {
        return null;
    }

    default void kaleidoscope$onBrewingRecipeDisplayed(BrewingRecipe recipe) {
    }

    default int kaleidoscope$unlockRecipes(Collection<BrewingRecipe> recipes) {
        return 0;
    }

    default int kaleidoscope$lockRecipes(Collection<BrewingRecipe> recipes) {
        return 0;
    }
}
