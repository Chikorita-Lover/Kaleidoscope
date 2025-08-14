package net.chikorita_lover.kaleidoscope.mixin.recipe;

import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeSerializers;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CookingRecipeJsonBuilder.class)
public class CookingRecipeJsonBuilderMixin {
    @Inject(method = "getCookingRecipeCategory", at = @At("HEAD"), cancellable = true)
    private static void getKilningRecipeCategory(RecipeSerializer<? extends AbstractCookingRecipe> serializer, ItemConvertible output, CallbackInfoReturnable<CookingRecipeCategory> ci) {
        if (serializer == KaleidoscopeRecipeSerializers.KILNING) {
            ci.setReturnValue(output.asItem() instanceof BlockItem ? CookingRecipeCategory.BLOCKS : CookingRecipeCategory.MISC);
        }
    }
}
