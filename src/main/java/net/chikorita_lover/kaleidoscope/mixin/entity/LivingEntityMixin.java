package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "drop", at = @At("TAIL"))
    private void dropChestContents(ServerWorld world, DamageSource damageSource, CallbackInfo ci) {
        if (this instanceof Chestable chestable) {
            chestable.kaleidoscope$dropChestContents(world);
        }
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readChestData(ReadView view, CallbackInfo ci) {
        if (this instanceof Chestable chestable) {
            chestable.kaleidoscope$readChestData(view);
        }
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeChestData(WriteView view, CallbackInfo ci) {
        if (this instanceof Chestable chestable) {
            chestable.kaleidoscope$writeChestData(view);
        }
    }

    @ModifyExpressionValue(method = "applyClimbingSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isClimbing()Z"))
    private boolean applyClimbingEffects(boolean climbing) {
        return climbing && !this.isOnGround();
    }
}
