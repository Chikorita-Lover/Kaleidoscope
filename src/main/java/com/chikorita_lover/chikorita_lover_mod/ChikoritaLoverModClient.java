package com.chikorita_lover.chikorita_lover_mod;

import com.chikorita_lover.chikorita_lover_mod.client.gui.screen.ingame.KilnScreen;
import com.chikorita_lover.chikorita_lover_mod.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ChikoritaLoverModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, RenderLayer.getCutout());

        HandledScreens.register(ChikoritaLoverMod.KILN_SCREEN_HANDLER, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, context, tooltip) -> {
            if (stack.getItem().isFood()) {
                buildFoodTooltip(stack, tooltip);
            }
        });
    }

    public void buildFoodTooltip(ItemStack stack, List<Text> list) {
        FoodComponent foodComponent = stack.getItem().getFoodComponent();
        assert foodComponent != null;
        int food = foodComponent.getHunger();
        float saturationModifier = foodComponent.getSaturationModifier();
        double foodSaturation = Math.round(20.0 * food * saturationModifier) / 10.0;

        if (food == 0.0 && foodSaturation == 0.0) return;

        Object[] hungerTooltip = new Object[]{Math.abs(food), Text.translatable("item.modifiers.food")};
        Object[] saturationTooltip = new Object[]{ItemStack.MODIFIER_FORMAT.format(Math.abs(foodSaturation)), Text.translatable("item.modifiers.food_saturation")};

        list.add(ScreenTexts.EMPTY);
        list.add(Text.translatable("item.modifiers.consumed").formatted(Formatting.GRAY));

        if (food > 0.0) {
            list.add(Text.translatable("attribute.modifier.plus.0", hungerTooltip).formatted(Formatting.BLUE));
        } else if (food < 0.0) {
            list.add(Text.translatable("attribute.modifier.take.0", hungerTooltip).formatted(Formatting.RED));
        }

        if (foodSaturation > 0.0) {
            list.add(Text.translatable("attribute.modifier.plus.0", saturationTooltip).formatted(Formatting.BLUE));
        } else if (foodSaturation < 0.0) {
            list.add(Text.translatable("attribute.modifier.take.0", saturationTooltip).formatted(Formatting.RED));
        }
    }
}
