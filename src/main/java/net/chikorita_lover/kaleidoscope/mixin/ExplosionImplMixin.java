package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ExplosionImpl.class)
public abstract class ExplosionImplMixin {
    @Shadow
    @Final
    private ServerWorld world;

    @Shadow
    public abstract Explosion.DestructionType getDestructionType();

    @Inject(method = "destroyBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;onExploded(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/explosion/Explosion;Ljava/util/function/BiConsumer;)V", shift = At.Shift.AFTER))
    private void crackNeighboringBlock(List<BlockPos> positions, CallbackInfo ci, @Local BlockPos position) {
        Random random = this.world.getRandom();
        BlockPos targetPos = position.offset(Direction.random(random), random.nextBetween(1, 2));
        if (KaleidoscopeConfig.DO_BLOCK_CRACKING.get() && random.nextBoolean() && this.getDestructionType().destroysBlocks() && !positions.contains(targetPos)) {
            CrackingRecipe.tryCrackBlock(this.world, targetPos);
        }
    }
}
