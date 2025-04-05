package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.chicory.api.registry.BlockRegistry;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class KaleidoscopeBlocks {
    public static final Block POLISHED_GRANITE_WALL = registerWithItem("polished_granite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)), KaleidoscopeConfig.STONE_BLOCKS);
    public static final Block POLISHED_DIORITE_WALL = registerWithItem("polished_diorite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)), KaleidoscopeConfig.STONE_BLOCKS);
    public static final Block POLISHED_ANDESITE_WALL = registerWithItem("polished_andesite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)), KaleidoscopeConfig.STONE_BLOCKS);

    public static final Block CALCITE_SLAB = registerWithItem("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block CALCITE_STAIRS = registerWithItem("calcite_stairs", new StairsBlock(Blocks.CALCITE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block CALCITE_WALL = registerWithItem("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block POLISHED_CALCITE = registerWithItem("polished_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = registerWithItem("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = registerWithItem("polished_calcite_stairs", new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_WALL = registerWithItem("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block SMOOTH_CALCITE = registerWithItem("smooth_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE).strength(1.9F, 6.0F)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block SMOOTH_CALCITE_SLAB = registerWithItem("smooth_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block SMOOTH_CALCITE_STAIRS = registerWithItem("smooth_calcite_stairs", new StairsBlock(SMOOTH_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_CALCITE)), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block CRACKED_TUFF_BRICKS = registerWithItem("cracked_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF_BRICKS)), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block BRICK_MOSAIC = registerWithItem("brick_mosaic", new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)), KaleidoscopeConfig.BRICK_MOSAICS);
    public static final Block BRICK_MOSAIC_STAIRS = registerWithItem("brick_mosaic_stairs", new StairsBlock(BRICK_MOSAIC.getDefaultState(), AbstractBlock.Settings.copy(BRICK_MOSAIC)), KaleidoscopeConfig.BRICK_MOSAICS);
    public static final Block BRICK_MOSAIC_SLAB = registerWithItem("brick_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(BRICK_MOSAIC)), KaleidoscopeConfig.BRICK_MOSAICS);

    public static final Block PACKED_MUD_STAIRS = registerWithItem("packed_mud_stairs", new StairsBlock(Blocks.PACKED_MUD.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PACKED_MUD)), KaleidoscopeConfig.MUD_BLOCKS);
    public static final Block PACKED_MUD_SLAB = registerWithItem("packed_mud_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)), KaleidoscopeConfig.MUD_BLOCKS);
    public static final Block PACKED_MUD_WALL = registerWithItem("packed_mud_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD)), KaleidoscopeConfig.MUD_BLOCKS);

    public static final Block CRACKED_MUD_BRICKS = registerWithItem("cracked_mud_bricks", new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS)), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block SMOOTH_BASALT_STAIRS = registerWithItem("smooth_basalt_stairs", new StairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)), KaleidoscopeConfig.BASALT_BLOCKS);
    public static final Block SMOOTH_BASALT_SLAB = registerWithItem("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)), KaleidoscopeConfig.BASALT_BLOCKS);
    public static final Block SMOOTH_BASALT_WALL = registerWithItem("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)), KaleidoscopeConfig.BASALT_BLOCKS);

    public static final Block CHARCOAL_BLOCK = registerWithItem("charcoal_block", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).strength(3.0F, 6.0F)), KaleidoscopeConfig.CHARCOAL_BLOCKS);

    public static final Block QUARTZ_BRICK_STAIRS = registerWithItem("quartz_brick_stairs", new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)), KaleidoscopeConfig.QUARTZ_BLOCKS);
    public static final Block QUARTZ_BRICK_SLAB = registerWithItem("quartz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)), KaleidoscopeConfig.QUARTZ_BLOCKS);
    public static final Block QUARTZ_BRICK_WALL = registerWithItem("quartz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS)), KaleidoscopeConfig.QUARTZ_BLOCKS);

    public static final Block SMOOTH_COPPER = registerWithItem("smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block SMOOTH_COPPER_STAIRS = registerWithItem("smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block SMOOTH_COPPER_SLAB = registerWithItem("smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block EXPOSED_SMOOTH_COPPER = registerWithItem("exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.EXPOSED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block EXPOSED_SMOOTH_COPPER_STAIRS = registerWithItem("exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block EXPOSED_SMOOTH_COPPER_SLAB = registerWithItem("exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WEATHERED_SMOOTH_COPPER = registerWithItem("weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.WEATHERED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WEATHERED_SMOOTH_COPPER_STAIRS = registerWithItem("weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WEATHERED_SMOOTH_COPPER_SLAB = registerWithItem("weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block OXIDIZED_SMOOTH_COPPER = registerWithItem("oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.OXIDIZED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block OXIDIZED_SMOOTH_COPPER_STAIRS = registerWithItem("oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block OXIDIZED_SMOOTH_COPPER_SLAB = registerWithItem("oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_SMOOTH_COPPER = registerWithItem("waxed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.WAXED_COPPER_BLOCK)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, WAXED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_EXPOSED_SMOOTH_COPPER = registerWithItem("waxed_exposed_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.WAXED_EXPOSED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_exposed_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, WAXED_EXPOSED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_exposed_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_WEATHERED_SMOOTH_COPPER = registerWithItem("waxed_weathered_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.WAXED_WEATHERED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_weathered_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WAXED_WEATHERED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_weathered_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER = registerWithItem("waxed_oxidized_smooth_copper", new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.WAXED_OXIDIZED_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_oxidized_smooth_copper_stairs", new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, WAXED_OXIDIZED_SMOOTH_COPPER.getDefaultState(), AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_oxidized_smooth_copper_slab", new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER)), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block CRACKED_RED_NETHER_BRICKS = registerWithItem("cracked_red_nether_bricks", new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);
    public static final Block RED_NETHER_BRICK_FENCE = registerWithItem("red_nether_brick_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));

    public static final Block END_STONE_STAIRS = registerWithItem("end_stone_stairs", new StairsBlock(Blocks.END_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block END_STONE_SLAB = registerWithItem("end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block END_STONE_WALL = registerWithItem("end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);

    public static final Block POLISHED_END_STONE = registerWithItem("polished_end_stone", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_STAIRS = registerWithItem("polished_end_stone_stairs", new StairsBlock(POLISHED_END_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_SLAB = registerWithItem("polished_end_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_WALL = registerWithItem("polished_end_stone_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE)), KaleidoscopeConfig.END_STONE_BLOCKS);

    public static final Block CRACKED_END_STONE_BRICKS = registerWithItem("cracked_end_stone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS)), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block TERRACOTTA_STAIRS = registerWithItem("terracotta_stairs", new StairsBlock(Blocks.TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block TERRACOTTA_SLAB = registerWithItem("terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block WHITE_TERRACOTTA_STAIRS = registerWithItem("white_terracotta_stairs", new StairsBlock(Blocks.WHITE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block WHITE_TERRACOTTA_SLAB = registerWithItem("white_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = registerWithItem("light_gray_terracotta_stairs", new StairsBlock(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerWithItem("light_gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block GRAY_TERRACOTTA_STAIRS = registerWithItem("gray_terracotta_stairs", new StairsBlock(Blocks.GRAY_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block GRAY_TERRACOTTA_SLAB = registerWithItem("gray_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BLACK_TERRACOTTA_STAIRS = registerWithItem("black_terracotta_stairs", new StairsBlock(Blocks.BLACK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BLACK_TERRACOTTA_SLAB = registerWithItem("black_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BROWN_TERRACOTTA_STAIRS = registerWithItem("brown_terracotta_stairs", new StairsBlock(Blocks.BROWN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BROWN_TERRACOTTA_SLAB = registerWithItem("brown_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block RED_TERRACOTTA_STAIRS = registerWithItem("red_terracotta_stairs", new StairsBlock(Blocks.RED_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block RED_TERRACOTTA_SLAB = registerWithItem("red_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block ORANGE_TERRACOTTA_STAIRS = registerWithItem("orange_terracotta_stairs", new StairsBlock(Blocks.ORANGE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block ORANGE_TERRACOTTA_SLAB = registerWithItem("orange_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block YELLOW_TERRACOTTA_STAIRS = registerWithItem("yellow_terracotta_stairs", new StairsBlock(Blocks.YELLOW_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block YELLOW_TERRACOTTA_SLAB = registerWithItem("yellow_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIME_TERRACOTTA_STAIRS = registerWithItem("lime_terracotta_stairs", new StairsBlock(Blocks.LIME_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIME_TERRACOTTA_SLAB = registerWithItem("lime_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block GREEN_TERRACOTTA_STAIRS = registerWithItem("green_terracotta_stairs", new StairsBlock(Blocks.GREEN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block GREEN_TERRACOTTA_SLAB = registerWithItem("green_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block CYAN_TERRACOTTA_STAIRS = registerWithItem("cyan_terracotta_stairs", new StairsBlock(Blocks.CYAN_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block CYAN_TERRACOTTA_SLAB = registerWithItem("cyan_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = registerWithItem("light_blue_terracotta_stairs", new StairsBlock(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = registerWithItem("light_blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BLUE_TERRACOTTA_STAIRS = registerWithItem("blue_terracotta_stairs", new StairsBlock(Blocks.BLUE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BLUE_TERRACOTTA_SLAB = registerWithItem("blue_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block PURPLE_TERRACOTTA_STAIRS = registerWithItem("purple_terracotta_stairs", new StairsBlock(Blocks.PURPLE_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block PURPLE_TERRACOTTA_SLAB = registerWithItem("purple_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block MAGENTA_TERRACOTTA_STAIRS = registerWithItem("magenta_terracotta_stairs", new StairsBlock(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block MAGENTA_TERRACOTTA_SLAB = registerWithItem("magenta_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block PINK_TERRACOTTA_STAIRS = registerWithItem("pink_terracotta_stairs", new StairsBlock(Blocks.PINK_TERRACOTTA.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block PINK_TERRACOTTA_SLAB = registerWithItem("pink_terracotta_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA)), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block STICK_BLOCK = registerWithItem("stick_block", new PillarBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASS).sounds(BlockSoundGroup.MANGROVE_ROOTS).strength(0.4F).burnable()), KaleidoscopeConfig.STICK_BLOCKS);

    public static final Block SOUL_JACK_O_LANTERN = registerWithItem("soul_jack_o_lantern", new CarvedPumpkinBlock(AbstractBlock.Settings.copy(Blocks.JACK_O_LANTERN).instrument(NoteBlockInstrument.DIDGERIDOO).luminance(state -> 10)), KaleidoscopeConfig.SOUL_JACK_O_LANTERNS);

    public static final Block FIREWORKS_TABLE = registerWithItem("fireworks_table", new FireworksTableBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()), KaleidoscopeConfig.FIREWORK_IMPROVEMENTS);
    public static final Block KILN = registerWithItem("kiln", new KilnBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).luminance(state -> state.get(Properties.LIT) ? 13 : 0).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F)), KaleidoscopeConfig.KILNS);

    public static final Block GLASS_DOOR = registerWithItem("glass_door", createGlassDoorBlock(null), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block WHITE_STAINED_GLASS_DOOR = registerWithItem("white_stained_glass_door", createGlassDoorBlock(DyeColor.WHITE), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIGHT_GRAY_STAINED_GLASS_DOOR = registerWithItem("light_gray_stained_glass_door", createGlassDoorBlock(DyeColor.LIGHT_GRAY), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block GRAY_STAINED_GLASS_DOOR = registerWithItem("gray_stained_glass_door", createGlassDoorBlock(DyeColor.GRAY), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BLACK_STAINED_GLASS_DOOR = registerWithItem("black_stained_glass_door", createGlassDoorBlock(DyeColor.BLACK), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BROWN_STAINED_GLASS_DOOR = registerWithItem("brown_stained_glass_door", createGlassDoorBlock(DyeColor.BROWN), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block RED_STAINED_GLASS_DOOR = registerWithItem("red_stained_glass_door", createGlassDoorBlock(DyeColor.RED), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block ORANGE_STAINED_GLASS_DOOR = registerWithItem("orange_stained_glass_door", createGlassDoorBlock(DyeColor.ORANGE), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block YELLOW_STAINED_GLASS_DOOR = registerWithItem("yellow_stained_glass_door", createGlassDoorBlock(DyeColor.YELLOW), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIME_STAINED_GLASS_DOOR = registerWithItem("lime_stained_glass_door", createGlassDoorBlock(DyeColor.LIME), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block GREEN_STAINED_GLASS_DOOR = registerWithItem("green_stained_glass_door", createGlassDoorBlock(DyeColor.GREEN), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block CYAN_STAINED_GLASS_DOOR = registerWithItem("cyan_stained_glass_door", createGlassDoorBlock(DyeColor.CYAN), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIGHT_BLUE_STAINED_GLASS_DOOR = registerWithItem("light_blue_stained_glass_door", createGlassDoorBlock(DyeColor.LIGHT_BLUE), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BLUE_STAINED_GLASS_DOOR = registerWithItem("blue_stained_glass_door", createGlassDoorBlock(DyeColor.BLUE), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block PURPLE_STAINED_GLASS_DOOR = registerWithItem("purple_stained_glass_door", createGlassDoorBlock(DyeColor.PURPLE), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block MAGENTA_STAINED_GLASS_DOOR = registerWithItem("magenta_stained_glass_door", createGlassDoorBlock(DyeColor.MAGENTA), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block PINK_STAINED_GLASS_DOOR = registerWithItem("pink_stained_glass_door", createGlassDoorBlock(DyeColor.PINK), KaleidoscopeConfig.GLASS_DOORS);

    public static final Block GLASS_TRAPDOOR = registerWithItem("glass_trapdoor", new GlassTrapdoorBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BLACK_STAINED_GLASS_TRAPDOOR = registerWithItem("black_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLACK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLACK)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BLUE_STAINED_GLASS_TRAPDOOR = registerWithItem("blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BLUE)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BROWN_STAINED_GLASS_TRAPDOOR = registerWithItem("brown_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.BROWN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.BROWN)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block CYAN_STAINED_GLASS_TRAPDOOR = registerWithItem("cyan_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.CYAN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.CYAN)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block GRAY_STAINED_GLASS_TRAPDOOR = registerWithItem("gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GRAY)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block GREEN_STAINED_GLASS_TRAPDOOR = registerWithItem("green_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.GREEN, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.GREEN)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIGHT_BLUE_STAINED_GLASS_TRAPDOOR = registerWithItem("light_blue_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_BLUE)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIGHT_GRAY_STAINED_GLASS_TRAPDOOR = registerWithItem("light_gray_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIGHT_GRAY)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block LIME_STAINED_GLASS_TRAPDOOR = registerWithItem("lime_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.LIME, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.LIME)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block MAGENTA_STAINED_GLASS_TRAPDOOR = registerWithItem("magenta_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.MAGENTA, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.MAGENTA)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block ORANGE_STAINED_GLASS_TRAPDOOR = registerWithItem("orange_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.ORANGE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.ORANGE)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block PINK_STAINED_GLASS_TRAPDOOR = registerWithItem("pink_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PINK, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PINK)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block PURPLE_STAINED_GLASS_TRAPDOOR = registerWithItem("purple_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.PURPLE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.PURPLE)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block RED_STAINED_GLASS_TRAPDOOR = registerWithItem("red_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.RED, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.RED)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block WHITE_STAINED_GLASS_TRAPDOOR = registerWithItem("white_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.WHITE, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.WHITE)), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block YELLOW_STAINED_GLASS_TRAPDOOR = registerWithItem("yellow_stained_glass_trapdoor", new StainedGlassTrapdoorBlock(DyeColor.YELLOW, AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(MapColor.YELLOW)), KaleidoscopeConfig.GLASS_DOORS);

    private static Block createGlassDoorBlock(@Nullable DyeColor dyeColor) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F);
        if (dyeColor != null) {
            return new StainedGlassDoorBlock(dyeColor, settings.mapColor(dyeColor));
        }
        return new GlassDoorBlock(settings.mapColor(MapColor.CLEAR));
    }

    public static Block register(String id, Block block) {
        return BlockRegistry.register(Kaleidoscope.of(id), block);
    }

    public static Block register(String id, Block block, Supplier<Boolean> condition) {
        return BlockRegistry.register(Kaleidoscope.of(id), block, condition);
    }

    public static Block registerWithItem(String id, Block block) {
        return BlockRegistry.registerWithItem(Kaleidoscope.of(id), block);
    }

    public static Block registerWithItem(String id, Block block, Supplier<Boolean> condition) {
        return BlockRegistry.registerWithItem(Kaleidoscope.of(id), block, condition);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(STICK_BLOCK, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().remove(Blocks.COAL_BLOCK);
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
