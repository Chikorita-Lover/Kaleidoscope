package com.chikoritalover.kaleidoscope.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class KaleidoscopeBlockFamilies {
    public static final BlockFamily CALCITE;
    public static final BlockFamily POLISHED_CALCITE;
    public static final BlockFamily TUFF;
    public static final BlockFamily POLISHED_TUFF;
    public static final BlockFamily CUT_COPPER;
    public static final BlockFamily EXPOSED_CUT_COPPER;
    public static final BlockFamily WEATHERED_CUT_COPPER;
    public static final BlockFamily OXIDIZED_CUT_COPPER;
    public static final BlockFamily WAXED_CUT_COPPER;
    public static final BlockFamily WAXED_EXPOSED_CUT_COPPER;
    public static final BlockFamily WAXED_WEATHERED_CUT_COPPER;
    public static final BlockFamily WAXED_OXIDIZED_CUT_COPPER;
    public static final BlockFamily BRICK_MOSAIC;
    public static final BlockFamily BLACK_PAINTED_BRICKS;
    public static final BlockFamily BLUE_PAINTED_BRICKS;
    public static final BlockFamily BROWN_PAINTED_BRICKS;
    public static final BlockFamily CYAN_PAINTED_BRICKS;
    public static final BlockFamily GRAY_PAINTED_BRICKS;
    public static final BlockFamily GREEN_PAINTED_BRICKS;
    public static final BlockFamily LIGHT_BLUE_PAINTED_BRICKS;
    public static final BlockFamily LIGHT_GRAY_PAINTED_BRICKS;
    public static final BlockFamily LIME_PAINTED_BRICKS;
    public static final BlockFamily MAGENTA_PAINTED_BRICKS;
    public static final BlockFamily ORANGE_PAINTED_BRICKS;
    public static final BlockFamily PINK_PAINTED_BRICKS;
    public static final BlockFamily PURPLE_PAINTED_BRICKS;
    public static final BlockFamily RED_PAINTED_BRICKS;
    public static final BlockFamily WHITE_PAINTED_BRICKS;
    public static final BlockFamily YELLOW_PAINTED_BRICKS;
    public static final BlockFamily RED_NETHER_BRICKS;
    public static final BlockFamily END_STONE;
    public static final BlockFamily POLISHED_END_STONE;
    public static final BlockFamily WHITE_TERRACOTTA;
    public static final BlockFamily ORANGE_TERRACOTTA;
    public static final BlockFamily MAGENTA_TERRACOTTA;
    public static final BlockFamily LIGHT_BLUE_TERRACOTTA;
    public static final BlockFamily YELLOW_TERRACOTTA;
    public static final BlockFamily LIME_TERRACOTTA;
    public static final BlockFamily PINK_TERRACOTTA;
    public static final BlockFamily GRAY_TERRACOTTA;
    public static final BlockFamily LIGHT_GRAY_TERRACOTTA;
    public static final BlockFamily CYAN_TERRACOTTA;
    public static final BlockFamily PURPLE_TERRACOTTA;
    public static final BlockFamily BLUE_TERRACOTTA;
    public static final BlockFamily BROWN_TERRACOTTA;
    public static final BlockFamily GREEN_TERRACOTTA;
    public static final BlockFamily RED_TERRACOTTA;
    public static final BlockFamily BLACK_TERRACOTTA;
    public static final BlockFamily TERRACOTTA;
    public static final BlockFamily PURPUR;
    public static final BlockFamily SMOOTH_BASALT;
    public static final BlockFamily PACKED_MUD;
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    static {
        CALCITE = register(Blocks.CALCITE).slab(KaleidoscopeBlocks.CALCITE_SLAB).stairs(KaleidoscopeBlocks.CALCITE_STAIRS).wall(KaleidoscopeBlocks.CALCITE_WALL).build();
        POLISHED_CALCITE = register(KaleidoscopeBlocks.POLISHED_CALCITE).slab(KaleidoscopeBlocks.POLISHED_CALCITE_SLAB).stairs(KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS).build();
        TUFF = register(Blocks.TUFF).slab(KaleidoscopeBlocks.TUFF_SLAB).stairs(KaleidoscopeBlocks.TUFF_STAIRS).wall(KaleidoscopeBlocks.TUFF_WALL).build();
        POLISHED_TUFF = register(KaleidoscopeBlocks.POLISHED_TUFF).slab(KaleidoscopeBlocks.POLISHED_TUFF_SLAB).stairs(KaleidoscopeBlocks.POLISHED_TUFF_STAIRS).build();

        CUT_COPPER = register(Blocks.CUT_COPPER).wall(KaleidoscopeBlocks.CUT_COPPER_WALL).noGenerateModels().build();
        EXPOSED_CUT_COPPER = register(Blocks.EXPOSED_CUT_COPPER).wall(KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL).noGenerateModels().build();
        WEATHERED_CUT_COPPER = register(Blocks.WEATHERED_CUT_COPPER).wall(KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL).noGenerateModels().build();
        OXIDIZED_CUT_COPPER = register(Blocks.OXIDIZED_CUT_COPPER).wall(KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL).noGenerateModels().build();

        WAXED_CUT_COPPER = register(Blocks.WAXED_CUT_COPPER).wall(KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL).group("waxed_cut_copper").noGenerateModels().build();
        WAXED_EXPOSED_CUT_COPPER = register(Blocks.WAXED_EXPOSED_CUT_COPPER).wall(KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL).group("waxed_exposed_cut_copper").noGenerateModels().build();
        WAXED_WEATHERED_CUT_COPPER = register(Blocks.WAXED_WEATHERED_CUT_COPPER).wall(KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL).group("waxed_weathered_cut_copper").noGenerateModels().build();
        WAXED_OXIDIZED_CUT_COPPER = register(Blocks.WAXED_OXIDIZED_CUT_COPPER).wall(KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL).group("waxed_oxidized_cut_copper").noGenerateModels().build();

        BRICK_MOSAIC = register(KaleidoscopeBlocks.BRICK_MOSAIC).slab(KaleidoscopeBlocks.BRICK_MOSAIC_SLAB).stairs(KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS).build();

        BLACK_PAINTED_BRICKS = register(KaleidoscopeBlocks.BLACK_PAINTED_BRICKS).slab(KaleidoscopeBlocks.BLACK_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BLACK_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BLACK_PAINTED_BRICK_WALL).group("painted_brick").build();
        BLUE_PAINTED_BRICKS = register(KaleidoscopeBlocks.BLUE_PAINTED_BRICKS).slab(KaleidoscopeBlocks.BLUE_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BLUE_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BLUE_PAINTED_BRICK_WALL).group("painted_brick").build();
        BROWN_PAINTED_BRICKS = register(KaleidoscopeBlocks.BROWN_PAINTED_BRICKS).slab(KaleidoscopeBlocks.BROWN_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BROWN_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BROWN_PAINTED_BRICK_WALL).group("painted_brick").build();
        CYAN_PAINTED_BRICKS = register(KaleidoscopeBlocks.CYAN_PAINTED_BRICKS).slab(KaleidoscopeBlocks.CYAN_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.CYAN_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.CYAN_PAINTED_BRICK_WALL).group("painted_brick").build();
        GRAY_PAINTED_BRICKS = register(KaleidoscopeBlocks.GRAY_PAINTED_BRICKS).slab(KaleidoscopeBlocks.GRAY_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.GRAY_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.GRAY_PAINTED_BRICK_WALL).group("painted_brick").build();
        GREEN_PAINTED_BRICKS = register(KaleidoscopeBlocks.GREEN_PAINTED_BRICKS).slab(KaleidoscopeBlocks.GREEN_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.GREEN_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.GREEN_PAINTED_BRICK_WALL).group("painted_brick").build();
        LIGHT_BLUE_PAINTED_BRICKS = register(KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICKS).slab(KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_WALL).group("painted_brick").build();
        LIGHT_GRAY_PAINTED_BRICKS = register(KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICKS).slab(KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_WALL).group("painted_brick").build();
        LIME_PAINTED_BRICKS = register(KaleidoscopeBlocks.LIME_PAINTED_BRICKS).slab(KaleidoscopeBlocks.LIME_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIME_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIME_PAINTED_BRICK_WALL).group("painted_brick").build();
        MAGENTA_PAINTED_BRICKS = register(KaleidoscopeBlocks.MAGENTA_PAINTED_BRICKS).slab(KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_WALL).group("painted_brick").build();
        ORANGE_PAINTED_BRICKS = register(KaleidoscopeBlocks.ORANGE_PAINTED_BRICKS).slab(KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_WALL).group("painted_brick").build();
        PINK_PAINTED_BRICKS = register(KaleidoscopeBlocks.PINK_PAINTED_BRICKS).slab(KaleidoscopeBlocks.PINK_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.PINK_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.PINK_PAINTED_BRICK_WALL).group("painted_brick").build();
        PURPLE_PAINTED_BRICKS = register(KaleidoscopeBlocks.PURPLE_PAINTED_BRICKS).slab(KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_WALL).group("painted_brick").build();
        RED_PAINTED_BRICKS = register(KaleidoscopeBlocks.RED_PAINTED_BRICKS).slab(KaleidoscopeBlocks.RED_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.RED_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.RED_PAINTED_BRICK_WALL).group("painted_brick").build();
        WHITE_PAINTED_BRICKS = register(KaleidoscopeBlocks.WHITE_PAINTED_BRICKS).slab(KaleidoscopeBlocks.WHITE_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.WHITE_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.WHITE_PAINTED_BRICK_WALL).group("painted_brick").build();
        YELLOW_PAINTED_BRICKS = register(KaleidoscopeBlocks.YELLOW_PAINTED_BRICKS).slab(KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_SLAB).stairs(KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_STAIRS).wall(KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_WALL).group("painted_brick").build();
        
        RED_NETHER_BRICKS = register(Blocks.RED_NETHER_BRICKS).chiseled(KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS).cracked(KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS).fence(KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE).noGenerateRecipes().build();
        END_STONE = register(Blocks.END_STONE).slab(KaleidoscopeBlocks.END_STONE_SLAB).stairs(KaleidoscopeBlocks.END_STONE_STAIRS).wall(KaleidoscopeBlocks.END_STONE_WALL).build();
        POLISHED_END_STONE = register(KaleidoscopeBlocks.POLISHED_END_STONE).slab(KaleidoscopeBlocks.POLISHED_END_STONE_SLAB).stairs(KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS).build();

        WHITE_TERRACOTTA = register(Blocks.WHITE_TERRACOTTA).slab(KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS).group("terracotta").build();
        ORANGE_TERRACOTTA = register(Blocks.ORANGE_TERRACOTTA).slab(KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS).group("terracotta").build();
        MAGENTA_TERRACOTTA = register(Blocks.MAGENTA_TERRACOTTA).slab(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS).group("terracotta").build();
        LIGHT_BLUE_TERRACOTTA = register(Blocks.LIGHT_BLUE_TERRACOTTA).slab(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS).group("terracotta").build();
        YELLOW_TERRACOTTA = register(Blocks.YELLOW_TERRACOTTA).slab(KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS).group("terracotta").build();
        LIME_TERRACOTTA = register(Blocks.LIME_TERRACOTTA).slab(KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS).group("terracotta").build();
        PINK_TERRACOTTA = register(Blocks.PINK_TERRACOTTA).slab(KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS).group("terracotta").build();
        GRAY_TERRACOTTA = register(Blocks.GRAY_TERRACOTTA).slab(KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS).group("terracotta").build();
        LIGHT_GRAY_TERRACOTTA = register(Blocks.LIGHT_GRAY_TERRACOTTA).slab(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS).group("terracotta").build();
        CYAN_TERRACOTTA = register(Blocks.CYAN_TERRACOTTA).slab(KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS).group("terracotta").build();
        PURPLE_TERRACOTTA = register(Blocks.PURPLE_TERRACOTTA).slab(KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS).group("terracotta").build();
        BLUE_TERRACOTTA = register(Blocks.BLUE_TERRACOTTA).slab(KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS).group("terracotta").build();
        BROWN_TERRACOTTA = register(Blocks.BROWN_TERRACOTTA).slab(KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS).group("terracotta").build();
        GREEN_TERRACOTTA = register(Blocks.GREEN_TERRACOTTA).slab(KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS).group("terracotta").build();
        RED_TERRACOTTA = register(Blocks.RED_TERRACOTTA).slab(KaleidoscopeBlocks.RED_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS).group("terracotta").build();
        BLACK_TERRACOTTA = register(Blocks.BLACK_TERRACOTTA).slab(KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS).group("terracotta").build();
        TERRACOTTA = register(Blocks.TERRACOTTA).slab(KaleidoscopeBlocks.TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.TERRACOTTA_STAIRS).group("terracotta").build();

        PURPUR = register(Blocks.PURPUR_BLOCK).chiseled(KaleidoscopeBlocks.CHISELED_PURPUR).noGenerateRecipes().build();
        SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT).slab(KaleidoscopeBlocks.SMOOTH_BASALT_SLAB).stairs(KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS).wall(KaleidoscopeBlocks.SMOOTH_BASALT_WALL).build();
        PACKED_MUD = register(Blocks.PACKED_MUD).slab(KaleidoscopeBlocks.PACKED_MUD_SLAB).stairs(KaleidoscopeBlocks.PACKED_MUD_STAIRS).build();
    }

    public KaleidoscopeBlockFamilies() {
    }

    public static BlockFamily.Builder register(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registries.BLOCK.getId(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }
}
