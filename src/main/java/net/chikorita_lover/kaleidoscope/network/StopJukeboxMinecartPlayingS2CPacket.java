package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record StopJukeboxMinecartPlayingS2CPacket(int entityId) implements FabricPacket {
    public static final PacketType<StopJukeboxMinecartPlayingS2CPacket> TYPE = PacketType.create(Kaleidoscope.of("stop_jukebox_minecart_playing"), StopJukeboxMinecartPlayingS2CPacket::new);

    public StopJukeboxMinecartPlayingS2CPacket(PacketByteBuf buf) {
        this(buf.readInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(this.entityId());
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
