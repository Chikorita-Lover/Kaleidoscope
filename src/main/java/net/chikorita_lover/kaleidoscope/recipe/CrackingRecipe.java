package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;

public class CrackingRecipe extends BlockTransmutingRecipe {
    public CrackingRecipe(RegistryKey<Block> block, RegistryKey<Block> result) {
        super(block, result);
    }

    public CrackingRecipe(Block block, Block result) {
        super(block, result);
    }

    public static void tryCrackBlock(ServerWorld world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Optional<RecipeEntry<CrackingRecipe>> recipe = world.getRecipeManager().getFirstMatch(KaleidoscopeRecipeTypes.CRACKING, new SingleBlockRecipeInput(state.getBlock()), world);
        if (recipe.isPresent()) {
            world.setBlockState(pos, recipe.get().value().createStateFrom(world, state));
            world.playSound(null, pos, KaleidoscopeSoundEvents.RANDOM_BLOCK_CRACK, SoundCategory.BLOCKS, 1.0F, MathHelper.nextBetween(world.getRandom(), 0.8F, 1.2F));
        }
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleBlockRecipeInput>> getSerializer() {
        return KaleidoscopeRecipeSerializers.CRACKING;
    }

    @Override
    public RecipeType<? extends Recipe<SingleBlockRecipeInput>> getType() {
        return KaleidoscopeRecipeTypes.CRACKING;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return KaleidoscopeRecipeBookCategories.CRACKING;
    }
}
