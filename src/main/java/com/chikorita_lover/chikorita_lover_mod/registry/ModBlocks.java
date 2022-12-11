package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block DIRT_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(1f, 6f).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static final Block DIRT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(1f, 6f).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static final Block DIRT_BRICK_STAIRS = new StairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(DIRT_BRICKS));
    public static final Block DIRT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(DIRT_BRICKS));
    public static final Block POLISHED_CALCITE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5f, 6f).sounds(ModBlockSoundGroup.POLISHED_CALCITE).requiresTool());
    public static final Block POLISHED_CALCITE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_WHITE).strength(1.5f, 6f).sounds(ModBlockSoundGroup.POLISHED_CALCITE).requiresTool());
    public static final Block POLISHED_CALCITE_STAIRS = new StairsBlock(POLISHED_CALCITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_CALCITE));
    public static final Block POLISHED_CALCITE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_CALCITE));
    public static void register(){
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_bricks"), DIRT_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_slab"), DIRT_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_wall"), DIRT_BRICK_WALL);

        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_wall"), POLISHED_CALCITE_WALL);
    }
}
