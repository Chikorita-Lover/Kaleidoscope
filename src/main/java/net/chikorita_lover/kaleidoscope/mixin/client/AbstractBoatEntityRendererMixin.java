package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.client.render.BannerEquippableRenderState;
import net.chikorita_lover.kaleidoscope.entity.BannerEquippable;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.AbstractBoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.BoatEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBoatEntityRenderer.class)
public class AbstractBoatEntityRendererMixin {
    @Unique
    private ItemModelManager itemModelManager;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void setItemModelManager(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.itemModelManager = context.getItemModelManager();
    }

    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/BoatEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V", shift = At.Shift.AFTER))
    private void renderBanner(BoatEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        ItemRenderState itemState = ((BannerEquippableRenderState) state).kaleidoscope$getState();
        if (itemState.isEmpty()) {
            return;
        }
        matrices.push();
        matrices.scale(2.0F, 2.0F, 2.0F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F));
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90.0F));
        if (state.entityType == EntityType.BAMBOO_RAFT || state.entityType == EntityType.BAMBOO_CHEST_RAFT) {
            matrices.translate(0.0, 0.28, 0.47);
        } else {
            matrices.translate(0.0, 0.34, 0.47);
        }
        itemState.render(matrices, queue, state.light, OverlayTexture.DEFAULT_UV, state.outlineColor);
        matrices.pop();
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/vehicle/AbstractBoatEntity;Lnet/minecraft/client/render/entity/state/BoatEntityRenderState;F)V", at = @At("TAIL"))
    private void updateBannerRenderState(AbstractBoatEntity entity, BoatEntityRenderState state, float tickProgress, CallbackInfo ci) {
        ItemStack stack = ((BannerEquippable) entity).kaleidoscope$getBannerStack();
        this.itemModelManager.updateForNonLivingEntity(((BannerEquippableRenderState) state).kaleidoscope$getState(), stack, ItemDisplayContext.FIXED, entity);
    }
}
