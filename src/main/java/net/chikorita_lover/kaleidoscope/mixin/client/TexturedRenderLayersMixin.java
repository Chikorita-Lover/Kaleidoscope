package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.DyedChestBlock;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS = new HashMap<>();
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS_LEFT = new HashMap<>();
    @Unique
    private static final Map<DyeColor, SpriteIdentifier> CHESTS_RIGHT = new HashMap<>();

    @Inject(method = "getChestTextureId(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/block/enums/ChestType;Z)Lnet/minecraft/client/util/SpriteIdentifier;", at = @At("HEAD"), cancellable = true)
    private static void foo(BlockEntity blockEntity, ChestType type, boolean christmas, CallbackInfoReturnable<SpriteIdentifier> cir) {
        BlockState state = blockEntity.getCachedState();
        if (!state.isIn(KaleidoscopeBlockTags.DYED_CHESTS) || !(state.getBlock() instanceof DyedChestBlock chest)) {
            return;
        }
        DyeColor color = chest.getColor();
        if (type == ChestType.LEFT) {
            cir.setReturnValue(CHESTS_LEFT.computeIfAbsent(color, TexturedRenderLayersMixin::mapLeft));
        } else if (type == ChestType.RIGHT) {
            cir.setReturnValue(CHESTS_RIGHT.computeIfAbsent(color, TexturedRenderLayersMixin::mapRight));
        } else {
            cir.setReturnValue(CHESTS.computeIfAbsent(color, TexturedRenderLayersMixin::map));
        }
    }

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
}
