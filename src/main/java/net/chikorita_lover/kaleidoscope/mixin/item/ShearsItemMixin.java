package net.chikorita_lover.kaleidoscope.mixin.item;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin extends Item {
    @Unique
    private static final ToolMaterial SHEARS_MATERIAL = ToolMaterials.IRON;

    public ShearsItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
    private void overrideMiningSpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(state.isIn(KaleidoscopeBlockTags.SHEARS_MINEABLE) ? SHEARS_MATERIAL.getMiningSpeedMultiplier() : super.getMiningSpeedMultiplier(stack, state));
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
