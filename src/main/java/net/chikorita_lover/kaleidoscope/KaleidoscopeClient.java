package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import net.chikorita_lover.kaleidoscope.client.KaleidoscopeClientNetworkHandler;
import net.chikorita_lover.kaleidoscope.client.gui.screen.FireworksTableScreen;
import net.chikorita_lover.kaleidoscope.client.gui.screen.KilnScreen;
import net.chikorita_lover.kaleidoscope.client.particle.FireflyParticle;
import net.chikorita_lover.kaleidoscope.client.render.StriderChestFeatureRenderer;
import net.chikorita_lover.kaleidoscope.block.entity.KaleidoscopeBlockEntityTypes;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeParticleTypes;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
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
    public static final EntityModelLayer STRIDER_CHEST = new EntityModelLayer(Identifier.of("strider"), "chest");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();

    static {
        NUMBER_FORMAT.setMaximumFractionDigits(1);
    }

    private static void buildFoodTooltip(ItemStack stack, List<Text> list) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        int nutrition = foodComponent.nutrition();
        float saturation = foodComponent.saturation();
        if (nutrition == 0.0 && saturation == 0.0) {
            return;
        }
        if (nutrition != 0.0F) {
            list.add(ScreenTexts.space().append(Text.translatable("attribute.modifier.equals.0", NUMBER_FORMAT.format(Math.abs(nutrition)), Text.translatable("item.modifiers.food")).formatted(Formatting.DARK_GREEN)));
        }
        if (saturation != 0.0F) {
            list.add(ScreenTexts.space().append(Text.translatable("attribute.modifier.equals.0", NUMBER_FORMAT.format(Math.abs(saturation)), Text.translatable("item.modifiers.food_saturation")).formatted(Formatting.DARK_GREEN)));
        }
    }

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            final int defaultColor = 0x385DC6;
            if (world == null || pos == null) {
                return defaultColor;
            }
            Optional<PotionCauldronBlockEntity> optional = world.getBlockEntity(pos, KaleidoscopeBlockEntityTypes.POTION_CAULDRON);
            return optional.map(PotionCauldronBlockEntity::getColor).orElse(defaultColor);
        }, KaleidoscopeBlocks.POTION_CAULDRON);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex != 1 ? -1 : DyedColorComponent.getColor(stack, 0), Items.BUNDLE);

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

        EntityModelLayerRegistry.registerModelLayer(STRIDER_CHEST, StriderChestFeatureRenderer::getTexturedModelData);

        HandledScreens.register(KaleidoscopeScreenHandlerTypes.FIREWORKS_TABLE, FireworksTableScreen::new);
        HandledScreens.register(KaleidoscopeScreenHandlerTypes.KILN, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
            if (stack.contains(DataComponentTypes.FOOD) && !(stack.getItem() instanceof OminousBottleItem)) {
                buildFoodTooltip(stack, lines);
            }
        });

        KaleidoscopeClientNetworkHandler.register();

        ParticleFactoryRegistry.getInstance().register(KaleidoscopeParticleTypes.FIREFLY, FireflyParticle.Factory::new);
    }
}
