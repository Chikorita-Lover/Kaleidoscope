package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Predicate;

@Mixin(AllayEntity.class)
public abstract class AllayEntityMixin extends LivingEntity {
    @Unique
    private static final Predicate<Entity> PLAYING_JUKEBOX_MINECART_PREDICATE = entity -> entity instanceof JukeboxMinecartEntity jukeboxMinecart && jukeboxMinecart.isPlayingRecord() && !jukeboxMinecart.isSilent();

    protected AllayEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "shouldStopDancing", at = @At("RETURN"))
    public boolean shouldStopDancing(boolean stopDancing) {
        return stopDancing && !this.isNearPlayingJukeboxMinecart();
    }

    @ModifyArg(method = "updateJukeboxPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AllayEntity;setDancing(Z)V"))
    public boolean setDancingOnUpdateJukeboxPos(boolean dancing) {
        return dancing || this.isNearPlayingJukeboxMinecart();
    }

    @Unique
    private boolean isNearPlayingJukeboxMinecart() {
        return !this.getWorld().getOtherEntities(this, new Box(this.getBlockPos()).expand(10.0), PLAYING_JUKEBOX_MINECART_PREDICATE).isEmpty();
    }
}
