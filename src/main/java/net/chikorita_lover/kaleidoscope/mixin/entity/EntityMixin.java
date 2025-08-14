package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyExpressionValue(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"), slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isAlive()Z", ordinal = 1)))
    private boolean isOfShears(boolean bl, PlayerEntity player, Hand hand) {
        return bl || player.getStackInHand(hand).isIn(ConventionalItemTags.SHEAR_TOOLS);
    }

    @ModifyArg(method = "applyMoveEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;stepOnBlock(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;ZZLnet/minecraft/util/math/Vec3d;)Z", ordinal = 1), index = 2)
    private boolean playSoundHigherBlockState(boolean playSound, @Local(argsOnly = true) Entity.MoveEffect moveEffect, @Local(ordinal = 0, argsOnly = true) BlockState landingState) {
        return moveEffect.playsSounds() && landingState.isAir();
    }
}
