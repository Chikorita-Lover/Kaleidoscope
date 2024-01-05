package com.chikoritalover.kaleidoscope.mixin;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {
    @Final
    @Shadow
    public static ToolMaterials GOLD;

    @Inject(method = "getDurability", at = @At("RETURN"), cancellable = true)
    public void getDurability(CallbackInfoReturnable<Integer> cir) {
        if (this.equals(GOLD)) {
            cir.setReturnValue(91);
        }
    }

    @Inject(method = "getAttackDamage", at = @At("RETURN"), cancellable = true)
    public void getAttackDamage(CallbackInfoReturnable<Float> cir) {
        if (this.equals(GOLD)) {
            cir.setReturnValue(1.0F);
        }
    }

    @Inject(method = "getMiningLevel", at = @At("RETURN"), cancellable = true)
    public void getMiningLevel(CallbackInfoReturnable<Integer> cir) {
        if (this.equals(GOLD)) {
            cir.setReturnValue(MiningLevels.STONE);
        }
    }
}
