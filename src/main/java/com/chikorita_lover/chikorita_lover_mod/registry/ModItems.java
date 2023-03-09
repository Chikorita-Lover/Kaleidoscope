package com.chikorita_lover.chikorita_lover_mod.registry;

import com.chikorita_lover.chikorita_lover_mod.ChikoritaLoverMod;
import com.chikorita_lover.chikorita_lover_mod.item.CakeSliceItem;
import com.chikorita_lover.chikorita_lover_mod.mixin.BundleItemMixin;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final BlockItem DIRT_BRICKS = new BlockItem(ModBlocks.DIRT_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_SLAB = new BlockItem(ModBlocks.DIRT_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_STAIRS = new BlockItem(ModBlocks.DIRT_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem DIRT_BRICK_WALL = new BlockItem(ModBlocks.DIRT_BRICK_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem STONE_TILES = new BlockItem(ModBlocks.STONE_TILES, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem STONE_TILE_SLAB = new BlockItem(ModBlocks.STONE_TILE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem STONE_TILE_STAIRS = new BlockItem(ModBlocks.STONE_TILE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem CALCITE_SLAB = new BlockItem(ModBlocks.CALCITE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem CALCITE_STAIRS = new BlockItem(ModBlocks.CALCITE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem CALCITE_WALL = new BlockItem(ModBlocks.CALCITE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem POLISHED_CALCITE = new BlockItem(ModBlocks.POLISHED_CALCITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_SLAB = new BlockItem(ModBlocks.POLISHED_CALCITE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_CALCITE_STAIRS = new BlockItem(ModBlocks.POLISHED_CALCITE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem TUFF_SLAB = new BlockItem(ModBlocks.TUFF_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem TUFF_STAIRS = new BlockItem(ModBlocks.TUFF_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem TUFF_WALL = new BlockItem(ModBlocks.TUFF_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem POLISHED_TUFF = new BlockItem(ModBlocks.POLISHED_TUFF, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_TUFF_SLAB = new BlockItem(ModBlocks.POLISHED_TUFF_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_TUFF_STAIRS = new BlockItem(ModBlocks.POLISHED_TUFF_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem CUT_COPPER_WALL = new BlockItem(ModBlocks.CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem EXPOSED_CUT_COPPER_WALL = new BlockItem(ModBlocks.EXPOSED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WEATHERED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WEATHERED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem OXIDIZED_CUT_COPPER_WALL = new BlockItem(ModBlocks.OXIDIZED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem WAXED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_EXPOSED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_WEATHERED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem WAXED_OXIDIZED_CUT_COPPER_WALL = new BlockItem(ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem LAPIS_LAZULI_TILES = new BlockItem(ModBlocks.LAPIS_LAZULI_TILES, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem LAPIS_LAZULI_TILE_SLAB = new BlockItem(ModBlocks.LAPIS_LAZULI_TILE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem LAPIS_LAZULI_TILE_STAIRS = new BlockItem(ModBlocks.LAPIS_LAZULI_TILE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem POLISHED_PRISMARINE = new BlockItem(ModBlocks.POLISHED_PRISMARINE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_PRISMARINE_SLAB = new BlockItem(ModBlocks.POLISHED_PRISMARINE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_PRISMARINE_STAIRS = new BlockItem(ModBlocks.POLISHED_PRISMARINE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_PRISMARINE_WALL = new BlockItem(ModBlocks.POLISHED_PRISMARINE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem CRACKED_RED_NETHER_BRICKS = new BlockItem(ModBlocks.CRACKED_RED_NETHER_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem CHISELED_RED_NETHER_BRICKS = new BlockItem(ModBlocks.CHISELED_RED_NETHER_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem POLISHED_END_STONE = new BlockItem(ModBlocks.POLISHED_END_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_END_STONE_SLAB = new BlockItem(ModBlocks.POLISHED_END_STONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_END_STONE_STAIRS = new BlockItem(ModBlocks.POLISHED_END_STONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem POLISHED_END_STONE_WALL = new BlockItem(ModBlocks.POLISHED_END_STONE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem END_STONE_PILLAR = new BlockItem(ModBlocks.END_STONE_PILLAR, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem CHISELED_PURPUR = new BlockItem(ModBlocks.CHISELED_PURPUR, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem SMOOTH_BASALT_SLAB = new BlockItem(ModBlocks.SMOOTH_BASALT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem SMOOTH_BASALT_STAIRS = new BlockItem(ModBlocks.SMOOTH_BASALT_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final BlockItem SMOOTH_BASALT_WALL = new BlockItem(ModBlocks.SMOOTH_BASALT_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static final BlockItem STICK_BUNDLE = new BlockItem(ModBlocks.STICK_BUNDLE, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final BlockItem RED_NETHER_BRICK_FENCE = new BlockItem(ModBlocks.RED_NETHER_BRICK_FENCE, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static final BlockItem KILN = new BlockItem(ModBlocks.KILN, new Item.Settings().group(ItemGroup.DECORATIONS));
    
    public static final BlockItem COPPER_DOOR = new BlockItem(ModBlocks.COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem EXPOSED_COPPER_DOOR = new BlockItem(ModBlocks.EXPOSED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WEATHERED_COPPER_DOOR = new BlockItem(ModBlocks.WEATHERED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem OXIDIZED_COPPER_DOOR = new BlockItem(ModBlocks.OXIDIZED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final BlockItem WAXED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_EXPOSED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_WEATHERED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_OXIDIZED_COPPER_DOOR = new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final BlockItem COPPER_TRAPDOOR = new BlockItem(ModBlocks.COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem EXPOSED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.EXPOSED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WEATHERED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.WEATHERED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem OXIDIZED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.OXIDIZED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final BlockItem WAXED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.WAXED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_EXPOSED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_WEATHERED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem WAXED_OXIDIZED_COPPER_TRAPDOOR = new BlockItem(ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, new Item.Settings().group(ItemGroup.REDSTONE));

    public static final Item CRIMSON_BOAT = new BoatItem(false, ModBoatType.CRIMSON, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item CRIMSON_CHEST_BOAT = new BoatItem(true, ModBoatType.CRIMSON, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item WARPED_BOAT = new BoatItem(false, ModBoatType.WARPED, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));
    public static final Item WARPED_CHEST_BOAT = new BoatItem(true, ModBoatType.WARPED, new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));

    public static final Item CHAINMAIL_HORSE_ARMOR = new HorseArmorItem(4, "chainmail", new Item.Settings().maxCount(1).group(ItemGroup.MISC));

    public static final Item CAKE_SLICE = new CakeSliceItem(new Item.Settings().food(ModFoodComponents.CAKE_SLICE).group(ItemGroup.FOOD));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_bricks"), DIRT_BRICKS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_slab"), DIRT_BRICK_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_stairs"), DIRT_BRICK_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "dirt_brick_wall"), DIRT_BRICK_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "stone_tiles"), STONE_TILES);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "stone_tile_slab"), STONE_TILE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "stone_tile_stairs"), STONE_TILE_STAIRS);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "calcite_slab"), CALCITE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "calcite_stairs"), CALCITE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "calcite_wall"), CALCITE_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite"), POLISHED_CALCITE);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_slab"), POLISHED_CALCITE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_calcite_stairs"), POLISHED_CALCITE_STAIRS);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "tuff_slab"), TUFF_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "tuff_stairs"), TUFF_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "tuff_wall"), TUFF_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_tuff"), POLISHED_TUFF);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_tuff_slab"), POLISHED_TUFF_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_tuff_stairs"), POLISHED_TUFF_STAIRS);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "cut_copper_wall"), CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "exposed_cut_copper_wall"), EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "weathered_cut_copper_wall"), WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "oxidized_cut_copper_wall"), OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_cut_copper_wall"), WAXED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_cut_copper_wall"), WAXED_EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_cut_copper_wall"), WAXED_WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_cut_copper_wall"), WAXED_OXIDIZED_CUT_COPPER_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tiles"), LAPIS_LAZULI_TILES);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tile_slab"), LAPIS_LAZULI_TILE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "lapis_lazuli_tile_stairs"), LAPIS_LAZULI_TILE_STAIRS);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine"), POLISHED_PRISMARINE);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_slab"), POLISHED_PRISMARINE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_stairs"), POLISHED_PRISMARINE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_prismarine_wall"), POLISHED_PRISMARINE_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "cracked_red_nether_bricks"), CRACKED_RED_NETHER_BRICKS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "chiseled_red_nether_bricks"), CHISELED_RED_NETHER_BRICKS);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone"), POLISHED_END_STONE);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_slab"), POLISHED_END_STONE_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_stairs"), POLISHED_END_STONE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "polished_end_stone_wall"), POLISHED_END_STONE_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "end_stone_pillar"), END_STONE_PILLAR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "chiseled_purpur"), CHISELED_PURPUR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "smooth_basalt_slab"), SMOOTH_BASALT_SLAB);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "smooth_basalt_stairs"), SMOOTH_BASALT_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "smooth_basalt_wall"), SMOOTH_BASALT_WALL);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "stick_bundle"), STICK_BUNDLE);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "red_nether_brick_fence"), RED_NETHER_BRICK_FENCE);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "kiln"), KILN);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "copper_door"), COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_door"), EXPOSED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_door"), WEATHERED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_door"), OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_door"), WAXED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_door"), WAXED_EXPOSED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_door"), WAXED_WEATHERED_COPPER_DOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_door"), WAXED_OXIDIZED_COPPER_DOOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "copper_trapdoor"), COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "exposed_copper_trapdoor"), EXPOSED_COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "weathered_copper_trapdoor"), WEATHERED_COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "oxidized_copper_trapdoor"), OXIDIZED_COPPER_TRAPDOOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_copper_trapdoor"), WAXED_COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_exposed_copper_trapdoor"), WAXED_EXPOSED_COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_weathered_copper_trapdoor"), WAXED_WEATHERED_COPPER_TRAPDOOR);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "waxed_oxidized_copper_trapdoor"), WAXED_OXIDIZED_COPPER_TRAPDOOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "crimson_boat"), CRIMSON_BOAT);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "crimson_chest_boat"), CRIMSON_CHEST_BOAT);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "warped_boat"), WARPED_BOAT);
        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "warped_chest_boat"), WARPED_CHEST_BOAT);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "chainmail_horse_armor"), CHAINMAIL_HORSE_ARMOR);

        Registry.register(Registry.ITEM, new Identifier(ChikoritaLoverMod.MODID, "cake_slice"), CAKE_SLICE);
    }

    public static void registerColorProviders() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)stack.getItem()).getColor(stack), Items.BUNDLE);
    }

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(ModBlocks.STICK_BUNDLE, 1000);
    }

    public static void registerMaxItemCounts() {
        MaxItemCountRegistry.registerMaxItemCount(Items.CAKE, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.EGG, 64);
        MaxItemCountRegistry.registerMaxItemCount(Items.POTION, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.SNOWBALL, 64);
    }
}
