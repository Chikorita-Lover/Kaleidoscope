package net.chikorita_lover.kaleidoscope.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.CrackingRecipe;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.recipe.MossScrapingRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin extends JsonDataLoader {
    @Unique
    private static final ArrayList<Recipe<?>> BLOCK_TRANSMUTING_RECIPES = new ArrayList<>();

    public RecipeManagerMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Unique
    private static boolean equals(DefaultedList<Ingredient> a, DefaultedList<Ingredient> b) {
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); ++i) {
                if (!a.get(i).toJson().equals(b.get(i).toJson())) {
                    return false;
                }
            }
            return true;
        }
        return false;
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

    @ModifyReceiver(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;entrySet()Ljava/util/Set;", ordinal = 1))
    private Map<RecipeType<?>, ImmutableMap.Builder<Identifier, Recipe<?>>> createKaleidoscopeRecipes(Map<RecipeType<?>, ImmutableMap.Builder<Identifier, Recipe<?>>> map2) {
        KilningRecipe.KILNING_RECIPES.clear();
        BLOCK_TRANSMUTING_RECIPES.clear();
        final ImmutableMap.Builder<Identifier, Recipe<?>> scrapingRecipes = ImmutableMap.builder();
        for (final Block mossyBlock : Registries.BLOCK) {
            Identifier id = Registries.BLOCK.getId(mossyBlock);
            if (id.getPath().contains("mossy_")) {
                final String path = id.getPath().replaceFirst("mossy_", "");
                Registries.BLOCK.stream().filter(block -> Objects.equals(Registries.BLOCK.getId(block).getPath(), path)).findFirst().ifPresent(block -> {
                    MossScrapingRecipe recipe = new MossScrapingRecipe(createMossScrapingId(mossyBlock), mossyBlock, block);
                    BLOCK_TRANSMUTING_RECIPES.add(recipe);
                    scrapingRecipes.put(recipe.getId(), recipe);
                });
            }
        }
        map2.put(KaleidoscopeRecipeTypes.MOSS_SCRAPING, scrapingRecipes);
        final ImmutableMap.Builder<Identifier, Recipe<?>> kilningRecipes = ImmutableMap.builder();
        final ImmutableMap.Builder<Identifier, Recipe<?>> crackingRecipes = ImmutableMap.builder();
        map2.get(RecipeType.SMELTING).build().forEach((identifier, recipe) -> {
            boolean hasSmokingBlasting = map2.get(RecipeType.SMOKING).build().entrySet().stream().anyMatch(smokingEntry -> identifier.getNamespace().equals(smokingEntry.getKey().getNamespace()) && equals(recipe.getIngredients(), smokingEntry.getValue().getIngredients())) || map2.get(RecipeType.BLASTING).build().entrySet().stream().anyMatch(blastingEntry -> identifier.getNamespace().equals(blastingEntry.getKey().getNamespace()) && equals(recipe.getIngredients(), blastingEntry.getValue().getIngredients()));
            if (hasSmokingBlasting) {
                return;
            }
            SmeltingRecipe smeltingRecipe = (SmeltingRecipe) recipe;
            final Ingredient ingredient = recipe.getIngredients().get(0);
            ItemStack result = recipe.getOutput(null);
            KilningRecipe kilningRecipe = new KilningRecipe(createKilningId(identifier), recipe.getGroup(), smeltingRecipe.getCategory(), ingredient, result, smeltingRecipe.getExperience(), smeltingRecipe.getCookTime() / 2);
            kilningRecipes.put(kilningRecipe.getId(), kilningRecipe);
            KilningRecipe.KILNING_RECIPES.add(kilningRecipe);
            if (Registries.ITEM.getId(result.getItem()).getPath().contains("cracked")) {
                final Block resultBlock = Block.getBlockFromItem(result.getItem());
                Arrays.stream(ingredient.getMatchingItemIds().toArray(Integer[]::new)).map(id -> Block.getBlockFromItem(Item.byRawId(id))).filter(block -> block != Blocks.AIR).forEach(block -> {
                    CrackingRecipe crackingRecipe = new CrackingRecipe(createCrackingId(resultBlock), block, resultBlock);
                    BLOCK_TRANSMUTING_RECIPES.add(crackingRecipe);
                    crackingRecipes.put(crackingRecipe.getId(), crackingRecipe);
                });
            }
        });
        map2.put(KaleidoscopeRecipeTypes.KILNING, kilningRecipes);
        map2.put(KaleidoscopeRecipeTypes.CRACKING, crackingRecipes);
        return map2;
    }

    @ModifyReceiver(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"))
    private ImmutableMap.Builder<Identifier, Recipe<?>> putKaleidoscopeRecipes(ImmutableMap.Builder<Identifier, Recipe<?>> builder) {
        KilningRecipe.KILNING_RECIPES.forEach(recipe -> builder.put(recipe.getId(), recipe));
        BLOCK_TRANSMUTING_RECIPES.forEach(recipe -> builder.put(recipe.getId(), recipe));
        return builder;
    }
}
