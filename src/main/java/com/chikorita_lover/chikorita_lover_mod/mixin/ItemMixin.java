package com.chikorita_lover.chikorita_lover_mod.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Item.class)
public class ItemMixin {
    private static final Map<ItemConvertible, Integer> MAX_COUNTS = Map.of(
            Items.POTION, 16
    );

    @Inject(method = "getMaxCount", at = @At("RETURN"), cancellable = true)
    public void getMaxCount(CallbackInfoReturnable<Integer> info) {
        Item item = Item.class.cast(this);

        if (MAX_COUNTS.containsKey(item)) {
            info.setReturnValue(MAX_COUNTS.get(item));
        }
    }
}
