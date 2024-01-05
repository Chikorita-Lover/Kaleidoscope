package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.item.ArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ArmorMaterials.class)
public class ArmorMaterialsMixin {
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ArmorMaterials;<init>(Ljava/lang/String;ILjava/lang/String;ILjava/util/EnumMap;ILnet/minecraft/sound/SoundEvent;FFLjava/util/function/Supplier;)V"))
    private static void registerArmorMaterial(Args args) {
        String string = args.get(0);
        if (string.equals("GOLD")) {
            args.set(3, 10);
        }
    }
}
