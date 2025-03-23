package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.recipe.FireworkRocketRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireworkRocketRecipe.class)
public class FireworkRocketRecipeMixin {
    @ModifyExpressionValue(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/Ingredient;test(Lnet/minecraft/item/ItemStack;)Z", ordinal = 2))
    private boolean isValidFireworkStar(boolean matches) {
        return matches && !KaleidoscopeConfig.DISABLE_FIREWORK_RECIPES.get();
    }
}
