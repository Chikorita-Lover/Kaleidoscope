package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {
    public static final Block CALCITE_SLAB = registerBlockWithItem("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_STAIRS = registerBlockWithItem("calcite_stairs", new StairsBlock(CALCITE.getDefaultState(), AbstractBlock.Settings.copy(CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CALCITE_WALL = registerBlockWithItem("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(CALCITE)), ItemGroup.BUILDING_BLOCKS);

    public static final Block POLISHED_CALCITE = registerBlockWithItem("polished_calcite", new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5F, 6F).sounds(BlockSoundGroup.CALCITE).requiresTool()), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = registerBlockWithItem("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = registerBlockWithItem("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE)), ItemGroup.BUILDING_BLOCKS);

    public static final Block TUFF_SLAB = registerBlockWithItem("tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_STAIRS = registerBlockWithItem("tuff_stairs", new StairsBlock(TUFF.getDefaultState(), AbstractBlock.Settings.copy(TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block TUFF_WALL = registerBlockWithItem("tuff_wall", new WallBlock(AbstractBlock.Settings.copy(TUFF)), ItemGroup.BUILDING_BLOCKS);

    public static final Block POLISHED_TUFF = registerBlockWithItem("polished_tuff", new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_GRAY).requiresTool().sounds(BlockSoundGroup.TUFF).strength(1.5F, 6F)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_SLAB = registerBlockWithItem("polished_tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_TUFF_STAIRS = registerBlockWithItem("polished_tuff_stairs", new StairsBlock(POLISHED_TUFF.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_TUFF)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CUT_COPPER_WALL = registerBlockWithItem("cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block EXPOSED_CUT_COPPER_WALL = registerBlockWithItem("exposed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WEATHERED_CUT_COPPER_WALL = registerBlockWithItem("weathered_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block OXIDIZED_CUT_COPPER_WALL = registerBlockWithItem("oxidized_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WAXED_CUT_COPPER_WALL = registerBlockWithItem("waxed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_EXPOSED_CUT_COPPER_WALL = registerBlockWithItem("waxed_exposed_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_WEATHERED_CUT_COPPER_WALL = registerBlockWithItem("waxed_weathered_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_WALL = registerBlockWithItem("waxed_oxidized_cut_copper_wall", new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_CUT_COPPER)), ItemGroup.BUILDING_BLOCKS);

    public static final Block BLACK_PAINTED_BRICKS = registerBlockWithItem("black_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BLACK)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_PAINTED_BRICK_SLAB = registerBlockWithItem("black_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLACK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_PAINTED_BRICK_STAIRS = registerBlockWithItem("black_painted_brick_stairs", new StairsBlock(BLACK_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLACK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_PAINTED_BRICK_WALL = registerBlockWithItem("black_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLACK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block BLUE_PAINTED_BRICKS = registerBlockWithItem("blue_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BLUE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_PAINTED_BRICK_SLAB = registerBlockWithItem("blue_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_PAINTED_BRICK_STAIRS = registerBlockWithItem("blue_painted_brick_stairs", new StairsBlock(BLUE_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_PAINTED_BRICK_WALL = registerBlockWithItem("blue_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block BROWN_PAINTED_BRICKS = registerBlockWithItem("brown_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.BROWN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_PAINTED_BRICK_SLAB = registerBlockWithItem("brown_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(BROWN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_PAINTED_BRICK_STAIRS = registerBlockWithItem("brown_painted_brick_stairs", new StairsBlock(BROWN_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(BROWN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_PAINTED_BRICK_WALL = registerBlockWithItem("brown_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(BROWN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CYAN_PAINTED_BRICKS = registerBlockWithItem("cyan_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.CYAN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_PAINTED_BRICK_SLAB = registerBlockWithItem("cyan_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CYAN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_PAINTED_BRICK_STAIRS = registerBlockWithItem("cyan_painted_brick_stairs", new StairsBlock(CYAN_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CYAN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_PAINTED_BRICK_WALL = registerBlockWithItem("cyan_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CYAN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block GRAY_PAINTED_BRICKS = registerBlockWithItem("gray_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.GRAY)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_PAINTED_BRICK_SLAB = registerBlockWithItem("gray_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_PAINTED_BRICK_STAIRS = registerBlockWithItem("gray_painted_brick_stairs", new StairsBlock(GRAY_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_PAINTED_BRICK_WALL = registerBlockWithItem("gray_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block GREEN_PAINTED_BRICKS = registerBlockWithItem("green_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.GREEN)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_PAINTED_BRICK_SLAB = registerBlockWithItem("green_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(GREEN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_PAINTED_BRICK_STAIRS = registerBlockWithItem("green_painted_brick_stairs", new StairsBlock(GREEN_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(GREEN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_PAINTED_BRICK_WALL = registerBlockWithItem("green_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(GREEN_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block LIGHT_BLUE_PAINTED_BRICKS = registerBlockWithItem("light_blue_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIGHT_BLUE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_PAINTED_BRICK_SLAB = registerBlockWithItem("light_blue_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_PAINTED_BRICK_STAIRS = registerBlockWithItem("light_blue_painted_brick_stairs", new StairsBlock(LIGHT_BLUE_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_PAINTED_BRICK_WALL = registerBlockWithItem("light_blue_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_BLUE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block LIGHT_GRAY_PAINTED_BRICKS = registerBlockWithItem("light_gray_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIGHT_GRAY)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_PAINTED_BRICK_SLAB = registerBlockWithItem("light_gray_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_PAINTED_BRICK_STAIRS = registerBlockWithItem("light_gray_painted_brick_stairs", new StairsBlock(LIGHT_GRAY_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIGHT_GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_PAINTED_BRICK_WALL = registerBlockWithItem("light_gray_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIGHT_GRAY_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block LIME_PAINTED_BRICKS = registerBlockWithItem("lime_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.LIME)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_PAINTED_BRICK_SLAB = registerBlockWithItem("lime_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(LIME_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_PAINTED_BRICK_STAIRS = registerBlockWithItem("lime_painted_brick_stairs", new StairsBlock(LIME_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(LIME_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_PAINTED_BRICK_WALL = registerBlockWithItem("lime_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(LIME_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block MAGENTA_PAINTED_BRICKS = registerBlockWithItem("magenta_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.MAGENTA)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_PAINTED_BRICK_SLAB = registerBlockWithItem("magenta_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(MAGENTA_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_PAINTED_BRICK_STAIRS = registerBlockWithItem("magenta_painted_brick_stairs", new StairsBlock(MAGENTA_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MAGENTA_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_PAINTED_BRICK_WALL = registerBlockWithItem("magenta_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(MAGENTA_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block ORANGE_PAINTED_BRICKS = registerBlockWithItem("orange_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.ORANGE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_PAINTED_BRICK_SLAB = registerBlockWithItem("orange_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(ORANGE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_PAINTED_BRICK_STAIRS = registerBlockWithItem("orange_painted_brick_stairs", new StairsBlock(ORANGE_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(ORANGE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_PAINTED_BRICK_WALL = registerBlockWithItem("orange_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(ORANGE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block PINK_PAINTED_BRICKS = registerBlockWithItem("pink_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.PINK)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_PAINTED_BRICK_SLAB = registerBlockWithItem("pink_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PINK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_PAINTED_BRICK_STAIRS = registerBlockWithItem("pink_painted_brick_stairs", new StairsBlock(PINK_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PINK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_PAINTED_BRICK_WALL = registerBlockWithItem("pink_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PINK_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block PURPLE_PAINTED_BRICKS = registerBlockWithItem("purple_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.PURPLE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_PAINTED_BRICK_SLAB = registerBlockWithItem("purple_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PURPLE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_PAINTED_BRICK_STAIRS = registerBlockWithItem("purple_painted_brick_stairs", new StairsBlock(PURPLE_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PURPLE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_PAINTED_BRICK_WALL = registerBlockWithItem("purple_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PURPLE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block RED_PAINTED_BRICKS = registerBlockWithItem("red_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.RED)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_PAINTED_BRICK_SLAB = registerBlockWithItem("red_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(RED_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_PAINTED_BRICK_STAIRS = registerBlockWithItem("red_painted_brick_stairs", new StairsBlock(RED_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(RED_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_PAINTED_BRICK_WALL = registerBlockWithItem("red_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(RED_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block WHITE_PAINTED_BRICKS = registerBlockWithItem("white_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.WHITE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WHITE_PAINTED_BRICK_SLAB = registerBlockWithItem("white_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(WHITE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WHITE_PAINTED_BRICK_STAIRS = registerBlockWithItem("white_painted_brick_stairs", new StairsBlock(WHITE_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(WHITE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WHITE_PAINTED_BRICK_WALL = registerBlockWithItem("white_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(WHITE_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block YELLOW_PAINTED_BRICKS = registerBlockWithItem("yellow_painted_bricks", new Block(AbstractBlock.Settings.copy(BRICKS).mapColor(MapColor.YELLOW)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_PAINTED_BRICK_SLAB = registerBlockWithItem("yellow_painted_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(YELLOW_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_PAINTED_BRICK_STAIRS = registerBlockWithItem("yellow_painted_brick_stairs", new StairsBlock(YELLOW_PAINTED_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(YELLOW_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_PAINTED_BRICK_WALL = registerBlockWithItem("yellow_painted_brick_wall", new WallBlock(AbstractBlock.Settings.copy(YELLOW_PAINTED_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlockWithItem("cracked_red_nether_bricks", new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CHISELED_RED_NETHER_BRICKS = registerBlockWithItem("chiseled_red_nether_bricks", new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS)), ItemGroup.BUILDING_BLOCKS);

    public static final Block POLISHED_END_STONE = registerBlockWithItem("polished_end_stone", new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 9.0F)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_END_STONE_SLAB = registerBlockWithItem("polished_end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block POLISHED_END_STONE_STAIRS = registerBlockWithItem("polished_end_stone_stairs", new StairsBlock(POLISHED_END_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_END_STONE)), ItemGroup.BUILDING_BLOCKS);

    public static final Block CHISELED_PURPUR = registerBlockWithItem("chiseled_purpur", new Block(AbstractBlock.Settings.copy(PURPUR_BLOCK)), ItemGroup.BUILDING_BLOCKS);

    public static final Block SMOOTH_BASALT_SLAB = registerBlockWithItem("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_BASALT)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SMOOTH_BASALT_STAIRS = registerBlockWithItem("smooth_basalt_stairs", new StairsBlock(SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_BASALT)), ItemGroup.BUILDING_BLOCKS);
    public static final Block SMOOTH_BASALT_WALL = registerBlockWithItem("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(SMOOTH_BASALT)), ItemGroup.BUILDING_BLOCKS);

    public static final Block STICK_BUNDLE = registerBlockWithItem("stick_bundle", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(ModBlockSoundGroup.STICK_BUNDLE).strength(2.0F)), ItemGroup.DECORATIONS);

    public static final Block RED_NETHER_BRICK_FENCE = registerBlockWithItem("red_nether_brick_fence", new FenceBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS)), ItemGroup.DECORATIONS);

    public static final Block KILN = registerBlockWithItem("kiln", new KilnBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F).luminance(createLightLevelFromLitBlockState(13))), ItemGroup.DECORATIONS);

    public static final Block GLASS_DOOR = registerBlockWithItem("glass_door", new GlassDoorBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F)), ItemGroup.REDSTONE);
    public static final Block BLACK_STAINED_GLASS_DOOR = registerBlockWithItem("black_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BLACK, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BLACK)), ItemGroup.REDSTONE);
    public static final Block BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("blue_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BLUE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BLUE)), ItemGroup.REDSTONE);
    public static final Block BROWN_STAINED_GLASS_DOOR = registerBlockWithItem("brown_stained_glass_door", new StainedGlassDoorBlock(DyeColor.BROWN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.BROWN)), ItemGroup.REDSTONE);
    public static final Block CYAN_STAINED_GLASS_DOOR = registerBlockWithItem("cyan_stained_glass_door", new StainedGlassDoorBlock(DyeColor.CYAN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.CYAN)), ItemGroup.REDSTONE);
    public static final Block GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("gray_stained_glass_door", new StainedGlassDoorBlock(DyeColor.GRAY, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.GRAY)), ItemGroup.REDSTONE);
    public static final Block GREEN_STAINED_GLASS_DOOR = registerBlockWithItem("green_stained_glass_door", new StainedGlassDoorBlock(DyeColor.GREEN, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.GREEN)), ItemGroup.REDSTONE);
    public static final Block LIGHT_BLUE_STAINED_GLASS_DOOR = registerBlockWithItem("light_blue_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIGHT_BLUE)), ItemGroup.REDSTONE);
    public static final Block LIGHT_GRAY_STAINED_GLASS_DOOR = registerBlockWithItem("light_gray_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIGHT_GRAY)), ItemGroup.REDSTONE);
    public static final Block LIME_STAINED_GLASS_DOOR = registerBlockWithItem("lime_stained_glass_door", new StainedGlassDoorBlock(DyeColor.LIME, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.LIME)), ItemGroup.REDSTONE);
    public static final Block MAGENTA_STAINED_GLASS_DOOR = registerBlockWithItem("magenta_stained_glass_door", new StainedGlassDoorBlock(DyeColor.MAGENTA, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.MAGENTA)), ItemGroup.REDSTONE);
    public static final Block ORANGE_STAINED_GLASS_DOOR = registerBlockWithItem("orange_stained_glass_door", new StainedGlassDoorBlock(DyeColor.ORANGE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.ORANGE)), ItemGroup.REDSTONE);
    public static final Block PINK_STAINED_GLASS_DOOR = registerBlockWithItem("pink_stained_glass_door", new StainedGlassDoorBlock(DyeColor.PINK, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.PINK)), ItemGroup.REDSTONE);
    public static final Block PURPLE_STAINED_GLASS_DOOR = registerBlockWithItem("purple_stained_glass_door", new StainedGlassDoorBlock(DyeColor.PURPLE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.PURPLE)), ItemGroup.REDSTONE);
    public static final Block RED_STAINED_GLASS_DOOR = registerBlockWithItem("red_stained_glass_door", new StainedGlassDoorBlock(DyeColor.RED, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.RED)), ItemGroup.REDSTONE);
    public static final Block WHITE_STAINED_GLASS_DOOR = registerBlockWithItem("white_stained_glass_door", new StainedGlassDoorBlock(DyeColor.WHITE, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.WHITE)), ItemGroup.REDSTONE);
    public static final Block YELLOW_STAINED_GLASS_DOOR = registerBlockWithItem("yellow_stained_glass_door", new StainedGlassDoorBlock(DyeColor.YELLOW, AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(MapColor.YELLOW)), ItemGroup.REDSTONE);

    public static final Block GLASS_TRAPDOOR = registerBlockWithItem("glass_trapdoor", new GlassTrapdoorBlock(AbstractBlock.Settings.of(Material.GLASS, MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F)), ItemGroup.REDSTONE);
    public static final Block BLACK_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("black_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLACK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLACK)), ItemGroup.REDSTONE);
    public static final Block BLUE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLUE)), ItemGroup.REDSTONE);
    public static final Block BROWN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("brown_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BROWN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BROWN)), ItemGroup.REDSTONE);
    public static final Block CYAN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("cyan_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.CYAN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.CYAN)), ItemGroup.REDSTONE);
    public static final Block GRAY_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GRAY)), ItemGroup.REDSTONE);
    public static final Block GREEN_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("green_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GREEN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GREEN)), ItemGroup.REDSTONE);
    public static final Block LIGHT_BLUE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("light_blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_BLUE)), ItemGroup.REDSTONE);
    public static final Block LIGHT_GRAY_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("light_gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_GRAY)), ItemGroup.REDSTONE);
    public static final Block LIME_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("lime_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIME, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIME)), ItemGroup.REDSTONE);
    public static final Block MAGENTA_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("magenta_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.MAGENTA, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.MAGENTA)), ItemGroup.REDSTONE);
    public static final Block ORANGE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("orange_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.ORANGE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.ORANGE)), ItemGroup.REDSTONE);
    public static final Block PINK_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("pink_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PINK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PINK)), ItemGroup.REDSTONE);
    public static final Block PURPLE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("purple_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PURPLE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PURPLE)), ItemGroup.REDSTONE);
    public static final Block RED_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("red_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.RED, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.RED)), ItemGroup.REDSTONE);
    public static final Block WHITE_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("white_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.WHITE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.WHITE)), ItemGroup.REDSTONE);
    public static final Block YELLOW_STAINED_GLASS_TRAPDOOR = registerBlockWithItem("yellow_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.YELLOW, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.YELLOW)), ItemGroup.REDSTONE);

    public static final Block COPPER_DOOR = registerBlockWithItem("copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block EXPOSED_COPPER_DOOR = registerBlockWithItem("exposed_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WEATHERED_COPPER_DOOR = registerBlockWithItem("weathered_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block OXIDIZED_COPPER_DOOR = registerBlockWithItem("oxidized_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);

    public static final Block WAXED_COPPER_DOOR = registerBlockWithItem("waxed_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_EXPOSED_COPPER_DOOR = registerBlockWithItem("waxed_exposed_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_WEATHERED_COPPER_DOOR = registerBlockWithItem("waxed_weathered_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_OXIDIZED_COPPER_DOOR = registerBlockWithItem("waxed_oxidized_copper_door", new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);

    public static final Block COPPER_TRAPDOOR = registerBlockWithItem("copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block EXPOSED_COPPER_TRAPDOOR = registerBlockWithItem("exposed_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WEATHERED_COPPER_TRAPDOOR = registerBlockWithItem("weathered_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block OXIDIZED_COPPER_TRAPDOOR = registerBlockWithItem("oxidized_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);

    public static final Block WAXED_COPPER_TRAPDOOR = registerBlockWithItem("waxed_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_EXPOSED_COPPER_TRAPDOOR = registerBlockWithItem("waxed_exposed_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_WEATHERED_COPPER_TRAPDOOR = registerBlockWithItem("waxed_weathered_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);
    public static final Block WAXED_OXIDIZED_COPPER_TRAPDOOR = registerBlockWithItem("waxed_oxidized_copper_trapdoor", new CopperTrapdoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).nonOpaque().requiresTool().sounds(BlockSoundGroup.COPPER).strength(3.0F)), ItemGroup.REDSTONE);

    public static final Block POTION_CAULDRON = register("potion_cauldron", new PotionCauldronBlock(AbstractBlock.Settings.copy(CAULDRON)));

    public static Block registerBlockWithItem(String id, Block block, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, id), new BlockItem(block, new Item.Settings().group(group)));
        return register(id, block);
    }

    public static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, id), block);
    }

    public static void register() {
    }

    public static void registerFlammableBlocks() {
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
        
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_DOOR, EXPOSED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_DOOR, WEATHERED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_DOOR, OXIDIZED_COPPER_DOOR);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_DOOR, WAXED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_DOOR, WAXED_EXPOSED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_DOOR, WAXED_WEATHERED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_DOOR, WAXED_OXIDIZED_COPPER_DOOR);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_TRAPDOOR, EXPOSED_COPPER_TRAPDOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_TRAPDOOR, WEATHERED_COPPER_TRAPDOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_TRAPDOOR, OXIDIZED_COPPER_TRAPDOOR);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_TRAPDOOR, WAXED_COPPER_TRAPDOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_TRAPDOOR, WAXED_EXPOSED_COPPER_TRAPDOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_TRAPDOOR, WAXED_WEATHERED_COPPER_TRAPDOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_TRAPDOOR, WAXED_OXIDIZED_COPPER_TRAPDOOR);
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> (Boolean)state.get(Properties.LIT) ? litLevel : 0;
    }
}
