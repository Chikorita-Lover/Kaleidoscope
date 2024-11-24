package net.chikorita_lover.kaleidoscope.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin extends JsonDataLoader {
    public ServerAdvancementLoaderMixin(Gson gson, String dataType) {
        super(gson, dataType);
    }

    @Unique
    private static CriterionConditions conditionsFromIngredient(Ingredient ingredient) {
        CriterionConditions conditions;
        var entry = ingredient.entries[0];
        if (entry instanceof Ingredient.TagEntry tagEntry) {
            conditions = RecipeProvider.conditionsFromTag(tagEntry.tag);
        } else if (entry instanceof Ingredient.StackEntry stackEntry) {
            conditions = RecipeProvider.conditionsFromItem(stackEntry.stack.getItem());
        } else {
            conditions = new ImpossibleCriterion.Conditions();
        }
        return conditions;
    }

    @Unique
    private static Identifier createAdvancementId(Recipe<?> recipe) {
        RecipeCategory category = switch (((KilningRecipe) recipe).getCategory()) {
            case FOOD -> RecipeCategory.FOOD;
            case BLOCKS -> RecipeCategory.BUILDING_BLOCKS;
            case MISC -> RecipeCategory.MISC;
        };
        return Kaleidoscope.of("recipes/" + category.getName() + "/" + recipe.getId().getPath());
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V", shift = At.Shift.AFTER))
    private void createKilningAdvancements(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci, @Local(ordinal = 1) Map<Identifier, Advancement.Builder> map2) {
        KilningRecipe.KILNING_RECIPES.forEach(recipe -> {
            try {
                Advancement.Builder builder = Advancement.Builder.create().parent(new Identifier("recipes/root")).rewards(AdvancementRewards.Builder.recipe(recipe.getId()).build()).criterion("has_ingredient", conditionsFromIngredient(recipe.getIngredients().get(0))).criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipe.getId())).requirements(new String[][]{{"has_ingredient", "has_the_recipe"}});
                Identifier id = createAdvancementId(recipe);
                map2.put(id, builder);
            } catch (Exception exception) {
                Kaleidoscope.LOGGER.error("Parsing error loading dynamic kilning advancement for recipe {}: {}", recipe.getId(), exception.getMessage());
            }
        });
    }
}
