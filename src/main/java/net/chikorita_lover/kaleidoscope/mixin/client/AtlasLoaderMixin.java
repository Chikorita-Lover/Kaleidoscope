package net.chikorita_lover.kaleidoscope.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.fabricmc.fabric.impl.content.registry.util.ImmutableCollectionUtils;
import net.minecraft.client.texture.atlas.AtlasLoader;
import net.minecraft.client.texture.atlas.AtlasSource;
import net.minecraft.client.texture.atlas.PalettedPermutationsAtlasSource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AtlasLoader.class)
public class AtlasLoaderMixin {
    @Unique
    private static final Identifier ARMOR_TRIMS_ATLAS = Identifier.ofVanilla("armor_trims");
    @Unique
    private static final Identifier BLOCKS_ATLAS = Identifier.ofVanilla("blocks");
    @Unique
    private static final Identifier TRIM_PALETTE_TEXTURE = Identifier.ofVanilla("trims/color_palettes/trim_palette");
    @Unique
    private static final Identifier HORSE_ARMOR_TRIM_TEXTURE = Kaleidoscope.of("trims/items/horse_armor_trim");
    @Unique
    private static final String ARMOR_TRIMS_SOURCE = "trims/entity/horse/armor";

    @Inject(method = "of", at = @At("RETURN"))
    @SuppressWarnings("UnreachableCode")
    private static void appendPalettedPermutations(ResourceManager resourceManager, Identifier id, CallbackInfoReturnable<AtlasLoader> cir, @Local List<AtlasSource> list) {
        for (AtlasSource source : list) {
            if (source instanceof PalettedPermutationsAtlasSource paletteSource) {
                PalettedPermutationsAtlasSourceAccessor accessor = (PalettedPermutationsAtlasSourceAccessor) ((Object) paletteSource);
                if (paletteSource.paletteKey().equals(TRIM_PALETTE_TEXTURE)) {
                    if (id.equals(BLOCKS_ATLAS)) {
                        List<Identifier> textures = ImmutableCollectionUtils.getAsMutableList(paletteSource::textures, accessor::setTextures);
                        textures.add(HORSE_ARMOR_TRIM_TEXTURE);
                    }
                    if (id.equals(ARMOR_TRIMS_ATLAS)) {
                        final List<Identifier> textures = ImmutableCollectionUtils.getAsMutableList(paletteSource::textures, accessor::setTextures);
                        final ResourceFinder rf = new ResourceFinder("textures/" + ARMOR_TRIMS_SOURCE, ".png");
                        rf.findResources(resourceManager).keySet().forEach(texture -> textures.add(rf.toResourceId(texture).withPrefixedPath(ARMOR_TRIMS_SOURCE + '/')));
                    }
                }
            }
        }
    }
}
