package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow
    public abstract Block getBlock();

    @Inject(method = "getPlacementContext", at = @At("HEAD"), cancellable = true)
    private void tryLadderPlacement(ItemPlacementContext context, CallbackInfoReturnable<ItemPlacementContext> cir) {
        if (this.getBlock() instanceof LadderBlock && !context.shouldCancelInteraction()) {
            BlockPos pos = context.getBlockPos();
            World world = context.getWorld();
            BlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof LadderBlock) {
                Direction direction = context.getVerticalPlayerLookDirection();
                Direction side = state.get(LadderBlock.FACING);
                BlockPos.Mutable mutable = pos.mutableCopy().move(direction);
                while (world.getBlockState(mutable.offset(side.getOpposite())).isSideSolidFullSquare(world, mutable.offset(side.getOpposite()), side)) {
                    state = world.getBlockState(mutable);
                    if (!(state.getBlock() instanceof LadderBlock)) {
                        if (!state.canReplace(context)) {
                            break;
                        }
                        cir.setReturnValue(ItemPlacementContext.offset(context, mutable, direction));
                        return;
                    }
                    mutable.move(direction);
                }
                cir.setReturnValue(null);
            }
        }
    }
}
