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
import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class KaleidoscopeRecipeGenerator extends RecipeGenerator {
    public KaleidoscopeRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    private static RegistryKey<Recipe<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.RECIPE, Kaleidoscope.of(id));
    }

    private void generateSmoothCopperFamily(BlockFamily family, ItemConvertible input) {
        Block block = family.getBaseBlock();
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, block, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(this.exporter);
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, family.getVariant(BlockFamily.Variant.STAIRS), new Block[]{block});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, family.getVariant(BlockFamily.Variant.SLAB), new Block[]{block}, 2);
    }

    private void generateWaxedSmoothCopperFamily(BlockFamily family, ItemConvertible input, BlockFamily unwaxedFamily) {
        this.generateSmoothCopperFamily(family, input);
        this.offerWaxingRecipe(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), unwaxedFamily.getBaseBlock());
        this.offerWaxingRecipe(RecipeCategory.BUILDING_BLOCKS, family.getVariant(BlockFamily.Variant.STAIRS), unwaxedFamily.getVariant(BlockFamily.Variant.STAIRS));
        this.offerWaxingRecipe(RecipeCategory.BUILDING_BLOCKS, family.getVariant(BlockFamily.Variant.SLAB), unwaxedFamily.getVariant(BlockFamily.Variant.SLAB));
    }

    private void generateGlassFamily(BlockFamily family, @Nullable TagKey<Item> dyeItem) {
        Block block = family.getBaseBlock();
        Block doorBlock = family.getVariant(BlockFamily.Variant.DOOR);
        Block trapdoorBlock = family.getVariant(BlockFamily.Variant.TRAPDOOR);
        if (dyeItem != null) {
            this.createShaped(RecipeCategory.REDSTONE, doorBlock, 3).input('#', block).pattern("##").pattern("##").pattern("##").group("stained_glass_door").criterion(hasItem(block), conditionsFromItem(block)).offerTo(this.exporter);
            this.createShapeless(RecipeCategory.REDSTONE, doorBlock).input(KaleidoscopeBlocks.GLASS_DOOR).input(dyeItem).group("stained_glass_door").criterion("has_glass_door", conditionsFromItem(KaleidoscopeBlocks.GLASS_DOOR)).offerTo(this.exporter, keyOf(convertBetween(doorBlock, KaleidoscopeBlocks.GLASS_DOOR)));
            this.createShaped(RecipeCategory.REDSTONE, trapdoorBlock).input('#', block).pattern("##").pattern("##").group("stained_glass_trapdoor").criterion(hasItem(block), conditionsFromItem(block)).offerTo(this.exporter);
            this.createShapeless(RecipeCategory.REDSTONE, trapdoorBlock).input(KaleidoscopeBlocks.GLASS_TRAPDOOR).input(dyeItem).group("stained_glass_trapdoor").criterion("has_glass_trapdoor", conditionsFromItem(KaleidoscopeBlocks.GLASS_TRAPDOOR)).offerTo(this.exporter, keyOf(convertBetween(trapdoorBlock, KaleidoscopeBlocks.GLASS_TRAPDOOR)));
        } else {
            offerDoorRecipe(doorBlock, block);
            this.offerTrapdoorRecipe2(trapdoorBlock, block);
        }
    }

    private void offerDoorRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.REDSTONE, output, 3).input('#', input).pattern("##").pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(this.exporter);
    }

    public void offerDyeingRecipes(List<Item> dyes, List<ItemConvertible> dyeables, @Nullable Item undyed, String group, RecipeCategory category) {
        for (int i = 0; i < dyes.size(); ++i) {
            Item dye = dyes.get(i);
            final ItemConvertible dyeable = dyeables.get(i);
            Stream<ItemConvertible> stream = dyeables.stream().filter(item -> !item.equals(dyeable));
            if (undyed != null) {
                stream = Stream.concat(stream, Stream.of(undyed));
            }
            this.createShapeless(category, dyeable).input(dye).input(Ingredient.ofItems(stream)).group(group).criterion("has_needed_dye", this.conditionsFromItem(dye)).offerTo(this.exporter, keyOf("dye_" + getItemPath(dyeable)));
        }
    }

    private void offerTrapdoorRecipe2(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.REDSTONE, output).input('#', input).pattern("##").pattern("##").criterion(hasItem(input), conditionsFromItem(input)).offerTo(this.exporter);
    }

    private void offerWaxingRecipe(RecipeCategory category, ItemConvertible output, ItemConvertible input) {
        this.createShapeless(category, output).input(input).input(Items.HONEYCOMB).group(getItemPath(output)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(this.exporter, keyOf(getItemPath(output) + "_from_honeycomb"));
    }

    private void offerFurnaceCrackingRecipe(ItemConvertible output, ItemConvertible input) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1F, 200).criterion(hasItem(input), conditionsFromItem(input)).offerTo(this.exporter);
    }

    private void offerStonecuttingRecipes(RecipeCategory category, ItemConvertible output, ItemConvertible[] inputs) {
        this.offerStonecuttingRecipes(category, output, inputs, 1);
    }

    private void offerStonecuttingRecipes(RecipeCategory category, ItemConvertible output, ItemConvertible[] inputs, int count) {
        for (ItemConvertible input : inputs) {
            StonecuttingRecipeJsonBuilder builder = StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItem(input), category, output, count).criterion(hasItem(input), this.conditionsFromItem(input));
            String path = convertBetween(output, input) + "_stonecutting";
            builder.offerTo(this.exporter, keyOf(path));
        }
    }

    private ShapelessRecipeJsonBuilder createFireworkShellRecipe(ItemConvertible output, Ingredient input) {
        return this.createShapeless(RecipeCategory.MISC, output, 8).input(Items.PAPER).input(Items.GUNPOWDER).input(input);
    }

    @Override
    public void generate() {
        List<Item> dyes = List.of(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE);
        KaleidoscopeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateRecipes).forEach(family -> this.generateFamily(family, FeatureFlags.VANILLA_FEATURES));

        this.offerWallRecipe(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_GRANITE_WALL, Blocks.POLISHED_GRANITE);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_GRANITE_WALL, new Block[]{Blocks.GRANITE, Blocks.POLISHED_GRANITE});

        this.offerWallRecipe(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_DIORITE_WALL, Blocks.POLISHED_DIORITE);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_DIORITE_WALL, new Block[]{Blocks.DIORITE, Blocks.POLISHED_DIORITE});

        this.offerWallRecipe(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL, Blocks.POLISHED_ANDESITE);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL, new Block[]{Blocks.ANDESITE, Blocks.POLISHED_ANDESITE});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICKS, new Block[]{Blocks.PACKED_MUD});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_STAIRS, new Block[]{Blocks.PACKED_MUD});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.MUD_BRICK_SLAB, new Block[]{Blocks.PACKED_MUD}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, Blocks.MUD_BRICK_WALL, new Block[]{Blocks.PACKED_MUD});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_STAIRS, new Block[]{Blocks.CALCITE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CALCITE_SLAB, new Block[]{Blocks.CALCITE}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.CALCITE_WALL, new Block[]{Blocks.CALCITE});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE, new Block[]{Blocks.CALCITE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_STAIRS, new Block[]{Blocks.CALCITE, KaleidoscopeBlocks.POLISHED_CALCITE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_CALCITE_SLAB, new Block[]{Blocks.CALCITE, KaleidoscopeBlocks.POLISHED_CALCITE}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_CALCITE_WALL, new Block[]{Blocks.CALCITE, KaleidoscopeBlocks.POLISHED_CALCITE});

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Blocks.CALCITE), RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE, 0.1F, 200).criterion(hasItem(Blocks.CALCITE), conditionsFromItem(Blocks.CALCITE)).offerTo(this.exporter);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE_STAIRS, new Block[]{KaleidoscopeBlocks.SMOOTH_CALCITE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_CALCITE_SLAB, new Block[]{KaleidoscopeBlocks.SMOOTH_CALCITE}, 2);

        this.offerFurnaceCrackingRecipe(KaleidoscopeBlocks.CRACKED_TUFF_BRICKS, Blocks.TUFF_BRICKS);

        this.offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CHARCOAL_BLOCK, Items.CHARCOAL);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_STAIRS, new Block[]{Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.QUARTZ_BRICK_SLAB, new Block[]{Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.QUARTZ_BRICK_WALL, new Block[]{Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS});

        this.generateSmoothCopperFamily(KaleidoscopeBlockFamilies.SMOOTH_COPPER, Blocks.CUT_COPPER);
        this.generateSmoothCopperFamily(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER, Blocks.EXPOSED_CUT_COPPER);
        this.generateSmoothCopperFamily(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER, Blocks.WEATHERED_CUT_COPPER);
        this.generateSmoothCopperFamily(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER, Blocks.OXIDIZED_CUT_COPPER);

        this.generateWaxedSmoothCopperFamily(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER, Blocks.WAXED_CUT_COPPER, KaleidoscopeBlockFamilies.SMOOTH_COPPER);
        this.generateWaxedSmoothCopperFamily(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER);
        this.generateWaxedSmoothCopperFamily(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER);
        this.generateWaxedSmoothCopperFamily(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER, KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER);

        this.offerChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC, Blocks.BRICK_SLAB);
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC, new Block[]{Blocks.BRICKS});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_STAIRS, new Block[]{Blocks.BRICKS, KaleidoscopeBlocks.BRICK_MOSAIC});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BRICK_MOSAIC_SLAB, new Block[]{Blocks.BRICKS, KaleidoscopeBlocks.BRICK_MOSAIC}, 2);

        this.offerFurnaceCrackingRecipe(KaleidoscopeBlocks.CRACKED_MUD_BRICKS, Blocks.MUD_BRICKS);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICKS, new Block[]{Blocks.PRISMARINE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_STAIRS, new Block[]{Blocks.PRISMARINE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.PRISMARINE_BRICK_SLAB, new Block[]{Blocks.PRISMARINE}, 2);

        this.offerFurnaceCrackingRecipe(KaleidoscopeBlocks.CRACKED_RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS);

        this.createShaped(RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICKS, 4).input('#', KaleidoscopeBlocks.POLISHED_END_STONE).pattern("##").pattern("##").criterion(hasItem(KaleidoscopeBlocks.POLISHED_END_STONE), conditionsFromItem(KaleidoscopeBlocks.POLISHED_END_STONE)).offerTo(this.exporter, keyOf("end_stone_bricks_from_polished_end_stone"));

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_STAIRS, new Block[]{Blocks.END_STONE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.END_STONE_SLAB, new Block[]{Blocks.END_STONE}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.END_STONE_WALL, new Block[]{Blocks.END_STONE});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE, new Block[]{Blocks.END_STONE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_STAIRS, new Block[]{Blocks.END_STONE, KaleidoscopeBlocks.POLISHED_END_STONE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.POLISHED_END_STONE_SLAB, new Block[]{Blocks.END_STONE, KaleidoscopeBlocks.POLISHED_END_STONE}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.POLISHED_END_STONE_WALL, new Block[]{Blocks.END_STONE, KaleidoscopeBlocks.POLISHED_END_STONE});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICKS, new Block[]{KaleidoscopeBlocks.POLISHED_END_STONE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_STAIRS, new Block[]{KaleidoscopeBlocks.POLISHED_END_STONE});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, Blocks.END_STONE_BRICK_SLAB, new Block[]{KaleidoscopeBlocks.POLISHED_END_STONE}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, Blocks.END_STONE_BRICK_WALL, new Block[]{KaleidoscopeBlocks.POLISHED_END_STONE});

        this.offerFurnaceCrackingRecipe(KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS, Blocks.END_STONE_BRICKS);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.TERRACOTTA_STAIRS, new Block[]{Blocks.TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.TERRACOTTA_SLAB, new Block[]{Blocks.TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_TERRACOTTA_STAIRS, new Block[]{Blocks.WHITE_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.WHITE_TERRACOTTA_SLAB, new Block[]{Blocks.WHITE_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, new Block[]{Blocks.LIGHT_GRAY_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, new Block[]{Blocks.LIGHT_GRAY_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_TERRACOTTA_STAIRS, new Block[]{Blocks.GRAY_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GRAY_TERRACOTTA_SLAB, new Block[]{Blocks.GRAY_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_TERRACOTTA_STAIRS, new Block[]{Blocks.BLACK_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLACK_TERRACOTTA_SLAB, new Block[]{Blocks.BLACK_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_TERRACOTTA_STAIRS, new Block[]{Blocks.BROWN_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BROWN_TERRACOTTA_SLAB, new Block[]{Blocks.BROWN_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_TERRACOTTA_STAIRS, new Block[]{Blocks.RED_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.RED_TERRACOTTA_SLAB, new Block[]{Blocks.RED_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_TERRACOTTA_STAIRS, new Block[]{Blocks.ORANGE_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.ORANGE_TERRACOTTA_SLAB, new Block[]{Blocks.ORANGE_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_TERRACOTTA_STAIRS, new Block[]{Blocks.YELLOW_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.YELLOW_TERRACOTTA_SLAB, new Block[]{Blocks.YELLOW_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_TERRACOTTA_STAIRS, new Block[]{Blocks.LIME_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIME_TERRACOTTA_SLAB, new Block[]{Blocks.LIME_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_TERRACOTTA_STAIRS, new Block[]{Blocks.GREEN_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.GREEN_TERRACOTTA_SLAB, new Block[]{Blocks.GREEN_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_TERRACOTTA_STAIRS, new Block[]{Blocks.CYAN_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.CYAN_TERRACOTTA_SLAB, new Block[]{Blocks.CYAN_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, new Block[]{Blocks.LIGHT_BLUE_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, new Block[]{Blocks.LIGHT_BLUE_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_TERRACOTTA_STAIRS, new Block[]{Blocks.BLUE_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.BLUE_TERRACOTTA_SLAB, new Block[]{Blocks.BLUE_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_TERRACOTTA_STAIRS, new Block[]{Blocks.PURPLE_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PURPLE_TERRACOTTA_SLAB, new Block[]{Blocks.PURPLE_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_STAIRS, new Block[]{Blocks.MAGENTA_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.MAGENTA_TERRACOTTA_SLAB, new Block[]{Blocks.MAGENTA_TERRACOTTA}, 2);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_TERRACOTTA_STAIRS, new Block[]{Blocks.PINK_TERRACOTTA});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PINK_TERRACOTTA_SLAB, new Block[]{Blocks.PINK_TERRACOTTA}, 2);

        this.createShaped(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SOUL_JACK_O_LANTERN).input('#', Blocks.CARVED_PUMPKIN).input('S', Blocks.SOUL_TORCH).pattern("#").pattern("S").criterion(hasItem(Blocks.CARVED_PUMPKIN), conditionsFromItem(Blocks.CARVED_PUMPKIN)).offerTo(this.exporter);

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_STAIRS, new Block[]{Blocks.SMOOTH_BASALT});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.SMOOTH_BASALT_SLAB, new Block[]{Blocks.SMOOTH_BASALT}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.SMOOTH_BASALT_WALL, new Block[]{Blocks.SMOOTH_BASALT});

        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_STAIRS, new Block[]{Blocks.PACKED_MUD});
        this.offerStonecuttingRecipes(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.PACKED_MUD_SLAB, new Block[]{Blocks.PACKED_MUD}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.MISC, KaleidoscopeBlocks.PACKED_MUD_WALL, new Block[]{Blocks.PACKED_MUD});

        this.createShaped(RecipeCategory.BUILDING_BLOCKS, KaleidoscopeBlocks.STICK_BLOCK).input('#', Items.STICK).pattern("###").pattern("###").pattern("###").criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK)).offerTo(this.exporter);

        this.createShaped(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, 6).input('W', Blocks.RED_NETHER_BRICKS).input('#', ConventionalItemTags.NETHER_BRICKS).pattern("W#W").pattern("W#W").criterion(hasItem(Blocks.RED_NETHER_BRICKS), conditionsFromItem(Blocks.RED_NETHER_BRICKS)).offerTo(this.exporter);

        this.offerStonecuttingRecipes(RecipeCategory.DECORATIONS, Blocks.NETHER_BRICK_FENCE, new Block[]{Blocks.NETHER_BRICKS}, 2);
        this.offerStonecuttingRecipes(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.RED_NETHER_BRICK_FENCE, new Block[]{Blocks.RED_NETHER_BRICKS}, 2);

        this.generateGlassFamily(KaleidoscopeBlockFamilies.GLASS, null);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.WHITE_STAINED_GLASS, ConventionalItemTags.WHITE_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.LIGHT_GRAY_STAINED_GLASS, ConventionalItemTags.LIGHT_GRAY_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.GRAY_STAINED_GLASS, ConventionalItemTags.GRAY_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.BLACK_STAINED_GLASS, ConventionalItemTags.BLACK_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.BROWN_STAINED_GLASS, ConventionalItemTags.BROWN_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.RED_STAINED_GLASS, ConventionalItemTags.RED_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.ORANGE_STAINED_GLASS, ConventionalItemTags.ORANGE_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.YELLOW_STAINED_GLASS, ConventionalItemTags.YELLOW_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.LIME_STAINED_GLASS, ConventionalItemTags.LIME_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.GREEN_STAINED_GLASS, ConventionalItemTags.GREEN_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.CYAN_STAINED_GLASS, ConventionalItemTags.CYAN_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.LIGHT_BLUE_STAINED_GLASS, ConventionalItemTags.LIGHT_BLUE_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.BLUE_STAINED_GLASS, ConventionalItemTags.BLUE_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.PURPLE_STAINED_GLASS, ConventionalItemTags.PURPLE_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.MAGENTA_STAINED_GLASS, ConventionalItemTags.MAGENTA_DYES);
        this.generateGlassFamily(KaleidoscopeBlockFamilies.PINK_STAINED_GLASS, ConventionalItemTags.PINK_DYES);

        this.createShaped(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.FIREWORKS_TABLE).input('#', ItemTags.PLANKS).input('@', Items.GUNPOWDER).pattern("@@").pattern("##").pattern("##").criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER)).offerTo(this.exporter);

        this.createShaped(RecipeCategory.DECORATIONS, KaleidoscopeBlocks.KILN).input('#', Blocks.SMOOTH_STONE_SLAB).input('C', ConventionalItemTags.COPPER_INGOTS).input('X', ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES).pattern("C#C").pattern("CXC").pattern("C#C").criterion(hasItem(Blocks.FURNACE), conditionsFromTag(ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES)).offerTo(this.exporter);

        this.createShaped(RecipeCategory.TOOLS, KaleidoscopeItems.NETHERITE_SHEARS).input('#', ConventionalItemTags.NETHERITE_INGOTS).pattern(" #").pattern("# ").criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromTag(ConventionalItemTags.NETHERITE_INGOTS)).offerTo(this.exporter);

        this.offerBoatRecipe(KaleidoscopeItems.CRIMSON_BOAT, Blocks.CRIMSON_PLANKS);
        this.offerChestBoatRecipe(KaleidoscopeItems.CRIMSON_CHEST_BOAT, KaleidoscopeItems.CRIMSON_BOAT);
        this.offerBoatRecipe(KaleidoscopeItems.WARPED_BOAT, Blocks.WARPED_PLANKS);
        this.offerChestBoatRecipe(KaleidoscopeItems.WARPED_CHEST_BOAT, KaleidoscopeItems.WARPED_BOAT);

        this.createShapeless(RecipeCategory.TRANSPORTATION, KaleidoscopeItems.JUKEBOX_MINECART).input(Blocks.JUKEBOX).input(Items.MINECART).criterion(hasItem(Items.MINECART), conditionsFromItem(Items.MINECART)).offerTo(this.exporter);

        this.createShaped(RecipeCategory.MISC, Items.MUSIC_DISC_PIGSTEP).input('S', KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP).pattern("SSS").pattern("SSS").pattern("SSS").criterion(hasItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP), conditionsFromItem(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP)).offerTo(this.exporter, keyOf(getItemPath(Items.MUSIC_DISC_PIGSTEP)));
        this.createShaped(RecipeCategory.MISC, Items.MUSIC_DISC_TEARS).input('S', KaleidoscopeItems.DISC_FRAGMENT_TEARS).pattern("SSS").pattern("SSS").pattern("SSS").criterion(hasItem(KaleidoscopeItems.DISC_FRAGMENT_TEARS), conditionsFromItem(KaleidoscopeItems.DISC_FRAGMENT_TEARS)).offerTo(this.exporter, keyOf(getItemPath(Items.MUSIC_DISC_TEARS)));

        this.createShapeless(RecipeCategory.MISC, Items.NAME_TAG).input(Items.PAPER).input(Items.INK_SAC).input(Items.IRON_INGOT).criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER)).offerTo(this.exporter, keyOf(getItemPath(Items.NAME_TAG)));

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.ofItem(Items.DIAMOND_HORSE_ARMOR), this.ingredientFromTag(ItemTags.NETHERITE_TOOL_MATERIALS), RecipeCategory.COMBAT, KaleidoscopeItems.NETHERITE_HORSE_ARMOR).criterion(hasItem(Items.NETHERITE_INGOT), this.conditionsFromTag(ItemTags.NETHERITE_TOOL_MATERIALS)).offerTo(this.exporter, keyOf(getItemPath(KaleidoscopeItems.NETHERITE_HORSE_ARMOR) + "_smithing"));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_NUGGET, 0.3F, 200).group(getItemPath(Items.IRON_NUGGET)).criterion(hasItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR)).offerTo(this.exporter, keyOf("iron_nugget_from_smelting_chainmail_horse_armor"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), RecipeCategory.MISC, Items.IRON_NUGGET, 0.3F, 100).group(getItemPath(Items.IRON_NUGGET)).criterion(hasItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR)).offerTo(this.exporter, keyOf("iron_nugget_from_blasting_chainmail_horse_armor"));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SWORD, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, KaleidoscopeItems.NETHERITE_HORSE_ARMOR), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 0.3F, 200).criterion(hasItem(Items.NETHERITE_PICKAXE), conditionsFromItem(Items.NETHERITE_PICKAXE)).criterion(hasItem(Items.NETHERITE_SHOVEL), conditionsFromItem(Items.NETHERITE_SHOVEL)).criterion(hasItem(Items.NETHERITE_AXE), conditionsFromItem(Items.NETHERITE_AXE)).criterion(hasItem(Items.NETHERITE_HOE), conditionsFromItem(Items.NETHERITE_HOE)).criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD)).criterion(hasItem(Items.NETHERITE_HELMET), conditionsFromItem(Items.NETHERITE_HELMET)).criterion(hasItem(Items.NETHERITE_CHESTPLATE), conditionsFromItem(Items.NETHERITE_CHESTPLATE)).criterion(hasItem(Items.NETHERITE_LEGGINGS), conditionsFromItem(Items.NETHERITE_LEGGINGS)).criterion(hasItem(Items.NETHERITE_BOOTS), conditionsFromItem(Items.NETHERITE_BOOTS)).criterion(hasItem(KaleidoscopeItems.NETHERITE_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.NETHERITE_HORSE_ARMOR)).offerTo(this.exporter, keyOf("netherite_scrap_from_smelting_netherite_gear"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SWORD, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS, KaleidoscopeItems.NETHERITE_HORSE_ARMOR), RecipeCategory.MISC, Items.NETHERITE_SCRAP, 0.3F, 100).criterion(hasItem(Items.NETHERITE_PICKAXE), conditionsFromItem(Items.NETHERITE_PICKAXE)).criterion(hasItem(Items.NETHERITE_SHOVEL), conditionsFromItem(Items.NETHERITE_SHOVEL)).criterion(hasItem(Items.NETHERITE_AXE), conditionsFromItem(Items.NETHERITE_AXE)).criterion(hasItem(Items.NETHERITE_HOE), conditionsFromItem(Items.NETHERITE_HOE)).criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD)).criterion(hasItem(Items.NETHERITE_HELMET), conditionsFromItem(Items.NETHERITE_HELMET)).criterion(hasItem(Items.NETHERITE_CHESTPLATE), conditionsFromItem(Items.NETHERITE_CHESTPLATE)).criterion(hasItem(Items.NETHERITE_LEGGINGS), conditionsFromItem(Items.NETHERITE_LEGGINGS)).criterion(hasItem(Items.NETHERITE_BOOTS), conditionsFromItem(Items.NETHERITE_BOOTS)).criterion(hasItem(KaleidoscopeItems.NETHERITE_HORSE_ARMOR), conditionsFromItem(KaleidoscopeItems.NETHERITE_HORSE_ARMOR)).offerTo(this.exporter, keyOf("netherite_scrap_from_blasting_netherite_gear"));

        this.createShapeless(RecipeCategory.MISC, Items.STICK, 9).input(KaleidoscopeBlocks.STICK_BLOCK).group("sticks").criterion(hasItem(KaleidoscopeBlocks.STICK_BLOCK), conditionsFromItem(KaleidoscopeBlocks.STICK_BLOCK)).offerTo(this.exporter, keyOf("stick_from_block"));

        this.createShapeless(RecipeCategory.MISC, Items.GREEN_DYE, 2).input(ConventionalItemTags.BLUE_DYES).input(ConventionalItemTags.YELLOW_DYES).group(getItemPath(Items.GREEN_DYE)).criterion(hasItem(Items.BLUE_DYE), conditionsFromTag(ConventionalItemTags.BLUE_DYES)).criterion(hasItem(Items.YELLOW_DYE), conditionsFromTag(ConventionalItemTags.YELLOW_DYES)).offerTo(this.exporter, keyOf("green_dye_from_blue_yellow_dye"));

        this.createFireworkShellRecipe(KaleidoscopeItems.LARGE_BALL_FIREWORK_SHELL, Ingredient.ofItem(Items.FIRE_CHARGE)).criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE)).offerTo(this.exporter);
        this.createFireworkShellRecipe(KaleidoscopeItems.STAR_FIREWORK_SHELL, Ingredient.ofItem(Items.GOLD_NUGGET)).criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE)).offerTo(this.exporter);
        this.createFireworkShellRecipe(KaleidoscopeItems.CREEPER_FIREWORK_SHELL, this.ingredientFromTag(ItemTags.SKULLS)).criterion("has_skull", conditionsFromTag(ItemTags.SKULLS)).offerTo(this.exporter);
        this.createFireworkShellRecipe(KaleidoscopeItems.BURST_FIREWORK_SHELL, Ingredient.ofItem(Items.FEATHER)).criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE)).offerTo(this.exporter);

        this.offerDyeingRecipes(dyes, List.of(KaleidoscopeBlocks.BLACK_CHEST, KaleidoscopeBlocks.BLUE_CHEST, KaleidoscopeBlocks.BROWN_CHEST, KaleidoscopeBlocks.CYAN_CHEST, KaleidoscopeBlocks.GRAY_CHEST, KaleidoscopeBlocks.GREEN_CHEST, KaleidoscopeBlocks.LIGHT_BLUE_CHEST, KaleidoscopeBlocks.LIGHT_GRAY_CHEST, KaleidoscopeBlocks.LIME_CHEST, KaleidoscopeBlocks.MAGENTA_CHEST, KaleidoscopeBlocks.ORANGE_CHEST, KaleidoscopeBlocks.PINK_CHEST, KaleidoscopeBlocks.PURPLE_CHEST, KaleidoscopeBlocks.RED_CHEST, KaleidoscopeBlocks.YELLOW_CHEST, KaleidoscopeBlocks.WHITE_CHEST), Items.CHEST, "chest_dye", RecipeCategory.DECORATIONS);
    }

    public static class Provider extends FabricRecipeProvider {
        public Provider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            return new KaleidoscopeRecipeGenerator(registries, exporter);
        }

        @Override
        public String getName() {
            return "Kaleidoscope Recipes";
        }
    }
}
