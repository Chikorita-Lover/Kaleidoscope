package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.block.CrackedBlockRegistry;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public class FireBlockMixin {
    @Unique
    private static final float CRACK_CHANCE = 0.33333333F;

    @Inject(method = "trySpreadingFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    private void tryBurningToCharcoal(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
        BlockState state = world.getBlockState(pos);
        if (state.isIn(KaleidoscopeBlockTags.BURNS_INTO_CHARCOAL) && world.getRandom().nextFloat() < 0.17F) {
            world.setBlockState(pos, KaleidoscopeBlocks.CHARCOAL_BLOCK.getStateWithProperties(state), Block.NOTIFY_ALL);
            ci.cancel();
        }
    }

    @Inject(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", shift = At.Shift.AFTER, ordinal = 0))
    private void tryCrackingBlock(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos targetPos = pos.offset(Direction.random(random));
        BlockState targetState = world.getBlockState(targetPos);
        if (random.nextFloat() >= CRACK_CHANCE || !CrackedBlockRegistry.contains(targetState.getBlock())) {
            return;
        }
        world.setBlockState(targetPos, CrackedBlockRegistry.get(targetState.getBlock()).getStateWithProperties(targetState));
        world.playSound(null, targetPos.getX(), targetPos.getY(), targetPos.getZ(), KaleidoscopeSoundEvents.RANDOM_BLOCK_CRACK, SoundCategory.BLOCKS, 0.5F, MathHelper.nextBetween(random, 0.8F, 1.2F));
    }
}
