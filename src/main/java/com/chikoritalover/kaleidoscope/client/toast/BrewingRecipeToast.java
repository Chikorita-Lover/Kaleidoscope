package com.chikoritalover.kaleidoscope.client.toast;

import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeToast implements Toast {
    private static final long DEFAULT_DURATION_MS = 5000L;
    private static final Text TITLE = Text.translatable("recipe.toast.title");
    private static final Text DESCRIPTION = Text.translatable("recipe.toast.description");
    private final List<BrewingRecipe> recipes = Lists.newArrayList();
    private long startTime;
    private boolean justUpdated;

    public BrewingRecipeToast(BrewingRecipe recipe) {
        this.recipes.add(recipe);
    }

    @Override
    public Toast.Visibility draw(DrawContext context, ToastManager manager, long startTime) {
        if (this.justUpdated) {
            this.startTime = startTime;
            this.justUpdated = false;
        }
        if (this.recipes.isEmpty()) {
            return Toast.Visibility.HIDE;
        }
        context.drawTexture(TEXTURE, 0, 0, 0, 32, this.getWidth(), this.getHeight());
        context.drawText(manager.getClient().textRenderer, TITLE, 30, 7, -11534256, false);
        context.drawText(manager.getClient().textRenderer, DESCRIPTION, 30, 18, -16777216, false);
        BrewingRecipe recipe = this.recipes.get((int) (startTime / Math.max(1.0, 5000.0 * manager.getNotificationDisplayTimeMultiplier() / this.recipes.size()) % this.recipes.size()));
        ItemStack itemStack = new ItemStack(Items.BREWING_STAND);
        context.getMatrices().push();
        context.getMatrices().scale(0.6F, 0.6F, 1.0F);
        context.drawItemWithoutEntity(itemStack, 3, 3);
        context.getMatrices().pop();
        context.drawItemWithoutEntity(recipe.getOutput(), 8, 8);
        return startTime - this.startTime >= 5000.0 * manager.getNotificationDisplayTimeMultiplier() ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
    }

    private void addRecipe(BrewingRecipe recipe) {
        this.recipes.add(recipe);
        this.justUpdated = true;
    }

    public static void show(ToastManager manager, BrewingRecipe recipe) {
        BrewingRecipeToast recipeToast = manager.getToast(BrewingRecipeToast.class, TYPE);
        if (recipeToast == null) {
            manager.add(new BrewingRecipeToast(recipe));
        } else {
            recipeToast.addRecipe(recipe);
        }
    }
}
