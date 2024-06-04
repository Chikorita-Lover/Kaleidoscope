package net.chikorita_lover.kaleidoscope.client.render;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeClient;
import net.chikorita_lover.kaleidoscope.entity.Chestable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class StriderChestFeatureRenderer<T extends StriderEntity, M extends StriderEntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/entity/strider/strider_chest.png");
    private final ModelPart chest;

    public StriderChestFeatureRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.chest = loader.getModelPart(KaleidoscopeClient.STRIDER_CHEST);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        modelData.getRoot().addChild("left_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(-8.0F, 30.0F, 0.0F, 0.0F, MathHelper.HALF_PI, 0.0F));
        modelData.getRoot().addChild("right_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(8.0F, 30.0F, 0.0F, 0.0F, -MathHelper.HALF_PI, 0.0F));
        return TexturedModelData.of(modelData, 32, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, StriderEntity striderEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!((Chestable) striderEntity).kaleidoscope$hasChest()) {
            return;
        }
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(TEXTURE));
        int overlay = LivingEntityRenderer.getOverlay(striderEntity, 0.0F);
        matrices.push();
        this.getContextModel().getPart().getChild(EntityModelPartNames.BODY).rotate(matrices);
        this.chest.render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }
}
