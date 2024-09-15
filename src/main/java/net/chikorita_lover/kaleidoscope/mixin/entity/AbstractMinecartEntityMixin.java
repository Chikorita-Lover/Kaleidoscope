package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private static void create(ServerWorld world, double x, double y, double z, AbstractMinecartEntity.Type type, ItemStack stack, PlayerEntity player, CallbackInfoReturnable<AbstractMinecartEntity> cir) {
        if (type == JukeboxMinecartEntity.JUKEBOX_TYPE) {
            cir.setReturnValue(new JukeboxMinecartEntity(world, x, y, z));
        }
    }
}
