package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItems;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoatEntity.class)
public class ChestBoatEntityMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> info) {
        ChestBoatEntity chestBoat = ChestBoatEntity.class.cast(this);

        if (chestBoat.getVariant() == KaleidoscopeItems.CRIMSON_BOAT_TYPE) {
            info.setReturnValue(KaleidoscopeItems.CRIMSON_CHEST_BOAT);
        }

        if (chestBoat.getVariant() == KaleidoscopeItems.WARPED_BOAT_TYPE) {
            info.setReturnValue(KaleidoscopeItems.WARPED_CHEST_BOAT);
        }
    }

    public boolean isFireImmune() {
        ChestBoatEntity chestBoat = ChestBoatEntity.class.cast(this);
        Block baseBlock = chestBoat.getVariant().getBaseBlock();

        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }
}
