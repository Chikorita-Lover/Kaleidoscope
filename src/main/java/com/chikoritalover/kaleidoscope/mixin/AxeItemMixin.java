package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/MiningToolItem;<init>(FFLnet/minecraft/item/ToolMaterial;Lnet/minecraft/registry/tag/TagKey;Lnet/minecraft/item/Item$Settings;)V"))
    private static void init(Args args) {
        if (args.get(2) == ToolMaterials.GOLD) {
            args.set(0, 7.0F);
            args.set(1, -3.2F);
        }
    }
}
