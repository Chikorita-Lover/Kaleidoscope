package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder.getItemId;

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
            
            offerPolishedStoneRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerSlabRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE);
            offerStairsRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);
            offerWallRecipe(exporter, ModBlocks.POLISHED_CALCITE_WALL, ModBlocks.POLISHED_CALCITE);

            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, Blocks.CALCITE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_SLAB, ModBlocks.POLISHED_CALCITE, 2);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, Blocks.CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_STAIRS, ModBlocks.POLISHED_CALCITE);
            offerStonecuttingRecipe(exporter, ModBlocks.POLISHED_CALCITE_WALL, Blocks.CALCITE);
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
            CookingRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.LOGS_THAT_BURN), Items.CHARCOAL, 0.15F, 100, ChikoritaLoverMod.KILN_COOKING_RECIPE_SERIALIZER).group("charcoal").criterion("has_log", conditionsFromTag(ItemTags.LOGS_THAT_BURN)).offerTo(exporter, new Identifier(ChikoritaLoverMod.MODID, getItemPath(Items.CHARCOAL) + "_from_kilning"));
            offerKilning(exporter, Items.POPPED_CHORUS_FRUIT, Items.CHORUS_FRUIT, 0.1F, 100);
            offerKilning(exporter, Blocks.SPONGE, Blocks.WET_SPONGE, 0.15F, 100);
            offerKilning(exporter, Items.LIME_DYE, Blocks.SEA_PICKLE, 0.1F, 100);
        }
    }
}
