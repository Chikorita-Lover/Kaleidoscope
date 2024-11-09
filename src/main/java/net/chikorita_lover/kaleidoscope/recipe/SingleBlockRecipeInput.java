package net.chikorita_lover.kaleidoscope.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record SingleBlockRecipeInput(Block block) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return new ItemStack(this.block);
    }

    @Override
    public int getSize() {
        return 1;
    }
}
