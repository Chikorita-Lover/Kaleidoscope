package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeBook;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeBookResults {
    private final List<AnimatedBrewingResultButton> resultButtons = Lists.newArrayListWithCapacity(20);
    private final BrewingRecipeAlternativesWidget alternatesWidget = new BrewingRecipeAlternativesWidget();
    private final List<BrewingRecipeBookWidget> recipeDisplayListeners = Lists.newArrayList();
    @Nullable
    private AnimatedBrewingResultButton hoveredResultButton;
    private MinecraftClient client;
    private List<BrewingRecipeResultCollection> resultCollections = ImmutableList.of();
    private ToggleButtonWidget nextPageButton;
    private ToggleButtonWidget prevPageButton;
    private int pageCount;
    private int currentPage;
    private ClientBrewingRecipeBook brewingRecipeBook;
    @Nullable
    private BrewingRecipe lastClickedRecipe;
    @Nullable
    private BrewingRecipeResultCollection resultCollection;

    public BrewingRecipeBookResults() {
        for (int i = 0; i < 20; ++i) {
            this.resultButtons.add(new AnimatedBrewingResultButton());
        }
    }

    public void initialize(MinecraftClient client, int parentLeft, int parentTop) {
        this.client = client;
        this.brewingRecipeBook = ((KaleidoscopePlayerEntity) client.player).kaleidoscope$getClientBrewingRecipeBook();
        for (int i = 0; i < this.resultButtons.size(); ++i) {
            this.resultButtons.get(i).setPosition(parentLeft + 11 + 25 * (i % 5), parentTop + 31 + 25 * (i / 5));
        }
        this.nextPageButton = new ToggleButtonWidget(parentLeft + 93, parentTop + 137, 12, 17, false);
        this.nextPageButton.setTextureUV(1, 208, 13, 18, BrewingRecipeBookWidget.TEXTURE);
        this.prevPageButton = new ToggleButtonWidget(parentLeft + 38, parentTop + 137, 12, 17, true);
        this.prevPageButton.setTextureUV(1, 208, 13, 18, BrewingRecipeBookWidget.TEXTURE);
    }

    public void setGui(BrewingRecipeBookWidget widget) {
        this.recipeDisplayListeners.remove(widget);
        this.recipeDisplayListeners.add(widget);
    }

    public void setResults(List<BrewingRecipeResultCollection> resultCollections, boolean resetCurrentPage) {
        this.resultCollections = resultCollections;
        this.pageCount = (int) Math.ceil(resultCollections.size() / 20.0);
        if (this.pageCount <= this.currentPage || resetCurrentPage) {
            this.currentPage = 0;
        }
        this.refreshResultButtons();
    }

    private void refreshResultButtons() {
        int i = 20 * this.currentPage;
        for (int j = 0; j < this.resultButtons.size(); ++j) {
            AnimatedBrewingResultButton animatedResultButton = this.resultButtons.get(j);
            if (i + j < this.resultCollections.size()) {
                BrewingRecipeResultCollection brewingRecipeResultCollection = this.resultCollections.get(i + j);
                animatedResultButton.showResultCollection(brewingRecipeResultCollection, this);
                animatedResultButton.visible = true;
                continue;
            }
            animatedResultButton.visible = false;
        }
        this.hideShowPageButtons();
    }

    private void hideShowPageButtons() {
        this.nextPageButton.visible = this.pageCount > 1 && this.currentPage < this.pageCount - 1;
        this.prevPageButton.visible = this.pageCount > 1 && this.currentPage > 0;
    }

    public void draw(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        if (this.pageCount > 1) {
            String string = this.currentPage + 1 + "/" + this.pageCount;
            int i = this.client.textRenderer.getWidth(string);
            context.drawText(this.client.textRenderer, string, x - i / 2 + 73, y + 141, -1, false);
        }
        this.hoveredResultButton = null;
        for (AnimatedBrewingResultButton animatedResultButton : this.resultButtons) {
            animatedResultButton.render(context, mouseX, mouseY, delta);
            if (!animatedResultButton.visible || !animatedResultButton.isSelected()) continue;
            this.hoveredResultButton = animatedResultButton;
        }
        this.prevPageButton.render(context, mouseX, mouseY, delta);
        this.nextPageButton.render(context, mouseX, mouseY, delta);
        this.alternatesWidget.render(context, mouseX, mouseY, delta);
    }

    public void drawTooltip(DrawContext context, int x, int y) {
        if (this.client.currentScreen != null && this.hoveredResultButton != null && !this.alternatesWidget.isVisible()) {
            context.drawTooltip(this.client.textRenderer, this.hoveredResultButton.getTooltip(false), x, y);
        }
    }

    @Nullable
    public BrewingRecipe getLastClickedRecipe() {
        return this.lastClickedRecipe;
    }

    @Nullable
    public BrewingRecipeResultCollection getLastClickedResults() {
        return this.resultCollection;
    }

    public void hideAlternates() {
        this.alternatesWidget.setVisible(false);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button, int areaLeft, int areaTop, int areaWidth, int areaHeight) {
        this.lastClickedRecipe = null;
        this.resultCollection = null;
        if (this.alternatesWidget.isVisible()) {
            if (this.alternatesWidget.mouseClicked(mouseX, mouseY, button)) {
                this.lastClickedRecipe = this.alternatesWidget.getLastClickedRecipe();
                this.resultCollection = this.alternatesWidget.getResults();
            } else {
                this.alternatesWidget.setVisible(false);
            }
            return true;
        }
        if (this.nextPageButton.mouseClicked(mouseX, mouseY, button)) {
            ++this.currentPage;
            this.refreshResultButtons();
            return true;
        }
        if (this.prevPageButton.mouseClicked(mouseX, mouseY, button)) {
            --this.currentPage;
            this.refreshResultButtons();
            return true;
        }
        for (AnimatedBrewingResultButton animatedResultButton : this.resultButtons) {
            if (!animatedResultButton.mouseClicked(mouseX, mouseY, button)) continue;
            if (button == 0) {
                this.lastClickedRecipe = animatedResultButton.currentRecipe();
                this.resultCollection = animatedResultButton.getResultCollection();
            } else if (button == 1 && !this.alternatesWidget.isVisible() && !animatedResultButton.hasResults()) {
                this.alternatesWidget.showAlternativesForResult(this.client, animatedResultButton.getResultCollection(), animatedResultButton.getX(), animatedResultButton.getY(), areaLeft + areaWidth / 2, areaTop + 13 + areaHeight / 2, animatedResultButton.getWidth());
            }
            return true;
        }
        return false;
    }

    public void onRecipesDisplayed(List<BrewingRecipe> recipes) {
        for (BrewingRecipeBookWidget recipeDisplayListener : this.recipeDisplayListeners) {
            recipeDisplayListener.onRecipesDisplayed(recipes);
        }
    }

    public MinecraftClient getClient() {
        return this.client;
    }

    public BrewingRecipeBook getRecipeBook() {
        return this.brewingRecipeBook;
    }

    protected void forEachButton(Consumer<ClickableWidget> consumer) {
        consumer.accept(this.nextPageButton);
        consumer.accept(this.prevPageButton);
        this.resultButtons.forEach(consumer);
    }
}
