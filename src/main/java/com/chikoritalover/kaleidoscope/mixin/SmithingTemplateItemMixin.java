package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(SmithingTemplateItem.class)
public class SmithingTemplateItemMixin {
    @Inject(method = "getNetheriteUpgradeEmptyBaseSlotTextures", at = @At("RETURN"), cancellable = true)
    private static void getNetheriteUpgradeEmptyBaseSlotTextures(CallbackInfoReturnable<List<Identifier>> callbackInfo) {
        ArrayList<Identifier> list = new ArrayList<>(callbackInfo.getReturnValue());
        list.add(new Identifier(Kaleidoscope.MODID, "item/empty_slot_horse_armor"));
        callbackInfo.setReturnValue(list);
    }
}
