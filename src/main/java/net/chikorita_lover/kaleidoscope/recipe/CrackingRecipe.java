package net.chikorita_lover.kaleidoscope.recipe;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CrackingRecipe extends AbstractBlockTransmutingRecipe {
    public CrackingRecipe(Identifier id, Block block, Block result) {
        super(id, block, result);
    }

    public static void tryCrackBlock(final World world, final BlockPos pos) {
        final BlockState state = world.getBlockState(pos);
        world.getRecipeManager().getFirstMatch(KaleidoscopeRecipeTypes.CRACKING, new SimpleInventory(new ItemStack(state.getBlock().asItem())), world).ifPresent(entry -> {
            world.setBlockState(pos, entry.createStateFrom(state));
            world.playSound(null, pos, KaleidoscopeSoundEvents.RANDOM_BLOCK_CRACK, SoundCategory.BLOCKS, 1.0F, MathHelper.nextBetween(world.getRandom(), 0.8F, 1.2F));
        });
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KaleidoscopeRecipeSerializers.CRACKING;
    }

    @Override
    public RecipeType<?> getType() {
        return KaleidoscopeRecipeTypes.CRACKING;
    }
}
