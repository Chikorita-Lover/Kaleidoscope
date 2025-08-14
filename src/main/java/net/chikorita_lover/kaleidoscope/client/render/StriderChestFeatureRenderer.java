package net.chikorita_lover.kaleidoscope.client.render;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.render.entity.state.StriderEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class StriderChestFeatureRenderer extends FeatureRenderer<StriderEntityRenderState, StriderEntityModel> {
    private static final Identifier TEXTURE = Kaleidoscope.of("textures/entity/strider/strider_chest.png");
    private final ModelPart chest;

    public StriderChestFeatureRenderer(FeatureRendererContext<StriderEntityRenderState, StriderEntityModel> context, LoadedEntityModels models) {
        super(context);
        this.chest = models.getModelPart(KaleidoscopeEntityModelLayers.STRIDER_CHEST);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        modelData.getRoot().addChild("left_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(-8.0F, 30.0F, 0.0F, 0.0F, MathHelper.HALF_PI, 0.0F));
        modelData.getRoot().addChild("right_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(8.0F, 30.0F, 0.0F, 0.0F, -MathHelper.HALF_PI, 0.0F));
        return TexturedModelData.of(modelData, 32, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, StriderEntityRenderState state, float limbAngle, float limbDistance) {
        if (!((ChestableRenderState) state).kaleidoscope$hasChest()) {
            return;
        }
        VertexConsumer vertices = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(TEXTURE));
        int overlay = LivingEntityRenderer.getOverlay(state, 0.0F);
        matrices.push();
        this.getContextModel().getRootPart().getChild(EntityModelPartNames.BODY).applyTransform(matrices);
        this.chest.render(matrices, vertices, light, overlay);
        matrices.pop();
    }
}
