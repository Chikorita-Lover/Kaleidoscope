package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeBook;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class AnimatedBrewingResultButton extends ClickableWidget {
    private static final Identifier TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/gui/brewing_recipe_book.png");
    private static final float field_32414 = 15.0F;
    private static final Text MORE_RECIPES_TEXT = Text.translatable("gui.recipebook.moreRecipes");
    private BrewingRecipeBook brewingRecipeBook;
    private BrewingRecipeResultCollection resultCollection;
    private float time;
    private float bounce;
    private int currentResultIndex;

    public AnimatedBrewingResultButton() {
        super(0, 0, 25, 25, ScreenTexts.EMPTY);
    }

    public void showResultCollection(BrewingRecipeResultCollection resultCollection, BrewingRecipeBookResults results) {
        this.resultCollection = resultCollection;
        this.brewingRecipeBook = results.getRecipeBook();
        List<BrewingRecipe> list = resultCollection.getResults(this.brewingRecipeBook.isFilteringBrewable());
        for (BrewingRecipe recipe : list) {
            if (!this.brewingRecipeBook.shouldDisplay(recipe)) continue;
            results.onRecipesDisplayed(list);
            this.bounce = field_32414;
            break;
        }
    }

    public BrewingRecipeResultCollection getResultCollection() {
        return this.resultCollection;
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        boolean bl;
        if (!Screen.hasControlDown()) {
            this.time += delta;
        }
        int i = 29;
        if (!this.resultCollection.hasCraftableRecipes()) {
            i += 25;
        }
        int j = 206;
        if (this.resultCollection.getResults(this.brewingRecipeBook.isFilteringBrewable()).size() > 1) {
            j += 25;
        }
        bl = this.bounce > 0.0F;
        if (bl) {
            float f = 1.0F + 0.1F * (float) Math.sin(this.bounce / 15.0F * Math.PI);
            context.getMatrices().push();
            context.getMatrices().translate(this.getX() + 8, this.getY() + 12, 0.0f);
            context.getMatrices().scale(f, f, 1.0f);
            context.getMatrices().translate(-(this.getX() + 8), -(this.getY() + 12), 0.0f);
            this.bounce -= delta;
        }
        context.drawTexture(TEXTURE, this.getX(), this.getY(), i, j, this.width, this.height);
        List<BrewingRecipe> list = this.getResults();
        this.currentResultIndex = MathHelper.floor(this.time / 30.0F) % list.size();
        ItemStack itemStack = this.getDisplayStack();
        int k = 4;
        if (this.resultCollection.hasSingleOutput() && this.getResults().size() > 1) {
            context.drawItem(itemStack, this.getX() + k + 1, this.getY() + k + 1, 0, 10);
            --k;
        }
        context.drawItemWithoutEntity(itemStack, this.getX() + k, this.getY() + k);
        if (bl) {
            context.getMatrices().pop();
        }
    }

    private List<BrewingRecipe> getResults() {
        List<BrewingRecipe> list = this.resultCollection.getRecipes(true);
        if (!this.brewingRecipeBook.isFilteringBrewable()) {
            list.addAll(this.resultCollection.getRecipes(false));
        }
        return list;
    }

    public boolean hasResults() {
        return this.getResults().size() == 1;
    }

    public BrewingRecipe currentRecipe() {
        List<BrewingRecipe> list = this.getResults();
        return list.get(this.currentResultIndex);
    }

    public List<Text> getTooltip(boolean bl) { // param serves no purpose aside from separating from super method
        ArrayList<Text> list = Lists.newArrayList(Screen.getTooltipFromItem(MinecraftClient.getInstance(), this.getDisplayStack()));
        if (this.resultCollection.getResults(this.brewingRecipeBook.isFilteringBrewable()).size() > 1) {
            list.add(MORE_RECIPES_TEXT);
        }
        return list;
    }

    private ItemStack getDisplayStack() {
        return this.getResults().get(this.currentResultIndex).getOutput();
    }

    @Override
    public void appendClickableNarrations(NarrationMessageBuilder builder) {
        ItemStack itemStack = this.getDisplayStack();
        builder.put(NarrationPart.TITLE, Text.translatable("narration.recipe", itemStack.getName()));
        if (this.resultCollection.getResults(this.brewingRecipeBook.isFilteringBrewable()).size() > 1) {
            builder.put(NarrationPart.USAGE, Text.translatable("narration.button.usage.hovered"), Text.translatable("narration.recipe.usage.more"));
        } else {
            builder.put(NarrationPart.USAGE, Text.translatable("narration.button.usage.hovered"));
        }
    }

    @Override
    public int getWidth() {
        return 25;
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return button == 0 || button == 1;
    }
}
