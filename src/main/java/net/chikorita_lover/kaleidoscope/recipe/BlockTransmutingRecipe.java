package net.chikorita_lover.kaleidoscope.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public abstract class BlockTransmutingRecipe implements Recipe<SingleBlockRecipeInput> {
    protected final RegistryKey<Block> block;
    protected final RegistryKey<Block> result;

    public BlockTransmutingRecipe(RegistryKey<Block> block, RegistryKey<Block> result) {
        this.block = block;
        this.result = result;
    }

    public BlockTransmutingRecipe(Block block, Block result) {
        this(block.getRegistryEntry().registryKey(), result.getRegistryEntry().registryKey());
    }

    public BlockState createStateFrom(WorldView world, BlockState state) {
        Block block = world.getRegistryManager().getOrThrow(RegistryKeys.BLOCK).get(this.result);
        return block != null ? block.getStateWithProperties(state) : state;
    }

    @Override
    public boolean matches(SingleBlockRecipeInput input, World world) {
        return input.block().getRegistryEntry().matchesKey(this.block);
    }

    @Override
    public ItemStack craft(SingleBlockRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return new ItemStack(registries.getOrThrow(RegistryKeys.BLOCK).getOrThrow(this.result).value());
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.NONE;
    }

    public interface RecipeFactory<T extends BlockTransmutingRecipe> {
        T create(RegistryKey<Block> block, RegistryKey<Block> result);
    }

    public static class Serializer<T extends BlockTransmutingRecipe> implements RecipeSerializer<T> {
        private final RecipeFactory<T> recipeFactory;
        private final MapCodec<T> codec;
        private final PacketCodec<RegistryByteBuf, T> packetCodec;

        public Serializer(RecipeFactory<T> recipeFactory) {
            this.recipeFactory = recipeFactory;
            this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(RegistryKey.createCodec(RegistryKeys.BLOCK).fieldOf("block").forGetter(recipe -> recipe.block), RegistryKey.createCodec(RegistryKeys.BLOCK).fieldOf("result").forGetter(recipe -> recipe.result)).apply(instance, recipeFactory::create));
            this.packetCodec = PacketCodec.ofStatic(this::write, this::read);
        }

        @Override
        public MapCodec<T> codec() {
            return this.codec;
        }

        @Override
        public PacketCodec<RegistryByteBuf, T> packetCodec() {
            return this.packetCodec;
        }

        private T read(RegistryByteBuf buf) {
            RegistryKey<Block> block = RegistryKey.createPacketCodec(RegistryKeys.BLOCK).decode(buf);
            RegistryKey<Block> result = RegistryKey.createPacketCodec(RegistryKeys.BLOCK).decode(buf);
            return this.recipeFactory.create(block, result);
        }

        private void write(RegistryByteBuf buf, T recipe) {
            RegistryKey.createPacketCodec(RegistryKeys.BLOCK).encode(buf, recipe.block);
            RegistryKey.createPacketCodec(RegistryKeys.BLOCK).encode(buf, recipe.result);
        }
    }
}
