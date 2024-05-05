package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {
    @ModifyReturnValue(method = "createPiglinAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder modifyPiglinAttributes(DefaultAttributeContainer.Builder builder) {
        return builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }
}
