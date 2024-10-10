package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntityRenderer.class)
public class BoatEntityRendererMixin {
    @Inject(method = "render(Lnet/minecraft/entity/vehicle/BoatEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;isSubmergedInWater()Z", shift = At.Shift.BEFORE))
    private void render(BoatEntity boatEntity, float f, float g, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        ItemStack stack = ((BannerEquippable) boatEntity).kaleidoscope$getBannerStack();
        if (stack.isEmpty() || !(stack.getItem() instanceof BannerItem)) {
            return;
        }
        matrices.push();
        matrices.scale(2.0F, 2.0F, 2.0F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90.0F));
        if (boatEntity.getVariant() == BoatEntity.Type.BAMBOO) {
            matrices.translate(0.0, 0.28, 0.47);
        } else {
            matrices.translate(0.0, 0.34, 0.47);
        }
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, boatEntity.getWorld(), boatEntity.getId());
        matrices.pop();
    }
}
