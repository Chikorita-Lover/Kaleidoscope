package com.chikoritalover.kaleidoscope.block;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassDoorBlock extends GlassDoorBlock implements Stainable {
    private final DyeColor color;

    public StainedGlassDoorBlock(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
