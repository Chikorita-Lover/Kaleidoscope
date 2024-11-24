package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

public record UpdateJukeboxMinecartS2CPacket(int entityId, ItemStack itemStack) implements FabricPacket {
    public static final PacketType<UpdateJukeboxMinecartS2CPacket> TYPE = PacketType.create(Kaleidoscope.of("update_jukebox_minecart"), UpdateJukeboxMinecartS2CPacket::new);

    public UpdateJukeboxMinecartS2CPacket(PacketByteBuf buf) {
        this(buf.readInt(), buf.readItemStack());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeItemStack(this.itemStack);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
