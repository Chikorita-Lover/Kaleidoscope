package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class KaleidoscopeBlockTags {
    public static final TagKey<Block> COPPER_DOORS = KaleidoscopeBlockTags.of("copper_doors");
    public static final TagKey<Block> FIREFLIES_SPAWNABLE_ON = KaleidoscopeBlockTags.of("fireflies_spawnable_on");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Kaleidoscope.MODID, id));
    }
}
