package net.chikorita_lover.kaleidoscope.block;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class StainedGlassTrapdoorBlock extends GlassTrapdoorBlock implements Stainable {
    private final DyeColor color;

    public StainedGlassTrapdoorBlock(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return this.color;
    }
}
