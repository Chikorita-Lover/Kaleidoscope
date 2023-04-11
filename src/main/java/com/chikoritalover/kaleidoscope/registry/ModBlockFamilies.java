package com.chikoritalover.kaleidoscope.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
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
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    static {
        CALCITE = register(Blocks.CALCITE).slab(ModBlocks.CALCITE_SLAB).stairs(ModBlocks.CALCITE_STAIRS).wall(ModBlocks.CALCITE_WALL).build();
        POLISHED_CALCITE = register(ModBlocks.POLISHED_CALCITE).slab(ModBlocks.POLISHED_CALCITE_SLAB).stairs(ModBlocks.POLISHED_CALCITE_STAIRS).build();
        TUFF = register(Blocks.TUFF).slab(ModBlocks.TUFF_SLAB).stairs(ModBlocks.TUFF_STAIRS).wall(ModBlocks.TUFF_WALL).build();
        POLISHED_TUFF = register(ModBlocks.POLISHED_TUFF).slab(ModBlocks.POLISHED_TUFF_SLAB).stairs(ModBlocks.POLISHED_TUFF_STAIRS).build();

        CUT_COPPER = register(Blocks.CUT_COPPER).wall(ModBlocks.CUT_COPPER_WALL).noGenerateModels().build();
        EXPOSED_CUT_COPPER = register(Blocks.EXPOSED_CUT_COPPER).wall(ModBlocks.EXPOSED_CUT_COPPER_WALL).noGenerateModels().build();
        WEATHERED_CUT_COPPER = register(Blocks.WEATHERED_CUT_COPPER).wall(ModBlocks.WEATHERED_CUT_COPPER_WALL).noGenerateModels().build();
        OXIDIZED_CUT_COPPER = register(Blocks.OXIDIZED_CUT_COPPER).wall(ModBlocks.OXIDIZED_CUT_COPPER_WALL).noGenerateModels().build();

        WAXED_CUT_COPPER = register(Blocks.WAXED_CUT_COPPER).wall(ModBlocks.WAXED_CUT_COPPER_WALL).group("waxed_cut_copper").noGenerateModels().build();
        WAXED_EXPOSED_CUT_COPPER = register(Blocks.WAXED_EXPOSED_CUT_COPPER).wall(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL).group("waxed_exposed_cut_copper").noGenerateModels().build();
        WAXED_WEATHERED_CUT_COPPER = register(Blocks.WAXED_WEATHERED_CUT_COPPER).wall(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL).group("waxed_weathered_cut_copper").noGenerateModels().build();
        WAXED_OXIDIZED_CUT_COPPER = register(Blocks.WAXED_OXIDIZED_CUT_COPPER).wall(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL).group("waxed_oxidized_cut_copper").noGenerateModels().build();

        BLACK_PAINTED_BRICKS = register(ModBlocks.BLACK_PAINTED_BRICKS).slab(ModBlocks.BLACK_PAINTED_BRICK_SLAB).stairs(ModBlocks.BLACK_PAINTED_BRICK_STAIRS).wall(ModBlocks.BLACK_PAINTED_BRICK_WALL).build();
        BLUE_PAINTED_BRICKS = register(ModBlocks.BLUE_PAINTED_BRICKS).slab(ModBlocks.BLUE_PAINTED_BRICK_SLAB).stairs(ModBlocks.BLUE_PAINTED_BRICK_STAIRS).wall(ModBlocks.BLUE_PAINTED_BRICK_WALL).build();
        BROWN_PAINTED_BRICKS = register(ModBlocks.BROWN_PAINTED_BRICKS).slab(ModBlocks.BROWN_PAINTED_BRICK_SLAB).stairs(ModBlocks.BROWN_PAINTED_BRICK_STAIRS).wall(ModBlocks.BROWN_PAINTED_BRICK_WALL).build();
        CYAN_PAINTED_BRICKS = register(ModBlocks.CYAN_PAINTED_BRICKS).slab(ModBlocks.CYAN_PAINTED_BRICK_SLAB).stairs(ModBlocks.CYAN_PAINTED_BRICK_STAIRS).wall(ModBlocks.CYAN_PAINTED_BRICK_WALL).build();
        GRAY_PAINTED_BRICKS = register(ModBlocks.GRAY_PAINTED_BRICKS).slab(ModBlocks.GRAY_PAINTED_BRICK_SLAB).stairs(ModBlocks.GRAY_PAINTED_BRICK_STAIRS).wall(ModBlocks.GRAY_PAINTED_BRICK_WALL).build();
        GREEN_PAINTED_BRICKS = register(ModBlocks.GREEN_PAINTED_BRICKS).slab(ModBlocks.GREEN_PAINTED_BRICK_SLAB).stairs(ModBlocks.GREEN_PAINTED_BRICK_STAIRS).wall(ModBlocks.GREEN_PAINTED_BRICK_WALL).build();
        LIGHT_BLUE_PAINTED_BRICKS = register(ModBlocks.LIGHT_BLUE_PAINTED_BRICKS).slab(ModBlocks.LIGHT_BLUE_PAINTED_BRICK_SLAB).stairs(ModBlocks.LIGHT_BLUE_PAINTED_BRICK_STAIRS).wall(ModBlocks.LIGHT_BLUE_PAINTED_BRICK_WALL).build();
        LIGHT_GRAY_PAINTED_BRICKS = register(ModBlocks.LIGHT_GRAY_PAINTED_BRICKS).slab(ModBlocks.LIGHT_GRAY_PAINTED_BRICK_SLAB).stairs(ModBlocks.LIGHT_GRAY_PAINTED_BRICK_STAIRS).wall(ModBlocks.LIGHT_GRAY_PAINTED_BRICK_WALL).build();
        LIME_PAINTED_BRICKS = register(ModBlocks.LIME_PAINTED_BRICKS).slab(ModBlocks.LIME_PAINTED_BRICK_SLAB).stairs(ModBlocks.LIME_PAINTED_BRICK_STAIRS).wall(ModBlocks.LIME_PAINTED_BRICK_WALL).build();
        MAGENTA_PAINTED_BRICKS = register(ModBlocks.MAGENTA_PAINTED_BRICKS).slab(ModBlocks.MAGENTA_PAINTED_BRICK_SLAB).stairs(ModBlocks.MAGENTA_PAINTED_BRICK_STAIRS).wall(ModBlocks.MAGENTA_PAINTED_BRICK_WALL).build();
        ORANGE_PAINTED_BRICKS = register(ModBlocks.ORANGE_PAINTED_BRICKS).slab(ModBlocks.ORANGE_PAINTED_BRICK_SLAB).stairs(ModBlocks.ORANGE_PAINTED_BRICK_STAIRS).wall(ModBlocks.ORANGE_PAINTED_BRICK_WALL).build();
        PINK_PAINTED_BRICKS = register(ModBlocks.PINK_PAINTED_BRICKS).slab(ModBlocks.PINK_PAINTED_BRICK_SLAB).stairs(ModBlocks.PINK_PAINTED_BRICK_STAIRS).wall(ModBlocks.PINK_PAINTED_BRICK_WALL).build();
        PURPLE_PAINTED_BRICKS = register(ModBlocks.PURPLE_PAINTED_BRICKS).slab(ModBlocks.PURPLE_PAINTED_BRICK_SLAB).stairs(ModBlocks.PURPLE_PAINTED_BRICK_STAIRS).wall(ModBlocks.PURPLE_PAINTED_BRICK_WALL).build();
        RED_PAINTED_BRICKS = register(ModBlocks.RED_PAINTED_BRICKS).slab(ModBlocks.RED_PAINTED_BRICK_SLAB).stairs(ModBlocks.RED_PAINTED_BRICK_STAIRS).wall(ModBlocks.RED_PAINTED_BRICK_WALL).build();
        WHITE_PAINTED_BRICKS = register(ModBlocks.WHITE_PAINTED_BRICKS).slab(ModBlocks.WHITE_PAINTED_BRICK_SLAB).stairs(ModBlocks.WHITE_PAINTED_BRICK_STAIRS).wall(ModBlocks.WHITE_PAINTED_BRICK_WALL).build();
        YELLOW_PAINTED_BRICKS = register(ModBlocks.YELLOW_PAINTED_BRICKS).slab(ModBlocks.YELLOW_PAINTED_BRICK_SLAB).stairs(ModBlocks.YELLOW_PAINTED_BRICK_STAIRS).wall(ModBlocks.YELLOW_PAINTED_BRICK_WALL).build();
        
        RED_NETHER_BRICKS = register(Blocks.RED_NETHER_BRICKS).chiseled(ModBlocks.CHISELED_RED_NETHER_BRICKS).cracked(ModBlocks.CRACKED_RED_NETHER_BRICKS).fence(ModBlocks.RED_NETHER_BRICK_FENCE).noGenerateRecipes().build();
        END_STONE = register(Blocks.END_STONE).slab(ModBlocks.END_STONE_SLAB).stairs(ModBlocks.END_STONE_STAIRS).wall(ModBlocks.END_STONE_WALL).build();
        POLISHED_END_STONE = register(ModBlocks.POLISHED_END_STONE).slab(ModBlocks.POLISHED_END_STONE_SLAB).stairs(ModBlocks.POLISHED_END_STONE_STAIRS).build();

        WHITE_TERRACOTTA = register(Blocks.WHITE_TERRACOTTA).slab(ModBlocks.WHITE_TERRACOTTA_SLAB).stairs(ModBlocks.WHITE_TERRACOTTA_STAIRS).build();
        ORANGE_TERRACOTTA = register(Blocks.ORANGE_TERRACOTTA).slab(ModBlocks.ORANGE_TERRACOTTA_SLAB).stairs(ModBlocks.ORANGE_TERRACOTTA_STAIRS).build();
        MAGENTA_TERRACOTTA = register(Blocks.MAGENTA_TERRACOTTA).slab(ModBlocks.MAGENTA_TERRACOTTA_SLAB).stairs(ModBlocks.MAGENTA_TERRACOTTA_STAIRS).build();
        LIGHT_BLUE_TERRACOTTA = register(Blocks.LIGHT_BLUE_TERRACOTTA).slab(ModBlocks.LIGHT_BLUE_TERRACOTTA_SLAB).stairs(ModBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS).build();
        YELLOW_TERRACOTTA = register(Blocks.YELLOW_TERRACOTTA).slab(ModBlocks.YELLOW_TERRACOTTA_SLAB).stairs(ModBlocks.YELLOW_TERRACOTTA_STAIRS).build();
        LIME_TERRACOTTA = register(Blocks.LIME_TERRACOTTA).slab(ModBlocks.LIME_TERRACOTTA_SLAB).stairs(ModBlocks.LIME_TERRACOTTA_STAIRS).build();
        PINK_TERRACOTTA = register(Blocks.PINK_TERRACOTTA).slab(ModBlocks.PINK_TERRACOTTA_SLAB).stairs(ModBlocks.PINK_TERRACOTTA_STAIRS).build();
        GRAY_TERRACOTTA = register(Blocks.GRAY_TERRACOTTA).slab(ModBlocks.GRAY_TERRACOTTA_SLAB).stairs(ModBlocks.GRAY_TERRACOTTA_STAIRS).build();
        LIGHT_GRAY_TERRACOTTA = register(Blocks.LIGHT_GRAY_TERRACOTTA).slab(ModBlocks.LIGHT_GRAY_TERRACOTTA_SLAB).stairs(ModBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS).build();
        CYAN_TERRACOTTA = register(Blocks.CYAN_TERRACOTTA).slab(ModBlocks.CYAN_TERRACOTTA_SLAB).stairs(ModBlocks.CYAN_TERRACOTTA_STAIRS).build();
        PURPLE_TERRACOTTA = register(Blocks.PURPLE_TERRACOTTA).slab(ModBlocks.PURPLE_TERRACOTTA_SLAB).stairs(ModBlocks.PURPLE_TERRACOTTA_STAIRS).build();
        BLUE_TERRACOTTA = register(Blocks.BLUE_TERRACOTTA).slab(ModBlocks.BLUE_TERRACOTTA_SLAB).stairs(ModBlocks.BLUE_TERRACOTTA_STAIRS).build();
        BROWN_TERRACOTTA = register(Blocks.BROWN_TERRACOTTA).slab(ModBlocks.BROWN_TERRACOTTA_SLAB).stairs(ModBlocks.BROWN_TERRACOTTA_STAIRS).build();
        GREEN_TERRACOTTA = register(Blocks.GREEN_TERRACOTTA).slab(ModBlocks.GREEN_TERRACOTTA_SLAB).stairs(ModBlocks.GREEN_TERRACOTTA_STAIRS).build();
        RED_TERRACOTTA = register(Blocks.RED_TERRACOTTA).slab(ModBlocks.RED_TERRACOTTA_SLAB).stairs(ModBlocks.RED_TERRACOTTA_STAIRS).build();
        BLACK_TERRACOTTA = register(Blocks.BLACK_TERRACOTTA).slab(ModBlocks.BLACK_TERRACOTTA_SLAB).stairs(ModBlocks.BLACK_TERRACOTTA_STAIRS).build();
        TERRACOTTA = register(Blocks.TERRACOTTA).slab(ModBlocks.TERRACOTTA_SLAB).stairs(ModBlocks.TERRACOTTA_STAIRS).build();

        PURPUR = register(Blocks.PURPUR_BLOCK).chiseled(ModBlocks.CHISELED_PURPUR).noGenerateRecipes().build();
        SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT).slab(ModBlocks.SMOOTH_BASALT_SLAB).stairs(ModBlocks.SMOOTH_BASALT_STAIRS).wall(ModBlocks.SMOOTH_BASALT_WALL).build();
    }

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
}
