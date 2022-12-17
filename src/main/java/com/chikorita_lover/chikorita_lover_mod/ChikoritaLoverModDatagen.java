package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;

import java.util.function.Consumer;

public class ChikoritaLoverModDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ChikoritaLoverModRecipeGenerator::new);
    }

    private static class ChikoritaLoverModRecipeGenerator extends FabricRecipeProvider {
        private ChikoritaLoverModRecipeGenerator(FabricDataGenerator generator) {
            super(generator);
        }

        public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(output, 4).input('#', input).pattern("#  ").pattern("## ").pattern("###").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(output, 3).input('#', input).pattern("##").pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerTrapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {

        }

        public static void offerWaxingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(output).input(input).input(Items.HONEYCOMB).group(output.asItem().toString()).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, output.asItem().toString() + "_from_honeycomb");
        }

        @Override
        protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
            offerPolishedStoneRecipe(exporter, ModBlocks.DIRT_BRICKS, Blocks.DIRT);
            offerSlabRecipe(exporter, ModBlocks.DIRT_BRICK_SLAB, ModBlocks.DIRT_BRICKS);
            offerStairsRecipe(exporter, ModBlocks.DIRT_BRICK_STAIRS, ModBlocks.DIRT_BRICKS);
            offerWallRecipe(exporter, ModBlocks.DIRT_BRICK_WALL, ModBlocks.DIRT_BRICKS);
            
            offerPolishedStoneRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerSlabRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE);
            offerStairsRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);
            offerWallRecipe(exporter, ModBlocks.POLISHED_CALCITE_WALL, ModBlocks.POLISHED_CALCITE);

            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_WALL, ModBlocks.POLISHED_CALCITE);

            offerWallRecipe(exporter, ModBlocks.CUT_COPPER_WALL, Blocks.CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_CUT_COPPER);

            offerStonecuttingRecipe(exporter, ModBlocks.CUT_COPPER_WALL, Blocks.COPPER_BLOCK, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.CUT_COPPER_WALL, Blocks.CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.EXPOSED_CUT_COPPER_WALL, Blocks.EXPOSED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.WEATHERED_CUT_COPPER_WALL, Blocks.WEATHERED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.OXIDIZED_CUT_COPPER_WALL, Blocks.OXIDIZED_CUT_COPPER);

            offerWallRecipe(exporter, ModBlocks.WAXED_CUT_COPPER_WALL, Blocks.WAXED_CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.WAXED_EXPOSED_CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WAXED_WEATHERED_CUT_COPPER);
            offerWallRecipe(exporter, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.WAXED_OXIDIZED_CUT_COPPER);

            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_CUT_COPPER_WALL, Blocks.WAXED_COPPER_BLOCK, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.WAXED_EXPOSED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WAXED_WEATHERED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.WAXED_OXIDIZED_COPPER, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_CUT_COPPER_WALL, Blocks.WAXED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, Blocks.WAXED_EXPOSED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, Blocks.WAXED_WEATHERED_CUT_COPPER);
            offerStonecuttingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, Blocks.WAXED_OXIDIZED_CUT_COPPER);

            offerWaxingRecipe(exporter, ModBlocks.WAXED_CUT_COPPER_WALL, ModBlocks.CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, ModBlocks.EXPOSED_CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, ModBlocks.WEATHERED_CUT_COPPER_WALL);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, ModBlocks.OXIDIZED_CUT_COPPER_WALL);

            offerCrackingRecipe(exporter, ModBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
            offerChiseledBlockRecipe(exporter, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB);
            offerStonecuttingRecipe(exporter, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);

            ShapedRecipeJsonBuilder.create(ModBlocks.RED_NETHER_BRICK_FENCE, 6).input('W', Blocks.RED_NETHER_BRICKS).input('#', Items.NETHER_BRICK).pattern("W#W").pattern("W#W").criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(exporter);

            offerDoorRecipe(exporter, ModBlocks.COPPER_DOOR, Items.COPPER_INGOT);

            offerWaxingRecipe(exporter, ModBlocks.WAXED_COPPER_DOOR, ModBlocks.COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_EXPOSED_COPPER_DOOR, ModBlocks.EXPOSED_COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_WEATHERED_COPPER_DOOR, ModBlocks.WEATHERED_COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, ModBlocks.OXIDIZED_COPPER_DOOR);

            ShapedRecipeJsonBuilder.create(ModBlocks.COPPER_TRAPDOOR).input('#', Items.COPPER_INGOT).pattern("##").pattern("##").criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);

            offerWaxingRecipe(exporter, ModBlocks.WAXED_COPPER_TRAPDOOR, ModBlocks.COPPER_TRAPDOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR, ModBlocks.EXPOSED_COPPER_TRAPDOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR, ModBlocks.WEATHERED_COPPER_TRAPDOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, ModBlocks.OXIDIZED_COPPER_TRAPDOOR);
        }
    }
}
