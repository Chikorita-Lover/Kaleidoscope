package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimPatterns;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaddleFeatureRenderer.class)
public class HorseArmorFeatureRendererMixin { // TODO is class needed?
    @Unique
    private static final Identifier DEFAULT_PATTERN_ASSET = ArmorTrimPatterns.HOST.getValue();

    @Unique
    private static Identifier getTrimTexture(Item armor, Identifier pattern, ArmorTrimMaterial material) {
        String namespace = pattern.getNamespace();
        if (namespace.equals(Identifier.DEFAULT_NAMESPACE)) {
            namespace = Kaleidoscope.MODID;
        }
        String asset = "foo"; // material.assets().overrides().getOrDefault(armor.getMaterial(), material.assetName());
        return Identifier.of(namespace, "trims/entity/horse/armor/" + pattern.getPath() + "_" + asset);
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/LivingEntityRenderState;FF)V", at = @At("TAIL"))
    public <S extends LivingEntityRenderState> void renderTrim(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, S state, float f, float g, CallbackInfo ci, @Local ItemStack itemStack) {
        /* if (itemStack.contains(DataComponentTypes.TRIM)) {
            ArmorTrim trim = itemStack.get(DataComponentTypes.TRIM);
            Identifier texture = getTrimTexture(armor, trim.getPattern().value().assetId(), trim.getMaterial().value());
            SpriteAtlasTexture atlas = MinecraftClient.getInstance().getBakedModelManager().getAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE);
            Sprite sprite = atlas.getSprite(texture);
            if (sprite.getContents().getId() == MissingSprite.getMissingSpriteId()) {
                sprite = atlas.getSprite(getTrimTexture(armor, DEFAULT_PATTERN_ASSET, trim.getMaterial().value()));
            }
            VertexConsumer vertexConsumer = sprite.getTextureSpecificVertexConsumer(vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE)));
            this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        } */
    }
}
