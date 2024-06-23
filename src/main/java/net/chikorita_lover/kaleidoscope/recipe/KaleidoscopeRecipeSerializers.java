package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeRecipeSerializers {
    public static final CookingRecipeSerializer<KilningRecipe> KILNING = Registry.register(Registries.RECIPE_SERIALIZER, Kaleidoscope.of("kilning"), new CookingRecipeSerializer<>(KilningRecipe::new, 100));

    public static void register() {
    }
}
