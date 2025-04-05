package net.chikorita_lover.kaleidoscope.mixin.entity;

import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractHorseEntity.class)
public class AbstractHorseEntityMixin {
    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AbstractHorseEntity;updateAnger()V"))
    private void onReactDamage(AbstractHorseEntity horse) {
        if (!horse.getBodyArmor().isIn(KaleidoscopeItemTags.PREVENTS_HORSE_ANGER)) {
            horse.updateAnger();
        }
    }
}
