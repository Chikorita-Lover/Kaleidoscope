package net.chikorita_lover.kaleidoscope.network;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record OpenStriderScreenS2CPacket(int syncId, int striderId) implements FabricPacket {
    public static final PacketType<OpenStriderScreenS2CPacket> TYPE = PacketType.create(Kaleidoscope.of("open_strider_screen"), OpenStriderScreenS2CPacket::new);

    public OpenStriderScreenS2CPacket(PacketByteBuf buf) {
        this(buf.readInt(), buf.readInt());
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(this.syncId);
        buf.writeInt(this.striderId);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
