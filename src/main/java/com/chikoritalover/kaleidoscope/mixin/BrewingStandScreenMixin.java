package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.Kaleidoscope;
import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import com.chikoritalover.kaleidoscope.screen.KaleidoscopeBrewingStandScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(BrewingStandScreen.class)
public abstract class BrewingStandScreenMixin extends HandledScreen<BrewingStandScreenHandler> implements KaleidoscopeBrewingStandScreen {
    @Unique
    private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");
    @Unique
    private static final Identifier EMPTY_SLOT_POTION_TEXTURE = new Identifier(Kaleidoscope.MODID, "item/empty_slot_potion");
    @Unique
    private static final Identifier EMPTY_SLOT_SPLASH_POTION_TEXTURE = new Identifier(Kaleidoscope.MODID, "item/empty_slot_splash_potion");
    @Unique
    private static final Identifier EMPTY_SLOT_LINGERING_POTION_TEXTURE = new Identifier(Kaleidoscope.MODID, "item/empty_slot_lingering_potion");
    @Unique
    private static final Identifier EMPTY_SLOT_BLAZE_POWDER_TEXTURE = new Identifier(Kaleidoscope.MODID, "textures/item/empty_slot_blaze_powder.png");
    @Unique
    private static final Text MISSING_POTION_TOOLTIP = Text.translatable("container.brewing.missing_potion_tooltip");
    @Unique
    private static final Text ADD_BLAZE_POWDER_TOOLTIP = Text.translatable("container.brewing.add_blaze_powder_tooltip");
    @Unique
    private static final List<Identifier> EMPTY_SLOT_TEXTURES = List.of(EMPTY_SLOT_POTION_TEXTURE, EMPTY_SLOT_SPLASH_POTION_TEXTURE, EMPTY_SLOT_LINGERING_POTION_TEXTURE);
    @Unique
    private final CyclingSlotIcon potionSlotIcon = new CyclingSlotIcon(0);
    @Unique
    private final CyclingSlotIcon potionSlotIcon2 = new CyclingSlotIcon(1);
    @Unique
    private final CyclingSlotIcon potionSlotIcon3 = new CyclingSlotIcon(2);
    @Unique
    private BrewingRecipeBookWidget brewingRecipeBook;
    @Unique
    private boolean narrow;

    public BrewingStandScreenMixin(BrewingStandScreenHandler handler, PlayerInventory inventory, Text title, boolean narrow) {
        super(handler, inventory, title);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(BrewingStandScreenHandler handler, PlayerInventory inventory, Text title, CallbackInfo ci) {
        this.brewingRecipeBook = new BrewingRecipeBookWidget();
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo ci) {
        this.narrow = this.width < 379;
        this.brewingRecipeBook.initialize(this.width, this.height, this.client, this.narrow, this.handler);
        this.x = this.brewingRecipeBook.findLeftEdge(this.width, this.backgroundWidth);
        this.addDrawableChild(new TexturedButtonWidget(this.x + 15, this.height / 2 - 33, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE, button -> {
            this.brewingRecipeBook.toggleOpen();
            this.x = this.brewingRecipeBook.findLeftEdge(this.width, this.backgroundWidth);
            button.setPosition(this.x + 15, this.height / 2 - 33);
        }));
        this.addSelectableChild(this.brewingRecipeBook);
        this.setInitialFocus(this.brewingRecipeBook);
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        this.brewingRecipeBook.update();
        this.potionSlotIcon.updateTexture(EMPTY_SLOT_TEXTURES);
        this.potionSlotIcon2.updateTexture(EMPTY_SLOT_TEXTURES);
        this.potionSlotIcon3.updateTexture(EMPTY_SLOT_TEXTURES);
    }

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"), index = 1)
    public int drawTexture(int x) {
        return x + this.x - (this.width - this.backgroundWidth) / 2;
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    public void drawBackground(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        if (!this.kaleidoscope$getRecipeBookWidget().hasGhostRecipe()) {
            this.potionSlotIcon.render(this.handler, context, delta, this.x, this.y);
            this.potionSlotIcon2.render(this.handler, context, delta, this.x, this.y);
            this.potionSlotIcon3.render(this.handler, context, delta, this.x, this.y);
        }
        if (!this.getScreenHandler().getSlot(4).hasStack()) {
            context.drawTexture(EMPTY_SLOT_BLAZE_POWDER_TEXTURE, this.x + 17, this.y + 17, 0, 0, 16, 16, 16, 16);
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.brewingRecipeBook.isOpen() && this.narrow) {
            this.drawBackground(context, delta, mouseX, mouseY);
            this.brewingRecipeBook.render(context, mouseX, mouseY, delta);
        } else {
            this.brewingRecipeBook.render(context, mouseX, mouseY, delta);
            super.render(context, mouseX, mouseY, delta);
            this.brewingRecipeBook.drawGhostSlots(context, this.x, this.y, delta);
        }
        this.drawMouseoverTooltip(context, mouseX, mouseY);
        this.renderSlotTooltip(context, mouseX, mouseY);
        this.brewingRecipeBook.drawTooltip(context, this.x, this.y, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.brewingRecipeBook.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (this.narrow && this.brewingRecipeBook.isOpen()) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType) {
        super.onMouseClick(slot, slotId, button, actionType);
        this.brewingRecipeBook.slotClicked(slot);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.brewingRecipeBook.keyPressed(keyCode, scanCode, modifiers)) {
            return false;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button) {
        boolean bl = mouseX < left || mouseY < top || mouseX >= left + this.backgroundWidth || mouseY >= top + this.backgroundHeight;
        return this.brewingRecipeBook.isClickOutsideBounds(mouseX, mouseY, this.x, this.y, this.backgroundWidth, this.backgroundHeight, button) && bl;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.brewingRecipeBook.charTyped(chr, modifiers)) {
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    @Unique
    private void renderSlotTooltip(DrawContext context, int mouseX, int mouseY) {
        Optional<Text> optional = Optional.empty();
        if (this.focusedSlot != null) {
            ItemStack itemStack = this.focusedSlot.getStack();
            if (itemStack.isEmpty()) {
                if (this.focusedSlot.id < 3 && !this.kaleidoscope$getRecipeBookWidget().hasGhostRecipe()) {
                    optional = Optional.of(MISSING_POTION_TOOLTIP);
                }
                if (this.focusedSlot.id == 4) {
                    optional = Optional.of(ADD_BLAZE_POWDER_TOOLTIP);
                }
            }
        }
        optional.ifPresent(text -> context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 135), mouseX, mouseY));
    }

    @Unique
    public void kaleidoscope$refreshRecipeBook() {
        this.brewingRecipeBook.refresh();
    }

    @Unique
    public BrewingRecipeBookWidget kaleidoscope$getRecipeBookWidget() {
        return this.brewingRecipeBook;
    }
}
