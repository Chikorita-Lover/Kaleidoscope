package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @Inject(method = "getBehaviorForItem", at = @At("HEAD"), cancellable = true)
    public void getBehaviorForItem(ItemStack stack, CallbackInfoReturnable<DispenserBehavior> callbackInfo) {
        Item item = stack.getItem();
        if (item instanceof MiningToolItem) {
            callbackInfo.setReturnValue(new FallibleItemDispenserBehavior() {
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                    ServerWorld world = pointer.getWorld();
                    BlockState blockState = world.getBlockState(blockPos);
                    ActionResult actionResult = stack.getItem().useOnBlock(new ItemUsageContext(world, null, null, stack, pointer.getWorld().raycastBlock(new Vec3d(pointer.getX(), pointer.getY(), pointer.getZ()), new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()), blockPos, VoxelShapes.UNBOUNDED, blockState)));
                    this.setSuccess(actionResult != ActionResult.FAIL && actionResult != ActionResult.PASS);
                    if (this.isSuccess()) {
                        if (stack.damage(1, world.random, null)) {
                            stack.setCount(0);
                        }
                        return stack;
                    }
                    return super.dispenseSilently(pointer, stack);
                }
            });
            callbackInfo.cancel();
        }
    }
}
