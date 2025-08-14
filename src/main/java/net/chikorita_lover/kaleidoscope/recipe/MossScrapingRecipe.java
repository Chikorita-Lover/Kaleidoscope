package net.chikorita_lover.kaleidoscope.recipe;

import net.minecraft.block.Block;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryKey;

public class MossScrapingRecipe extends BlockTransmutingRecipe {
    public MossScrapingRecipe(RegistryKey<Block> block, RegistryKey<Block> result) {
        super(block, result);
    }

    public MossScrapingRecipe(Block block, Block result) {
        super(block, result);
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleBlockRecipeInput>> getSerializer() {
        return KaleidoscopeRecipeSerializers.MOSS_SCRAPING;
    }

    @Override
    public RecipeType<? extends Recipe<SingleBlockRecipeInput>> getType() {
        return KaleidoscopeRecipeTypes.MOSS_SCRAPING;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return KaleidoscopeRecipeBookCategories.MOSS_SCRAPING;
    }
}
