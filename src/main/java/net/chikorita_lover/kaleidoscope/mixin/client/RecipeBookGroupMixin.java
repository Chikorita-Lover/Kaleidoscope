package net.chikorita_lover.kaleidoscope.mixin.client;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableList;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.book.RecipeBookCategory;
import org.spongepowered.asm.mixin.*;
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
    @Unique
    private static final RecipeBookGroup KILN_SEARCH = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_SEARCH");
    @Unique
    private static final RecipeBookGroup KILN_BLOCKS = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_BLOCKS");
    @Unique
    private static final RecipeBookGroup KILN_MISC = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_MISC");

    @Shadow
    @Final
    @Mutable
    public static Map<RecipeBookGroup, List<RecipeBookGroup>> SEARCH_MAP;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void setSearchMap(CallbackInfo ci) {
        Map<RecipeBookGroup, List<RecipeBookGroup>> searchMap = new HashMap<>();
        SEARCH_MAP.forEach((group, groups) -> searchMap.put(group, new ArrayList<>(groups)));
        searchMap.put(KILN_SEARCH, ImmutableList.of(KILN_BLOCKS, KILN_MISC));
        SEARCH_MAP = searchMap;
    }

    @Inject(method = "getGroups", at = @At("HEAD"), cancellable = true)
    private static void getKilnGroups(RecipeBookCategory category, CallbackInfoReturnable<List<RecipeBookGroup>> cir) {
        if (category == Kaleidoscope.KILNING_CATEGORY) {
            List<RecipeBookGroup> groups = ImmutableList.of(KILN_SEARCH, KILN_BLOCKS, KILN_MISC);
            cir.setReturnValue(groups);
            cir.cancel();
        }
    }
}
