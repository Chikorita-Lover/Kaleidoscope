package com.chikoritalover.kaleidoscope;

import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlockFamilies;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlocks;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItemTags;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItems;
import com.google.gson.JsonElement;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class KaleidoscopeDataGenerator implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(KaleidoscopeLootTableGenerator::new);
        fabricDataGenerator.createPack().addProvider(KaleidoscopeModelGenerator::new);
        fabricDataGenerator.createPack().addProvider(KaleidoscopeRecipeGenerator::new);
    }

    private static class KaleidoscopeLootTableGenerator extends FabricBlockLootTableProvider {
        public KaleidoscopeLootTableGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        private void addVariantDrop(BlockFamily blockFamily, BlockFamily.Variant variant) {
            if (!blockFamily.getVariants().containsKey(variant)) return;
            Block block = blockFamily.getVariant(variant);
            switch (variant) {
                case SLAB -> addDrop(block, slabDrops(block));
                case DOOR -> addDrop(block, doorDrops(block));
                default -> addDrop(block);
            }
        }

        private void addVariantDrops(BlockFamily blockFamily) {
            addVariantDrop(blockFamily, BlockFamily.Variant.STAIRS);
            addVariantDrop(blockFamily, BlockFamily.Variant.SLAB);
            addVariantDrop(blockFamily, BlockFamily.Variant.WALL);
        }

        private void addDrops(BlockFamily blockFamily) {
            addDrop(blockFamily.getBaseBlock());
            addVariantDrops(blockFamily);
        }

        private void addGlassSlabDrop(Block block) {
            addDrop(block, glassSlabDrops(block));
        }

        @Override
        public void generate() {
            addDrop(KaleidoscopeBlocks.POLISHED_CALCITE_WALL);

            addDrop(KaleidoscopeBlocks.CHARCOAL_BLOCK);

            addDrops(KaleidoscopeBlockFamilies.SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER);

            addDrops(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER);
            addDrops(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER);

            addDrops(KaleidoscopeBlockFamilies.BRICK_MOSAIC);

            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.BLACK_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.BLUE_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.BROWN_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.CYAN_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.GRAY_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.GREEN_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.LIME_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.PINK_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.RED_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.WHITE_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_BRICK_WALL);
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_BRICKS);
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_SLAB, slabDrops(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_SLAB));
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_STAIRS);
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_BRICK_WALL);

            this.addDrop(KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.RED_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.RED_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS);
            this.addDrop(KaleidoscopeBlocks.TERRACOTTA_SLAB, slabDrops(KaleidoscopeBlocks.TERRACOTTA_SLAB));
            this.addDrop(KaleidoscopeBlocks.TERRACOTTA_STAIRS);

            addDrop(KaleidoscopeBlocks.END_STONE_SLAB, slabDrops(KaleidoscopeBlocks.END_STONE_SLAB));
            addDrop(KaleidoscopeBlocks.END_STONE_STAIRS);
            addDrop(KaleidoscopeBlocks.END_STONE_WALL);

            addDrop(KaleidoscopeBlocks.POLISHED_END_STONE_WALL);

            this.addDrop(KaleidoscopeBlocks.SOUL_JACK_O_LANTERN);

            this.addDrop(KaleidoscopeBlocks.PACKED_MUD_SLAB, slabDrops(KaleidoscopeBlocks.PACKED_MUD_SLAB));
            this.addDrop(KaleidoscopeBlocks.PACKED_MUD_STAIRS);

            addGlassSlabDrop(KaleidoscopeBlocks.GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.WHITE_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.GRAY_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.BLACK_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.BROWN_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.RED_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.LIME_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.GREEN_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.CYAN_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.BLUE_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_SLAB);
            addGlassSlabDrop(KaleidoscopeBlocks.PINK_STAINED_GLASS_SLAB);

            this.addDrop(KaleidoscopeBlocks.GLASS_DOOR, doorDrops(KaleidoscopeBlocks.GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR));
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, doorDrops(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR));

            this.addDrop(KaleidoscopeBlocks.GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR);
            this.addDrop(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR);
        }

        private LootTable.Builder glassSlabDrops(Block drop) {
            return slabDrops(drop).modifyPools(builder -> builder.conditionally(WITH_SILK_TOUCH));
        }
    }

    private static class KaleidoscopeModelGenerator extends FabricModelProvider {
        private KaleidoscopeModelGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            KaleidoscopeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach((family) -> blockStateModelGenerator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_COPPER).family(KaleidoscopeBlockFamilies.CUT_COPPER).same(Blocks.WAXED_CUT_COPPER).family(KaleidoscopeBlockFamilies.WAXED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.EXPOSED_CUT_COPPER).family(KaleidoscopeBlockFamilies.EXPOSED_CUT_COPPER).same(Blocks.WAXED_EXPOSED_CUT_COPPER).family(KaleidoscopeBlockFamilies.WAXED_EXPOSED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WEATHERED_CUT_COPPER).family(KaleidoscopeBlockFamilies.WEATHERED_CUT_COPPER).same(Blocks.WAXED_WEATHERED_CUT_COPPER).family(KaleidoscopeBlockFamilies.WAXED_WEATHERED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.OXIDIZED_CUT_COPPER).family(KaleidoscopeBlockFamilies.OXIDIZED_CUT_COPPER).same(Blocks.WAXED_OXIDIZED_CUT_COPPER).family(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.SMOOTH_COPPER).same(KaleidoscopeBlocks.WAXED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER).same(KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER).same(KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER).same(KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER);
            blockStateModelGenerator.registerAxisRotated(KaleidoscopeBlocks.CHARCOAL_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(KaleidoscopeBlocks.SOUL_JACK_O_LANTERN, TextureMap.sideEnd(Blocks.PUMPKIN));
            blockStateModelGenerator.registerAxisRotated(KaleidoscopeBlocks.STICK_BUNDLE, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            blockStateModelGenerator.registerCooker(KaleidoscopeBlocks.KILN, TexturedModel.ORIENTABLE_WITH_BOTTOM);
            registerGlassSlab(blockStateModelGenerator, Blocks.GLASS, KaleidoscopeBlocks.GLASS_SLAB);
            KaleidoscopeBlockFamilies.getFamilies().filter(blockFamily -> blockFamily.getGroup().isPresent() && blockFamily.getGroup().get().equals("stained_glass")).forEach((family) -> registerGlassSlab(blockStateModelGenerator, family.getBaseBlock(), family.getVariant(BlockFamily.Variant.SLAB)));
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerDoor(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.registerOrientableTrapdoor(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR);
            blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(KaleidoscopeBlocks.POTION_CAULDRON).coordinate(BlockStateVariantMap.create(LeveledCauldronBlock.LEVEL).register(1, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_LEVEL1.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_level1", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector))).register(2, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_LEVEL2.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_level2", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector))).register(3, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_FULL.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_full", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), blockStateModelGenerator.modelCollector)))));
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(KaleidoscopeItems.CRIMSON_BOAT, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.CRIMSON_CHEST_BOAT, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.WARPED_BOAT, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.WARPED_CHEST_BOAT, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP, Models.GENERATED);
            itemModelGenerator.register(KaleidoscopeItems.CAKE_SLICE, Models.GENERATED);
        }

        private void registerGlassSlab(BlockStateModelGenerator blockStateModelGenerator, Block block, Block slabBlock) {
            BiConsumer<Identifier, Supplier<JsonElement>> modelCollector = blockStateModelGenerator.modelCollector;
            TextureMap textureMap = TextureMap.all(block);
            TextureMap textureMap2 = TextureMap.sideEnd(TextureMap.getSubId(slabBlock, "_side"), textureMap.getTexture(TextureKey.TOP));
            Identifier identifier = Models.SLAB.upload(slabBlock, textureMap2, modelCollector);
            Identifier identifier2 = Models.SLAB_TOP.upload(slabBlock, textureMap2, modelCollector);
            Identifier identifier3 = Models.CUBE_COLUMN.uploadWithoutVariant(slabBlock, "_double", textureMap2, modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slabBlock, identifier, identifier2, identifier3));
        }
    }

    private static class KaleidoscopeRecipeGenerator extends FabricRecipeProvider {
        private KaleidoscopeRecipeGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        public static void offerBrickDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8).input('#', Blocks.BRICKS).input('X', input).pattern("###").pattern("#X#").pattern("###").group("stained_bricks").criterion("has_bricks", RecipeProvider.conditionsFromItem(Blocks.BRICKS)).offerTo(exporter);
        }

        public static void offerCrackingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, output, input, 0.1F, 100);
        }

        public static void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 3).input('#', input).pattern("##").pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerGlassDoorDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output).input(KaleidoscopeBlocks.GLASS_DOOR).input(input).group("stained_glass_door").criterion("has_glass_door", RecipeProvider.conditionsFromItem(KaleidoscopeBlocks.GLASS_DOOR)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, RecipeProvider.convertBetween(output, KaleidoscopeBlocks.GLASS_DOOR)));
        }

        public static void offerGlassTrapdoorDyeingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output).input(KaleidoscopeBlocks.GLASS_TRAPDOOR).input(input).group("stained_glass_trapdoor").criterion("has_glass_trapdoor", RecipeProvider.conditionsFromItem(KaleidoscopeBlocks.GLASS_TRAPDOOR)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, RecipeProvider.convertBetween(output, KaleidoscopeBlocks.GLASS_TRAPDOOR)));
        }

        public static void offerKilning(Consumer<RecipeJsonProvider> exporter, RecipeCategory recipeCategory, ItemConvertible output, ItemConvertible input, float experience, int cookingTime) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), recipeCategory, output, experience, cookingTime, Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER).group(getItemPath(output)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(output) + "_from_kilning"));
        }

        public static void offerSmoothCopperRecipes(Consumer<RecipeJsonProvider> exporter, BlockFamily blockFamily, ItemConvertible input) {
            Block block = blockFamily.getBaseBlock();
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, block, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, block, input, 0.1F, 100);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.STAIRS), block);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.SLAB), block, 2);
        }

        public static void offerTrapdoorRecipe2(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 1).input('#', input).pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerWaxedSmoothCopperRecipes(Consumer<RecipeJsonProvider> exporter, BlockFamily blockFamily, ItemConvertible input, BlockFamily blockFamily2) {
            offerSmoothCopperRecipes(exporter, blockFamily, input);
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getBaseBlock(), blockFamily2.getBaseBlock());
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.STAIRS), blockFamily2.getVariant(BlockFamily.Variant.STAIRS));
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.SLAB), blockFamily2.getVariant(BlockFamily.Variant.SLAB));
        }

        public static void offerWaxingRecipe(Consumer<RecipeJsonProvider> exporter, RecipeCategory recipeCategory, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(recipeCategory, output).input(input).input(Items.HONEYCOMB).group(output.asItem().toString()).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, output.asItem().toString() + "_from_honeycomb"));
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> exporter) {
            KaleidoscopeBlockFamilies.getFamilies().filter(family -> family.shouldGenerateRecipes(FeatureFlags.DEFAULT_ENABLED_FEATURES)).forEach(family -> RecipeProvider.generateFamily(exporter, family));

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICKS, Blocks.PACKED_MUD);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_SLAB, Blocks.PACKED_MUD, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_STAIRS, Blocks.PACKED_MUD);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, Blocks.MUD_BRICK_WALL, Blocks.PACKED_MUD);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_STAIRS, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.CALCITE_WALL, Blocks.CALCITE);

            offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE, Blocks.CALCITE);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, Blocks.CALCITE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, KaleidoscopeBlocks.POLISHED_CALCITE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, KaleidoscopeBlocks.POLISHED_CALCITE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_CALCITE_WALL, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_CALCITE_WALL, KaleidoscopeBlocks.POLISHED_CALCITE);

            offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHARCOAL_BLOCK, Items.CHARCOAL);

            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.CUT_COPPER_WALL, Blocks.COPPER_BLOCK, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.CUT_COPPER_WALL, Blocks.CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_CUT_COPPER);

            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL, Blocks.WAXED_COPPER_BLOCK, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.WAXED_EXPOSED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WAXED_WEATHERED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.WAXED_OXIDIZED_COPPER, 4);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL, Blocks.WAXED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.WAXED_EXPOSED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WAXED_WEATHERED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.WAXED_OXIDIZED_CUT_COPPER);

            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WAXED_CUT_COPPER_WALL, KaleidoscopeBlocks.CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, KaleidoscopeBlocks.EXPOSED_CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, KaleidoscopeBlocks.WEATHERED_CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, KaleidoscopeBlocks.OXIDIZED_CUT_COPPER_WALL);

            offerSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.SMOOTH_COPPER, Blocks.CUT_COPPER);
            offerSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER, Blocks.EXPOSED_CUT_COPPER);
            offerSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER, Blocks.WEATHERED_CUT_COPPER);
            offerSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER, Blocks.OXIDIZED_CUT_COPPER);

            offerWaxedSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER, Blocks.WAXED_CUT_COPPER, KaleidoscopeBlockFamilies.SMOOTH_COPPER);
            offerWaxedSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER);
            offerWaxedSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER);
            offerWaxedSmoothCopperRecipes(exporter, KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER, KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER);

            offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC, Blocks.BRICK_SLAB);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC, Blocks.BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS, Blocks.BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, Blocks.BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS, KaleidoscopeBlocks.BRICK_MOSAIC);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, KaleidoscopeBlocks.BRICK_MOSAIC, 2);

            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.BLACK_STAINED_BRICKS, Items.BLACK_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.BLUE_STAINED_BRICKS, Items.BLUE_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.BROWN_STAINED_BRICKS, Items.BROWN_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.CYAN_STAINED_BRICKS, Items.CYAN_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.GRAY_STAINED_BRICKS, Items.GRAY_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.GREEN_STAINED_BRICKS, Items.GREEN_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS, Items.LIGHT_BLUE_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS, Items.LIGHT_GRAY_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.LIME_STAINED_BRICKS, Items.LIME_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS, Items.MAGENTA_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.ORANGE_STAINED_BRICKS, Items.ORANGE_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.PINK_STAINED_BRICKS, Items.PINK_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.PURPLE_STAINED_BRICKS, Items.PURPLE_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.RED_STAINED_BRICKS, Items.RED_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.WHITE_STAINED_BRICKS, Items.WHITE_DYE);
            offerBrickDyeingRecipe(exporter, KaleidoscopeBlocks.YELLOW_STAINED_BRICKS, Items.YELLOW_DYE);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_STAINED_BRICK_SLAB, KaleidoscopeBlocks.BLACK_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_STAINED_BRICK_SLAB, KaleidoscopeBlocks.BLUE_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_STAINED_BRICK_SLAB, KaleidoscopeBlocks.BROWN_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_STAINED_BRICK_SLAB, KaleidoscopeBlocks.CYAN_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_STAINED_BRICK_SLAB, KaleidoscopeBlocks.GRAY_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_STAINED_BRICK_SLAB, KaleidoscopeBlocks.GREEN_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_SLAB, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_SLAB, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_STAINED_BRICK_SLAB, KaleidoscopeBlocks.LIME_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_SLAB, KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_STAINED_BRICK_SLAB, KaleidoscopeBlocks.ORANGE_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_STAINED_BRICK_SLAB, KaleidoscopeBlocks.PINK_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_STAINED_BRICK_SLAB, KaleidoscopeBlocks.PURPLE_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_STAINED_BRICK_SLAB, KaleidoscopeBlocks.RED_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_STAINED_BRICK_SLAB, KaleidoscopeBlocks.WHITE_STAINED_BRICKS, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_STAINED_BRICK_SLAB, KaleidoscopeBlocks.YELLOW_STAINED_BRICKS, 2);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.BLACK_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.BLUE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.BROWN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.CYAN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.GRAY_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.GREEN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.LIME_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.ORANGE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.PINK_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.PURPLE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.RED_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.WHITE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_STAINED_BRICK_STAIRS, KaleidoscopeBlocks.YELLOW_STAINED_BRICKS);

            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.BLACK_STAINED_BRICK_WALL, KaleidoscopeBlocks.BLACK_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.BLUE_STAINED_BRICK_WALL, KaleidoscopeBlocks.BLUE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.BROWN_STAINED_BRICK_WALL, KaleidoscopeBlocks.BROWN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.CYAN_STAINED_BRICK_WALL, KaleidoscopeBlocks.CYAN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.GRAY_STAINED_BRICK_WALL, KaleidoscopeBlocks.GRAY_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.GREEN_STAINED_BRICK_WALL, KaleidoscopeBlocks.GREEN_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICK_WALL, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICK_WALL, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.LIME_STAINED_BRICK_WALL, KaleidoscopeBlocks.LIME_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.MAGENTA_STAINED_BRICK_WALL, KaleidoscopeBlocks.MAGENTA_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.ORANGE_STAINED_BRICK_WALL, KaleidoscopeBlocks.ORANGE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.PINK_STAINED_BRICK_WALL, KaleidoscopeBlocks.PINK_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.PURPLE_STAINED_BRICK_WALL, KaleidoscopeBlocks.PURPLE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.RED_STAINED_BRICK_WALL, KaleidoscopeBlocks.RED_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.WHITE_STAINED_BRICK_WALL, KaleidoscopeBlocks.WHITE_STAINED_BRICKS);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.YELLOW_STAINED_BRICK_WALL, KaleidoscopeBlocks.YELLOW_STAINED_BRICKS);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE);

            offerCrackingRecipe(exporter, KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
            offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_SLAB, Blocks.END_STONE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_STAIRS, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.END_STONE_WALL, Blocks.END_STONE);

            offerPolishedStoneRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE, Blocks.END_STONE);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_SLAB, Blocks.END_STONE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_SLAB, KaleidoscopeBlocks.POLISHED_END_STONE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS, KaleidoscopeBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_END_STONE_WALL, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_END_STONE_WALL, KaleidoscopeBlocks.POLISHED_END_STONE);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICKS, KaleidoscopeBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_SLAB, KaleidoscopeBlocks.POLISHED_END_STONE, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_STAIRS, KaleidoscopeBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, Blocks.END_STONE_BRICK_WALL, KaleidoscopeBlocks.POLISHED_END_STONE);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB, Blocks.BLACK_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB, Blocks.BLUE_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB, Blocks.BROWN_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB, Blocks.CYAN_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB, Blocks.GRAY_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB, Blocks.GREEN_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, Blocks.LIGHT_BLUE_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, Blocks.LIGHT_GRAY_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB, Blocks.LIME_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB, Blocks.MAGENTA_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB, Blocks.ORANGE_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB, Blocks.PINK_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB, Blocks.PURPLE_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_TERRACOTTA_SLAB, Blocks.RED_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB, Blocks.WHITE_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB, Blocks.YELLOW_TERRACOTTA, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.TERRACOTTA_SLAB, Blocks.TERRACOTTA, 2);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS, Blocks.BLACK_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS, Blocks.BLUE_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS, Blocks.BROWN_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS, Blocks.CYAN_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS, Blocks.GRAY_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS, Blocks.GREEN_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, Blocks.LIGHT_BLUE_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, Blocks.LIGHT_GRAY_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS, Blocks.LIME_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS, Blocks.MAGENTA_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS, Blocks.ORANGE_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS, Blocks.PINK_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS, Blocks.PURPLE_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS, Blocks.RED_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS, Blocks.WHITE_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS, Blocks.YELLOW_TERRACOTTA);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.TERRACOTTA_STAIRS, Blocks.TERRACOTTA);

            offerChiseledBlockRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHISELED_PURPUR, Blocks.PURPUR_SLAB);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHISELED_PURPUR, Blocks.PURPUR_BLOCK);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SOUL_JACK_O_LANTERN).input('#', Blocks.CARVED_PUMPKIN).input('S', Blocks.SOUL_TORCH).pattern("#").pattern("S").criterion("has_carved_pumpkin", conditionsFromItem(Blocks.CARVED_PUMPKIN)).offerTo(exporter);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
            offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD, 2);
            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD);

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.STICK_BUNDLE).input('#', Items.STICK).pattern("###").pattern("###").pattern("###").criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, 6).input('W', Blocks.RED_NETHER_BRICKS).input('#', Items.NETHER_BRICK).pattern("W#W").pattern("W#W").criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(exporter);

            ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.KILN).input('#', Items.BRICK).input('X', Blocks.FURNACE).pattern(" # ").pattern("#X#").pattern(" # ").criterion(hasItem(Blocks.FURNACE), conditionsFromItem(Blocks.FURNACE)).offerTo(exporter);

            offerDoorRecipe(exporter, KaleidoscopeBlocks.GLASS_DOOR, Blocks.GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, Blocks.BLACK_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, Blocks.BLUE_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, Blocks.BROWN_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, Blocks.CYAN_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, Blocks.GRAY_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, Blocks.GREEN_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, Blocks.LIGHT_BLUE_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, Blocks.LIGHT_GRAY_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, Blocks.LIME_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, Blocks.MAGENTA_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, Blocks.ORANGE_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, Blocks.PINK_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, Blocks.PURPLE_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, Blocks.RED_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, Blocks.WHITE_STAINED_GLASS);
            offerDoorRecipe(exporter, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, Blocks.YELLOW_STAINED_GLASS);

            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, Items.BLACK_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, Items.BLUE_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, Items.BROWN_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, Items.CYAN_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, Items.GRAY_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, Items.GREEN_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, Items.LIGHT_BLUE_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, Items.LIGHT_GRAY_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, Items.LIME_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, Items.MAGENTA_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, Items.ORANGE_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, Items.PINK_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, Items.PURPLE_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, Items.RED_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, Items.WHITE_DYE);
            offerGlassDoorDyeingRecipe(exporter, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, Items.YELLOW_DYE);

            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.GLASS_TRAPDOOR, Blocks.GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, Blocks.BLACK_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, Blocks.BLUE_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, Blocks.BROWN_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, Blocks.CYAN_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, Blocks.GRAY_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, Blocks.GREEN_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, Blocks.LIGHT_BLUE_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, Blocks.LIGHT_GRAY_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, Blocks.LIME_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, Blocks.MAGENTA_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, Blocks.ORANGE_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR, Blocks.PINK_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, Blocks.PURPLE_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, Blocks.RED_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, Blocks.WHITE_STAINED_GLASS);
            offerTrapdoorRecipe2(exporter, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, Blocks.YELLOW_STAINED_GLASS);

            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, Items.BLACK_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, Items.BLUE_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, Items.BROWN_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, Items.CYAN_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, Items.GRAY_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, Items.GREEN_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, Items.LIGHT_BLUE_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, Items.LIGHT_GRAY_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, Items.LIME_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, Items.MAGENTA_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, Items.ORANGE_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR, Items.PINK_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, Items.PURPLE_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, Items.RED_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, Items.WHITE_DYE);
            offerGlassTrapdoorDyeingRecipe(exporter, KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, Items.YELLOW_DYE);

            offerBoatRecipe(exporter, KaleidoscopeItems.CRIMSON_BOAT, Blocks.CRIMSON_PLANKS);
            offerBoatRecipe(exporter, KaleidoscopeItems.WARPED_BOAT, Blocks.WARPED_PLANKS);

            offerChestBoatRecipe(exporter, KaleidoscopeItems.CRIMSON_CHEST_BOAT, KaleidoscopeItems.CRIMSON_BOAT);
            offerChestBoatRecipe(exporter, KaleidoscopeItems.WARPED_CHEST_BOAT, KaleidoscopeItems.WARPED_BOAT);

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STICK, 9).input(KaleidoscopeBlocks.STICK_BUNDLE).group(getItemPath(Items.STICK)).criterion(hasItem(KaleidoscopeBlocks.STICK_BUNDLE), conditionsFromItem(KaleidoscopeBlocks.STICK_BUNDLE)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, "stick_from_bundle"));

            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GREEN_DYE, 2).input(ConventionalItemTags.BLUE_DYES).input(ConventionalItemTags.YELLOW_DYES).criterion(hasItem(Items.BLUE_DYE), RecipeProvider.conditionsFromTag(ConventionalItemTags.BLUE_DYES)).criterion(hasItem(Items.YELLOW_DYE), RecipeProvider.conditionsFromTag(ConventionalItemTags.YELLOW_DYES)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, "green_dye_from_blue_yellow_dye"));

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1F, 200).criterion(hasItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(Items.IRON_NUGGET) + "_from_chainmail_horse_armor_smelting"));
            CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1F, 100).criterion(hasItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(Items.IRON_NUGGET) + "_from_chainmail_horse_armor_blasting"));

            offerNetheriteUpgradeRecipe(exporter, Items.DIAMOND_HORSE_ARMOR, RecipeCategory.COMBAT, KaleidoscopeItems.NETHERITE_HORSE_ARMOR);

            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MUSIC_DISC_PIGSTEP).input('S', KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).pattern("SSS").pattern("SSS").pattern("SSS").criterion(hasItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP), RecipeProvider.conditionsFromItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(Items.MUSIC_DISC_PIGSTEP)));

            //Base kilning recipes
            CookingRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.SAND), RecipeCategory.BUILDING_BLOCKS, Blocks.GLASS, 0.1F, 100, Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER).group("glass").criterion(hasItem(Blocks.SAND), conditionsFromTag(ItemTags.SAND)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(Blocks.GLASS) + "_from_kilning"));
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, Blocks.COBBLESTONE, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_SANDSTONE, Blocks.SANDSTONE, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_RED_SANDSTONE, Blocks.RED_SANDSTONE, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_STONE, Blocks.STONE, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_QUARTZ, Blocks.QUARTZ_BLOCK, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.MISC, Items.BRICK, Items.CLAY_BALL, 0.3F, 100);
            offerKilning(exporter, RecipeCategory.MISC, Items.NETHER_BRICK, Blocks.NETHERRACK, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_NETHER_BRICKS, Blocks.NETHER_BRICKS, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_BASALT, Blocks.BASALT, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.TERRACOTTA, Blocks.CLAY, 0.35F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_STONE_BRICKS, Blocks.STONE_BRICKS, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRACKED_DEEPSLATE_TILES, Blocks.DEEPSLATE_TILES, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.YELLOW_GLAZED_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.LIME_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PINK_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GRAY_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.CYAN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BLUE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.BROWN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.GREEN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.RED_GLAZED_TERRACOTTA, Blocks.RED_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.MISC, Items.GREEN_DYE, Blocks.CACTUS, 1.0F, 100);
            CookingRecipeJsonBuilder.create(Ingredient.fromTag(KaleidoscopeItemTags.BURNS_INTO_CHARCOAL), RecipeCategory.MISC, Items.CHARCOAL, 0.15F, 100, Kaleidoscope.KILN_COOKING_RECIPE_SERIALIZER).group(getItemPath(Items.CHARCOAL)).criterion("has_wood", conditionsFromTag(KaleidoscopeItemTags.BURNS_INTO_CHARCOAL)).offerTo(exporter, new Identifier(Kaleidoscope.MODID, getItemPath(Items.CHARCOAL) + "_from_kilning"));
            offerKilning(exporter, RecipeCategory.MISC, Items.POPPED_CHORUS_FRUIT, Items.CHORUS_FRUIT, 0.1F, 100);
            offerKilning(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SPONGE, Blocks.WET_SPONGE, 0.15F, 100);
            offerKilning(exporter, RecipeCategory.MISC, Items.LIME_DYE, Blocks.SEA_PICKLE, 0.1F, 100);
        }
    }
}
