package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(RabbitEntity.class)
public abstract class RabbitEntityMixin extends AnimalEntity {
    protected RabbitEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void playEatSound() {
        this.playSound(KaleidoscopeSoundEvents.ENTITY_RABBIT_EAT, 1.0F, 1.0F);
    }
}
