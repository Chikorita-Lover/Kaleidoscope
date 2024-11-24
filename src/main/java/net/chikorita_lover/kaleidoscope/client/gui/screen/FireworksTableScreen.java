package net.chikorita_lover.kaleidoscope.client.gui.screen;

import net.chikorita_lover.kaleidoscope.Kaleidoscope;
import net.chikorita_lover.kaleidoscope.registry.tag.KaleidoscopeItemTags;
import net.chikorita_lover.kaleidoscope.screen.FireworksTableScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CyclingSlotIcon;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class FireworksTableScreen extends HandledScreen<FireworksTableScreenHandler> {
    private static final Identifier TEXTURE = Kaleidoscope.of("textures/gui/container/fireworks_table.png");
    private static final Identifier EMPTY_SLOT_DIAMOND_TEXTURE = Identifier.of(Identifier.DEFAULT_NAMESPACE, "item/empty_slot_diamond");
    private static final Identifier EMPTY_SLOT_FIREWORK_SHELL_TEXTURE = Kaleidoscope.of("item/empty_slot_firework_shell");
    private static final Identifier EMPTY_SLOT_FIREWORK_STAR_TEXTURE = Kaleidoscope.of("item/empty_slot_firework_star");
    private static final Identifier EMPTY_SLOT_GLOWSTONE_DUST_TEXTURE = Kaleidoscope.of("item/empty_slot_glowstone_dust");
    private static final Identifier EMPTY_SLOT_GUNPOWDER_TEXTURE = Kaleidoscope.of("item/empty_slot_gunpowder");
    private static final Identifier EMPTY_SLOT_PAPER_TEXTURE = Kaleidoscope.of("item/empty_slot_paper");
    private static final Text ADD_DIAMOND_TOOLTIP = Text.translatable("container.fireworks_table.add_diamond_tooltip");
    private static final Text ADD_DYE_TOOLTIP = Text.translatable("container.fireworks_table.add_dye_tooltip");
    private static final Text ADD_FIREWORK_SHELL_TOOLTIP = Text.translatable("container.fireworks_table.add_firework_shell_tooltip");
    private static final Text ADD_FIREWORK_STAR_TOOLTIP = Text.translatable("container.fireworks_table.add_firework_star_tooltip");
    private static final Text ADD_GLOWSTONE_DUST_TOOLTIP = Text.translatable("container.fireworks_table.add_glowstone_dust_tooltip");
    private static final Text ADD_GUNPOWDER_TOOLTIP = Text.translatable("container.fireworks_table.add_gunpowder_tooltip");
    private static final Text MISSING_BASE_TOOLTIP = Text.translatable("container.fireworks_table.missing_base_tooltip");
    private static final List<Identifier> BASE_SLOT_TEXTURES = List.of(EMPTY_SLOT_GUNPOWDER_TEXTURE, EMPTY_SLOT_FIREWORK_STAR_TEXTURE, EMPTY_SLOT_PAPER_TEXTURE);
    private static final List<Identifier> FIREWORK_STAR_MODIFIER_TEXTURES = List.of(EMPTY_SLOT_FIREWORK_SHELL_TEXTURE, EMPTY_SLOT_GLOWSTONE_DUST_TEXTURE, EMPTY_SLOT_DIAMOND_TEXTURE);
    private final CyclingSlotIcon baseSlotIcon = new CyclingSlotIcon(0);
    private final CyclingSlotIcon modifierSlotIcon = new CyclingSlotIcon(1);
    private final CyclingSlotIcon modifierSlotIcon2 = new CyclingSlotIcon(2);
    private final CyclingSlotIcon modifierSlotIcon3 = new CyclingSlotIcon(3);

    public FireworksTableScreen(FireworksTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.titleY = 5;
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        ItemStack baseStack = this.getScreenHandler().getBaseSlot().getStack();
        this.baseSlotIcon.updateTexture(BASE_SLOT_TEXTURES);
        BiConsumer<CyclingSlotIcon, Integer> consumer = (slot, i) -> {
            if (!baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) && this.getScreenHandler().slots.subList(0, i).stream().anyMatch(slot2 -> !slot2.hasStack())) {
                slot.updateTexture(List.of());
                return;
            }
            slot.updateTexture(baseStack.isIn(KaleidoscopeItemTags.FIREWORK_STAR_BASES) ? List.of(FIREWORK_STAR_MODIFIER_TEXTURES.get(i - 1)) : baseStack.isOf(Items.PAPER) ? List.of(EMPTY_SLOT_GUNPOWDER_TEXTURE) : List.of());
        };
        consumer.accept(this.modifierSlotIcon, 1);
        consumer.accept(this.modifierSlotIcon2, 2);
        consumer.accept(this.modifierSlotIcon3, 3);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderSlotTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        this.renderBackground(context);
        context.drawTexture(TEXTURE, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawInvalidInputArrow(context, this.x, this.y);
        this.baseSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.modifierSlotIcon.render(this.handler, context, delta, this.x, this.y);
        this.modifierSlotIcon2.render(this.handler, context, delta, this.x, this.y);
        this.modifierSlotIcon3.render(this.handler, context, delta, this.x, this.y);
        if (this.getScreenHandler().getBaseSlot().hasStack()) {
            context.drawTexture(TEXTURE, this.x + 74, this.y + 16, 0, this.backgroundHeight, 54, 54);
            int i = 0;
            for (Slot slot : this.getScreenHandler().slots.subList(4, 12)) {
                if (!slot.hasStack()) break;
                ++i;
            }
            if (i < 8) {
                context.drawTexture(TEXTURE, this.x + 74 + (i % 3) * 18, this.y + 18 + (Math.floorDiv(i, 3) * 18), this.getScreenHandler().getBaseSlot().getStack().isOf(Items.PAPER) ? 18 : 0, this.backgroundHeight + 54, 18, 18);
            }
        }
    }

    private void drawInvalidInputArrow(DrawContext context, int x, int y) {
        if (this.getScreenHandler().hasInvalidInputs()) {
            context.drawTexture(TEXTURE, x + 133, y + 16, this.backgroundWidth, 0, 28, 23);
        }
    }

    private void renderSlotTooltip(DrawContext context, int mouseX, int mouseY) {
        Optional<Text> optional = Optional.empty();
        if (this.getScreenHandler().hasInvalidInputs() && this.isPointWithinBounds(133, 16, 28, 23, mouseX, mouseY)) {
            optional = Optional.ofNullable(this.getScreenHandler().getInputError().getTooltip());
        }
        if (this.focusedSlot != null) {
            ItemStack itemStack = this.getScreenHandler().getBaseSlot().getStack();
            ItemStack itemStack2 = this.focusedSlot.getStack();
            if (itemStack.isEmpty()) {
                if (this.focusedSlot.id == 0) {
                    optional = Optional.of(MISSING_BASE_TOOLTIP);
                }
            } else {
                if (itemStack2.isEmpty() && this.focusedSlot.id >= 1 && this.focusedSlot.id < 4) {
                    optional = Optional.of(itemStack.isOf(Items.PAPER) ? ADD_GUNPOWDER_TOOLTIP : List.of(ADD_FIREWORK_SHELL_TOOLTIP, ADD_GLOWSTONE_DUST_TOOLTIP, ADD_DIAMOND_TOOLTIP).get(this.focusedSlot.id - 1));
                }
                if (itemStack2.isEmpty() && this.focusedSlot.id >= 4 && this.focusedSlot.id < 12) {
                    optional = itemStack.isOf(Items.PAPER) ? Optional.of(ADD_FIREWORK_STAR_TOOLTIP) : Optional.of(ADD_DYE_TOOLTIP);
                }
            }
        }
        optional.ifPresentOrElse(text -> context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 175), mouseX, mouseY), () -> this.drawMouseoverTooltip(context, mouseX, mouseY));
    }
}
