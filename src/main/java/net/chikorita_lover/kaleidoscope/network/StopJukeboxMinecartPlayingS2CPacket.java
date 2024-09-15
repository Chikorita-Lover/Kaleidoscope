package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record StopJukeboxMinecartPlayingS2CPacket(int entityId) implements CustomPayload {
    public static final Id<StopJukeboxMinecartPlayingS2CPacket> PACKET_ID = new Id<>(Kaleidoscope.of("stop_jukebox_minecart_playing"));
    public static final PacketCodec<RegistryByteBuf, StopJukeboxMinecartPlayingS2CPacket> PACKET_CODEC = PacketCodec.of(StopJukeboxMinecartPlayingS2CPacket::write, StopJukeboxMinecartPlayingS2CPacket::new);

    public StopJukeboxMinecartPlayingS2CPacket(RegistryByteBuf buf) {
        this(buf.readInt());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeInt(this.entityId());
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
