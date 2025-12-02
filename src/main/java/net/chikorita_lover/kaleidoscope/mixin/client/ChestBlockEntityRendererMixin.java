package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.DyedChestBlock;
import net.chikorita_lover.kaleidoscope.client.render.DyeableRenderState;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(ChestBlockEntityRenderer.class)
public class ChestBlockEntityRendererMixin {
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS = new HashMap<>();
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS_LEFT = new HashMap<>();
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS_RIGHT = new HashMap<>();

    @Unique
    private static SpriteIdentifier map(DyeColor color) {
        return TexturedRenderLayers.CHEST_SPRITE_MAPPER.map(Kaleidoscope.of(color.getId()));
    }

    @Unique
    private static SpriteIdentifier mapLeft(DyeColor color) {
        return TexturedRenderLayers.CHEST_SPRITE_MAPPER.map(Kaleidoscope.of(color.getId() + "_left"));
    }

    @Unique
    private static SpriteIdentifier mapRight(DyeColor color) {
        return TexturedRenderLayers.CHEST_SPRITE_MAPPER.map(Kaleidoscope.of(color.getId() + "_right"));
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/client/render/block/entity/state/ChestBlockEntityRenderState;FLnet/minecraft/util/math/Vec3d;Lnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V", at = @At("TAIL"))
    private <T extends BlockEntity> void updateDyedRenderState(T chest, ChestBlockEntityRenderState renderState, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay, CallbackInfo ci) {
        BlockState blockState = chest.getCachedState();
        if (chest.getCachedState().isIn(KaleidoscopeBlockTags.DYED_CHESTS) && blockState.getBlock() instanceof DyedChestBlock dyedChest) {
            ((DyeableRenderState) renderState).kaleidoscope$setColor(dyedChest.getColor());
        }
    }

    @ModifyExpressionValue(method = "render(Lnet/minecraft/client/render/block/entity/state/ChestBlockEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TexturedRenderLayers;getChestTextureId(Lnet/minecraft/client/render/block/entity/state/ChestBlockEntityRenderState$Variant;Lnet/minecraft/block/enums/ChestType;)Lnet/minecraft/client/util/SpriteIdentifier;"))
    private SpriteIdentifier modifyChestTextureId(SpriteIdentifier texture, @Local(argsOnly = true) ChestBlockEntityRenderState state) {
        DyeColor color = ((DyeableRenderState) state).kaleidoscope$getColor();
        if (color != null) {
            texture = switch (state.chestType) {
                case LEFT -> CHESTS_LEFT.computeIfAbsent(color, ChestBlockEntityRendererMixin::mapLeft);
                case RIGHT -> CHESTS_RIGHT.computeIfAbsent(color, ChestBlockEntityRendererMixin::mapRight);
                default -> CHESTS.computeIfAbsent(color, ChestBlockEntityRendererMixin::map);
            };
        }
        return texture;
    }
}
