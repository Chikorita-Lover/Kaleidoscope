package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBoatType;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItems;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> info) {
        BoatEntity boat = BoatEntity.class.cast(this);

        if (boat.getVariant() == KaleidoscopeBoatType.CRIMSON) {
            info.setReturnValue(KaleidoscopeItems.CRIMSON_BOAT);
        }

        if (boat.getVariant() == KaleidoscopeBoatType.WARPED) {
            info.setReturnValue(KaleidoscopeItems.WARPED_BOAT);
        }
    }

    public boolean isFireImmune() {
        BoatEntity boat = BoatEntity.class.cast(this);
        Block baseBlock = boat.getVariant().getBaseBlock();

        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }
}
