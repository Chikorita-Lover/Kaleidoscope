package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.gson.Gson;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Objects;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends JsonDataLoader {
    @Unique
    private static final ArrayList<RecipeEntry<Recipe<?>>> BLOCK_TRANSMUTING_RECIPE_ENTRIES = new ArrayList<>();

    public RecipeManagerMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Unique
    private static boolean matches(RecipeEntry<?> entry, RecipeEntry<?> otherEntry) {
        return entry.id().getNamespace().equals(otherEntry.id().getNamespace()) && entry.value().getIngredients().equals(otherEntry.value().getIngredients());
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

    @ModifyReceiver(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMultimap$Builder;build()Lcom/google/common/collect/ImmutableMultimap;"))
    private ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<Recipe<?>>> createKaleidoscopeRecipes(final ImmutableMultimap.Builder<RecipeType<?>, RecipeEntry<Recipe<?>>> builder) {
        BLOCK_TRANSMUTING_RECIPE_ENTRIES.clear();
        for (final Block block : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(block);
            if (id.getPath().contains("cracked_")) {
                final String path = id.getPath().replaceFirst("cracked_", "");
                Registries.BLOCK.stream().filter(blockx -> Objects.equals(Registries.BLOCK.getId(blockx).getPath(), path)).findFirst().ifPresent(block2 -> {
                    CrackingRecipe recipe = new CrackingRecipe(block2, block);
                    RecipeEntry<Recipe<?>> entry = new RecipeEntry<>(createCrackingId(block), recipe);
                    BLOCK_TRANSMUTING_RECIPE_ENTRIES.add(entry);
                    builder.put(KaleidoscopeRecipeTypes.CRACKING, entry);
                });
            }
            if (id.getPath().contains("mossy_")) {
                final String path = id.getPath().replaceFirst("mossy_", "");
                Registries.BLOCK.stream().filter(blockx -> Objects.equals(Registries.BLOCK.getId(blockx).getPath(), path)).findFirst().ifPresent(block2 -> {
                    MossScrapingRecipe recipe = new MossScrapingRecipe(block, block2);
                    RecipeEntry<Recipe<?>> entry = new RecipeEntry<>(createMossScrapingId(block), recipe);
                    BLOCK_TRANSMUTING_RECIPE_ENTRIES.add(entry);
                    builder.put(KaleidoscopeRecipeTypes.MOSS_SCRAPING, entry);
                });
            }
        }
        KilningRecipe.KILNING_RECIPE_ENTRIES.clear();
        ImmutableMultimap<RecipeType<?>, RecipeEntry<Recipe<?>>> recipesByType = builder.build();
        recipesByType.get(RecipeType.SMELTING).forEach(smeltingEntry -> {
            final SmeltingRecipe smeltingRecipe = (SmeltingRecipe) smeltingEntry.value();
            boolean hasSmokingBlasting = recipesByType.get(RecipeType.SMOKING).stream().anyMatch(smokingEntry -> matches(smeltingEntry, smokingEntry)) || recipesByType.get(RecipeType.BLASTING).stream().anyMatch(blastingEntry -> matches(smeltingEntry, blastingEntry));
            if (hasSmokingBlasting) {
                return;
            }
            final Ingredient ingredient = smeltingRecipe.getIngredients().get(0);
            ItemStack result = smeltingRecipe.getResult(null);
            KilningRecipe kilningRecipe = new KilningRecipe(smeltingRecipe.getGroup(), smeltingRecipe.getCategory(), ingredient, result, smeltingRecipe.getExperience(), smeltingRecipe.getCookingTime() / 2);
            RecipeEntry<Recipe<?>> kilningEntry = new RecipeEntry<>(createKilningId(smeltingEntry.id()), kilningRecipe);
            KilningRecipe.KILNING_RECIPE_ENTRIES.add(kilningEntry);
            builder.put(KaleidoscopeRecipeTypes.KILNING, kilningEntry);
        });
        return builder;
    }

    @ModifyReceiver(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"))
    private ImmutableMap.Builder<Identifier, RecipeEntry<Recipe<?>>> putKaleidoscopeRecipes(final ImmutableMap.Builder<Identifier, RecipeEntry<Recipe<?>>> builder2) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.forEach(entry -> builder2.put(entry.id(), entry));
        BLOCK_TRANSMUTING_RECIPE_ENTRIES.forEach(entry -> builder2.put(entry.id(), entry));
        return builder2;
    }
}
