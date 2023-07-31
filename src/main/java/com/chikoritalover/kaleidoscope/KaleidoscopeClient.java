package com.chikoritalover.kaleidoscope;

import com.chikoritalover.kaleidoscope.block.entity.PotionCauldronBlockEntity;
import com.chikoritalover.kaleidoscope.client.gui.screen.KilnScreen;
import com.chikoritalover.kaleidoscope.client.particle.FireflyParticle;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeBlocks;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeItems;
import com.chikoritalover.kaleidoscope.registry.KaleidoscopeParticleTypes;
import com.google.common.collect.Lists;
import me.melontini.dark_matter.enums.util.EnumWrapper;
import me.melontini.dark_matter.recipe_book.RecipeBookHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.*;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class KaleidoscopeClient implements ClientModInitializer {
    private static final List<Block> BLOCKS_WITH_TOOLTIPS = List.of(Blocks.CHISELED_BOOKSHELF, Blocks.END_PORTAL_FRAME, Blocks.FLOWER_POT, Blocks.LECTERN, Blocks.LODESTONE, Blocks.RESPAWN_ANCHOR);
    private static final RecipeBookGroup KILN_SEARCH = EnumWrapper.RecipeBookGroup.extend("KALEIDOSCOPE_KILN_SEARCH", Items.COMPASS.getDefaultStack());
    private static final RecipeBookGroup KILN_BLOCKS = EnumWrapper.RecipeBookGroup.extend("KALEIDOSCOPE_KILN_BLOCKS", Items.STONE.getDefaultStack());
    private static final RecipeBookGroup KILN_MISC = EnumWrapper.RecipeBookGroup.extend("KALEIDOSCOPE_KILN_MISC", Items.BRICK.getDefaultStack(), Items.CHARCOAL.getDefaultStack());

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null || world.getBlockEntity(pos) == null) {
                return 3694022;
            }
            return ((PotionCauldronBlockEntity) world.getBlockEntity(pos)).getColor();
        }, KaleidoscopeBlocks.POTION_CAULDRON);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)stack.getItem()).getColor(stack), Items.BUNDLE);

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
        
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.EXPOSED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WEATHERED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.OXIDIZED_COPPER_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_EXPOSED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_WEATHERED_COPPER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_OXIDIZED_COPPER_DOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.EXPOSED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WEATHERED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.OXIDIZED_COPPER_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(KaleidoscopeBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, RenderLayer.getCutout());

        HandledScreens.register(Kaleidoscope.KILN_SCREEN_HANDLER, KilnScreen::new);

        ItemTooltipCallback.EVENT.register((stack, context, tooltip) -> {
            for (Block block : BLOCKS_WITH_TOOLTIPS) {
                buildBlockInteractTooltip(stack, tooltip, block);
            }
            if (stack.getItem().isFood()) {
                buildFoodTooltip(stack, tooltip);
            }
            if (stack.getItem() instanceof HorseArmorItem) {
                buildHorseArmorTooltip(stack, tooltip);
            }
        });

        ParticleFactoryRegistry.getInstance().register(KaleidoscopeParticleTypes.FIREFLY, FireflyParticle.Factory::new);

        RecipeBookHelper.addRecipePredicate(Kaleidoscope.KILNING, recipe -> {
            AbstractCookingRecipe abstractCookingRecipe = (AbstractCookingRecipe) recipe;
            return abstractCookingRecipe.getCategory() == CookingRecipeCategory.BLOCKS ? KILN_BLOCKS : KILN_MISC;
        });
        RecipeBookHelper.addToGetGroups(Kaleidoscope.KILNING_CATEGORY, Lists.newArrayList(KILN_SEARCH, KILN_BLOCKS, KILN_MISC));
        RecipeBookHelper.addToSearchMap(KILN_SEARCH, Lists.newArrayList(KILN_BLOCKS, KILN_MISC));
    }

    public void buildBlockInteractTooltip(ItemStack itemStack, List<Text> list, Block block) {
        if (!itemStack.isOf(block.asItem())) {
            return;
        }
        list.add(ScreenTexts.EMPTY);
        list.add(Text.translatable(block.getTranslationKey() + ".desc1").formatted(Formatting.GRAY));
        list.add(ScreenTexts.space().append(Text.translatable(block.getTranslationKey() + ".desc2").formatted(Formatting.BLUE)));
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

    public void buildHorseArmorTooltip(ItemStack stack, List<Text> list) {
        HorseArmorItem horseArmorItem = (HorseArmorItem) stack.getItem();
        int armor = horseArmorItem.getBonus();

        if (armor == 0.0) return;

        Object[] armorTooltip = new Object[]{Math.abs(armor), Text.translatable("attribute.name.generic.armor")};

        list.add(ScreenTexts.EMPTY);
        list.add(Text.translatable("item.modifiers.horse").formatted(Formatting.GRAY));

        if (armor > 0.0) {
            list.add(Text.translatable("attribute.modifier.plus.0", armorTooltip).formatted(Formatting.BLUE));
        } else if (armor < 0.0) {
            list.add(Text.translatable("attribute.modifier.take.0", armorTooltip).formatted(Formatting.RED));
        }
        if (stack.isOf(KaleidoscopeItems.NETHERITE_HORSE_ARMOR)) {
            list.add(Text.translatable("attribute.modifier.plus.0", 3, Text.translatable("attribute.name.generic.armor_toughness")).formatted(Formatting.BLUE));
            list.add(Text.translatable("attribute.modifier.plus.0", 2, Text.translatable("attribute.name.generic.knockback_resistance")).formatted(Formatting.BLUE));
        }
    }
}
