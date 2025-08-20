package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.entity.ai.goal.CatSitOnBlockGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CatSitOnBlockGoal.class)
public class CatSitOnBlockGoalMixin {
    @ModifyExpressionValue(method = "isTargetPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 0))
    private boolean isChest(boolean chest, WorldView world, BlockPos pos) {
        return chest || world.getBlockState(pos).isIn(KaleidoscopeBlockTags.DYED_CHESTS);
    }
}
