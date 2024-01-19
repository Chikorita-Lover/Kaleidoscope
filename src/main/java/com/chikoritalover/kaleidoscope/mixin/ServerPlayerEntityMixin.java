package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingUtil;
import com.chikoritalover.kaleidoscope.server.ServerBrewingRecipeBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements KaleidoscopePlayerEntity {
    @Unique
    private final ServerBrewingRecipeBook brewingRecipeBook = new ServerBrewingRecipeBook();
    @Shadow
    @Final
    public MinecraftServer server;

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSleeping()Z", shift = At.Shift.BEFORE))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("brewingRecipeBook", NbtElement.COMPOUND_TYPE)) {
            this.brewingRecipeBook.readNbt(nbt.getCompound("brewingRecipeBook"));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.put("brewingRecipeBook", this.brewingRecipeBook.toNbt());
    }

    @Override
    public int kaleidoscope$unlockRecipes(Collection<BrewingRecipe> recipes) {
        return this.brewingRecipeBook.unlockRecipes(recipes, ServerPlayerEntity.class.cast(this));
    }

    @Override
    public int kaleidoscope$lockRecipes(Collection<BrewingRecipe> recipes) {
        return this.brewingRecipeBook.lockRecipes(recipes, ServerPlayerEntity.class.cast(this));
    }

    @Inject(method = "copyFrom", at = @At("TAIL"))
    public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        this.brewingRecipeBook.copyFrom(((KaleidoscopePlayerEntity) oldPlayer).kaleidoscope$getServerBrewingRecipeBook());
    }

    @Override
    public ServerBrewingRecipeBook kaleidoscope$getServerBrewingRecipeBook() {
        return this.brewingRecipeBook;
    }

    @Mixin(targets = "net.minecraft.server.network.ServerPlayerEntity$2")
    static class ServerPlayerEntity2Mixin {
        @Final
        ServerPlayerEntity field_29183;

        @Inject(method = "onSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/InventoryChangedCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
        public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack, CallbackInfo ci) {
            ((KaleidoscopePlayerEntity) field_29183).kaleidoscope$unlockRecipes(BrewingUtil.getUnlockableRecipes(stack));
        }
    }
}
