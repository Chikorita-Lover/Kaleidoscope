package net.chikorita_lover.kaleidoscope.mixin.client;

import net.minecraft.client.texture.atlas.PalettedPermutationsAtlasSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(PalettedPermutationsAtlasSource.class)
public interface PalettedPermutationsAtlasSourceAccessor {
    @Accessor
    List<Identifier> getTextures();

    @Accessor
    @Mutable
    void setTextures(List<Identifier> textures);

    @Accessor
    Map<String, Identifier> getPermutations();

    @Accessor
    @Mutable
    void setPermutations(Map<String, Identifier> permutations);

    @Accessor
    Identifier getPaletteKey();
}
