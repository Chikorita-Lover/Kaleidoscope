package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin extends Block {
    public ScaffoldingBlockMixin(Settings settings) {
        super(settings);
    }

    @ModifyExpressionValue(method = "calculateDistance", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isSideSolidFullSquare(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"))
    private static boolean isStable(boolean sideSolid, BlockView world, BlockPos pos) {
        return sideSolid || Kaleidoscope.isHoisted(world, pos, world.getBlockState(pos));
    }

    @ModifyReturnValue(method = "shouldBeBottom", at = @At("RETURN"))
    private boolean shouldBeBottom(boolean stable, BlockView world, BlockPos pos, int distance) {
        return stable || Kaleidoscope.isHoisted(world, pos, world.getBlockState(pos)) && !world.getBlockState(pos.down()).isOf(this);
    }
}
