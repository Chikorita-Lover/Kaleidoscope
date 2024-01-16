package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.BrewingStandScreenHandler;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeGroupButtonWidget extends ToggleButtonWidget {
    private static final float field_32412 = 15.0F;
    private final ClientBrewingRecipeBook.Group category;
    private float bounce;

    public BrewingRecipeGroupButtonWidget(ClientBrewingRecipeBook.Group category) {
        super(0, 0, 35, 27, false);
        this.category = category;
        this.setTextureUV(153, 2, 35, 0, BrewingRecipeBookWidget.TEXTURE);
    }

    public void checkForNewRecipes(MinecraftClient client) {
        ClientBrewingRecipeBook clientBrewingRecipeBook = ((KaleidoscopePlayerEntity) client.player).kaleidoscope$getClientBrewingRecipeBook();
        List<BrewingRecipeResultCollection> list = clientBrewingRecipeBook.getResultsForGroup(this.category);
        if (!(client.player.currentScreenHandler instanceof BrewingStandScreenHandler)) {
            return;
        }
        for (BrewingRecipeResultCollection recipeResultCollection : list) {
            for (BrewingRecipe recipe : recipeResultCollection.getResults(clientBrewingRecipeBook.isFilteringBrewable())) {
                if (!clientBrewingRecipeBook.shouldDisplay(recipe)) continue;
                this.bounce = field_32412;
                return;
            }
        }
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.bounce > 0.0F) {
            float f = 1.0F + 0.1F * (float) Math.sin(this.bounce / field_32412 * Math.PI);
            context.getMatrices().push();
            context.getMatrices().translate(this.getX() + 8, this.getY() + 12, 0.0F);
            context.getMatrices().scale(1.0F, f, 1.0F);
            context.getMatrices().translate(-(this.getX() + 8), -(this.getY() + 12), 0.0F);
        }
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        RenderSystem.disableDepthTest();
        int i = this.u;
        int j = this.v;
        if (this.toggled) {
            i += this.pressedUOffset;
        }
        if (this.isSelected()) {
            j += this.hoverVOffset;
        }
        int k = this.getX();
        if (this.toggled) {
            k -= 2;
        }
        context.drawTexture(this.texture, k, this.getY(), i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
        this.renderIcons(context, minecraftClient.getItemRenderer());
        if (this.bounce > 0.0F) {
            context.getMatrices().pop();
            this.bounce -= delta;
        }
    }

    private void renderIcons(DrawContext context, ItemRenderer itemRenderer) {
        int i = this.toggled ? -2 : 0;
        List<ItemStack> list = this.category.getIcons();
        if (list.size() == 1) {
            context.drawItemWithoutEntity(list.get(0), this.getX() + 9 + i, this.getY() + 5);
        } else if (list.size() == 2) {
            context.drawItemWithoutEntity(list.get(0), this.getX() + 3 + i, this.getY() + 5);
            context.drawItemWithoutEntity(list.get(1), this.getX() + 14 + i, this.getY() + 5);
        }
    }

    public ClientBrewingRecipeBook.Group getCategory() {
        return this.category;
    }

    public boolean hasKnownRecipes(ClientBrewingRecipeBook brewingRecipeBook) {
        List<BrewingRecipeResultCollection> list = brewingRecipeBook.getResultsForGroup(this.category);
        this.visible = false;
        if (list != null) {
            for (BrewingRecipeResultCollection recipeResultCollection : list) {
                if (!recipeResultCollection.isInitialized() || !recipeResultCollection.hasFittingRecipes()) continue;
                this.visible = true;
                break;
            }
        }
        return this.visible;
    }
}
