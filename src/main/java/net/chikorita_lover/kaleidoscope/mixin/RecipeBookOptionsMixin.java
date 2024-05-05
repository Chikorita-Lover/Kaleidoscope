package net.chikorita_lover.kaleidoscope.mixin;

import com.mojang.datafixers.util.Pair;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipeBookOptions.class)
public class RecipeBookOptionsMixin {
    @Shadow
    @Final
    @Mutable
    private static Map<RecipeBookCategory, Pair<String, String>> CATEGORY_OPTION_NAMES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void setCategoryOptionNames(CallbackInfo ci) {
        Map<RecipeBookCategory, Pair<String, String>> names = new HashMap<>(CATEGORY_OPTION_NAMES);
        names.put(Kaleidoscope.KILNING_CATEGORY, Pair.of("isKaleidoscopeKilnGuiOpen", "isKaleidoscopeKilnFilteringCraftable"));
        CATEGORY_OPTION_NAMES = names;
    }
}
