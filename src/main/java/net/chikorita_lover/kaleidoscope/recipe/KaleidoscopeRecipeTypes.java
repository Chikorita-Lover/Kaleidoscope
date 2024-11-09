package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeRecipeTypes {
    public static final RecipeType<CrackingRecipe> CRACKING = register("cracking");
    public static final RecipeType<KilningRecipe> KILNING = register("kilning");

    public static void register() {
    }

    private static <T extends Recipe<?>> RecipeType<T> register(String path) {
        return Registry.register(Registries.RECIPE_TYPE, Kaleidoscope.of(path), new RecipeType<T>() {
            @Override
            public String toString() {
                return path;
            }
        });
    }
}
