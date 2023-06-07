package com.chikoritalover.kaleidoscope.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", shift = At.Shift.BEFORE))
    public void onDeath(DamageSource damageSource, CallbackInfo callbackInfo) {
        LivingEntity livingEntity = LivingEntity.class.cast(this);
        if (!livingEntity.getWorld().isClient && livingEntity.getWorld().getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES) && livingEntity.hasCustomName()) {
            if (livingEntity instanceof TameableEntity entity && entity.isTamed()) return;
            livingEntity.getServer().getPlayerManager().broadcast(livingEntity.getDamageTracker().getDeathMessage(), false);
        }
    }
}
