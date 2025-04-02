package net.chikorita_lover.kaleidoscope.mixin.client;

import net.chikorita_lover.kaleidoscope.KaleidoscopeClient;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.client.texture.atlas.PalettedPermutationsAtlasSource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(PalettedPermutationsAtlasSource.class)
public class PalettedPermutationsAtlasSourceMixin {
    @Unique
    private static final Identifier TRIM_PALETTE_TEXTURE = Identifier.ofVanilla("trims/color_palettes/trim_palette");
    @Shadow
    @Final
    private Identifier paletteKey;
    @Shadow
    @Final
    private Map<String, Identifier> permutations;

    @Inject(method = "load", at = @At("TAIL"))
    private void collectTrimPalettes(ResourceManager resourceManager, AtlasSource.SpriteRegions regions, CallbackInfo ci) {
        if (this.paletteKey.equals(TRIM_PALETTE_TEXTURE)) {
            KaleidoscopeClient.TRIM_PALETTES.addAll(this.permutations.keySet());
        }
    }
}
