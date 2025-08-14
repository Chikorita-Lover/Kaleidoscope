package net.chikorita_lover.kaleidoscope.data;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlockFamilies;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.item.FireworkShellItem;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;
import java.util.function.BiConsumer;

public class KaleidoscopeModelProvider extends FabricModelProvider {
    private static final Model SHEARS_TEMPLATE_MODEL = new Model(Optional.of(Kaleidoscope.of("item/template_shears")), Optional.empty(), TextureKey.LAYER0);

    public KaleidoscopeModelProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static void registerWall(BlockStateModelGenerator generator, Block block, Block wallBlock) {
        TextureMap textures = TexturedModel.CUBE_ALL.get(block).getTextures();
        BiConsumer<Identifier, ModelSupplier> modelCollector = generator.modelCollector;
        WeightedVariant postModelId = BlockStateModelGenerator.createWeightedVariant(Models.TEMPLATE_WALL_POST.upload(wallBlock, textures, modelCollector));
        WeightedVariant lowSideModel = BlockStateModelGenerator.createWeightedVariant(Models.TEMPLATE_WALL_SIDE.upload(wallBlock, textures, modelCollector));
        WeightedVariant tallSideModel = BlockStateModelGenerator.createWeightedVariant(Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, textures, modelCollector));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, postModelId, lowSideModel, tallSideModel));
        Identifier parentModel = Models.WALL_INVENTORY.upload(wallBlock, textures, modelCollector);
        generator.registerParentedItemModel(wallBlock, parentModel);
    }

    private static void registerKiln(BlockStateModelGenerator generator, Block block, TexturedModel.Factory modelFactory) {
        WeightedVariant unlitModel = BlockStateModelGenerator.createWeightedVariant(modelFactory.upload(block, generator.modelCollector));
        final Identifier frontId = TextureMap.getSubId(block, "_front_on");
        final Identifier topId = TextureMap.getSubId(block, "_top_on");
        WeightedVariant litModel = BlockStateModelGenerator.createWeightedVariant(modelFactory.get(block).textures(textures -> textures.put(TextureKey.FRONT, frontId).put(TextureKey.TOP, topId)).upload(block, "_on", generator.modelCollector));
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block).with(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, litModel, unlitModel)).coordinate(BlockStateVariantMap.operations(Properties.HORIZONTAL_FACING).register(Direction.EAST, BlockStateModelGenerator.ROTATE_Y_90).register(Direction.SOUTH, BlockStateModelGenerator.ROTATE_Y_180).register(Direction.WEST, BlockStateModelGenerator.ROTATE_Y_270).register(Direction.NORTH, BlockStateModelGenerator.NO_OP)));
    }

    @Override
    public void generateBlockStateModels(final BlockStateModelGenerator generator) {
        KaleidoscopeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach((family) -> generator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        registerWall(generator, Blocks.POLISHED_GRANITE, KaleidoscopeBlocks.POLISHED_GRANITE_WALL);
        registerWall(generator, Blocks.POLISHED_DIORITE, KaleidoscopeBlocks.POLISHED_DIORITE_WALL);
        registerWall(generator, Blocks.POLISHED_ANDESITE, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL);

        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.SMOOTH_COPPER).parented(KaleidoscopeBlocks.SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER);

        generator.registerSimpleCubeAll(KaleidoscopeBlocks.CRACKED_TUFF_BRICKS);

        generator.blockStateCollector.accept(BlockStateModelGenerator.createMudBrickState(KaleidoscopeBlocks.CRACKED_MUD_BRICKS, BlockStateModelGenerator.createModelVariant(Models.CUBE_ALL.upload(KaleidoscopeBlocks.CRACKED_MUD_BRICKS, TextureMap.all(KaleidoscopeBlocks.CRACKED_MUD_BRICKS), generator.modelCollector)), TexturedModel.CUBE_ALL.get(KaleidoscopeBlocks.CRACKED_MUD_BRICKS).getTextures(), generator.modelCollector));

        generator.registerSimpleCubeAll(KaleidoscopeBlocks.CRACKED_END_STONE_BRICKS);

        generator.registerAxisRotated(KaleidoscopeBlocks.CHARCOAL_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);

        KaleidoscopeBlockFamilies.getFamilies().filter(family -> family.equals(KaleidoscopeBlockFamilies.GLASS) || family.getGroup().isPresent() && family.getGroup().get().equals("stained_glass")).forEach(family -> {
            generator.registerDoor(family.getVariant(BlockFamily.Variant.DOOR));
            generator.registerOrientableTrapdoor(family.getVariant(BlockFamily.Variant.TRAPDOOR));
        });

        generator.registerAxisRotated(KaleidoscopeBlocks.STICK_BLOCK, TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
        generator.registerNorthDefaultHorizontalRotatable(KaleidoscopeBlocks.SOUL_JACK_O_LANTERN, TextureMap.sideEnd(Blocks.PUMPKIN));

        generator.registerCubeWithCustomTextures(KaleidoscopeBlocks.FIREWORKS_TABLE, Blocks.JUNGLE_PLANKS, TextureMap::frontTopSide);
        registerKiln(generator, KaleidoscopeBlocks.KILN, TexturedModel.ORIENTABLE_WITH_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(Items.SHEARS, SHEARS_TEMPLATE_MODEL);
        generator.register(KaleidoscopeItems.NETHERITE_SHEARS, SHEARS_TEMPLATE_MODEL);
        generator.register(KaleidoscopeItems.CRIMSON_BOAT, Models.GENERATED);
        generator.register(KaleidoscopeItems.CRIMSON_CHEST_BOAT, Models.GENERATED);
        generator.register(KaleidoscopeItems.WARPED_BOAT, Models.GENERATED);
        generator.register(KaleidoscopeItems.WARPED_CHEST_BOAT, Models.GENERATED);
        generator.register(KaleidoscopeItems.JUKEBOX_MINECART, Models.GENERATED);
        generator.register(KaleidoscopeItems.CHAINMAIL_HORSE_ARMOR, Models.GENERATED);
        generator.register(KaleidoscopeItems.NETHERITE_HORSE_ARMOR, Models.GENERATED);
        generator.register(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP, Models.GENERATED);
        for (Item item : Registries.ITEM) {
            if (item instanceof FireworkShellItem) {
                generator.register(item, Models.GENERATED);
            }
        }
    }
}
