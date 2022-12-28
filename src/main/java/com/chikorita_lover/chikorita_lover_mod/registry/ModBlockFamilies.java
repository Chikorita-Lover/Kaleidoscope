package com.chikorita_lover.chikorita_lover_mod.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();
    public static final BlockFamily DIRT_BRICKS;
    public static final BlockFamily POLISHED_CALCITE;
    public static final BlockFamily CUT_COPPER;
    public static final BlockFamily EXPOSED_CUT_COPPER;
    public static final BlockFamily WEATHERED_CUT_COPPER;
    public static final BlockFamily OXIDIZED_CUT_COPPER;
    public static final BlockFamily WAXED_CUT_COPPER;
    public static final BlockFamily WAXED_EXPOSED_CUT_COPPER;
    public static final BlockFamily WAXED_WEATHERED_CUT_COPPER;
    public static final BlockFamily WAXED_OXIDIZED_CUT_COPPER;
    public static final BlockFamily POLISHED_PRISMARINE;
    public static final BlockFamily RED_NETHER_BRICKS;
    public static final BlockFamily POLISHED_END_STONE;

    public ModBlockFamilies() {
    }

    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getId(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }

    static {
        DIRT_BRICKS = register(ModBlocks.DIRT_BRICKS).slab(ModBlocks.DIRT_BRICK_SLAB).stairs(ModBlocks.DIRT_BRICK_STAIRS).wall(ModBlocks.DIRT_BRICK_WALL).build();
        POLISHED_CALCITE = register(ModBlocks.POLISHED_CALCITE).slab(ModBlocks.POLISHED_CALCITE_SLAB).stairs(ModBlocks.POLISHED_CALCITE_STAIRS).wall(ModBlocks.POLISHED_CALCITE_WALL).build();

        CUT_COPPER = register(Blocks.CUT_COPPER).wall(ModBlocks.CUT_COPPER_WALL).noGenerateModels().build();
        EXPOSED_CUT_COPPER = register(Blocks.EXPOSED_CUT_COPPER).wall(ModBlocks.EXPOSED_CUT_COPPER_WALL).noGenerateModels().build();
        WEATHERED_CUT_COPPER = register(Blocks.WEATHERED_CUT_COPPER).wall(ModBlocks.WEATHERED_CUT_COPPER_WALL).noGenerateModels().build();
        OXIDIZED_CUT_COPPER = register(Blocks.OXIDIZED_CUT_COPPER).wall(ModBlocks.OXIDIZED_CUT_COPPER_WALL).noGenerateModels().build();

        WAXED_CUT_COPPER = register(Blocks.WAXED_CUT_COPPER).wall(ModBlocks.WAXED_CUT_COPPER_WALL).group("waxed_cut_copper").noGenerateModels().build();
        WAXED_EXPOSED_CUT_COPPER = register(Blocks.WAXED_EXPOSED_CUT_COPPER).wall(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL).group("waxed_exposed_cut_copper").noGenerateModels().build();
        WAXED_WEATHERED_CUT_COPPER = register(Blocks.WAXED_WEATHERED_CUT_COPPER).wall(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL).group("waxed_weathered_cut_copper").noGenerateModels().build();
        WAXED_OXIDIZED_CUT_COPPER = register(Blocks.WAXED_OXIDIZED_CUT_COPPER).wall(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL).group("waxed_oxidized_cut_copper").noGenerateModels().build();

        POLISHED_PRISMARINE = register(ModBlocks.POLISHED_PRISMARINE).slab(ModBlocks.POLISHED_PRISMARINE_SLAB).stairs(ModBlocks.POLISHED_PRISMARINE_STAIRS).wall(ModBlocks.POLISHED_PRISMARINE_WALL).build();
        RED_NETHER_BRICKS = register(Blocks.RED_NETHER_BRICKS).chiseled(ModBlocks.CHISELED_RED_NETHER_BRICKS).cracked(ModBlocks.CRACKED_RED_NETHER_BRICKS).fence(ModBlocks.RED_NETHER_BRICK_FENCE).build();
        POLISHED_END_STONE = register(ModBlocks.POLISHED_END_STONE).slab(ModBlocks.POLISHED_END_STONE_SLAB).stairs(ModBlocks.POLISHED_END_STONE_STAIRS).wall(ModBlocks.POLISHED_END_STONE_WALL).build();
    }
}
