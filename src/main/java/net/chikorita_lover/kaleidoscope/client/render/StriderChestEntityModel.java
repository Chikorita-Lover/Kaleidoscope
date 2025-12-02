package net.chikorita_lover.kaleidoscope.client.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.StriderEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class StriderChestEntityModel extends EntityModel<StriderEntityRenderState> {
    public StriderChestEntityModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        modelData.getRoot().addChild("left_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(-8.0F, 30.0F, 0.0F, 0.0F, MathHelper.HALF_PI, 0.0F));
        modelData.getRoot().addChild("right_chest", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -35.0F, -3.0F, 8.0F, 8.0F, 3.0F), ModelTransform.of(8.0F, 30.0F, 0.0F, 0.0F, -MathHelper.HALF_PI, 0.0F));
        return TexturedModelData.of(modelData, 32, 16);
    }
}
