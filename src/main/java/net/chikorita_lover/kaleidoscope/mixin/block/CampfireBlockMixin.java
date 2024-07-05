package net.chikorita_lover.kaleidoscope.mixin.block;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {
    @Inject(method = "isSignalFireBaseBlock", at = @At("RETURN"), cancellable = true)
    private void isSignalFireBaseBlock(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(KaleidoscopeBlockTags.SIGNAL_FIRE_BASE_BLOCKS)) {
            cir.setReturnValue(true);
        }
    }
}
