package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.SideShapeType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FallingBlockEntity.class)
public class FallingBlockEntityMixin {
    @Inject(method = "spawnFromBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", shift = At.Shift.BEFORE), cancellable = true)
    private static void tryCancelFallingBlock(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<FallingBlockEntity> cir, @Local FallingBlockEntity fallingBlockEntity) {
        BlockState chain = world.getBlockState(pos.up());
        if (chain.getBlock() instanceof ChainBlock && chain.get(ChainBlock.AXIS).isVertical() && chain.isSideSolid(world, pos, Direction.UP, SideShapeType.CENTER)) {
            fallingBlockEntity.remove(Entity.RemovalReason.DISCARDED);
            cir.setReturnValue(fallingBlockEntity);
        }
    }
}
