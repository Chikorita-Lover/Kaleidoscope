package com.chikorita_lover.chikorita_lover_mod.mixin;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBoatType;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
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

        if (chestBoat.getBoatType() == ModBoatType.CRIMSON) {
            info.setReturnValue(ModItems.CRIMSON_CHEST_BOAT);
        }

        if (chestBoat.getBoatType() == ModBoatType.WARPED) {
            info.setReturnValue(ModItems.WARPED_CHEST_BOAT);
        }
    }

    public boolean isFireImmune() {
        ChestBoatEntity chestBoat = ChestBoatEntity.class.cast(this);
        Block baseBlock = chestBoat.getBoatType().getBaseBlock();

        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }
}
