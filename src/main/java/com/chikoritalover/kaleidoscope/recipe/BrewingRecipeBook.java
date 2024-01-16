package com.chikoritalover.kaleidoscope.recipe;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class BrewingRecipeBook {
    public final Set<Identifier> recipeIds = Sets.newHashSet();
    public final Set<Identifier> toBeDisplayed = Sets.newHashSet();
    private boolean isFilteringBrewable = false;
    private boolean isGuiOpen = false;

    public void copyFrom(BrewingRecipeBook book) {
        this.recipeIds.clear();
        this.toBeDisplayed.clear();
        this.recipeIds.addAll(book.recipeIds);
        this.toBeDisplayed.addAll(book.toBeDisplayed);
        this.setGuiOpen(book.isGuiOpen());
        this.setFilteringBrewable(book.isFilteringBrewable());
    }

    public void add(BrewingRecipe recipe) {
        this.add(recipe.getId());
    }

    public void add(Identifier id) {
        this.recipeIds.add(id);
    }

    public boolean contains(@Nullable BrewingRecipe recipe) {
        if (recipe == null) {
            return false;
        }
        return this.recipeIds.contains(recipe.getId());
    }

    public void remove(BrewingRecipe recipe) {
        this.remove(recipe.getId());
    }

    public void remove(Identifier id) {
        this.recipeIds.remove(id);
        this.toBeDisplayed.remove(id);
    }

    public boolean shouldDisplay(BrewingRecipe recipe) {
        return this.toBeDisplayed.contains(recipe.getId());
    }

    public void onRecipeDisplayed(BrewingRecipe recipe) {
        this.toBeDisplayed.remove(recipe.getId());
    }

    public void display(BrewingRecipe recipe) {
        this.display(recipe.getId());
    }

    public void display(Identifier id) {
        this.toBeDisplayed.add(id);
    }

    public boolean isGuiOpen() {
        return this.isGuiOpen;
    }

    public void setGuiOpen(boolean open) {
        this.isGuiOpen = open;
    }

    public boolean isFilteringBrewable() {
        return this.isFilteringBrewable;
    }

    public void setFilteringBrewable(boolean filteringBrewable) {
        this.isFilteringBrewable = filteringBrewable;
    }

    public void setCategoryOptions(boolean guiOpen, boolean filteringCraftable) {
        this.setGuiOpen(guiOpen);
        this.setFilteringBrewable(filteringCraftable);
    }
}
