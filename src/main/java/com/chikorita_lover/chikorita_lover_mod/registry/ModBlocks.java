package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.block.CopperDoorBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block DIRT_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(1f, 3f).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static final Block DIRT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(1f, 3f).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static final Block DIRT_BRICK_STAIRS = new StairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(DIRT_BRICKS));

    public static final Block POLISHED_CALCITE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5f, 6f).sounds(BlockSoundGroup.CALCITE).requiresTool());
    public static final Block POLISHED_CALCITE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5f, 6f).sounds(BlockSoundGroup.CALCITE).requiresTool());
    public static final Block POLISHED_CALCITE_STAIRS = new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE));
    public static final Block POLISHED_CALCITE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE));

    public static final Block COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block EXPOSED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WEATHERED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block OXIDIZED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static final Block WAXED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_EXPOSED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.of(Material.METAL, MapColor.TERRACOTTA_LIGHT_GRAY).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_WEATHERED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.of(Material.METAL, MapColor.DARK_AQUA).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());
    public static final Block WAXED_OXIDIZED_COPPER_DOOR = new CopperDoorBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.of(Material.METAL, MapColor.TEAL).requiresTool().strength(3.0F).sounds(BlockSoundGroup.COPPER).nonOpaque());

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_bricks"), DIRT_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_slab"), DIRT_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_wall"), DIRT_BRICK_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_wall"), POLISHED_CALCITE_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "copper_door"), COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_door"), EXPOSED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_door"), WEATHERED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_door"), OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_door"), WAXED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_door"), WAXED_EXPOSED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_door"), WAXED_WEATHERED_COPPER_DOOR);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_door"), WAXED_OXIDIZED_COPPER_DOOR);
    }

    public static void registerOxidizablePairs() {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_DOOR, EXPOSED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_DOOR, WEATHERED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_DOOR, OXIDIZED_COPPER_DOOR);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_DOOR, WAXED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_DOOR, WAXED_EXPOSED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_DOOR, WAXED_WEATHERED_COPPER_DOOR);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_DOOR, WAXED_OXIDIZED_COPPER_DOOR);
    }
}
