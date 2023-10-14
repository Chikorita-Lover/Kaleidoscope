package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    private static final RecipeBookGroup KILN_BLOCKS = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_BLOCKS");
    private static final RecipeBookGroup KILN_MISC = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_MISC");

    @Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
    private static void getGroupForRecipe(RecipeEntry<?> recipe, CallbackInfoReturnable<RecipeBookGroup> callbackInfo) {
        Recipe<?> recipe2 = recipe.value();
        RecipeType<?> recipeType = recipe2.getType();
        if (recipe2 instanceof AbstractCookingRecipe abstractCookingRecipe) {
            CookingRecipeCategory cookingRecipeCategory = abstractCookingRecipe.getCategory();
            if (recipeType == Kaleidoscope.KILNING) {
                callbackInfo.setReturnValue(cookingRecipeCategory == CookingRecipeCategory.BLOCKS ? KILN_BLOCKS : KILN_MISC);
                callbackInfo.cancel();
            }
        }
    }
}
