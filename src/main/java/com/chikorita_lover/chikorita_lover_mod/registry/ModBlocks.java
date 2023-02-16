package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.block.CopperDoorBlock;
import com.chikorita_lover.chikorita_lover_mod.block.CopperTrapdoorBlock;
import com.chikorita_lover.chikorita_lover_mod.block.KilnBlock;
import com.chikorita_lover.chikorita_lover_mod.block.OxidizableWallBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

import static net.minecraft.block.Blocks.*;

public class ModBlocks {
    public static final Block DIRT_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(1.0F, 1.0F).sounds(ModBlockSoundGroup.DIRT_BRICKS));
    public static final Block DIRT_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_STAIRS = new StairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(DIRT_BRICKS));

    public static final Block POLISHED_CALCITE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5F, 6F).sounds(BlockSoundGroup.CALCITE).requiresTool());
    public static final Block POLISHED_CALCITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE));
    public static final Block POLISHED_CALCITE_STAIRS = new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE));
    public static final Block POLISHED_CALCITE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE));

    public static final Block STONE_TILES = new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(1.5F, 6F));
    public static final Block STONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(STONE_TILES));
    public static final Block STONE_TILE_STAIRS = new StairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(STONE_TILES));

    public static final Block CUT_COPPER_WALL = new OxidizableWallBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(CUT_COPPER));
    public static final Block EXPOSED_CUT_COPPER_WALL = new OxidizableWallBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(EXPOSED_CUT_COPPER));
    public static final Block WEATHERED_CUT_COPPER_WALL = new OxidizableWallBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(WEATHERED_CUT_COPPER));
    public static final Block OXIDIZED_CUT_COPPER_WALL = new OxidizableWallBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(OXIDIZED_CUT_COPPER));

    public static final Block WAXED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copy(WAXED_CUT_COPPER));
    public static final Block WAXED_EXPOSED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copy(WAXED_EXPOSED_CUT_COPPER));
    public static final Block WAXED_WEATHERED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copy(WAXED_WEATHERED_CUT_COPPER));
    public static final Block WAXED_OXIDIZED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copy(WAXED_OXIDIZED_CUT_COPPER));

    public static final Block LAPIS_LAZULI_TILES = new Block(FabricBlockSettings.of(Material.METAL, MapColor.LAPIS_BLUE).requiresTool().strength(3.0F, 3.0F));
    public static final Block LAPIS_LAZULI_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(LAPIS_LAZULI_TILES));
    public static final Block LAPIS_LAZULI_TILE_STAIRS = new StairsBlock(LAPIS_LAZULI_TILES.getDefaultState(), AbstractBlock.Settings.copy(LAPIS_LAZULI_TILES));

    public static final Block POLISHED_PRISMARINE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.DIAMOND_BLUE).strength(1.5F, 6F).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block POLISHED_PRISMARINE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(POLISHED_PRISMARINE));
    public static final Block POLISHED_PRISMARINE_STAIRS = new StairsBlock(POLISHED_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_PRISMARINE));
    public static final Block POLISHED_PRISMARINE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_PRISMARINE));

    public static final Block CRACKED_RED_NETHER_BRICKS = new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS));
    public static final Block CHISELED_RED_NETHER_BRICKS = new Block(AbstractBlock.Settings.copy(RED_NETHER_BRICKS));

    public static final Block POLISHED_END_STONE = new Block(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 9.0F));
    public static final Block POLISHED_END_STONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE));
    public static final Block POLISHED_END_STONE_STAIRS = new StairsBlock(POLISHED_END_STONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_END_STONE));
    public static final Block POLISHED_END_STONE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_END_STONE));

    public static final Block END_STONE_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().strength(3.0F, 9.0F));

    public static final Block CHISELED_PURPUR = new Block(AbstractBlock.Settings.copy(PURPUR_BLOCK));

    public static final Block STICK_BUNDLE = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).sounds(ModBlockSoundGroup.STICK_BUNDLE).strength(2.0F));

    public static final Block RED_NETHER_BRICK_FENCE = new FenceBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.DARK_RED).requiresTool().strength(2.0F, 6.0F).sounds(BlockSoundGroup.NETHER_BRICKS));

    public static final Block KILN = new KilnBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.5F).luminance(createLightLevelFromLitBlockState(13)));

    public static final Block COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block EXPOSED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WEATHERED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block OXIDIZED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static final Block WAXED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_EXPOSED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_WEATHERED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_OXIDIZED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static final Block COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block EXPOSED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WEATHERED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block OXIDIZED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static final Block WAXED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_EXPOSED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_WEATHERED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_OXIDIZED_COPPER_TRAPDOOR = new CopperTrapdoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_bricks"), DIRT_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_slab"), DIRT_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_wall"), DIRT_BRICK_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "stone_tiles"), STONE_TILES);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "stone_tile_slab"), STONE_TILE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "stone_tile_stairs"), STONE_TILE_STAIRS);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_wall"), POLISHED_CALCITE_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "cut_copper_wall"), CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "exposed_cut_copper_wall"), EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "weathered_cut_copper_wall"), WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "oxidized_cut_copper_wall"), OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_cut_copper_wall"), WAXED_CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_cut_copper_wall"), WAXED_EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_cut_copper_wall"), WAXED_WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_cut_copper_wall"), WAXED_OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tiles"), LAPIS_LAZULI_TILES);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tile_slab"), LAPIS_LAZULI_TILE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tile_stairs"), LAPIS_LAZULI_TILE_STAIRS);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine"), POLISHED_PRISMARINE);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_slab"), POLISHED_PRISMARINE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_stairs"), POLISHED_PRISMARINE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_wall"), POLISHED_PRISMARINE_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "cracked_red_nether_bricks"), CRACKED_RED_NETHER_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "chiseled_red_nether_bricks"), CHISELED_RED_NETHER_BRICKS);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone"), POLISHED_END_STONE);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_slab"), POLISHED_END_STONE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_stairs"), POLISHED_END_STONE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_wall"), POLISHED_END_STONE_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "end_stone_pillar"), END_STONE_PILLAR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "chiseled_purpur"), CHISELED_PURPUR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "stick_bundle"), STICK_BUNDLE);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "red_nether_brick_fence"), RED_NETHER_BRICK_FENCE);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "kiln"), KILN);
        
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "copper_door"), COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_door"), EXPOSED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_door"), WEATHERED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_door"), OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_door"), WAXED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_door"), WAXED_EXPOSED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_door"), WAXED_WEATHERED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_door"), WAXED_OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "copper_trapdoor"), COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_trapdoor"), EXPOSED_COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_trapdoor"), WEATHERED_COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_trapdoor"), OXIDIZED_COPPER_TRAPDOOR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_trapdoor"), WAXED_COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_trapdoor"), WAXED_EXPOSED_COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_trapdoor"), WAXED_WEATHERED_COPPER_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_trapdoor"), WAXED_OXIDIZED_COPPER_TRAPDOOR);
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
