package com.chikoritalover.kaleidoscope.network;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Identifier;

public class BrewingRecipeBookDataC2SPacket implements Packet<ServerPlayPacketListener> {
    private final Identifier recipeId;

    public BrewingRecipeBookDataC2SPacket(BrewingRecipe recipe) {
        this.recipeId = recipe.getId();
    }

    public BrewingRecipeBookDataC2SPacket(PacketByteBuf buf) {
        this.recipeId = buf.readIdentifier();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeIdentifier(this.recipeId);
    }

    @Override
    public void apply(ServerPlayPacketListener listener) {
    }

    public Identifier getRecipeId() {
        return this.recipeId;
    }
}
