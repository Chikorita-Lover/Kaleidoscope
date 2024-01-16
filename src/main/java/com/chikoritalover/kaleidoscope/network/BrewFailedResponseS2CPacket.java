package com.chikoritalover.kaleidoscope.network;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Identifier;

public class BrewFailedResponseS2CPacket implements Packet<ClientPlayPacketListener> {
    private final int syncId;
    private final Identifier recipeId;

    public BrewFailedResponseS2CPacket(int syncId, BrewingRecipe recipe) {
        this.syncId = syncId;
        this.recipeId = recipe.getId();
    }

    public BrewFailedResponseS2CPacket(PacketByteBuf buf) {
        this.syncId = buf.readByte();
        this.recipeId = buf.readIdentifier();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeByte(this.syncId);
        buf.writeIdentifier(this.recipeId);
    }

    @Override
    public void apply(ClientPlayPacketListener clientPlayPacketListener) {
    }

    public Identifier getRecipeId() {
        return this.recipeId;
    }

    public int getSyncId() {
        return this.syncId;
    }
}
