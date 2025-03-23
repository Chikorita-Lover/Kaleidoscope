package net.chikorita_lover.kaleidoscope.mixin.recipe;

import net.chikorita_lover.kaleidoscope.KaleidoscopeConfig;
import net.minecraft.recipe.FireworkStarRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkStarRecipe.class)
public class FireworkStarRecipeMixin {
    @Inject(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void isRecipeEnabled(CraftingRecipeInput craftingRecipeInput, World world, CallbackInfoReturnable<Boolean> cir) {
        if (KaleidoscopeConfig.DISABLE_FIREWORK_RECIPES.get()) {
            cir.setReturnValue(false);
        }
    }
}
