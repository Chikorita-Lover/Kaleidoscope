package net.chikorita_lover.kaleidoscope.mixin.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlock.class)
public class FallingBlockMixin {
    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo callbackInfo) {
        BlockState blockState = world.getBlockState(pos.up());
        if (blockState.getBlock() instanceof ChainBlock && Block.sideCoversSmallSquare(world, pos, Direction.UP)) {
            if (blockState.get(ChainBlock.AXIS) == Direction.Axis.Y) {
                callbackInfo.cancel();
            }
        }
    }
}
