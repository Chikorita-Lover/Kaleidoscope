package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.block.ToolDispenserBehavior;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @Inject(method = "getBehaviorForItem(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/dispenser/DispenserBehavior;", at = @At("HEAD"), cancellable = true)
    private static void getToolBehavior(ItemStack stack, CallbackInfoReturnable<DispenserBehavior> cir) {
        if (stack.isIn(KaleidoscopeItemTags.DISPENSER_TOOLS)) {
            cir.setReturnValue(ToolDispenserBehavior.INSTANCE);
        }
    }
}
