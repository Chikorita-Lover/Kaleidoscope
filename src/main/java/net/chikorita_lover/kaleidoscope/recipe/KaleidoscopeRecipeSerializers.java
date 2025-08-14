package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeRecipeSerializers {
    public static final BlockTransmutingRecipe.Serializer<CrackingRecipe> CRACKING = Registry.register(Registries.RECIPE_SERIALIZER, Kaleidoscope.of("cracking"), new BlockTransmutingRecipe.Serializer<>(CrackingRecipe::new));
    public static final RecipeSerializer<KilningRecipe> KILNING = Registry.register(Registries.RECIPE_SERIALIZER, Kaleidoscope.of("kilning"), new AbstractCookingRecipe.Serializer<>(KilningRecipe::new, 100));
    public static final BlockTransmutingRecipe.Serializer<MossScrapingRecipe> MOSS_SCRAPING = Registry.register(Registries.RECIPE_SERIALIZER, Kaleidoscope.of("moss_scraping"), new BlockTransmutingRecipe.Serializer<>(MossScrapingRecipe::new));

    public static void register() {
    }
}
