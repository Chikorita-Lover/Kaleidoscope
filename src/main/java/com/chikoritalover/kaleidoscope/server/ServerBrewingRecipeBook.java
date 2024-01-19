package com.chikoritalover.kaleidoscope.server;

import com.chikoritalover.kaleidoscope.network.KaleidoscopePlayNetworking;
import com.chikoritalover.kaleidoscope.network.UnlockBrewingRecipesS2CPacket;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeBook;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ServerBrewingRecipeBook extends BrewingRecipeBook {
    public int unlockRecipes(Collection<BrewingRecipe> recipes, ServerPlayerEntity player) {
        ArrayList<Identifier> list = Lists.newArrayList();
        for (BrewingRecipe recipe : recipes) {
            Identifier identifier = recipe.getId();
            if (this.recipeIds.contains(identifier)) continue;
            this.add(identifier);
            this.display(recipe);
            list.add(identifier);
        }
        if (!list.isEmpty()) {
            this.sendUnlockRecipesPacket(UnlockBrewingRecipesS2CPacket.Action.ADD, player, list);
        }
        return list.size();
    }

    public int lockRecipes(Collection<BrewingRecipe> recipes, ServerPlayerEntity player) {
        ArrayList<Identifier> list = Lists.newArrayList();
        for (BrewingRecipe recipe : recipes) {
            Identifier identifier = recipe.getId();
            if (!this.recipeIds.contains(identifier)) continue;
            this.remove(identifier);
            list.add(identifier);
        }
        this.sendUnlockRecipesPacket(UnlockBrewingRecipesS2CPacket.Action.REMOVE, player, list);
        return list.size();
    }

    private void sendUnlockRecipesPacket(UnlockBrewingRecipesS2CPacket.Action action, ServerPlayerEntity player, List<Identifier> recipes) {
        PacketByteBuf buf = PacketByteBufs.create();
        UnlockBrewingRecipesS2CPacket packet = new UnlockBrewingRecipesS2CPacket(action, recipes, Collections.emptyList(), this.isGuiOpen(), this.isFilteringBrewable());
        packet.write(buf);
        ServerPlayNetworking.send(player, KaleidoscopePlayNetworking.UNLOCK_BREWING_RECIPES, buf);
    }

    public NbtCompound toNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        NbtList nbtList = new NbtList();
        for (Identifier identifier : this.recipeIds) {
            nbtList.add(NbtString.of(identifier.toString()));
        }
        nbtCompound.put("recipes", nbtList);
        NbtList nbtList2 = new NbtList();
        for (Identifier identifier : this.toBeDisplayed) {
            nbtList2.add(NbtString.of(identifier.toString()));
        }
        nbtCompound.put("toBeDisplayed", nbtList2);
        nbtCompound.put("isFilteringBrewable", NbtByte.of(this.isFilteringBrewable()));
        nbtCompound.put("isGuiOpen", NbtByte.of(this.isGuiOpen()));
        return nbtCompound;
    }

    public void readNbt(NbtCompound nbt) {
        this.setFilteringBrewable(nbt.getBoolean("isFilteringBrewable"));
        this.setGuiOpen(nbt.getBoolean("isGuiOpen"));
        NbtList nbtList = nbt.getList("recipes", NbtElement.STRING_TYPE);
        this.handleList(nbtList, this::add);
        NbtList nbtList2 = nbt.getList("toBeDisplayed", NbtElement.STRING_TYPE);
        this.handleList(nbtList2, this::display);
    }

    private void handleList(NbtList list, Consumer<Identifier> handler) {
        for (int i = 0; i < list.size(); ++i) {
            handler.accept(new Identifier(list.getString(i)));
        }
    }

    public void sendInitRecipesPacket(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        UnlockBrewingRecipesS2CPacket packet = new UnlockBrewingRecipesS2CPacket(UnlockBrewingRecipesS2CPacket.Action.INIT, this.toBeDisplayed, this.recipeIds, this.isGuiOpen(), this.isFilteringBrewable());
        packet.write(buf);
        ServerPlayNetworking.send(player, KaleidoscopePlayNetworking.UNLOCK_BREWING_RECIPES, buf);
    }
}
