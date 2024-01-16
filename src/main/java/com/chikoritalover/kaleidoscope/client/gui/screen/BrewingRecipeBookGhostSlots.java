package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeBookGhostSlots {
    private final List<GhostInputSlot> slots = Lists.newArrayList();
    float time;
    @Nullable
    private BrewingRecipe recipe;

    public void reset() {
        this.recipe = null;
        this.slots.clear();
        this.time = 0.0F;
    }

    public void addSlot(Ingredient ingredient, int x, int y) {
        this.slots.add(new GhostInputSlot(ingredient, x, y));
    }

    public GhostInputSlot getSlot(int index) {
        return this.slots.get(index);
    }

    public int getSlotCount() {
        return this.slots.size();
    }

    @Nullable
    public BrewingRecipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(BrewingRecipe recipe) {
        this.recipe = recipe;
    }

    public void draw(DrawContext context, MinecraftClient client, int x, int y, float tickDelta) {
        if (!Screen.hasControlDown()) {
            this.time += tickDelta;
        }
        for (int i = 0; i < this.slots.size(); ++i) {
            GhostInputSlot ghostInputSlot = this.slots.get(i);
            int j = ghostInputSlot.getX() + x;
            int k = ghostInputSlot.getY() + y;
            context.fill(j, k, j + 16, k + 16, 0x30FF0000);
            ItemStack itemStack = ghostInputSlot.getCurrentItemStack();
            context.drawItemWithoutEntity(itemStack, j, k);
            context.fill(RenderLayer.getGuiGhostRecipeOverlay(), j, k, j + 16, k + 16, 0x30FFFFFF);
            if (i != 0) continue;
            context.drawItemInSlot(client.textRenderer, itemStack, j, k);
        }
    }

    @Environment(value = EnvType.CLIENT)
    public class GhostInputSlot {
        private final Ingredient ingredient;
        private final int x;
        private final int y;

        public GhostInputSlot(Ingredient ingredient, int x, int y) {
            this.ingredient = ingredient;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public ItemStack getCurrentItemStack() {
            ItemStack[] itemStacks = this.ingredient.getMatchingStacks();
            if (itemStacks.length == 0) {
                return ItemStack.EMPTY;
            }
            return itemStacks[MathHelper.floor(BrewingRecipeBookGhostSlots.this.time / 30.0F) % itemStacks.length];
        }
    }
}
