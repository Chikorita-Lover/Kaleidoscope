package net.chikorita_lover.kaleidoscope.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBehavior.class)
public interface DispenserBehaviorMixin {
    @Inject(method = "registerDefaults", at = @At("TAIL"))
    private static void registerKaleidoscopeDefaults(CallbackInfo ci, @Local(ordinal = 1) ItemDispenserBehavior itemDispenserBehavior2) {
        DispenserBlock.registerBehavior(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR, itemDispenserBehavior2);
        DispenserBlock.registerBehavior(KaleidoscopeItems.NETHERITE_HORSE_ARMOR, itemDispenserBehavior2);
    }
}
