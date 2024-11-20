package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin extends Block {
    @Shadow
    @Final
    public static BooleanProperty BOTTOM;
    @Shadow
    @Final
    private static VoxelShape COLLISION_SHAPE;
    @Shadow
    @Final
    private static VoxelShape OUTLINE_SHAPE;

    public ScaffoldingBlockMixin(Settings settings) {
        super(settings);
    }

    @ModifyExpressionValue(method = "calculateDistance", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideSolidFullSquare(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"))
    private static boolean isStable(boolean sideSolid, BlockView world, BlockPos pos) {
        return sideSolid || Kaleidoscope.isHoisted(world, pos, world.getBlockState(pos));
    }

    @ModifyReturnValue(method = "getCollisionShape", at = @At(value = "RETURN", ordinal = 1))
    private VoxelShape getHoistedShape(VoxelShape shape, BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Kaleidoscope.isHoisted(world, pos, state) && state.get(BOTTOM) && context.isAbove(OUTLINE_SHAPE, pos, true) ? COLLISION_SHAPE : shape;
    }

    @ModifyReturnValue(method = "shouldBeBottom", at = @At("RETURN"))
    private boolean shouldBeBottom(boolean stable, BlockView world, BlockPos pos, int distance) {
        return stable || Kaleidoscope.isHoisted(world, pos, world.getBlockState(pos)) && !world.getBlockState(pos.down()).isOf(this);
    }
}
