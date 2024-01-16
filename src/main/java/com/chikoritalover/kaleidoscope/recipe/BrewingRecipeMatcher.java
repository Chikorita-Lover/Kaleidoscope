package com.chikoritalover.kaleidoscope.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.Ingredient;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class BrewingRecipeMatcher {
    public final HashMap<ItemStack, Integer> itemStacks = new HashMap<>();

    private boolean contains(ItemStack stack) {
        for (ItemStack itemStack : this.itemStacks.keySet()) {
            if (ItemStack.canCombine(stack, itemStack)) return true;
        }
        return false;
    }

    private ItemStack consume(ItemStack stack) {
        for (ItemStack itemStack : this.itemStacks.keySet()) {
            if (!ItemStack.canCombine(stack, itemStack)) continue;
            int i = this.itemStacks.get(itemStack);
            if (i >= stack.getCount()) {
                this.itemStacks.put(itemStack, i - stack.getCount());
                return itemStack.copyWithCount(stack.getCount());
            }
        }
        return ItemStack.EMPTY;
    }

    public void addInput(ItemStack stack) {
        AtomicBoolean aBoolean = new AtomicBoolean(false);
        this.itemStacks.forEach((stack2, count) -> {
            if (ItemStack.canCombine(stack, stack2)) {
                this.itemStacks.put(stack2, count + stack.getCount());
                aBoolean.set(true);
            }
        });
        if (!aBoolean.get()) {
            this.itemStacks.put(stack.copyWithCount(1), stack.getCount());
        }
    }

    public int match(BrewingRecipe recipe) {
        return new Matcher(recipe).match();
    }

    public void clear() {
        this.itemStacks.clear();
    }

    class Matcher {
        private final BrewingRecipe recipe;

        public Matcher(BrewingRecipe recipe) {
            this.recipe = recipe;
        }

        public int match() {
            Ingredient ingredient = this.recipe.getReagent();
            ItemStack itemStack = ItemStack.EMPTY;
            for (ItemStack itemStack2 : BrewingRecipeMatcher.this.itemStacks.keySet()) {
                if (!ingredient.test(itemStack2)) continue;
                itemStack = BrewingRecipeMatcher.this.consume(itemStack2);
                break;
            }
            if (itemStack.isEmpty()) {
                return 0;
            }

            int i = 0;
            for (ItemStack itemStack2 : BrewingRecipeMatcher.this.itemStacks.keySet()) {
                if (!this.recipe.getInput().test(itemStack2) || PotionUtil.getPotion(itemStack2) != recipe.getInputPotion()) continue;
                int j = BrewingRecipeMatcher.this.itemStacks.get(itemStack2);
                i += j;
            }
            BrewingRecipeMatcher.this.addInput(itemStack);
            return i;
        }
    }
}
