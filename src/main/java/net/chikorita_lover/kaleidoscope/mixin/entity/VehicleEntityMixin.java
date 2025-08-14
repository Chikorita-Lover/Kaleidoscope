package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VehicleEntity.class)
public abstract class VehicleEntityMixin extends Entity {
    public VehicleEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "killAndDropItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/VehicleEntity;dropStack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/entity/ItemEntity;", shift = At.Shift.AFTER))
    private void dropBanner(ServerWorld world, Item item, CallbackInfo ci) {
        if (this instanceof BannerEquippable bannerEquippable) {
            this.dropStack(world, bannerEquippable.kaleidoscope$getBannerStack());
        }
    }
}
