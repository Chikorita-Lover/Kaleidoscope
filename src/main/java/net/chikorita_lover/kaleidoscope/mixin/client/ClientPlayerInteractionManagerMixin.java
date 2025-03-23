package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.passive.StriderEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @ModifyReturnValue(method = "hasRidingInventory", at = @At("RETURN"))
    private boolean hasValidRidingInventory(boolean original) {
        return original && (!(this.client.player.getVehicle() instanceof StriderEntity) || KaleidoscopeConfig.ALLOW_STRIDER_EQUIPMENT.get());
    }
}
