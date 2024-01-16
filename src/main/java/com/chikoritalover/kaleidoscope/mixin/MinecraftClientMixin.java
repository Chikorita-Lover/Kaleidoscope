package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.search.SearchManager;
import net.minecraft.client.search.TextSearchProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final private SearchManager searchManager;

    @Inject(method = "initializeSearchProviders", at = @At("TAIL"))
    public void initializeSearchProviders(CallbackInfo ci) {
        this.searchManager.put(BrewingRecipeBookWidget.BREWING_RECIPE_OUTPUT, collections -> new TextSearchProvider<>(resultCollection -> resultCollection.getAllRecipes().stream().flatMap(recipe -> {
            ItemStack itemStack = recipe.getOutput();
            return itemStack.getTooltip(null, TooltipContext.Default.BASIC).stream();
        }).map(text -> Formatting.strip(text.getString()).trim()).filter(text -> !text.isEmpty()), resultCollection -> resultCollection.getAllRecipes().stream().map(recipe -> Registries.ITEM.getId(recipe.getOutput().getItem())), collections));
    }
}
