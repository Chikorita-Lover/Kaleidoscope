package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.client.render.ChestableRenderState;
import net.chikorita_lover.kaleidoscope.client.render.StriderChestFeatureRenderer;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.StriderEntityRenderer;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.render.entity.state.StriderEntityRenderState;
import net.minecraft.entity.passive.StriderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StriderEntityRenderer.class)
public abstract class StriderEntityRendererMixin extends AgeableMobEntityRenderer<StriderEntity, StriderEntityRenderState, StriderEntityModel> {
    public StriderEntityRendererMixin(EntityRendererFactory.Context context, StriderEntityModel model, StriderEntityModel babyModel, float shadowRadius) {
        super(context, model, babyModel, shadowRadius);
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/StriderEntity;Lnet/minecraft/client/render/entity/state/StriderEntityRenderState;F)V", at = @At("TAIL"))
    private void updateChestRenderState(StriderEntity entity, StriderEntityRenderState state, float tickProgress, CallbackInfo ci) {
        ((ChestableRenderState) state).kaleidoscope$setHasChest(((Chestable) entity).kaleidoscope$hasChest());
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addChestFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new StriderChestFeatureRenderer(this, context.getEntityModels()));
    }
}
