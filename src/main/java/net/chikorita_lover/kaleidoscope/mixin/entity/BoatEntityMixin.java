package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeItems;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends VehicleEntity {
    public BoatEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract BoatEntity.Type getVariant();

    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    protected void asItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == KaleidoscopeItems.CRIMSON_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.CRIMSON_BOAT);
        }
        if (this.getVariant() == KaleidoscopeItems.WARPED_BOAT_TYPE) {
            cir.setReturnValue(KaleidoscopeItems.WARPED_BOAT);
        }
    }

    @Override
    public boolean isFireImmune() {
        Block baseBlock = this.getVariant().getBaseBlock();
        return FlammableBlockRegistry.getDefaultInstance().get(baseBlock).getBurnChance() == 0;
    }
}
