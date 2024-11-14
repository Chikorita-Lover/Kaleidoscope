package net.chikorita_lover.kaleidoscope.data;

import net.chikorita_lover.kaleidoscope.recipe.AbstractBlockTransmutingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BlockTransmutingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final Block block;
    private final Block result;
    private final AbstractBlockTransmutingRecipe.RecipeFactory<?> recipeFactory;

    public BlockTransmutingRecipeJsonBuilder(Block block, Block result, AbstractBlockTransmutingRecipe.RecipeFactory<?> recipeFactory) {
        this.block = block;
        this.result = result;
        this.recipeFactory = recipeFactory;
    }

    public static BlockTransmutingRecipeJsonBuilder create(Block block, Block result, AbstractBlockTransmutingRecipe.RecipeFactory<?> recipeFactory) {
        return new BlockTransmutingRecipeJsonBuilder(block, result, recipeFactory);
    }

    public static BlockTransmutingRecipeJsonBuilder createCracking(Block block, Block result) {
        return create(block, result, CrackingRecipe::new);
    }

    public static BlockTransmutingRecipeJsonBuilder createMossScraping(Block block, Block result) {
        return create(block, result, MossScrapingRecipe::new);
    }

    @Deprecated
    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        return this;
    }

    @Deprecated
    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return this.result.asItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        AbstractBlockTransmutingRecipe recipe = this.recipeFactory.create(this.block.getRegistryEntry().registryKey(), this.result.getRegistryEntry().registryKey());
        exporter.accept(recipeId, recipe, null);
    }
}
