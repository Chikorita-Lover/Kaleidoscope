package net.chikorita_lover.kaleidoscope.block;

import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;

public class DyedChestBlock extends ChestBlock {
    private final DyeColor color;

    public DyedChestBlock(DyeColor color, Settings settings) {
        super(() -> BlockEntityType.CHEST, settings);
        this.color = color;
    }

    public DyeColor getColor() {
        return this.color;
    }
}
