package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {
    @Shadow
    @Final
    private static List<Ingredient> POTION_TYPES;

    @Inject(method = "registerItemRecipe", at = @At("TAIL"))
    private static void registerItemRecipe(Item input, Item ingredient, Item output, CallbackInfo ci) {
        Potion potion = Potions.EMPTY; // Call the Potions class to register all potions
        for (Potion potion2 : Registries.POTION) {
            if (potion2 == potion) continue;
            Ingredient ingredient2 = Ingredient.ofStacks(PotionUtil.setPotion(new ItemStack(input), potion2));
            ItemStack itemStack2 = PotionUtil.setPotion(new ItemStack(output), potion2);
            BrewingRecipe.BREWING_RECIPES.add(new BrewingRecipe(ingredient2, potion2, Ingredient.ofItems(ingredient), itemStack2));
        }
    }

    @Inject(method = "registerPotionRecipe", at = @At("TAIL"))
    private static void registerPotionRecipe(Potion input, Item item, Potion output, CallbackInfo ci) {
        for (Ingredient ingredient : POTION_TYPES) {
            for (ItemStack itemStack : ingredient.getMatchingStacks()) {
                Ingredient ingredient2 = Ingredient.ofStacks(PotionUtil.setPotion(itemStack.copy(), input));
                ItemStack itemStack2 = PotionUtil.setPotion(itemStack.copy(), output);
                BrewingRecipe.BREWING_RECIPES.add(new BrewingRecipe(ingredient2, input, Ingredient.ofItems(item), itemStack2));
            }
        }
    }
}
