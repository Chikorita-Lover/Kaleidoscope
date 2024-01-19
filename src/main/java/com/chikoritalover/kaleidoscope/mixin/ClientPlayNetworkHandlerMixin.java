package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.screen.KaleidoscopeBrewingStandScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.SynchronizeRecipesS2CPacket;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Collection;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "onScreenHandlerSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/ScreenHandler;setStackInSlot(IILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
    public void onBrewingStandSlotUpdate(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        if (this.client.currentScreen instanceof BrewingStandScreen brewingStandScreen) {
            BrewingRecipeBookWidget widget = ((KaleidoscopeBrewingStandScreen) brewingStandScreen).kaleidoscope$getRecipeBookWidget();
            if (widget.isOpen()) widget.refreshInputs();

            Collection<BrewingRecipe> collection = new ArrayList<>();
            ItemStack itemStack = packet.getItemStack();
            BrewingRecipe.BREWING_RECIPES.stream().filter(recipe -> recipe.getInput().test(itemStack) && PotionUtil.getPotion(itemStack) == recipe.getInputPotion() || recipe.getInputPotion() == Potions.AWKWARD && recipe.getReagent().test(itemStack)).forEach(collection::add);
            ((KaleidoscopePlayerEntity) this.client.player).kaleidoscope$unlockRecipes(collection);
        }
    }

    @Inject(method = "onPlayerRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;setId(I)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onPlayerRespawn(PlayerRespawnS2CPacket packet, CallbackInfo ci, RegistryKey registryKey, RegistryEntry registryEntry, ClientPlayerEntity clientPlayerEntity, int i, String string, ClientPlayerEntity clientPlayerEntity2) {
        ClientBrewingRecipeBook clientBrewingRecipeBook = ((KaleidoscopePlayerEntity) clientPlayerEntity).kaleidoscope$getClientBrewingRecipeBook();
        ((KaleidoscopePlayerEntity) clientPlayerEntity2).kaleidoscope$getClientBrewingRecipeBook().copyFrom(clientBrewingRecipeBook);
    }

    @Inject(method = "onSynchronizeRecipes", at = @At("TAIL"))
    public void onSynchronizeRecipes(SynchronizeRecipesS2CPacket packet, CallbackInfo ci) {
        ClientBrewingRecipeBook clientBrewingRecipeBook = ((KaleidoscopePlayerEntity) this.client.player).kaleidoscope$getClientBrewingRecipeBook();
        ArrayList<BrewingRecipe> arrayList = new ArrayList<>(BrewingRecipe.BREWING_RECIPES);
        clientBrewingRecipeBook.reload(arrayList);
        this.client.reloadSearchProvider(BrewingRecipeBookWidget.BREWING_RECIPE_OUTPUT, clientBrewingRecipeBook.getOrderedResults());
    }
}
