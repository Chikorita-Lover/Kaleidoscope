package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record OpenStriderScreenS2CPacket(int syncId, int striderId) implements CustomPayload {
    public static final Id<OpenStriderScreenS2CPacket> PACKET_ID = new Id<>(new Identifier(Kaleidoscope.MODID, "open_strider_screen"));
    public static final PacketCodec<RegistryByteBuf, OpenStriderScreenS2CPacket> PACKET_CODEC = PacketCodec.of(OpenStriderScreenS2CPacket::write, OpenStriderScreenS2CPacket::new);

    public OpenStriderScreenS2CPacket(RegistryByteBuf buf) {
        this(buf.readInt(), buf.readInt());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeInt(this.syncId);
        buf.writeInt(this.striderId);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
