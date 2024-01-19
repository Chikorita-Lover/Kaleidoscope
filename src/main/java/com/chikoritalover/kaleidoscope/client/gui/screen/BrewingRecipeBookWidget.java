package com.chikoritalover.kaleidoscope.client.gui.screen;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.client.ClientBrewingRecipeBook;
import com.chikoritalover.kaleidoscope.entity.KaleidoscopePlayerEntity;
import com.chikoritalover.kaleidoscope.network.BrewRequestC2SPacket;
import com.chikoritalover.kaleidoscope.network.BrewingRecipeBookOptionsC2SPacket;
import com.chikoritalover.kaleidoscope.network.KaleidoscopePlayNetworking;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipe;
import com.chikoritalover.kaleidoscope.recipe.BrewingRecipeMatcher;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleButtonWidget;
import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.search.SearchManager;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Environment(value = EnvType.CLIENT)
public class BrewingRecipeBookWidget implements Drawable, Element, Selectable {
    public static final SearchManager.Key<BrewingRecipeResultCollection> BREWING_RECIPE_OUTPUT = new SearchManager.Key<>();
    protected static final Identifier TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/gui/brewing_recipe_book.png");
    private static final Text SEARCH_HINT_TEXT = Text.translatable("gui.recipebook.search_hint").formatted(Formatting.ITALIC).formatted(Formatting.GRAY);
    private static final Text TOGGLE_BREWABLE_RECIPES_TEXT = Text.translatable("gui.recipebook.toggleRecipes.brewable");
    private static final Text TOGGLE_ALL_RECIPES_TEXT = Text.translatable("gui.recipebook.toggleRecipes.all");
    protected final BrewingRecipeBookGhostSlots ghostSlots = new BrewingRecipeBookGhostSlots();
    private final List<BrewingRecipeGroupButtonWidget> tabButtons = Lists.newArrayList();
    private final BrewingRecipeBookResults recipesArea = new BrewingRecipeBookResults();
    private final BrewingRecipeMatcher recipeFinder = new BrewingRecipeMatcher();
    protected ToggleButtonWidget toggleCraftableButton;
    protected BrewingStandScreenHandler brewingStandScreenHandler;
    protected MinecraftClient client;
    private int leftOffset;
    private int parentWidth;
    private int parentHeight;
    @Nullable
    private BrewingRecipeGroupButtonWidget currentTab;
    @Nullable
    private TextFieldWidget searchField;
    private String searchText = "";
    private ClientBrewingRecipeBook recipeBook;
    private int cachedInvChangeCount;
    private boolean searching;
    private boolean open;
    private boolean narrow;

    public void initialize(int parentWidth, int parentHeight, MinecraftClient client, boolean narrow, BrewingStandScreenHandler brewingStandScreenHandler) {
        this.client = client;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.brewingStandScreenHandler = brewingStandScreenHandler;
        this.narrow = narrow;
        client.player.currentScreenHandler = brewingStandScreenHandler;
        this.recipeBook = ((KaleidoscopePlayerEntity) client.player).kaleidoscope$getClientBrewingRecipeBook();
        this.cachedInvChangeCount = client.player.getInventory().getChangeCount();
        this.open = this.isGuiOpen();
        if (this.open) {
            this.reset();
        }
    }

    public void reset() {
        this.leftOffset = this.narrow ? 0 : 86;
        int i = (this.parentWidth - 147) / 2 - this.leftOffset;
        int j = (this.parentHeight - 166) / 2;
        String string = this.searchField != null ? this.searchField.getText() : "";
        this.searchField = new TextFieldWidget(this.client.textRenderer, i + 26, j + 14, 79, this.client.textRenderer.fontHeight + 3, Text.translatable("itemGroup.search"));
        this.searchField.setMaxLength(50);
        this.searchField.setVisible(true);
        this.searchField.setEditableColor(0xFFFFFF);
        this.searchField.setText(string);
        this.searchField.setPlaceholder(SEARCH_HINT_TEXT);
        this.recipesArea.initialize(this.client, i, j);
        this.recipesArea.setGui(this);
        this.toggleCraftableButton = new ToggleButtonWidget(i + 110, j + 12, 26, 16, this.recipeBook.isFilteringBrewable());
        this.updateTooltip();
        this.setBookButtonTexture();
        this.tabButtons.clear();
        this.tabButtons.add(new BrewingRecipeGroupButtonWidget(ClientBrewingRecipeBook.Group.SEARCH));
        this.tabButtons.add(new BrewingRecipeGroupButtonWidget(ClientBrewingRecipeBook.Group.EFFECTS));
        this.tabButtons.add(new BrewingRecipeGroupButtonWidget(ClientBrewingRecipeBook.Group.STRONG_LONG));
        this.tabButtons.add(new BrewingRecipeGroupButtonWidget(ClientBrewingRecipeBook.Group.MISC));
        if (this.currentTab != null) {
            this.currentTab = this.tabButtons.stream().filter(button -> button.getCategory().equals(this.currentTab.getCategory())).findFirst().orElse(null);
        }
        if (this.currentTab == null) {
            this.currentTab = this.tabButtons.get(0);
        }
        this.currentTab.setToggled(true);
        this.refreshInputs();
        this.refreshTabButtons();
    }

    private void updateTooltip() {
        this.toggleCraftableButton.setTooltip(this.toggleCraftableButton.isToggled() ? Tooltip.of(this.getToggleCraftableButtonText()) : Tooltip.of(TOGGLE_ALL_RECIPES_TEXT));
    }

    protected void setBookButtonTexture() {
        this.toggleCraftableButton.setTextureUV(152, 41, 28, 18, TEXTURE);
    }

    public int findLeftEdge(int width, int backgroundWidth) {
        return this.isOpen() && !this.narrow ? 177 + (width - backgroundWidth - 200) / 2 : (width - backgroundWidth) / 2;
    }

    public void toggleOpen() {
        this.setOpen(!this.isOpen());
    }

    public boolean isOpen() {
        return this.open;
    }

    protected void setOpen(boolean opened) {
        if (opened) {
            this.reset();
        }
        this.open = opened;
        this.recipeBook.setGuiOpen(opened);
        if (!opened) {
            this.recipesArea.hideAlternates();
        }
        this.sendBookDataPacket();
    }

    private boolean isGuiOpen() {
        return this.recipeBook.isGuiOpen();
    }

    public void slotClicked(@Nullable Slot slot) {
        if (slot != null && slot.id < 5) {
            this.ghostSlots.reset();
            if (this.isOpen()) {
                this.refreshInputs();
            }
        }
    }

    private void refreshResults(boolean resetCurrentPage) {
        List<BrewingRecipeResultCollection> list = this.recipeBook.getResultsForGroup(this.currentTab.getCategory());
        list.forEach(brewingRecipeResultCollection -> brewingRecipeResultCollection.computeCraftables(this.recipeFinder, this.recipeBook));
        ArrayList<BrewingRecipeResultCollection> list2 = Lists.newArrayList(list);
        list2.removeIf(resultCollection -> !resultCollection.isInitialized());
        list2.removeIf(resultCollection -> !resultCollection.hasFittingRecipes());
        String string = this.searchField.getText();
        if (!string.isEmpty()) {
            ObjectLinkedOpenHashSet<BrewingRecipeResultCollection> objectSet = new ObjectLinkedOpenHashSet<>(this.client.getSearchProvider(BREWING_RECIPE_OUTPUT).findAll(string.toLowerCase(Locale.ROOT)));
            list2.removeIf(brewingRecipeResultCollection -> !objectSet.contains(brewingRecipeResultCollection));
        }
        if (this.recipeBook.isFilteringBrewable()) {
            list2.removeIf(resultCollection -> !resultCollection.hasCraftableRecipes());
        }
        this.recipesArea.setResults(list2, resetCurrentPage);
    }

    private void refreshTabButtons() {
        int i = (this.parentWidth - 147) / 2 - this.leftOffset - 30;
        int j = (this.parentHeight - 166) / 2 + 3;
        int l = 0;
        for (BrewingRecipeGroupButtonWidget brewingRecipeGroupButtonWidget : this.tabButtons) {
            ClientBrewingRecipeBook.Group group = brewingRecipeGroupButtonWidget.getCategory();
            if (group == ClientBrewingRecipeBook.Group.SEARCH) {
                brewingRecipeGroupButtonWidget.visible = true;
                brewingRecipeGroupButtonWidget.setPosition(i, j + 27 * l++);
                continue;
            }
            if (!brewingRecipeGroupButtonWidget.hasKnownRecipes(this.recipeBook)) continue;
            brewingRecipeGroupButtonWidget.setPosition(i, j + 27 * l++);
            brewingRecipeGroupButtonWidget.checkForNewRecipes(this.client);
        }
    }

    public void update() {
        boolean bl = this.isGuiOpen();
        if (this.isOpen() != bl) {
            this.setOpen(bl);
        }
        if (!this.isOpen()) {
            return;
        }
        if (this.cachedInvChangeCount != this.client.player.getInventory().getChangeCount()) {
            this.refreshInputs();
            this.cachedInvChangeCount = this.client.player.getInventory().getChangeCount();
        }
        this.searchField.tick();
    }

    public void refreshInputs() {
        this.recipeFinder.clear();
        for (ItemStack itemStack : this.client.player.getInventory().main) {
            this.recipeFinder.addInput(itemStack);
        }
        for (int i = 0; i < 5; ++i) {
            this.recipeFinder.addInput(this.brewingStandScreenHandler.getSlot(i).getStack());
        }
        this.refreshResults(false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (!this.isOpen()) {
            return;
        }
        context.getMatrices().push();
        context.getMatrices().translate(0.0f, 0.0f, 100.0f);
        int i = (this.parentWidth - 147) / 2 - this.leftOffset;
        int j = (this.parentHeight - 166) / 2;
        context.drawTexture(TEXTURE, i, j, 1, 1, 147, 166);
        this.searchField.render(context, mouseX, mouseY, delta);
        for (BrewingRecipeGroupButtonWidget brewingRecipeGroupButtonWidget : this.tabButtons) {
            brewingRecipeGroupButtonWidget.render(context, mouseX, mouseY, delta);
        }
        this.toggleCraftableButton.render(context, mouseX, mouseY, delta);
        this.recipesArea.draw(context, i, j, mouseX, mouseY, delta);
        context.getMatrices().pop();
    }

    public void drawTooltip(DrawContext context, int x, int y, int mouseX, int mouseY) {
        if (!this.isOpen()) {
            return;
        }
        this.recipesArea.drawTooltip(context, mouseX, mouseY);
        this.drawGhostSlotTooltip(context, x, y, mouseX, mouseY);
    }

    protected Text getToggleCraftableButtonText() {
        return TOGGLE_BREWABLE_RECIPES_TEXT;
    }

    private void drawGhostSlotTooltip(DrawContext context, int x, int y, int mouseX, int mouseY) {
        ItemStack itemStack = null;
        for (int i = 0; i < this.ghostSlots.getSlotCount(); ++i) {
            BrewingRecipeBookGhostSlots.GhostInputSlot ghostInputSlot = this.ghostSlots.getSlot(i);
            int j = ghostInputSlot.getX() + x;
            int k = ghostInputSlot.getY() + y;
            if (mouseX < j || mouseY < k || mouseX >= j + 16 || mouseY >= k + 16) continue;
            itemStack = ghostInputSlot.getCurrentItemStack();
        }
        if (itemStack != null && this.client.currentScreen != null) {
            context.drawTooltip(this.client.textRenderer, Screen.getTooltipFromItem(this.client, itemStack), mouseX, mouseY);
        }
    }

    public void drawGhostSlots(DrawContext context, int x, int y, float delta) {
        this.ghostSlots.draw(context, this.client, x, y, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.isOpen() || this.client.player.isSpectator()) {
            return false;
        }
        if (this.recipesArea.mouseClicked(mouseX, mouseY, button, (this.parentWidth - 147) / 2 - this.leftOffset, (this.parentHeight - 166) / 2, 147, 166)) {
            BrewingRecipe recipe = this.recipesArea.getLastClickedRecipe();
            BrewingRecipeResultCollection brewingRecipeResultCollection = this.recipesArea.getLastClickedResults();
            if (recipe != null && brewingRecipeResultCollection != null) {
                if (!brewingRecipeResultCollection.isCraftable(recipe) && this.ghostSlots.getRecipe() == recipe) {
                    return false;
                }
                this.ghostSlots.reset();
                PacketByteBuf buf = PacketByteBufs.create();
                BrewRequestC2SPacket packet = new BrewRequestC2SPacket(this.client.player.currentScreenHandler.syncId, recipe, Screen.hasShiftDown());
                packet.write(buf);
                ClientPlayNetworking.send(KaleidoscopePlayNetworking.BREW_REQUEST, buf);
                if (!this.isWide()) {
                    this.setOpen(false);
                }
            }
            return true;
        }
        if (this.searchField.mouseClicked(mouseX, mouseY, button)) {
            this.searchField.setFocused(true);
            return true;
        }
        this.searchField.setFocused(false);
        if (this.toggleCraftableButton.mouseClicked(mouseX, mouseY, button)) {
            boolean bl = this.toggleFilteringCraftable();
            this.toggleCraftableButton.setToggled(bl);
            this.updateTooltip();
            this.sendBookDataPacket();
            this.refreshResults(false);
            return true;
        }
        for (BrewingRecipeGroupButtonWidget brewingRecipeGroupButtonWidget : this.tabButtons) {
            if (!brewingRecipeGroupButtonWidget.mouseClicked(mouseX, mouseY, button)) continue;
            if (this.currentTab != brewingRecipeGroupButtonWidget) {
                if (this.currentTab != null) {
                    this.currentTab.setToggled(false);
                }
                this.currentTab = brewingRecipeGroupButtonWidget;
                this.currentTab.setToggled(true);
                this.refreshResults(true);
            }
            return true;
        }
        return false;
    }

    private boolean toggleFilteringCraftable() {
        boolean bl = !this.recipeBook.isFilteringBrewable();
        this.recipeBook.setFilteringBrewable(bl);
        return bl;
    }

    public boolean isClickOutsideBounds(double mouseX, double mouseY, int x, int y, int backgroundWidth, int backgroundHeight, int button) {
        if (!this.isOpen()) {
            return true;
        }
        boolean bl = mouseX < x || mouseY < y || mouseX >= x + backgroundWidth || mouseY >= y + backgroundHeight;
        boolean bl2 = x - 147 < mouseX && mouseX < x && y < mouseY && mouseY < y + backgroundHeight;
        return bl && !bl2 && !this.currentTab.isSelected();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.searching = false;
        if (!this.isOpen() || this.client.player.isSpectator()) {
            return false;
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && !this.isWide()) {
            this.setOpen(false);
            return true;
        }
        if (this.searchField.keyPressed(keyCode, scanCode, modifiers)) {
            this.refreshSearchResults();
            return true;
        }
        if (this.searchField.isFocused() && this.searchField.isVisible() && keyCode != GLFW.GLFW_KEY_ESCAPE) {
            return true;
        }
        if (this.client.options.chatKey.matchesKey(keyCode, scanCode) && !this.searchField.isFocused()) {
            this.searching = true;
            this.searchField.setFocused(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        this.searching = false;
        return Element.super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.searching) {
            return false;
        }
        if (!this.isOpen() || this.client.player.isSpectator()) {
            return false;
        }
        if (this.searchField.charTyped(chr, modifiers)) {
            this.refreshSearchResults();
            return true;
        }
        return Element.super.charTyped(chr, modifiers);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return false;
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public void setFocused(boolean focused) {
    }

    private void refreshSearchResults() {
        String string = this.searchField.getText().toLowerCase(Locale.ROOT);
        this.triggerPirateSpeakEasterEgg(string);
        if (!string.equals(this.searchText)) {
            this.refreshResults(false);
            this.searchText = string;
        }
    }

    private void triggerPirateSpeakEasterEgg(String search) {
        if ("excitedze".equals(search)) {
            LanguageManager languageManager = this.client.getLanguageManager();
            String string = "en_pt";
            LanguageDefinition languageDefinition = languageManager.getLanguage(string);
            if (languageDefinition == null || languageManager.getLanguage().equals(string)) {
                return;
            }
            languageManager.setLanguage("en_pt");
            this.client.options.language = "en_pt";
            this.client.reloadResources();
            this.client.options.write();
        }
    }

    private boolean isWide() {
        return this.leftOffset == 86;
    }

    public void refresh() {
        this.refreshTabButtons();
        if (this.isOpen()) {
            this.refreshResults(false);
        }
    }

    public void onRecipesDisplayed(List<BrewingRecipe> recipes) {
        for (BrewingRecipe recipe : recipes) {
            ((KaleidoscopePlayerEntity) this.client.player).kaleidoscope$onBrewingRecipeDisplayed(recipe);
        }
    }

    public void showGhostRecipe(BrewingRecipe recipe, List<Slot> slots) {
        this.ghostSlots.setRecipe(recipe);
        for (int i = 0; i < 3; ++i) {
            this.ghostSlots.addSlot(recipe.getInput(), slots.get(i).x, slots.get(i).y);
        }
        this.ghostSlots.addSlot(recipe.getReagent(), slots.get(3).x, slots.get(3).y);
    }

    protected void sendBookDataPacket() {
        if (this.client.getNetworkHandler() != null) {
            boolean bl = this.recipeBook.isGuiOpen();
            boolean bl2 = this.recipeBook.isFilteringBrewable();
            PacketByteBuf packetByteBuf = PacketByteBufs.create();
            BrewingRecipeBookOptionsC2SPacket packet = new BrewingRecipeBookOptionsC2SPacket(bl, bl2);
            packet.write(packetByteBuf);
            ClientPlayNetworking.send(KaleidoscopePlayNetworking.BREWING_RECIPE_BOOK_OPTIONS, packetByteBuf);
        }
    }

    @Override
    public Selectable.SelectionType getType() {
        return this.open ? Selectable.SelectionType.HOVERED : Selectable.SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
        ArrayList<ClickableWidget> list = Lists.newArrayList();
        this.recipesArea.forEachButton(button -> {
            if (button.isNarratable()) {
                list.add(button);
            }
        });
        list.add(this.searchField);
        list.add(this.toggleCraftableButton);
        list.addAll(this.tabButtons);
        Screen.SelectedElementNarrationData selectedElementNarrationData = Screen.findSelectedElementData(list, null);
        if (selectedElementNarrationData != null) {
            selectedElementNarrationData.selectable.appendNarrations(builder.nextMessage());
        }
    }

    public boolean hasGhostRecipe() {
        return this.ghostSlots.getRecipe() != null;
    }
}
