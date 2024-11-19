package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract BlockState getBlockState();

    @WrapOperation(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/TntEntity;setVelocity(DDD)V"))
    private void trySetVelocity(TntEntity entity, double x, double y, double z, Operation<Void> operation) {
        if (!this.isHoisted()) {
            operation.call(this, x, y, z);
        }
    }

    @ModifyReturnValue(method = "getGravity", at = @At("RETURN"))
    private double tryHoist(double gravity) {
        return this.isHoisted() ? 0.0F : gravity;
    }

    @Unique
    private boolean isHoisted() {
        return Kaleidoscope.isHoisted(this.getWorld(), this.getBlockPos(), this.getBlockState());
    }
}
