package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBlockFamilies;
import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import com.chikorita_lover.chikorita_lover_mod.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

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

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
            ModBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach((family) -> blockStateModelGenerator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.CUT_COPPER).family(ModBlockFamilies.CUT_COPPER).same(Blocks.WAXED_CUT_COPPER).family(ModBlockFamilies.WAXED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.EXPOSED_CUT_COPPER).family(ModBlockFamilies.EXPOSED_CUT_COPPER).same(Blocks.WAXED_EXPOSED_CUT_COPPER).family(ModBlockFamilies.WAXED_EXPOSED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.WEATHERED_CUT_COPPER).family(ModBlockFamilies.WEATHERED_CUT_COPPER).same(Blocks.WAXED_WEATHERED_CUT_COPPER).family(ModBlockFamilies.WAXED_WEATHERED_CUT_COPPER);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.OXIDIZED_CUT_COPPER).family(ModBlockFamilies.OXIDIZED_CUT_COPPER).same(Blocks.WAXED_OXIDIZED_CUT_COPPER).family(ModBlockFamilies.WAXED_OXIDIZED_CUT_COPPER);
            blockStateModelGenerator.registerAxisRotated(ModBlocks.END_STONE_PILLAR, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            blockStateModelGenerator.registerAxisRotated(ModBlocks.STICK_BUNDLE, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            blockStateModelGenerator.registerCooker(ModBlocks.KILN, TexturedModel.ORIENTABLE_WITH_BOTTOM);
            registerOxidizableDoor(blockStateModelGenerator, ModBlocks.COPPER_DOOR, ModBlocks.WAXED_COPPER_DOOR);
            registerOxidizableDoor(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_DOOR, ModBlocks.WAXED_EXPOSED_COPPER_DOOR);
            registerOxidizableDoor(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_DOOR, ModBlocks.WAXED_WEATHERED_COPPER_DOOR);
            registerOxidizableDoor(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_DOOR, ModBlocks.WAXED_OXIDIZED_COPPER_DOOR);
            registerOxidizableTrapdoor(blockStateModelGenerator, ModBlocks.COPPER_TRAPDOOR, ModBlocks.WAXED_COPPER_TRAPDOOR);
            registerOxidizableTrapdoor(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_TRAPDOOR, ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR);
            registerOxidizableTrapdoor(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_TRAPDOOR, ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR);
            registerOxidizableTrapdoor(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_TRAPDOOR, ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR);
        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(ModItems.CRIMSON_BOAT, Models.GENERATED);
            itemModelGenerator.register(ModItems.CRIMSON_CHEST_BOAT, Models.GENERATED);
            itemModelGenerator.register(ModItems.WARPED_BOAT, Models.GENERATED);
            itemModelGenerator.register(ModItems.WARPED_CHEST_BOAT, Models.GENERATED);
            itemModelGenerator.register(ModItems.CHAINMAIL_HORSE_ARMOR, Models.GENERATED);
            itemModelGenerator.register(ModItems.CAKE_SLICE, Models.GENERATED);
        }

        public final void registerItemModel(BlockStateModelGenerator blockStateModelGenerator, Block block, String texturePath) {
            Item item = block.asItem();
            Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(new Identifier(ChikoritaLoverMod.MODID, "item/" + texturePath)), blockStateModelGenerator.modelCollector);
        }

        public void registerOxidizableDoor(BlockStateModelGenerator blockStateModelGenerator, Block doorBlock, Block waxedDoorBlock) {
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
            registerItemModel(blockStateModelGenerator, waxedDoorBlock, doorBlock.asItem().toString());
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createDoorBlockState(doorBlock, identifier, identifier2, identifier3, identifier4, identifier5, identifier6, identifier7, identifier8));
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createDoorBlockState(waxedDoorBlock, identifier, identifier2, identifier3, identifier4, identifier5, identifier6, identifier7, identifier8));
        }

        public void registerOxidizableTrapdoor(BlockStateModelGenerator blockStateModelGenerator, Block trapdoorBlock, Block waxedTrapdoorBlock) {
            TextureMap textureMap = TextureMap.texture(trapdoorBlock);
            Identifier identifier = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_TOP.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier2 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_BOTTOM.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
            Identifier identifier3 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_OPEN.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createOrientableTrapdoorBlockState(trapdoorBlock, identifier, identifier2, identifier3));
            blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createOrientableTrapdoorBlockState(waxedTrapdoorBlock, identifier, identifier2, identifier3));
            blockStateModelGenerator.registerParentedItemModel(trapdoorBlock, identifier2);
            blockStateModelGenerator.registerParentedItemModel(waxedTrapdoorBlock, identifier2);
        }
    }

    private static class ChikoritaLoverModRecipeGenerator extends FabricRecipeProvider {
        private ChikoritaLoverModRecipeGenerator(FabricDataGenerator generator) {
            super(generator);
        }

        public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(output, 4).input('#', input).pattern("#  ").pattern("## ").pattern("###").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerCrackingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), output, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
            offerKilning(exporter, output, input, 0.1F, 100);
        }

        public static void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(output, 3).input('#', input).pattern("##").pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        }

        public static void offerKilning(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, float experience, int cookingTime) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER).group(getItemPath(output)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(output) + "_from_kilning"));
        }

        public static void offerStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            offerStonecuttingRecipe(exporter, output, input, 1);
        }

        public static void offerStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, int count) {
            SingleItemRecipeJsonBuilder var10000 = SingleItemRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(input), output, count).criterion(hasItem(input), conditionsFromItem(input));
            String var10002 = convertBetween(output, input);
            var10000.offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, var10002 + "_stonecutting"));
        }

        public static void offerWaxingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
            ShapelessRecipeJsonBuilder.create(output).input(input).input(Items.HONEYCOMB).group(output.asItem().toString()).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, output.asItem().toString() + "_from_honeycomb"));
        }

        @Override
        protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
            offerStonecuttingRecipe(exporter, Blocks.MUD_BRICKS, Blocks.PACKED_MUD);
            offerStonecuttingRecipe(exporter, Blocks.MUD_BRICK_SLAB, Blocks.PACKED_MUD, 2);
            offerStonecuttingRecipe(exporter, Blocks.MUD_BRICK_STAIRS, Blocks.PACKED_MUD);
            offerStonecuttingRecipe(exporter, Blocks.MUD_BRICK_WALL, Blocks.PACKED_MUD);

            offerPolishedStoneRecipe(exporter, ModBlocks.DIRT_BRICKS, Blocks.DIRT);
            offerSlabRecipe(exporter, ModBlocks.DIRT_BRICK_SLAB, ModBlocks.DIRT_BRICKS);
            offerStairsRecipe(exporter, ModBlocks.DIRT_BRICK_STAIRS, ModBlocks.DIRT_BRICKS);
            offerWallRecipe(exporter, ModBlocks.DIRT_BRICK_WALL, ModBlocks.DIRT_BRICKS);

            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICKS, Blocks.DIRT);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_SLAB, Blocks.DIRT, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_SLAB, ModBlocks.DIRT_BRICKS, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_STAIRS, Blocks.DIRT);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_STAIRS, ModBlocks.DIRT_BRICKS);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_WALL, Blocks.DIRT);
            offerStonecuttingRecipe(exporter, ModBlocks.DIRT_BRICK_WALL, ModBlocks.DIRT_BRICKS);

            offerPolishedStoneRecipe(exporter, ModBlocks.STONE_TILES, Blocks.STONE_BRICKS);
            offerSlabRecipe(exporter, ModBlocks.STONE_TILE_SLAB, ModBlocks.STONE_TILES);
            offerStairsRecipe(exporter, ModBlocks.STONE_TILE_STAIRS, ModBlocks.STONE_TILES);

            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILES, Blocks.STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILES, Blocks.STONE_BRICKS);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_SLAB, Blocks.STONE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_SLAB, Blocks.STONE_BRICKS, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_SLAB, ModBlocks.STONE_TILES, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_STAIRS, Blocks.STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_STAIRS, Blocks.STONE_BRICKS);
            offerStonecuttingRecipe(exporter, ModBlocks.STONE_TILE_STAIRS, ModBlocks.STONE_TILES);

            offerSlabRecipe(exporter, ModBlocks.CALCITE_SLAB, Blocks.CALCITE);
            offerStairsRecipe(exporter, ModBlocks.CALCITE_STAIRS, Blocks.CALCITE);
            offerWallRecipe(exporter, ModBlocks.CALCITE_WALL, Blocks.CALCITE);

            offerStonecuttingRecipe(exporter, ModBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.CALCITE_STAIRS, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.CALCITE_WALL, Blocks.CALCITE);

            offerPolishedStoneRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerSlabRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE);
            offerStairsRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);

            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, Blocks.CALCITE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);

            offerSlabRecipe(exporter, ModBlocks.TUFF_SLAB, Blocks.TUFF);
            offerStairsRecipe(exporter, ModBlocks.TUFF_STAIRS, Blocks.TUFF);
            offerWallRecipe(exporter, ModBlocks.TUFF_WALL, Blocks.TUFF);

            offerStonecuttingRecipe(exporter, ModBlocks.TUFF_SLAB, Blocks.TUFF, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.TUFF_STAIRS, Blocks.TUFF);
            offerStonecuttingRecipe(exporter, ModBlocks.TUFF_WALL, Blocks.TUFF);

            offerPolishedStoneRecipe(exporter, ModBlocks.POLISHED_TUFF, Blocks.TUFF);
            offerSlabRecipe(exporter, ModBlocks.POLISHED_TUFF_SLAB, ModBlocks.POLISHED_TUFF);
            offerStairsRecipe(exporter, ModBlocks.POLISHED_TUFF_STAIRS, ModBlocks.POLISHED_TUFF);

            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_TUFF, Blocks.TUFF);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_TUFF_SLAB, Blocks.TUFF, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_TUFF_SLAB, ModBlocks.POLISHED_TUFF, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_TUFF_STAIRS, Blocks.TUFF);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_TUFF_STAIRS, ModBlocks.POLISHED_TUFF);

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

            ShapedRecipeJsonBuilder.create(ModBlocks.LAPIS_LAZULI_TILES, 16).input('#', Blocks.LAPIS_BLOCK).pattern("##").pattern("##").criterion(hasItem(Blocks.LAPIS_BLOCK), conditionsFromItem(Blocks.LAPIS_BLOCK)).offerTo(exporter);
            offerSlabRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_SLAB, ModBlocks.LAPIS_LAZULI_TILES);
            offerStairsRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_STAIRS, ModBlocks.LAPIS_LAZULI_TILES);

            offerStonecuttingRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILES, Blocks.LAPIS_BLOCK, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_SLAB, Blocks.LAPIS_BLOCK, 8);
            offerStonecuttingRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_SLAB, ModBlocks.LAPIS_LAZULI_TILES, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_STAIRS, Blocks.LAPIS_BLOCK, 4);
            offerStonecuttingRecipe(exporter, ModBlocks.LAPIS_LAZULI_TILE_STAIRS, ModBlocks.LAPIS_LAZULI_TILES);

            offerStonecuttingRecipe(exporter, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE);
            offerStonecuttingRecipe(exporter, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE, 2);
            offerStonecuttingRecipe(exporter, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE);

            offerCrackingRecipe(exporter, ModBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);
            offerChiseledBlockRecipe(exporter, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB);
            offerStonecuttingRecipe(exporter, ModBlocks.CHISELED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);

            offerPolishedStoneRecipe(exporter, ModBlocks.POLISHED_END_STONE, Blocks.END_STONE);
            offerSlabRecipe(exporter, ModBlocks.POLISHED_END_STONE_SLAB, ModBlocks.POLISHED_END_STONE);
            offerStairsRecipe(exporter, ModBlocks.POLISHED_END_STONE_STAIRS, ModBlocks.POLISHED_END_STONE);
            offerWallRecipe(exporter, ModBlocks.POLISHED_END_STONE_WALL, ModBlocks.POLISHED_END_STONE);

            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_SLAB, Blocks.END_STONE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_SLAB, ModBlocks.POLISHED_END_STONE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_STAIRS, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_STAIRS, ModBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_WALL, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_END_STONE_WALL, ModBlocks.POLISHED_END_STONE);

            offerStonecuttingRecipe(exporter, Blocks.END_STONE_BRICKS, ModBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, Blocks.END_STONE_BRICK_SLAB, ModBlocks.POLISHED_END_STONE, 2);
            offerStonecuttingRecipe(exporter, Blocks.END_STONE_BRICK_STAIRS, ModBlocks.POLISHED_END_STONE);
            offerStonecuttingRecipe(exporter, Blocks.END_STONE_BRICK_WALL, ModBlocks.POLISHED_END_STONE);

            ShapedRecipeJsonBuilder.create(ModBlocks.END_STONE_PILLAR, 2).input('#', ModBlocks.POLISHED_END_STONE).pattern("#").pattern("#").criterion(hasItem(ModBlocks.POLISHED_END_STONE), conditionsFromItem(ModBlocks.POLISHED_END_STONE)).offerTo(exporter);
            offerStonecuttingRecipe(exporter, ModBlocks.END_STONE_PILLAR, Blocks.END_STONE);
            offerStonecuttingRecipe(exporter, ModBlocks.END_STONE_PILLAR, ModBlocks.POLISHED_END_STONE);

            offerChiseledBlockRecipe(exporter, ModBlocks.CHISELED_PURPUR, Blocks.PURPUR_SLAB);
            offerStonecuttingRecipe(exporter, ModBlocks.CHISELED_PURPUR, Blocks.PURPUR_BLOCK);

            offerSlabRecipe(exporter, ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT);
            offerStairsRecipe(exporter, ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
            offerWallRecipe(exporter, ModBlocks.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);

            offerStonecuttingRecipe(exporter, ModBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
            offerStonecuttingRecipe(exporter, ModBlocks.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);

            ShapedRecipeJsonBuilder.create(ModBlocks.STICK_BUNDLE).input('#', Items.STICK).pattern("###").pattern("###").pattern("###").criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

            ShapedRecipeJsonBuilder.create(ModBlocks.RED_NETHER_BRICK_FENCE, 6).input('W', Blocks.RED_NETHER_BRICKS).input('#', Items.NETHER_BRICK).pattern("W#W").pattern("W#W").criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(exporter);

            ShapedRecipeJsonBuilder.create(ModBlocks.KILN).input('#', Items.BRICK).input('X', Blocks.FURNACE).pattern(" # ").pattern("#X#").pattern(" # ").criterion(hasItem(Blocks.FURNACE), conditionsFromItem(Blocks.FURNACE)).offerTo(exporter);

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

            offerBoatRecipe(exporter, ModItems.CRIMSON_BOAT, Blocks.CRIMSON_PLANKS);
            offerBoatRecipe(exporter, ModItems.WARPED_BOAT, Blocks.WARPED_PLANKS);

            offerChestBoatRecipe(exporter, ModItems.CRIMSON_CHEST_BOAT, ModItems.CRIMSON_BOAT);
            offerChestBoatRecipe(exporter, ModItems.WARPED_CHEST_BOAT, ModItems.WARPED_BOAT);

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.STICK_BUNDLE), Items.CHARCOAL, 0.15F, 200).group(getItemPath(Items.CHARCOAL)).criterion(hasItem(ModBlocks.STICK_BUNDLE), conditionsFromItem(ModBlocks.STICK_BUNDLE)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.CHARCOAL) + "_from_stick_bundle_smelting"));
            CookingRecipeJsonBuilder.create(Ingredient.ofItems(ModBlocks.STICK_BUNDLE), Items.CHARCOAL, 0.15F, 100, ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER).group(getItemPath(Items.CHARCOAL)).criterion(hasItem(ModBlocks.STICK_BUNDLE), conditionsFromItem(ModBlocks.STICK_BUNDLE)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.CHARCOAL) + "_from_stick_bundle_kilning"));

            ShapelessRecipeJsonBuilder.create(Items.STICK, 9).input(ModBlocks.STICK_BUNDLE).group(getItemPath(Items.STICK)).criterion(hasItem(ModBlocks.STICK_BUNDLE), conditionsFromItem(ModBlocks.STICK_BUNDLE)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, "stick_from_bundle"));

            ShapelessRecipeJsonBuilder.create(Items.GREEN_DYE, 2).input(Items.BLUE_DYE).input(Items.YELLOW_DYE).criterion(hasItem(Items.BLUE_DYE), RecipeProvider.conditionsFromItem(Items.BLUE_DYE)).criterion(hasItem(Items.YELLOW_DYE), RecipeProvider.conditionsFromItem(Items.YELLOW_DYE)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, "green_dye_from_blue_yellow_dye"));

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.CHAINMAIL_HORSE_ARMOR), Items.IRON_NUGGET, 0.1F, 200).criterion(hasItem(ModItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(ModItems.CHAINMAIL_HORSE_ARMOR)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.IRON_NUGGET) + "_from_chainmail_horse_armor_smelting"));
            CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItems.CHAINMAIL_HORSE_ARMOR), Items.IRON_NUGGET, 0.1F, 100).criterion(hasItem(ModItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(ModItems.CHAINMAIL_HORSE_ARMOR)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.IRON_NUGGET) + "_from_chainmail_horse_armor_blasting"));

            //Base kilning recipes
            CookingRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.SAND), Blocks.GLASS, 0.1F, 100, ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER).group("glass").criterion(hasItem(Blocks.SAND), conditionsFromTag(ItemTags.SAND)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Blocks.GLASS) + "_from_kilning"));
            offerKilning(exporter, Blocks.STONE, Blocks.COBBLESTONE, 0.1F, 100);
            offerKilning(exporter, Blocks.SMOOTH_SANDSTONE, Blocks.SANDSTONE, 0.1F, 100);
            offerKilning(exporter, Blocks.SMOOTH_RED_SANDSTONE, Blocks.RED_SANDSTONE, 0.1F, 100);
            offerKilning(exporter, Blocks.SMOOTH_STONE, Blocks.STONE, 0.1F, 100);
            offerKilning(exporter, Blocks.SMOOTH_QUARTZ, Blocks.QUARTZ_BLOCK, 0.1F, 100);
            offerKilning(exporter, Items.BRICK, Items.CLAY_BALL, 0.3F, 100);
            offerKilning(exporter, Items.NETHER_BRICK, Blocks.NETHERRACK, 0.1F, 100);
            offerKilning(exporter, Blocks.CRACKED_NETHER_BRICKS, Blocks.NETHER_BRICKS, 0.1F, 100);
            offerKilning(exporter, Blocks.SMOOTH_BASALT, Blocks.BASALT, 0.1F, 100);
            offerKilning(exporter, Blocks.TERRACOTTA, Blocks.CLAY, 0.35F, 100);
            offerKilning(exporter, Blocks.CRACKED_STONE_BRICKS, Blocks.STONE_BRICKS, 0.1F, 100);
            offerKilning(exporter, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, 0.1F, 100);
            offerKilning(exporter, Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE, 0.1F, 100);
            offerKilning(exporter, Blocks.CRACKED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, 0.1F, 100);
            offerKilning(exporter, Blocks.CRACKED_DEEPSLATE_TILES, Blocks.DEEPSLATE_TILES, 0.1F, 100);
            offerKilning(exporter, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.YELLOW_GLAZED_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.LIME_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PINK_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GRAY_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.CYAN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BLUE_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.BROWN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.GREEN_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.RED_GLAZED_TERRACOTTA, Blocks.RED_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, 0.1F, 100);
            offerKilning(exporter, Items.GREEN_DYE, Blocks.CACTUS, 1.0F, 100);
            CookingRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.LOGS_THAT_BURN), Items.CHARCOAL, 0.15F, 100, ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER).group(getItemPath(Items.CHARCOAL)).criterion("has_log", conditionsFromTag(ItemTags.LOGS_THAT_BURN)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.CHARCOAL) + "_from_kilning"));
            offerKilning(exporter, Items.POPPED_CHORUS_FRUIT, Items.CHORUS_FRUIT, 0.1F, 100);
            offerKilning(exporter, Blocks.SPONGE, Blocks.WET_SPONGE, 0.15F, 100);
            offerKilning(exporter, Items.LIME_DYE, Blocks.SEA_PICKLE, 0.1F, 100);
        }
    }
}
