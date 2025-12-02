package net.chikorita_lover.kaleidoscope.client.render;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.render.entity.state.StriderEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class StriderChestFeatureRenderer extends FeatureRenderer<StriderEntityRenderState, StriderEntityModel> {
    private static final Identifier TEXTURE = Kaleidoscope.of("textures/entity/strider/strider_chest.png");
    private final StriderChestEntityModel model;

    public StriderChestFeatureRenderer(FeatureRendererContext<StriderEntityRenderState, StriderEntityModel> context, LoadedEntityModels entityModels) {
        super(context);
        this.model = new StriderChestEntityModel(entityModels.getModelPart(KaleidoscopeEntityModelLayers.STRIDER_CHEST));
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, StriderEntityRenderState state, float limbAngle, float limbDistance) {
        if (!((ChestableRenderState) state).kaleidoscope$hasChest()) {
            return;
        }
        int overlay = LivingEntityRenderer.getOverlay(state, 0.0F);
        matrices.push();
        this.getContextModel().getRootPart().getChild(EntityModelPartNames.BODY).applyTransform(matrices);
        queue.submitModel(this.model, state, matrices, this.model.getLayer(TEXTURE), light, overlay, state.outlineColor, null);
        matrices.pop();
    }
}
