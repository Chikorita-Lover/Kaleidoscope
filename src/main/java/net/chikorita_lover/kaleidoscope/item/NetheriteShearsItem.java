package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;

public class NetheriteShearsItem extends ShearsItem {
    public NetheriteShearsItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        float multiplier = super.getMiningSpeedMultiplier(stack, state);
        return multiplier != 1.0F ? multiplier * 1.5F : 1.0F;
    }
}
