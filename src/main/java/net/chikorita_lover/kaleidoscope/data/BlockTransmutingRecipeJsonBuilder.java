package net.chikorita_lover.kaleidoscope.data;

import com.google.gson.JsonObject;
import net.chikorita_lover.kaleidoscope.recipe.AbstractBlockTransmutingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BlockTransmutingRecipeJsonBuilder implements CraftingRecipeJsonBuilder {
    private final Block block;
    private final Block result;
    private final RecipeSerializer<? extends AbstractBlockTransmutingRecipe> serializer;

    public BlockTransmutingRecipeJsonBuilder(Block block, Block result, RecipeSerializer<? extends AbstractBlockTransmutingRecipe> serializer) {
        this.block = block;
        this.result = result;
        this.serializer = serializer;
    }

    public static BlockTransmutingRecipeJsonBuilder create(Block block, Block result, RecipeSerializer<? extends AbstractBlockTransmutingRecipe> serializer) {
        return new BlockTransmutingRecipeJsonBuilder(block, result, serializer);
    }

    public static BlockTransmutingRecipeJsonBuilder createCracking(Block block, Block result) {
        return create(block, result, KaleidoscopeRecipeSerializers.CRACKING);
    }

    public static BlockTransmutingRecipeJsonBuilder createMossScraping(Block block, Block result) {
        return create(block, result, KaleidoscopeRecipeSerializers.MOSS_SCRAPING);
    }

    @Deprecated
    @Override
    public CraftingRecipeJsonBuilder criterion(String name, CriterionConditions conditions) {
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
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        exporter.accept(new BlockTransmutingRecipeJsonProvider(recipeId, this.block, this.result, this.serializer));
    }

    static class BlockTransmutingRecipeJsonProvider implements RecipeJsonProvider {
        private final Identifier recipeId;
        private final Block block;
        private final Block result;
        private final RecipeSerializer<? extends AbstractBlockTransmutingRecipe> serializer;

        public BlockTransmutingRecipeJsonProvider(Identifier recipeId, Block block, Block result, RecipeSerializer<? extends AbstractBlockTransmutingRecipe> serializer) {
            this.recipeId = recipeId;
            this.block = block;
            this.result = result;
            this.serializer = serializer;
        }

        public void serialize(JsonObject json) {
            json.addProperty("block", Registries.BLOCK.getId(this.block).toString());
            json.addProperty("result", Registries.BLOCK.getId(this.result).toString());
        }

        public RecipeSerializer<?> getSerializer() {
            return this.serializer;
        }

        public Identifier getRecipeId() {
            return this.recipeId;
        }

        @Nullable
        public JsonObject toAdvancementJson() {
            return null;
        }

        @Nullable
        public Identifier getAdvancementId() {
            return null;
        }
    }
}
