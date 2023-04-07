package com.chikoritalover.kaleidoscope.registry;

import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MossyBlocksRegistry {
    public static Map<Block, Block> MOSSY_TO_CLEAN_BLOCKS = new HashMap<>();

    public static void registerMossyBlockPair(Block mossyBlock, Block cleanBlock) {
        MOSSY_TO_CLEAN_BLOCKS.put(mossyBlock, cleanBlock);
    }
}
