package com.chikoritalover.kaleidoscope.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.Registries;

import java.util.Map;
import java.util.stream.Stream;

public class KaleidoscopeBlockFamilies {
    private static final Map<Block, BlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();
    public static final BlockFamily CALCITE = register(Blocks.CALCITE).slab(KaleidoscopeBlocks.CALCITE_SLAB).stairs(KaleidoscopeBlocks.CALCITE_STAIRS).wall(KaleidoscopeBlocks.CALCITE_WALL).build();
    public static final BlockFamily POLISHED_CALCITE = register(KaleidoscopeBlocks.POLISHED_CALCITE).slab(KaleidoscopeBlocks.POLISHED_CALCITE_SLAB).stairs(KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS).wall(KaleidoscopeBlocks.POLISHED_CALCITE_WALL).build();

    public static final BlockFamily SMOOTH_COPPER = register(KaleidoscopeBlocks.SMOOTH_COPPER).stairs(KaleidoscopeBlocks.SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily EXPOSED_SMOOTH_COPPER = register(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily WEATHERED_SMOOTH_COPPER = register(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily OXIDIZED_SMOOTH_COPPER = register(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily WAXED_SMOOTH_COPPER = register(KaleidoscopeBlocks.WAXED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.WAXED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.WAXED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily WAXED_EXPOSED_SMOOTH_COPPER = register(KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily WAXED_WEATHERED_SMOOTH_COPPER = register(KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER_SLAB).noGenerateModels().build();
    public static final BlockFamily WAXED_OXIDIZED_SMOOTH_COPPER = register(KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER).stairs(KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS).slab(KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER_SLAB).noGenerateModels().build();

    public static final BlockFamily BRICK_MOSAIC = register(KaleidoscopeBlocks.BRICK_MOSAIC).slab(KaleidoscopeBlocks.BRICK_MOSAIC_SLAB).stairs(KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS).build();

    public static final BlockFamily WHITE_STAINED_BRICKS = register(KaleidoscopeBlocks.WHITE_STAINED_BRICKS).slab(KaleidoscopeBlocks.WHITE_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.WHITE_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.WHITE_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily LIGHT_GRAY_STAINED_BRICKS = register(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS).slab(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily GRAY_STAINED_BRICKS = register(KaleidoscopeBlocks.GRAY_STAINED_BRICKS).slab(KaleidoscopeBlocks.GRAY_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.GRAY_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.GRAY_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily BLACK_STAINED_BRICKS = register(KaleidoscopeBlocks.BLACK_STAINED_BRICKS).slab(KaleidoscopeBlocks.BLACK_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BLACK_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BLACK_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily BROWN_STAINED_BRICKS = register(KaleidoscopeBlocks.BROWN_STAINED_BRICKS).slab(KaleidoscopeBlocks.BROWN_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BROWN_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BROWN_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily RED_STAINED_BRICKS = register(KaleidoscopeBlocks.RED_STAINED_BRICKS).slab(KaleidoscopeBlocks.RED_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.RED_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.RED_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily ORANGE_STAINED_BRICKS = register(KaleidoscopeBlocks.ORANGE_STAINED_BRICKS).slab(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily YELLOW_STAINED_BRICKS = register(KaleidoscopeBlocks.YELLOW_STAINED_BRICKS).slab(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily LIME_STAINED_BRICKS = register(KaleidoscopeBlocks.LIME_STAINED_BRICKS).slab(KaleidoscopeBlocks.LIME_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIME_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIME_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily GREEN_STAINED_BRICKS = register(KaleidoscopeBlocks.GREEN_STAINED_BRICKS).slab(KaleidoscopeBlocks.GREEN_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.GREEN_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.GREEN_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily CYAN_STAINED_BRICKS = register(KaleidoscopeBlocks.CYAN_STAINED_BRICKS).slab(KaleidoscopeBlocks.CYAN_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.CYAN_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.CYAN_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily LIGHT_BLUE_STAINED_BRICKS = register(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS).slab(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily BLUE_STAINED_BRICKS = register(KaleidoscopeBlocks.BLUE_STAINED_BRICKS).slab(KaleidoscopeBlocks.BLUE_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.BLUE_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.BLUE_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily PURPLE_STAINED_BRICKS = register(KaleidoscopeBlocks.PURPLE_STAINED_BRICKS).slab(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily MAGENTA_STAINED_BRICKS = register(KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS).slab(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_WALL).group("stained_brick").build();
    public static final BlockFamily PINK_STAINED_BRICKS = register(KaleidoscopeBlocks.PINK_STAINED_BRICKS).slab(KaleidoscopeBlocks.PINK_STAINED_BRICK_SLAB).stairs(KaleidoscopeBlocks.PINK_STAINED_BRICK_STAIRS).wall(KaleidoscopeBlocks.PINK_STAINED_BRICK_WALL).group("stained_brick").build();

    public static final BlockFamily RED_NETHER_BRICKS = register(Blocks.RED_NETHER_BRICKS).chiseled(KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS).cracked(KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS).fence(KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE).noGenerateRecipes().build();
    public static final BlockFamily END_STONE = register(Blocks.END_STONE).slab(KaleidoscopeBlocks.END_STONE_SLAB).stairs(KaleidoscopeBlocks.END_STONE_STAIRS).wall(KaleidoscopeBlocks.END_STONE_WALL).build();
    public static final BlockFamily POLISHED_END_STONE = register(KaleidoscopeBlocks.POLISHED_END_STONE).slab(KaleidoscopeBlocks.POLISHED_END_STONE_SLAB).stairs(KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS).wall(KaleidoscopeBlocks.POLISHED_END_STONE_WALL).build();

    public static final BlockFamily GLASS = register(Blocks.GLASS).slab(KaleidoscopeBlocks.GLASS_SLAB).noGenerateModels().build();
    public static final BlockFamily WHITE_STAINED_GLASS = register(Blocks.WHITE_STAINED_GLASS).slab(KaleidoscopeBlocks.WHITE_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily LIGHT_GRAY_STAINED_GLASS = register(Blocks.LIGHT_GRAY_STAINED_GLASS).slab(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily GRAY_STAINED_GLASS = register(Blocks.GRAY_STAINED_GLASS).slab(KaleidoscopeBlocks.GRAY_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily BLACK_STAINED_GLASS = register(Blocks.BLACK_STAINED_GLASS).slab(KaleidoscopeBlocks.BLACK_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily BROWN_STAINED_GLASS = register(Blocks.BROWN_STAINED_GLASS).slab(KaleidoscopeBlocks.BROWN_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily RED_STAINED_GLASS = register(Blocks.RED_STAINED_GLASS).slab(KaleidoscopeBlocks.RED_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily ORANGE_STAINED_GLASS = register(Blocks.ORANGE_STAINED_GLASS).slab(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily YELLOW_STAINED_GLASS = register(Blocks.YELLOW_STAINED_GLASS).slab(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily LIME_STAINED_GLASS = register(Blocks.LIME_STAINED_GLASS).slab(KaleidoscopeBlocks.LIME_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily GREEN_STAINED_GLASS = register(Blocks.GREEN_STAINED_GLASS).slab(KaleidoscopeBlocks.GREEN_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily CYAN_STAINED_GLASS = register(Blocks.CYAN_STAINED_GLASS).slab(KaleidoscopeBlocks.CYAN_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily LIGHT_BLUE_STAINED_GLASS = register(Blocks.LIGHT_BLUE_STAINED_GLASS).slab(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily BLUE_STAINED_GLASS = register(Blocks.BLUE_STAINED_GLASS).slab(KaleidoscopeBlocks.BLUE_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily PURPLE_STAINED_GLASS = register(Blocks.PURPLE_STAINED_GLASS).slab(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily MAGENTA_STAINED_GLASS = register(Blocks.MAGENTA_STAINED_GLASS).slab(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();
    public static final BlockFamily PINK_STAINED_GLASS = register(Blocks.PINK_STAINED_GLASS).slab(KaleidoscopeBlocks.PINK_STAINED_GLASS_SLAB).group("stained_glass").noGenerateModels().build();

    public static final BlockFamily TERRACOTTA = register(Blocks.TERRACOTTA).slab(KaleidoscopeBlocks.TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily WHITE_TERRACOTTA = register(Blocks.WHITE_TERRACOTTA).slab(KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily LIGHT_GRAY_TERRACOTTA = register(Blocks.LIGHT_GRAY_TERRACOTTA).slab(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily GRAY_TERRACOTTA = register(Blocks.GRAY_TERRACOTTA).slab(KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily BLACK_TERRACOTTA = register(Blocks.BLACK_TERRACOTTA).slab(KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily BROWN_TERRACOTTA = register(Blocks.BROWN_TERRACOTTA).slab(KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily RED_TERRACOTTA = register(Blocks.RED_TERRACOTTA).slab(KaleidoscopeBlocks.RED_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily ORANGE_TERRACOTTA = register(Blocks.ORANGE_TERRACOTTA).slab(KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily YELLOW_TERRACOTTA = register(Blocks.YELLOW_TERRACOTTA).slab(KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily LIME_TERRACOTTA = register(Blocks.LIME_TERRACOTTA).slab(KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily GREEN_TERRACOTTA = register(Blocks.GREEN_TERRACOTTA).slab(KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily CYAN_TERRACOTTA = register(Blocks.CYAN_TERRACOTTA).slab(KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily LIGHT_BLUE_TERRACOTTA = register(Blocks.LIGHT_BLUE_TERRACOTTA).slab(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily BLUE_TERRACOTTA = register(Blocks.BLUE_TERRACOTTA).slab(KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily PURPLE_TERRACOTTA = register(Blocks.PURPLE_TERRACOTTA).slab(KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily MAGENTA_TERRACOTTA = register(Blocks.MAGENTA_TERRACOTTA).slab(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS).group("terracotta").build();
    public static final BlockFamily PINK_TERRACOTTA = register(Blocks.PINK_TERRACOTTA).slab(KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB).stairs(KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS).group("terracotta").build();

    public static final BlockFamily PURPUR = register(Blocks.PURPUR_BLOCK).chiseled(KaleidoscopeBlocks.CHISELED_PURPUR).noGenerateRecipes().build();
    public static final BlockFamily SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT).slab(KaleidoscopeBlocks.SMOOTH_BASALT_SLAB).stairs(KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS).wall(KaleidoscopeBlocks.SMOOTH_BASALT_WALL).build();
    public static final BlockFamily PACKED_MUD = register(Blocks.PACKED_MUD).slab(KaleidoscopeBlocks.PACKED_MUD_SLAB).stairs(KaleidoscopeBlocks.PACKED_MUD_STAIRS).build();

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
