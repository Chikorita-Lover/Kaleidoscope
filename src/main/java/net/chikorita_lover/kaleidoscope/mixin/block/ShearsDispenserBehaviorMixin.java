package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShearsDispenserBehavior.class)
public class ShearsDispenserBehaviorMixin {
    @ModifyExpressionValue(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/dispenser/ShearsDispenserBehavior;tryShearBlock(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean tryCarvePumpkin(boolean success, @Local(argsOnly = true) BlockPointer pointer) {
        if (success) {
            return true;
        }
        ServerWorld world = pointer.world();
        BlockState state = pointer.state();
        Direction dispenserDirection = state.get(DispenserBlock.FACING);
        BlockPos pos = pointer.pos().offset(dispenserDirection);
        if (world.getBlockState(pos).getBlock() instanceof PumpkinBlock) {
            Direction direction = dispenserDirection.getAxis() == Direction.Axis.Y ? Direction.NORTH : dispenserDirection.getOpposite();
            world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS);
            world.setBlockState(pos, Blocks.CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, direction), Block.NOTIFY_ALL_AND_REDRAW);
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5 + direction.getOffsetX() * 0.65, pos.getY() + 0.1, pos.getZ() + 0.5 + direction.getOffsetZ() * 0.65, new ItemStack(Items.PUMPKIN_SEEDS, 4));
            itemEntity.setVelocity(0.05 * direction.getOffsetX() + world.getRandom().nextDouble() * 0.02, 0.05, 0.05 * direction.getOffsetZ() + world.getRandom().nextDouble() * 0.02);
            world.spawnEntity(itemEntity);
            world.emitGameEvent(null, GameEvent.SHEAR, pos);
            return true;
        }
        return false;
    }
}
