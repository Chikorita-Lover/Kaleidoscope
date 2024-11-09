package net.chikorita_lover.kaleidoscope.data;

import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class CrackingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final Block block;
    private final Block result;

    public CrackingRecipeJsonBuilder(Block block, Block result) {
        this.block = block;
        this.result = result;
    }

    public static CrackingRecipeJsonBuilder create(Block block, Block result) {
        return new CrackingRecipeJsonBuilder(block, result);
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
        CrackingRecipe recipe = new CrackingRecipe(this.block.getRegistryEntry().registryKey(), this.result.getRegistryEntry().registryKey());
        exporter.accept(recipeId, recipe, null);
    }
}
