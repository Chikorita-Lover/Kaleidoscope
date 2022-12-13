package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static net.minecraft.data.client.BlockStateModelGenerator.createDoorBlockState;

public class ChikoritaLoverModDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(ChikoritaLoverModModelGenerator::new);
        fabricDataGenerator.addProvider(ChikoritaLoverModRecipeGenerator::new);
    }

    private static class ChikoritaLoverModModelGenerator extends FabricModelProvider {
        private ChikoritaLoverModModelGenerator(FabricDataGenerator generator) {
            super(generator);
        }

        public void registerOxidizableDoor(Block doorBlock, Block waxedDoorBlock, BlockStateModelGenerator blockStateModelGenerator) {
            TextureMap textureMap = TextureMap.topBottom(doorBlock);
            Identifier identifier = Models.DOOR_BOTTOM_LEFT.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier2 = Models.DOOR_BOTTOM_LEFT_OPEN.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier3 = Models.DOOR_BOTTOM_RIGHT.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier4 = Models.DOOR_BOTTOM_RIGHT_OPEN.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier5 = Models.DOOR_TOP_LEFT.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier6 = Models.DOOR_TOP_LEFT_OPEN.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier7 = Models.DOOR_TOP_RIGHT.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier8 = Models.DOOR_TOP_RIGHT_OPEN.upload(doorBlock, textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.registerItemModel(doorBlock.asItem());
            blockStateModelGenerator.blockStateCollector.accept(createDoorBlockState(doorBlock, identifier, identifier2, identifier3, identifier4, identifier5, identifier6, identifier7, identifier8));
            blockStateModelGenerator.blockStateCollector.accept(createDoorBlockState(waxedDoorBlock, identifier, identifier2, identifier3, identifier4, identifier5, identifier6, identifier7, identifier8));
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            this.registerOxidizableDoor(ModBlocks.COPPER_DOOR, ModBlocks.WAXED_COPPER_DOOR, blockStateModelGenerator);
            this.registerOxidizableDoor(ModBlocks.EXPOSED_COPPER_DOOR, ModBlocks.WAXED_EXPOSED_COPPER_DOOR, blockStateModelGenerator);
            this.registerOxidizableDoor(ModBlocks.WEATHERED_COPPER_DOOR, ModBlocks.WAXED_WEATHERED_COPPER_DOOR, blockStateModelGenerator);
            this.registerOxidizableDoor(ModBlocks.OXIDIZED_COPPER_DOOR, ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, blockStateModelGenerator);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ModItems.WAXED_COPPER_DOOR, ModItems.COPPER_DOOR, Models.GENERATED);
            itemModelGenerator.register(ModItems.WAXED_EXPOSED_COPPER_DOOR, ModItems.EXPOSED_COPPER_DOOR, Models.GENERATED);
            itemModelGenerator.register(ModItems.WAXED_WEATHERED_COPPER_DOOR, ModItems.WEATHERED_COPPER_DOOR, Models.GENERATED);
            itemModelGenerator.register(ModItems.WAXED_OXIDIZED_COPPER_DOOR, ModItems.OXIDIZED_COPPER_DOOR, Models.GENERATED);
        }
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

        public static void offerWaxingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(output).input(input).input(Items.HONEYCOMB).group(output.asItem().toString()).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, output.asItem().toString() + "_from_honeycomb");
        }

        @Override
        protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
            ShapedRecipeJsonBuilder.create(ModBlocks.DIRT_BRICKS, 4).input('#', ItemTags.DIRT).pattern("##").pattern("##").criterion(hasItem(Blocks.DIRT), conditionsFromTag(ItemTags.DIRT)).offerTo(exporter);
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

            offerDoorRecipe(exporter, ModBlocks.COPPER_DOOR, Items.COPPER_INGOT);

            offerWaxingRecipe(exporter, ModBlocks.WAXED_COPPER_DOOR, ModBlocks.COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_EXPOSED_COPPER_DOOR, ModBlocks.EXPOSED_COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_WEATHERED_COPPER_DOOR, ModBlocks.WEATHERED_COPPER_DOOR);
            offerWaxingRecipe(exporter, ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, ModBlocks.OXIDIZED_COPPER_DOOR);
        }
    }
}
