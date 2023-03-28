package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        AbstractBlock block = AbstractBlock.class.cast(this);

        if (block instanceof CropBlock cropBlock) {
            if (dropExperience && state.get(cropBlock.getAgeProperty()) == cropBlock.getMaxAge() && world.random.nextFloat() < 0.7F) {
                ((Block) block).dropExperience(world, pos, 1);
            }
        }
    }
}
