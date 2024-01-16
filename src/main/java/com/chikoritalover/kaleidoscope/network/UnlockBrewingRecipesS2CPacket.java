package com.chikoritalover.kaleidoscope.network;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.List;

public class UnlockBrewingRecipesS2CPacket implements Packet<ClientPlayPacketListener> {
    private final Action action;
    private final List<Identifier> recipesToChange;
    private final List<Identifier> recipesToInit;
    private final boolean isFilteringBrewable;
    private final boolean isGuiOpen;

    public UnlockBrewingRecipesS2CPacket(Action action, Collection<Identifier> recipesToChange, Collection<Identifier> recipesToInit, boolean isGuiOpen, boolean isFilteringBrewable) {
        this.action = action;
        this.recipesToChange = ImmutableList.copyOf(recipesToChange);
        this.recipesToInit = ImmutableList.copyOf(recipesToInit);
        this.isFilteringBrewable = isFilteringBrewable;
        this.isGuiOpen = isGuiOpen;
    }

    public UnlockBrewingRecipesS2CPacket(PacketByteBuf buf) {
        this.action = buf.readEnumConstant(Action.class);
        this.recipesToChange = buf.readList(PacketByteBuf::readIdentifier);
        this.recipesToInit = this.action == Action.INIT ? buf.readList(PacketByteBuf::readIdentifier) : ImmutableList.of();
        this.isFilteringBrewable = buf.readBoolean();
        this.isGuiOpen = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeEnumConstant(this.action);
        buf.writeCollection(this.recipesToChange, PacketByteBuf::writeIdentifier);
        if (this.action == Action.INIT) {
            buf.writeCollection(this.recipesToInit, PacketByteBuf::writeIdentifier);
        }
        buf.writeBoolean(this.isFilteringBrewable);
        buf.writeBoolean(this.isGuiOpen);
    }

    @Override
    public void apply(ClientPlayPacketListener handler) {
    }

    public List<Identifier> getRecipeIdsToChange() {
        return this.recipesToChange;
    }

    public List<Identifier> getRecipeIdsToInit() {
        return this.recipesToInit;
    }

    public Action getAction() {
        return this.action;
    }

    public boolean isFilteringBrewable() {
        return this.isFilteringBrewable;
    }

    public boolean isGuiOpen() {
        return this.isGuiOpen;
    }

    public enum Action {
        INIT,
        ADD,
        REMOVE
    }
}
