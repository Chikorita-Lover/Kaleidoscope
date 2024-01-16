package com.chikoritalover.kaleidoscope.network;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Identifier;

public class BrewRequestC2SPacket implements Packet<ServerPlayPacketListener> {
    private final int syncId;
    private final Identifier recipeId;
    private final boolean brewAll;

    public BrewRequestC2SPacket(int syncId, BrewingRecipe recipe, boolean brewAll) {
        this.syncId = syncId;
        this.recipeId = recipe.getId();
        this.brewAll = brewAll;
    }

    public BrewRequestC2SPacket(PacketByteBuf buf) {
        this.syncId = buf.readByte();
        this.recipeId = buf.readIdentifier();
        this.brewAll = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeByte(this.syncId);
        buf.writeIdentifier(this.recipeId);
        buf.writeBoolean(this.brewAll);
    }

    @Override
    public void apply(ServerPlayPacketListener listener) {

    }

    public int getSyncId() {
        return this.syncId;
    }

    public Identifier getRecipeId() {
        return this.recipeId;
    }

    public boolean shouldBrewAll() {
        return this.brewAll;
    }
}
