package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OcelotEntity.class)
public abstract class OcelotEntityMixin extends AnimalEntity {
    protected OcelotEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void playEatSound() {
        this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
    }
}
