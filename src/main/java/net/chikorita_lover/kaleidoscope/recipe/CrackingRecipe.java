package net.chikorita_lover.kaleidoscope.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CrackingRecipe implements Recipe<SingleBlockRecipeInput> {
    private final RegistryKey<Block> block;
    private final RegistryKey<Block> result;

    public CrackingRecipe(RegistryKey<Block> block, RegistryKey<Block> result) {
        this.block = block;
        this.result = result;
    }

    public CrackingRecipe(Block block, Block result) {
        this(block.getRegistryEntry().registryKey(), result.getRegistryEntry().registryKey());
    }

    public static void tryCrackBlock(final World world, final BlockPos pos) {
        final BlockState state = world.getBlockState(pos);
        world.getRecipeManager().getFirstMatch(KaleidoscopeRecipeTypes.CRACKING, new SingleBlockRecipeInput(state.getBlock()), world).ifPresent(entry -> {
            world.setBlockState(pos, entry.value().createStateFrom(world, state));
            world.playSound(null, pos, KaleidoscopeSoundEvents.RANDOM_BLOCK_CRACK, SoundCategory.BLOCKS, 1.0F, MathHelper.nextBetween(world.getRandom(), 0.8F, 1.2F));
        });
    }

    private BlockState createStateFrom(WorldView world, BlockState state) {
        Block block = world.getRegistryManager().get(RegistryKeys.BLOCK).get(this.result);
        return block != null ? block.getStateWithProperties(state) : state;
    }

    @Override
    public boolean matches(SingleBlockRecipeInput input, World world) {
        return input.block().getRegistryEntry().matchesKey(this.block);
    }

    @Override
    public ItemStack craft(SingleBlockRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return this.getResult(lookup);
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return new ItemStack(registriesLookup.getWrapperOrThrow(RegistryKeys.BLOCK).getOrThrow(this.result).value());
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.FLINT_AND_STEEL);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KaleidoscopeRecipeSerializers.CRACKING;
    }

    @Override
    public RecipeType<?> getType() {
        return KaleidoscopeRecipeTypes.CRACKING;
    }

    public static class Serializer implements RecipeSerializer<CrackingRecipe> {
        private static final MapCodec<CrackingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(RegistryKey.createCodec(RegistryKeys.BLOCK).fieldOf("block").forGetter(recipe -> recipe.block), RegistryKey.createCodec(RegistryKeys.BLOCK).fieldOf("result").forGetter(recipe -> recipe.result)).apply(instance, CrackingRecipe::new));
        private static final PacketCodec<RegistryByteBuf, CrackingRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

        private static CrackingRecipe read(RegistryByteBuf buf) {
            RegistryKey<Block> block = RegistryKey.createPacketCodec(RegistryKeys.BLOCK).decode(buf);
            RegistryKey<Block> result = RegistryKey.createPacketCodec(RegistryKeys.BLOCK).decode(buf);
            return new CrackingRecipe(block, result);
        }

        private static void write(RegistryByteBuf buf, CrackingRecipe recipe) {
            RegistryKey.createPacketCodec(RegistryKeys.BLOCK).encode(buf, recipe.block);
            RegistryKey.createPacketCodec(RegistryKeys.BLOCK).encode(buf, recipe.result);
        }

        @Override
        public MapCodec<CrackingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CrackingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
