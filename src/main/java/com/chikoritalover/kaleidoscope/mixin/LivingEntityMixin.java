package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isProjectile()Z", shift = At.Shift.BEFORE))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (source.isExplosive()) {
            PlayerEntity player = PlayerEntity.class.cast(this);

            player.disableShield(true);
        }
    }
}
