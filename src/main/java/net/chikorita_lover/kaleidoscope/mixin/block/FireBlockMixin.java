package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlockTags;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class FireBlockMixin {
    @Inject(method = "trySpreadingFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    public void trySpreadingFire(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo callbackInfo) {
        BlockState state = world.getBlockState(pos);
        if (state.isIn(KaleidoscopeBlockTags.BURNS_INTO_CHARCOAL) && world.getRandom().nextFloat() < 0.17F) {
            BlockState blockState = KaleidoscopeBlocks.CHARCOAL_BLOCK.getDefaultState();
            if (state.getProperties().contains(Properties.AXIS)) {
                blockState = blockState.with(Properties.AXIS, state.get(Properties.AXIS));
            }
            world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
            callbackInfo.cancel();
        }
    }
}
