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
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.Source;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.JukeboxPlayableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Optional;

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
        ClientPlayNetworking.registerGlobalReceiver(StopJukeboxMinecartPlayingS2CPacket.PACKET_ID, (payload, context) -> {
            MinecraftClient client = context.client();
            if (client.world == null) {
                Kaleidoscope.LOGGER.error("Could not retrieve client world for jukebox minecart stop event, returned null");
                return;
            }
            // Stop all sound instances that match the received entity ID.
            client.getSoundManager().soundSystem.sources.forEach((soundInstance, sourceManager) -> {
                if (soundInstance instanceof JukeboxMinecartSoundInstance minecartSoundInstance) {
                    if (minecartSoundInstance.entityId == payload.entityId()) {
                        sourceManager.run(Source::stop);
                    }
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(UpdateJukeboxMinecartS2CPacket.PACKET_ID, (payload, context) -> {
            MinecraftClient client = context.client();
            if (client.world == null) {
                Kaleidoscope.LOGGER.error("Could not retrieve client world for jukebox minecart update, returned null");
                return;
            }
            // Instantiate a new sound instance that moves with the jukebox minecart.
            if (client.world.getEntityById(payload.entityId()) instanceof JukeboxMinecartEntity jukeboxMinecart) {
                jukeboxMinecart.setStack(0, payload.itemStack());
                JukeboxMinecartSoundInstance soundInstance = new JukeboxMinecartSoundInstance(jukeboxMinecart);
                client.getSoundManager().play(soundInstance);
                Optional<RegistryEntry<JukeboxSong>> optional = JukeboxSong.getSongEntryFromStack(client.world.getRegistryManager(), payload.itemStack());
                optional.ifPresent(jukeboxSongRegistryEntry -> client.inGameHud.setRecordPlayingOverlay(jukeboxSongRegistryEntry.value().description()));
            }
        });
    }
}
