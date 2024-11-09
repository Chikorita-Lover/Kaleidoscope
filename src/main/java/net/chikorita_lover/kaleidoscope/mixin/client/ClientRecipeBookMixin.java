package net.chikorita_lover.kaleidoscope.mixin.client;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(ClientRecipeBook.class)
public abstract class ClientRecipeBookMixin {
    @Unique
    private static final RecipeBookGroup KILN_BLOCKS = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_BLOCKS");
    @Unique
    private static final RecipeBookGroup KILN_MISC = ClassTinkerers.getEnum(RecipeBookGroup.class, "KALEIDOSCOPE_KILN_MISC");

    @Shadow
    private static RecipeBookGroup getGroupForRecipe(RecipeEntry<?> recipe) {
        return null;
    }

    @Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
    private static void getGroupForRecipe(RecipeEntry<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        Recipe<?> recipe2 = recipe.value();
        RecipeType<?> recipeType = recipe2.getType();
        if (recipe2 instanceof AbstractCookingRecipe abstractCookingRecipe) {
            CookingRecipeCategory cookingRecipeCategory = abstractCookingRecipe.getCategory();
            if (recipeType == KaleidoscopeRecipeTypes.KILNING) {
                cir.setReturnValue(cookingRecipeCategory == CookingRecipeCategory.BLOCKS ? KILN_BLOCKS : KILN_MISC);
            }
        }
    }

    @Unique
    private static int compareGroups(@Nullable RecipeBookGroup group, @Nullable RecipeBookGroup group2) {
        if (group == group2) {
            return 0;
        }
        if (group == null) {
            return 1;
        }
        if (group2 == null) {
            return -1;
        }
        for (Map.Entry<RecipeBookGroup, List<RecipeBookGroup>> entry : RecipeBookGroup.SEARCH_MAP.entrySet()) {
            for (RecipeBookGroup category : entry.getValue()) {
                if (category == group) {
                    return -1;
                }
                if (category == group2) {
                    return 1;
                }
            }
        }
        return group.compareTo(group2);
    }

    @Unique
    private static int compareEntries(RegistryWrapper.WrapperLookup registryManager, List<ItemStack> sortedItems, RecipeEntry<?> entry, RecipeEntry<?> entry2) {
        RecipeBookGroup group = getGroupForRecipe(entry);
        RecipeBookGroup group2 = getGroupForRecipe(entry2);
        if (group != group2) {
            return compareGroups(group, group2);
        }
        ItemStack stack = entry.value().getResult(registryManager);
        ItemStack stack2 = entry2.value().getResult(registryManager);
        if (stack2.isOf(stack.getItem())) {
            return entry.id().compareTo(entry2.id());
        }
        int i = Integer.MAX_VALUE;
        int j = Integer.MAX_VALUE;
        for (int k = 0; k < sortedItems.size(); ++k) {
            ItemStack displayStack = sortedItems.get(k);
            if (ItemStack.areItemsAndComponentsEqual(stack, displayStack)) {
                i = k;
            }
            if (ItemStack.areItemsAndComponentsEqual(stack2, displayStack)) {
                j = k;
            }
            if (i < Integer.MAX_VALUE && j < Integer.MAX_VALUE) {
                break;
            }
        }
        return i - j;
    }

    @ModifyExpressionValue(method = "reload", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;copyOf(Ljava/util/Map;)Lcom/google/common/collect/ImmutableMap;"))
    private ImmutableMap<RecipeBookGroup, List<RecipeResultCollection>> sortResults(ImmutableMap<RecipeBookGroup, List<RecipeResultCollection>> resultsByGroup, @Local(argsOnly = true) DynamicRegistryManager registryManager) {
        FeatureSet features = MinecraftClient.getInstance().world != null ? MinecraftClient.getInstance().world.getEnabledFeatures() : FeatureSet.empty();
        ItemGroups.updateDisplayContext(features, false, registryManager);
        ImmutableMap.Builder<RecipeBookGroup, List<RecipeResultCollection>> sortedResults = ImmutableMap.builder();
        List<ItemStack> displayStacks = List.copyOf(ItemGroups.getSearchGroup().getDisplayStacks());
        resultsByGroup.forEach((group, collections) -> {
            ArrayList<RecipeResultCollection> sortedCollections = new ArrayList<>(collections);
            collections.forEach(collection -> {
                ArrayList<RecipeEntry<?>> recipes = new ArrayList<>(collection.getAllRecipes());
                recipes.sort((entry, entry2) -> compareEntries(registryManager, displayStacks, entry, entry2));
                collection.recipes = recipes;
            });
            sortedCollections.sort((collection, collection2) -> compareEntries(registryManager, displayStacks, collection.getAllRecipes().get(0), collection2.getAllRecipes().get(0)));
            sortedResults.put(group, sortedCollections);
        });
        return sortedResults.build();
    }
}