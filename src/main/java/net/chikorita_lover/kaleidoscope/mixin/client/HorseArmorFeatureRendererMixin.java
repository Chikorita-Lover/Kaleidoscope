package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.HorseArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.texture.MissingSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.item.trim.ArmorTrimPatterns;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseArmorFeatureRenderer.class)
public class HorseArmorFeatureRendererMixin {
    @Unique
    private static final Identifier DEFAULT_PATTERN_ASSET = ArmorTrimPatterns.HOST.getValue();
    @Shadow
    @Final
    private HorseEntityModel<HorseEntity> model;

    @Unique
    private static Identifier getTrimTexture(ArmorItem armor, Identifier pattern, ArmorTrimMaterial material) {
        String namespace = pattern.getNamespace();
        if (namespace.equals(Identifier.DEFAULT_NAMESPACE)) {
            namespace = Kaleidoscope.MODID;
        }
        String asset = material.overrideArmorMaterials().getOrDefault(armor.getMaterial(), material.assetName());
        return Identifier.of(namespace, "trims/entity/horse/armor/" + pattern.getPath() + "_" + asset);
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/HorseEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/HorseEntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V", shift = At.Shift.AFTER))
    public void renderTrim(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, HorseEntity horseEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo callbackInfo, @Local ItemStack itemStack) {
        if (itemStack.contains(DataComponentTypes.TRIM) && itemStack.getItem() instanceof ArmorItem armor) {
            ArmorTrim trim = itemStack.get(DataComponentTypes.TRIM);
            Identifier texture = getTrimTexture(armor, trim.getPattern().value().assetId(), trim.getMaterial().value());
            SpriteAtlasTexture atlas = MinecraftClient.getInstance().getBakedModelManager().getAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE);
            Sprite sprite = atlas.getSprite(texture);
            if (sprite.getContents().getId() == MissingSprite.getMissingSpriteId()) {
                sprite = atlas.getSprite(getTrimTexture(armor, DEFAULT_PATTERN_ASSET, trim.getMaterial().value()));
            }
            VertexConsumer vertexConsumer = sprite.getTextureSpecificVertexConsumer(vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE)));
            this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        }
    }
}
