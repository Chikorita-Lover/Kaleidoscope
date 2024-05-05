package net.chikorita_lover.kaleidoscope.mixin.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/MiningToolItem;<init>(Lnet/minecraft/item/ToolMaterial;Lnet/minecraft/registry/tag/TagKey;Lnet/minecraft/item/Item$Settings;)V"))
    private static void init(Args args) {
        if (args.get(0).equals(ToolMaterials.GOLD)) {
            args.set(2, ((Item.Settings) args.get(2)).attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.GOLD, 7.0F, -3.2F)));
        }
    }
}
