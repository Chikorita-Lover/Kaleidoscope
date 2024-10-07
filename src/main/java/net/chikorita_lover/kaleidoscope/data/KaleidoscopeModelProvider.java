package net.chikorita_lover.kaleidoscope.data;

import com.google.gson.JsonElement;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlockFamilies;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.item.FireworkShellItem;
import net.chikorita_lover.kaleidoscope.item.KaleidoscopeItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class KaleidoscopeModelProvider extends FabricModelProvider {
    private static final Model SHEARS_TEMPLATE_MODEL = new Model(Optional.of(Kaleidoscope.of("item/template_shears")), Optional.empty(), TextureKey.LAYER0);

    public KaleidoscopeModelProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private static void registerWall(BlockStateModelGenerator blockStateModelGenerator, Block block, Block wallBlock) {
        TextureMap textures = TexturedModel.CUBE_ALL.get(block).getTextures();
        BiConsumer<Identifier, Supplier<JsonElement>> modelCollector = blockStateModelGenerator.modelCollector;
        Identifier postModelId = Models.TEMPLATE_WALL_POST.upload(wallBlock, textures, modelCollector);
        Identifier lowSideModelId = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, textures, modelCollector);
        Identifier tallSideModelId = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, textures, modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, postModelId, lowSideModelId, tallSideModelId));
        Identifier inventoryModelId = Models.WALL_INVENTORY.upload(wallBlock, textures, modelCollector);
        blockStateModelGenerator.registerParentedItemModel(wallBlock, inventoryModelId);
    }

    private static void registerKiln(BlockStateModelGenerator generator, Block block, TexturedModel.Factory modelFactory) {
        Identifier unlitModel = modelFactory.upload(block, generator.modelCollector);
        Identifier frontId = TextureMap.getSubId(block, "_front_on");
        Identifier topId = TextureMap.getSubId(block, "_top_on");
        Identifier litModel = modelFactory.get(block).textures(textures -> textures.put(TextureKey.FRONT, frontId).put(TextureKey.TOP, topId)).upload(block, "_on", generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, litModel, unlitModel)).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        KaleidoscopeBlockFamilies.getFamilies().filter(BlockFamily::shouldGenerateModels).forEach((family) -> generator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        registerWall(generator, Blocks.POLISHED_GRANITE, KaleidoscopeBlocks.POLISHED_GRANITE_WALL);
        registerWall(generator, Blocks.POLISHED_DIORITE, KaleidoscopeBlocks.POLISHED_DIORITE_WALL);
        registerWall(generator, Blocks.POLISHED_ANDESITE, KaleidoscopeBlocks.POLISHED_ANDESITE_WALL);

        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.SMOOTH_COPPER).parented(KaleidoscopeBlocks.SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.EXPOSED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.EXPOSED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_EXPOSED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_EXPOSED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WEATHERED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.WEATHERED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_WEATHERED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_WEATHERED_SMOOTH_COPPER);
        generator.registerCubeAllModelTexturePool(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.OXIDIZED_SMOOTH_COPPER).parented(KaleidoscopeBlocks.OXIDIZED_SMOOTH_COPPER, KaleidoscopeBlocks.WAXED_OXIDIZED_SMOOTH_COPPER).family(KaleidoscopeBlockFamilies.WAXED_OXIDIZED_SMOOTH_COPPER);

        generator.registerSimpleCubeAll(KaleidoscopeBlocks.CRACKED_TUFF_BRICKS);

        Models.CUBE_ALL.upload(KaleidoscopeBlocks.CRACKED_MUD_BRICKS, TexturedModel.CUBE_ALL.get(KaleidoscopeBlocks.CRACKED_MUD_BRICKS).getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createMudBrickState(KaleidoscopeBlocks.CRACKED_MUD_BRICKS, TextureMap.getId(KaleidoscopeBlocks.CRACKED_MUD_BRICKS), TexturedModel.CUBE_ALL.get(KaleidoscopeBlocks.CRACKED_MUD_BRICKS).getTextures(), generator.modelCollector));

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
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(KaleidoscopeBlocks.POTION_CAULDRON).coordinate(BlockStateVariantMap.create(LeveledCauldronBlock.LEVEL).register(1, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_LEVEL1.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_level1", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), generator.modelCollector))).register(2, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_LEVEL2.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_level2", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), generator.modelCollector))).register(3, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_FULL.upload(KaleidoscopeBlocks.POTION_CAULDRON, "_full", TextureMap.cauldron(TextureMap.getSubId(Blocks.WATER, "_still")), generator.modelCollector)))));
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

        generator.register(KaleidoscopeItems.DISC_FRAGMENT_PIGSTEP, Models.GENERATED);
        for (Item item : Registries.ITEM) {
            if (item instanceof FireworkShellItem) {
                generator.register(item, Models.GENERATED);
            }
        }
    }
}
