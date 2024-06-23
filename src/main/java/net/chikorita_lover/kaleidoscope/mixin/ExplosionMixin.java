package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.block.CrackedBlockRegistry;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public class ExplosionMixin {
    @Shadow
    @Final
    private World world;

    @Inject(method = "affectWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;onExploded(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/explosion/Explosion;Ljava/util/function/BiConsumer;)V"))
    private void tryCrackNeighbor(boolean particles, CallbackInfo ci, @Local BlockPos blockPos) {
        Random random = this.world.getRandom();
        if (random.nextBoolean()) {
            return;
        }
        BlockPos pos = blockPos.offset(Direction.random(random), random.nextBetween(1, 2));
        BlockState state = this.world.getBlockState(pos);
        if (CrackedBlockRegistry.contains(state.getBlock())) {
            this.world.setBlockState(pos, CrackedBlockRegistry.get(state.getBlock()).getStateWithProperties(state));
            this.world.playSoundAtBlockCenter(pos, KaleidoscopeSoundEvents.RANDOM_BLOCK_CRACK, SoundCategory.BLOCKS, 1.0F, MathHelper.nextBetween(random, 0.8F, 1.2F), true);
        }
    }
}
