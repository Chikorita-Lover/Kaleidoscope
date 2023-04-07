package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockTags {
    public static final TagKey<Block> COPPER_DOORS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Kaleidoscope.MODID, "copper_doors"));
}
