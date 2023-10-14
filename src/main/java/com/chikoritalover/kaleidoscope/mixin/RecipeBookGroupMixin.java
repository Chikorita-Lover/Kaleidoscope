package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.book.RecipeBookCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(RecipeBookGroup.class)
public class RecipeBookGroupMixin {
    private static final RecipeBookGroup KILN_SEARCH = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_SEARCH");
    private static final RecipeBookGroup KILN_BLOCKS = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_BLOCKS");
    private static final RecipeBookGroup KILN_MISC = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_MISC");

    @Shadow
    @Final
    @Mutable
    public static Map<RecipeBookGroup, List<RecipeBookGroup>> SEARCH_MAP;

    @Inject(at = @At("TAIL"), method = "<clinit>")
    private static void setSearchMap(CallbackInfo ci) {
        Map<RecipeBookGroup, List<RecipeBookGroup>> newMap = new HashMap<>();
        SEARCH_MAP.forEach((group, groups) -> newMap.put(group, new ArrayList<>(groups)));
        newMap.put(KILN_SEARCH, ImmutableList.of(KILN_BLOCKS, KILN_MISC));
        SEARCH_MAP = newMap;
    }

    @Inject(method = "getGroups", at = @At("HEAD"), cancellable = true)
    private static void getGroups(RecipeBookCategory category, CallbackInfoReturnable<List<RecipeBookGroup>> callbackInfo) {
        if (category == Kaleidoscope.KILNING_CATEGORY) {
            List<RecipeBookGroup> var10000 = ImmutableList.of(KILN_SEARCH, KILN_BLOCKS, KILN_MISC);
            callbackInfo.setReturnValue(var10000);
            callbackInfo.cancel();
        }
    }
}
