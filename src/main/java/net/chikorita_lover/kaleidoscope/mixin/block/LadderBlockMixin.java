package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LadderBlock.class)
public class LadderBlockMixin extends Block {
    public LadderBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return KaleidoscopeConfig.EXTEND_LADDERS_ON_INTERACT.get() && context.getStack().getItem() instanceof BlockItem item && item.getBlock() instanceof LadderBlock;
    }
}
