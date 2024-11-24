package net.chikorita_lover.kaleidoscope.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$12")
public class DiggerEnchantmentTargetMixin {
    @ModifyReturnValue(method = "isAcceptableItem", at = @At("RETURN"))
    private boolean isAcceptableItems(boolean acceptableItem, Item item) {
        return acceptableItem || Registries.ITEM.getOrCreateEntryList(ConventionalItemTags.SHEARS).contains(item.getRegistryEntry());
    }
}
