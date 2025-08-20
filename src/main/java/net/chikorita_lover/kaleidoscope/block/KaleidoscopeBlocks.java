package net.chikorita_lover.kaleidoscope.block;

import net.chikorita_lover.chicory.api.resource.ToggleableFeatureRegistry;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class KaleidoscopeBlocks {
    public static final Block POLISHED_GRANITE_WALL = registerWithItem("polished_granite_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE), KaleidoscopeConfig.STONE_BLOCKS);
    public static final Block POLISHED_DIORITE_WALL = registerWithItem("polished_diorite_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE), KaleidoscopeConfig.STONE_BLOCKS);
    public static final Block POLISHED_ANDESITE_WALL = registerWithItem("polished_andesite_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE), KaleidoscopeConfig.STONE_BLOCKS);

    public static final Block CALCITE_SLAB = registerWithItem("calcite_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block CALCITE_STAIRS = registerStairsBlock("calcite_stairs", Blocks.CALCITE, KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block CALCITE_WALL = registerWithItem("calcite_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block POLISHED_CALCITE = registerWithItem("polished_calcite", AbstractBlock.Settings.copy(Blocks.CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_STAIRS = registerStairsBlock("polished_calcite_stairs", POLISHED_CALCITE, KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_SLAB = registerWithItem("polished_calcite_slab", SlabBlock::new, AbstractBlock.Settings.copy(POLISHED_CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block POLISHED_CALCITE_WALL = registerWithItem("polished_calcite_wall", WallBlock::new, AbstractBlock.Settings.copy(POLISHED_CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block SMOOTH_CALCITE = registerWithItem("smooth_calcite", AbstractBlock.Settings.copy(Blocks.CALCITE).strength(1.9F, 6.0F), KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block SMOOTH_CALCITE_STAIRS = registerStairsBlock("smooth_calcite_stairs", SMOOTH_CALCITE, KaleidoscopeConfig.CALCITE_BLOCKS);
    public static final Block SMOOTH_CALCITE_SLAB = registerWithItem("smooth_calcite_slab", SlabBlock::new, AbstractBlock.Settings.copy(SMOOTH_CALCITE), KaleidoscopeConfig.CALCITE_BLOCKS);

    public static final Block CRACKED_TUFF_BRICKS = registerWithItem("cracked_tuff_bricks", AbstractBlock.Settings.copy(Blocks.TUFF_BRICKS), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block BRICK_MOSAIC = registerWithItem("brick_mosaic", AbstractBlock.Settings.copy(Blocks.BRICKS), KaleidoscopeConfig.BRICK_MOSAICS);
    public static final Block BRICK_MOSAIC_STAIRS = registerStairsBlock("brick_mosaic_stairs", BRICK_MOSAIC, KaleidoscopeConfig.BRICK_MOSAICS);
    public static final Block BRICK_MOSAIC_SLAB = registerWithItem("brick_mosaic_slab", SlabBlock::new, AbstractBlock.Settings.copy(BRICK_MOSAIC), KaleidoscopeConfig.BRICK_MOSAICS);

    public static final Block PACKED_MUD_STAIRS = registerStairsBlock("packed_mud_stairs", Blocks.PACKED_MUD, KaleidoscopeConfig.MUD_BLOCKS);
    public static final Block PACKED_MUD_SLAB = registerWithItem("packed_mud_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.PACKED_MUD), KaleidoscopeConfig.MUD_BLOCKS);
    public static final Block PACKED_MUD_WALL = registerWithItem("packed_mud_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.PACKED_MUD), KaleidoscopeConfig.MUD_BLOCKS);

    public static final Block CRACKED_MUD_BRICKS = registerWithItem("cracked_mud_bricks", AbstractBlock.Settings.copy(Blocks.MUD_BRICKS), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block SMOOTH_BASALT_STAIRS = registerStairsBlock("smooth_basalt_stairs", Blocks.SMOOTH_BASALT, KaleidoscopeConfig.BASALT_BLOCKS);
    public static final Block SMOOTH_BASALT_SLAB = registerWithItem("smooth_basalt_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT), KaleidoscopeConfig.BASALT_BLOCKS);
    public static final Block SMOOTH_BASALT_WALL = registerWithItem("smooth_basalt_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT), KaleidoscopeConfig.BASALT_BLOCKS);

    public static final Block CHARCOAL_BLOCK = registerWithItem("charcoal_block", PillarBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_BROWN).requiresTool().sounds(BlockSoundGroup.DEEPSLATE).strength(3.0F, 6.0F), KaleidoscopeConfig.CHARCOAL_BLOCKS);

    public static final Block QUARTZ_BRICK_STAIRS = registerStairsBlock("quartz_brick_stairs", Blocks.QUARTZ_BRICKS, KaleidoscopeConfig.QUARTZ_BLOCKS);
    public static final Block QUARTZ_BRICK_SLAB = registerWithItem("quartz_brick_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS), KaleidoscopeConfig.QUARTZ_BLOCKS);
    public static final Block QUARTZ_BRICK_WALL = registerWithItem("quartz_brick_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS), KaleidoscopeConfig.QUARTZ_BLOCKS);

    public static final Block SMOOTH_COPPER = registerWithItem("smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block SMOOTH_COPPER_STAIRS = registerWithItem("smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block SMOOTH_COPPER_SLAB = registerWithItem("smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block EXPOSED_SMOOTH_COPPER = registerWithItem("exposed_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.EXPOSED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block EXPOSED_SMOOTH_COPPER_STAIRS = registerWithItem("exposed_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, EXPOSED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block EXPOSED_SMOOTH_COPPER_SLAB = registerWithItem("exposed_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(EXPOSED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WEATHERED_SMOOTH_COPPER = registerWithItem("weathered_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.WEATHERED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WEATHERED_SMOOTH_COPPER_STAIRS = registerWithItem("weathered_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WEATHERED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WEATHERED_SMOOTH_COPPER_SLAB = registerWithItem("weathered_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(WEATHERED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block OXIDIZED_SMOOTH_COPPER = registerWithItem("oxidized_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.OXIDIZED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block OXIDIZED_SMOOTH_COPPER_STAIRS = registerWithItem("oxidized_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, OXIDIZED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block OXIDIZED_SMOOTH_COPPER_SLAB = registerWithItem("oxidized_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(OXIDIZED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_SMOOTH_COPPER = registerWithItem("waxed_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.WAXED_COPPER_BLOCK), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.UNAFFECTED, WAXED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(WAXED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_EXPOSED_SMOOTH_COPPER = registerWithItem("waxed_exposed_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.WAXED_EXPOSED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_exposed_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.EXPOSED, WAXED_EXPOSED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_EXPOSED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_exposed_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(WAXED_EXPOSED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_WEATHERED_SMOOTH_COPPER = registerWithItem("waxed_weathered_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.WAXED_WEATHERED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_weathered_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.WEATHERED, WAXED_WEATHERED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_WEATHERED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_weathered_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(WAXED_WEATHERED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER = registerWithItem("waxed_oxidized_smooth_copper", settings -> new OxidizableBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.WAXED_OXIDIZED_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS = registerWithItem("waxed_oxidized_smooth_copper_stairs", settings -> new OxidizableStairsBlock(Oxidizable.OxidationLevel.OXIDIZED, WAXED_OXIDIZED_SMOOTH_COPPER.getDefaultState(), settings), AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);
    public static final Block WAXED_OXIDIZED_SMOOTH_COPPER_SLAB = registerWithItem("waxed_oxidized_smooth_copper_slab", settings -> new OxidizableSlabBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(WAXED_OXIDIZED_SMOOTH_COPPER), KaleidoscopeConfig.SMOOTH_COPPER);

    public static final Block CRACKED_RED_NETHER_BRICKS = registerWithItem("cracked_red_nether_bricks", AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);
    public static final Block RED_NETHER_BRICK_FENCE = registerWithItem("red_nether_brick_fence", FenceBlock::new, AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS));

    public static final Block END_STONE_STAIRS = registerStairsBlock("end_stone_stairs", Blocks.END_STONE, KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block END_STONE_SLAB = registerWithItem("end_stone_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.END_STONE), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block END_STONE_WALL = registerWithItem("end_stone_wall", WallBlock::new, AbstractBlock.Settings.copy(Blocks.END_STONE), KaleidoscopeConfig.END_STONE_BLOCKS);

    public static final Block POLISHED_END_STONE = registerWithItem("polished_end_stone", AbstractBlock.Settings.copy(Blocks.END_STONE), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_STAIRS = registerStairsBlock("polished_end_stone_stairs", POLISHED_END_STONE, KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_SLAB = registerWithItem("polished_end_stone_slab", SlabBlock::new, AbstractBlock.Settings.copy(POLISHED_END_STONE), KaleidoscopeConfig.END_STONE_BLOCKS);
    public static final Block POLISHED_END_STONE_WALL = registerWithItem("polished_end_stone_wall", WallBlock::new, AbstractBlock.Settings.copy(POLISHED_END_STONE), KaleidoscopeConfig.END_STONE_BLOCKS);

    public static final Block CRACKED_END_STONE_BRICKS = registerWithItem("cracked_end_stone_bricks", AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS), KaleidoscopeConfig.ADDITIONAL_CRACKED_BLOCKS);

    public static final Block TERRACOTTA_STAIRS = registerStairsBlock("terracotta_stairs", Blocks.TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block TERRACOTTA_SLAB = registerWithItem("terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block WHITE_TERRACOTTA_STAIRS = registerStairsBlock("white_terracotta_stairs", Blocks.WHITE_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block WHITE_TERRACOTTA_SLAB = registerWithItem("white_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIGHT_GRAY_TERRACOTTA_STAIRS = registerStairsBlock("light_gray_terracotta_stairs", Blocks.LIGHT_GRAY_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIGHT_GRAY_TERRACOTTA_SLAB = registerWithItem("light_gray_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block GRAY_TERRACOTTA_STAIRS = registerStairsBlock("gray_terracotta_stairs", Blocks.GRAY_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block GRAY_TERRACOTTA_SLAB = registerWithItem("gray_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.GRAY_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BLACK_TERRACOTTA_STAIRS = registerStairsBlock("black_terracotta_stairs", Blocks.BLACK_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BLACK_TERRACOTTA_SLAB = registerWithItem("black_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.BLACK_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BROWN_TERRACOTTA_STAIRS = registerStairsBlock("brown_terracotta_stairs", Blocks.BROWN_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BROWN_TERRACOTTA_SLAB = registerWithItem("brown_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.BROWN_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block RED_TERRACOTTA_STAIRS = registerStairsBlock("red_terracotta_stairs", Blocks.RED_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block RED_TERRACOTTA_SLAB = registerWithItem("red_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.RED_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block ORANGE_TERRACOTTA_STAIRS = registerStairsBlock("orange_terracotta_stairs", Blocks.ORANGE_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block ORANGE_TERRACOTTA_SLAB = registerWithItem("orange_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.ORANGE_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block YELLOW_TERRACOTTA_STAIRS = registerStairsBlock("yellow_terracotta_stairs", Blocks.YELLOW_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block YELLOW_TERRACOTTA_SLAB = registerWithItem("yellow_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.YELLOW_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIME_TERRACOTTA_STAIRS = registerStairsBlock("lime_terracotta_stairs", Blocks.LIME_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIME_TERRACOTTA_SLAB = registerWithItem("lime_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.LIME_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block GREEN_TERRACOTTA_STAIRS = registerStairsBlock("green_terracotta_stairs", Blocks.GREEN_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block GREEN_TERRACOTTA_SLAB = registerWithItem("green_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.GREEN_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block CYAN_TERRACOTTA_STAIRS = registerStairsBlock("cyan_terracotta_stairs", Blocks.CYAN_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block CYAN_TERRACOTTA_SLAB = registerWithItem("cyan_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.CYAN_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block LIGHT_BLUE_TERRACOTTA_STAIRS = registerStairsBlock("light_blue_terracotta_stairs", Blocks.LIGHT_BLUE_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block LIGHT_BLUE_TERRACOTTA_SLAB = registerWithItem("light_blue_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block BLUE_TERRACOTTA_STAIRS = registerStairsBlock("blue_terracotta_stairs", Blocks.BLUE_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block BLUE_TERRACOTTA_SLAB = registerWithItem("blue_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.BLUE_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block PURPLE_TERRACOTTA_STAIRS = registerStairsBlock("purple_terracotta_stairs", Blocks.PURPLE_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block PURPLE_TERRACOTTA_SLAB = registerWithItem("purple_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.PURPLE_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block MAGENTA_TERRACOTTA_STAIRS = registerStairsBlock("magenta_terracotta_stairs", Blocks.MAGENTA_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block MAGENTA_TERRACOTTA_SLAB = registerWithItem("magenta_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block PINK_TERRACOTTA_STAIRS = registerStairsBlock("pink_terracotta_stairs", Blocks.PINK_TERRACOTTA, KaleidoscopeConfig.TERRACOTTA_BLOCKS);
    public static final Block PINK_TERRACOTTA_SLAB = registerWithItem("pink_terracotta_slab", SlabBlock::new, AbstractBlock.Settings.copy(Blocks.PINK_TERRACOTTA), KaleidoscopeConfig.TERRACOTTA_BLOCKS);

    public static final Block STICK_BLOCK = registerWithItem("stick_block", PillarBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASS).sounds(BlockSoundGroup.MANGROVE_ROOTS).strength(0.4F).burnable(), KaleidoscopeConfig.STICK_BLOCKS);

    public static final Block SOUL_JACK_O_LANTERN = registerWithItem("soul_jack_o_lantern", CarvedPumpkinBlock::new, AbstractBlock.Settings.copy(Blocks.JACK_O_LANTERN).instrument(NoteBlockInstrument.DIDGERIDOO).luminance(state -> 10), KaleidoscopeConfig.SOUL_JACK_O_LANTERNS);

    public static final Block FIREWORKS_TABLE = registerWithItem("fireworks_table", FireworksTableBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable(), KaleidoscopeConfig.FIREWORK_IMPROVEMENTS);
    public static final Block KILN = registerWithItem("kiln", KilnBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.BASEDRUM).luminance(state -> state.get(Properties.LIT) ? 13 : 0).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F), KaleidoscopeConfig.KILNS);

    public static final Block GLASS_DOOR = registerWithItem("glass_door", GlassDoorBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block WHITE_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("white_stained_glass_door", DyeColor.WHITE);
    public static final Block LIGHT_GRAY_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("light_gray_stained_glass_door", DyeColor.LIGHT_GRAY);
    public static final Block GRAY_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("gray_stained_glass_door", DyeColor.GRAY);
    public static final Block BLACK_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("black_stained_glass_door", DyeColor.BLACK);
    public static final Block BROWN_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("brown_stained_glass_door", DyeColor.BROWN);
    public static final Block RED_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("red_stained_glass_door", DyeColor.RED);
    public static final Block ORANGE_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("orange_stained_glass_door", DyeColor.ORANGE);
    public static final Block YELLOW_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("yellow_stained_glass_door", DyeColor.YELLOW);
    public static final Block LIME_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("lime_stained_glass_door", DyeColor.LIME);
    public static final Block GREEN_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("green_stained_glass_door", DyeColor.GREEN);
    public static final Block CYAN_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("cyan_stained_glass_door", DyeColor.CYAN);
    public static final Block LIGHT_BLUE_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("light_blue_stained_glass_door", DyeColor.LIGHT_BLUE);
    public static final Block BLUE_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("blue_stained_glass_door", DyeColor.BLUE);
    public static final Block PURPLE_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("purple_stained_glass_door", DyeColor.PURPLE);
    public static final Block MAGENTA_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("magenta_stained_glass_door", DyeColor.MAGENTA);
    public static final Block PINK_STAINED_GLASS_DOOR = registerStainedGlassDoorBlock("pink_stained_glass_door", DyeColor.PINK);

    public static final Block GLASS_TRAPDOOR = registerWithItem("glass_trapdoor", GlassTrapdoorBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).mapColor(MapColor.CLEAR).nonOpaque().sounds(BlockSoundGroup.GLASS).strength(0.3F), KaleidoscopeConfig.GLASS_DOORS);
    public static final Block BLACK_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("black_stained_glass_trapdoor", DyeColor.BLACK);
    public static final Block BLUE_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("blue_stained_glass_trapdoor", DyeColor.BLUE);
    public static final Block BROWN_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("brown_stained_glass_trapdoor", DyeColor.BROWN);
    public static final Block CYAN_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("cyan_stained_glass_trapdoor", DyeColor.CYAN);
    public static final Block GRAY_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("gray_stained_glass_trapdoor", DyeColor.GRAY);
    public static final Block GREEN_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("green_stained_glass_trapdoor", DyeColor.GREEN);
    public static final Block LIGHT_BLUE_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("light_blue_stained_glass_trapdoor", DyeColor.LIGHT_BLUE);
    public static final Block LIGHT_GRAY_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("light_gray_stained_glass_trapdoor", DyeColor.LIGHT_GRAY);
    public static final Block LIME_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("lime_stained_glass_trapdoor", DyeColor.LIME);
    public static final Block MAGENTA_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("magenta_stained_glass_trapdoor", DyeColor.MAGENTA);
    public static final Block ORANGE_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("orange_stained_glass_trapdoor", DyeColor.ORANGE);
    public static final Block PINK_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("pink_stained_glass_trapdoor", DyeColor.PINK);
    public static final Block PURPLE_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("purple_stained_glass_trapdoor", DyeColor.PURPLE);
    public static final Block RED_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("red_stained_glass_trapdoor", DyeColor.RED);
    public static final Block WHITE_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("white_stained_glass_trapdoor", DyeColor.WHITE);
    public static final Block YELLOW_STAINED_GLASS_TRAPDOOR = registerStainedGlassTrapdoorBlock("yellow_stained_glass_trapdoor", DyeColor.YELLOW);

    public static final Block WHITE_CHEST = registerDyedChest("white_chest", DyeColor.WHITE);
    public static final Block LIGHT_GRAY_CHEST = registerDyedChest("light_gray_chest", DyeColor.LIGHT_GRAY);
    public static final Block GRAY_CHEST = registerDyedChest("gray_chest", DyeColor.GRAY);
    public static final Block BLACK_CHEST = registerDyedChest("black_chest", DyeColor.BLACK);
    public static final Block BROWN_CHEST = registerDyedChest("brown_chest", DyeColor.BROWN);
    public static final Block RED_CHEST = registerDyedChest("red_chest", DyeColor.RED);
    public static final Block ORANGE_CHEST = registerDyedChest("orange_chest", DyeColor.ORANGE);
    public static final Block YELLOW_CHEST = registerDyedChest("yellow_chest", DyeColor.YELLOW);
    public static final Block LIME_CHEST = registerDyedChest("lime_chest", DyeColor.LIME);
    public static final Block GREEN_CHEST = registerDyedChest("green_chest", DyeColor.GREEN);
    public static final Block CYAN_CHEST = registerDyedChest("cyan_chest", DyeColor.CYAN);
    public static final Block LIGHT_BLUE_CHEST = registerDyedChest("light_blue_chest", DyeColor.LIGHT_BLUE);
    public static final Block BLUE_CHEST = registerDyedChest("blue_chest", DyeColor.BLUE);
    public static final Block PURPLE_CHEST = registerDyedChest("purple_chest", DyeColor.PURPLE);
    public static final Block MAGENTA_CHEST = registerDyedChest("magenta_chest", DyeColor.MAGENTA);
    public static final Block PINK_CHEST = registerDyedChest("pink_chest", DyeColor.PINK);

    private static Block registerStairsBlock(String id, final Block base, Supplier<Boolean> condition) {
        return registerWithItem(id, settings -> new StairsBlock(base.getDefaultState(), settings), AbstractBlock.Settings.copy(base), condition);
    }

    private static Block registerStainedGlassDoorBlock(String id, final @Nullable DyeColor dyeColor) {
        return registerWithItem(id, settings -> new StainedGlassDoorBlock(dyeColor, settings), AbstractBlock.Settings.copy(GLASS_DOOR).mapColor(dyeColor), KaleidoscopeConfig.GLASS_DOORS);
    }

    private static Block registerStainedGlassTrapdoorBlock(String id, final @Nullable DyeColor dyeColor) {
        return registerWithItem(id, settings -> new StainedGlassTrapdoorBlock(dyeColor, settings), AbstractBlock.Settings.copy(GLASS_TRAPDOOR).mapColor(dyeColor), KaleidoscopeConfig.GLASS_DOORS);
    }

    private static Block registerDyedChest(String id, final DyeColor color) {
        return registerWithItem(id, settings -> new DyedChestBlock(color, settings), AbstractBlock.Settings.copy(Blocks.CHEST).mapColor(color));
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Kaleidoscope.of(id));
    }

    private static Block registerWithItem(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = Blocks.register(keyOf(id), factory, settings);
        Items.register(block);
        return block;
    }

    private static Block registerWithItem(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, Supplier<Boolean> condition) {
        Block block = Blocks.register(keyOf(id), factory, settings);
        ToggleableFeatureRegistry.add(block, condition);
        ToggleableFeatureRegistry.add(Items.register(block), condition);
        return block;
    }

    private static Block registerWithItem(String id, AbstractBlock.Settings settings, Supplier<Boolean> condition) {
        Block block = Blocks.register(keyOf(id), settings);
        ToggleableFeatureRegistry.add(block, condition);
        ToggleableFeatureRegistry.add(Items.register(block), condition);
        return block;
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(STICK_BLOCK, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().remove(Blocks.COAL_BLOCK);
        BlockEntityType.CHEST.addSupportedBlock(WHITE_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(LIGHT_GRAY_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(GRAY_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(BLACK_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(BROWN_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(RED_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(ORANGE_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(YELLOW_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(LIME_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(GREEN_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(CYAN_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(LIGHT_BLUE_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(BLUE_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(PURPLE_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(MAGENTA_CHEST);
        BlockEntityType.CHEST.addSupportedBlock(PINK_CHEST);
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
