package net.chikorita_lover.kaleidoscope.client.sound;

import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class JukeboxMinecartSoundInstance extends MovingSoundInstance {
    public final int entityId;
    private final World world;

    public JukeboxMinecartSoundInstance(JukeboxMinecartEntity jukeboxMinecart) {
        super(getSoundEventFromItemStack(jukeboxMinecart.getStack(0)), SoundCategory.RECORDS, SoundInstance.createRandom());
        this.world = jukeboxMinecart.getWorld();
        this.entityId = jukeboxMinecart.getId();
        this.repeat = false;
        this.volume = 4.0F;
        this.setPosition(jukeboxMinecart.getPos());
    }

    private static SoundEvent getSoundEventFromItemStack(ItemStack stack) {
        if (stack.getItem() instanceof MusicDiscItem musicDisc) {
            return musicDisc.getSound();
        }
        return SoundEvents.INTENTIONALLY_EMPTY;
    }

    @Override
    public boolean shouldAlwaysPlay() {
        return true;
    }

    @Override
    public void tick() {
        JukeboxMinecartEntity jukeboxMinecart = this.getEntity();
        if (this.isDone() || jukeboxMinecart == null) {
            return;
        }
        if (!jukeboxMinecart.isPlayingRecord() || jukeboxMinecart.isSilent()) {
            this.setDone();
            return;
        }
        this.setPosition(jukeboxMinecart.getPos());
    }

    public JukeboxMinecartEntity getEntity() {
        Entity entity = this.world.getEntityById(this.entityId);
        if (entity instanceof JukeboxMinecartEntity jukeboxMinecart) {
            return jukeboxMinecart;
        }
        return null;
    }

    private void setPosition(Vec3d vec3d) {
        this.x = vec3d.getX();
        this.y = vec3d.getY();
        this.z = vec3d.getZ();
    }
}
