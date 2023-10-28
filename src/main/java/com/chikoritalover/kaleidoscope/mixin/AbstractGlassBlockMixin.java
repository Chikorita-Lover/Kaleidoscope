package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.GlassSlabBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractGlassBlock.class)
public class AbstractGlassBlockMixin extends TransparentBlock {
    public AbstractGlassBlockMixin(Settings settings) {
        super(settings);
    }

    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.getBlock() instanceof GlassSlabBlock slabBlock && slabBlock.baseBlock == this) {
            EnumProperty<SlabType> property = Properties.SLAB_TYPE;
            if (direction.getAxis() == Direction.Axis.Y || stateFrom.get(property) == SlabType.DOUBLE) {
                return true;
            }
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
