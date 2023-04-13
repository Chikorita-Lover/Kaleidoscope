package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {
    public static final TagKey<Block> COPPER_DOORS = ModBlockTags.of("copper_doors");
    public static final TagKey<Block> FIREFLIES_SPAWNABLE_ON = ModBlockTags.of("fireflies_spawnable_on");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(Kaleidoscope.MODID, id));
    }
}
