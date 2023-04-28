package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowballEntity.class)
public class SnowballEntityMixin {
    @Inject(at = @At("HEAD"), method = "handleStatus")
    public void handleStatus(byte status, CallbackInfo info) {
        SnowballEntity entity = SnowballEntity.class.cast(this);

        if (status == 3) {
            entity.world.playSound(entity.getX(), entity.getY(), entity.getZ(), KaleidoscopeSoundEvents.ENTITY_SNOWBALL_HIT, SoundCategory.NEUTRAL, 1.0F, 1.0F, true);
        }
    }
}
