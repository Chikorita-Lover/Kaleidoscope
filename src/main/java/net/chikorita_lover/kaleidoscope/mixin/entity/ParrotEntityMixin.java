package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.entity.HealableAnimal;
import net.chikorita_lover.kaleidoscope.entity.JukeboxMinecartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin extends TameableShoulderEntity implements HealableAnimal {
    @Unique
    private static final Predicate<Entity> PLAYING_JUKEBOX_MINECART_PREDICATE = entity -> entity instanceof JukeboxMinecartEntity jukeboxMinecart && jukeboxMinecart.isPlayingRecord() && !jukeboxMinecart.isSilent();

    protected ParrotEntityMixin(EntityType<? extends TameableShoulderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void playEatSound() {
        this.playSound(SoundEvents.ENTITY_PARROT_EAT, 1.0F, 1.0F);
    }

    @ModifyReturnValue(method = "isSongPlaying", at = @At("RETURN"))
    public boolean isSongPlaying(boolean songPlaying) {
        return songPlaying || this.isNearPlayingJukeboxMinecart();
    }

    @Unique
    private boolean isNearPlayingJukeboxMinecart() {
        return !this.getWorld().getOtherEntities(this, new Box(this.getBlockPos()).expand(3.46), PLAYING_JUKEBOX_MINECART_PREDICATE).isEmpty();
    }

    @Override
    public boolean kaleidoscope$isFeedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.PARROT_FOOD);
    }
}
