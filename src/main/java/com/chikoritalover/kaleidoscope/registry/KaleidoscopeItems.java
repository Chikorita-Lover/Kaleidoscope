package com.chikoritalover.kaleidoscope.registry;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.item.CakeSliceItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class KaleidoscopeItems {
    public static final Item CRIMSON_BOAT = register("crimson_boat", new BoatItem(false, KaleidoscopeBoatType.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item CRIMSON_CHEST_BOAT = register("crimson_chest_boat", new BoatItem(true, KaleidoscopeBoatType.CRIMSON, new Item.Settings().maxCount(1)));
    public static final Item WARPED_BOAT = register("warped_boat", new BoatItem(false, KaleidoscopeBoatType.WARPED, new Item.Settings().maxCount(1)));
    public static final Item WARPED_CHEST_BOAT = register("warped_chest_boat", new BoatItem(true, KaleidoscopeBoatType.WARPED, new Item.Settings().maxCount(1)));
    public static final Item CHAINMAIL_HORSE_ARMOR = register("chainmail_horse_armor", new HorseArmorItem(4, "chainmail", new Item.Settings().maxCount(1)));
    public static final Item CAKE_SLICE = register("cake_slice", new CakeSliceItem(new Item.Settings().food(KaleidoscopeFoodComponents.CAKE_SLICE)));
    public static final Item DISC_FRAGMENT_PIGSTEP = register("disc_fragment_pigstep", new DiscFragmentItem(new Item.Settings()));

    public static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(BlockItem item) {
        return register(item.getBlock(), item);
    }

    private static Item register(Block block, Item item) {
        return register(Registries.BLOCK.getId(block), item);
    }

    private static Item register(String id, Item item) {
        return register(new Identifier(Kaleidoscope.MODID, id), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, id, item);
    }

    public static void registerCompostingChances() {
        CompostingChanceRegistry.INSTANCE.add(Items.BAMBOO, 0.3F);
    }

    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(KaleidoscopeBlocks.STICK_BUNDLE, 300);
    }

    public static void registerMaxItemCounts() {
        MaxItemCountRegistry.registerMaxItemCount(Items.CAKE, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.EGG, 64);
        MaxItemCountRegistry.registerMaxItemCount(Items.POTION, 16);
        MaxItemCountRegistry.registerMaxItemCount(Items.SNOWBALL, 64);
    }

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Blocks.DEEPSLATE, Blocks.CALCITE, KaleidoscopeBlocks.CALCITE_STAIRS, KaleidoscopeBlocks.CALCITE_SLAB, KaleidoscopeBlocks.CALCITE_WALL, KaleidoscopeBlocks.POLISHED_CALCITE, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB);
            entries.addAfter(Blocks.REINFORCED_DEEPSLATE, Blocks.TUFF, KaleidoscopeBlocks.TUFF_STAIRS, KaleidoscopeBlocks.TUFF_SLAB, KaleidoscopeBlocks.TUFF_WALL, KaleidoscopeBlocks.POLISHED_TUFF, KaleidoscopeBlocks.POLISHED_TUFF_STAIRS, KaleidoscopeBlocks.POLISHED_TUFF_SLAB);
            entries.addAfter(Blocks.PACKED_MUD, KaleidoscopeBlocks.PACKED_MUD_STAIRS, KaleidoscopeBlocks.PACKED_MUD_SLAB);
            entries.addAfter(Blocks.RED_NETHER_BRICKS, KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS);
            entries.addAfter(Blocks.RED_NETHER_BRICK_WALL, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS);
            entries.addAfter(Blocks.SMOOTH_BASALT, KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS, KaleidoscopeBlocks.SMOOTH_BASALT_SLAB, KaleidoscopeBlocks.SMOOTH_BASALT_WALL);
            entries.addAfter(Blocks.END_STONE, KaleidoscopeBlocks.END_STONE_STAIRS, KaleidoscopeBlocks.END_STONE_SLAB, KaleidoscopeBlocks.END_STONE_WALL, KaleidoscopeBlocks.POLISHED_END_STONE, KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS, KaleidoscopeBlocks.POLISHED_END_STONE_SLAB);
            entries.addAfter(Blocks.PURPUR_PILLAR, KaleidoscopeBlocks.CHISELED_PURPUR);
            entries.addAfter(Blocks.CUT_COPPER_SLAB, KaleidoscopeBlocks.CUT_COPPER_WALL, KaleidoscopeBlocks.COPPER_DOOR, KaleidoscopeBlocks.COPPER_TRAPDOOR);
            entries.addAfter(Blocks.EXPOSED_CUT_COPPER_SLAB, KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL, KaleidoscopeBlocks.EXPOSED_COPPER_DOOR, KaleidoscopeBlocks.EXPOSED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.WEATHERED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL, KaleidoscopeBlocks.WEATHERED_COPPER_DOOR, KaleidoscopeBlocks.WEATHERED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.OXIDIZED_CUT_COPPER_SLAB, KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL, KaleidoscopeBlocks.OXIDIZED_COPPER_DOOR, KaleidoscopeBlocks.OXIDIZED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.WAXED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_COPPER_DOOR, KaleidoscopeBlocks.WAXED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_EXPOSED_COPPER_DOOR, KaleidoscopeBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_WEATHERED_COPPER_DOOR, KaleidoscopeBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR);
            entries.addAfter(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_OXIDIZED_COPPER_DOOR, KaleidoscopeBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addBefore(Blocks.TERRACOTTA, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL, KaleidoscopeBlocks.WHITE_PAINTED_BRICKS, KaleidoscopeBlocks.WHITE_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.WHITE_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.WHITE_PAINTED_BRICK_WALL, KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICKS, KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.LIGHT_GRAY_PAINTED_BRICK_WALL, KaleidoscopeBlocks.GRAY_PAINTED_BRICKS, KaleidoscopeBlocks.GRAY_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.GRAY_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.GRAY_PAINTED_BRICK_WALL, KaleidoscopeBlocks.BLACK_PAINTED_BRICKS, KaleidoscopeBlocks.BLACK_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.BLACK_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.BLACK_PAINTED_BRICK_WALL, KaleidoscopeBlocks.BROWN_PAINTED_BRICKS, KaleidoscopeBlocks.BROWN_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.BROWN_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.BROWN_PAINTED_BRICK_WALL, KaleidoscopeBlocks.RED_PAINTED_BRICKS, KaleidoscopeBlocks.RED_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.RED_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.RED_PAINTED_BRICK_WALL, KaleidoscopeBlocks.ORANGE_PAINTED_BRICKS, KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.ORANGE_PAINTED_BRICK_WALL, KaleidoscopeBlocks.YELLOW_PAINTED_BRICKS, KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.YELLOW_PAINTED_BRICK_WALL, KaleidoscopeBlocks.LIME_PAINTED_BRICKS, KaleidoscopeBlocks.LIME_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.LIME_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.LIME_PAINTED_BRICK_WALL, KaleidoscopeBlocks.GREEN_PAINTED_BRICKS, KaleidoscopeBlocks.GREEN_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.GREEN_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.GREEN_PAINTED_BRICK_WALL, KaleidoscopeBlocks.CYAN_PAINTED_BRICKS, KaleidoscopeBlocks.CYAN_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.CYAN_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.CYAN_PAINTED_BRICK_WALL, KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICKS, KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.LIGHT_BLUE_PAINTED_BRICK_WALL, KaleidoscopeBlocks.BLUE_PAINTED_BRICKS, KaleidoscopeBlocks.BLUE_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.BLUE_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.BLUE_PAINTED_BRICK_WALL, KaleidoscopeBlocks.PURPLE_PAINTED_BRICKS, KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.PURPLE_PAINTED_BRICK_WALL, KaleidoscopeBlocks.MAGENTA_PAINTED_BRICKS, KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.MAGENTA_PAINTED_BRICK_WALL, KaleidoscopeBlocks.PINK_PAINTED_BRICKS, KaleidoscopeBlocks.PINK_PAINTED_BRICK_STAIRS, KaleidoscopeBlocks.PINK_PAINTED_BRICK_SLAB, KaleidoscopeBlocks.PINK_PAINTED_BRICK_WALL);
            entries.addAfter(Blocks.TERRACOTTA, KaleidoscopeBlocks.TERRACOTTA_STAIRS, KaleidoscopeBlocks.TERRACOTTA_SLAB);
            entries.addAfter(Blocks.WHITE_TERRACOTTA, KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS, KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.LIGHT_GRAY_TERRACOTTA, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.GRAY_TERRACOTTA, KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS, KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.BLACK_TERRACOTTA, KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS, KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.BROWN_TERRACOTTA, KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS, KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.RED_TERRACOTTA, KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS, KaleidoscopeBlocks.RED_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.ORANGE_TERRACOTTA, KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS, KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.YELLOW_TERRACOTTA, KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS, KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.LIME_TERRACOTTA, KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS, KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.GREEN_TERRACOTTA, KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS, KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.CYAN_TERRACOTTA, KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS, KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.LIGHT_BLUE_TERRACOTTA, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.BLUE_TERRACOTTA, KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS, KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.PURPLE_TERRACOTTA, KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS, KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.MAGENTA_TERRACOTTA, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.PINK_TERRACOTTA, KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS, KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB);
            entries.addAfter(Blocks.PINK_STAINED_GLASS_PANE, KaleidoscopeBlocks.GLASS_DOOR, KaleidoscopeBlocks.GLASS_TRAPDOOR, KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.JACK_O_LANTERN, KaleidoscopeBlocks.SOUL_JACK_O_LANTERN);
            entries.addBefore(Blocks.HAY_BLOCK, KaleidoscopeBlocks.STICK_BUNDLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(Blocks.BLAST_FURNACE, KaleidoscopeBlocks.KILN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(Items.BAMBOO_CHEST_RAFT, KaleidoscopeItems.CRIMSON_BOAT, KaleidoscopeItems.CRIMSON_CHEST_BOAT, KaleidoscopeItems.WARPED_BOAT, KaleidoscopeItems.WARPED_CHEST_BOAT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> entries.addAfter(Items.CAKE, KaleidoscopeItems.CAKE_SLICE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.addAfter(Items.LEATHER_HORSE_ARMOR, KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.addAfter(Items.DISC_FRAGMENT_5, KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP));
    }
}
