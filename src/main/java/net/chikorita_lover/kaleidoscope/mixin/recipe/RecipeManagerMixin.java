package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.Gson;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.recipe.*;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends JsonDataLoader {
    public RecipeManagerMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Unique
    private static Identifier createKilningId(Identifier id) {
        String smeltingPath = id.getPath();
        String kilningPath;
        if (smeltingPath.matches("\\w+_from_smelting(\\w+)?")) {
            kilningPath = smeltingPath.replace("from_smelting", "from_kilning");
        } else if (smeltingPath.matches("\\w+_from_\\w+")) {
            kilningPath = smeltingPath.replace("from", "from_kilning");
        } else {
            kilningPath = smeltingPath + "_from_kilning";
        }
        String namespace = id.getNamespace();
        if (!namespace.equals("minecraft") && !namespace.equals(Kaleidoscope.MODID)) {
            kilningPath = namespace + "/" + kilningPath;
        }
        return Kaleidoscope.of(kilningPath);
    }

    @ModifyExpressionValue(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMultimap$Builder;build()Lcom/google/common/collect/ImmutableMultimap;"))
    private ImmutableMultimap<RecipeType<?>, RecipeEntry<Recipe<?>>> foo(ImmutableMultimap<RecipeType<?>, RecipeEntry<Recipe<?>>> recipesByType, @Local ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<Recipe<?>>> builder) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.clear();
        recipesByType.get(RecipeType.SMELTING).forEach(smeltingEntry -> {
            SmeltingRecipe smeltingRecipe = (SmeltingRecipe) smeltingEntry.value();
            boolean hasSmokingBlasting = recipesByType.get(RecipeType.SMOKING).stream().anyMatch(smokingEntry -> smeltingEntry.id().getNamespace().equals(smokingEntry.id().getNamespace()) && smokingEntry.value().getIngredients().equals(smeltingRecipe.getIngredients())) || recipesByType.get(RecipeType.BLASTING).stream().anyMatch(blastingEntry -> smeltingEntry.id().getNamespace().equals(blastingEntry.id().getNamespace()) && blastingEntry.value().getIngredients().equals(smeltingRecipe.getIngredients()));
            if (hasSmokingBlasting) {
                return;
            }
            Identifier id = createKilningId(smeltingEntry.id());
            KilningRecipe kilningRecipe = new KilningRecipe(smeltingRecipe.getGroup(), smeltingRecipe.getCategory(), smeltingRecipe.getIngredients().get(0), smeltingRecipe.getResult(null), smeltingRecipe.getExperience(), smeltingRecipe.getCookingTime() / 2);
            RecipeEntry<Recipe<?>> kilningEntry = new RecipeEntry<>(id, kilningRecipe);
            KilningRecipe.KILNING_RECIPE_ENTRIES.add(kilningEntry);
            builder.put(Kaleidoscope.KILNING, kilningEntry);
        });
        return builder.build();
    }

    @ModifyExpressionValue(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"))
    private ImmutableMap<Identifier, RecipeEntry<Recipe<?>>> foo(ImmutableMap<Identifier, RecipeEntry<Recipe<?>>> recipesById, @Local ImmutableMap.Builder<Identifier, RecipeEntry<Recipe<?>>> builder2) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.forEach(entry -> {
            builder2.put(entry.id(), entry);
        });
        return builder2.build();
    }
}
