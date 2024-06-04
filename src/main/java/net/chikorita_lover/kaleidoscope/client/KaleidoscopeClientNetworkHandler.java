package net.chikorita_lover.kaleidoscope.client;

import net.chikorita_lover.kaleidoscope.client.gui.screen.StriderScreen;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.screen.StriderScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.inventory.SimpleInventory;

@Environment(EnvType.CLIENT)
public class KaleidoscopeClientNetworkHandler {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(OpenStriderScreenS2CPacket.PACKET_ID, (payload, context) -> {
            MinecraftClient client = context.client();
            if (client.world == null || client.player == null) {
                return;
            }
            Entity entity = client.world.getEntityById(payload.striderId());
            if (entity instanceof StriderEntity striderEntity) {
                ClientPlayerEntity clientPlayerEntity = client.player;
                SimpleInventory simpleInventory = new SimpleInventory(((Chestable) striderEntity).kaleidoscope$getInventorySize());
                StriderScreenHandler handler = new StriderScreenHandler(payload.syncId(), clientPlayerEntity.getInventory(), simpleInventory, striderEntity);
                clientPlayerEntity.currentScreenHandler = handler;
                client.setScreen(new StriderScreen(handler, clientPlayerEntity.getInventory(), striderEntity));
            }
        });
    }
}
