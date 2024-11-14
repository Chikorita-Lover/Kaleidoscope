package net.chikorita_lover.kaleidoscope.data;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlockFamilies;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class KaleidoscopeRecipeProvider extends FabricRecipeProvider {
    public KaleidoscopeRecipeProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static String getCrackingItemPath(ItemConvertible item) {
        return getItemPath(item) + "_cracking";
    }

    public static void offerFurnaceCrackingRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerDoorRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 3).input('#', input).pattern("##").pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerFireworkShellRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, 8).input(Items.PAPER).input(Items.GUNPOWDER).input(input).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerGlassFamilyRecipes(RecipeExporter exporter, BlockFamily family, @Nullable TagKey<Item> dyeItem) {
        Block block = family.getBaseBlock();
        Block doorBlock = family.getVariant(BlockFamily.Variant.DOOR);
        Block trapdoorBlock = family.getVariant(BlockFamily.Variant.TRAPDOOR);
        if (dyeItem != null) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, doorBlock, 3).input('#', block).pattern("##").pattern("##").pattern("##").group("stained_glass_door").criterion(hasItem(block), conditionsFromItem(block)).offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, doorBlock).input(KaleidoscopeBlocks.GLASS_DOOR).input(dyeItem).group("stained_glass_door").criterion("has_glass_door", conditionsFromItem(KaleidoscopeBlocks.GLASS_DOOR)).offerTo(exporter, Kaleidoscope.of(convertBetween(doorBlock, KaleidoscopeBlocks.GLASS_DOOR)));

            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, trapdoorBlock).input('#', block).pattern("##").pattern("##").group("stained_glass_trapdoor").criterion(hasItem(block), conditionsFromItem(block)).offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, trapdoorBlock).input(KaleidoscopeBlocks.GLASS_TRAPDOOR).input(dyeItem).group("stained_glass_trapdoor").criterion("has_glass_trapdoor", conditionsFromItem(KaleidoscopeBlocks.GLASS_TRAPDOOR)).offerTo(exporter, Kaleidoscope.of(convertBetween(trapdoorBlock, KaleidoscopeBlocks.GLASS_TRAPDOOR)));
        } else {
            offerDoorRecipe(exporter, doorBlock, block);
            offerTrapdoorRecipe2(exporter, trapdoorBlock, block);
        }
    }

    private static void offerSmoothCopperRecipes(RecipeExporter exporter, BlockFamily blockFamily, ItemConvertible input) {
        Block block = blockFamily.getBaseBlock();
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, block, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.STAIRS), block);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.SLAB), block, 2);
    }

    public static void offerStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        offerStonecuttingRecipe(exporter, category, output, input, 1);
    }

    public static void offerStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible output, ItemConvertible input, int count) {
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(input), category, output, count).criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter, Kaleidoscope.of(convertBetween(output, input) + "_stonecutting"));
    }

    private static void offerTrapdoorRecipe2(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 1).input('#', input).pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerWaxedSmoothCopperRecipes(RecipeExporter exporter, BlockFamily blockFamily, ItemConvertible input, BlockFamily blockFamily2) {
        offerSmoothCopperRecipes(exporter, blockFamily, input);
        offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getBaseBlock(), blockFamily2.getBaseBlock());
        offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.STAIRS), blockFamily2.getVariant(BlockFamily.Variant.STAIRS));
        offerWaxingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, blockFamily.getVariant(BlockFamily.Variant.SLAB), blockFamily2.getVariant(BlockFamily.Variant.SLAB));
    }

    private static void offerWaxingRecipe(RecipeExporter exporter, RecipeCategory recipeCategory, ItemConvertible output, ItemConvertible input) {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output).input(input).input(Items.HONEYCOMB).group(getItemPath(output)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter, Kaleidoscope.of(getItemPath(output) + "_from_honeycomb"));
    }

    @Override
    public void generate(RecipeExporter exporter) {
        KaleidoscopeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateRecipes).forEach(family -> RecipeProvider.generateFamily(exporter, family, FeatureFlags.VANILLA_FEATURES));

        offerWallRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_GRANITE_WALL, Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        offerWallRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_DIORITE_WALL, Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        offerWallRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL, Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICKS, Blocks.PACKED_MUD);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_SLAB, Blocks.PACKED_MUD, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_STAIRS, Blocks.PACKED_MUD);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, Blocks.MUD_BRICK_WALL, Blocks.PACKED_MUD);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_SLAB, Blocks.CALCITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_STAIRS, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.CALCITE_WALL, Blocks.CALCITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, KaleidoscopeBlocks.POLISHED_CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, Blocks.CALCITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, KaleidoscopeBlocks.POLISHED_CALCITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_CALCITE_WALL, Blocks.CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_CALCITE_WALL, KaleidoscopeBlocks.POLISHED_CALCITE);

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Blocks.CALCITE), RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE, 0.1F, 200).criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE)).offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE_STAIRS, KaleidoscopeBlocks.SMOOTH_CALCITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE_SLAB, KaleidoscopeBlocks.SMOOTH_CALCITE, 2);

        offerFurnaceCrackingRecipe(exporter, KaleidoscopeBlocks.CRACKED_TUFF_BRICKS, Blocks.TUFF_BRICKS);

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHARCOAL_BLOCK, Items.CHARCOAL);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_STAIRS, Blocks.QUARTZ_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_SLAB, Blocks.QUARTZ_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.QUARTZ_BRICK_WALL, Blocks.QUARTZ_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.QUARTZ_BRICK_WALL, Blocks.QUARTZ_BRICKS);

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
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS, KaleidoscopeBlocks.BRICK_MOSAIC);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, Blocks.BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, KaleidoscopeBlocks.BRICK_MOSAIC, 2);

        offerFurnaceCrackingRecipe(exporter, KaleidoscopeBlocks.CRACKED_MUD_BRICKS, Blocks.MUD_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE);

        offerFurnaceCrackingRecipe(exporter, KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_SLAB, Blocks.END_STONE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_STAIRS, Blocks.END_STONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.END_STONE_WALL, Blocks.END_STONE);

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

        offerFurnaceCrackingRecipe(exporter, KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS, Blocks.END_STONE_BRICKS);

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

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SOUL_JACK_O_LANTERN).input('#', Blocks.CARVED_PUMPKIN).input('S', Blocks.SOUL_TORCH).pattern("#").pattern("S").criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN)).offerTo(exporter);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_SLAB, Blocks.SMOOTH_BASALT, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS, Blocks.SMOOTH_BASALT);
        offerStonecuttingRecipe(exporter, RecipeCategory.MISC, KaleidoscopeBlocks.SMOOTH_BASALT_WALL, Blocks.SMOOTH_BASALT);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_STAIRS, Blocks.PACKED_MUD);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_SLAB, Blocks.PACKED_MUD, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_WALL, Blocks.PACKED_MUD);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.STICK_BLOCK).input('#', Items.STICK).pattern("###").pattern("###").pattern("###").criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, 6).input('W', Blocks.RED_NETHER_BRICKS).input('#', ConventionalItemTags.NETHER_BRICKS).pattern("W#W").pattern("W#W").criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(exporter);

        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.GLASS, null);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.WHITE_STAINED_GLASS, ConventionalItemTags.WHITE_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.LIGHT_GRAY_STAINED_GLASS, ConventionalItemTags.LIGHT_GRAY_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.GRAY_STAINED_GLASS, ConventionalItemTags.GRAY_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.BLACK_STAINED_GLASS, ConventionalItemTags.BLACK_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.BROWN_STAINED_GLASS, ConventionalItemTags.BROWN_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.RED_STAINED_GLASS, ConventionalItemTags.RED_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.ORANGE_STAINED_GLASS, ConventionalItemTags.ORANGE_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.YELLOW_STAINED_GLASS, ConventionalItemTags.YELLOW_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.LIME_STAINED_GLASS, ConventionalItemTags.LIME_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.GREEN_STAINED_GLASS, ConventionalItemTags.GREEN_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.CYAN_STAINED_GLASS, ConventionalItemTags.CYAN_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.LIGHT_BLUE_STAINED_GLASS, ConventionalItemTags.LIGHT_BLUE_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.BLUE_STAINED_GLASS, ConventionalItemTags.BLUE_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.PURPLE_STAINED_GLASS, ConventionalItemTags.PURPLE_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.MAGENTA_STAINED_GLASS, ConventionalItemTags.MAGENTA_DYES);
        offerGlassFamilyRecipes(exporter, KaleidoscopeBlockFamilies.PINK_STAINED_GLASS, ConventionalItemTags.PINK_DYES);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.FIREWORKS_TABLE).input('#', ItemTags.PLANKS).input('@', Items.GUNPOWDER).pattern("@@").pattern("##").pattern("##").criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.KILN).input('#', Blocks.SMOOTH_STONE_SLAB).input('C', ConventionalItemTags.COPPER_INGOTS).input('X', ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES).pattern("C#C").pattern("CXC").pattern("C#C").criterion(hasItem(Blocks.FURNACE), conditionsFromTag(ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES)).offerTo(exporter);

        BlockTransmutingRecipeJsonBuilder.createCracking(Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS).offerTo(exporter, Kaleidoscope.of(getCrackingItemPath(Blocks.INFESTED_STONE_BRICKS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, KaleidoscopeItems.NETHERITE_SHEARS).input('#', ConventionalItemTags.NETHERITE_INGOTS).pattern(" #").pattern("# ").criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromTag(ConventionalItemTags.NETHERITE_INGOTS)).offerTo(exporter);

        offerBoatRecipe(exporter, KaleidoscopeItems.CRIMSON_BOAT, Blocks.CRIMSON_PLANKS);
        offerChestBoatRecipe(exporter, KaleidoscopeItems.CRIMSON_CHEST_BOAT, KaleidoscopeItems.CRIMSON_BOAT);
        offerBoatRecipe(exporter, KaleidoscopeItems.WARPED_BOAT, Blocks.WARPED_PLANKS);
        offerChestBoatRecipe(exporter, KaleidoscopeItems.WARPED_CHEST_BOAT, KaleidoscopeItems.WARPED_BOAT);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, KaleidoscopeItems.JUKEBOX_MINECART).input(Blocks.JUKEBOX).input(Items.MINECART).criterion(hasItem(Items.MINECART), conditionsFromItem(Items.MINECART)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MUSIC_DISC_PIGSTEP).input('S', KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).pattern("SSS").pattern("SSS").pattern("SSS").criterion(hasItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP), conditionsFromItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP)).offerTo(exporter, Kaleidoscope.of(getItemPath(Items.MUSIC_DISC_PIGSTEP)));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SWORD, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 0.3F, 200).criterion(hasItem(Items.NETHERITE_PICKAXE), conditionsFromItem(Items.NETHERITE_PICKAXE)).criterion(hasItem(Items.NETHERITE_SHOVEL), conditionsFromItem(Items.NETHERITE_SHOVEL)).criterion(hasItem(Items.NETHERITE_AXE), conditionsFromItem(Items.NETHERITE_AXE)).criterion(hasItem(Items.NETHERITE_HOE), conditionsFromItem(Items.NETHERITE_HOE)).criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD)).criterion(hasItem(Items.NETHERITE_HELMET), conditionsFromItem(Items.NETHERITE_HELMET)).criterion(hasItem(Items.NETHERITE_CHESTPLATE), conditionsFromItem(Items.NETHERITE_CHESTPLATE)).criterion(hasItem(Items.NETHERITE_LEGGINGS), conditionsFromItem(Items.NETHERITE_LEGGINGS)).criterion(hasItem(Items.NETHERITE_BOOTS), conditionsFromItem(Items.NETHERITE_BOOTS)).offerTo(exporter, Kaleidoscope.of("netherite_scrap_from_smelting_netherite_gear"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SWORD, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 0.3F, 100).criterion(hasItem(Items.NETHERITE_PICKAXE), conditionsFromItem(Items.NETHERITE_PICKAXE)).criterion(hasItem(Items.NETHERITE_SHOVEL), conditionsFromItem(Items.NETHERITE_SHOVEL)).criterion(hasItem(Items.NETHERITE_AXE), conditionsFromItem(Items.NETHERITE_AXE)).criterion(hasItem(Items.NETHERITE_HOE), conditionsFromItem(Items.NETHERITE_HOE)).criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD)).criterion(hasItem(Items.NETHERITE_HELMET), conditionsFromItem(Items.NETHERITE_HELMET)).criterion(hasItem(Items.NETHERITE_CHESTPLATE), conditionsFromItem(Items.NETHERITE_CHESTPLATE)).criterion(hasItem(Items.NETHERITE_LEGGINGS), conditionsFromItem(Items.NETHERITE_LEGGINGS)).criterion(hasItem(Items.NETHERITE_BOOTS), conditionsFromItem(Items.NETHERITE_BOOTS)).offerTo(exporter, Kaleidoscope.of("netherite_scrap_from_blasting_netherite_gear"));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STICK, 9).input(KaleidoscopeBlocks.STICK_BLOCK).group("sticks").criterion(hasItem(KaleidoscopeBlocks.STICK_BLOCK), conditionsFromItem(KaleidoscopeBlocks.STICK_BLOCK)).offerTo(exporter, Kaleidoscope.of("stick_from_block"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GREEN_DYE, 2).input(ConventionalItemTags.BLUE_DYES).input(ConventionalItemTags.YELLOW_DYES).group(getItemPath(Items.GREEN_DYE)).criterion(hasItem(Items.BLUE_DYE), RecipeProvider.conditionsFromTag(ConventionalItemTags.BLUE_DYES)).criterion(hasItem(Items.YELLOW_DYE), RecipeProvider.conditionsFromTag(ConventionalItemTags.YELLOW_DYES)).offerTo(exporter, Kaleidoscope.of("green_dye_from_blue_yellow_dye"));

        offerFireworkShellRecipe(exporter, KaleidoscopeItems.LARGE_BALL_FIREWORK_SHELL, Items.FIRE_CHARGE);
        offerFireworkShellRecipe(exporter, KaleidoscopeItems.STAR_FIREWORK_SHELL, Items.GOLD_NUGGET);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, KaleidoscopeItems.CREEPER_FIREWORK_SHELL, 8).input(Items.PAPER).input(Items.GUNPOWDER).input(ItemTags.SKULLS).criterion("has_skull", conditionsFromTag(ItemTags.SKULLS)).offerTo(exporter);
        offerFireworkShellRecipe(exporter, KaleidoscopeItems.BURST_FIREWORK_SHELL, Items.FEATHER);
    }
}
