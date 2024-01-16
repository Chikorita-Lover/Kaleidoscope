package com.chikoritalover.kaleidoscope.mixin;

import com.chikoritalover.kaleidoscope.client.gui.screen.BrewingRecipeBookWidget;
import com.chikoritalover.kaleidoscope.screen.KaleidoscopeBrewingStandScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandScreen.class)
public abstract class BrewingStandScreenMixin extends HandledScreen<BrewingStandScreenHandler> implements KaleidoscopeBrewingStandScreen {
    @Unique
    private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");
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
        this.brewingRecipeBook.update();
    }

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"), index = 1)
    public int drawTexture(int x) {
        return x + this.x - (this.width - this.backgroundWidth) / 2;
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
    public void kaleidoscope$refreshRecipeBook() {
        this.brewingRecipeBook.refresh();
    }

    @Unique
    public BrewingRecipeBookWidget kaleidoscope$getRecipeBookWidget() {
        return this.brewingRecipeBook;
    }
}
