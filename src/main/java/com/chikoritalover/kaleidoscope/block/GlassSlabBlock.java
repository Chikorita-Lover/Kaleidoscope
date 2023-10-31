package com.chikoritalover.kaleidoscope.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GlassSlabBlock extends SlabBlock {
    public final Block baseBlock;

    public GlassSlabBlock(Block baseBlock, Settings settings) {
        super(settings);
        this.baseBlock = baseBlock;
    }

    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            if (direction.getAxis() == Direction.Axis.Y ^ stateFrom.get(TYPE) == state.get(TYPE)) {
                return true;
            }
            if (stateFrom.get(TYPE) == SlabType.DOUBLE) {
                return true;
            }
        }
        if (stateFrom.isOf(baseBlock)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
}
