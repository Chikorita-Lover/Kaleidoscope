package net.chikorita_lover.kaleidoscope;

import net.chikorita_lover.chicory.api.recipe.ClientRecipeEvents;
import net.chikorita_lover.chicory.api.recipe.RecipeBookGroupRegistry;
import net.chikorita_lover.kaleidoscope.block.KaleidoscopeBlocks;
import net.chikorita_lover.kaleidoscope.client.KaleidoscopeClientNetworkHandler;
import net.chikorita_lover.kaleidoscope.client.gui.screen.FireworksTableScreen;
import net.chikorita_lover.kaleidoscope.client.gui.screen.KilnScreen;
import net.chikorita_lover.kaleidoscope.client.particle.FireflyParticle;
import net.chikorita_lover.kaleidoscope.client.render.StriderChestFeatureRenderer;
import net.chikorita_lover.kaleidoscope.entity.KaleidoscopeEntityTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.registry.KaleidoscopeParticleTypes;
import net.chikorita_lover.kaleidoscope.screen.KaleidoscopeScreenHandlerTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.OminousBottleItem;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.text.NumberFormat;
import java.util.List;

public class KaleidoscopeClient implements ClientModInitializer {
    public static final EntityModelLayer STRIDER_CHEST_LAYER = new EntityModelLayer(Identifier.of("strider"), "chest");
    public static final EntityModelLayer JUKEBOX_MINECART_LAYER = new EntityModelLayer(Kaleidoscope.of("jukebox_minecart"), "main");
    private static final Text FOOD_TEXT = Text.translatable("item.modifiers.food");
    private static final Text FOOD_SATURATION_TEXT = Text.translatable("item.modifiers.food_saturation");
    private static final RecipeBookGroup KILN_SEARCH = RecipeBookGroupRegistry.register("kaleidoscope_kiln_search", new ItemStack(Items.COMPASS));
    private static final RecipeBookGroup KILN_BLOCKS = RecipeBookGroupRegistry.register("kaleidoscope_kiln_blocks", new ItemStack(Blocks.STONE));
    private static final RecipeBookGroup KILN_MISC = RecipeBookGroupRegistry.register("kaleidoscope_kiln_misc", new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.CHARCOAL));

    private static void buildFoodTooltip(ItemStack stack, List<Text> list) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        int nutrition = foodComponent.nutrition();
        float saturation = foodComponent.saturation();
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

        ClientRecipeEvents.GROUP_RECIPE.register(recipe -> {
            if (recipe instanceof KilningRecipe kilningRecipe) {
                return kilningRecipe.getCategory() == CookingRecipeCategory.BLOCKS ? KILN_BLOCKS : KILN_MISC;
            }
            return null;
        });

        EntityModelLayerRegistry.registerModelLayer(STRIDER_CHEST_LAYER, StriderChestFeatureRenderer::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(JUKEBOX_MINECART_LAYER, MinecartEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(KaleidoscopeEntityTypes.JUKEBOX_MINECART, context -> new MinecartEntityRenderer<>(context, JUKEBOX_MINECART_LAYER));

        HandledScreens.register(KaleidoscopeScreenHandlerTypes.FIREWORKS_TABLE, FireworksTableScreen::new);
        HandledScreens.register(KaleidoscopeScreenHandlerTypes.KILN, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
            if (KaleidoscopeConfig.SHOW_FOOD_TOOLTIPS.get() && stack.contains(DataComponentTypes.FOOD) && !(stack.getItem() instanceof OminousBottleItem)) {
                buildFoodTooltip(stack, lines);
            }
        });

        KaleidoscopeClientNetworkHandler.register();

        ParticleFactoryRegistry.getInstance().register(KaleidoscopeParticleTypes.FIREFLY, FireflyParticle.Factory::new);

        RecipeBookGroupRegistry.addGroups(KilningRecipe.CATEGORY, KILN_SEARCH, KILN_BLOCKS, KILN_MISC);
    }
}
