package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.block.entity.KaleidoscopeBrewingStandBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandBlock.class)
public class BrewingStandBlockMixin {
    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStateReplaced(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V", shift = At.Shift.BEFORE))
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BrewingStandBlockEntity && world instanceof ServerWorld) {
            ((KaleidoscopeBrewingStandBlockEntity) blockEntity).kaleidoscope$getRecipesUsedAndDropExperience((ServerWorld) world, pos.toCenterPos());
        }
    }
}
