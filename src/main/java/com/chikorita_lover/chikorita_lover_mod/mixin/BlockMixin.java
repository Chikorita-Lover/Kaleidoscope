package com.chikorita_lover.chikorita_lover_mod.mixin;

import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(at = @At("HEAD"), method = "getSoundGroup", cancellable = true)
    public void getSoundGroup(BlockState state, CallbackInfoReturnable<BlockSoundGroup> info) {
        Block block = Block.class.cast(this);

        if (state.isIn(BlockTags.SAPLINGS) && block.soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.AZALEA);
        }

        if (state.isIn(BlockTags.SMALL_FLOWERS) && block.soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.CROP);
        }

        if (state.isIn(BlockTags.TALL_FLOWERS) && block.soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.FLOWERING_AZALEA);
        }
    }
}
