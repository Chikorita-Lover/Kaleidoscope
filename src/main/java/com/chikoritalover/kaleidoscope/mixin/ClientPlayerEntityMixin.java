package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.network.BrewingRecipeBookDataC2SPacket;
import com.chikoritalover.kaleidoscope.network.KaleidoscopePlayNetworking;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.stat.StatHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements KaleidoscopePlayerEntity {
    @Unique
    private ClientBrewingRecipeBook brewingRecipeBook;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting, CallbackInfo ci) {
        this.brewingRecipeBook = new ClientBrewingRecipeBook();
    }

    @Override
    public ClientBrewingRecipeBook kaleidoscope$getClientBrewingRecipeBook() {
        return this.brewingRecipeBook;
    }

    @Override
    public void kaleidoscope$onBrewingRecipeDisplayed(BrewingRecipe recipe) {
        if (this.brewingRecipeBook.shouldDisplay(recipe)) {
            this.brewingRecipeBook.onRecipeDisplayed(recipe);
            PacketByteBuf buf = PacketByteBufs.create();
            BrewingRecipeBookDataC2SPacket packet = new BrewingRecipeBookDataC2SPacket(recipe);
            packet.write(buf);
            ClientPlayNetworking.send(KaleidoscopePlayNetworking.BREWING_RECIPE_BOOK_DATA, buf);
        }
    }
}
