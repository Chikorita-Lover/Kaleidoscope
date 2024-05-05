package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinBruteEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEntityMixin {
    @ModifyReturnValue(method = "createPiglinBruteAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder modifyPiglinBruteAttributes(DefaultAttributeContainer.Builder builder) {
        return builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);
    }
}
