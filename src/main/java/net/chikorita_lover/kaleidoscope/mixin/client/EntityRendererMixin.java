package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @ModifyReturnValue(method = "getBoundingBox", at = @At("RETURN"))
    private <T extends Entity> Box modifyBannerEquippableBoundingBox(Box box, T entity) {
        return entity instanceof BannerEquippable ? box.withMaxY(box.maxY + 1.875) : box;
    }
}
