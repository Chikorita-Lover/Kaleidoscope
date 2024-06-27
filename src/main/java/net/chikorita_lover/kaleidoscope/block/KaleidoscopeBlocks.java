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

import java.util.function.ToIntFunction;

import static net.minecraft.block.Blocks.*;

public class KaleidoscopeBlocks {
    public static final Block POLISHED_GRANITE_WALL = registerBlockWithItem("polished_granite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_GRANITE)));
    public static final Block POLISHED_DIORITE_WALL = registerBlockWithItem("polished_diorite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_DIORITE)));
    public static final Block POLISHED_ANDESITE_WALL = registerBlockWithItem("polished_andesite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_ANDESITE)));

    public static final Block CALCITE_SLAB = registerBlockWithItem("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(CALCITE)));
    public static final Block CALCITE_STAIRS = registerBlockWithItem("calcite_stairs", new StairsBlock(CALCITE.getDefaultState(), AbstractBlock.Settings.copy(CALCITE)));
    public static final Block CALCITE_WALL = registerBlockWithItem("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(CALCITE)));

    public static final Block POLISHED_CALCITE = registerBlockWithItem("polished_calcite", new Block(AbstractBlock.Settings.copy(CALCITE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CALCITE_SLAB = registerBlockWithItem("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_STAIRS = registerBlockWithItem("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE)));
    public static final Block POLISHED_CALCITE_WALL = registerBlockWithItem("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)));

    public static final Block CRACKED_TUFF_BRICKS = registerBlockWithItem("cracked_tuff_bricks", new Block(AbstractBlock.Settings.copy(TUFF_BRICKS)));

    public static final Block BRICK_MOSAIC = registerBlockWithItem("brick_mosaic", new Block(AbstractBlock.Settings.copy(BRICKS)));
    public static final Block BRICK_MOSAIC_STAIRS = registerBlockWithItem("brick_mosaic_stairs", new StairsBlock(BRICK_MOSAIC.getDefaultState(), AbstractBlock.Settings.copy(BRICK_MOSAIC)));
    public static final Block BRICK_MOSAIC_SLAB = registerBlockWithItem("brick_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(BRICK_MOSAIC)));

    public static final Block PACKED_MUD_STAIRS = registerBlockWithItem("packed_mud_stairs", new StairsBlock(PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(PACKED_MUD)));
    public static final Block PACKED_MUD_SLAB = registerBlockWithItem("packed_mud_slab", new SlabBlock(AbstractBlock.Settings.copy(PACKED_MUD)));
    public static final Block PACKED_MUD_WALL = registerBlockWithItem("packed_mud_wall", new WallBlock(AbstractBlock.Settings.copy(PACKED_MUD)));

    public static final Block CRACKED_MUD_BRICKS = registerBlockWithItem("cracked_mud_bricks", new Block(AbstractBlock.Settings.copy(MUD_BRICKS)));

    public static final Block SMOOTH_BASALT_STAIRS = registerBlockWithItem("smooth_basalt_stairs", new StairsBlock(SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_SLAB = registerBlockWithItem("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_BASALT)));
    public static final Block SMOOTH_BASALT_WALL = registerBlockWithItem("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(SMOOTH_BASALT)));

    public static final Block CHARCOAL_BLOCK = registerBlockWithItem("charcoal_block", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).strength(3.0F, 6.0F)));

    public static final Block QUARTZ_BRICK_STAIRS = registerBlockWithItem("quartz_brick_stairs", new StairsBlock(QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(QUARTZ_BRICKS)));
    public static final Block QUARTZ_BRICK_SLAB = registerBlockWithItem("quartz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(QUARTZ_BRICKS)));
    public static final Block QUARTZ_BRICK_WALL = registerBlockWithItem("quartz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(QUARTZ_BRICKS)));

    public static final Block CUT_COPPER_WALL = registerBlockWithItem("cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(CUT_COPPER)));
    public static final Block SMOOTH_COPPER = registerBlockWithItem("smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(COPPER_BLOCK)));
    public static final Block SMOOTH_COPPER_STAIRS = registerBlockWithItem("smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_COPPER)));
    public static final Block SMOOTH_COPPER_SLAB = registerBlockWithItem("smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(SMOOTH_COPPER)));

    public static final Block EXPOSED_CUT_COPPER_WALL = registerBlockWithItem("exposed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_CUT_COPPER)));
    public static final Block EXPOSED_SMOOTH_COPPER = registerBlockWithItem("exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_COPPER)));
    public static final Block EXPOSED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)));
    public static final Block EXPOSED_SMOOTH_COPPER_SLAB = registerBlockWithItem("exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)));

    public static final Block WEATHERED_CUT_COPPER_WALL = registerBlockWithItem("weathered_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_CUT_COPPER)));
    public static final Block WEATHERED_SMOOTH_COPPER = registerBlockWithItem("weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_COPPER)));
    public static final Block WEATHERED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)));
    public static final Block WEATHERED_SMOOTH_COPPER_SLAB = registerBlockWithItem("weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)));

    public static final Block OXIDIZED_CUT_COPPER_WALL = registerBlockWithItem("oxidized_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_CUT_COPPER)));
    public static final Block OXIDIZED_SMOOTH_COPPER = registerBlockWithItem("oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_COPPER)));
    public static final Block OXIDIZED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)));
    public static final Block OXIDIZED_SMOOTH_COPPER_SLAB = registerBlockWithItem("oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)));

    public static final Block WAXED_CUT_COPPER_WALL = registerBlockWithItem("waxed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_CUT_COPPER)));
    public static final Block WAXED_SMOOTH_COPPER = registerBlockWithItem("waxed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_COPPER_BLOCK)));
    public static final Block WAXED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, WAXED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)));
    public static final Block WAXED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)));

    public static final Block WAXED_EXPOSED_CUT_COPPER_WALL = registerBlockWithItem("waxed_exposed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_CUT_COPPER)));
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER = registerBlockWithItem("waxed_exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_COPPER)));
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, WAXED_EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)));
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)));

    public static final Block WAXED_WEATHERED_CUT_COPPER_WALL = registerBlockWithItem("waxed_weathered_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_CUT_COPPER)));
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER = registerBlockWithItem("waxed_weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_COPPER)));
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WAXED_WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)));
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)));

    public static final Block WAXED_OXIDIZED_CUT_COPPER_WALL = registerBlockWithItem("waxed_oxidized_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_CUT_COPPER)));
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER = registerBlockWithItem("waxed_oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_COPPER)));
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS = registerBlockWithItem("waxed_oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, WAXED_OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)));
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_SLAB = registerBlockWithItem("waxed_oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)));

    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlockWithItem("cracked_red_nether_bricks", new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS)));
    public static final Block RED_NETHER_BRICK_FENCE = registerBlockWithItem("red_nether_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(RED_NETHER_BRICKS)));
    public static final Block CHISELED_RED_NETHER_BRICKS = registerBlockWithItem("chiseled_red_nether_bricks", new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS)));

    public static final Block END_STONE_STAIRS = registerBlockWithItem("end_stone_stairs", new StairsBlock(END_STONE.getDefaultState(), AbstractBlock.Settings.copy(END_STONE)));
    public static final Block END_STONE_SLAB = registerBlockWithItem("end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(END_STONE)));
    public static final Block END_STONE_WALL = registerBlockWithItem("end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(END_STONE)));

    public static final Block POLISHED_END_STONE = registerBlockWithItem("polished_end_stone", new Block(AbstractBlock.Settings.copy(END_STONE)));
    public static final Block POLISHED_END_STONE_STAIRS = registerBlockWithItem("polished_end_stone_stairs", new StairsBlock(POLISHED_END_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_END_STONE)));
    public static final Block POLISHED_END_STONE_SLAB = registerBlockWithItem("polished_end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)));
    public static final Block POLISHED_END_STONE_WALL = registerBlockWithItem("polished_end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)));

    public static final Block CRACKED_END_STONE_BRICKS = registerBlockWithItem("cracked_end_stone_bricks", new Block(AbstractBlock.Settings.copy(END_STONE_BRICKS)));

    public static final Block CHISELED_PURPUR = registerBlockWithItem("chiseled_purpur", new Block(AbstractBlock.Settings.copy(PURPUR_BLOCK)));

    public static final Block WHITE_STAINED_BRICKS = registerBlockWithItem("white_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.WHITE)));
    public static final Block WHITE_STAINED_BRICK_STAIRS = registerBlockWithItem("white_stained_brick_stairs", new StairsBlock(WHITE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));
    public static final Block WHITE_STAINED_BRICK_SLAB = registerBlockWithItem("white_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));
    public static final Block WHITE_STAINED_BRICK_WALL = registerBlockWithItem("white_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(WHITE_STAINED_BRICKS)));

    public static final Block LIGHT_GRAY_STAINED_BRICKS = registerBlockWithItem("light_gray_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_STAIRS = registerBlockWithItem("light_gray_stained_brick_stairs", new StairsBlock(LIGHT_GRAY_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_SLAB = registerBlockWithItem("light_gray_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));
    public static final Block LIGHT_GRAY_STAINED_BRICK_WALL = registerBlockWithItem("light_gray_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_STAINED_BRICKS)));

    public static final Block GRAY_STAINED_BRICKS = registerBlockWithItem("gray_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.GRAY)));
    public static final Block GRAY_STAINED_BRICK_STAIRS = registerBlockWithItem("gray_stained_brick_stairs", new StairsBlock(GRAY_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));
    public static final Block GRAY_STAINED_BRICK_SLAB = registerBlockWithItem("gray_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));
    public static final Block GRAY_STAINED_BRICK_WALL = registerBlockWithItem("gray_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GRAY_STAINED_BRICKS)));

    public static final Block BLACK_STAINED_BRICKS = registerBlockWithItem("black_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BLACK)));
    public static final Block BLACK_STAINED_BRICK_STAIRS = registerBlockWithItem("black_stained_brick_stairs", new StairsBlock(BLACK_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));
    public static final Block BLACK_STAINED_BRICK_SLAB = registerBlockWithItem("black_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));
    public static final Block BLACK_STAINED_BRICK_WALL = registerBlockWithItem("black_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLACK_STAINED_BRICKS)));

    public static final Block BROWN_STAINED_BRICKS = registerBlockWithItem("brown_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BROWN)));
    public static final Block BROWN_STAINED_BRICK_STAIRS = registerBlockWithItem("brown_stained_brick_stairs", new StairsBlock(BROWN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));
    public static final Block BROWN_STAINED_BRICK_SLAB = registerBlockWithItem("brown_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));
    public static final Block BROWN_STAINED_BRICK_WALL = registerBlockWithItem("brown_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BROWN_STAINED_BRICKS)));

    public static final Block RED_STAINED_BRICKS = registerBlockWithItem("red_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.RED)));
    public static final Block RED_STAINED_BRICK_STAIRS = registerBlockWithItem("red_stained_brick_stairs", new StairsBlock(RED_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));
    public static final Block RED_STAINED_BRICK_SLAB = registerBlockWithItem("red_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));
    public static final Block RED_STAINED_BRICK_WALL = registerBlockWithItem("red_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(RED_STAINED_BRICKS)));

    public static final Block ORANGE_STAINED_BRICKS = registerBlockWithItem("orange_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.ORANGE)));
    public static final Block ORANGE_STAINED_BRICK_STAIRS = registerBlockWithItem("orange_stained_brick_stairs", new StairsBlock(ORANGE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));
    public static final Block ORANGE_STAINED_BRICK_SLAB = registerBlockWithItem("orange_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));
    public static final Block ORANGE_STAINED_BRICK_WALL = registerBlockWithItem("orange_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(ORANGE_STAINED_BRICKS)));

    public static final Block YELLOW_STAINED_BRICKS = registerBlockWithItem("yellow_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.YELLOW)));
    public static final Block YELLOW_STAINED_BRICK_STAIRS = registerBlockWithItem("yellow_stained_brick_stairs", new StairsBlock(YELLOW_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));
    public static final Block YELLOW_STAINED_BRICK_SLAB = registerBlockWithItem("yellow_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));
    public static final Block YELLOW_STAINED_BRICK_WALL = registerBlockWithItem("yellow_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(YELLOW_STAINED_BRICKS)));

    public static final Block LIME_STAINED_BRICKS = registerBlockWithItem("lime_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIME)));
    public static final Block LIME_STAINED_BRICK_STAIRS = registerBlockWithItem("lime_stained_brick_stairs", new StairsBlock(LIME_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));
    public static final Block LIME_STAINED_BRICK_SLAB = registerBlockWithItem("lime_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));
    public static final Block LIME_STAINED_BRICK_WALL = registerBlockWithItem("lime_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIME_STAINED_BRICKS)));

    public static final Block GREEN_STAINED_BRICKS = registerBlockWithItem("green_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.GREEN)));
    public static final Block GREEN_STAINED_BRICK_STAIRS = registerBlockWithItem("green_stained_brick_stairs", new StairsBlock(GREEN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));
    public static final Block GREEN_STAINED_BRICK_SLAB = registerBlockWithItem("green_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));
    public static final Block GREEN_STAINED_BRICK_WALL = registerBlockWithItem("green_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_STAINED_BRICKS)));

    public static final Block CYAN_STAINED_BRICKS = registerBlockWithItem("cyan_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.CYAN)));
    public static final Block CYAN_STAINED_BRICK_STAIRS = registerBlockWithItem("cyan_stained_brick_stairs", new StairsBlock(CYAN_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));
    public static final Block CYAN_STAINED_BRICK_SLAB = registerBlockWithItem("cyan_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));
    public static final Block CYAN_STAINED_BRICK_WALL = registerBlockWithItem("cyan_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CYAN_STAINED_BRICKS)));

    public static final Block LIGHT_BLUE_STAINED_BRICKS = registerBlockWithItem("light_blue_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_STAIRS = registerBlockWithItem("light_blue_stained_brick_stairs", new StairsBlock(LIGHT_BLUE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_SLAB = registerBlockWithItem("light_blue_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));
    public static final Block LIGHT_BLUE_STAINED_BRICK_WALL = registerBlockWithItem("light_blue_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_STAINED_BRICKS)));

    public static final Block BLUE_STAINED_BRICKS = registerBlockWithItem("blue_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BLUE)));
    public static final Block BLUE_STAINED_BRICK_STAIRS = registerBlockWithItem("blue_stained_brick_stairs", new StairsBlock(BLUE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));
    public static final Block BLUE_STAINED_BRICK_SLAB = registerBlockWithItem("blue_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));
    public static final Block BLUE_STAINED_BRICK_WALL = registerBlockWithItem("blue_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLUE_STAINED_BRICKS)));

    public static final Block PURPLE_STAINED_BRICKS = registerBlockWithItem("purple_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.PURPLE)));
    public static final Block PURPLE_STAINED_BRICK_STAIRS = registerBlockWithItem("purple_stained_brick_stairs", new StairsBlock(PURPLE_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));
    public static final Block PURPLE_STAINED_BRICK_SLAB = registerBlockWithItem("purple_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));
    public static final Block PURPLE_STAINED_BRICK_WALL = registerBlockWithItem("purple_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PURPLE_STAINED_BRICKS)));

    public static final Block MAGENTA_STAINED_BRICKS = registerBlockWithItem("magenta_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.MAGENTA)));
    public static final Block MAGENTA_STAINED_BRICK_STAIRS = registerBlockWithItem("magenta_stained_brick_stairs", new StairsBlock(MAGENTA_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));
    public static final Block MAGENTA_STAINED_BRICK_SLAB = registerBlockWithItem("magenta_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));
    public static final Block MAGENTA_STAINED_BRICK_WALL = registerBlockWithItem("magenta_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(MAGENTA_STAINED_BRICKS)));

    public static final Block PINK_STAINED_BRICKS = registerBlockWithItem("pink_stained_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.PINK)));
    public static final Block PINK_STAINED_BRICK_STAIRS = registerBlockWithItem("pink_stained_brick_stairs", new StairsBlock(PINK_STAINED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));
    public static final Block PINK_STAINED_BRICK_SLAB = registerBlockWithItem("pink_stained_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));
    public static final Block PINK_STAINED_BRICK_WALL = registerBlockWithItem("pink_stained_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PINK_STAINED_BRICKS)));

    public static final Block TERRACOTTA_STAIRS = registerBlockWithItem("terracotta_stairs", new StairsBlock(TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(TERRACOTTA)));
    public static final Block TERRACOTTA_SLAB = registerBlockWithItem("terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(TERRACOTTA)));

    public static final Block WHITE_TERRACOTTA_STAIRS = registerBlockWithItem("white_terracotta_stairs", new StairsBlock(WHITE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(WHITE_TERRACOTTA)));
    public static final Block WHITE_TERRACOTTA_SLAB = registerBlockWithItem("white_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(WHITE_TERRACOTTA)));

    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = registerBlockWithItem("light_gray_terracotta_stairs", new StairsBlock(LIGHT_GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_GRAY_TERRACOTTA)));
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerBlockWithItem("light_gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_TERRACOTTA)));

    public static final Block GRAY_TERRACOTTA_STAIRS = registerBlockWithItem("gray_terracotta_stairs", new StairsBlock(GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(GRAY_TERRACOTTA)));
    public static final Block GRAY_TERRACOTTA_SLAB = registerBlockWithItem("gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(GRAY_TERRACOTTA)));

    public static final Block BLACK_TERRACOTTA_STAIRS = registerBlockWithItem("black_terracotta_stairs", new StairsBlock(BLACK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(BLACK_TERRACOTTA)));
    public static final Block BLACK_TERRACOTTA_SLAB = registerBlockWithItem("black_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(BLACK_TERRACOTTA)));

    public static final Block BROWN_TERRACOTTA_STAIRS = registerBlockWithItem("brown_terracotta_stairs", new StairsBlock(BROWN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(BROWN_TERRACOTTA)));
    public static final Block BROWN_TERRACOTTA_SLAB = registerBlockWithItem("brown_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(BROWN_TERRACOTTA)));

    public static final Block RED_TERRACOTTA_STAIRS = registerBlockWithItem("red_terracotta_stairs", new StairsBlock(RED_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(RED_TERRACOTTA)));
    public static final Block RED_TERRACOTTA_SLAB = registerBlockWithItem("red_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(RED_TERRACOTTA)));

    public static final Block ORANGE_TERRACOTTA_STAIRS = registerBlockWithItem("orange_terracotta_stairs", new StairsBlock(ORANGE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(ORANGE_TERRACOTTA)));
    public static final Block ORANGE_TERRACOTTA_SLAB = registerBlockWithItem("orange_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(ORANGE_TERRACOTTA)));

    public static final Block YELLOW_TERRACOTTA_STAIRS = registerBlockWithItem("yellow_terracotta_stairs", new StairsBlock(YELLOW_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(YELLOW_TERRACOTTA)));
    public static final Block YELLOW_TERRACOTTA_SLAB = registerBlockWithItem("yellow_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(YELLOW_TERRACOTTA)));

    public static final Block LIME_TERRACOTTA_STAIRS = registerBlockWithItem("lime_terracotta_stairs", new StairsBlock(LIME_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(LIME_TERRACOTTA)));
    public static final Block LIME_TERRACOTTA_SLAB = registerBlockWithItem("lime_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(LIME_TERRACOTTA)));

    public static final Block GREEN_TERRACOTTA_STAIRS = registerBlockWithItem("green_terracotta_stairs", new StairsBlock(GREEN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(GREEN_TERRACOTTA)));
    public static final Block GREEN_TERRACOTTA_SLAB = registerBlockWithItem("green_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_TERRACOTTA)));

    public static final Block CYAN_TERRACOTTA_STAIRS = registerBlockWithItem("cyan_terracotta_stairs", new StairsBlock(CYAN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(CYAN_TERRACOTTA)));
    public static final Block CYAN_TERRACOTTA_SLAB = registerBlockWithItem("cyan_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(CYAN_TERRACOTTA)));

    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = registerBlockWithItem("light_blue_terracotta_stairs", new StairsBlock(LIGHT_BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_BLUE_TERRACOTTA)));
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = registerBlockWithItem("light_blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_TERRACOTTA)));

    public static final Block BLUE_TERRACOTTA_STAIRS = registerBlockWithItem("blue_terracotta_stairs", new StairsBlock(BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(BLUE_TERRACOTTA)));
    public static final Block BLUE_TERRACOTTA_SLAB = registerBlockWithItem("blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(BLUE_TERRACOTTA)));

    public static final Block PURPLE_TERRACOTTA_STAIRS = registerBlockWithItem("purple_terracotta_stairs", new StairsBlock(PURPLE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(PURPLE_TERRACOTTA)));
    public static final Block PURPLE_TERRACOTTA_SLAB = registerBlockWithItem("purple_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(PURPLE_TERRACOTTA)));

    public static final Block MAGENTA_TERRACOTTA_STAIRS = registerBlockWithItem("magenta_terracotta_stairs", new StairsBlock(MAGENTA_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(MAGENTA_TERRACOTTA)));
    public static final Block MAGENTA_TERRACOTTA_SLAB = registerBlockWithItem("magenta_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(MAGENTA_TERRACOTTA)));

    public static final Block PINK_TERRACOTTA_STAIRS = registerBlockWithItem("pink_terracotta_stairs", new StairsBlock(PINK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(PINK_TERRACOTTA)));
    public static final Block PINK_TERRACOTTA_SLAB = registerBlockWithItem("pink_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(PINK_TERRACOTTA)));

    public static final Block STICK_BUNDLE = registerBlockWithItem("stick_bundle", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASS).sounds(BlockSoundGroup.MANGROVE_ROOTS).strength(0.4F)));

    public static final Block SOUL_JACK_O_LANTERN = registerBlockWithItem("soul_jack_o_lantern", new CarvedPumpkinBlock(AbstractBlock.Settings.copy(JACK_O_LANTERN).instrument(NoteBlockInstrument.DIDGERIDOO).luminance(state -> 10)));

    public static final Block FIREWORKS_TABLE = registerBlockWithItem("fireworks_table", new FireworksTableBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block KILN = registerBlockWithItem("kiln", new KilnBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).luminance(createLightLevelFromLitBlockState(13)).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F)));

    public static final Block GLASS_SLAB = registerBlockWithItem("glass_slab", new GlassSlabBlock(GLASS, AbstractBlock.Settings.copy(GLASS)));
    public static final Block BLACK_STAINED_GLASS_SLAB = registerBlockWithItem("black_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) BLACK_STAINED_GLASS));
    public static final Block BLUE_STAINED_GLASS_SLAB = registerBlockWithItem("blue_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) BLUE_STAINED_GLASS));
    public static final Block BROWN_STAINED_GLASS_SLAB = registerBlockWithItem("brown_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) BROWN_STAINED_GLASS));
    public static final Block CYAN_STAINED_GLASS_SLAB = registerBlockWithItem("cyan_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) CYAN_STAINED_GLASS));
    public static final Block GRAY_STAINED_GLASS_SLAB = registerBlockWithItem("gray_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) GRAY_STAINED_GLASS));
    public static final Block GREEN_STAINED_GLASS_SLAB = registerBlockWithItem("green_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) GREEN_STAINED_GLASS));
    public static final Block LIGHT_BLUE_STAINED_GLASS_SLAB = registerBlockWithItem("light_blue_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) LIGHT_BLUE_STAINED_GLASS));
    public static final Block LIGHT_GRAY_STAINED_GLASS_SLAB = registerBlockWithItem("light_gray_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) LIGHT_GRAY_STAINED_GLASS));
    public static final Block LIME_STAINED_GLASS_SLAB = registerBlockWithItem("lime_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) LIME_STAINED_GLASS));
    public static final Block MAGENTA_STAINED_GLASS_SLAB = registerBlockWithItem("magenta_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) MAGENTA_STAINED_GLASS));
    public static final Block ORANGE_STAINED_GLASS_SLAB = registerBlockWithItem("orange_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) ORANGE_STAINED_GLASS));
    public static final Block PINK_STAINED_GLASS_SLAB = registerBlockWithItem("pink_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) PINK_STAINED_GLASS));
    public static final Block PURPLE_STAINED_GLASS_SLAB = registerBlockWithItem("purple_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) PURPLE_STAINED_GLASS));
    public static final Block RED_STAINED_GLASS_SLAB = registerBlockWithItem("red_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) RED_STAINED_GLASS));
    public static final Block WHITE_STAINED_GLASS_SLAB = registerBlockWithItem("white_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) WHITE_STAINED_GLASS));
    public static final Block YELLOW_STAINED_GLASS_SLAB = registerBlockWithItem("yellow_stained_glass_slab", createStainedGlassSlabBlock((StainedGlassBlock) YELLOW_STAINED_GLASS));

    public static final Block GLASS_DOOR = registerBlockWithItem("glass_door", new GlassDoorBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F)));
    public static final Block BLACK_STAINED_GLASS_DOOR = registerBlockWithItem("black_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BLACK, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BLACK)));
    public static final Block BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("blue_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BLUE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BLUE)));
    public static final Block BROWN_STAINED_GLASS_DOOR = registerBlockWithItem("brown_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BROWN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BROWN)));
    public static final Block CYAN_STAINED_GLASS_DOOR = registerBlockWithItem("cyan_stained_glass_door", new StainedGlassDoorBlock(DyeColor.CYAN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.CYAN)));
    public static final Block GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("gray_stained_glass_door", new StainedGlassDoorBlock(DyeColor.GRAY, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.GRAY)));
    public static final Block GREEN_STAINED_GLASS_DOOR = registerBlockWithItem("green_stained_glass_door", new StainedGlassDoorBlock(DyeColor.GREEN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.GREEN)));
    public static final Block LIGHT_BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("light_blue_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIGHT_BLUE)));
    public static final Block LIGHT_GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("light_gray_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIGHT_GRAY)));
    public static final Block LIME_STAINED_GLASS_DOOR = registerBlockWithItem("lime_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIME, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIME)));
    public static final Block MAGENTA_STAINED_GLASS_DOOR = registerBlockWithItem("magenta_stained_glass_door", new StainedGlassDoorBlock(DyeColor.MAGENTA, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.MAGENTA)));
    public static final Block ORANGE_STAINED_GLASS_DOOR = registerBlockWithItem("orange_stained_glass_door", new StainedGlassDoorBlock(DyeColor.ORANGE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.ORANGE)));
    public static final Block PINK_STAINED_GLASS_DOOR = registerBlockWithItem("pink_stained_glass_door", new StainedGlassDoorBlock(DyeColor.PINK, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.PINK)));
    public static final Block PURPLE_STAINED_GLASS_DOOR = registerBlockWithItem("purple_stained_glass_door", new StainedGlassDoorBlock(DyeColor.PURPLE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.PURPLE)));
    public static final Block RED_STAINED_GLASS_DOOR = registerBlockWithItem("red_stained_glass_door", new StainedGlassDoorBlock(DyeColor.RED, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.RED)));
    public static final Block WHITE_STAINED_GLASS_DOOR = registerBlockWithItem("white_stained_glass_door", new StainedGlassDoorBlock(DyeColor.WHITE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.WHITE)));
    public static final Block YELLOW_STAINED_GLASS_DOOR = registerBlockWithItem("yellow_stained_glass_door", new StainedGlassDoorBlock(DyeColor.YELLOW, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.YELLOW)));

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

    public static final Block POTION_CAULDRON = register("potion_cauldron", new PotionCauldronBlock(AbstractBlock.Settings.copy(CAULDRON)));

    private static Block createStainedGlassSlabBlock(StainedGlassBlock block) {
        return new StainedGlassSlabBlock(block.getColor(), block, AbstractBlock.Settings.copy(block));
    }

    public static Block registerBlockWithItem(String id, Block block) {
        Block block2 = register(id, block);
        KaleidoscopeItems.register(block2);
        return block2;
    }

    public static Block register(String id, Block block) {
        return Registry.register(Registries.BLOCK, Kaleidoscope.of(id), block);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().remove(COAL_BLOCK);
        FlammableBlockRegistry.getDefaultInstance().add(STICK_BUNDLE, 5, 5);
    }

    public static void registerMossyPairs() {
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_COBBLESTONE, COBBLESTONE);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_COBBLESTONE_SLAB, COBBLESTONE_SLAB);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_COBBLESTONE_STAIRS, COBBLESTONE_STAIRS);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_COBBLESTONE_WALL, COBBLESTONE_WALL);

        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_STONE_BRICKS, STONE_BRICKS);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_STONE_BRICK_SLAB, STONE_BRICK_SLAB);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_STONE_BRICK_STAIRS, STONE_BRICK_STAIRS);
        MossyBlocksRegistry.registerMossyBlockPair(MOSSY_STONE_BRICK_WALL, STONE_BRICK_WALL);

        MossyBlocksRegistry.registerMossyBlockPair(INFESTED_MOSSY_STONE_BRICKS, INFESTED_STONE_BRICKS);
    }

    public static void registerOxidizablePairs() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(CUT_COPPER_WALL, EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_CUT_COPPER_WALL, WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_CUT_COPPER_WALL, OXIDIZED_CUT_COPPER_WALL);

        OxidizableBlocksRegistry.registerWaxableBlockPair(CUT_COPPER_WALL, WAXED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_CUT_COPPER_WALL, WAXED_EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_CUT_COPPER_WALL, WAXED_WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_CUT_COPPER_WALL, WAXED_OXIDIZED_CUT_COPPER_WALL);

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

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> (Boolean) state.get(Properties.LIT) ? litLevel : 0;
    }
}
