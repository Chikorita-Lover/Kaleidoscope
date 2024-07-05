package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class KaleidoscopeBlocks {
    public static final Block POLISHED_GRANITE_WALL = registerBlockWithItem("polished_granite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)));
    public static final Block POLISHED_DIORITE_WALL = registerBlockWithItem("polished_diorite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)));
    public static final Block POLISHED_ANDESITE_WALL = registerBlockWithItem("polished_andesite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));

    public static final Block CALCITE_SLAB = registerBlockWithItem("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
    public static final Block CALCITE_STAIRS = registerBlockWithItem("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CALCITE)));
    public static final Block CALCITE_WALL = registerBlockWithItem("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

    public static final Block POLISHED_CALCITE = registerBlockWithItem("polished_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
    public static final Block POLISHED_CALCITE_SLAB = registerBlockWithItem("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_STAIRS = registerBlockWithItem("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_WALL = registerBlockWithItem("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)));

    public static final Block SMOOTH_CALCITE = registerBlockWithItem("smooth_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE).strength(1.9F, 6.0F)));
    public static final Block SMOOTH_CALCITE_SLAB = registerBlockWithItem("smooth_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_CALCITE)));
    public static final Block SMOOTH_CALCITE_STAIRS = registerBlockWithItem("smooth_calcite_stairs", new StairsBlock(SMOOTH_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_CALCITE)));

    public static final Block CRACKED_TUFF_BRICKS = registerBlockWithItem("cracked_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF_BRICKS)));

    public static final Block BRICK_MOSAIC = registerBlockWithItem("brick_mosaic", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));
    public static final Block BRICK_MOSAIC_STAIRS = registerBlockWithItem("brick_mosaic_stairs", new StairsBlock(BRICK_MOSAIC.getDefaultState(), AbstractBlock.Settings.copy(BRICK_MOSAIC)));
    public static final Block BRICK_MOSAIC_SLAB = registerBlockWithItem("brick_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(BRICK_MOSAIC)));

    public static final Block PACKED_MUD_STAIRS = registerBlockWithItem("packed_mud_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_MUD_SLAB = registerBlockWithItem("packed_mud_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));
    public static final Block PACKED_MUD_WALL = registerBlockWithItem("packed_mud_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)));

    public static final Block CRACKED_MUD_BRICKS = registerBlockWithItem("cracked_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)));

    public static final Block SMOOTH_BASALT_STAIRS = registerBlockWithItem("smooth_basalt_stairs", new StairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_SLAB = registerBlockWithItem("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_WALL = registerBlockWithItem("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

    public static final Block CHARCOAL_BLOCK = registerBlockWithItem("charcoal_block", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).strength(3.0F, 6.0F)));

    public static final Block QUARTZ_BRICK_STAIRS = registerBlockWithItem("quartz_brick_stairs", new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)));
    public static final Block QUARTZ_BRICK_SLAB = registerBlockWithItem("quartz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)));
    public static final Block QUARTZ_BRICK_WALL = registerBlockWithItem("quartz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)));

    public static final Block SMOOTH_COPPER = registerBlockWithItem("smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
    public static final Block SMOOTH_COPPER_STAIRS = registerBlockWithItem("smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_COPPER)));
    public static final Block SMOOTH_COPPER_SLAB = registerBlockWithItem("smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(SMOOTH_COPPER)));

    public static final Block EXPOSED_SMOOTH_COPPER = registerBlockWithItem("exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.EXPOSED_COPPER)));
    public static final Block EXPOSED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)));
    public static final Block EXPOSED_SMOOTH_COPPER_SLAB = registerBlockWithItem("exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)));

    public static final Block WEATHERED_SMOOTH_COPPER = registerBlockWithItem("weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.WEATHERED_COPPER)));
    public static final Block WEATHERED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)));
    public static final Block WEATHERED_SMOOTH_COPPER_SLAB = registerBlockWithItem("weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)));

    public static final Block OXIDIZED_SMOOTH_COPPER = registerBlockWithItem("oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.OXIDIZED_COPPER)));
    public static final Block OXIDIZED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)));
    public static final Block OXIDIZED_SMOOTH_COPPER_SLAB = registerBlockWithItem("oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)));

    public static final Block WAXED_SMOOTH_COPPER = registerBlockWithItem("waxed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.WAXED_COPPER_BLOCK)));
    public static final Block WAXED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, WAXED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)));
    public static final Block WAXED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)));

    public static final Block WAXED_EXPOSED_SMOOTH_COPPER = registerBlockWithItem("waxed_exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.WAXED_EXPOSED_COPPER)));
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, WAXED_EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)));
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)));

    public static final Block WAXED_WEATHERED_SMOOTH_COPPER = registerBlockWithItem("waxed_weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.WAXED_WEATHERED_COPPER)));
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WAXED_WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)));
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)));

    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER = registerBlockWithItem("waxed_oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.WAXED_OXIDIZED_COPPER)));
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, WAXED_OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)));
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)));

    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlockWithItem("cracked_red_nether_bricks", new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));
    public static final Block RED_NETHER_BRICK_FENCE = registerBlockWithItem("red_nether_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));

    public static final Block END_STONE_STAIRS = registerBlockWithItem("end_stone_stairs", new StairsBlock(Blocks.END_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.END_STONE)));
    public static final Block END_STONE_SLAB = registerBlockWithItem("end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)));
    public static final Block END_STONE_WALL = registerBlockWithItem("end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)));

    public static final Block POLISHED_END_STONE = registerBlockWithItem("polished_end_stone", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE)));
    public static final Block POLISHED_END_STONE_STAIRS = registerBlockWithItem("polished_end_stone_stairs", new StairsBlock(POLISHED_END_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_END_STONE)));
    public static final Block POLISHED_END_STONE_SLAB = registerBlockWithItem("polished_end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)));
    public static final Block POLISHED_END_STONE_WALL = registerBlockWithItem("polished_end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)));

    public static final Block CRACKED_END_STONE_BRICKS = registerBlockWithItem("cracked_end_stone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)));

    public static final Block WHITE_STAINED_BRICKS = registerBlockWithItem("white_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.WHITE)));
    public static final Block WHITE_STAINED_BRICK_STAIRS = registerBlockWithItem("white_stained_brick_stairs", new StairsBlock(WHITE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));
    public static final Block WHITE_STAINED_BRICK_SLAB = registerBlockWithItem("white_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));
    public static final Block WHITE_STAINED_BRICK_WALL = registerBlockWithItem("white_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));

    public static final Block LIGHT_GRAY_STAINED_BRICKS = registerBlockWithItem("light_gray_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_STAIRS = registerBlockWithItem("light_gray_stained_brick_stairs", new StairsBlock(LIGHT_GRAY_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_SLAB = registerBlockWithItem("light_gray_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_WALL = registerBlockWithItem("light_gray_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));

    public static final Block GRAY_STAINED_BRICKS = registerBlockWithItem("gray_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.GRAY)));
    public static final Block GRAY_STAINED_BRICK_STAIRS = registerBlockWithItem("gray_stained_brick_stairs", new StairsBlock(GRAY_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));
    public static final Block GRAY_STAINED_BRICK_SLAB = registerBlockWithItem("gray_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));
    public static final Block GRAY_STAINED_BRICK_WALL = registerBlockWithItem("gray_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));

    public static final Block BLACK_STAINED_BRICKS = registerBlockWithItem("black_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.BLACK)));
    public static final Block BLACK_STAINED_BRICK_STAIRS = registerBlockWithItem("black_stained_brick_stairs", new StairsBlock(BLACK_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));
    public static final Block BLACK_STAINED_BRICK_SLAB = registerBlockWithItem("black_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));
    public static final Block BLACK_STAINED_BRICK_WALL = registerBlockWithItem("black_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));

    public static final Block BROWN_STAINED_BRICKS = registerBlockWithItem("brown_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.BROWN)));
    public static final Block BROWN_STAINED_BRICK_STAIRS = registerBlockWithItem("brown_stained_brick_stairs", new StairsBlock(BROWN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));
    public static final Block BROWN_STAINED_BRICK_SLAB = registerBlockWithItem("brown_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));
    public static final Block BROWN_STAINED_BRICK_WALL = registerBlockWithItem("brown_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));

    public static final Block RED_STAINED_BRICKS = registerBlockWithItem("red_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.RED)));
    public static final Block RED_STAINED_BRICK_STAIRS = registerBlockWithItem("red_stained_brick_stairs", new StairsBlock(RED_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));
    public static final Block RED_STAINED_BRICK_SLAB = registerBlockWithItem("red_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));
    public static final Block RED_STAINED_BRICK_WALL = registerBlockWithItem("red_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));

    public static final Block ORANGE_STAINED_BRICKS = registerBlockWithItem("orange_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.ORANGE)));
    public static final Block ORANGE_STAINED_BRICK_STAIRS = registerBlockWithItem("orange_stained_brick_stairs", new StairsBlock(ORANGE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));
    public static final Block ORANGE_STAINED_BRICK_SLAB = registerBlockWithItem("orange_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));
    public static final Block ORANGE_STAINED_BRICK_WALL = registerBlockWithItem("orange_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));

    public static final Block YELLOW_STAINED_BRICKS = registerBlockWithItem("yellow_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.YELLOW)));
    public static final Block YELLOW_STAINED_BRICK_STAIRS = registerBlockWithItem("yellow_stained_brick_stairs", new StairsBlock(YELLOW_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));
    public static final Block YELLOW_STAINED_BRICK_SLAB = registerBlockWithItem("yellow_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));
    public static final Block YELLOW_STAINED_BRICK_WALL = registerBlockWithItem("yellow_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));

    public static final Block LIME_STAINED_BRICKS = registerBlockWithItem("lime_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.LIME)));
    public static final Block LIME_STAINED_BRICK_STAIRS = registerBlockWithItem("lime_stained_brick_stairs", new StairsBlock(LIME_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));
    public static final Block LIME_STAINED_BRICK_SLAB = registerBlockWithItem("lime_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));
    public static final Block LIME_STAINED_BRICK_WALL = registerBlockWithItem("lime_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));

    public static final Block GREEN_STAINED_BRICKS = registerBlockWithItem("green_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.GREEN)));
    public static final Block GREEN_STAINED_BRICK_STAIRS = registerBlockWithItem("green_stained_brick_stairs", new StairsBlock(GREEN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));
    public static final Block GREEN_STAINED_BRICK_SLAB = registerBlockWithItem("green_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));
    public static final Block GREEN_STAINED_BRICK_WALL = registerBlockWithItem("green_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));

    public static final Block CYAN_STAINED_BRICKS = registerBlockWithItem("cyan_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.CYAN)));
    public static final Block CYAN_STAINED_BRICK_STAIRS = registerBlockWithItem("cyan_stained_brick_stairs", new StairsBlock(CYAN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));
    public static final Block CYAN_STAINED_BRICK_SLAB = registerBlockWithItem("cyan_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));
    public static final Block CYAN_STAINED_BRICK_WALL = registerBlockWithItem("cyan_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));

    public static final Block LIGHT_BLUE_STAINED_BRICKS = registerBlockWithItem("light_blue_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_STAIRS = registerBlockWithItem("light_blue_stained_brick_stairs", new StairsBlock(LIGHT_BLUE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_SLAB = registerBlockWithItem("light_blue_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_WALL = registerBlockWithItem("light_blue_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));

    public static final Block BLUE_STAINED_BRICKS = registerBlockWithItem("blue_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.BLUE)));
    public static final Block BLUE_STAINED_BRICK_STAIRS = registerBlockWithItem("blue_stained_brick_stairs", new StairsBlock(BLUE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));
    public static final Block BLUE_STAINED_BRICK_SLAB = registerBlockWithItem("blue_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));
    public static final Block BLUE_STAINED_BRICK_WALL = registerBlockWithItem("blue_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));

    public static final Block PURPLE_STAINED_BRICKS = registerBlockWithItem("purple_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.PURPLE)));
    public static final Block PURPLE_STAINED_BRICK_STAIRS = registerBlockWithItem("purple_stained_brick_stairs", new StairsBlock(PURPLE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));
    public static final Block PURPLE_STAINED_BRICK_SLAB = registerBlockWithItem("purple_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));
    public static final Block PURPLE_STAINED_BRICK_WALL = registerBlockWithItem("purple_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));

    public static final Block MAGENTA_STAINED_BRICKS = registerBlockWithItem("magenta_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.MAGENTA)));
    public static final Block MAGENTA_STAINED_BRICK_STAIRS = registerBlockWithItem("magenta_stained_brick_stairs", new StairsBlock(MAGENTA_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));
    public static final Block MAGENTA_STAINED_BRICK_SLAB = registerBlockWithItem("magenta_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));
    public static final Block MAGENTA_STAINED_BRICK_WALL = registerBlockWithItem("magenta_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));

    public static final Block PINK_STAINED_BRICKS = registerBlockWithItem("pink_stained_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS).mapColor(MapColor.PINK)));
    public static final Block PINK_STAINED_BRICK_STAIRS = registerBlockWithItem("pink_stained_brick_stairs", new StairsBlock(PINK_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));
    public static final Block PINK_STAINED_BRICK_SLAB = registerBlockWithItem("pink_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));
    public static final Block PINK_STAINED_BRICK_WALL = registerBlockWithItem("pink_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));

    public static final Block TERRACOTTA_STAIRS = registerBlockWithItem("terracotta_stairs", new StairsBlock(Blocks.TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TERRACOTTA)));
    public static final Block TERRACOTTA_SLAB = registerBlockWithItem("terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA)));

    public static final Block WHITE_TERRACOTTA_STAIRS = registerBlockWithItem("white_terracotta_stairs", new StairsBlock(Blocks.WHITE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA)));
    public static final Block WHITE_TERRACOTTA_SLAB = registerBlockWithItem("white_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA)));

    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = registerBlockWithItem("light_gray_terracotta_stairs", new StairsBlock(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)));
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerBlockWithItem("light_gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)));

    public static final Block GRAY_TERRACOTTA_STAIRS = registerBlockWithItem("gray_terracotta_stairs", new StairsBlock(Blocks.GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA)));
    public static final Block GRAY_TERRACOTTA_SLAB = registerBlockWithItem("gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA)));

    public static final Block BLACK_TERRACOTTA_STAIRS = registerBlockWithItem("black_terracotta_stairs", new StairsBlock(Blocks.BLACK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA)));
    public static final Block BLACK_TERRACOTTA_SLAB = registerBlockWithItem("black_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA)));

    public static final Block BROWN_TERRACOTTA_STAIRS = registerBlockWithItem("brown_terracotta_stairs", new StairsBlock(Blocks.BROWN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA)));
    public static final Block BROWN_TERRACOTTA_SLAB = registerBlockWithItem("brown_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA)));

    public static final Block RED_TERRACOTTA_STAIRS = registerBlockWithItem("red_terracotta_stairs", new StairsBlock(Blocks.RED_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA)));
    public static final Block RED_TERRACOTTA_SLAB = registerBlockWithItem("red_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA)));

    public static final Block ORANGE_TERRACOTTA_STAIRS = registerBlockWithItem("orange_terracotta_stairs", new StairsBlock(Blocks.ORANGE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA)));
    public static final Block ORANGE_TERRACOTTA_SLAB = registerBlockWithItem("orange_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA)));

    public static final Block YELLOW_TERRACOTTA_STAIRS = registerBlockWithItem("yellow_terracotta_stairs", new StairsBlock(Blocks.YELLOW_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA)));
    public static final Block YELLOW_TERRACOTTA_SLAB = registerBlockWithItem("yellow_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA)));

    public static final Block LIME_TERRACOTTA_STAIRS = registerBlockWithItem("lime_terracotta_stairs", new StairsBlock(Blocks.LIME_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA)));
    public static final Block LIME_TERRACOTTA_SLAB = registerBlockWithItem("lime_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA)));

    public static final Block GREEN_TERRACOTTA_STAIRS = registerBlockWithItem("green_terracotta_stairs", new StairsBlock(Blocks.GREEN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA)));
    public static final Block GREEN_TERRACOTTA_SLAB = registerBlockWithItem("green_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA)));

    public static final Block CYAN_TERRACOTTA_STAIRS = registerBlockWithItem("cyan_terracotta_stairs", new StairsBlock(Blocks.CYAN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA)));
    public static final Block CYAN_TERRACOTTA_SLAB = registerBlockWithItem("cyan_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA)));

    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = registerBlockWithItem("light_blue_terracotta_stairs", new StairsBlock(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA)));
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = registerBlockWithItem("light_blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA)));

    public static final Block BLUE_TERRACOTTA_STAIRS = registerBlockWithItem("blue_terracotta_stairs", new StairsBlock(Blocks.BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA)));
    public static final Block BLUE_TERRACOTTA_SLAB = registerBlockWithItem("blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA)));

    public static final Block PURPLE_TERRACOTTA_STAIRS = registerBlockWithItem("purple_terracotta_stairs", new StairsBlock(Blocks.PURPLE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA)));
    public static final Block PURPLE_TERRACOTTA_SLAB = registerBlockWithItem("purple_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA)));

    public static final Block MAGENTA_TERRACOTTA_STAIRS = registerBlockWithItem("magenta_terracotta_stairs", new StairsBlock(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA)));
    public static final Block MAGENTA_TERRACOTTA_SLAB = registerBlockWithItem("magenta_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA)));

    public static final Block PINK_TERRACOTTA_STAIRS = registerBlockWithItem("pink_terracotta_stairs", new StairsBlock(Blocks.PINK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA)));
    public static final Block PINK_TERRACOTTA_SLAB = registerBlockWithItem("pink_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA)));

    public static final Block STICK_BLOCK = registerBlockWithItem("stick_block", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASS).sounds(BlockSoundGroup.MANGROVE_ROOTS).strength(0.4F).burnable()));

    public static final Block SOUL_JACK_O_LANTERN = registerBlockWithItem("soul_jack_o_lantern", new CarvedPumpkinBlock(AbstractBlock.Settings.copy(Blocks.JACK_O_LANTERN).instrument(NoteBlockInstrument.DIDGERIDOO).luminance(state -> 10)));

    public static final Block FIREWORKS_TABLE = registerBlockWithItem("fireworks_table", new FireworksTableBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block KILN = registerBlockWithItem("kiln", new KilnBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).luminance(state -> state.get(Properties.LIT) ? 13 : 0).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F)));

    public static final Block GLASS_DOOR = registerBlockWithItem("glass_door", createGlassDoorBlock(null));
    public static final Block WHITE_STAINED_GLASS_DOOR = registerBlockWithItem("white_stained_glass_door", createGlassDoorBlock(DyeColor.WHITE));
    public static final Block LIGHT_GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("light_gray_stained_glass_door", createGlassDoorBlock(DyeColor.LIGHT_GRAY));
    public static final Block GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("gray_stained_glass_door", createGlassDoorBlock(DyeColor.GRAY));
    public static final Block BLACK_STAINED_GLASS_DOOR = registerBlockWithItem("black_stained_glass_door", createGlassDoorBlock(DyeColor.BLACK));
    public static final Block BROWN_STAINED_GLASS_DOOR = registerBlockWithItem("brown_stained_glass_door", createGlassDoorBlock(DyeColor.BROWN));
    public static final Block RED_STAINED_GLASS_DOOR = registerBlockWithItem("red_stained_glass_door", createGlassDoorBlock(DyeColor.RED));
    public static final Block ORANGE_STAINED_GLASS_DOOR = registerBlockWithItem("orange_stained_glass_door", createGlassDoorBlock(DyeColor.ORANGE));
    public static final Block YELLOW_STAINED_GLASS_DOOR = registerBlockWithItem("yellow_stained_glass_door", createGlassDoorBlock(DyeColor.YELLOW));
    public static final Block LIME_STAINED_GLASS_DOOR = registerBlockWithItem("lime_stained_glass_door", createGlassDoorBlock(DyeColor.LIME));
    public static final Block GREEN_STAINED_GLASS_DOOR = registerBlockWithItem("green_stained_glass_door", createGlassDoorBlock(DyeColor.GREEN));
    public static final Block CYAN_STAINED_GLASS_DOOR = registerBlockWithItem("cyan_stained_glass_door", createGlassDoorBlock(DyeColor.CYAN));
    public static final Block LIGHT_BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("light_blue_stained_glass_door", createGlassDoorBlock(DyeColor.LIGHT_BLUE));
    public static final Block BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("blue_stained_glass_door", createGlassDoorBlock(DyeColor.BLUE));
    public static final Block PURPLE_STAINED_GLASS_DOOR = registerBlockWithItem("purple_stained_glass_door", createGlassDoorBlock(DyeColor.PURPLE));
    public static final Block MAGENTA_STAINED_GLASS_DOOR = registerBlockWithItem("magenta_stained_glass_door", createGlassDoorBlock(DyeColor.MAGENTA));
    public static final Block PINK_STAINED_GLASS_DOOR = registerBlockWithItem("pink_stained_glass_door", createGlassDoorBlock(DyeColor.PINK));

    public static final Block GLASS_TRAPDOOR = registerBlockWithItem("glass_trapdoor", new GlassTrapdoorBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F)));
    public static final Block BLACK_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("black_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLACK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLACK)));
    public static final Block BLUE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLUE)));
    public static final Block BROWN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("brown_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BROWN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BROWN)));
    public static final Block CYAN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("cyan_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.CYAN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.CYAN)));
    public static final Block GRAY_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GRAY)));
    public static final Block GREEN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("green_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GREEN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GREEN)));
    public static final Block LIGHT_BLUE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("light_blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block LIGHT_GRAY_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("light_gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block LIME_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("lime_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIME, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIME)));
    public static final Block MAGENTA_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("magenta_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.MAGENTA, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.MAGENTA)));
    public static final Block ORANGE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("orange_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.ORANGE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.ORANGE)));
    public static final Block PINK_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("pink_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PINK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PINK)));
    public static final Block PURPLE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("purple_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PURPLE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PURPLE)));
    public static final Block RED_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("red_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.RED, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.RED)));
    public static final Block WHITE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("white_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.WHITE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.WHITE)));
    public static final Block YELLOW_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("yellow_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.YELLOW, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.YELLOW)));

    public static final Block POTION_CAULDRON = register("potion_cauldron", new PotionCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)));

    private static Block createGlassDoorBlock(@Nullable DyeColor dyeColor) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F);
        if (dyeColor != null) {
            return new StainedGlassDoorBlock(dyeColor, settings.mapColor(dyeColor));
        }
        return new GlassDoorBlock(settings.mapColor(MapColor.CLEAR));
    }

    public static Block registerBlockWithItem(String id, Block block) {
        Block block2 = register(id, block);
        KaleidoscopeItems.register(id, block2);
        return block2;
    }

    public static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, Kaleidoscope.of(id), block);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(STICK_BLOCK, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().remove(Blocks.COAL_BLOCK);
    }

    public static void registerMossyPairs() {
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_COBBLESTONE, Blocks.COBBLESTONE);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.COBBLESTONE_SLAB);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.COBBLESTONE_STAIRS);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL);

        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_STONE_BRICKS, Blocks.STONE_BRICKS);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS);
        MossyBlocksRegistry.registerMossyBlockPair(Blocks.MOSSY_STONE_BRICK_WALL, Blocks.STONE_BRICK_WALL);

        MossyBlocksRegistry.registerMossyBlockPair(Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.INFESTED_STONE_BRICKS);
    }

    public static void registerOxidizablePairs() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(SMOOTH_COPPER, EXPOSED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(SMOOTH_COPPER_STAIRS, EXPOSED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(SMOOTH_COPPER_SLAB, EXPOSED_SMOOTH_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_SMOOTH_COPPER, WEATHERED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_SMOOTH_COPPER_STAIRS, WEATHERED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_SMOOTH_COPPER_SLAB, WEATHERED_SMOOTH_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_SMOOTH_COPPER, OXIDIZED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_SMOOTH_COPPER_STAIRS, OXIDIZED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_SMOOTH_COPPER_SLAB, OXIDIZED_SMOOTH_COPPER_SLAB);

        OxidizableBlocksRegistry.registerWaxableBlockPair(SMOOTH_COPPER, WAXED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(SMOOTH_COPPER_STAIRS, WAXED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(SMOOTH_COPPER_SLAB, WAXED_SMOOTH_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_SMOOTH_COPPER, WAXED_EXPOSED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_SMOOTH_COPPER_STAIRS, WAXED_EXPOSED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_SMOOTH_COPPER_SLAB, WAXED_EXPOSED_SMOOTH_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_SMOOTH_COPPER, WAXED_WEATHERED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_SMOOTH_COPPER_STAIRS, WAXED_WEATHERED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_SMOOTH_COPPER_SLAB, WAXED_WEATHERED_SMOOTH_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_SMOOTH_COPPER, WAXED_OXIDIZED_SMOOTH_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_SMOOTH_COPPER_STAIRS, WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_SMOOTH_COPPER_SLAB, WAXED_OXIDIZED_SMOOTH_COPPER_SLAB);
    }
}
