package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyArg(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;stepOnBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;ZZLnet/minecraft/util/math/Vec3d;)Z", ordinal = 1), index = 2)
    private boolean playSoundHigherBlockState(boolean playSound, @Local Entity.MoveEffect moveEffect, @Local(ordinal = 0) BlockState blockState) {
        return moveEffect.playsSounds() && blockState.isAir();
    }
}
