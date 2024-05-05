package net.chikorita_lover.kaleidoscope.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityMixin {
    @ModifyReturnValue(method = "createZombifiedPiglinAttributes", at = @At("RETURN"))
    private static DefaultAttributeContainer.Builder modifyZombifiedPiglinAttributes(DefaultAttributeContainer.Builder builder) {
        return builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }
}
