package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.ModBlockSoundGroup;
import com.chikoritalover.kaleidoscope.registry.ModItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.minecraft.block.Blocks.*;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(at = @At("TAIL"), method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V")
    private static void dropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo callbackInfo) {
        if (world instanceof ServerWorld) {
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

    @Inject(at = @At("RETURN"), method = "getSoundGroup", cancellable = true)
    public void getSoundGroup(BlockState state, CallbackInfoReturnable<BlockSoundGroup> info) {
        Block block = state.getBlock();
        BlockSoundGroup soundGroup = info.getReturnValue();
        if (ImmutableList.of(ANDESITE, ANDESITE_SLAB, ANDESITE_STAIRS, ANDESITE_WALL, POLISHED_ANDESITE, POLISHED_ANDESITE_SLAB, POLISHED_ANDESITE_STAIRS).contains(block))
            info.setReturnValue(ModBlockSoundGroup.ANDESITE);
        if (ImmutableList.of(DIORITE, DIORITE_SLAB, DIORITE_STAIRS, DIORITE_WALL, POLISHED_DIORITE, POLISHED_DIORITE_SLAB, POLISHED_DIORITE_STAIRS).contains(block))
            info.setReturnValue(ModBlockSoundGroup.DIORITE);
        if (block == Blocks.WET_SPONGE) {
            info.setReturnValue(BlockSoundGroup.WET_GRASS);
        }
        if (state.isIn(BlockTags.SAPLINGS) && soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.BAMBOO_SAPLING);
        }
        if (state.isIn(BlockTags.LEAVES) && soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.AZALEA_LEAVES);
        }
        if (state.isIn(BlockTags.FLOWERS) && soundGroup == BlockSoundGroup.GRASS) {
            info.setReturnValue(BlockSoundGroup.CROP);
        }
    }
}
