package com.chikorita_lover.chikorita_lover_mod.mixin;

import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At("TAIL"), method = "afterBreak")
    public void dropStacks(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo info) {
        int count = 0;

        if (state.getBlock() == Blocks.CAKE) {
            count = 7 - state.get(CakeBlock.BITES);
        } else if (state.isIn(BlockTags.CANDLE_CAKES)) {
            count = 7;
        }

        if (count > 0) {
            ItemStack dropStack = new ItemStack(ModItems.CAKE_SLICE, count);
            Block.dropStack(world, pos, dropStack);
        }
    }
}
