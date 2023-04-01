package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
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
                    World world = pointer.getWorld();
                    Position pos = DispenserBlock.getOutputLocation(pointer);
                    BlockPos blockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                    BlockState blockState = world.getBlockState(blockPos);
                    ActionResult actionResult = stack.getItem().useOnBlock(new ItemUsageContext(world, null, null, stack, pointer.getWorld().raycastBlock(new Vec3d(pointer.getX(), pointer.getY(), pointer.getZ()), new Vec3d(pos.getX(), pos.getY(), pos.getZ()), blockPos, VoxelShapes.UNBOUNDED, blockState)));
                    this.setSuccess(actionResult != ActionResult.FAIL && actionResult != ActionResult.PASS);
                    if (this.isSuccess() && stack.damage(1, world.random, null)) {
                        stack.setCount(0);
                    }
                    return stack;
                }
            });
        }
    }
}
