package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractHorseEntity.class)
public abstract class AbstractHorseEntityMixin extends AnimalEntity {
    protected AbstractHorseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract @Nullable SoundEvent getEatSound();

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AbstractHorseEntity;updateAnger()V"))
    private void onReactDamage(AbstractHorseEntity horse) {
        if (!horse.getBodyArmor().isIn(KaleidoscopeItemTags.PREVENTS_HORSE_ANGER)) {
            horse.updateAnger();
        }
    }

    @Override
    protected void playEatSound() {
        this.playSound(this.getEatSound(), 1.0F, 1.0F);
    }
}
