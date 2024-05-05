package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CakeSliceItem extends Item {
    public CakeSliceItem(Item.Settings settings) {
        super(settings);
    }

    private static void addSliceFromStack(BlockPos pos, ItemStack stack, PlayerEntity player, World world) {
        if (!player.isCreative()) {
            stack.decrement(1);
        }
        world.playSound(null, pos, Blocks.CAKE.getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 0.8F);
        if (world.getBlockState(pos).isOf(Blocks.CAKE)) {
            world.setBlockState(pos, Blocks.CAKE.getDefaultState().with(Properties.BITES, Math.max(0, world.getBlockState(pos).get(Properties.BITES) - 1)));
        } else {
            world.setBlockState(pos, Blocks.CAKE.getDefaultState().with(Properties.BITES, CakeBlock.MAX_BITES));
        }
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        if (player != null) {
            if (player.isSneaking() || !world.getBlockState(pos).isOf(Blocks.CAKE)) {
                BlockPos targetPos = context.getBlockPos().add(context.getSide().getVector());
                // check if we can place a cake, or if there is a cake we can add slices to
                if (Block.sideCoversSmallSquare(world, targetPos.down(), Direction.UP) && world.getBlockState(targetPos).isReplaceable() || world.getBlockState(targetPos).isOf(Blocks.CAKE) && world.getBlockState(targetPos).get(Properties.BITES) > 0) {
                    addSliceFromStack(targetPos, stack, player, world);
                    return ActionResult.SUCCESS;
                }
            } else if (world.getBlockState(pos).isOf(Blocks.CAKE)) {
                if (world.getBlockState(pos).get(Properties.BITES) > 0) {
                    addSliceFromStack(pos, stack, player, world);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.FAIL;
    }
}
