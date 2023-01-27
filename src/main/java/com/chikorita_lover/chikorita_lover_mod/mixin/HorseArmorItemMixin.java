package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorseArmorItem.class)
public class HorseArmorItemMixin {
    private final String entityTexture;

    public HorseArmorItemMixin(String entityTexture) {
        this.entityTexture = entityTexture;
    }

    @Inject(at = @At("HEAD"), method = "getEntityTexture", cancellable = true)
    public void getEntityTexture(CallbackInfoReturnable<Identifier> info) {
        HorseArmorItem item = HorseArmorItem.class.cast(this);
        String namespace = Registry.ITEM.getId(item.asItem()).getNamespace();

        info.setReturnValue(new Identifier(namespace, this.entityTexture));
    }
}
