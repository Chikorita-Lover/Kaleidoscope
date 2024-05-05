package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.chikorita_lover.kaleidoscope.client.gui.screen.FireworksTableScreen;
import net.chikorita_lover.kaleidoscope.client.gui.screen.KilnScreen;
import net.chikorita_lover.kaleidoscope.client.particle.FireflyParticle;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlockEntities;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.OminousBottleItem;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

public class KaleidoscopeClient implements ClientModInitializer {
    private static final List<Block> BLOCKS_WITH_TOOLTIPS = List.of(Blocks.CHISELED_BOOKSHELF, Blocks.END_PORTAL_FRAME, Blocks.FLOWER_POT, Blocks.LECTERN, Blocks.LODESTONE, Blocks.RESPAWN_ANCHOR);

    private static void buildBlockInteractTooltip(ItemStack itemStack, List<Text> list, Block block) {
        if (!itemStack.isOf(block.asItem())) {
            return;
        }
        list.add(ScreenTexts.EMPTY);
        list.add(Text.translatable(block.getTranslationKey() + ".desc1").formatted(Formatting.GRAY));
        list.add(ScreenTexts.space().append(Text.translatable(block.getTranslationKey() + ".desc2").formatted(Formatting.BLUE)));
    }

    private static void buildFoodTooltip(ItemStack stack, List<Text> list) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        int nutrition = foodComponent.nutrition();
        float saturation = foodComponent.saturation();
        if (nutrition == 0.0 && saturation == 0.0) {
            return;
        }

        list.add(ScreenTexts.EMPTY);
        list.add(Text.translatable("item.modifiers.consumed").formatted(Formatting.GRAY));

        if (nutrition != 0.0F) {
            list.add(createModifierLine(nutrition, Text.translatable("item.modifiers.food")));
        }
        if (saturation != 0.0F) {
            list.add(createModifierLine(saturation, Text.translatable("item.modifiers.food_saturation")));
        }
    }

    private static Text createModifierLine(double value, Text text) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(1);
        String formattedValue = format.format(Math.abs(value));
        if (value > 0.0) {
            return Text.translatable("attribute.modifier.plus.0", formattedValue, text).formatted(Formatting.BLUE);
        } else if (value < 0.0) {
            return Text.translatable("attribute.modifier.take.0", formattedValue, text).formatted(Formatting.RED);
        } else {
            return Text.translatable("attribute.modifier.equals.0", formattedValue, text).formatted(Formatting.DARK_GREEN);
        }
    }

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            final int defaultColor = 0x385DC6;
            if (world == null || pos == null) {
                return defaultColor;
            }
            Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntities.POTION_CAULDRON);
            return optional.map(PotionCauldronBlockEntity::getColor).orElse(defaultColor);
        }, KaleidoscopeBlocks.POTION_CAULDRON);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex != 1 ? -1 : DyedColorComponent.getColor(stack, 0), Items.BUNDLE);

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GLASS_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLACK_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLUE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BROWN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.CYAN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GRAY_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GREEN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIME_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PINK_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.RED_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WHITE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GLASS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, RenderLayer.getTranslucent());

        HandledScreens.register(Kaleidoscope.FIREWORKS_TABLE, FireworksTableScreen::new);
        HandledScreens.register(Kaleidoscope.KILN_SCREEN_HANDLER, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
            for (Block block : BLOCKS_WITH_TOOLTIPS) {
                buildBlockInteractTooltip(stack, lines, block);
            }
            if (stack.contains(DataComponentTypes.FOOD) && !(stack.getItem() instanceof OminousBottleItem)) {
                buildFoodTooltip(stack, lines);
            }
        });

        ParticleFactoryRegistry.getInstance().register(KaleidoscopeParticleTypes.FIREFLY, FireflyParticle.Factory::new);
    }
}
