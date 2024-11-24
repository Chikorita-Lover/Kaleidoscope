package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
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

@Mixin(com.teamabode.scribe.client.renderer.ScribeBoatRenderer.class)
public class ScribeBoatRendererMixin {
    @Inject(method = "render(Lnet/minecraft/entity/vehicle/BoatEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;isSubmergedInWater()Z", shift = At.Shift.BEFORE))
    private void render(BoatEntity boat, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight, CallbackInfo ci) {
        ItemStack stack = ((BannerEquippable) boat).kaleidoscope$getBannerStack();
        if (stack.isEmpty() || !(stack.getItem() instanceof BannerItem)) {
            return;
        }
        matrixStack.push();
        matrixStack.scale(2.0F, 2.0F, 2.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrixStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90.0F));
        matrixStack.translate(0.0, 0.34, 0.47);
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, packedLight, OverlayTexture.DEFAULT_UV, matrixStack, buffer, boat.getWorld(), boat.getId());
        matrixStack.pop();
    }
}
