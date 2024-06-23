package net.chikorita_lover.kaleidoscope.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GlassDoorBlock extends DoorBlock {
    public GlassDoorBlock(Settings settings) {
        super(KaleidoscopeBlockSetType.GLASS, settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

/*
    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            Direction facingDirection = getFacingDirection(state);
            if (facingDirection == getFacingDirection(stateFrom) && facingDirection.getAxis() != direction.getAxis()) {
                return true;
            }
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }

    private static Direction getFacingDirection(BlockState state) {
        Direction direction = state.get(FACING);
        return !state.get(OPEN) ? direction : (state.get(HINGE) == DoorHinge.RIGHT ? direction.rotateYCounterclockwise() : direction.rotateYClockwise());
    }
*/
}
