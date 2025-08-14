package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.chikorita_lover.kaleidoscope.screen.KilnScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(ServerRecipeManager.class)
public abstract class ServerRecipeManagerMixin {
    @Shadow
    private static ServerRecipeManager.SoleIngredientGetter cookingIngredientGetter(RecipeType<? extends SingleStackRecipe> expectedType) {
        return null;
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

    @Unique
    private static Identifier createCrackingId(Block result) {
        Identifier id = Registries.BLOCK.getId(result);
        String path = id.getPath().concat("_from_cracking");
        String namespace = id.getNamespace();
        if (!namespace.equals("minecraft") && !namespace.equals(Kaleidoscope.MODID)) {
            path = namespace + '/' + path;
        }
        return Kaleidoscope.of(path);
    }

    @Unique
    private static Identifier createMossScrapingId(Block block) {
        Identifier id = Registries.BLOCK.getId(block);
        String path = id.getPath().concat("_scraping");
        String namespace = id.getNamespace();
        if (!namespace.equals("minecraft") && !namespace.equals(Kaleidoscope.MODID)) {
            path = namespace + '/' + path;
        }
        return Kaleidoscope.of(path);
    }

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Map;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;"))
    private static Map<RegistryKey<RecipePropertySet>, ServerRecipeManager.SoleIngredientGetter> putKilningGetter(Map<RegistryKey<RecipePropertySet>, ServerRecipeManager.SoleIngredientGetter> getters) {
        getters = new HashMap<>(getters);
        getters.put(KilnScreenHandler.PROPERTY_SET, cookingIngredientGetter(KaleidoscopeRecipeTypes.KILNING));
        return getters;
    }

    @Shadow
    @Nullable
    public abstract ServerRecipeManager.@Nullable ServerRecipe get(NetworkRecipeId id);

    @Inject(method = "prepare(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)Lnet/minecraft/recipe/PreparedRecipes;", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/JsonDataLoader;load(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/resource/ResourceFinder;Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Ljava/util/Map;)V", shift = At.Shift.AFTER))
    private void generateKaleidoscopeRecipes(ResourceManager manager, Profiler profiler, CallbackInfoReturnable<PreparedRecipes> cir, final @Local SortedMap<Identifier, Recipe<?>> sortedMap) {
        for (final Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);
            if (id.getPath().contains("cracked_")) {
                final String path = id.getPath().replaceFirst("cracked_", "");
                Registries.BLOCK.stream().filter(blockx -> Objects.equals(Registries.BLOCK.getId(blockx).getPath(), path)).findFirst().ifPresent(base -> {
                    CrackingRecipe recipe = new CrackingRecipe(base, block);
                    Identifier recipeId = createCrackingId(block);
                    sortedMap.put(recipeId, recipe);
                });
            }
            if (id.getPath().contains("mossy_")) {
                final String path = id.getPath().replaceFirst("mossy_", "");
                Registries.BLOCK.stream().filter(blockx -> Objects.equals(Registries.BLOCK.getId(blockx).getPath(), path)).findFirst().ifPresent(result -> {
                    MossScrapingRecipe recipe = new MossScrapingRecipe(block, result);
                    Identifier recipeId = createMossScrapingId(block);
                    sortedMap.put(recipeId, recipe);
                });
            }
        }
        KilningRecipe.SMELTING_TO_KILNING.clear();
        List<Recipe<?>> list2 = sortedMap.values().stream().filter(recipe -> recipe instanceof BlastingRecipe || recipe instanceof SmokingRecipe).toList();
        Map<Identifier, Recipe<?>> kilningRecipes = new HashMap<>();
        sortedMap.forEach((id, recipe) -> {
            if (!(recipe instanceof SmeltingRecipe smelting)) {
                return;
            }
            boolean hasSmokingBlasting = list2.stream().anyMatch(other -> ((AbstractCookingRecipe) other).ingredient().equals(smelting.ingredient()));
            if (hasSmokingBlasting) {
                return;
            }
            KilningRecipe kilning = KilningRecipe.createFromSmelting(smelting);
            Identifier recipeId = createKilningId(id);
            kilningRecipes.put(recipeId, kilning);
            KilningRecipe.SMELTING_TO_KILNING.put(RegistryKey.of(RegistryKeys.RECIPE, id), RegistryKey.of(RegistryKeys.RECIPE, recipeId));
        });
        sortedMap.putAll(kilningRecipes);
    }
}
