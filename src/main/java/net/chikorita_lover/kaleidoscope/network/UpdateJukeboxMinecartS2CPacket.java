package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record UpdateJukeboxMinecartS2CPacket(int entityId, ItemStack itemStack) implements CustomPayload {
    public static final Id<UpdateJukeboxMinecartS2CPacket> PACKET_ID = new Id<>(Kaleidoscope.of("update_jukebox_minecart"));
    public static final PacketCodec<RegistryByteBuf, UpdateJukeboxMinecartS2CPacket> PACKET_CODEC = PacketCodec.of(UpdateJukeboxMinecartS2CPacket::write, UpdateJukeboxMinecartS2CPacket::new);

    public UpdateJukeboxMinecartS2CPacket(RegistryByteBuf buf) {
        this(buf.readInt(), ItemStack.PACKET_CODEC.decode(buf));
    }

    public void write(RegistryByteBuf buf) {
        buf.writeInt(this.entityId());
        ItemStack.PACKET_CODEC.encode(buf, this.itemStack());
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
