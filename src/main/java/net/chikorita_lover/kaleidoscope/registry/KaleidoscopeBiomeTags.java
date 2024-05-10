package net.chikorita_lover.kaleidoscope.registry;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class KaleidoscopeBiomeTags {
    public static final TagKey<Biome> SPAWNS_FIREFLIES = KaleidoscopeBiomeTags.of("spawns_fireflies");

    private static TagKey<Biome> of(String id) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(Kaleidoscope.MODID, id));
    }
}