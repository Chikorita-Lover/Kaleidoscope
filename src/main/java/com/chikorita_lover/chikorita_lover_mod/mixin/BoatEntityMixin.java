package com.chikorita_lover.chikorita_lover_mod.mixin;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBoatType;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
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

        if (boat.getBoatType() == ModBoatType.CRIMSON) {
            info.setReturnValue(ModItems.CRIMSON_BOAT);
        }

        if (boat.getBoatType() == ModBoatType.WARPED) {
            info.setReturnValue(ModItems.WARPED_BOAT);
        }
    }

    public boolean isFireImmune() {
        BoatEntity boat = BoatEntity.class.cast(this);
        Block baseBlock = boat.getBoatType().getBaseBlock();

        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }
}
