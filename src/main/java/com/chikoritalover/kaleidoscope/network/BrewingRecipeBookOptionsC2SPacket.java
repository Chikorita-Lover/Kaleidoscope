package com.chikoritalover.kaleidoscope.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;

public class BrewingRecipeBookOptionsC2SPacket implements Packet<ServerPlayPacketListener> {
    private final boolean guiOpen;
    private final boolean filteringBrewable;

    public BrewingRecipeBookOptionsC2SPacket(boolean guiOpen, boolean filteringBrewable) {
        this.guiOpen = guiOpen;
        this.filteringBrewable = filteringBrewable;
    }

    public BrewingRecipeBookOptionsC2SPacket(PacketByteBuf buf) {
        this.guiOpen = buf.readBoolean();
        this.filteringBrewable = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeBoolean(this.guiOpen);
        buf.writeBoolean(this.filteringBrewable);
    }

    @Override
    public void apply(ServerPlayPacketListener serverPlayPacketListener) {
    }

    public boolean isGuiOpen() {
        return this.guiOpen;
    }

    public boolean isFilteringBrewable() {
        return this.filteringBrewable;
    }
}
