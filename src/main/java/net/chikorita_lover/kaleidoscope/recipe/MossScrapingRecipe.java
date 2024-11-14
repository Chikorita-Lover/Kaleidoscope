package net.chikorita_lover.kaleidoscope.recipe;

import net.minecraft.block.Block;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKey;

public class MossScrapingRecipe extends AbstractBlockTransmutingRecipe {
    public MossScrapingRecipe(RegistryKey<Block> block, RegistryKey<Block> result) {
        super(block, result);
    }

    public MossScrapingRecipe(Block block, Block result) {
        super(block, result);
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
