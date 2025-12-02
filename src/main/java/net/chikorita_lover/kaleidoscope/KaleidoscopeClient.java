package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.chicory.api.splash.SplashTextRegistry;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.client.KaleidoscopeClientNetworkHandler;
import net.chikorita_lover.kaleidoscope.client.gui.screen.FireworksTableScreen;
import net.chikorita_lover.kaleidoscope.client.gui.screen.KilnScreen;
import net.chikorita_lover.kaleidoscope.client.render.KaleidoscopeEntityModelLayers;
import net.chikorita_lover.kaleidoscope.client.render.StriderChestEntityModel;
import net.chikorita_lover.kaleidoscope.client.render.StriderChestFeatureRenderer;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.SpecialBlockRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.MinecartEntityModel;
import net.minecraft.client.render.item.model.special.ChestModelRenderer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KaleidoscopeClient implements ClientModInitializer {
    public static final Set<String> TRIM_PALETTES = new HashSet<>();
    private static final Text FOOD_TEXT = Text.translatable("item.modifiers.food");
    private static final Text FOOD_SATURATION_TEXT = Text.translatable("item.modifiers.food_saturation");

    private static void buildFoodTooltip(ItemStack stack, List<Text> list) {
        FoodComponent component = stack.get(DataComponentTypes.FOOD);
        int nutrition = component.nutrition();
        float saturation = component.saturation();
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(1);
        if (nutrition != 0.0F) {
            list.add(ScreenTexts.space().append(Text.translatable("attribute.modifier.equals.0", nf.format(Math.abs(nutrition)), FOOD_TEXT).formatted(Formatting.DARK_GREEN)));
        }
        if (saturation != 0.0F) {
            list.add(ScreenTexts.space().append(Text.translatable("attribute.modifier.equals.0", nf.format(Math.abs(saturation)), FOOD_SATURATION_TEXT).formatted(Formatting.DARK_GREEN)));
        }
    }

    @Override
    public void onInitializeClient() {
        SplashTextRegistry.addFile(Kaleidoscope.of("texts/splashes.txt"));
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GLASS_DOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.WHITE_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GRAY_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BLACK_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BROWN_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.RED_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIME_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GREEN_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.CYAN_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BLUE_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.PINK_STAINED_GLASS_DOOR, BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GLASS_TRAPDOOR, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.WHITE_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIGHT_GRAY_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GRAY_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BLACK_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BROWN_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.RED_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.ORANGE_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.YELLOW_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIME_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.GREEN_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.CYAN_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.LIGHT_BLUE_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.BLUE_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.PURPLE_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.MAGENTA_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(KaleidoscopeBlocks.PINK_STAINED_GLASS_TRAPDOOR, BlockRenderLayer.TRANSLUCENT);

        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.CRIMSON_BOAT, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.CRIMSON_CHEST_BOAT, BoatEntityModel::getChestTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.JUKEBOX_MINECART, MinecartEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.STRIDER_CHEST, StriderChestEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.WARPED_BOAT, BoatEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(KaleidoscopeEntityModelLayers.WARPED_CHEST_BOAT, BoatEntityModel::getChestTexturedModelData);

        EntityRendererRegistry.register(KaleidoscopeEntityTypes.CRIMSON_BOAT, context -> new BoatEntityRenderer(context, KaleidoscopeEntityModelLayers.CRIMSON_BOAT));
        EntityRendererRegistry.register(KaleidoscopeEntityTypes.CRIMSON_CHEST_BOAT, context -> new BoatEntityRenderer(context, KaleidoscopeEntityModelLayers.CRIMSON_CHEST_BOAT));
        EntityRendererRegistry.register(KaleidoscopeEntityTypes.JUKEBOX_MINECART, context -> new MinecartEntityRenderer(context, KaleidoscopeEntityModelLayers.JUKEBOX_MINECART));
        EntityRendererRegistry.register(KaleidoscopeEntityTypes.WARPED_BOAT, context -> new BoatEntityRenderer(context, KaleidoscopeEntityModelLayers.WARPED_BOAT));
        EntityRendererRegistry.register(KaleidoscopeEntityTypes.WARPED_CHEST_BOAT, context -> new BoatEntityRenderer(context, KaleidoscopeEntityModelLayers.WARPED_CHEST_BOAT));

        HandledScreens.register(KaleidoscopeScreenHandlerTypes.FIREWORKS_TABLE, FireworksTableScreen::new);
        HandledScreens.register(KaleidoscopeScreenHandlerTypes.KILN, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
            if (KaleidoscopeConfig.SHOW_FOOD_TOOLTIPS.get() && stack.contains(DataComponentTypes.FOOD)) {
                buildFoodTooltip(stack, lines);
            }
        });

        KaleidoscopeClientNetworkHandler.register();

        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.WHITE_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("white")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.LIGHT_GRAY_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("light_gray")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.GRAY_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("gray")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.BLACK_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("black")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.BROWN_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("brown")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.RED_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("red")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.ORANGE_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("orange")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.YELLOW_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("yellow")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.LIME_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("lime")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.GREEN_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("green")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.CYAN_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("cyan")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.LIGHT_BLUE_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("light_blue")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.BLUE_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("blue")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.PURPLE_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("purple")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.MAGENTA_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("magenta")));
        SpecialBlockRendererRegistry.register(KaleidoscopeBlocks.PINK_CHEST, new ChestModelRenderer.Unbaked(Kaleidoscope.of("pink")));
    }
}
