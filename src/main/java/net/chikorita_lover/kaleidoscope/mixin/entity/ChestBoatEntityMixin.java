package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoatEntity.class)
public abstract class ChestBoatEntityMixin extends BoatEntity {
    public ChestBoatEntityMixin(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    protected void asItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == KaleidoscopeItems.CRIMSON_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.CRIMSON_CHEST_BOAT);
        }
        if (this.getVariant() == KaleidoscopeItems.WARPED_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.WARPED_CHEST_BOAT);
        }
    }
}
