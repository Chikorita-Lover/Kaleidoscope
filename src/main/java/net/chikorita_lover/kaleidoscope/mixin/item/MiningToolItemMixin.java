package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MiningToolItem.class)
public class MiningToolItemMixin extends ToolItem {
    @Unique
    private static final DispenserBehavior DISPENSER_BEHAVIOR = new FallibleItemDispenserBehavior() {
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            BlockPos targetPos = pointer.pos().offset(pointer.state().get(DispenserBlock.FACING));
            ServerWorld world = pointer.world();
            BlockState blockState = world.getBlockState(targetPos);
            ActionResult actionResult = stack.getItem().useOnBlock(new ItemUsageContext(world, null, null, stack, world.raycastBlock(pointer.centerPos(), targetPos.toCenterPos(), targetPos, VoxelShapes.UNBOUNDED, blockState)));
            this.setSuccess(actionResult != ActionResult.FAIL && actionResult != ActionResult.PASS);
            if (this.isSuccess()) {
                stack.damage(1, world.getRandom(), null, () -> stack.setCount(0));
                return stack;
            }
            return super.dispenseSilently(pointer, stack);
        }
    };

    public MiningToolItemMixin(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void registerDispenserBehavior(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings, CallbackInfo ci) {
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
    }
}
