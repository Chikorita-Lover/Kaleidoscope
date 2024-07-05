package net.chikorita_lover.kaleidoscope.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import org.jetbrains.annotations.Nullable;

public class CakeSliceItem extends Item {
    public CakeSliceItem(Item.Settings settings) {
        super(settings);
    }

    private static void addSliceFromStack(World world, BlockPos pos, ItemStack stack, @Nullable PlayerEntity player) {
        if (player == null || !player.isCreative()) {
            stack.decrement(1);
        }
        BlockState oldState = world.getBlockState(pos);
        int bites = oldState.isOf(Blocks.CAKE) ? oldState.get(Properties.BITES) - 1 : CakeBlock.MAX_BITES;
        BlockState newState = Blocks.CAKE.getDefaultState().with(Properties.BITES, bites);
        world.setBlockState(pos, newState);
        world.playSound(null, pos, newState.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 0.8F);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        if (player != null) {
            player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        if (!state.isOf(Blocks.CAKE) || (player != null && player.isSneaking())) {
            BlockPos targetPos = context.getBlockPos().add(context.getSide().getVector());
            // check if we can place a cake, or if there is a cake we can add slices to
            BlockState targetState = world.getBlockState(targetPos);
            if (Block.sideCoversSmallSquare(world, targetPos.down(), Direction.UP) && targetState.isReplaceable() || targetState.isOf(Blocks.CAKE) && targetState.get(Properties.BITES) > 0) {
                addSliceFromStack(world, targetPos, stack, player);
                return ActionResult.SUCCESS;
            }
        } else {
            if (state.get(Properties.BITES) > 0) {
                addSliceFromStack(world, pos, stack, player);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
