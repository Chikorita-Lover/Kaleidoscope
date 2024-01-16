package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onSynchronizeRecipes", at = @At("TAIL"))
    public void onSynchronizeRecipes(SynchronizeRecipesS2CPacket packet, CallbackInfo ci) {
        ClientBrewingRecipeBook clientBrewingRecipeBook = ((KaleidoscopePlayerEntity) this.client.player).kaleidoscope$getClientBrewingRecipeBook();
        ArrayList<BrewingRecipe> arrayList = new ArrayList<>(BrewingRecipe.BREWING_RECIPES);
        clientBrewingRecipeBook.reload(arrayList);
        this.client.reloadSearchProvider(BrewingRecipeBookWidget.BREWING_RECIPE_OUTPUT, clientBrewingRecipeBook.getOrderedResults());
    }
}
