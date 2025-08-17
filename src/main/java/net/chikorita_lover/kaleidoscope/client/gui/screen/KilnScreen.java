package net.chikorita_lover.kaleidoscope.client.gui.screen;

import net.chikorita_lover.chicory.api.recipe.RecipeScreenHelper;
import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.recipe.KaleidoscopeRecipeBookCategories;
import net.chikorita_lover.kaleidoscope.recipe.KilningRecipe;
import net.chikorita_lover.kaleidoscope.screen.KilnScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnScreenHandler> {
    private static final Identifier LIT_PROGRESS_TEXTURE = Kaleidoscope.of("container/kiln/lit_progress");
    private static final Identifier BURN_PROGRESS_TEXTURE = Kaleidoscope.of("container/kiln/burn_progress");
    private static final Identifier TEXTURE = Kaleidoscope.of("textures/gui/container/kiln.png");
    private static final Text TOGGLE_KILNABLE_TEXT = Text.translatable("gui.recipebook.toggleRecipes.kilnable");
    private static final List<RecipeBookWidget.Tab> TABS = List.of(RecipeScreenHelper.createTab(Kaleidoscope.KILNING_CATEGORY), new RecipeBookWidget.Tab(Blocks.STONE.asItem(), KaleidoscopeRecipeBookCategories.KILN_BLOCKS), new RecipeBookWidget.Tab(Items.LAVA_BUCKET, Items.CHARCOAL, KaleidoscopeRecipeBookCategories.KILN_MISC));

    public KilnScreen(KilnScreenHandler container, PlayerInventory inventory, Text title) {
        super(container, inventory, title, TOGGLE_KILNABLE_TEXT, TEXTURE, LIT_PROGRESS_TEXTURE, BURN_PROGRESS_TEXTURE, TABS);
    }
}
