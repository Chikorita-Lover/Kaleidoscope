package net.chikorita_lover.kaleidoscope.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;
import java.util.Optional;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin extends JsonDataLoader {
    public ServerAdvancementLoaderMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Unique
    private static AdvancementCriterion<?> conditionsFromIngredient(Ingredient ingredient) {
        AdvancementCriterion<?> criterion;
        var entry = ingredient.entries[0];
        if (entry instanceof Ingredient.TagEntry tagEntry) {
            criterion = RecipeProvider.conditionsFromTag(tagEntry.tag());
        } else if (entry instanceof Ingredient.StackEntry stackEntry) {
            criterion = RecipeProvider.conditionsFromItem(stackEntry.stack().getItem());
        } else {
            criterion = Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions());
        }
        return criterion;
    }

    @Unique
    private static Identifier createAdvancementId(RecipeEntry<Recipe<?>> entry) {
        RecipeCategory category = switch (((KilningRecipe) entry.value()).getCategory()) {
            case FOOD -> RecipeCategory.FOOD;
            case BLOCKS -> RecipeCategory.BUILDING_BLOCKS;
            case MISC -> RecipeCategory.MISC;
        };
        return Kaleidoscope.of("recipes/" + category.getName() + "/" + entry.id().getPath());
    }

    @Shadow
    protected abstract void validate(Identifier id, Advancement advancement);

    @ModifyExpressionValue(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;builder()Lcom/google/common/collect/ImmutableMap$Builder;"))
    private ImmutableMap.Builder<Identifier, AdvancementEntry> foo(ImmutableMap.Builder<Identifier, AdvancementEntry> builder) {
        KilningRecipe.KILNING_RECIPE_ENTRIES.forEach(entry -> {
            try {
                Map<String, AdvancementCriterion<?>> criteria = Map.of("has_ingredient", conditionsFromIngredient(entry.value().getIngredients().get(0)), "has_the_recipe", RecipeUnlockedCriterion.create(entry.id()));
                Advancement advancement = new Advancement(Optional.of(Identifier.ofVanilla("recipes/root")), Optional.empty(), AdvancementRewards.Builder.recipe(entry.id()).build(), criteria, AdvancementRequirements.anyOf(criteria.keySet()), false);
                Identifier id = createAdvancementId(entry);
                this.validate(id, advancement);
                builder.put(id, new AdvancementEntry(id, advancement));
            } catch (Exception exception) {
                Kaleidoscope.LOGGER.error("Parsing error loading dynamic kilning advancement for recipe {}: {}", entry.id(), exception.getMessage());
            }
        });
        return builder;
    }
}
