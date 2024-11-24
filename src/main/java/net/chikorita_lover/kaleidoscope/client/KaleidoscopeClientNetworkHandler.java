package net.chikorita_lover.kaleidoscope.client;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.client.gui.screen.StriderScreen;
import net.chikorita_lover.kaleidoscope.client.sound.JukeboxMinecartSoundInstance;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.chikorita_lover.kaleidoscope.network.OpenStriderScreenS2CPacket;
import net.chikorita_lover.kaleidoscope.network.StopJukeboxMinecartPlayingS2CPacket;
import net.chikorita_lover.kaleidoscope.network.UpdateJukeboxMinecartS2CPacket;
import net.chikorita_lover.kaleidoscope.screen.StriderScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.Source;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.MusicDiscItem;

@Environment(EnvType.CLIENT)
public class KaleidoscopeClientNetworkHandler {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(OpenStriderScreenS2CPacket.TYPE.getId(), (client, handler, buf, responseSender) -> {
            OpenStriderScreenS2CPacket packet = new OpenStriderScreenS2CPacket(buf);
            client.execute(() -> {
                if (client.world == null || client.player == null) {
                    return;
                }
                Entity entity = client.world.getEntityById(packet.striderId());
                if (entity instanceof StriderEntity striderEntity) {
                    ClientPlayerEntity clientPlayerEntity = client.player;
                    SimpleInventory simpleInventory = new SimpleInventory(((Chestable) striderEntity).kaleidoscope$getInventorySize());
                    StriderScreenHandler screenHandler = new StriderScreenHandler(packet.syncId(), clientPlayerEntity.getInventory(), simpleInventory, striderEntity);
                    clientPlayerEntity.currentScreenHandler = screenHandler;
                    client.setScreen(new StriderScreen(screenHandler, clientPlayerEntity.getInventory(), striderEntity));
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(StopJukeboxMinecartPlayingS2CPacket.TYPE.getId(), (client, handler, buf, responseSender) -> {
            StopJukeboxMinecartPlayingS2CPacket packet = new StopJukeboxMinecartPlayingS2CPacket(buf);
            client.execute(() -> {
                if (client.world == null) {
                    Kaleidoscope.LOGGER.error("Could not retrieve client world for jukebox minecart stop event, returned null");
                    return;
                }
                // Stop all sound instances that match the received entity ID.
                client.getSoundManager().soundSystem.sources.forEach((soundInstance, sourceManager) -> {
                    if (soundInstance instanceof JukeboxMinecartSoundInstance minecartSoundInstance) {
                        if (minecartSoundInstance.entityId == packet.entityId()) {
                            sourceManager.run(Source::stop);
                        }
                    }
                });
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(UpdateJukeboxMinecartS2CPacket.TYPE.getId(), (client, handler, buf, responseSender) -> {
            UpdateJukeboxMinecartS2CPacket packet = new UpdateJukeboxMinecartS2CPacket(buf);
            client.execute(() -> {
                if (client.world == null) {
                    Kaleidoscope.LOGGER.error("Could not retrieve client world for jukebox minecart update, returned null");
                    return;
                }
                // Instantiate a new sound instance that moves with the jukebox minecart.
                if (client.world.getEntityById(packet.entityId()) instanceof JukeboxMinecartEntity jukeboxMinecart) {
                    jukeboxMinecart.setStack(0, packet.itemStack());
                    JukeboxMinecartSoundInstance soundInstance = new JukeboxMinecartSoundInstance(jukeboxMinecart);
                    client.getSoundManager().play(soundInstance);
                    if (packet.itemStack().getItem() instanceof MusicDiscItem musicDisc) {
                        client.inGameHud.setRecordPlayingOverlay(musicDisc.getDescription());
                    }
                }
            });
        });
    }
}
