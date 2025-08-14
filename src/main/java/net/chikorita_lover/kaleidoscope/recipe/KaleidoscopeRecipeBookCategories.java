package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class KaleidoscopeRecipeBookCategories {
    public static final RecipeBookCategory KILN_BLOCKS = register("kiln_blocks");
    public static final RecipeBookCategory KILN_MISC = register("kiln_misc");
    public static final RecipeBookCategory CRACKING = register("cracking");
    public static final RecipeBookCategory MOSS_SCRAPING = register("moss_scraping");

    private static RecipeBookCategory register(String id) {
        return Registry.register(Registries.RECIPE_BOOK_CATEGORY, Kaleidoscope.of(id), new RecipeBookCategory());
    }

    public static void register() {
    }
}
