package com.chikorita_lover.chikorita_lover_mod.item;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CakeSliceItem extends Item {
    public CakeSliceItem(Item.Settings settings) {
        super(settings);
    }

    public void addSliceFromStack(BlockPos blockPos, ItemStack itemStack, PlayerEntity player, World world) {
        if (!player.isCreative()) {
            itemStack.decrement(1);
        }

        world.playSound(null, blockPos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
        if (world.getBlockState(blockPos).isOf(Blocks.CAKE)) {
            world.setBlockState(blockPos, Blocks.CAKE.getDefaultState().with(Properties.BITES, Math.max(0, world.getBlockState(blockPos).get(Properties.BITES) - 1)));
        } else {
            world.setBlockState(blockPos, Blocks.CAKE.getDefaultState().with(Properties.BITES, 6));
        }
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        ItemStack itemStack = context.getStack();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        assert player != null;

        if (player.isSneaking() || !world.getBlockState(blockPos).isOf(Blocks.CAKE)) {
            BlockPos blockPos2 = context.getBlockPos().add(context.getSide().getVector());

            //Check if we can place a cake, or if there is a cake we can add slices to
            if (Blocks.CAKE.canPlaceAt(Blocks.CAKE.getDefaultState(), world, blockPos2) && world.getBlockState(blockPos2).getMaterial().isReplaceable() || world.getBlockState(blockPos2).isOf(Blocks.CAKE) && world.getBlockState(blockPos2).get(Properties.BITES) > 0) {
                addSliceFromStack(blockPos2, itemStack, player, world);
                return ActionResult.SUCCESS;
            }
        } else if (world.getBlockState(blockPos).isOf(Blocks.CAKE)) {
            if (world.getBlockState(blockPos).get(Properties.BITES) > 0) {
                addSliceFromStack(blockPos, itemStack, player, world);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}
