package net.chikorita_lover.kaleidoscope.recipe;

import net.minecraft.block.Block;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class MossScrapingRecipe extends AbstractBlockTransmutingRecipe {
    public MossScrapingRecipe(Identifier id, Block block, Block result) {
        super(id, block, result);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KaleidoscopeRecipeSerializers.MOSS_SCRAPING;
    }

    @Override
    public RecipeType<?> getType() {
        return KaleidoscopeRecipeTypes.MOSS_SCRAPING;
    }
}
