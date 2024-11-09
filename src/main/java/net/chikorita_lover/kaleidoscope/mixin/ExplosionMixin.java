package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
public abstract class ExplosionMixin {
    @Shadow
    @Final
    private World world;
    @Shadow
    @Final
    private ObjectArrayList<BlockPos> affectedBlocks;

    @Shadow
    public abstract Explosion.DestructionType getDestructionType();

    @Inject(method = "affectWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;onExploded(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/explosion/Explosion;Ljava/util/function/BiConsumer;)V", shift = At.Shift.AFTER))
    private void tryCrackNeighbor(boolean particles, CallbackInfo ci, @Local BlockPos blockPos) {
        Random random = this.world.getRandom();
        BlockPos pos = blockPos.offset(Direction.random(random), random.nextBetween(1, 2));
        if (random.nextBoolean() && this.getDestructionType() != Explosion.DestructionType.TRIGGER_BLOCK && !this.affectedBlocks.contains(pos) && this.world instanceof ServerWorld serverWorld) {
            CrackingRecipe.tryCrackBlock(serverWorld, pos);
        }
    }
}
