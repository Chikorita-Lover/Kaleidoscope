package net.chikorita_lover.kaleidoscope.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class KaleidoscopeItemGroups {
    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addBefore(Blocks.DEEPSLATE, Blocks.CALCITE, KaleidoscopeBlocks.CALCITE_STAIRS, KaleidoscopeBlocks.CALCITE_SLAB, KaleidoscopeBlocks.CALCITE_WALL, KaleidoscopeBlocks.POLISHED_CALCITE, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, KaleidoscopeBlocks.POLISHED_CALCITE_WALL);
            entries.addAfter(Blocks.BRICKS, KaleidoscopeBlocks.BRICK_MOSAIC);
            entries.addAfter(Blocks.BRICK_STAIRS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS);
            entries.addAfter(Blocks.BRICK_SLAB, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB);
            entries.addAfter(Blocks.PACKED_MUD, KaleidoscopeBlocks.PACKED_MUD_STAIRS, KaleidoscopeBlocks.PACKED_MUD_SLAB);
            entries.addAfter(Blocks.RED_NETHER_BRICKS, KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS);
            entries.addAfter(Blocks.RED_NETHER_BRICK_WALL, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS);
            entries.addAfter(Blocks.SMOOTH_BASALT, KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS, KaleidoscopeBlocks.SMOOTH_BASALT_SLAB, KaleidoscopeBlocks.SMOOTH_BASALT_WALL);
            entries.addAfter(Blocks.END_STONE, KaleidoscopeBlocks.END_STONE_STAIRS, KaleidoscopeBlocks.END_STONE_SLAB, KaleidoscopeBlocks.END_STONE_WALL, KaleidoscopeBlocks.POLISHED_END_STONE, KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS, KaleidoscopeBlocks.POLISHED_END_STONE_SLAB, KaleidoscopeBlocks.POLISHED_END_STONE_WALL);
            entries.addAfter(Blocks.PURPUR_PILLAR, KaleidoscopeBlocks.CHISELED_PURPUR);
            entries.addAfter(Blocks.COAL_BLOCK, KaleidoscopeBlocks.CHARCOAL_BLOCK);
            entries.addAfter(Blocks.CUT_COPPER_SLAB, KaleidoscopeBlocks.CUT_COPPER_WALL, KaleidoscopeBlocks.SMOOTH_COPPER, KaleidoscopeBlocks.SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.EXPOSED_CUT_COPPER_SLAB, KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL, KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER, KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.WEATHERED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL, KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER, KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.OXIDIZED_CUT_COPPER_SLAB, KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL, KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER, KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.WAXED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.WAXED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER_SLAB);
            entries.addAfter(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER_STAIRS, KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER_SLAB);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addBefore(Blocks.TERRACOTTA, Blocks.BRICKS, KaleidoscopeBlocks.BRICK_MOSAIC, Blocks.BRICK_STAIRS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS, Blocks.BRICK_SLAB, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, Blocks.BRICK_WALL);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.WHITE_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.LIGHT_GRAY_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.GRAY_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.BLACK_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.BROWN_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.RED_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.ORANGE_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.YELLOW_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.LIME_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.GREEN_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.CYAN_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.LIGHT_BLUE_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.BLUE_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.PURPLE_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.MAGENTA_STAINED_BRICKS);
            addStainedBricks(entries, KaleidoscopeBlockFamilies.PINK_STAINED_BRICKS);
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
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.WHITE_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.LIGHT_GRAY_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.GRAY_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.BLACK_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.BROWN_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.RED_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.ORANGE_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.YELLOW_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.LIME_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.GREEN_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.CYAN_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.LIGHT_BLUE_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.BLUE_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.PURPLE_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.MAGENTA_STAINED_GLASS);
            extendStainedGlass(entries, KaleidoscopeBlockFamilies.PINK_STAINED_GLASS);
            entries.addAfter(Blocks.PINK_STAINED_GLASS_PANE, KaleidoscopeBlocks.GLASS_DOOR, KaleidoscopeBlocks.GLASS_TRAPDOOR, KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.JACK_O_LANTERN, KaleidoscopeBlocks.SOUL_JACK_O_LANTERN);
            entries.addBefore(Blocks.HAY_BLOCK, KaleidoscopeBlocks.STICK_BUNDLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addBefore(Blocks.FURNACE, KaleidoscopeBlocks.FIREWORKS_TABLE);
            entries.addAfter(Blocks.BLAST_FURNACE, KaleidoscopeBlocks.KILN);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.addAfter(Items.BAMBOO_CHEST_RAFT, KaleidoscopeItems.CRIMSON_CHEST_BOAT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(Items.BAMBOO_CHEST_RAFT, KaleidoscopeItems.CRIMSON_BOAT, KaleidoscopeItems.CRIMSON_CHEST_BOAT, KaleidoscopeItems.WARPED_BOAT, KaleidoscopeItems.WARPED_CHEST_BOAT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> entries.addAfter(Items.CAKE, KaleidoscopeItems.CAKE_SLICE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.DISC_FRAGMENT_5, KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP);
            entries.addBefore(Items.EXPERIENCE_BOTTLE, KaleidoscopeItems.LARGE_BALL_FIREWORK_SHELL, KaleidoscopeItems.STAR_FIREWORK_SHELL, KaleidoscopeItems.CREEPER_FIREWORK_SHELL, KaleidoscopeItems.BURST_FIREWORK_SHELL);
        });
    }

    private static void addStainedBricks(FabricItemGroupEntries entries, BlockFamily blockFamily) {
        entries.addBefore(Blocks.TERRACOTTA, blockFamily.getBaseBlock(), blockFamily.getVariant(BlockFamily.Variant.STAIRS), blockFamily.getVariant(BlockFamily.Variant.SLAB), blockFamily.getVariant(BlockFamily.Variant.WALL));
    }

    private static void extendStainedGlass(FabricItemGroupEntries entries, BlockFamily blockFamily) {
        entries.addAfter(blockFamily.getBaseBlock(), blockFamily.getVariant(BlockFamily.Variant.SLAB));
    }
}
