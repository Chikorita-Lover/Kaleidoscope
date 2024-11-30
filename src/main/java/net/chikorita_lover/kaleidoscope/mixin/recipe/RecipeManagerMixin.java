package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.Gson;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends JsonDataLoader {
    @Unique
    private static final ArrayList<RecipeEntry<Recipe<?>>> BLOCK_TRANSMUTING_RECIPE_ENTRIES = new ArrayList<>();

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

    @ModifyExpressionValue(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMultimap$Builder;build()Lcom/google/common/collect/ImmutableMultimap;"))
    private ImmutableMultimap<RecipeType<?>, RecipeEntry<Recipe<?>>> createKaleidoscopeRecipes(final ImmutableMultimap<RecipeType<?>, RecipeEntry<Recipe<?>>> recipesByType, @Local final ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<Recipe<?>>> builder) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.clear();
        BLOCK_TRANSMUTING_RECIPE_ENTRIES.clear();
        for (final Block mossyBlock : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(mossyBlock);
            if (id.getPath().contains("mossy_")) {
                final String path = id.getPath().replaceFirst("mossy_", "");
                Registries.BLOCK.stream().filter(block -> Objects.equals(Registries.BLOCK.getId(block).getPath(), path)).findFirst().ifPresent(block -> {
                    MossScrapingRecipe recipe = new MossScrapingRecipe(mossyBlock, block);
                    RecipeEntry<Recipe<?>> entry = new RecipeEntry<>(createMossScrapingId(mossyBlock), recipe);
                    BLOCK_TRANSMUTING_RECIPE_ENTRIES.add(entry);
                    builder.put(KaleidoscopeRecipeTypes.MOSS_SCRAPING, entry);
                });
            }
        }
        recipesByType.get(RecipeType.SMELTING).forEach(smeltingEntry -> {
            final SmeltingRecipe smeltingRecipe = (SmeltingRecipe) smeltingEntry.value();
            boolean hasSmokingBlasting = recipesByType.get(RecipeType.SMOKING).stream().anyMatch(smokingEntry -> smeltingEntry.id().getNamespace().equals(smokingEntry.id().getNamespace()) && smokingEntry.value().getIngredients().equals(smeltingRecipe.getIngredients())) || recipesByType.get(RecipeType.BLASTING).stream().anyMatch(blastingEntry -> smeltingEntry.id().getNamespace().equals(blastingEntry.id().getNamespace()) && blastingEntry.value().getIngredients().equals(smeltingRecipe.getIngredients()));
            if (hasSmokingBlasting) {
                return;
            }
            final Ingredient ingredient = smeltingRecipe.getIngredients().get(0);
            ItemStack result = smeltingRecipe.getResult(null);
            KilningRecipe kilningRecipe = new KilningRecipe(smeltingRecipe.getGroup(), smeltingRecipe.getCategory(), ingredient, result, smeltingRecipe.getExperience(), smeltingRecipe.getCookingTime() / 2);
            RecipeEntry<Recipe<?>> kilningEntry = new RecipeEntry<>(createKilningId(smeltingEntry.id()), kilningRecipe);
            KilningRecipe.KILNING_RECIPE_ENTRIES.add(kilningEntry);
            builder.put(KaleidoscopeRecipeTypes.KILNING, kilningEntry);
            if (Registries.ITEM.getId(result.getItem()).getPath().contains("cracked")) {
                final Block resultBlock = Block.getBlockFromItem(result.getItem());
                Arrays.stream(ingredient.getMatchingStacks()).map(stack -> Block.getBlockFromItem(stack.getItem())).filter(block -> block != Blocks.AIR).forEach(block -> {
                    CrackingRecipe crackingRecipe = new CrackingRecipe(block, resultBlock);
                    RecipeEntry<Recipe<?>> crackingEntry = new RecipeEntry<>(createCrackingId(resultBlock), crackingRecipe);
                    BLOCK_TRANSMUTING_RECIPE_ENTRIES.add(crackingEntry);
                    builder.put(KaleidoscopeRecipeTypes.CRACKING, crackingEntry);
                });
            }
        });
        return builder.build();
    }

    @ModifyExpressionValue(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"))
    private ImmutableMap<Identifier, RecipeEntry<Recipe<?>>> putKaleidoscopeRecipes(ImmutableMap<Identifier, RecipeEntry<Recipe<?>>> recipesById, @Local final ImmutableMap.Builder<Identifier, RecipeEntry<Recipe<?>>> builder2) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.forEach(entry -> builder2.put(entry.id(), entry));
        BLOCK_TRANSMUTING_RECIPE_ENTRIES.forEach(entry -> builder2.put(entry.id(), entry));
        return builder2.build();
    }
}
