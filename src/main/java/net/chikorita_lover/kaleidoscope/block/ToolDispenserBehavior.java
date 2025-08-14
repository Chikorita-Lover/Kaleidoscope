package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ToolDispenserBehavior extends FallibleItemDispenserBehavior {
    public static final ToolDispenserBehavior INSTANCE = new ToolDispenserBehavior();

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ServerWorld world = pointer.world();
        if (!world.isClient()) {
            Direction facing = pointer.state().get(DispenserBlock.FACING);
            BlockPos targetPos = pointer.pos().offset(facing);
            BlockHitResult hitResult = new BlockHitResult(pointer.centerPos().offset(facing, 0.5), facing.getOpposite(), targetPos, false);
            try {
                this.setSuccess(stack.getItem().useOnBlock(new ItemUsageContext(world, null, null, stack, hitResult)).isAccepted());
            } catch (Exception exception) {
                Kaleidoscope.LOGGER.error("Error trying to use item {} via dispenser at {}", stack.getItem(), targetPos, exception);
            }
            if (this.isSuccess()) {
                stack.damage(1, world, null, item -> {
                });
            }
        }
        return stack;
    }
}
