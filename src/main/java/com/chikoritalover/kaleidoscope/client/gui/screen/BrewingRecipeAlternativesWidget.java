package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeAlternativesWidget implements Drawable, Element {
    public static final int field_42162 = 25;
    static final Identifier TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/gui/brewing_recipe_book.png");
    private static final int field_32406 = 4;
    private static final int field_32407 = 5;
    private static final float field_33739 = 0.375F;
    private final List<AlternativeButtonWidget> alternativeButtons = Lists.newArrayList();
    float time;
    private boolean visible;
    private int buttonX;
    private int buttonY;
    private MinecraftClient client;
    private BrewingRecipeResultCollection resultCollection;
    @Nullable
    private BrewingRecipe lastClickedRecipe;

    public void showAlternativesForResult(MinecraftClient client, BrewingRecipeResultCollection results, int buttonX, int buttonY, int areaCenterX, int areaCenterY, float delta) {
        float o;
        float n;
        float m;
        float h;
        float g;
        this.client = client;
        this.resultCollection = results;
        boolean bl = ((KaleidoscopePlayerEntity) client.player).kaleidoscope$getClientBrewingRecipeBook().isFilteringBrewable();
        List<BrewingRecipe> list = results.getRecipes(true);
        List<BrewingRecipe> list2 = bl ? Collections.emptyList() : results.getRecipes(false);
        int i = list.size();
        int j = i + list2.size();
        int k = j <= 16 ? 4 : 5;
        int l = (int) Math.ceil((double) j / k);
        this.buttonX = buttonX;
        this.buttonY = buttonY;
        float f = this.buttonX + Math.min(j, k) * 25;
        if (f > (g = (float) (areaCenterX + 50))) {
            this.buttonX = (int) ((float) this.buttonX - delta * (float) ((int) ((f - g) / delta)));
        }
        if ((h = (float) (this.buttonY + l * 25)) > (m = (float) (areaCenterY + 50))) {
            this.buttonY = (int) ((float) this.buttonY - delta * (float) MathHelper.ceil((h - m) / delta));
        }
        if ((n = (float) this.buttonY) < (o = (float) (areaCenterY - 100))) {
            this.buttonY = (int) ((float) this.buttonY - delta * (float) MathHelper.ceil((n - o) / delta));
        }
        this.visible = true;
        this.alternativeButtons.clear();
        for (int p = 0; p < j; ++p) {
            boolean bl2 = p < i;
            BrewingRecipe recipe = bl2 ? list.get(p) : list2.get(p - i);
            int q = this.buttonX + 4 + 25 * (p % k);
            int r = this.buttonY + 5 + 25 * (p / k);
            this.alternativeButtons.add(new AlternativeButtonWidget(q, r, recipe, bl2));
        }
        this.lastClickedRecipe = null;
    }

    public BrewingRecipeResultCollection getResults() {
        return this.resultCollection;
    }

    @Nullable
    public BrewingRecipe getLastClickedRecipe() {
        return this.lastClickedRecipe;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return false;
        }
        for (AlternativeButtonWidget alternativeButtonWidget : this.alternativeButtons) {
            if (!alternativeButtonWidget.mouseClicked(mouseX, mouseY, button)) continue;
            this.lastClickedRecipe = alternativeButtonWidget.recipe;
            return true;
        }
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (!this.visible) {
            return;
        }
        this.time += delta;
        RenderSystem.enableBlend();
        context.getMatrices().push();
        context.getMatrices().translate(0.0f, 0.0f, 1000.0f);
        int i = this.alternativeButtons.size() <= 16 ? 4 : 5;
        int j = Math.min(this.alternativeButtons.size(), i);
        int k = MathHelper.ceil((double) this.alternativeButtons.size() / i);
        context.drawNineSlicedTexture(TEXTURE, this.buttonX, this.buttonY, j * 25 + 8, k * 25 + 8, 4, 32, 32, 82, 208);
        RenderSystem.disableBlend();
        for (AlternativeButtonWidget alternativeButtonWidget : this.alternativeButtons) {
            alternativeButtonWidget.render(context, mouseX, mouseY, delta);
        }
        context.getMatrices().pop();
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public void setFocused(boolean focused) {
    }

    @Environment(value = EnvType.CLIENT)
    class AlternativeButtonWidget extends ClickableWidget {
        protected final List<AlternativeButtonWidget.InputSlot> slots;
        final BrewingRecipe recipe;
        private final boolean craftable;

        public AlternativeButtonWidget(int x, int y, BrewingRecipe recipe, boolean craftable) {
            super(x, y, 200, 20, ScreenTexts.EMPTY);
            this.slots = Lists.newArrayList();
            this.width = 24;
            this.height = 24;
            this.recipe = recipe;
            this.craftable = craftable;
            this.alignRecipe(recipe);
        }

        protected void alignRecipe(BrewingRecipe recipe) {
            this.slots.add(new InputSlot(10, 4, recipe.getReagent().getMatchingStacks()));
            ItemStack[] itemStacks = recipe.getInput().getMatchingStacks();
            for (int i = 0; i < 3; ++i) {
                this.slots.add(new InputSlot(3 + i * 7, i == 1 ? 16 : 14, itemStacks));
            }
        }

        @Override
        public void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }

        @Override
        public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
            int i = !this.craftable ? 178 : 152;
            int j = this.isSelected() ? 104 : 78;
            context.drawTexture(TEXTURE, this.getX(), this.getY(), i, j, this.width, this.height);
            context.getMatrices().push();
            context.getMatrices().translate(this.getX() + 2.0, this.getY() + 2.0, 150.0);
            for (InputSlot inputSlot : this.slots) {
                context.getMatrices().push();
                context.getMatrices().translate(inputSlot.x, inputSlot.y, 0.0);
                context.getMatrices().scale(0.375F, 0.375F, 1.0F);
                context.getMatrices().translate(-8.0, -8.0, 0.0);
                if (inputSlot.stacks.length > 0) {
                    context.drawItem(inputSlot.stacks[MathHelper.floor(BrewingRecipeAlternativesWidget.this.time / 30.0F) % inputSlot.stacks.length], 0, 0);
                }
                context.getMatrices().pop();
            }
            context.getMatrices().pop();
        }

        @Environment(value = EnvType.CLIENT)
        protected class InputSlot {
            public final ItemStack[] stacks;
            public final int x;
            public final int y;

            public InputSlot(int x, int y, ItemStack[] stacks) {
                this.x = x;
                this.y = y;
                this.stacks = stacks;
            }
        }
    }
}
