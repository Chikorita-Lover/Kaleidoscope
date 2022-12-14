package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final BlockItem DIRT_BRICKS = new BlockItem(ModBlocks.DIRT_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_SLAB = new BlockItem(ModBlocks.DIRT_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_STAIRS = new BlockItem(ModBlocks.DIRT_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_WALL = new BlockItem(ModBlocks.DIRT_BRICK_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem POLISHED_CALCITE = new BlockItem(ModBlocks.POLISHED_CALCITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_SLAB = new BlockItem(ModBlocks.POLISHED_CALCITE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_STAIRS = new BlockItem(ModBlocks.POLISHED_CALCITE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_WALL = new BlockItem(ModBlocks.POLISHED_CALCITE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem CUT_COPPER_WALL = new BlockItem(ModBlocks.CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_CUT_COPPER_WALL = new BlockItem(ModBlocks.EXPOSED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WEATHERED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem OXIDIZED_CUT_COPPER_WALL = new BlockItem(ModBlocks.OXIDIZED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem WAXED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_OXIDIZED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    
    public static final BlockItem COPPER_DOOR = new BlockItem(ModBlocks.COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem EXPOSED_COPPER_DOOR = new BlockItem(ModBlocks.EXPOSED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WEATHERED_COPPER_DOOR = new BlockItem(ModBlocks.WEATHERED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem OXIDIZED_COPPER_DOOR = new BlockItem(ModBlocks.OXIDIZED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final BlockItem WAXED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_EXPOSED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_WEATHERED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_OXIDIZED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_bricks"), DIRT_BRICKS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_slab"), DIRT_BRICK_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_wall"), DIRT_BRICK_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_wall"), POLISHED_CALCITE_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "cut_copper_wall"), CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "exposed_cut_copper_wall"), EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "weathered_cut_copper_wall"), WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "oxidized_cut_copper_wall"), OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_cut_copper_wall"), WAXED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_cut_copper_wall"), WAXED_EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_cut_copper_wall"), WAXED_WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_cut_copper_wall"), WAXED_OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "copper_door"), COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_door"), EXPOSED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_door"), WEATHERED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_door"), OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_door"), WAXED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_door"), WAXED_EXPOSED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_door"), WAXED_WEATHERED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_door"), WAXED_OXIDIZED_COPPER_DOOR);
    }
}
