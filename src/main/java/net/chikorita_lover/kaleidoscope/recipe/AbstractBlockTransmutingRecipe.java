package net.chikorita_lover.kaleidoscope.recipe;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public abstract class AbstractBlockTransmutingRecipe implements Recipe<Inventory> {
    protected final Identifier id;
    protected final Block block;
    protected final Block result;

    public AbstractBlockTransmutingRecipe(Identifier id, Block block, Block result) {
        this.id = id;
        this.block = block;
        this.result = result;
    }

    public BlockState createStateFrom(BlockState state) {
        return this.result.getStateWithProperties(state);
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return inventory.getStack(0).isOf(this.block.asItem());
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return this.getOutput(registryManager);
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return new ItemStack(this.result);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    public interface RecipeFactory<T extends AbstractBlockTransmutingRecipe> {
        T create(Identifier id, Block block, Block result);
    }

    public static class Serializer<T extends AbstractBlockTransmutingRecipe> implements RecipeSerializer<T> {
        private final RecipeFactory<T> recipeFactory;

        public Serializer(RecipeFactory<T> recipeFactory) {
            this.recipeFactory = recipeFactory;
        }

        @Override
        public T read(Identifier id, final JsonObject json) {
            Block block = Registries.BLOCK.getOrEmpty(new Identifier(JsonHelper.getString(json, "block"))).orElseThrow(() -> new IllegalStateException("Block: " + JsonHelper.getString(json, "block") + " does not exist"));
            Block result = Registries.BLOCK.getOrEmpty(new Identifier(JsonHelper.getString(json, "result"))).orElseThrow(() -> new IllegalStateException("Block: " + JsonHelper.getString(json, "result") + " does not exist"));
            return this.recipeFactory.create(id, block, result);
        }

        @Override
        public T read(Identifier id, PacketByteBuf buf) {
            Block block = Registries.BLOCK.get(buf.readIdentifier());
            Block result = Registries.BLOCK.get(buf.readIdentifier());
            return this.recipeFactory.create(id, block, result);
        }

        @Override
        public void write(PacketByteBuf buf, T recipe) {
            buf.writeIdentifier(Registries.BLOCK.getId(recipe.block));
            buf.writeIdentifier(Registries.BLOCK.getId(recipe.result));
        }
    }
}
