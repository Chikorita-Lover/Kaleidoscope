package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeBook;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeMatcher;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeResultCollection {
    private final List<BrewingRecipe> recipes;
    private final boolean singleOutput;
    private final Set<BrewingRecipe> craftableRecipes = Sets.newHashSet();
    private final Set<BrewingRecipe> fittingRecipes = Sets.newHashSet();
    private final Set<BrewingRecipe> unlockedRecipes = Sets.newHashSet();

    public BrewingRecipeResultCollection(List<BrewingRecipe> recipes) {
        this.recipes = ImmutableList.copyOf(recipes);
        this.singleOutput = recipes.size() <= 1 || shouldHaveSingleOutput(recipes);
    }

    private static boolean shouldHaveSingleOutput(List<BrewingRecipe> recipes) {
        int i = recipes.size();
        ItemStack itemStack = recipes.get(0).getOutput();
        for (int j = 1; j < i; ++j) {
            ItemStack itemStack2 = recipes.get(j).getOutput();
            if (ItemStack.canCombine(itemStack, itemStack2)) continue;
            return false;
        }
        return true;
    }

    public boolean isInitialized() {
        return !this.unlockedRecipes.isEmpty();
    }

    public void initialize(BrewingRecipeBook brewingRecipeBook) {
        for (BrewingRecipe recipe : this.recipes) {
            if (!brewingRecipeBook.contains(recipe)) continue;
            this.unlockedRecipes.add(recipe);
        }
    }

    public void computeCraftables(BrewingRecipeMatcher recipeFinder, BrewingRecipeBook brewingRecipeBook) {
        for (BrewingRecipe recipe : this.recipes) {
            boolean bl = brewingRecipeBook.contains(recipe);
            if (bl) {
                this.fittingRecipes.add(recipe);
            } else {
                this.fittingRecipes.remove(recipe);
            }
            if (bl && recipeFinder.match(recipe) > 0) {
                this.craftableRecipes.add(recipe);
                continue;
            }
            this.craftableRecipes.remove(recipe);
        }
    }

    public boolean isCraftable(BrewingRecipe recipe) {
        return this.craftableRecipes.contains(recipe);
    }

    public boolean hasCraftableRecipes() {
        return !this.craftableRecipes.isEmpty();
    }

    public boolean hasFittingRecipes() {
        return !this.fittingRecipes.isEmpty();
    }

    public List<BrewingRecipe> getAllRecipes() {
        return this.recipes;
    }

    public List<BrewingRecipe> getResults(boolean craftableOnly) {
        ArrayList<BrewingRecipe> list = Lists.newArrayList();
        Set<BrewingRecipe> set = craftableOnly ? this.craftableRecipes : this.fittingRecipes;
        for (BrewingRecipe recipe : this.recipes) {
            if (!set.contains(recipe)) continue;
            list.add(recipe);
        }
        return list;
    }

    public List<BrewingRecipe> getRecipes(boolean craftable) {
        ArrayList<BrewingRecipe> list = Lists.newArrayList();
        for (BrewingRecipe recipe : this.recipes) {
            if (!this.fittingRecipes.contains(recipe) || this.craftableRecipes.contains(recipe) != craftable) continue;
            list.add(recipe);
        }
        return list;
    }

    public boolean hasSingleOutput() {
        return this.singleOutput;
    }
}
