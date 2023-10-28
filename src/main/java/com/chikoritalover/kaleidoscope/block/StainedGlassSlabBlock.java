package com.chikoritalover.kaleidoscope.block;

import net.minecraft.block.Block;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassSlabBlock extends GlassSlabBlock implements Stainable {
    private final DyeColor color;

    public StainedGlassSlabBlock(DyeColor color, Block baseBlock, Settings settings) {
        super(baseBlock, settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
