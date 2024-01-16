package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Shadow @Final private List<ServerPlayerEntity> players;

    @Inject(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerRecipeBook;sendInitRecipesPacket(Lnet/minecraft/server/network/ServerPlayerEntity;)V", shift = At.Shift.AFTER))
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
        ((KaleidoscopePlayerEntity) player).kaleidoscope$getServerBrewingRecipeBook().sendInitRecipesPacket(player);
    }

    @Inject(method = "onDataPacksReloaded", at = @At("TAIL"))
    public void onDataPacksReloaded(CallbackInfo ci) {
        for (ServerPlayerEntity serverPlayerEntity : this.players) {
            ((KaleidoscopePlayerEntity) serverPlayerEntity).kaleidoscope$getServerBrewingRecipeBook().sendInitRecipesPacket(serverPlayerEntity);
        }
    }
}
