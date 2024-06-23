package net.chikorita_lover.kaleidoscope.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;

public class CrackedBlockRegistry {
    private static final HashMap<Block, Block> REGISTRY = new HashMap<>();

    public static void register() {
        register(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        register(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        register(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
        register(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        register(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        register(Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS);
        register(Blocks.TUFF_BRICKS, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS);
        register(Blocks.MUD_BRICKS, KaleidoscopeBlocks.CRACKED_MUD_BRICKS);
        register(Blocks.RED_NETHER_BRICKS, KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS);
        register(Blocks.END_STONE_BRICKS, KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS);
    }

    public static Block get(Block block) {
        return REGISTRY.get(block);
    }

    public static boolean contains(Block block) {
        return REGISTRY.containsKey(block);
    }

    public static void register(Block block, Block crackedBlock) {
        REGISTRY.put(block, crackedBlock);
    }
}
