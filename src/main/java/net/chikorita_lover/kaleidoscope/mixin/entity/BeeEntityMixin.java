package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BeeEntity.class)
public abstract class BeeEntityMixin extends AnimalEntity {
    protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void playEatSound() {
        this.playSound(SoundEvents.ENTITY_BEE_POLLINATE, 1.0F, 1.0F);
    }
}
